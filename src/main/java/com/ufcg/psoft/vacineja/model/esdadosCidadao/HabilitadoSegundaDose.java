package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.notificacao.Notificador;

@Entity
public class HabilitadoSegundaDose extends Estado {

	public HabilitadoSegundaDose() {
		this.nomeDoEstado = "HabilitadoSegundaDose";
	}

	@Override
	public void atualiza(Cidadao cidadao, Notificador notificador) {}

	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		cidadao.mudaEstado(new Finalizado());
		return true;
	}
	
	@Override
	public String toString() {
		return "Habilitado para tomar 2ª dose";
	}
}
