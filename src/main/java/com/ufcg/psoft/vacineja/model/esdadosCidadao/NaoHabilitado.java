package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;

@Entity
public class NaoHabilitado extends Estado {

	public NaoHabilitado() {
		this.nomeDoEstado = "NaoHabilitado";
	}

	@Override
	public void atualiza(Cidadao cidadao) {
		cidadao.mudaEstado(new HabilitadoPrimeiraDose());
	}
	
	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {}
	
	@Override
	public String toString() {
		return "Não habilitado para vacina";
	}
	
}
