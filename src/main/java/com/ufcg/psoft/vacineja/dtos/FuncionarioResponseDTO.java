package com.ufcg.psoft.vacineja.dtos;

import com.ufcg.psoft.vacineja.model.Funcionario;
import lombok.Getter;

@Getter
public class FuncionarioResponseDTO {
    private String cpf;
    private String nome;
    private String cargo;
    private String localTrabalho;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.cpf = funcionario.getCidadao().getCpf();
        this.nome = funcionario.getCidadao().getNome();
        this.cargo = funcionario.getCargo();
        this.localTrabalho = funcionario.getLocalTrabalho();
    }
}