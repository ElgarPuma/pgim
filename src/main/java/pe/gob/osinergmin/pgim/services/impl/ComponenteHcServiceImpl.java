package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimComponenteHcDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteHc;
import pe.gob.osinergmin.pgim.models.repository.ComponenteHcRepository;
import pe.gob.osinergmin.pgim.services.ComponenteHcService;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidad componentes de hecho
 *               verificado
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 18/12/2024
 * @fecha_de_ultima_actualización: 18/12/2024
 */
@Service
@Transactional(readOnly = true)
public class ComponenteHcServiceImpl implements ComponenteHcService {

    @Autowired
    private ComponenteHcRepository componenteHcRepository;

    @Override
    public List<PgimComponenteHcDTO> listarComponenteMineroHc(Long idHechoConstatado) {
        List<PgimComponenteHcDTO> lPgimComponenteHcDTO = this.componenteHcRepository
                .listarComponenteMineroHc(idHechoConstatado);
        return lPgimComponenteHcDTO;
    }

    @Override
    public PgimComponenteHcDTO obtenerComponenteMineroHcId(Long idComponenteHc) {
        PgimComponenteHcDTO pgimComponenteHcDTO = this.componenteHcRepository
                .obtenerComponenteMineroHcId(idComponenteHc);
        return pgimComponenteHcDTO;
    }

    @Override
    public PgimComponenteHc getByIdComponenteHc(Long idComponenteHc) {
        return this.componenteHcRepository.findById(idComponenteHc).orElse(null);
    }

}
