package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import javax.persistence.Entity;

import com.ufcg.psoft.vacineja.model.Cidadao;

@Entity
public class Habilitado2Dose extends Estado {

	@Override
	public void atualiza(Cidadao cidadao) {}

	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		cidadao.mudaEstado(new Finalizado());
	}
	
	@Override
	public String toString() {
		return "Habilitado para tomar 2ª dose";
	}
}
