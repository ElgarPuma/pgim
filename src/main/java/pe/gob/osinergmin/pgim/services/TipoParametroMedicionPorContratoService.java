package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;

/**
 * Tipo de parámetro de medición por contrato.
 * 
 * @descripción: Interfaz para la gestión de los parámetros de medición en el
 *               marco de los contratos de supervisión
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 */
public interface TipoParametroMedicionPorContratoService {

    /**
     * Permite obtener la lista de parámetros de medición por el identificador del
     * contrato de supervisión.
     * 
     * @param idContrato
     * @return
     */
    List<PgimTipopameXContratoDTO> listarParametrosPorContrato(Long idContrato);

    /**
     * Permite seleccionar y asociar los tipos de parámetros a un contrato determinado.
     * @param idContrato
     * @param lPgimTprmtroXTinstrmntoDTO
     * @param auditoriaDTO
     */
    void seleccionarTParametrosXTTipoInstrumento(Long idContrato,
            List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO, AuditoriaDTO auditoriaDTO);

    /**
     * Permite eliminar el tipo de parámetro de un contrato dado.
     * @param idTipopameXContrato
     * @param obtenerAuditoria
     */
    void eliminarTipoParametroDeContrato(Long idTipopameXContrato, AuditoriaDTO obtenerAuditoria);

}
