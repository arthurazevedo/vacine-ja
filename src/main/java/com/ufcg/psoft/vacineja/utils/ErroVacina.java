package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

public final class ErroVacina {

    private static final String VACINA_NAO_ENCONTRADA = "Vacina com id: %s não foi encontrada.";

    private static final String FABRICANTE_NULO = "Fabricante da vacina não pode ser nulo";

    private static final String SEM_INTERVALO_ENTRE_DOSES =
            "Vacinas com mais de uma dose devem possuir um intervalo de dias entre as doses.";

    private static final String VACINA_DE_DOSE_UNICA = "Não existe intervalo de dias para vacinas de dose única.";

    private static final String FABRICANTE_JA_CADASTRADO = "A vacina do(a) %s já está cadastrada.";

    public static String erroVacinaNaoEncontrada(Long id) {
        return format(VACINA_NAO_ENCONTRADA, id);
    }

    public static String erroFabricanteNulo() {
        return FABRICANTE_NULO;
    }

    public static String erroVacinaDeDoseUnica() {
        return VACINA_DE_DOSE_UNICA;
    }

    public static String erroVacinaSemIntervaloEntreDoses() {
        return SEM_INTERVALO_ENTRE_DOSES;
    }

    public static String erroFabricanteJaCadastrado(String fabricante) {
        return format(FABRICANTE_JA_CADASTRADO, fabricante);
    }
}
