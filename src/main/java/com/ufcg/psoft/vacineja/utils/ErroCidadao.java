package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public class ErroCidadao {
	private static final String EMAIL_JA_CADASTRADO = "Email: %s já cadastrado.";
	private static final String CPF_JA_CADASTRADO = "O CPF %s já está cadastrado no sistema.";
	private static final String CIDADAO_NAO_EXISTE = "O cidadao com CPF %s não existe.";
    private static final String CIDADAO_INEXISTENTE = "Cidadão não encontrado.";
    private static final String CIDADAO_NAO_HABILITADO = "O cidadao com CPF %s não está habilitado para vacinação.";

    public static String erroEmailJaCadastrado(String email) {
        return format(EMAIL_JA_CADASTRADO, email);
    }

    public static String erroCidadaoNaoExiste(String cpf) {
        return format(CIDADAO_NAO_EXISTE, cpf);
    }

    public static String erroCpfJaCadastrado(String cpf) {
        return format(CPF_JA_CADASTRADO, cpf);
    }

    public static String erroCidadaoNaoEcontrado() {
        return CIDADAO_INEXISTENTE;
    }
    
    public static String erroCidadaoNaoHabilitado(String cpf) {
    	return format(CIDADAO_NAO_HABILITADO, cpf);
    }
}
