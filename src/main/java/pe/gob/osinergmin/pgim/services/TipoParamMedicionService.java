package pe.gob.osinergmin.pgim.services;

import javax.validation.Valid;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoParametroMed;

/**
 * Interfaz para la gestión de los servicios relacionados con tipo de parámetro de medición
 * 
 * @descripción: Tipo de parámetro de medición
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 12/09/2022
 */
public interface TipoParamMedicionService {

    /**
     * Permite obtener un tipo de parámetro de medición por identificador
     * @param idTipoParametroMed
     * @return
     */
    PgimTipoParametroMed getByIdTipoParametroMed(Long idTipoParametroMed);

    /**
     * Permite crear un tipo de parámetro de medición
     * @param PgimTipoParametroMedDTO
     * @param auditoriaDTO
     * @return
     */
    PgimTipoParametroMedDTO crearTipoParamMedicion(PgimTipoParametroMedDTO PgimTipoParametroMedDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite modificar un tipo de parámetro de medición
     * 
     * @param pgimTipoParametroMedDTO
     * @param auditoriaDTO
     * @return
     */
    PgimTipoParametroMedDTO modificarTipoParamMedicion(@Valid PgimTipoParametroMedDTO pgimTipoParametroMedDTO, PgimTipoParametroMed pgimTipoParametroMedActual,
                    AuditoriaDTO auditoriaDTO);
    

    /**
     * Permite eliminar un tipo de parámetro de medición
     * @param pgimTipoParametroMedDTO
     * @param PgimTipoParametroMedActual
     * @param auditoriaDTO
     * @return
     */
    PgimTipoParametroMedDTO eliminarTipoParamMedicion(@Valid PgimTipoParametroMedDTO pgimTipoParametroMedDTO, PgimTipoParametroMed PgimTipoParametroMedActual, AuditoriaDTO auditoriaDTO);


}
