package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public class ErroCidadao {
	private static final String EMAIL_JA_CADASTRADO = "Email: %s já cadastrado.";

    public static String erroEmailJaCadastrado(String email) {
        return format(EMAIL_JA_CADASTRADO, email);
    }
}
