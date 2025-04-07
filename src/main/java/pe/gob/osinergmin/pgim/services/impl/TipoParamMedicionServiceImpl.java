package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoParametroMed;
import pe.gob.osinergmin.pgim.models.repository.PgimTipoParametroMedRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoParametroXTipoInstrumentoRepository;
import pe.gob.osinergmin.pgim.services.TipoParamMedicionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad tipo de parámetro de medición
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 12/09/2022
 */
@Service
@Transactional(readOnly = true)
public class TipoParamMedicionServiceImpl implements TipoParamMedicionService{

    @Autowired
    private PgimTipoParametroMedRepository pgimTipoParametroMedRepository;

    @Autowired
    private TipoParametroXTipoInstrumentoRepository tipoParametroXTipoInstrumentoRepository;

    @Override
    public PgimTipoParametroMed getByIdTipoParametroMed(Long idTipoParametroMed) {
        return this.pgimTipoParametroMedRepository.findById(idTipoParametroMed).orElse(null);
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTipoParametroMedDTO crearTipoParamMedicion(PgimTipoParametroMedDTO pgimTipoParametroMedDTO,
            AuditoriaDTO auditoriaDTO) {

        PgimTipoParametroMedDTO existe = this.pgimTipoParametroMedRepository.obtenerNombreParamMedicion(pgimTipoParametroMedDTO.getNoTipoParametro());
        if(existe != null){
            throw new PgimException(TipoResultado.WARNING,
                "El tipo de parámetro "+pgimTipoParametroMedDTO.getNoTipoParametro()+" ya se encuentra registrado, por favor ingrese un parámetro diferente." );            
        }
        
        PgimTipoParametroMed pgimTipoParametroMed = new PgimTipoParametroMed();

        String ultimoCorrelativo = this.pgimTipoParametroMedRepository.obtenerUtimoCoorelativoTP();
        Integer numeroCorrelativo = Integer.parseInt(ultimoCorrelativo.split("-")[1]) +1;

        String correlativo = String.format("%05d", numeroCorrelativo);
        pgimTipoParametroMed.setCoTipoParametro("TP-" + correlativo);

        pgimTipoParametroMed.setNoTipoParametro(pgimTipoParametroMedDTO.getNoTipoParametro());
        pgimTipoParametroMed.setDeTipoParametro(pgimTipoParametroMedDTO.getDeTipoParametro());
	    pgimTipoParametroMed.setEsRegistro(ConstantesUtil.IND_ACTIVO);

        pgimTipoParametroMed.setFeCreacion(auditoriaDTO.getFecha());
        pgimTipoParametroMed.setUsCreacion(auditoriaDTO.getUsername());
        pgimTipoParametroMed.setIpCreacion(auditoriaDTO.getTerminal());

        PgimTipoParametroMed pgimTipoParametroMedCreado = this.pgimTipoParametroMedRepository.save(pgimTipoParametroMed);

		PgimTipoParametroMedDTO pgimTipoParametroMedDTOCreado = this.obtenerIdParamMedicion(pgimTipoParametroMedCreado.getIdTipoParametroMed());

        return pgimTipoParametroMedDTOCreado;
    }

    private PgimTipoParametroMedDTO obtenerIdParamMedicion(Long idTipoParametroMed) {
        PgimTipoParametroMedDTO pgimTipoParametroMedDTO = new PgimTipoParametroMedDTO();
        pgimTipoParametroMedDTO = this.pgimTipoParametroMedRepository.obtenerIdParamMedicion(idTipoParametroMed);
        return pgimTipoParametroMedDTO; 
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTipoParametroMedDTO modificarTipoParamMedicion(@Valid PgimTipoParametroMedDTO pgimTipoParametroMedDTO,
    PgimTipoParametroMed pgimTipoParametroMedActual, AuditoriaDTO auditoriaDTO) {

        PgimTipoParametroMedDTO pgimTipoParametroMedDTOexiste = this.pgimTipoParametroMedRepository.obtenerNombreParamMedicion(pgimTipoParametroMedDTO.getNoTipoParametro());
        if(pgimTipoParametroMedDTOexiste != null){
            if(pgimTipoParametroMedDTOexiste.getNoTipoParametro().equals(pgimTipoParametroMedActual.getNoTipoParametro())){
                pgimTipoParametroMedActual.setDeTipoParametro(pgimTipoParametroMedDTO.getDeTipoParametro());
            }else{
                throw new PgimException(TipoResultado.WARNING,
                "El tipo de parámetro "+pgimTipoParametroMedDTO.getNoTipoParametro()+" ya se encuentra registrado, por favor ingrese un parámetro diferente." );            
            }
        }else{
            pgimTipoParametroMedActual.setNoTipoParametro(pgimTipoParametroMedDTO.getNoTipoParametro());
            pgimTipoParametroMedActual.setDeTipoParametro(pgimTipoParametroMedDTO.getDeTipoParametro());
        }

        pgimTipoParametroMedActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipoParametroMedActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipoParametroMedActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTipoParametroMed PgimTipoParametroMedModificado = this.pgimTipoParametroMedRepository.save(pgimTipoParametroMedActual);

        PgimTipoParametroMedDTO PgimTipoParametroMedDTOModificado = this.pgimTipoParametroMedRepository.obtenerIdParamMedicion(
            PgimTipoParametroMedModificado.getIdTipoParametroMed());

        return PgimTipoParametroMedDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTipoParametroMedDTO eliminarTipoParamMedicion(@Valid PgimTipoParametroMedDTO pgimTipoParametroMedDTO, PgimTipoParametroMed pgimTipoParametroMedActual, AuditoriaDTO auditoriaDTO) {

        List<PgimTprmtroXTinstrmntoDTO> existe = this.tipoParametroXTipoInstrumentoRepository
            .obtenerTPramUseXid(pgimTipoParametroMedDTO.getIdTipoParametroMed());

        if (existe.size() != 0) {  
                throw new PgimException(TipoResultado.WARNING,
                "El parámetro de medición esta siendo utilizado en al menos un instrumento, por lo que no es posible eliminarlo.");    
        }

        pgimTipoParametroMedActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimTipoParametroMedActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipoParametroMedActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipoParametroMedActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTipoParametroMed pgimTipoParametroMedEliminado = this.pgimTipoParametroMedRepository.save(pgimTipoParametroMedActual);

		PgimTipoParametroMedDTO pgimTipoParametroMedDTOEliminado = this.pgimTipoParametroMedRepository.obtenerIdParamMedicionElimado(pgimTipoParametroMedEliminado.getIdTipoParametroMed());

        return pgimTipoParametroMedDTOEliminado;
    }

    
}
