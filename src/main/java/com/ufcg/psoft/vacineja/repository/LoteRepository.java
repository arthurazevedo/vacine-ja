package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<LoteDeVacina, Long> {
    List<LoteDeVacina> findAllLotesByVacinaFabricante(String fabricnate);
    
    @Query("SELECT SUM(l.numDoses) FROM LoteDeVacina l")
    long sumNumDoses();
}

