package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.notificacao.Notificador;

@Entity
public class Finalizado extends Estado {

	public Finalizado() {
		this.nomeDoEstado = "Finalizado";
	}

	@Override
	public void atualiza(Cidadao cidadao, Notificador notificador) {}

	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		return false;
	}
	
	@Override
	public String toString() {
		return "Vacinação finalizada";
	}
}
