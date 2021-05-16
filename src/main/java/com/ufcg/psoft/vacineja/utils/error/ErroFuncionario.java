package com.ufcg.psoft.vacineja.utils.error;

public class ErroFuncionario {
    private static final String ID_CIDADAO_NECESSARIO = "É necessário que informe o Id do Cidadao.";
    private static final String FUNCIONARIO_JA_EXISTE = "Funcionário já cadastrado ou com perfil em fase de análise.";
    private static final String FUNCIONARIO_NAO_EXISTE = "Funcionário com cpf %s não existe.";

    public static String erroIdCidadaoNaoPodeSerNull() { return ID_CIDADAO_NECESSARIO; }

    public static String erroFuncionarioJaExiste() {
        return FUNCIONARIO_JA_EXISTE;
    }

    public static String erroFuncionarioNaoExiste(String cpf) {
        return String.format(FUNCIONARIO_NAO_EXISTE, cpf);
    }
}
