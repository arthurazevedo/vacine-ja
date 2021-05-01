package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import com.ufcg.psoft.vacineja.model.Cidadao;


public class Finalizado implements Estado {

	@Override
	public void atualiza(Cidadao cidadao, int idadeMinima) {}

	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {}
	
	@Override
	public String toString() {
		return "Vacinação finalizada";
	}
}
