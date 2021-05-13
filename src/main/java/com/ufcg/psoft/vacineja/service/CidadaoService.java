package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadaoService {
	
	@Autowired
    private TipoUsuarioFactory tipoUsuarioFactory;
	
    @Autowired
    private CidadaoRepository cidadaoRepository;

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
        cidadaoRepository.save(cidadao);
    }
}
