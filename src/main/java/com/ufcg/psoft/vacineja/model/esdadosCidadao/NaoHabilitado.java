package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.notificacao.Notificador;

import javax.persistence.Entity;

@Entity
public class NaoHabilitado extends Estado {

	public NaoHabilitado() {
		this.nomeDoEstado = "NaoHabilitado";
	}

	@Override
	public void atualiza(Cidadao cidadao, Notificador notificador) {
		cidadao.mudaEstado(new HabilitadoPrimeiraDose());
		notificador.notificar(cidadao, "Você está habilitado para vacinação!",
				"Você já está habilitado para tomar a primeira dose da vacina!");
	}
	
	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		return false;
	}
	
	@Override
	public String toString() {
		return "Não habilitado para vacina";
	}
	
}
