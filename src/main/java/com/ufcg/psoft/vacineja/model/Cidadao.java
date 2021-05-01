package com.ufcg.psoft.vacineja.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ufcg.psoft.vacineja.model.enums.TipoUsuarioEnum;
import com.ufcg.psoft.vacineja.model.esdadosCidadao.Estado;
import com.ufcg.psoft.vacineja.model.esdadosCidadao.NaoHabilitada;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cidadao extends Usuario {
	
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
	private List<String> comorbidades;
	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	private Estado estado;
	
	public Cidadao(String email, String senha, String nome, String cpf, String endereco, String sus, String telefone,
			String profissao, List<String> comorbidades, LocalDate nascimento) {
		super.setEmail(email);
		super.setSenha(senha);
		super.setPerfil(TipoUsuarioEnum.CIDADAO.getValue());
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.sus = sus;
		this.telefone = telefone;
		this.profissao = profissao;
		this.comorbidades = comorbidades;
		this.nascimento = nascimento;
		this.estado = new NaoHabilitada();
		atualiza();
	}

	public void atualiza() {
		this.estado.atualiza(this, idadeMinima());
	}

	public void mudaEstado(Estado estado) {
		this.estado = estado;
	}

	public long getIdade() {
		return ChronoUnit.YEARS.between(this.nascimento, LocalDate.now());
	}

	public boolean temComorbidadeValida() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean temProfissaoValida() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//temporario
	private int idadeMinima() {
		return 80;
	}
}
