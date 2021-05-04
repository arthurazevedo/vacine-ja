package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public class ErroLote {
    private static final String LOTE_NAO_ENCONTRADO = "Lote com id: %s não foi encontrado.";

    public static String erroLoteNaoEcontrado(Long id) {
        return format(LOTE_NAO_ENCONTRADO, id);
    }
}
