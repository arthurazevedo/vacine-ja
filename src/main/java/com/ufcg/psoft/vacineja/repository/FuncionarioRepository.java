package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Funcionario;
import com.ufcg.psoft.vacineja.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCidadaoUsuario(Usuario usuario);
    boolean existsByCidadao(Cidadao cidadao);
    List<Funcionario> findAllByAprovado(boolean aprovado);
}
