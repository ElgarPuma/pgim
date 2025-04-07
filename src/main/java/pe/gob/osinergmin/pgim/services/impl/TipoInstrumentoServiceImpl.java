package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoParametroMedDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoInstrumento;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoParametroMed;
import pe.gob.osinergmin.pgim.models.entity.PgimTprmtroXTinstrmnto;
import pe.gob.osinergmin.pgim.models.repository.PgimTipoParametroMedRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoInstrumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoParametroXTipoInstrumentoRepository;
import pe.gob.osinergmin.pgim.services.TipoInstrumentoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio para gestionar los tipos de parámestros e
 *               instrumentos maestros.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 07/08/2022
 */
@Service
@Transactional(readOnly = true)
public class TipoInstrumentoServiceImpl implements TipoInstrumentoService {

    @Autowired
    private TipoParametroXTipoInstrumentoRepository tipoParametroXTipoInstrumentoRepository;

    @Autowired
    private TipoInstrumentoRepository tipoInstrumentoRepository;

    @Autowired
    private PgimTipoParametroMedRepository pgimTipoParametroMedRepository;

    @Override
    public List<PgimTprmtroXTinstrmntoDTO> listarInstrumentosYParametros(Long idEspecialidad, Long idContrato) {
        List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO = this.tipoParametroXTipoInstrumentoRepository.listarInstrumentosYParametros(idEspecialidad, idContrato);
        
        return lPgimTprmtroXTinstrmntoDTO;
    }

    @Override
    public Page<PgimTipoInstrumentoDTO> listarTipoInstrumento( PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, Pageable paginador){
        Page<PgimTipoInstrumentoDTO> pPgimTipoInstrumentoDTO = this.tipoInstrumentoRepository.listarTipoInstrumento(pgimTipoInstrumentoDTO.getTextoBusqueda(), pgimTipoInstrumentoDTO.getIdEspecialidad(), pgimTipoInstrumentoDTO.getNoTipoInstrumento(), paginador);
        return pPgimTipoInstrumentoDTO; 
    }

    @Override
    public Page<PgimTipoParametroMedDTO> listarParamMedicion( PgimTipoParametroMedDTO pgimTipoParametroMedDTO, Pageable paginador){
        Page<PgimTipoParametroMedDTO> pPgimTipoParametroMedDTO = this.pgimTipoParametroMedRepository.listarParamMedicion(pgimTipoParametroMedDTO.getTextoBusqueda(), paginador);
        return pPgimTipoParametroMedDTO; 
    }

    @Override
    public Page<PgimTprmtroXTinstrmntoDTO> listarParamXinstrumento( Long idTipoInstrumento, Pageable paginador){
        Page<PgimTprmtroXTinstrmntoDTO> pPgimTprmtroXTinstrmntoDTO = this.tipoParametroXTipoInstrumentoRepository.listarParamXinstrumento(idTipoInstrumento, paginador);
        return pPgimTprmtroXTinstrmntoDTO; 
    }

    @Override
	public PgimTipoInstrumentoDTO obtenerTipoInstrumento(Long idTipoInstrumento) {
		return this.tipoInstrumentoRepository.obtenerTipoInstrumento(idTipoInstrumento);
	}

    @Override
    public PgimTipoInstrumento getByIdTipoInstrumento(Long idTipoInstrumento) {
        return this.tipoInstrumentoRepository.findById(idTipoInstrumento).orElse(null);
    }
    
    @Transactional(readOnly = false)
    @Override
    public PgimTipoInstrumentoDTO crearTipoInstrumento(PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO,
            AuditoriaDTO auditoriaDTO) {

        // Buscar si existe un instrumento registrado con el mismo nombre
        PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOExiste = this.tipoInstrumentoRepository
            .obtenerInstrumentoExistente(pgimTipoInstrumentoDTO.getIdEspecialidad(),
                    pgimTipoInstrumentoDTO.getNoTipoInstrumento());

        if (pgimTipoInstrumentoDTOExiste != null) {
            throw new PgimException(TipoResultado.WARNING,
                    "El instrumento de medición para la especialidad indicada ya se encuentra registrada, por favor regístre un instrumento diferente y vuelva a intentar");
        }
        
        PgimTipoInstrumento pgimTipoInstrumento = new PgimTipoInstrumento();

        String ultimoCorrelativo = this.tipoInstrumentoRepository.obtenerUtimoCoorelativoTI(pgimTipoInstrumentoDTO.getIdEspecialidad());
        Integer numeroCorrelativo = Integer.parseInt(ultimoCorrelativo.split("-")[2]) +1;
        String siglasEspecialidad = (pgimTipoInstrumentoDTO.getDescNoEspecialidad().toUpperCase()).substring(0, 4);

        String correlativo = String.format("%03d", numeroCorrelativo);
        pgimTipoInstrumento.setCoTipoInstrumento("TI-" +siglasEspecialidad+ "-" + correlativo);

        pgimTipoInstrumento.setNoTipoInstrumento(pgimTipoInstrumentoDTO.getNoTipoInstrumento());
        pgimTipoInstrumento.setDeTipoInstrumento(pgimTipoInstrumentoDTO.getDeTipoInstrumento());

        PgimEspecialidad pgimEspecialidad =  new PgimEspecialidad();
        pgimEspecialidad.setIdEspecialidad(pgimTipoInstrumentoDTO.getIdEspecialidad());
        pgimTipoInstrumento.setPgimEspecialidad(pgimEspecialidad);

	    pgimTipoInstrumento.setEsRegistro(ConstantesUtil.IND_ACTIVO);

        pgimTipoInstrumento.setFeCreacion(auditoriaDTO.getFecha());
        pgimTipoInstrumento.setUsCreacion(auditoriaDTO.getUsername());
        pgimTipoInstrumento.setIpCreacion(auditoriaDTO.getTerminal());

        PgimTipoInstrumento pgimTipoInstrumentoCreado = this.tipoInstrumentoRepository.save(pgimTipoInstrumento);
		PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOCreado = this.obtenerTipoInstrumento(pgimTipoInstrumentoCreado.getIdTipoInstrumento());

        return pgimTipoInstrumentoDTOCreado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTipoInstrumentoDTO modificarTipoInstrumento(@Valid PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO,
    PgimTipoInstrumento pgimTipoInstrumentoActual, AuditoriaDTO auditoriaDTO) {

        // Buscar si existe un instrumento registrado con el mismo nombre

        PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOExiste = this.tipoInstrumentoRepository
            .obtenerInstrumentoExistente(pgimTipoInstrumentoDTO.getIdEspecialidad(),
                    pgimTipoInstrumentoDTO.getNoTipoInstrumento());

        if (pgimTipoInstrumentoDTOExiste != null) {  
            if(pgimTipoInstrumentoDTOExiste.getNoTipoInstrumento().equals(pgimTipoInstrumentoActual.getNoTipoInstrumento())){
                pgimTipoInstrumentoActual.setDeTipoInstrumento(pgimTipoInstrumentoDTO.getDeTipoInstrumento());
            }else{
                throw new PgimException(TipoResultado.WARNING,
                "El instrumento de medición para la especialidad indicada ya se encuentra registrada, por favor ingrese un instrumento diferente y vuelva a intentar");    
            }
        } else {
            pgimTipoInstrumentoActual.setNoTipoInstrumento(pgimTipoInstrumentoDTO.getNoTipoInstrumento());
            pgimTipoInstrumentoActual.setDeTipoInstrumento(pgimTipoInstrumentoDTO.getDeTipoInstrumento());
        }

        pgimTipoInstrumentoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipoInstrumentoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipoInstrumentoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTipoInstrumento PgimTipoParametroMedModificado = this.tipoInstrumentoRepository.save(pgimTipoInstrumentoActual);

		PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOModificado = this.obtenerTipoInstrumento(PgimTipoParametroMedModificado.getIdTipoInstrumento());

        return pgimTipoInstrumentoDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public List<Object> registrarTiposParamXinstr(PgimTprmtroXTinstrmntoDTO[] lPgimTprmtroXTinstrmntoDTO,
            AuditoriaDTO auditoriaDTO) {
        
        List<Object> lPgimTprmtroXTinstrmntoDTOSel = new ArrayList<>();

        // Buscar si existe el parametro seleccionado para el instrumento de interés
        for (PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO : lPgimTprmtroXTinstrmntoDTO) {

            PgimTprmtroXTinstrmntoDTO PgimTprmtroXTinstrmntoDTOExiste = this.tipoParametroXTipoInstrumentoRepository
                .obtenerParamXinstrExistente(pgimTprmtroXTinstrmntoDTO.getIdTipoInstrumento(),
                pgimTprmtroXTinstrmntoDTO.getIdTipoParametroMed());

            if(PgimTprmtroXTinstrmntoDTOExiste == null){
                PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmnto = new PgimTprmtroXTinstrmnto();

                PgimTipoInstrumento pgimTipoInstrumento = new PgimTipoInstrumento();
                pgimTipoInstrumento.setIdTipoInstrumento(pgimTprmtroXTinstrmntoDTO.getIdTipoInstrumento());
                pgimTprmtroXTinstrmnto.setPgimTipoInstrumento(pgimTipoInstrumento);

                PgimTipoParametroMed pgimTipoParametroMed = new PgimTipoParametroMed();
                pgimTipoParametroMed.setIdTipoParametroMed(pgimTprmtroXTinstrmntoDTO.getIdTipoParametroMed());
                pgimTprmtroXTinstrmnto.setPgimTipoParametroMed(pgimTipoParametroMed);

                pgimTprmtroXTinstrmnto.setDeRangoMedicion("Por definir");

                pgimTprmtroXTinstrmnto.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        
                pgimTprmtroXTinstrmnto.setFeCreacion(auditoriaDTO.getFecha());
                pgimTprmtroXTinstrmnto.setUsCreacion(auditoriaDTO.getUsername());
                pgimTprmtroXTinstrmnto.setIpCreacion(auditoriaDTO.getTerminal());

                PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmntoCreado = this.tipoParametroXTipoInstrumentoRepository.save(pgimTprmtroXTinstrmnto);
                PgimTprmtroXTinstrmntoDTO pgimTipoInstrumentoDTOCreadoreado = this.tipoParametroXTipoInstrumentoRepository.obtenerParamXinstrExistente(pgimTprmtroXTinstrmntoCreado.getPgimTipoInstrumento().getIdTipoInstrumento(),pgimTprmtroXTinstrmntoCreado.getPgimTipoParametroMed().getIdTipoParametroMed());
                lPgimTprmtroXTinstrmntoDTOSel.add(pgimTipoInstrumentoDTOCreadoreado);
            }
        }


        return lPgimTprmtroXTinstrmntoDTOSel;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTprmtroXTinstrmntoDTO quitarTipoParamXinstr(@Valid PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO, PgimTprmtroXTinstrmnto pgimTipoParamXinstrActual, AuditoriaDTO auditoriaDTO) {

        PgimTprmtroXTinstrmntoDTO pgimTipoInstrumentoDTOExisteContrato = this.tipoParametroXTipoInstrumentoRepository
            .obtenerTPramXinstrConContratoXid(pgimTprmtroXTinstrmntoDTO.getIdTipoInstrumento(),pgimTprmtroXTinstrmntoDTO.getIdTipoParametroMed());

        if (pgimTipoInstrumentoDTOExisteContrato != null) {  
                throw new PgimException(TipoResultado.WARNING,
                "El parámetro de medición del instrumento no se puede deseleccionar pues esta siendo usado en el marco de algún contrato.");    
        }
               
        pgimTipoParamXinstrActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimTipoParamXinstrActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipoParamXinstrActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipoParamXinstrActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmntoModificado = this.tipoParametroXTipoInstrumentoRepository.save(pgimTipoParamXinstrActual);

		PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTOModificado = this.tipoParametroXTipoInstrumentoRepository.obtenerParamXinstrXidQuitado(pgimTprmtroXTinstrmntoModificado.getIdTprmtroXTinstrmnto());

        return pgimTprmtroXTinstrmntoDTOModificado;
    }

    @Override
    public PgimTprmtroXTinstrmnto getByIdTipoParamXinstr(Long idTprmtroXTinstrmnto) {
        return this.tipoParametroXTipoInstrumentoRepository.findById(idTprmtroXTinstrmnto).orElse(null);
    }


    @Transactional(readOnly = false)
    @Override
    public PgimTprmtroXTinstrmntoDTO modificarParamXinstr(@Valid PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO,
    PgimTprmtroXTinstrmnto pgimTprmtroXTinstrmntoActual, AuditoriaDTO auditoriaDTO) {

        if((pgimTprmtroXTinstrmntoDTO.getDePrecision() == null || pgimTprmtroXTinstrmntoDTO.getDePrecision() == "") && (pgimTprmtroXTinstrmntoDTO.getDeExactitud() == null || pgimTprmtroXTinstrmntoDTO.getDeExactitud() == "" )){
                throw new PgimException(TipoResultado.WARNING,
                "Es necesario que registre al menos uno de los siguientes campos: 'Precisión' y/o 'Exactitud'");            
        }

        pgimTprmtroXTinstrmntoActual.setDeRangoMedicion(pgimTprmtroXTinstrmntoDTO.getDeRangoMedicion());
        pgimTprmtroXTinstrmntoActual.setDePrecision(pgimTprmtroXTinstrmntoDTO.getDePrecision());
        pgimTprmtroXTinstrmntoActual.setDeExactitud(pgimTprmtroXTinstrmntoDTO.getDeExactitud());
        pgimTprmtroXTinstrmntoActual.setDeResolucion(pgimTprmtroXTinstrmntoDTO.getDeResolucion());

        pgimTprmtroXTinstrmntoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTprmtroXTinstrmntoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTprmtroXTinstrmntoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTprmtroXTinstrmnto PgimTipoParametroMedModificado = this.tipoParametroXTipoInstrumentoRepository.save(pgimTprmtroXTinstrmntoActual);

		PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTOModificado = this.tipoParametroXTipoInstrumentoRepository.obtenerParamXinstrXid(PgimTipoParametroMedModificado.getIdTprmtroXTinstrmnto());

        return pgimTprmtroXTinstrmntoDTOModificado;
    }

    @Transactional(readOnly = false)
    @Override
    public PgimTipoInstrumentoDTO eliminarTipoInstrumento(@Valid PgimTipoInstrumentoDTO pgimTipoInstrumentoDTO, PgimTipoInstrumento pgimTipoInstrumentoActual, AuditoriaDTO auditoriaDTO) {

        List<PgimTprmtroXTinstrmntoDTO> pgimTipoInstrumentoDTOExisteContrato = this.tipoParametroXTipoInstrumentoRepository
            .obtenerTInstrConContratoXid(pgimTipoInstrumentoDTO.getIdTipoInstrumento());

        if (pgimTipoInstrumentoDTOExisteContrato.size() != 0) {  
                throw new PgimException(TipoResultado.WARNING,
                "El tipo de instrumento de medición no se puede eliminar pues esta siendo usado en el marco de algún contrato.");    
        }else{
            List<PgimTprmtroXTinstrmntoDTO> lParametrosXinstr =  this.tipoParametroXTipoInstrumentoRepository.lParamXinstrumento(pgimTipoInstrumentoDTO.getIdTipoInstrumento());
            if(lParametrosXinstr.size() != 0){
                for (PgimTprmtroXTinstrmntoDTO pgimTprmtroXTinstrmntoDTO : lParametrosXinstr) {
                    PgimTprmtroXTinstrmnto paramXinstrActual = this.getByIdTipoParamXinstr(pgimTprmtroXTinstrmntoDTO.getIdTprmtroXTinstrmnto());
                    if(paramXinstrActual != null){
                        paramXinstrActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                        paramXinstrActual.setFeActualizacion(auditoriaDTO.getFecha());
                        paramXinstrActual.setUsActualizacion(auditoriaDTO.getUsername());
                        paramXinstrActual.setIpActualizacion(auditoriaDTO.getTerminal());
                    }
                }
            }
        }
        
        pgimTipoInstrumentoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimTipoInstrumentoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTipoInstrumentoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTipoInstrumentoActual.setIpActualizacion(auditoriaDTO.getTerminal());

        PgimTipoInstrumento pgimTipoInstrumentoEliminado = this.tipoInstrumentoRepository.save(pgimTipoInstrumentoActual);

		PgimTipoInstrumentoDTO pgimTipoInstrumentoDTOEliminado = this.tipoInstrumentoRepository.obtenerTipoInstrumento(pgimTipoInstrumentoEliminado.getIdTipoInstrumento());

        return pgimTipoInstrumentoDTOEliminado;
    }
}