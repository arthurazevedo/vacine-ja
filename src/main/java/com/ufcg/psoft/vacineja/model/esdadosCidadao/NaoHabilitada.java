package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import com.ufcg.psoft.vacineja.model.Cidadao;

public class NaoHabilitada implements Estado {

	@Override
	public void atualiza(Cidadao cidadao, int idadeMinima) {
		if(cidadao.getIdade() >= idadeMinima || cidadao.temComorbidadeValida() || cidadao.temProfissaoValida()) {
			cidadao.mudaEstado(new Habilitado1Dose());
		}
	}

	@Override
	public void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {}
	
	@Override
	public String toString() {
		return "Não habilitado para vacina";
	}
	
}
