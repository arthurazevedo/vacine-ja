package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.CidadaoUpdateDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CidadaoService {
    @Autowired
    private CidadaoRepository cidadaoRepository;

    public String pegarEstadoCidadao(String cpf) {
        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findByCpf(cpf);
        if(cidadaoOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoExiste(cpf))
            );
        }

        return cidadaoOptional.get().exibeEstado();
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
}
