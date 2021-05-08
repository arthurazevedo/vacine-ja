package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);

	void deleteByEmail(String email);
}
