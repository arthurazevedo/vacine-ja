package com.ufcg.psoft.vacineja.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.vacineja.dtos.AgendamentoDTO;
import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import com.ufcg.psoft.vacineja.repository.AgendamentoRepository;
import com.ufcg.psoft.vacineja.service.factory.TipoUsuarioFactory;
import com.ufcg.psoft.vacineja.utils.ErroAgendamento;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;

@Service
public class AgendamentoService {
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoUsuarioFactory usuarioFactory;
	
	public void salvaAgendamento(AgendamentoDTO agendamentoDTO, String email) {
		Usuario usuario = (Usuario) usuarioService.loadUserByUsername(email);
			
		if(autorizadoAgendar(usuario) || agendamentoDTO.getHorario() == null) {
            throw new ValidacaoException(
                 new ErroDeSistema(ErroAgendamento.erroNaoAutorizado(), HttpStatus.BAD_REQUEST)
            );
        }
		
		if(!horarioEstaVago(agendamentoDTO.getHorario()) || agendamentoDTO.getHorario().before(new Date())) {
            throw new ValidacaoException(
                 new ErroDeSistema(ErroAgendamento.erroHorarioIndisponivel(agendamentoDTO.getHorario()), HttpStatus.BAD_REQUEST)
            );
        }
		
		agendamentoRepository.save(new Agendamento(agendamentoDTO.getHorario(), usuario));
	}
	
	public boolean horarioEstaVago(Date horario) {
		return !agendamentoRepository.existsByLessThanTenMinInterval(horario);
	}
	
	
	public boolean autorizadoAgendar(Usuario usuario) {
		
		Cidadao cidadao = (Cidadao) usuarioFactory.get(usuario);
		if(cidadao.exibeEstado().equals("Habilitado para tomar 1ª dose") && !agendamentoRepository.existsByUsuario(usuario)) {
			return true;
		}
		
		if(cidadao.exibeEstado().equals("Habilitado para tomar 2ª dose") && agendamentoRepository.existsOnlyOneByUsuario(usuario)) {
			return true;
		}
		
		return false;
	}
}
