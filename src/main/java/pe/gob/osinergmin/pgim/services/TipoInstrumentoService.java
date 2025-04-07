package pe.gob.osinergmin.pgim.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoInstrumento;
import pe.gob.osinergmin.pgim.models.entity.PgimTprmtroXTinstrmnto;

/**
 * Tipo de instrumento de medición
 * 
 * @descripción: Interfaz para la gestión de los tipos de instrumentos y sus
 *               parámetros de medición
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 */
public interface TipoInstrumentoService {

    /**
     * Permite obtener el listado de instrumentos de medición y sus parámetros por
     * especialidad
     * 
     * @param idEspecialidad
     * @param idContrato
     * @return
     */
    List<PgimTprmtroXTinstrmntoDTO> listarInstrumentosYParametros(Long idEspecialidad, Long idContrato);

    /**
     * Permite obtener el listado de tipo de instrumentos de medición
     * @param pgimTipoInstrumentoDTO
     * @param paginador
     * @return
     */
    Page<PgimTipoInstrumentoDTO> listarTipoInstrumento(PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, Pageable paginador);

    /**
     * Permite obtener el listado de parámetros de medición
     * @param pgimTipoParametroMedDTO
     * @param paginador
     * @return
     */
    Page<PgimTipoParametroMedDTO> listarParamMedicion(PgimTipoParametroMedDTO pgimTipoParametroMedDTO, Pageable paginador);

    /**
     * Permite obtener el listado de parámetros por instrumentos 
     * @param pgimTprmtroXTinstrmntoDTO
     * @param paginador
     * @return
     */
    Page<PgimTprmtroXTinstrmntoDTO> listarParamXinstrumento(Long idTipoInstrumento, Pageable paginador);

    /**
     * Permite obtener el tipo de intrumento
     * @param idTipoInstrumento
     * @return
     */
    PgimTipoInstrumentoDTO obtenerTipoInstrumento(Long idTipoInstrumento);

    /**
     * Permite obtener un tipo de instrumento por el identificador
     * @param idTipoInstrumento
     * @return
     */
    PgimTipoInstrumento getByIdTipoInstrumento(Long idTipoInstrumento);

    /**
     * Permite crear un tipo de intrumento
     * @param pgimTipoInstrumentoDTO
     * @param auditoriaDTO
     * @return
     */
    PgimTipoInstrumentoDTO crearTipoInstrumento(PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite modificar un tipo de intrumento
     * @param pgimTipoInstrumentoDTO
     * @param pgimTipoInstrumentoActual
     * @param auditoriaDTO
     * @return
     */
    PgimTipoInstrumentoDTO modificarTipoInstrumento(@Valid PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, PgimTipoInstrumento pgimTipoInstrumentoActual,
                    AuditoriaDTO auditoriaDTO);

    /**
     * Permite registrar parámetros de medición para un instrumento de interés
     * @param lPgimTprmtroXTinstrmntoDTO
     * @param auditoriaDTO
     * @return
     */
    List<Object> registrarTiposParamXinstr(PgimTprmtroXTinstrmntoDTO[] lPgimTprmtroXTinstrmntoDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite obtener un parámetro de medición de un instrumento de interés por el indentificador
     * @param idTprmtroXTinstrmnto
     * @return
     */
    PgimTprmtroXTinstrmnto getByIdTipoParamXinstr(Long idTprmtroXTinstrmnto);

    /**
     * Permite quitar los parámetros de medición de un instrumento de interés.
     * @param pgimTprmtroXTinstrmntoDTO
     * @param pgimTipoParamXinstrActual
     * @param auditoriaDTO
     * @return
     */
    PgimTprmtroXTinstrmntoDTO quitarTipoParamXinstr(@Valid PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO, PgimTprmtroXTinstrmnto pgimTipoParamXinstrActual, AuditoriaDTO auditoriaDTO);

    /**
     * Permite la modificación de un parámetro de medición de un instrumento
     * @param pgimTprmtroXTinstrmntoDTO
     * @param pgimTprmtroXTinstrmntoActual
     * @param auditoriaDTO
     * @return
     */
    PgimTprmtroXTinstrmntoDTO modificarParamXinstr(@Valid PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO, PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmntoActual,
                    AuditoriaDTO auditoriaDTO);

    /**
     * Permite eliminar un tipo de instrumento
     * @param pgimTipoInstrumentoDTO
     * @param pgimTipoInstrumentoActual
     * @param auditoriaDTO
     * @return
     */
    PgimTipoInstrumentoDTO eliminarTipoInstrumento(@Valid PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, PgimTipoInstrumento pgimTipoInstrumentoActual, AuditoriaDTO auditoriaDTO);

}
