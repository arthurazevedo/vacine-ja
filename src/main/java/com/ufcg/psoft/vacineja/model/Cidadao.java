package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import com.ufcg.psoft.vacineja.model.esdadosCidadao.Estado;
import com.ufcg.psoft.vacineja.model.esdadosCidadao.NaoHabilitada;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Cidadao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
	private String nome;
	private String cpf;
	private String endereco;
	private String sus;
	private String telefone;
	private String profissao;
	private LocalDate nascimento;
	@Setter(value = AccessLevel.NONE)
	private String perfil;
	@ElementCollection
	private List<String> comorbidades;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@JoinColumn(name = "estado")
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;
	
	public Cidadao(String nome, String cpf, String endereco, String sus, String telefone,
			String profissao, List<String> comorbidades, LocalDate nascimento) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.sus = sus;
		this.telefone = telefone;
		this.profissao = profissao;
		this.comorbidades = comorbidades;
		this.nascimento = nascimento;
		this.estado = new NaoHabilitada();
		this.perfil = TipoUsuarioEnum.CIDADAO.getValue();
	}

	public void atualiza(int idadeMinima, List<String> comorbidadesValidas, List<String> profissoesValidas) {
		if(profissoesValidas.contains(this.profissao) || idadeMinima >= getIdade() ||
				temComorbidadesValida(comorbidadesValidas) || !(this.estado instanceof NaoHabilitada ))	
			this.estado.atualiza(this);
	}
	
	private boolean temComorbidadesValida(List<String> comorbidadesValidas) {
		for(String comorbidade: this.comorbidades) {
			if(comorbidadesValidas.contains(comorbidade))
				return true;
		}
		return false;
	}

	public void mudaEstado(Estado estado) {
		this.estado = estado;
	}

	public long getIdade() {
		return ChronoUnit.YEARS.between(this.nascimento, LocalDate.now());
	}
}
