package com.ufcg.psoft.vacineja.repository;

import com.ufcg.psoft.vacineja.model.Cidadao;
import com.ufcg.psoft.vacineja.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CidadaoRepository extends JpaRepository<Cidadao, Long> {
    Optional<Cidadao> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    Optional<Cidadao> findByUsuario(Usuario usuario);

    @Query("SELECT c FROM Cidadao c WHERE c.nascimento <= :dataDeNascimento")
    List<Cidadao> listAllCidadaosAcimaDaIdadeMinima(@Param("dataDeNascimento") LocalDate dataDeNascimento);

    @Query("SELECT c FROM Cidadao c WHERE c.profissao = :profissao")
    List<Cidadao> findAllCidadaosComProfissaoDentroDoPerfil(@Param("profissao") String profissao);

    @Query(value = "SELECT * FROM cidadao_comorbidades WHERE comorbidades = :comorbidade", nativeQuery = true)
    List<Long> findAllCidadaosIdsComComorbidadesDentroDoPerfil(@Param("comorbidade") String comorbidade);
}
