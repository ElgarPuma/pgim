package pe.gob.osinergmin.pgim.services.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTO;
import pe.gob.osinergmin.pgim.models.repository.RecursoExtraidoRepository;
import pe.gob.osinergmin.pgim.services.RecursoExtraidoService;

/**
 * @descripción: Servicio para recurso extraido (minerales metálicos)
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 21/12/2022
 */
@Service
@Transactional(readOnly = true)
public class RecursoExtraidoServiceImpl implements RecursoExtraidoService{

    @Autowired
	private RecursoExtraidoRepository recursoExtraidoRepository;
    

    @Override
    public Page<PgimRecursoExtraidoAuxDTO> listarRecursosExtraidosPaginado(PgimRecursoExtraidoAuxDTO filtro, Pageable paginador){
       
        String feInicio = "";
        String feFin = "";
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyyMM");
        if(filtro.getDescFeInicial() != null){
            feInicio = sdfr.format(filtro.getDescFeInicial());
        }
        if(filtro.getDescFeFinal() != null){
            feFin = sdfr.format(filtro.getDescFeFinal());
        }

        Page<PgimRecursoExtraidoAuxDTO> pPgimRecursoExtraidoAuxDTO = this.recursoExtraidoRepository.listarRecursosExtraidosPaginado(filtro.getDescNoUnidadMinera(),
            feInicio, feFin, paginador);

        return pPgimRecursoExtraidoAuxDTO;
    }

}
