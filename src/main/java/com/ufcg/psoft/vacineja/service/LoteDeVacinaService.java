package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaCreateDTO;
import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaResponseDTO;
import com.ufcg.psoft.vacineja.dtos.LoteDeVacinaUpdateDTO;
import com.ufcg.psoft.vacineja.model.LoteDeVacina;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.LoteRepository;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.ErroLote;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class LoteDeVacinaService {
    @Autowired
    private LoteRepository loteRepository;

    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private MapperUtil mapper;

    public LoteDeVacinaResponseDTO cadastrarLote(LoteDeVacinaCreateDTO loteDeVacinaCreateDTO) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(loteDeVacinaCreateDTO.getVacinaId());
        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(loteDeVacinaCreateDTO.getVacinaId()))
            );
        }

        LoteDeVacina lote = new LoteDeVacina();
        lote.setVacina(optionalVacina.get());
        lote.setDataDeValidade(loteDeVacinaCreateDTO.getDataDeValidade());
        lote.setNumDoses(loteDeVacinaCreateDTO.getNumDoses());

        loteRepository.save(lote);
         return mapper.toDTO(lote, LoteDeVacinaResponseDTO.class);
    }

    public LoteDeVacinaResponseDTO buscarPorId(Long id) {
        Optional<LoteDeVacina> optionalLote = loteRepository.findById(id);

        if(optionalLote.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroLote.erroLoteNaoEcontrado(id))
            );
        }

        return mapper.toDTO(optionalLote.get(), LoteDeVacinaResponseDTO.class);
    }

    public List<LoteDeVacinaResponseDTO> listarLotesPorVacina(Long vacinaId) {
        return loteRepository.findAllLotesByVacinaId(vacinaId).stream().map(loteDeVacina ->
                mapper.toDTO(loteDeVacina, LoteDeVacinaResponseDTO.class)
        ).collect(Collectors.toList());
    }

    public LoteDeVacina editarLote(Long id, LoteDeVacinaUpdateDTO loteDeVacinaUpdateDTO) {
        Optional<LoteDeVacina> optionalLote = loteRepository.findById(id);
        if(optionalLote.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroLote.erroLoteNaoEcontrado(id))
            );
        }

        LoteDeVacina lote = optionalLote.get();

        if(nonNull(loteDeVacinaUpdateDTO.getDataDeValidade()) &&
                loteDeVacinaUpdateDTO.getDataDeValidade().compareTo(LocalDate.now()) > 0) {
            lote.setDataDeValidade(loteDeVacinaUpdateDTO.getDataDeValidade());
        }

        if(nonNull(loteDeVacinaUpdateDTO.getNumDoses()) && loteDeVacinaUpdateDTO.getNumDoses() >= 0) {
            lote.setNumDoses(loteDeVacinaUpdateDTO.getNumDoses());
        }

        Optional<Vacina> optionalVacina = vacinaRepository.findById(loteDeVacinaUpdateDTO.getVacinaId());
        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(loteDeVacinaUpdateDTO.getVacinaId()))
            );
        } else {
            lote.setVacina(optionalVacina.get());
        }

        loteRepository.save(lote);
        return lote;
    }

    public void removerLote(Long id) {
        Optional<LoteDeVacina> optionalLote = loteRepository.findById(id);
        if(optionalLote.isEmpty()) {
            throw new ValidacaoException(
                   new ErroDeSistema(ErroLote.erroLoteNaoEcontrado(id))
            );
        }

        loteRepository.delete(optionalLote.get());
    }
}
