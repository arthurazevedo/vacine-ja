package com.ufcg.psoft.vacineja.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.model.Usuario;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	@Query("SELECT COUNT(a) > 0 FROM Agendamento a WHERE minute(a.horario) - minute(:horario) < 10")
	boolean existsByLessThanTenMinInterval(@Param("horario") Date horario);
	
	boolean existsByUsuario(Usuario usuario);
	
	@Query("SELECT COUNT(a) = 1 FROM Agendamento a WHERE a.usuario = :usuario")
	boolean existsOnlyOneByUsuario(@Param("usuario") Usuario usuario);

	void deleteByUsuario(Usuario usuario);
}
