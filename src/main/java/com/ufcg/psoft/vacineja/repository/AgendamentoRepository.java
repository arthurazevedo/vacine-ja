package com.ufcg.psoft.vacineja.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ufcg.psoft.vacineja.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
	@Query("SELECT COUNT(a) > 0 FROM Agendamento a WHERE minute(a.horario) - minute(:horario) < 10")
	boolean existsByTenMinOrLessInterwal(@Param("horario") Date horario);
}
