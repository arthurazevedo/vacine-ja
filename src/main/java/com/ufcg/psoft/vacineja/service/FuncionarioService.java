package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroFuncionario;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MapperUtil mapper;

    @Autowired
    private TipoUsuarioFactory tipoUsuarioFactory;

    public void cadastrarFuncionario(FuncionarioCadastroDTO funcionarioDTO) {
        Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();

        Cidadao cidadaoAuthenticated = (Cidadao) tipoUsuarioFactory.get((Usuario) autenticacao.getPrincipal());

        boolean naoExisteCidadao = cidadaoAuthenticated == null;

        if (naoExisteCidadao) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroCidadao.erroCidadaoNaoEcontrado())
            );
        }

        if (funcionarioDTO.getCargo() == null || funcionarioDTO.getLocalTrabalho() == null
                || funcionarioDTO.getCargo().equals("") || funcionarioDTO.getLocalTrabalho().equals("")) {
            throw new ValidacaoException(
                    new ErroDeSistema("Informações inválidas.")
            );
        }

        boolean funcionarioExiste = funcionarioRepository.existsByCidadao(cidadaoAuthenticated);

        if (funcionarioExiste) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroFuncionarioJaExiste())
            );
        }

        Funcionario funcionario = mapper.toEntity(funcionarioDTO, Funcionario.class);

        funcionario.setAprovado(false);
        funcionario.setCidadao(cidadaoAuthenticated);

        funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getFuncionariosPendentes() {
        return funcionarioRepository.findAllByAprovado(false);
    }

    public void aprovarCadastroFuncionario(String cpfFuncionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByCidadaoCpf(cpfFuncionario);

        if(funcionarioOptional.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroFuncionarioNaoExiste(cpfFuncionario), HttpStatus.NOT_FOUND)
            );
        }

        Funcionario funcionario = funcionarioOptional.get();
        funcionario.setAprovado(true);
        funcionarioRepository.save(funcionario);
    }
}
