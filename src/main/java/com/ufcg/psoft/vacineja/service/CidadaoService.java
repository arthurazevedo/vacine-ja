package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.model.PerfilVacinacao;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import com.ufcg.psoft.vacineja.repository.PerfilVacinacaoRepository;
import com.ufcg.psoft.vacineja.utils.ConverterKeysUnicas;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroPerfilVacinacao;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CidadaoService {
	
	@Autowired
    private TipoUsuarioFactory tipoUsuarioFactory;
	
    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private PerfilVacinacaoRepository perfilVacinacaoRepository;

    public String pegarEstadoCidadao(String cpf) {
        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);
        if(cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf), HttpStatus.NOT_FOUND)
            );
        }

        return cidadaoOptional.get().exibeEstado();
    }
    
    public boolean contemCidadao(String cpf) {
    	return cidadaoRepository.existsByCpf(cpf);
    }

    public Cidadao atualizaCidadao(CidadaoUpdateDTO cidadaoUpdateDTO) {
    	Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();

        Cidadao cidadaoAuthenticated = (Cidadao) tipoUsuarioFactory.get((Usuario) autenticacao.getPrincipal());

        boolean naoExisteCidadao = cidadaoAuthenticated == null;

        if (naoExisteCidadao) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoEcontrado())
            );
        }

         if(cidadaoUpdateDTO.getComorbidades() != null) {
        	 cidadaoAuthenticated.setComorbidades(cidadaoUpdateDTO.getComorbidades());
         }

         if(cidadaoUpdateDTO.getNome() != null) {
        	 cidadaoAuthenticated.setNome(cidadaoUpdateDTO.getNome());
         }

         if(cidadaoUpdateDTO.getEndereco() != null) {
        	 cidadaoAuthenticated.setEndereco(cidadaoUpdateDTO.getEndereco());
         }

         if(cidadaoUpdateDTO.getTelefone() != null) {
        	 cidadaoAuthenticated.setTelefone(cidadaoUpdateDTO.getTelefone());
         }

         if(cidadaoUpdateDTO.getProfissao() != null) {
        	 cidadaoAuthenticated.setProfissao(cidadaoUpdateDTO.getProfissao());
         }

         cidadaoRepository.save(cidadaoAuthenticated);
         return cidadaoAuthenticated;
    }

    public void salvarCidadao(Cidadao cidadao) {
        cidadao.habilita(getPerfilVacinacao());
        cidadaoRepository.save(cidadao);
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
}
