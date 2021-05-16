package com.ufcg.psoft.vacineja.service;

import com.ufcg.psoft.vacineja.dtos.VacinaAtualizaDTO;
import com.ufcg.psoft.vacineja.dtos.VacinaDTO;
import com.ufcg.psoft.vacineja.model.Vacina;
import com.ufcg.psoft.vacineja.repository.VacinaRepository;
import com.ufcg.psoft.vacineja.utils.StringUtil;
import com.ufcg.psoft.vacineja.utils.error.ErroVacina;
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
        vacinaDTO.setFabricante(StringUtil.converterKeysUnicas(vacinaDTO.getFabricante()));
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

    public Vacina editarVacina(Long id, VacinaAtualizaDTO vacinaDTO) {
        Optional<Vacina> optionalVacina = vacinaRepository.findById(id);

        if(optionalVacina.isEmpty()) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaNaoEncontrada(id), HttpStatus.NOT_FOUND)
            );
        }

        Vacina vacina = optionalVacina.get();

        if(!vacinaDTO.getFabricante().isBlank()) {
            vacina.setFabricante(StringUtil.converterKeysUnicas(vacinaDTO.getFabricante()));
        }

        if(vacinaDTO.getFabricante() != null) {
        	vacina.setPrecisaSegundaDose(vacinaDTO.getPrecisaSegundaDose());
        }

        if(vacinaDTO.getIntervaloEntreDoses() != null && vacina.isPrecisaSegundaDose()) {
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

        if(vacinaRepository.existsByFabricante(StringUtil.converterKeysUnicas(fabricante))) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroFabricanteJaCadastrado(fabricante))
            );
        }

        if(!vacinaDTO.getPrecisaSegundaDose() && vacinaDTO.getIntervaloEntreDoses() > 0) {
            throw new ValidacaoException(
                    new ErroDeSistema(ErroVacina.erroVacinaDeDoseUnica())
            );
        }

    }
}
