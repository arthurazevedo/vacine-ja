package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
