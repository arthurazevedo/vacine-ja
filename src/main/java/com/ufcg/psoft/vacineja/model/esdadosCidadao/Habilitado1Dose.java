package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import com.ufcg.psoft.vacineja.model.Cidadao;


public class Habilitado1Dose implements Estado {
	
	@Override
	public void atualiza(Cidadao cidadao, int idadeMinima) {}

	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		if(precisaSegundaDose)
			cidadao.mudaEstado(new Tomou1Dose(diasEntreDoses));
		else
			cidadao.mudaEstado(new Finalizado());
	}

	@Override
	public String toString() {
		return "Habilitado para tomar 1ª dose";
	}
	
}
