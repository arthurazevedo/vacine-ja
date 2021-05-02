package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}
