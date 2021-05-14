package com.ufcg.psoft.vacineja.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "lotes")
@Data
@NoArgsConstructor
public class LoteDeVacina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @NonNull
    private Vacina vacina;
    private Long numDoses;
    @NonNull
    private LocalDate dataDeValidade;
}
