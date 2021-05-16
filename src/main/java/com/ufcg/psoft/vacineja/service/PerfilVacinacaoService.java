package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.model.PerfilVacinacao;
import com.ufcg.psoft.vacineja.repository.PerfilVacinacaoRepository;
import com.ufcg.psoft.vacineja.utils.ErroPerfilVacinacao;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PerfilVacinacaoService {
    private final long perfilId = 1;

    @Autowired
    private PerfilVacinacaoRepository perfilVacinacaoRepository;

    @Autowired
    private CidadaoService cidadaoService;

    private PerfilVacinacao pegarPerfil() {
        Optional<PerfilVacinacao> optionalPerfilVacinacao = perfilVacinacaoRepository.findById(perfilId);
        if(optionalPerfilVacinacao.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroPerfilVacinacao.erroAoAcessarPerfil(), HttpStatus.NOT_FOUND)
            );
        }
        return optionalPerfilVacinacao.get();
    }

    public Set<String> listarProfissoes() {
        return this.pegarPerfil().getProfissoes();
    }

    public Set<String> listarComorbidades() {
        return this.pegarPerfil().getComorbidades();
    }

    public int listarIdadeMinima() {
        return this.pegarPerfil().getIdade();
    }

    public void adicionarProfissao(String novaProfissao) {
        if(novaProfissao.isBlank()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroPerfilVacinacao.erroProfissaoInvalida())
            );
        }

        PerfilVacinacao perfilVacinacao = this.pegarPerfil();
        perfilVacinacao.adicionarProfissao(novaProfissao);
        perfilVacinacaoRepository.save(perfilVacinacao);

        cidadaoService.atualizaEstadoDeCidadaosAdequadosPorProfissao(novaProfissao);
    }

    public void adicionarComorbidade(String novaComorbidade) {
        if(novaComorbidade.isBlank()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroPerfilVacinacao.erroComorbidadeInvalida())
            );
        }

        PerfilVacinacao perfilVacinacao =  this.pegarPerfil();
        perfilVacinacao.adicionarComorbidade(novaComorbidade);
        perfilVacinacaoRepository.save(perfilVacinacao);
        cidadaoService.atualizaEstadoDeCidadaosAdequadosPorComorbidade(novaComorbidade);
    }

    public void alterarIdadeMinima(int novaIdade) {
        PerfilVacinacao perfilVacinacao = this.pegarPerfil();
        int idadeAtual = perfilVacinacao.getIdade();
        if(novaIdade >= idadeAtual) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroPerfilVacinacao.erroIdadeMinima())
            );
        }
        perfilVacinacao.setIdade(novaIdade);
        perfilVacinacaoRepository.save(perfilVacinacao);
        cidadaoService.atualizaEstadoDeCidadaosAcimaDaIdadeMinima(novaIdade);
    }
}
