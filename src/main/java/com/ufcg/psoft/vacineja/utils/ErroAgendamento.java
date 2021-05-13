package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

import java.util.Date;

public class ErroAgendamento {
	private static final String NAO_AUTORIZADO = "Agendamento não autorizado. Há agendamento pendente ou usuário não habilitado.";
	private static final String HORARIO_INDISPONIVEL = "O horario %s não está disponível.";

    public static String erroNaoAutorizado() {
        return NAO_AUTORIZADO;
    }

    public static String erroHorarioIndisponivel(Date horario) {
        return format(HORARIO_INDISPONIVEL, horario.toString());
    }
}
