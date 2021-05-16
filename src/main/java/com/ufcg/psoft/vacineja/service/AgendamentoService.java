package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.AgendamentoDTO;
import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.repository.AgendamentoRepository;
import com.ufcg.psoft.vacineja.repository.LoteRepository;
import com.ufcg.psoft.vacineja.utils.ErroAgendamento;
import com.ufcg.psoft.vacineja.utils.LoginUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private LoteRepository loteRepository;

	@Autowired
	private LoginUtil loginUtil;
	
	public void salvaAgendamento(AgendamentoDTO agendamentoDTO) {
		Cidadao cidadao = loginUtil.pegarCidadaoLogado();
			
		autorizadoAgendar(cidadao);
		
		if(agendamentoDTO.getHorario() == null) {
            throw new ValidacaoException(
                 new ErroDeSistema("Horário não pode ser nulo", HttpStatus.BAD_REQUEST)
            );
        }
		
		horarioEstaVago(agendamentoDTO.getHorario());
		
		agendamentoRepository.save(new Agendamento(agendamentoDTO.getHorario(), cidadao.getUsuario()));
	}
	
	private void horarioEstaVago(Date horario) {
		if(agendamentoRepository.existsByLessThanTenMinInterval(horario)) {
			throw new ValidacaoException(
		         new ErroDeSistema(ErroAgendamento.erroHorarioIndisponivel(horario), HttpStatus.BAD_REQUEST)
		    );
        }
	}

	private void autorizadoAgendar(Cidadao cidadao) {
		if(!cidadao.exibeEstado().contains("Habilitado para tomar")) {
			throw new ValidacaoException(
	             new ErroDeSistema(ErroAgendamento.erroUsuarioNaoHabilitado(), HttpStatus.BAD_REQUEST)
	        );
		}
		
		if(agendamentoRepository.existsByUsuario(cidadao.getUsuario())) {
			throw new ValidacaoException(
	             new ErroDeSistema(ErroAgendamento.erroAgendamentoPendente(), HttpStatus.BAD_REQUEST)
	        );
		}
		
		if(!(agendamentoRepository.count() < loteRepository.sumNumDoses())) {
			throw new ValidacaoException(
		         new ErroDeSistema(ErroAgendamento.erroNaoHaDosesDisponiveis(), HttpStatus.BAD_REQUEST)
		    );
		}
	}
}
