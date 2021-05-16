package com.ufcg.psoft.vacineja.utils.error;

public class ErroPerfilVacinacao {

    private static final String ERRO_AO_ACESSAR_PERFIL = "Ocorreu um erro ao tentar acessar o perfil de vacinação";
    private static final String IDADE_MINIMA_INVALIDA = "A idade tem que ser menor que a idade mínima atual";

    public static String erroAoAcessarPerfil() {
        return ERRO_AO_ACESSAR_PERFIL;
    }
    public static String erroIdadeMinima() {
        return IDADE_MINIMA_INVALIDA;
    }
}
