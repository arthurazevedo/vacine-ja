package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.CidadaoRepository;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroFuncionario;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();

        Long idCidadao = ((Usuario) autenticacao.getPrincipal()).getTipo().getId();

        if (idCidadao == null) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroIdCidadaoNaoPodeSerNull())
            );
        }

        if (funcionarioDTO.getCargo() == null || funcionarioDTO.getLocalTrabalho() == null
                || funcionarioDTO.getCargo().equals("") || funcionarioDTO.getLocalTrabalho().equals("")) {
            throw new ValidacaoException(
                    new ErroDeSistema("Informações inválidas.")
            );
        }

        Optional<Cidadao> cidadaoOptional = cidadaoRepository.findById(idCidadao);

        boolean naoExisteCidadao = cidadaoOptional.isEmpty();

        if (naoExisteCidadao) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoEcontrado(idCidadao))
            );
        }

        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findByIdCidadao(idCidadao);

        boolean funcionarioExiste = funcionarioOptional.isPresent();

        if (funcionarioExiste) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroFuncionarioJaExiste())
            );
        }

        Funcionario funcionario = mapper.toEntity(funcionarioDTO, Funcionario.class);

        funcionario.setAprovado(false);
        funcionario.setIdCidadao(cidadaoOptional.get().getId());

        funcionarioRepository.save(funcionario);
    }
}
