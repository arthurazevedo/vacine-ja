package com.ufcg.psoft.vacineja.dtos;

import com.ufcg.psoft.vacineja.model.Funcionario;
import lombok.Getter;

@Getter
public class FuncionarioResponseDTO {
    private String nome;
    private String cpf;
    private String cargo;
    private String localTrabalho;

    public FuncionarioResponseDTO(Funcionario funcionario) {
        this.nome = funcionario.getCidadao().getNome();
        this.cpf = funcionario.getCidadao().getCpf();
        this.cargo = funcionario.getCargo();
        this.localTrabalho = funcionario.getLocalTrabalho();
    }
}