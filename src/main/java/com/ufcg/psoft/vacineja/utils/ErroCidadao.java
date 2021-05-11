package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public class ErroCidadao {
	private static final String EMAIL_JA_CADASTRADO = "Email: %s já cadastrado.";
	private static final String CIDADAO_NAO_EXISTE = "O cidadao com CPF %s não existe.";
    private static final String CIDADAO_INEXISTENTE = "Cidadão não encontrado.";

    public static String erroEmailJaCadastrado(String email) {
        return format(EMAIL_JA_CADASTRADO, email);
    }

    public static String erroCidadaoNaoExiste(String cpf) {
        return format(CIDADAO_NAO_EXISTE, cpf);
    }

    public static String erroCidadaoNaoEcontrado() {
        return CIDADAO_INEXISTENTE;
    }
}
