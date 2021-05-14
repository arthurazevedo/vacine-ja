package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.PerfilVacinacao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.PerfilVacinacaoRepository;
import com.ufcg.psoft.vacineja.utils.ConverterKeysUnicas;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroPerfilVacinacao;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class CidadaoService {
    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private PerfilVacinacaoRepository perfilVacinacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MapperUtil mapperUtil;

    @Value("${hash.forca}")
    private int forcaHash;

    public String pegarEstadoCidadao(String cpf) {
        Cidadao cidadao = pegarCidadaoPorCpf(cpf);
        return cidadao.exibeEstado();
    }

    public Cidadao pegarCidadaoPorCpf(String cpf) {
        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);
        if(cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf), HttpStatus.NOT_FOUND)
            );
        }
        return cidadaoOptional.get();
    }

    public void salvaCidadao(Cidadao cidadao) {
        cidadaoRepository.save(cidadao);
    }
    
    public boolean contemCidadao(String cpf) {
    	return cidadaoRepository.existsByCpf(cpf);
    }

    public Cidadao atualizaCidadao(String cpf, CidadaoUpdateDTO cidadaoUpdateDTO) {
       Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);
         if(cidadaoOptional.isEmpty()) {
             throw new ValidacaoException(
                  new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf))
             );
         }
         Cidadao cidadao = cidadaoOptional.get();

         if(cidadaoUpdateDTO.getComorbidades() != null) {
           cidadao.setComorbidades(cidadaoUpdateDTO.getComorbidades());
         }

         if(cidadaoUpdateDTO.getNome() != null) {
           cidadao.setNome(cidadaoUpdateDTO.getNome());
         }

         if(cidadaoUpdateDTO.getEndereco() != null) {
           cidadao.setEndereco(cidadaoUpdateDTO.getEndereco());
         }

         if(cidadaoUpdateDTO.getTelefone() != null) {
           cidadao.setTelefone(cidadaoUpdateDTO.getTelefone());
         }

         if(cidadaoUpdateDTO.getProfissao() != null) {
           cidadao.setProfissao(cidadaoUpdateDTO.getProfissao());
         }

         cidadaoRepository.save(cidadao);
         return cidadao;
    }

    public Cidadao salvarCidadao(CidadaoRequestDTO cidadaoRequestDTO) {
        validaCadastroPorEmailECpf(cidadaoRequestDTO);
        validaCidadaoRequestDTO(cidadaoRequestDTO);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(forcaHash);
        cidadaoRequestDTO.setSenha(encoder.encode(cidadaoRequestDTO.getSenha()));
        Usuario usuario = new Usuario(cidadaoRequestDTO);
        usuarioService.salvaUsuario(usuario);

        Cidadao cidadao = mapperUtil.toEntity(cidadaoRequestDTO, Cidadao.class);
        cidadao.setUsuario(usuario);
        cidadao.habilita(getPerfilVacinacao());
        cidadaoRepository.save(cidadao);

        return cidadao;
    }

    public void atualizaEstadoDeCidadaosAcimaDaIdadeMinima(int idadeMinima) {
        LocalDate dataDeNascimento = LocalDate.now().minusYears(idadeMinima);
        List<Cidadao> cidadaos = cidadaoRepository.listAllCidadaosAcimaDaIdadeMinima(dataDeNascimento);
        cidadaos.forEach(cidadao -> cidadao.habilita(getPerfilVacinacao()));

        cidadaoRepository.saveAll(cidadaos);
    }

    public void atualizaEstadoDeCidadaosAdequadosPorComorbidade(String comorbidade) {

        List<Long> cidadaosIds = cidadaoRepository
                .findAllCidadaosIdsComComorbidadesDentroDoPerfil(ConverterKeysUnicas.convert(comorbidade));

        List<Cidadao> cidadaos = cidadaoRepository.findAllById(cidadaosIds);
        cidadaos.forEach(cidadao -> cidadao.habilita(getPerfilVacinacao()));

        cidadaoRepository.saveAll(cidadaos);

    }

    public void atualizaEstadoDeCidadaosAdequadosPorProfissao(String profissao) {

        List<Cidadao> cidadaos = cidadaoRepository
                .findAllCidadaosComProfissaoDentroDoPerfil(ConverterKeysUnicas.convert(profissao));
        cidadaos.forEach(cidadao -> cidadao.habilita(getPerfilVacinacao()));

        cidadaoRepository.saveAll(cidadaos);
    }

    private PerfilVacinacao getPerfilVacinacao() {
        Optional<PerfilVacinacao> optionalPerfilVacinacao = perfilVacinacaoRepository.findById(1L);
        if (optionalPerfilVacinacao.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroPerfilVacinacao.erroAoAcessarPerfil())
            );
        }
        return optionalPerfilVacinacao.get();
    }

    private void validaCadastroPorEmailECpf(CidadaoRequestDTO cidadaoRequestDTO) {
        if(nonNull(cidadaoRequestDTO.getEmail()) && usuarioService.contemUsuario(cidadaoRequestDTO.getEmail())) {
            throw new ValidacaoException(new ErroDeSistema(
                    ErroCidadao.erroEmailJaCadastrado(cidadaoRequestDTO.getEmail()), HttpStatus.BAD_REQUEST)
            );
        }

        if(nonNull(cidadaoRequestDTO.getCpf()) && contemCidadao(cidadaoRequestDTO.getCpf())) {
            throw new ValidacaoException(new ErroDeSistema(
                    ErroCidadao.erroCpfJaCadastrado(cidadaoRequestDTO.getCpf()), HttpStatus.BAD_REQUEST)
            );
        }
    }

    private void validaCidadaoRequestDTO(CidadaoRequestDTO cidadaoRequestDTO) {

        if(validaParamDTO(cidadaoRequestDTO.getCpf()) || cidadaoRequestDTO.getCpf().length() != 11) {
            throw new ValidacaoException(new ErroDeSistema(
                    "CPF inválido. O CPF deve conter 11 dígitos.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getEndereco())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Endereço inválido.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getProfissao())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Profissão inválida.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getNome())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Nome inválido.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getEmail()) || !cidadaoRequestDTO.getEmail().contains("@")) {
            throw new ValidacaoException(new ErroDeSistema(
                    "E-mail inválido.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getNascimento())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Data de Nascimento Inválida.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getSus())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Número SUS inválido.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getTelefone())) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Número de Telefone Inválido.", HttpStatus.BAD_REQUEST
            ));
        }

        if(validaParamDTO(cidadaoRequestDTO.getSenha()) || cidadaoRequestDTO.getSenha().length() <= 4) {
            throw new ValidacaoException(new ErroDeSistema(
                    "Informe uma senha válida com no mínimo 5 caracteres.", HttpStatus.BAD_REQUEST
            ));
        }

        if(isNull(cidadaoRequestDTO.getComorbidades())) {
            cidadaoRequestDTO.setComorbidades(Set.of());
        }
    }

    private <T> boolean validaParamDTO(T param) {
        return isNull(param) || param.toString().isBlank();
    }
}
