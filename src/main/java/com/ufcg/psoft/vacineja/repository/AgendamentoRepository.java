package com.ufcg.psoft.vacineja.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.psoft.vacineja.model.Agendamento;
import com.ufcg.psoft.vacineja.model.Usuario;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	@Query(value = "SELECT CASE WHEN count(*) > 0 THEN true ELSE false END FROM Agendamento a WHERE ABS(DATEDIFF(MINUTE, a.horario, :horario)) < 10", nativeQuery = true)
	boolean existsByLessThanTenMinInterval(@Param("horario") Date horario);
	
	boolean existsByUsuario(Usuario usuario);
	
	void deleteByUsuario(Usuario usuario);
}
