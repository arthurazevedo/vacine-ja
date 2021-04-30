package com.ufcg.psoft.vacineja.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.lang.String.format;

public class ErroVacina {

    private static final String VACINA_NAO_ENCONTRADA = "Vacina com id: %s não foi encontrada.";

    private static final String FABRICANTE_NULO = "Fabricante da vacina não pode ser nulo";

    private static final String QUANTIDADE_DE_DOSES_INVALIDA = "A vacina deve necessitar de apenas uma ou duas doses.";

    private static final String SEM_INTERVALO_ENTRE_DOSES =
            "Vacinas com mais de uma dose devem possuir um intervalo de dias entre as doses.";

    private static final String VACINA_DE_DOSE_UNICA = "Não existe intervalo de dias para vacinas de dose única.";

    public static ResponseEntity<CustomError> erroVacinaNaoEncontrada(Long id) {
        return new ResponseEntity<>(new CustomError(format(VACINA_NAO_ENCONTRADA, id)), HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<CustomError> erroFabricanteNulo() {
        return new ResponseEntity<>(new CustomError(FABRICANTE_NULO), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomError> erroQuantidadeDeDosesInvalida() {
        return new ResponseEntity<>(new CustomError(QUANTIDADE_DE_DOSES_INVALIDA), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomError> erroVacinaDeDoseUnica() {
        return new ResponseEntity<>(new CustomError(VACINA_DE_DOSE_UNICA), HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<CustomError> erroVacinaSemIntervaloEntreDoses() {
        return new ResponseEntity<>(new CustomError(SEM_INTERVALO_ENTRE_DOSES), HttpStatus.BAD_REQUEST);
    }
}
