package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {
    Optional<Cidadao> findByCpf(String cpf);
    List<Cidadao> findAllCidadaoByEstadoNomeDoEstado(String nome);
	  boolean existsByCpf(String cpf);
    Optional<Cidadao> findByUsuario(Usuario usuario);
}
