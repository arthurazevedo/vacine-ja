package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import com.ufcg.psoft.vacineja.model.Cidadao;

public interface Estado {

	void atualiza(Cidadao cidadao, int idadeMinima);

	void vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose);

}
