package com.ufcg.psoft.vacineja.model.esdadosCidadao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.service.notificacao.Notificador;

@Entity
public class TomouPrimeiraDose extends Estado {

	@Transient
	Notificador notificador = new Notificador();

	private LocalDate dataDaVacina;
	private int diasEntreDoses;

	public TomouPrimeiraDose() {}

	public TomouPrimeiraDose(int diasEntreDoses) {
		this.nomeDoEstado = "TomouPrimeiraDose";
		this.dataDaVacina = LocalDate.now();
		this.diasEntreDoses = diasEntreDoses;
	}

	@Override
	public void atualiza(Cidadao cidadao) {
		if(ChronoUnit.DAYS.between(this.dataDaVacina, LocalDate.now()) >= diasEntreDoses) {
			cidadao.mudaEstado(new HabilitadoSegundaDose());
			notificador.notificar(cidadao, "Você está habilitado para vacinação!",
					"Você já está habilitado para tomar a primeira dose da vacina!");
		}
	}

	@Override
	public boolean vacina(Cidadao cidadao, int diasEntreDoses, boolean precisaSegundaDose) {
		return false;
	}
	
	@Override
	public String toString() {
		return "Tomou 1ª dose";
	}

}
