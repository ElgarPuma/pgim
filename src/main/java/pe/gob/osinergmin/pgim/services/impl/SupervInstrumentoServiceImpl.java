package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstrmntoXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrmtroXSupervDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTipopameXContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstrmntoXSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimPrmtroXSuperv;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoInstrumento;
import pe.gob.osinergmin.pgim.models.entity.PgimTipopameXContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.PrmtroXSupervRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.SupervInstrumentoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad instrumentos de supervisión
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 02/08/2022
 */
@Service
@Transactional(readOnly = true)
public class SupervInstrumentoServiceImpl implements SupervInstrumentoService{
  
  @Autowired
  private SupervInstrumentoRepository supervInstrumentoRepository;
  
  @Autowired
  private PrmtroXSupervRepository prmtroXSupervRepository;
  
  @Autowired
	private ValorParametroRepository valorParametroRepository;
  
  @Override
  public Page<PgimInstrmntoXSupervDTO> listar( PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, Pageable paginador){
      Page<PgimInstrmntoXSupervDTO> pPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.listar(pgimInstrmntoXSupervDTO.getIdSupervision(), paginador);
      return pPgimInstrmntoXSupervDTO; 
  }

  @Override
  public List<PgimInstrmntoXSupervDTO> listarInstXsuperv( String codSupervision){
      List<PgimInstrmntoXSupervDTO> pPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.listarInstXsuperv(codSupervision,EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString(), EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString(),EValorParametro.TIPO_ESTDO_INSTRMNTO_MODIFICADO.toString());
      return pPgimInstrmntoXSupervDTO; 
  }

  @Override
  public List<PgimInstrmntoXSupervDTO> ObtenerListaInstrumentoRegAprob( Long  idSupervision, String estRegistrado, String estAprobado, String estModificado){
      List<PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.ObtenerListaInstrumentoRegAprob(idSupervision, estRegistrado, estAprobado, estModificado);
      return lPgimInstrmntoXSupervDTO; 
  }

  @Transactional(readOnly = false)
	@Override
	public PgimInstrmntoXSupervDTO crearInstrumentoXSuperv(PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, AuditoriaDTO auditoriaDTO) {

    PgimInstrmntoXSuperv pgimInstrmntoXSuperv = new PgimInstrmntoXSuperv();

    pgimInstrmntoXSuperv.setPgimTipoInstrumento(new PgimTipoInstrumento());
    pgimInstrmntoXSuperv.getPgimTipoInstrumento().setIdTipoInstrumento(pgimInstrmntoXSupervDTO.getIdTipoInstrumento());

    pgimInstrmntoXSuperv.setPgimSupervision(new PgimSupervision());
    pgimInstrmntoXSuperv.getPgimSupervision().setIdSupervision(pgimInstrmntoXSupervDTO.getIdSupervision());;

    Long idEstado = this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString());
    pgimInstrmntoXSuperv.setEstadoInstrumento( new PgimValorParametro());
    pgimInstrmntoXSuperv.getEstadoInstrumento().setIdValorParametro(idEstado);
    
    pgimInstrmntoXSuperv.setPgimInstanciaPaso(new PgimInstanciaPaso());
    pgimInstrmntoXSuperv.getPgimInstanciaPaso().setIdInstanciaPaso(pgimInstrmntoXSupervDTO.getIdInstanciaPaso());

    pgimInstrmntoXSuperv.setCoInstrumento("cod");
    pgimInstrmntoXSuperv.setCoSerieInstrumento(pgimInstrmntoXSupervDTO.getCoSerieInstrumento());
    pgimInstrmntoXSuperv.setNoMarcaInstrumento(pgimInstrmntoXSupervDTO.getNoMarcaInstrumento());
    pgimInstrmntoXSuperv.setNoModeloInstrumento(pgimInstrmntoXSupervDTO.getNoModeloInstrumento());
    pgimInstrmntoXSuperv.setCoCertificadoCalibracion(pgimInstrmntoXSupervDTO.getCoCertificadoCalibracion());
    pgimInstrmntoXSuperv.setNoLaboratorioCalibracion(pgimInstrmntoXSupervDTO.getNoLaboratorioCalibracion());
    pgimInstrmntoXSuperv.setFeCalibracion(pgimInstrmntoXSupervDTO.getFeCalibracion());
    pgimInstrmntoXSuperv.setFeVencimientoCalibracion(pgimInstrmntoXSupervDTO.getFeVencimientoCalibracion());

		pgimInstrmntoXSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstrmntoXSuperv.setFeCreacion(auditoriaDTO.getFecha());
		pgimInstrmntoXSuperv.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstrmntoXSuperv.setIpCreacion(auditoriaDTO.getTerminal());

		PgimInstrmntoXSuperv pgimInstrmntoXSupervCreado = this.supervInstrumentoRepository.save(pgimInstrmntoXSuperv);

    pgimInstrmntoXSuperv.setCoInstrumento(pgimInstrmntoXSuperv.getIdInstrmntoXSuperv().toString());
    pgimInstrmntoXSupervCreado = this.supervInstrumentoRepository.save(pgimInstrmntoXSuperv);

		PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTOCreada = this.obtenerInstrumentoXSupervPorId(pgimInstrmntoXSupervCreado.getIdInstrmntoXSuperv());

    for (PgimTipopameXContratoDTO pgimTipopameXContratoDTO : pgimInstrmntoXSupervDTO.getDescLPgimTipopameXContrato()) {
      PgimPrmtroXSuperv pgimPrmtroXSuperv = new PgimPrmtroXSuperv();
      
      pgimPrmtroXSuperv.setPgimInstrmntoXSuperv(new PgimInstrmntoXSuperv());
      pgimPrmtroXSuperv.getPgimInstrmntoXSuperv().setIdInstrmntoXSuperv(pgimInstrmntoXSuperv.getIdInstrmntoXSuperv());

      pgimPrmtroXSuperv.setPgimTipopameXContrato(new PgimTipopameXContrato());
      pgimPrmtroXSuperv.getPgimTipopameXContrato().setIdTipopameXContrato(pgimTipopameXContratoDTO.getIdTipopameXContrato());

      pgimPrmtroXSuperv.setDeRangoMedicion(pgimTipopameXContratoDTO.getDeRangoMedicion());
      pgimPrmtroXSuperv.setDePrecision(pgimTipopameXContratoDTO.getDePrecision());
      pgimPrmtroXSuperv.setDeExactitud(pgimTipopameXContratoDTO.getDeExactitud());
      pgimPrmtroXSuperv.setDeResolucion(pgimTipopameXContratoDTO.getDeResolucion());

      pgimPrmtroXSuperv.setEsRegistro(ConstantesUtil.IND_ACTIVO);
      pgimPrmtroXSuperv.setFeCreacion(auditoriaDTO.getFecha());
      pgimPrmtroXSuperv.setUsCreacion(auditoriaDTO.getUsername());
      pgimPrmtroXSuperv.setIpCreacion(auditoriaDTO.getTerminal());

      this.prmtroXSupervRepository.save(pgimPrmtroXSuperv);
    }

    if(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv() != 0){
      PgimInstrmntoXSuperv pgimInstrmntoXSupervActual = this.getByIdInstrumentoXSuperv(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv());
      String coEstado = EValorParametro.TIPO_ESTDO_INSTRMNTO_REEMPLAZADO.toString();

      pgimInstrmntoXSupervActual.setInstrmntoXSupervRmplzo(new PgimInstrmntoXSuperv());
      pgimInstrmntoXSupervActual.getInstrmntoXSupervRmplzo().setIdInstrmntoXSuperv(pgimInstrmntoXSupervDTOCreada.getIdInstrmntoXSuperv());

      this.cambiarEstadoInstrumentoXSuperv(coEstado, pgimInstrmntoXSupervActual, auditoriaDTO);
      
    }
    
		return pgimInstrmntoXSupervDTOCreada;

	}

  @Transactional(readOnly = false)
	@Override
	public PgimInstrmntoXSupervDTO modificarInstrumentoXSuperv(PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO, PgimInstrmntoXSuperv pgimInstrmntoXSuperv , AuditoriaDTO auditoriaDTO) {
    
    Long idEstado = this.valorParametroRepository.obtenerIdValorParametro(pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento());
    pgimInstrmntoXSuperv.setEstadoInstrumento( new PgimValorParametro());
    pgimInstrmntoXSuperv.getEstadoInstrumento().setIdValorParametro(idEstado);

    pgimInstrmntoXSuperv.setCoSerieInstrumento(pgimInstrmntoXSupervDTO.getCoSerieInstrumento());
    pgimInstrmntoXSuperv.setNoMarcaInstrumento(pgimInstrmntoXSupervDTO.getNoMarcaInstrumento());
    pgimInstrmntoXSuperv.setNoModeloInstrumento(pgimInstrmntoXSupervDTO.getNoModeloInstrumento());
    pgimInstrmntoXSuperv.setCoCertificadoCalibracion(pgimInstrmntoXSupervDTO.getCoCertificadoCalibracion());
    pgimInstrmntoXSuperv.setNoLaboratorioCalibracion(pgimInstrmntoXSupervDTO.getNoLaboratorioCalibracion());
    pgimInstrmntoXSuperv.setFeCalibracion(pgimInstrmntoXSupervDTO.getFeCalibracion());
    pgimInstrmntoXSuperv.setFeVencimientoCalibracion(pgimInstrmntoXSupervDTO.getFeVencimientoCalibracion());

		pgimInstrmntoXSuperv.setFeActualizacion(auditoriaDTO.getFecha());
		pgimInstrmntoXSuperv.setUsActualizacion(auditoriaDTO.getUsername());
		pgimInstrmntoXSuperv.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimInstrmntoXSuperv pgimInstrmntoXSupervModificado = this.supervInstrumentoRepository.save(pgimInstrmntoXSuperv);

		PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTOModificado = this.obtenerInstrumentoXSupervPorId(pgimInstrmntoXSupervModificado.getIdInstrmntoXSuperv());

   	return pgimInstrmntoXSupervDTOModificado;

	}
  
  

  @Transactional(readOnly = false)
  @Override
  public PgimInstrmntoXSupervDTO cambiarEstadoInstrumentoXSuperv(String coEstado, PgimInstrmntoXSuperv pgimInstrmntoXSuperv , AuditoriaDTO auditoriaDTO) {
    
    Long idEstado = this.valorParametroRepository.obtenerIdValorParametro(coEstado);

    pgimInstrmntoXSuperv.setEstadoInstrumento( new PgimValorParametro());
    pgimInstrmntoXSuperv.getEstadoInstrumento().setIdValorParametro(idEstado);

		pgimInstrmntoXSuperv.setFeActualizacion(auditoriaDTO.getFecha());
		pgimInstrmntoXSuperv.setUsActualizacion(auditoriaDTO.getUsername());
		pgimInstrmntoXSuperv.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimInstrmntoXSuperv pgimInstrmntoXSupervModificado = this.supervInstrumentoRepository.save(pgimInstrmntoXSuperv);

		PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTOModificado = this.obtenerInstrumentoXSupervPorId(pgimInstrmntoXSupervModificado.getIdInstrmntoXSuperv());

   	return pgimInstrmntoXSupervDTOModificado;

  }

  @Transactional(readOnly = false)
  @Override
  public void eliminarInstrumentoXSuperv(PgimInstrmntoXSuperv pgimInstrmntoXSupervActual, PgimInstrmntoXSuperv pgimInstrmntoXSupervReemplazado, AuditoriaDTO auditoriaDTO) {

    if(pgimInstrmntoXSupervReemplazado != null){
      String coEstado = EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString();
      pgimInstrmntoXSupervReemplazado.setInstrmntoXSupervRmplzo(null);
      this.cambiarEstadoInstrumentoXSuperv(coEstado, pgimInstrmntoXSupervReemplazado, auditoriaDTO);

    }


    List <PgimPrmtroXSupervDTO> lPgimPrmtroXSupervDTO = this.prmtroXSupervRepository.obtenerParamInstrumentoSuperv(pgimInstrmntoXSupervActual.getIdInstrmntoXSuperv());

    for (PgimPrmtroXSupervDTO pgimPrmtroXSupervDTO : lPgimPrmtroXSupervDTO) {

      PgimPrmtroXSuperv pgimPrmtroXSuperv =  this.prmtroXSupervRepository.findById(pgimPrmtroXSupervDTO.getIdPrmtroXSuperv()).orElse(null);
      pgimPrmtroXSuperv.setEsRegistro(ConstantesUtil.IND_INACTIVO);
      pgimPrmtroXSuperv.setFeActualizacion(auditoriaDTO.getFecha());
      pgimPrmtroXSuperv.setUsActualizacion(auditoriaDTO.getUsername());
      pgimPrmtroXSuperv.setIpActualizacion(auditoriaDTO.getTerminal());

      this.prmtroXSupervRepository.save(pgimPrmtroXSuperv);
    }

    pgimInstrmntoXSupervActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    pgimInstrmntoXSupervActual.setFeActualizacion(auditoriaDTO.getFecha());
    pgimInstrmntoXSupervActual.setUsActualizacion(auditoriaDTO.getUsername());
    pgimInstrmntoXSupervActual.setIpActualizacion(auditoriaDTO.getTerminal());

    this.supervInstrumentoRepository.save(pgimInstrmntoXSupervActual);

  }

  @Override
  public PgimInstrmntoXSupervDTO obtenerInstrumentoXSupervPorId(Long idInstrmntoXSuperv){
    PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO = new PgimInstrmntoXSupervDTO();
    pgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.obtenerInstrmntoXSupervDTOPorId(idInstrmntoXSuperv);
    return pgimInstrmntoXSupervDTO; 
  }

  @Override
  public PgimInstrmntoXSuperv getByIdInstrumentoXSuperv(Long idInstrmntoXSuperv){
    return this.supervInstrumentoRepository.findById(idInstrmntoXSuperv).orElse(null); 
  }
  
  @Override
  public List<PgimTipoInstrumentoDTO> obtenerTipoInstrumentoPorIdContrato(Long idContrato) {
    List<PgimTipoInstrumentoDTO> lPgimTipoinsXContratoDTO = this.supervInstrumentoRepository.obtenerTipoInstrumentoPorIdContrato(idContrato);

		return lPgimTipoinsXContratoDTO;
  }

  @Override
  public List<PgimTipopameXContratoDTO> obtenerTipoParamInstrumentoContrato(Long idContrato) {
    List<PgimTipopameXContratoDTO> lPgimTipopameXContratoDTO = this.supervInstrumentoRepository.obtenerTipoParamPorIdContrato(idContrato);

		return lPgimTipopameXContratoDTO;
  }

  @Override
  public List<PgimPrmtroXSupervDTO> obtenerParamInstrumentoSuperv(Long instrmntoXSuperv) {
    List <PgimPrmtroXSupervDTO> lPgimPrmtroXSupervDTO = this.prmtroXSupervRepository.obtenerParamInstrumentoSuperv(instrmntoXSuperv);
    return lPgimPrmtroXSupervDTO;
  }

  @Override
  public Page<PgimInstrmntoXSupervDTO> listarInstrumentoParaAprobar( Long idSupervision, Pageable paginador){
    Page<PgimInstrmntoXSupervDTO> pPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.listarInstrumentoParaAprobar(idSupervision, EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString(), EValorParametro.TIPO_ESTDO_INSTRMNTO_MODIFICADO.toString(), EValorParametro.TIPO_ESTDO_INSTRMNTO_NO_DISPONIBLE.toString(), paginador);
    return pPgimInstrmntoXSupervDTO; 
  }

  @Transactional(readOnly = false)
  @Override
  public List<PgimInstrmntoXSupervDTO> aprobarInstrumentoManual(Long idSupervision, AuditoriaDTO auditoriaDTO) {
    
    List <PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTO = this.supervInstrumentoRepository.listarAll(idSupervision);

    List <PgimInstrmntoXSupervDTO> lPgimInstrmntoXSupervDTOAux = new ArrayList<PgimInstrmntoXSupervDTO>();

    for (PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTO : lPgimInstrmntoXSupervDTO) {	
      PgimInstrmntoXSuperv pgimInstrmntoXSuperv = this.supervInstrumentoRepository.findById(pgimInstrmntoXSupervDTO.getIdInstrmntoXSuperv()).orElse(null);		        		
      
      String estadoNuevo = "";

      if(pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento().equals(EValorParametro.TIPO_ESTDO_INSTRMNTO_REGISTRADO.toString())){
        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString();
      }else if(pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento().equals(EValorParametro.TIPO_ESTDO_INSTRMNTO_MODIFICADO.toString())){
        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_APROBADO.toString();
      }else if(pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento().equals(EValorParametro.TIPO_ESTDO_INSTRMNTO_NO_DISPONIBLE.toString())){
        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_N_DSPNBLE_APROB.toString();
      }else if(pgimInstrmntoXSupervDTO.getDescCoEstadoInstrumento().equals(EValorParametro.TIPO_ESTDO_INSTRMNTO_REEMPLAZADO.toString())){
        estadoNuevo = EValorParametro.TIPO_ESTDO_INSTRMNTO_RMPLZDO_APROB.toString();
      }

      if(!estadoNuevo.equals("")){
        PgimInstrmntoXSupervDTO pgimInstrmntoXSupervDTOModificado = this.cambiarEstadoInstrumentoXSuperv(estadoNuevo, pgimInstrmntoXSuperv, auditoriaDTO);
        lPgimInstrmntoXSupervDTOAux.add(pgimInstrmntoXSupervDTOModificado); 
      }else{
        lPgimInstrmntoXSupervDTOAux.add(pgimInstrmntoXSupervDTO); 
      }
    }

    return lPgimInstrmntoXSupervDTOAux;
  }

}
