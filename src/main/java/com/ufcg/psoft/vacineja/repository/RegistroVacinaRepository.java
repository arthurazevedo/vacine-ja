package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.RegistroVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroVacinaRepository extends JpaRepository<RegistroVacina, Long> {
}
