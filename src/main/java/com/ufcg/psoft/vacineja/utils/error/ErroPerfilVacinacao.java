package com.ufcg.psoft.vacineja.utils.error;

public class ErroPerfilVacinacao {

    private static final String ERRO_AO_ACESSAR_PERFIL = "Ocorreu um erro ao tentar acessar o perfil de vacinação";
    private static final String IDADE_MINIMA_INVALIDA = "A idade tem que ser maior que 0 e menor que a idade mínima atual";
    private static final String PROFISSAO_INVALIDA = "A profissão não pode ficar em branco";
    private static final String COMORBIDADE_INVALIDA = "A comorbidade não pode ficar em branco";

    public static String erroAoAcessarPerfil() {
        return ERRO_AO_ACESSAR_PERFIL;
    }
    public static String erroIdadeMinima() {
        return IDADE_MINIMA_INVALIDA;
    }
    public static String erroProfissaoInvalida() {
        return PROFISSAO_INVALIDA;
    }
    public static String erroComorbidadeInvalida() {
        return COMORBIDADE_INVALIDA;
    }
}
