package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import com.ufcg.psoft.vacineja.model.Cidadao;

@Entity
@Inheritance
public abstract class Estado {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	protected String nomeDoEstado;
	
	public abstract void atualiza(Cidadao cidadao);

	public abstract void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose);

}
