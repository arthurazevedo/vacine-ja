package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public class ErroFuncionario {
    private static final String CIDADAO_INEXISTENTE = "Cidadão de id %s não encontrado.";
    private static final String ID_CIDADAO_NECESSARIO = "É necessário que informe o Id do Cidadao.";

    public static String erroFuncionarioNaoEcontrado(Long id) {
        return format(CIDADAO_INEXISTENTE, id);
    }

    public static String erroIdCidadaoNaoPodeSerNull() { return ID_CIDADAO_NECESSARIO; }
}
