package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.ConverterKeysUnicas;
import com.ufcg.psoft.vacineja.utils.ErroVacina;
import com.ufcg.psoft.vacineja.utils.MapperUtil;
import com.ufcg.psoft.vacineja.utils.error.exception.ValidacaoException;
import com.ufcg.psoft.vacineja.utils.error.model.ErroDeSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacinaService {
    @Autowired
    private VacinaRepository vacinaRepository;

    @Autowired
    private MapperUtil mapper;

    public Vacina cadastrarVacina(VacinaDTO vacinaDTO) {

        validarDtoCadastroDeVacina(vacinaDTO);
        vacinaDTO.setFabricante(ConverterKeysUnicas.convert(vacinaDTO.getFabricante()));
        final var vacina = mapper.toEntity(vacinaDTO, Vacina.class);
        vacinaRepository.save(vacina);
        return vacina;
    }

    public Vacina buscarPorId(Long id) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);
        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(id), HttpStatus.NOT_FOUND)
            );
        }
        return optionalVacina.get();

    }

    public List<Vacina> listarVacinas() {
        return vacinaRepository.findAll();
    }

    public Vacina editarVacina(Long id, VacinaDTO vacinaDTO) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);

        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(id), HttpStatus.NOT_FOUND)
            );
        }

        Vacina vacina = optionalVacina.get();

        if(!vacinaDTO.getFabricante().isBlank()) {
            vacina.setFabricante(vacinaDTO.getFabricante());
        }

        vacina.setPrecisaSegundaDose(vacinaDTO.isPrecisaSegundaDose());

        if(vacinaDTO.getIntervaloEntreDoses() > 0 && vacinaDTO.isPrecisaSegundaDose()) {
            vacina.setIntervaloEntreDoses(vacinaDTO.getIntervaloEntreDoses());
        }

        vacinaRepository.save(vacina);
        return vacina;
    }

    public void removerVacinaPorId(Long id) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);
        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(id), HttpStatus.NOT_FOUND)
            );
        }
        vacinaRepository.delete(optionalVacina.get());
    }

    private void validarDtoCadastroDeVacina(VacinaDTO vacinaDTO) {
        String fabricante = vacinaDTO.getFabricante();
        if(fabricante.isBlank()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroFabricanteNulo())
            );
        }

        if(vacinaRepository.existsByFabricante(ConverterKeysUnicas.convert(fabricante))) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroFabricanteJaCadastrado(fabricante))
            );
        }

        if(vacinaDTO.isPrecisaSegundaDose() && vacinaDTO.getIntervaloEntreDoses() <= 0) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaSemIntervaloEntreDoses())
            );
        } else if(!vacinaDTO.isPrecisaSegundaDose() && vacinaDTO.getIntervaloEntreDoses() > 0) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaDeDoseUnica())
            );
        }

    }
}
