package com.ufcg.psoft.vacineja.model;

import com.ufcg.psoft.vacineja.model.esdadosCidadao.Estado;
import com.ufcg.psoft.vacineja.model.esdadosCidadao.NaoHabilitado;
import com.ufcg.psoft.vacineja.utils.ConverterKeysUnicas;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Cidadao implements TipoUsuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String nome;

	@OneToOne
	private Usuario usuario;

	@Column(unique = true)
	private String cpf;

	private String endereco;
	private String sus;
	private String telefone;
	private String profissao;
	private LocalDate nascimento;

	@ElementCollection
	private Set<String> comorbidades;

	@Getter(value = AccessLevel.NONE)
	@Setter(value = AccessLevel.NONE)
	@JoinColumn(name = "estado")
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado = new NaoHabilitado();
	
	public Cidadao() {}
	
	public Cidadao(String nome, String cpf, String endereco, String sus, String telefone,
			String profissao, Set<String> comorbidades, LocalDate nascimento) {
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.sus = sus;
		this.telefone = telefone;
		this.profissao = ConverterKeysUnicas.convert(profissao);
		this.comorbidades = comorbidades;
		this.nascimento = nascimento;
	}

	public void habilita(PerfilVacinacao vacinacao) {
		if(vacinacao.getProfissoes().contains(this.profissao) || calculaIdade() >= vacinacao.getIdade() ||
				temComorbidadeValida(vacinacao.getComorbidades()))	
			this.estado.atualiza(this);
	}
	
	public void atualiza() {
		if(!(this.estado instanceof NaoHabilitado))
			this.estado.atualiza(this);
	}
	
	public boolean vacina(int diasEntreDoses, boolean precisaSegundaDose) {
		return this.estado.vacina(this, diasEntreDoses, precisaSegundaDose);
	}
	
	private boolean temComorbidadeValida(Set<String> comorbidadesValidas) {
		for(String comorbidade: comorbidadesValidas) {
			if(this.comorbidades.contains(comorbidade))
				return true;
		}
		return false;
	}

	public void mudaEstado(Estado estado) {
		this.estado = estado;
	}

	public long calculaIdade() {
		return ChronoUnit.YEARS.between(this.nascimento, LocalDate.now());
	}

	public String exibeEstado() {
		return this.estado.toString();
	}

	public void setProfissao(String profissao) {
		this.profissao = ConverterKeysUnicas.convert(profissao);
	}

	public void setComorbidades(Set<String> comorbidades) {
		this.comorbidades = comorbidades
				.stream()
				.map(ConverterKeysUnicas::convert)
				.collect(Collectors.toSet());
	}
}
