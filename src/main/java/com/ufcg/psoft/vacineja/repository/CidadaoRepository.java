package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {
}
