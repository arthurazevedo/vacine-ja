package com.ufcg.psoft.vacineja.utils.error;

import static java.lang.String.format;

public final class ErroVacina {

    private static final String VACINA_NAO_ENCONTRADA = "Vacina com id: %s não foi encontrada.";

    private static final String VACINA_POR_FABRICANTE_NAO_ENCONTRADA = "Vacina com fabricante: %s não foi encontrada.";

    private static final String VACINA_DE_DOSE_UNICA = "Não existe intervalo de dias para vacinas de dose única.";

    private static final String FABRICANTE_JA_CADASTRADO = "A vacina do(a) %s já está cadastrada.";

    public static String erroVacinaNaoEncontrada(Long id) {
        return format(VACINA_NAO_ENCONTRADA, id);
    }

    public static String erroVacinaPorFabricanteNaoEcontrada(String fabricante) {
        return format(VACINA_POR_FABRICANTE_NAO_ENCONTRADA, fabricante);
    }

    public static String erroVacinaDeDoseUnica() {
        return VACINA_DE_DOSE_UNICA;
    }

    public static String erroFabricanteJaCadastrado(String fabricante) {
        return format(FABRICANTE_JA_CADASTRADO, fabricante);
    }
}
