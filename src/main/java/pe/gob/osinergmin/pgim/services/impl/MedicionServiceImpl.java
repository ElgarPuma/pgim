package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.FiltroIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.ItemMedicionIndicadorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedicionDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimEspecialidad;
import pe.gob.osinergmin.pgim.models.entity.PgimIndicador;
import pe.gob.osinergmin.pgim.models.entity.PgimMedicion;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.IndicadorRepository;
import pe.gob.osinergmin.pgim.models.repository.MedicionRepository;
import pe.gob.osinergmin.pgim.services.MedicionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad medicion
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creación: 23/10/2023
 */
@Service
@Transactional(readOnly = true)
public class MedicionServiceImpl implements MedicionService{

  @Autowired
  private MedicionRepository medicionRepository;
  
  @Autowired
  private IndicadorRepository indicadorRepository;

  @Override
  public Page<PgimMedicionDTO> listarMediciones(PgimMedicionDTO pgimMedicionDTOFiltro, Pageable paginador) {
    return this.medicionRepository.listarMediciones(pgimMedicionDTOFiltro.getTextoBusqueda(), pgimMedicionDTOFiltro.getUsCreacion(), paginador);
  }

  @Transactional(readOnly = false)
  @Override
  public PgimMedicionDTO crearMedicion(PgimMedicionDTO pgimMedicionDTO,
      AuditoriaDTO auditoriaDTO) throws Exception {
    
    //Generar el nuevo código
		String ultimoCoCorrelativo = this.medicionRepository.obtenerUltimoCoCorrelativoMedicion();
    int nuevoCorrelativo = 1;
		if(ultimoCoCorrelativo == null) ultimoCoCorrelativo = "MED-0000";
    if(ultimoCoCorrelativo.split("-").length > 1 )
      nuevoCorrelativo = Integer.parseInt(ultimoCoCorrelativo.split("-")[1]) + 1;
		String nuevoCorrelativoAux = String.format("%04d", nuevoCorrelativo);
		String codigo =  "MED-" + nuevoCorrelativoAux;

    PgimMedicion pgimMedicion = new PgimMedicion();

    PgimIndicador pgimIndicador = new PgimIndicador();
    pgimIndicador.setIdIndicador(pgimMedicionDTO.getIdIndicador());
    pgimMedicion.setPgimIndicador(pgimIndicador);

    if(pgimMedicionDTO.getIdEspecialidad() != null){
      PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
      pgimEspecialidad.setIdEspecialidad(pgimMedicionDTO.getIdEspecialidad());
      pgimMedicion.setPgimEspecialidad(pgimEspecialidad);
    }

    if(pgimMedicionDTO.getIdDivisionSupervisora() != null){
      PgimValorParametro divisionSupervisora = new PgimValorParametro();
      divisionSupervisora.setIdValorParametro(pgimMedicionDTO.getIdDivisionSupervisora());
      pgimMedicion.setDivisionSupervisora(divisionSupervisora);
    }

    pgimMedicion.setEsPublicacion(ConstantesUtil.IND_INACTIVO);
    pgimMedicion.setCoMedicion(codigo);
    pgimMedicion.setNoMedicion(pgimMedicionDTO.getNoMedicion());
    pgimMedicion.setDeMedicion(pgimMedicionDTO.getDeMedicion());
    pgimMedicion.setFeInicial(pgimMedicionDTO.getFeInicial());
    pgimMedicion.setFeFinal(pgimMedicionDTO.getFeFinal());
    
    pgimMedicion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimMedicion.setFeCreacion(auditoriaDTO.getFecha());
		pgimMedicion.setUsCreacion(auditoriaDTO.getUsername());
		pgimMedicion.setIpCreacion(auditoriaDTO.getTerminal());
		
    PgimMedicion pgimMedicionCreado = this.medicionRepository.save(pgimMedicion);
		PgimMedicionDTO pgimMedicionDTOCreado = this.obtenerMedicionPorId(pgimMedicionCreado.getIdMedicion());

		return pgimMedicionDTOCreado;
  }

  @Transactional(readOnly = false)
  @Override
  public PgimMedicionDTO modificarMedicion(PgimMedicionDTO pgimMedicionDTO, PgimMedicion pgimMedicionDTOActual,
      AuditoriaDTO auditoriaDTO) throws Exception {
    
    PgimIndicador pgimIndicador = new PgimIndicador();
    pgimIndicador.setIdIndicador(pgimMedicionDTO.getIdIndicador());
    pgimMedicionDTOActual.setPgimIndicador(pgimIndicador);

    pgimMedicionDTOActual.setPgimEspecialidad(null);
    if(pgimMedicionDTO.getIdEspecialidad() != null){
      PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
      pgimEspecialidad.setIdEspecialidad(pgimMedicionDTO.getIdEspecialidad());
      pgimMedicionDTOActual.setPgimEspecialidad(pgimEspecialidad);
    }

    pgimMedicionDTOActual.setDivisionSupervisora(null);
    if(pgimMedicionDTO.getIdDivisionSupervisora() != null){
      PgimValorParametro divisionSupervisora = new PgimValorParametro();
      divisionSupervisora.setIdValorParametro(pgimMedicionDTO.getIdDivisionSupervisora());
      pgimMedicionDTOActual.setDivisionSupervisora(divisionSupervisora);
    }

    pgimMedicionDTOActual.setNoMedicion(pgimMedicionDTO.getNoMedicion());
    pgimMedicionDTOActual.setDeMedicion(pgimMedicionDTO.getDeMedicion());
    pgimMedicionDTOActual.setFeInicial(pgimMedicionDTO.getFeInicial());
    pgimMedicionDTOActual.setFeFinal(pgimMedicionDTO.getFeFinal());
        
		pgimMedicionDTOActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMedicionDTOActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMedicionDTOActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
    PgimMedicion pgimMedicionCreado = this.medicionRepository.save(pgimMedicionDTOActual);
		PgimMedicionDTO pgimMedicionDTOModificado = this.obtenerMedicionPorId(pgimMedicionCreado.getIdMedicion());

		return pgimMedicionDTOModificado;
  }

  @Transactional(readOnly = false)
  @Override
  public PgimMedicionDTO publicarMedicion(PgimMedicionDTO pgimMedicionDTO, PgimMedicion pgimMedicionActual, AuditoriaDTO auditoriaDTO)
      throws Exception {
	  
	  	if(pgimMedicionDTO.getEsPublicacion().equals(ConstantesUtil.FL_IND_SI)) {
	  		
	  		pgimMedicionActual.setPgimEspecialidad(null);
	  		if(pgimMedicionDTO.getIdEspecialidad() != null){
	  	      PgimEspecialidad pgimEspecialidad = new PgimEspecialidad();
	  	      pgimEspecialidad.setIdEspecialidad(pgimMedicionDTO.getIdEspecialidad());
	  	      pgimMedicionActual.setPgimEspecialidad(pgimEspecialidad);
	  	    }

	  		pgimMedicionActual.setDivisionSupervisora(null);
	  	    if(pgimMedicionDTO.getIdDivisionSupervisora() != null){
	  	      PgimValorParametro divisionSupervisora = new PgimValorParametro();
	  	      divisionSupervisora.setIdValorParametro(pgimMedicionDTO.getIdDivisionSupervisora());
	  	      pgimMedicionActual.setDivisionSupervisora(divisionSupervisora);
	  	    }

	  	    pgimMedicionActual.setFeInicial(pgimMedicionDTO.getFeInicial());
	  	    pgimMedicionActual.setFeFinal(pgimMedicionDTO.getFeFinal());		  
	  	}

	  	pgimMedicionActual.setEsPublicacion(pgimMedicionDTO.getEsPublicacion());
		pgimMedicionActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMedicionActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMedicionActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimMedicion pgimMedicionModificado = this.medicionRepository.save(pgimMedicionActual);
		PgimMedicionDTO pgimMedicionDTOModificado = this.obtenerMedicionPorId(pgimMedicionModificado.getIdMedicion());

		return pgimMedicionDTOModificado;

  }

  @Transactional(readOnly = false)
  @Override
  public void eliminarMedicion(PgimMedicion pgimMedicionActual, AuditoriaDTO auditoriaDTO)
      throws Exception {
    
    pgimMedicionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
		pgimMedicionActual.setFeActualizacion(auditoriaDTO.getFecha());
		pgimMedicionActual.setUsActualizacion(auditoriaDTO.getUsername());
		pgimMedicionActual.setIpActualizacion(auditoriaDTO.getTerminal());
		
		this.medicionRepository.save(pgimMedicionActual);

  }

  @Override
  public PgimMedicionDTO obtenerMedicionPorId(Long idMedicion) {
    return this.medicionRepository.obtenerMedicionPorId(idMedicion);
  }

  @Override
  public PgimMedicion getByIdMedicion(Long idMedicion) {
    return this.medicionRepository.findById(idMedicion).orElse(null);
  }
  
  @Override
  public List<ItemMedicionIndicadorDTO> listarItemsMedicionPorActorNegocio(FiltroIndicadorDTO filtro){
	  
	  List<ItemMedicionIndicadorDTO> lstItemMedicionIndicadorDTO = null;
	  
	  PgimIndicador pgimIndicador = this.indicadorRepository.findById(filtro.getIdIndicador()).orElse(null);
	  
	  if(pgimIndicador == null) {
		  throw new PgimException(TipoResultado.ERROR, "No se encontró el indicador con ID: " + filtro.getIdIndicador());
	  }
	  
	  Long idProceso = (pgimIndicador.getPgimProceso() != null) ? pgimIndicador.getPgimProceso().getIdProceso() : null;
	  Long idTipoActorNegocio = (pgimIndicador.getTipoActorNegocio() != null) ? pgimIndicador.getTipoActorNegocio().getIdValorParametro() : null;
	  Long idEspecialidad = (filtro.getIdEspecialidad() != null) ? filtro.getIdEspecialidad() : 0L;
	  Long idDivisionSupervisora = (filtro.getIdDivisionSupervisora() != null) ? filtro.getIdDivisionSupervisora() : 0L;
	  
	  if(idProceso == null) {
		  throw new PgimException(TipoResultado.ERROR, "El indicador no tiene un id de proceso para ejecutar la medición");
	  }
	  
	  if(idTipoActorNegocio == null) {
		  throw new PgimException(TipoResultado.ERROR, "El indicador no tiene un id de tipo de actor de negocio para ejecutar la medición");
	  }
	  
	  if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
		  
		  if(idTipoActorNegocio.equals(ConstantesUtil.PARAM_INDIC_TIPO_ACTORN_AF)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorActorNegocioAF(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro.getFeHasta(), 
					  idEspecialidad, 
					  idDivisionSupervisora
					  );
			  
		  }else if(idTipoActorNegocio.equals(ConstantesUtil.PARAM_INDIC_TIPO_ACTORN_UM)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorActorNegocioUM(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta(), 
					  idEspecialidad, 
					  idDivisionSupervisora
					  );
		  }
		  
	  }else if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)){
		  
		  if(idTipoActorNegocio.equals(ConstantesUtil.PARAM_INDIC_TIPO_ACTORN_AF)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorActorNegocioAF(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta(), 
					  idEspecialidad, 
					  idDivisionSupervisora
					  );
			  
		  }else if(idTipoActorNegocio.equals(ConstantesUtil.PARAM_INDIC_TIPO_ACTORN_UM)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorActorNegocioUM(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta(), 
					  idEspecialidad, 
					  idDivisionSupervisora
					  );
		  }
		  
	  }
	  
	  return lstItemMedicionIndicadorDTO;
  }
  
  @Override
  public List<ItemMedicionIndicadorDTO> listarItemsMedicionPorCaracteristica(FiltroIndicadorDTO filtro){
	  
	  List<ItemMedicionIndicadorDTO> lstItemMedicionIndicadorDTO = null;
	  
	  PgimIndicador pgimIndicador = this.indicadorRepository.findById(filtro.getIdIndicador()).orElse(null);
	  
	  if(pgimIndicador == null) {
		  throw new PgimException(TipoResultado.ERROR, "No se encontró el indicador con ID: " + filtro.getIdIndicador());
	  }
	  
	  Long idProceso = (pgimIndicador.getPgimProceso() != null) ? pgimIndicador.getPgimProceso().getIdProceso() : null;
	  Long idTipoCaracterFisc = (pgimIndicador.getTipoCaracterFisc() != null) ? pgimIndicador.getTipoCaracterFisc().getIdValorParametro() : null;
	  
	  if(idProceso == null) {
		  throw new PgimException(TipoResultado.ERROR, "El indicador no tiene un id de proceso para ejecutar la medición");
	  }
	  
	  if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
		  
		  if(idTipoCaracterFisc == null) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorCaracteristicaGeneral(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
			  
		  } else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_ESPECIALIDAD)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorCaracteristicaEspecialidad(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro.getFeHasta()
					  );
			  
		  }else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_DS)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorCaracteristicaDS(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
			  
		  }else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_ESPEC_Y_DS)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionFisPorCaracteristicaEspecYDS(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
		  }
		  
	  }else if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)){
		  
		  if(idTipoCaracterFisc == null) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorCaracteristicaGeneral(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
			  
		  } else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_ESPECIALIDAD)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorCaracteristicaEspecialidad(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
			  
		  }else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_DS)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorCaracteristicaDS(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
			  
		  }else if(idTipoCaracterFisc.equals(ConstantesUtil.PARAM_INDIC_TIPO_CARACTERIST_ESPEC_Y_DS)) {
			  lstItemMedicionIndicadorDTO = this.medicionRepository.listarItemsMedicionPasPorCaracteristicaEspecYDS(
					  filtro.getIdIndicador(),
					  filtro.getFeDesde(), 
					  filtro .getFeHasta()
					  );
		  }
		  
	  }
	  
	  return lstItemMedicionIndicadorDTO;
  }

  @Override
  public List<PgimMedicionDTO> obtenerMedicionesPorIdIndicador(Long indicador) {
    return this.medicionRepository.obtenerMedicionesPorIdIndicador(indicador);
  }

}
