package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;

@Entity
public class HabilitadoPrimeiraDose extends Estado {

	public HabilitadoPrimeiraDose() {
		this.nomeDoEstado = "HabilitadoPrimeiraDose";
	}

	@Override
	public void atualiza(Cidadao cidadao) {}

	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		if(precisaSegundaDose)
			cidadao.mudaEstado(new TomouPrimeiraDose(diasEntreDoses));
		else
			cidadao.mudaEstado(new Finalizado());
		return true;
	}

	@Override
	public String toString() {
		return "Habilitado para tomar 1ª dose";
	}
	
}
