package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.AprovarDTO;
import com.ufcg.psoft.vacineja.dtos.FuncionarioCadastroDTO;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import com.ufcg.psoft.vacineja.repository.FuncionarioRepository;
import com.ufcg.psoft.vacineja.utils.ErroCidadao;
import com.ufcg.psoft.vacineja.utils.ErroFuncionario;
import com.ufcg.psoft.vacineja.utils.LoginUtil;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MapperUtil mapper;

    @Autowired
    private LoginUtil loginUtil;

    public void cadastrarFuncionario(FuncionarioCadastroDTO funcionarioDTO) {
        Cidadao cidadao = loginUtil.pegarCidadaoLogado();

        boolean naoExisteCidadao = cidadao == null;

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

        boolean funcionarioExiste = funcionarioRepository.existsByCidadao(cidadao);

        if (funcionarioExiste) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroFuncionario.erroFuncionarioJaExiste())
            );
        }

        Funcionario funcionario = mapper.toEntity(funcionarioDTO, Funcionario.class);

        funcionario.setAprovado(false);
        funcionario.setCidadao(cidadao);

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
        funcionario.getCidadao().getUsuario().setPerfil(TipoUsuarioEnum.FUNCIONARIO.getValue());
        funcionarioRepository.save(funcionario);
    }
}
