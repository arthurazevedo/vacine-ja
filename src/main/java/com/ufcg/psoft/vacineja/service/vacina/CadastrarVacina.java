package com.ufcg.psoft.vacineja.service.vacina;

import com.ufcg.psoft.vacineja.dtos.vacina.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.CustomError;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class CadastrarVacina {
    @Autowired
    private VacinaRepository vacinaRepository;

    public ResponseEntity<?> executar(VacinaDTO vacinaDTO, ModelMapper mapper) {
        final var validacaoDTO = validarCampos(vacinaDTO);
        if(nonNull(validacaoDTO)) {
            return validacaoDTO;
        }
        final var vacina = mapper.map(vacinaDTO, Vacina.class);
        vacinaRepository.save(vacina);
        return new ResponseEntity<>(vacina, HttpStatus.CREATED);
    }

    private ResponseEntity<CustomError> validarCampos(VacinaDTO vacinaDTO) {
        if(vacinaDTO.getFabricante().isBlank()) {
            return ErroVacina.erroFabricanteNulo();
        }

        if(vacinaDTO.getDosesRequeridas() > 2 || vacinaDTO.getDosesRequeridas() < 0) {
            return ErroVacina.erroQuantidadeDeDosesInvalida();
        }

        if(vacinaDTO.getDosesRequeridas() > 1 && vacinaDTO.getIntervaloEntreDoses() == 0) {
            return ErroVacina.erroVacinaSemIntervaloEntreDoses();
        } else if(vacinaDTO.getDosesRequeridas() == 1 && vacinaDTO.getIntervaloEntreDoses() > 0) {
            return ErroVacina.erroVacinaDeDoseUnica();
        }

        return null;
    }
}
