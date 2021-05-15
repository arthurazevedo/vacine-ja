package com.ufcg.psoft.vacineja.utils;

import static java.lang.String.format;

import java.util.Date;

public class ErroAgendamento {
	private static final String USUARIO_NAO_HABILITADO = "Agendamento não autorizado. Usuário não habilitado.";
	private static final String HA_AGENDAMENTO_PENDENTE = "Agendamento não autorizado. Usuário tem agendamento pendente.";
	private static final String HORARIO_INDISPONIVEL = "O horario %s não está disponível.";

    public static String erroUsuarioNaoHabilitado() {
        return USUARIO_NAO_HABILITADO;
    }
    
    public static String erroAgendamentoPendente() {
        return HA_AGENDAMENTO_PENDENTE;
    }

    public static String erroHorarioIndisponivel(Date horario) {
        return format(HORARIO_INDISPONIVEL, horario.toString());
    }
}
