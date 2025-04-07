package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimResponsableSuperDTO;
import pe.gob.osinergmin.pgim.models.repository.ResponsableSuperRepository;
import pe.gob.osinergmin.pgim.services.ResponsableSuperService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Responsable super
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/07/2020
 * @fecha_de_ultima_actualización: 10/08/2020 
 */
@Service
@Transactional(readOnly = true)
public class ResponsableSuperServiceImpl implements ResponsableSuperService{
    
    @Autowired
    private ResponsableSuperRepository responsableSuperRepository;


    @Override
    public List<PgimResponsableSuperDTO> listarPorPalabraClave(String palabra) {
        List<PgimResponsableSuperDTO> lPgimResponsableSuperDTO = this.responsableSuperRepository.listarPorPalabraClave(palabra);

        return lPgimResponsableSuperDTO;
    }
}