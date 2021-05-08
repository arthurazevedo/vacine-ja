package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;
import com.ufcg.psoft.vacineja.utils.ErroFuncionario;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private MapperUtil mapper;

    public void cadastrarFuncionario(FuncionarioCadastroDTO funcionarioDTO) {
        Long idCidadao = funcionarioDTO.getIdCidadao();

        if (idCidadao == null) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroIdCidadaoNaoPodeSerNull())
            );
        }

        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findById(idCidadao);

        boolean naoExisteCidadao = cidadaoOptional.isEmpty();

        if (naoExisteCidadao) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroFuncionarioNaoEcontrado(idCidadao))
            );
        }

        Funcionario funcionario = mapper.toEntity(funcionarioDTO, Funcionario.class);

        funcionario.setAprovado(false);
        funcionario.setId(cidadaoOptional.get().getId());

        funcionarioRepository.save(funcionario);
    }
}
