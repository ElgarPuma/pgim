package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimTipopameXContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimTprmtroXTinstrmnto;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoParametroMedicionPorContratoRepository;
import pe.gob.osinergmin.pgim.services.TipoParametroMedicionPorContratoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Tipo de parametro de medición por contrato
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class TipoParametroMedicionPorContratoServiceImpl implements TipoParametroMedicionPorContratoService {

    @Autowired
    private TipoParametroMedicionPorContratoRepository tipoParametroMedicionPorContratoRepository;

    @Autowired
    private SupervisionRepository supervisionRepository;

    public List<PgimTipopameXContratoDTO> listarParametrosPorContrato(Long idContrato) {
        List<PgimTipopameXContratoDTO> lPgimTipopameXContratoDTO = null;

        lPgimTipopameXContratoDTO = this.tipoParametroMedicionPorContratoRepository
                .listarParametrosPorContrato(idContrato);

        return lPgimTipopameXContratoDTO;
    }

    @Transactional(readOnly = false)
    @Override
    public void seleccionarTParametrosXTTipoInstrumento(Long idContrato,
            List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTOParaAsociar, AuditoriaDTO auditoriaDTO) {

        List<PgimTipopameXContratoDTO> lPgimTipopameXContrato = this.tipoParametroMedicionPorContratoRepository
                .listarParametrosPorContrato(idContrato);

        PgimTipopameXContrato pgimTipopameXContrato = null;

        boolean seEncuentra;

        for (PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTOParaAsociar : lPgimTprmtroXTinstrmntoDTOParaAsociar) {

            seEncuentra = false;

            for (PgimTipopameXContratoDTO pgimTipopameXContratoDTO : lPgimTipopameXContrato) {
                if (pgimTprmtroXTinstrmntoDTOParaAsociar.getIdTprmtroXTinstrmnto()
                        .equals(pgimTipopameXContratoDTO.getIdTprmtroXTinstrmnto())) {
                    seEncuentra = true;
                    break;
                }
            }

            if (!seEncuentra) {
                // ¿Elemento de base de datos NO encuentra en la lista de seleccionados?
                pgimTipopameXContrato = new PgimTipopameXContrato();

                pgimTipopameXContrato.setPgimContrato(new PgimContrato());
                pgimTipopameXContrato.getPgimContrato().setIdContrato(idContrato);

                pgimTipopameXContrato.setPgimTprmtroXTinstrmnto(new PgimTprmtroXTinstrmnto());
                pgimTipopameXContrato.getPgimTprmtroXTinstrmnto()
                        .setIdTprmtroXTinstrmnto(pgimTprmtroXTinstrmntoDTOParaAsociar.getIdTprmtroXTinstrmnto());

                pgimTipopameXContrato.setDeRangoMedicion(pgimTprmtroXTinstrmntoDTOParaAsociar.getDeRangoMedicion());
                pgimTipopameXContrato.setDePrecision(pgimTprmtroXTinstrmntoDTOParaAsociar.getDePrecision());
                pgimTipopameXContrato.setDeExactitud(pgimTprmtroXTinstrmntoDTOParaAsociar.getDeExactitud());
                pgimTipopameXContrato.setDeResolucion(pgimTprmtroXTinstrmntoDTOParaAsociar.getDeResolucion());

                pgimTipopameXContrato.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimTipopameXContrato.setUsCreacion(auditoriaDTO.getUsername());
                pgimTipopameXContrato.setFeCreacion(auditoriaDTO.getFecha());
                pgimTipopameXContrato.setIpCreacion(auditoriaDTO.getTerminal());

                this.tipoParametroMedicionPorContratoRepository.save(pgimTipopameXContrato);
            }

        }

    }

    @Transactional(readOnly = false)
    @Override
    public void eliminarTipoParametroDeContrato(Long idTipopameXContrato, AuditoriaDTO auditoriaDTO) {
        
        PgimTipopameXContrato pgimTipopameXContrato = this.tipoParametroMedicionPorContratoRepository
                .findById(idTipopameXContrato).orElse(null);
            
        Long idTprmtroXTinstrmnto =  pgimTipopameXContrato.getPgimTprmtroXTinstrmnto().getIdTprmtroXTinstrmnto();
       
        Long idContrato = pgimTipopameXContrato.getPgimContrato().getIdContrato();

        List<PgimSupervisionDTO> lPgimSupervisionDTO = this.supervisionRepository.obtenerFiscalizacionesAfectadasPorTipoParametro(idContrato, idTprmtroXTinstrmnto);

        String mensajeExcepcion = "";
        int nroRegistros = lPgimSupervisionDTO.size();
        
        if (nroRegistros > 0) {

            int limite = 5;
            int contador = 0;

            for (PgimSupervisionDTO pgimSupervisionDTO : lPgimSupervisionDTO) {
                contador = contador + 1;

                if (mensajeExcepcion == "") {
                    mensajeExcepcion = String.format("Aquí algunas: %s", pgimSupervisionDTO.getCoSupervision());
                } else {
                    mensajeExcepcion = String.format("%s, %s", mensajeExcepcion, pgimSupervisionDTO.getCoSupervision());
                }

                if (contador >= limite) {
                    break;
                }
            }

            mensajeExcepcion = String.format("Existen fiscalizaciones (cantidad = %d) que hacen uso de este tipo de parámetro y tipo de instrumento, por ello, la deselección no podrá realizarse. %s", nroRegistros, mensajeExcepcion);

            throw new PgimException("error", mensajeExcepcion);
        }

        // Se debe validar si es que el tipo de parámetro está siendo empleado en alguna fiscalización relacionada al contrato.    

        pgimTipopameXContrato.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimTipopameXContrato.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipopameXContrato.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipopameXContrato.setIpActualizacion(auditoriaDTO.getTerminal());

        this.tipoParametroMedicionPorContratoRepository.save(pgimTipopameXContrato);
    }

}
