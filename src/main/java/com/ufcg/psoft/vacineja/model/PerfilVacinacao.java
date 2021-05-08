package com.ufcg.psoft.vacineja.model;

import com.ufcg.psoft.vacineja.utils.ConverterKeysUnicas;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PerfilVacinacao {
    @Id
    private Long id;

    @ElementCollection
    @CollectionTable(name="listaDeComorbidades")
    private Set<String> comorbidades;

    @ElementCollection
    @CollectionTable(name="listaDeProfissoes")
    private Set<String> profissoes;

    private int idade;

    public void adicionarComorbidade(String comorbidade) {
        this.comorbidades.add(ConverterKeysUnicas.convert(comorbidade));
    }

    public void adicionarProfissao(String profissao) {
        this.profissoes.add(ConverterKeysUnicas.convert(profissao));
    }

}
