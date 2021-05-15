package com.ufcg.psoft.vacineja.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.dtos.AgendamentoDTO;
import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.AgendamentoRepository;
import com.ufcg.psoft.vacineja.repository.LoteRepository;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import com.ufcg.psoft.vacineja.utils.ErroAgendamento;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;

@Service
public class AgendamentoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private TipoUsuarioFactory usuarioFactory;
	
	public void salvaAgendamento(AgendamentoDTO agendamentoDTO) {
		Authentication autenticacao = SecurityContextHolder.getContext().getAuthentication();
		
		Usuario usuario = (Usuario) autenticacao.getPrincipal();
			
		autorizadoAgendar(usuario);
		
		if(agendamentoDTO.getHorario() == null) {
            throw new ValidacaoException(
                 new ErroDeSistema("Horário não pode ser nulo", HttpStatus.BAD_REQUEST)
            );
        }
		
		horarioEstaVago(agendamentoDTO.getHorario());
		
		agendamentoRepository.save(new Agendamento(agendamentoDTO.getHorario(), usuario));
	}
	
	public void horarioEstaVago(Date horario) {
		if(agendamentoRepository.existsByLessThanTenMinInterval(horario) || horario.before(new Date())) {
			throw new ValidacaoException(
		         new ErroDeSistema(ErroAgendamento.erroHorarioIndisponivel(horario), HttpStatus.BAD_REQUEST)
		    );
        }
	}
	
	
	public void autorizadoAgendar(Usuario usuario) {
		Cidadao cidadao = (Cidadao) usuarioFactory.get(usuario);
		
		if(!cidadao.exibeEstado().contains("Habilitado para tomar")) {
			throw new ValidacaoException(
	             new ErroDeSistema(ErroAgendamento.erroUsuarioNaoHabilitado(), HttpStatus.BAD_REQUEST)
	        );
		}
		
		if(agendamentoRepository.existsByUsuario(usuario)) {
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
