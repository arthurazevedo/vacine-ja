package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByIdCidadao(Long idCidadao);
}
