package com.ufcg.psoft.vacineja.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.dtos.AgendamentoDTO;
import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.repository.AgendamentoRepository;
import com.ufcg.psoft.vacineja.repository.UsuarioRepository;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public boolean horarioEstaVago(Date horario) {
		return !agendamentoRepository.existsByTenMinOrLessInterwal(horario);
	}
	
	public void salvaAgendamento(AgendamentoDTO agendamentoDTO, String email) {
		if(!horarioEstaVago(agendamentoDTO.getHorario())) {
            throw new ValidacaoException(
                 new ErroDeSistema("horário indisponível")
            );
        }
		agendamentoRepository.save(new Agendamento(agendamentoDTO.getHorario(), usuarioRepository.findByEmail(email).get()));
	}
}
