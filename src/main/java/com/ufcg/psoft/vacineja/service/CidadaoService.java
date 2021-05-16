package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoRequestDTO;
import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.PerfilVacinacao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.AgendamentoRepository;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.PerfilVacinacaoRepository;
import com.ufcg.psoft.vacineja.utils.StringUtil;
import com.ufcg.psoft.vacineja.utils.error.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.error.ErroPerfilVacinacao;
import com.ufcg.psoft.vacineja.utils.LoginUtil;
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

@Service
public class CidadaoService {
    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private PerfilVacinacaoRepository perfilVacinacaoRepository;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private AgendamentoRepository AgendamentoRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private LoginUtil loginUtil;

    @Value("${hash.forca}")
    private int forcaHash;

    public String pegarEstadoCidadao() {
    	Cidadao cidadao = loginUtil.pegarCidadaoLogado();
        
        return cidadao.exibeEstado();
    }
    
    public boolean contemCidadao(String cpf) {
        cpf = StringUtil.paraStringDeNumeros(cpf);
    	return cidadaoRepository.existsByCpf(cpf);
    }

    public Cidadao atualizaCidadao(CidadaoUpdateDTO cidadaoUpdateDTO) {
        Cidadao cidadao = loginUtil.pegarCidadaoLogado();

        boolean naoExisteCidadao = cidadao == null;

        if (naoExisteCidadao) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoEcontrado())
            );
        }

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
                .findAllCidadaosIdsComComorbidadesDentroDoPerfil(StringUtil.converterKeysUnicas(comorbidade));

        List<Cidadao> cidadaos = cidadaoRepository.findAllById(cidadaosIds);
        cidadaos.forEach(cidadao -> cidadao.habilita(getPerfilVacinacao()));

        cidadaoRepository.saveAll(cidadaos);

    }

    public void atualizaEstadoDeCidadaosAdequadosPorProfissao(String profissao) {

        List<Cidadao> cidadaos = cidadaoRepository
                .findAllCidadaosComProfissaoDentroDoPerfil(StringUtil.converterKeysUnicas(profissao));
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
        if(usuarioService.contemUsuario(cidadaoRequestDTO.getEmail())) {
            throw new ValidacaoException(new ErroDeSistema(
                    ErroCidadao.erroEmailJaCadastrado(cidadaoRequestDTO.getEmail()), HttpStatus.BAD_REQUEST)
            );
        }

        if(contemCidadao(cidadaoRequestDTO.getCpf())) {
            throw new ValidacaoException(new ErroDeSistema(
                    ErroCidadao.erroCpfJaCadastrado(cidadaoRequestDTO.getCpf()), HttpStatus.BAD_REQUEST)
            );
        }
    }
    
    public Cidadao vacinaCidadao(String cpf, int diasEntreDoses, boolean precisaSegundaDose) {
        cpf = StringUtil.paraStringDeNumeros(cpf);

    	Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);

        if (cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf), HttpStatus.NOT_FOUND)
            );
        }

        Cidadao cidadao = cidadaoOptional.get();
        
        if(!cidadao.vacina(diasEntreDoses, precisaSegundaDose)) {
        	throw new ValidacaoException(
                new ErroDeSistema(ErroCidadao.erroCidadaoNaoHabilitado(cpf), HttpStatus.BAD_REQUEST)
            );
        }
        
        cidadaoRepository.save(cidadao);
        
        AgendamentoRepository.deleteByUsuario(cidadao.getUsuario());
        
        return cidadao;
    }
}
