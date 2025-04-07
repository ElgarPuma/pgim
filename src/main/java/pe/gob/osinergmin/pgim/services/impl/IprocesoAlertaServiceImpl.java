package pe.gob.osinergmin.pgim.services.impl;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAlerta;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimIprocesoAlerta;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoProcesoAlerta;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.IprocesoAlertaRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.TipoProcesoAlertaRepository;
import pe.gob.osinergmin.pgim.services.AlertaService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.IprocesoAlertaService;
import pe.gob.osinergmin.pgim.services.RevisionInformeService;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;
import pe.gob.osinergmin.pgim.utils.FechaFeriado;

/**
 * Servicio para la gestion de la interaccion con la base de datos y otros
 * servicios.
 * 
 * @descripcion: Logica de negocio de la entidad Alerta Instancia Proceso
 * 
 * @author: palominovega
 * @version: 1.0
 * @fecha_de_creacion: 20/12/2020
 */
@Service
@Transactional(readOnly = true)
public class IprocesoAlertaServiceImpl implements IprocesoAlertaService {

  @Autowired
  private AlertaService alertaService;

  @Autowired
  private InstanciaProcesService instanciaProcesService;

  @Autowired
  private RevisionInformeService revisionInformeService;

  @Autowired
  private IprocesoAlertaRepository iprocesoAlertaRepository;

  @Autowired
  private TipoProcesoAlertaRepository tipoProcesoAlertaRepository;

  @Autowired
  private InstanciaPasoRepository instanciaPasoRepository;

  @Autowired
  private InstanciaProcesRepository instanciaProcesRepository;

  @Autowired
  private EquipoInstanciaProcesoRepository equipoInstanciaProcesoRepository;

  @Autowired
  private PersonalOsiRepository personalOsiRepository;

  @Autowired
  private PersonalContratoRepository personalContratoRepository;

  @Autowired
  private SupervisionRepository supervisionRepository;

  @Autowired
  private ContratoRepository contratoRepository;

  @Autowired
  private SigedSoapService sigedSoapService;
  
  @Override
  public void determinarTipAlertInstanciaACrear(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
      AuditoriaDTO auditoriaDTO) throws Exception {
        
    Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
    Long idTipoProcesoAlerta = 0L;
    Long idTipoProcesoAlertaPadre = 0L;
    Date feInstanciaPaso = pgimInstanciaPaso.getFeInstanciaPaso();
    Long idInstanciaProceso = pgimInstanciaProces.getIdInstanciaProceso();

    String tipoAccion = "";

    // PGIM-6230
    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTFISICA__ESPERAR_DESCARGOS_OIPAS) ||
        idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTIFELECTRONICA__ESPERAR_DESCARGOS_OIPAS) ) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD;
      tipoAccion = "registrar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);

    } 
    if (idRelacionPaso
        .equals(ConstantesUtil.PARAM_RP_FIRMAR_AMPLIACION_CADUCIDAD__NOTIF_RESOL_AMPLIACION_PLAZO) ||
        idRelacionPaso
        .equals(ConstantesUtil.PARAM_RP_FIRMAR_AMPLIACION_CADUCIDAD_F2__NOTIF_RESOL_AMPLIACION_PLAZO_F2)
        ) {
      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD_AMPLIACION;
      idTipoProcesoAlertaPadre = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD;
      tipoAccion = "reemplazar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);

    } 
    if (idRelacionPaso
        .equals(ConstantesUtil.PARAM_RP_VERIF_NOTIF_FISICA_RES_SANCION__ESPERAR_RECURSO_IMPUGNATIVO) ||
        idRelacionPaso.equals(ConstantesUtil.PARAM_RP_VERIF_NOTIF_ELECTR_RES_SANCION__ESPERAR_RECURSO_IMPUGNATIVO)) {
      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD_AMPLIACION;
      idTipoProcesoAlertaPadre = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD;
      tipoAccion = "desactivar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);

    }
    // PGIM-7513
    else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD) ||
        idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF)) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR;
      tipoAccion = "registrar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);

    } 
    if (idRelacionPaso
        .equals(ConstantesUtil.PARAM_RP_APROBAR_SOLICITUD_AMPLIACIÓN_PLAZO_CONF_HECHOS_CONSTATADOS_INFRACCIONES)) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR_AMPLIACION;
      idTipoProcesoAlertaPadre = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR;
      tipoAccion = "reemplazar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
      idInstanciaProceso, feInstanciaPaso, auditoriaDTO);
    } 
    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_FISC_COMPL_INICIO_PAS__FISC_COMPLETADA_PAS) ||
        idRelacionPaso.equals(ConstantesUtil.PARAM_RP_COMPLETAR_FISC_PAS__FISC_COMPLETADA_PAS) ||
        idRelacionPaso.equals(ConstantesUtil.PARAM_RP_COMPLETAR_FISC_ARCH__FISC_ARCHIVADA)) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR_AMPLIACION;
      idTipoProcesoAlertaPadre = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR;
      tipoAccion = "desactivar";

      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);
    }
    
    // PGIM-7514
    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME)) {
          
      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME;
      tipoAccion = "registrar";

      List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTO = iprocesoAlertaRepository.existeIProcesoAlerta(
        idInstanciaProceso, idTipoProcesoAlerta, idTipoProcesoAlertaPadre);

      if(lPgimIprocesoAlertaDTO.size() == 0){
        this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
          idInstanciaProceso, feInstanciaPaso, auditoriaDTO);
      }

    }

    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_ELABORAR_PRESENTAR_INF_FISCALIZACION) || 
        idRelacionPaso.equals(ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD) ) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME;
      tipoAccion = "desactivar";
      
      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);
    }       

    // PGIM-7453
    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME;
      tipoAccion = "registrar";

      PgimSupervisionDTO pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
      
      if(pgimSupervisionDTO.getFlPropia().equals("0")) {
    	  this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
    		        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);  
      }
      
    }

    if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME) ) {

      idTipoProcesoAlerta = ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME;
      tipoAccion = "desactivar";
      
      this.accionCrearAlertaInstancia( tipoAccion, idTipoProcesoAlerta, idTipoProcesoAlertaPadre,
        idInstanciaProceso, feInstanciaPaso, auditoriaDTO);
    }       


  }

  public void accionCrearAlertaInstancia(String tipoAccion, Long idTipoProcesoAlerta, Long idTipoProcesoAlertaPadre, 
    Long idInstanciaProceso, Date feInstanciaPaso,  AuditoriaDTO auditoriaDTO ) throws Exception{
    
    PgimIprocesoAlerta pgimIprocesoAlerta = new PgimIprocesoAlerta();

    if (tipoAccion.equals("registrar")) {
      this.crerInstanciaProcesoAlerta(null, idTipoProcesoAlerta,
        feInstanciaPaso, idInstanciaProceso, auditoriaDTO);

    } else if (tipoAccion.equals("reemplazar")) {

      List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTO = new ArrayList<PgimInstanciaPasoDTO>();

      // PGIM-6230
      if (idTipoProcesoAlertaPadre.equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD)) {
        lPgimInstanciaPasoDTO = this.instanciaPasoRepository.obtenerInstanciaPasosXrelacion(
            idInstanciaProceso,
            ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTIFELECTRONICA__ESPERAR_DESCARGOS_OIPAS);

        if (lPgimInstanciaPasoDTO.size() == 0) {
          lPgimInstanciaPasoDTO = this.instanciaPasoRepository.obtenerInstanciaPasosXrelacion(
              idInstanciaProceso,
              ConstantesUtil.PARAM_RP_VERIF_VALIDEZ_NOTFISICA__ESPERAR_DESCARGOS_OIPAS);
        }

      }
      // PGIM-7513
      else if (idTipoProcesoAlertaPadre.equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR)) {
        lPgimInstanciaPasoDTO = this.instanciaPasoRepository.obtenerInstanciaPasosXrelacion(
            idInstanciaProceso,
            ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD);

        if (lPgimInstanciaPasoDTO.size() == 0) {
          lPgimInstanciaPasoDTO = this.instanciaPasoRepository.obtenerInstanciaPasosXrelacion(
              idInstanciaProceso,
              ConstantesUtil.PARAM_RELACION_ELABORAR_INFOR_ELAB_MCAF_OCAF);
        }
      }

      PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO = iprocesoAlertaRepository.obtenerIProcesoAlerta(
          idInstanciaProceso, idTipoProcesoAlerta, idTipoProcesoAlertaPadre);
      
      Long idIprocesoAlertaPadre = null;
      if(pgimIprocesoAlertaDTO != null){
        pgimIprocesoAlerta = iprocesoAlertaRepository.findById(pgimIprocesoAlertaDTO.getIdIprocesoAlerta()).orElse(null);
        idIprocesoAlertaPadre = pgimIprocesoAlerta.getIdIprocesoAlerta();
        this.desactivarInstanciaProcesoAlerta(pgimIprocesoAlerta, auditoriaDTO);
      }

      this.crerInstanciaProcesoAlerta(idIprocesoAlertaPadre, idTipoProcesoAlerta,
          lPgimInstanciaPasoDTO.get(0).getFeInstanciaPaso(), idInstanciaProceso, auditoriaDTO);

    } else if (tipoAccion.equals("desactivar")) {

     List<PgimIprocesoAlertaDTO> listapgimIprocesoAlertaDTO = iprocesoAlertaRepository.obtenerListaIProcesoAlerta(
              idInstanciaProceso, idTipoProcesoAlerta, idTipoProcesoAlertaPadre);
        
     if (listapgimIprocesoAlertaDTO != null & listapgimIprocesoAlertaDTO.size()>0) {
    	 
    	 PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO2 = null;
    	 
    	 if (listapgimIprocesoAlertaDTO.size() == 1) {
    		 pgimIprocesoAlertaDTO2 = listapgimIprocesoAlertaDTO.get(0);
    		 
		}else {
			
			
			pgimIprocesoAlertaDTO2 = listapgimIprocesoAlertaDTO.get(0);
			listapgimIprocesoAlertaDTO.remove(pgimIprocesoAlertaDTO2);
	    	
	    	 for(PgimIprocesoAlertaDTO objetoPgimIprocesoAlertaDTO : listapgimIprocesoAlertaDTO) {
	    		 PgimIprocesoAlerta pgimIprocesoAlerta2 = new PgimIprocesoAlerta();
	    		 pgimIprocesoAlerta2 = iprocesoAlertaRepository.findById(objetoPgimIprocesoAlertaDTO.getIdIprocesoAlerta()).orElse(null);
	    		 this.eliminarInstanciaProcesoAlerta(pgimIprocesoAlerta2, auditoriaDTO);
	    	 }
		}
		
    	 if(pgimIprocesoAlertaDTO2 != null){
    	        pgimIprocesoAlerta = iprocesoAlertaRepository.findById(pgimIprocesoAlertaDTO2.getIdIprocesoAlerta()).orElse(null);
    	        this.desactivarInstanciaProcesoAlerta(pgimIprocesoAlerta, auditoriaDTO);
    	 }
    	 
	 }

    }

  }

  /**
   * Permite desactivar la alerta instancia proceso
   * 
   * @param pgimIprocesoAlerta
   * @param auditoriaDTO
   */
  private void eliminarInstanciaProcesoAlerta(PgimIprocesoAlerta pgimIprocesoAlerta, AuditoriaDTO auditoriaDTO) {
	    pgimIprocesoAlerta.setEsRegistro(ConstantesUtil.IND_INACTIVO);
	    pgimIprocesoAlerta.setFlActivo(ConstantesUtil.IND_INACTIVO);
	    pgimIprocesoAlerta.setFeActualizacion(auditoriaDTO.getFecha());
	    pgimIprocesoAlerta.setUsActualizacion("PROGRAMADOR");
	    pgimIprocesoAlerta.setIpActualizacion(auditoriaDTO.getTerminal());
	    iprocesoAlertaRepository.save(pgimIprocesoAlerta);
	
}

/**
   * Permite registrar una alerta instancia proceso
   * 
   * @param idTipoProcesoAlerta
   * @param feInstanciaPaso
   * @param idInstanciaProceso
   * @param auditoriaDTO
   * @return
   * @throws Exception
   */
  private PgimIprocesoAlerta crerInstanciaProcesoAlerta(Long idIprocesoAlertaPadre, Long idTipoProcesoAlerta,
      Date feInstanciaPaso, Long idInstanciaProceso, AuditoriaDTO auditoriaDTO) throws Exception {

    PgimIprocesoAlerta pgimIprocesoAlerta = new PgimIprocesoAlerta();

    PgimTipoProcesoAlerta pgimTipoProcesoAlerta = tipoProcesoAlertaRepository.findById(idTipoProcesoAlerta)
        .orElse(null);
    LocalDate lFechaInstanciaPaso = CommonsUtil.convertirALocalDate(feInstanciaPaso);
    LocalDate lFechaInicioAlerta = null;
    LocalDate lFechaFinAlerta = null;

    if (pgimTipoProcesoAlerta.getTipoPeriodoAlerta().getCoClaveTexto()
        .equals(EValorParametro.TIPO_PERIODO_ALERTA_MENSUAL.toString())) {
      lFechaInicioAlerta = lFechaInstanciaPaso.plusMonths(pgimTipoProcesoAlerta.getCaPeriodoInicio());
      lFechaFinAlerta = lFechaInstanciaPaso.plusMonths(pgimTipoProcesoAlerta.getCaPeriodoFin());
    } else if(pgimTipoProcesoAlerta.getTipoPeriodoAlerta().getCoClaveTexto()
        .equals(EValorParametro.TIPO_PERIODO_ALERTA_DIARIA.toString())){ 
      
      if(idTipoProcesoAlerta.equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME)){
        
        PgimSupervisionDTO pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
        PgimContrato pgimContrato = this.contratoRepository.findById(pgimSupervisionDTO.getDescIdContrato()).orElse(null);

        LocalDate lFechaInstanciaPasoHabil = this.calcularFechasHabiles(lFechaInstanciaPaso.getDayOfMonth(), 
          lFechaInstanciaPaso.getMonthValue(), lFechaInstanciaPaso.getYear(), 1); 

        lFechaInicioAlerta = lFechaInstanciaPasoHabil.plusDays(pgimContrato.getNuDiasRevisionInforme() - pgimTipoProcesoAlerta.getCaPeriodoInicio());
        lFechaFinAlerta = lFechaInstanciaPasoHabil.plusDays(pgimContrato.getNuDiasRevisionInforme()-1);
      }

      // PGIM-7453
      if(idTipoProcesoAlerta.equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME)){
        
        PgimSupervisionDTO pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
        PgimContrato pgimContrato = this.contratoRepository.findById(pgimSupervisionDTO.getDescIdContrato()).orElse(null);
        PgimDocumentoDTO pgiDocumentoDTOActaFiscaliza = this.revisionInformeService.obtenerDocumentoMasReciente(
					pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_PROCESO_SUPERVISION,
					ConstantesUtil.PARAM_SUPERVISION_SUPERVISION_CAMPO, ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION);

        lFechaInstanciaPaso = CommonsUtil.convertirALocalDate(pgiDocumentoDTOActaFiscaliza.getFeOrigenDocumento());

        LocalDate lFechaInstanciaPasoHabil = this.calcularFechasHabiles(lFechaInstanciaPaso.getDayOfMonth(), 
          lFechaInstanciaPaso.getMonthValue(), lFechaInstanciaPaso.getYear(), 1); 

        lFechaInicioAlerta = lFechaInstanciaPasoHabil.plusDays(pgimContrato.getNuDiasEntregaInforme() - pgimTipoProcesoAlerta.getCaPeriodoInicio() );
        lFechaFinAlerta = lFechaInstanciaPasoHabil.plusDays(pgimContrato.getNuDiasEntregaInforme()-1);
      }
    }

    Long cantDias = ChronoUnit.DAYS.between(lFechaInicioAlerta, lFechaFinAlerta);
    Long cantDiasPlazo = cantDias / 3;
    Long cantDiasPlazoReal = 0L;

    if (cantDiasPlazo > 0)
      cantDiasPlazoReal = cantDiasPlazo - 1;

    LocalDate feIntervalo1Inicial = lFechaInicioAlerta;
    LocalDate feIntervalo1Final = lFechaInicioAlerta.plusDays(cantDiasPlazoReal);

    LocalDate feIntervalo2Inicial = feIntervalo1Final.plusDays(1);
    LocalDate feIntervalo2Final = feIntervalo2Inicial.plusDays(cantDiasPlazoReal);

    LocalDate feIntervalo3Inicial = feIntervalo2Final.plusDays(1);
    LocalDate feIntervalo3Final = lFechaFinAlerta;

    if(cantDias == 1){
      feIntervalo1Inicial = lFechaInicioAlerta;
      feIntervalo1Final = lFechaInicioAlerta.plusDays(cantDiasPlazoReal);
  
      feIntervalo2Inicial = feIntervalo1Final;
      feIntervalo2Final = feIntervalo2Inicial.plusDays(cantDiasPlazoReal);
  
      feIntervalo3Inicial = feIntervalo2Final.plusDays(1);
      feIntervalo3Final = lFechaFinAlerta;

    }

    if(cantDias == 0){
      feIntervalo1Inicial = lFechaInicioAlerta;
      feIntervalo1Final = lFechaInicioAlerta.plusDays(cantDiasPlazoReal);
  
      feIntervalo2Inicial = feIntervalo1Final;
      feIntervalo2Final = feIntervalo2Inicial.plusDays(cantDiasPlazoReal);
  
      feIntervalo3Inicial = feIntervalo2Final;
      feIntervalo3Final = lFechaFinAlerta;
    }

    pgimIprocesoAlerta.setFeIntervalo1Inicial(CommonsUtil.convertirADate(feIntervalo1Inicial));
    pgimIprocesoAlerta.setFeIntervalo1Final(CommonsUtil.convertirADateEndDay(feIntervalo1Final));

    pgimIprocesoAlerta.setFeIntervalo2Inicial(CommonsUtil.convertirADate(feIntervalo2Inicial));
    pgimIprocesoAlerta.setFeIntervalo2Final(CommonsUtil.convertirADateEndDay(feIntervalo2Final));

    pgimIprocesoAlerta.setFeIntervalo3Inicial(CommonsUtil.convertirADate(feIntervalo3Inicial));
    pgimIprocesoAlerta.setFeIntervalo3Final(CommonsUtil.convertirADateEndDay(feIntervalo3Final));

    if (idIprocesoAlertaPadre != null) {
      pgimIprocesoAlerta.setIprocesoAlertaPadre(new PgimIprocesoAlerta());
      pgimIprocesoAlerta.getIprocesoAlertaPadre().setIdIprocesoAlerta(idIprocesoAlertaPadre);
    }

    pgimIprocesoAlerta.setPgimInstanciaProces(new PgimInstanciaProces());
    pgimIprocesoAlerta.getPgimInstanciaProces().setIdInstanciaProceso(idInstanciaProceso);
    pgimIprocesoAlerta.setPgimTipoProcesoAlerta(new PgimTipoProcesoAlerta());
    pgimIprocesoAlerta.getPgimTipoProcesoAlerta()
        .setIdTipoProcesoAlerta(pgimTipoProcesoAlerta.getIdTipoProcesoAlerta());
    pgimIprocesoAlerta.setFlActivo(ConstantesUtil.IND_ACTIVO);
    pgimIprocesoAlerta.setEsRegistro(ConstantesUtil.IND_ACTIVO);
    pgimIprocesoAlerta.setFeCreacion(auditoriaDTO.getFecha());
    pgimIprocesoAlerta.setUsCreacion("PROGRAMADOR");
    pgimIprocesoAlerta.setIpCreacion(auditoriaDTO.getTerminal());

    iprocesoAlertaRepository.save(pgimIprocesoAlerta);

    return pgimIprocesoAlerta;
  }

  /**
   * Permite desactivar la alerta instancia proceso
   * 
   * @param pgimIprocesoAlerta
   * @param auditoriaDTO
   */
  private void desactivarInstanciaProcesoAlerta(PgimIprocesoAlerta pgimIprocesoAlerta, AuditoriaDTO auditoriaDTO) {
    pgimIprocesoAlerta.setFlActivo(ConstantesUtil.IND_INACTIVO);
    pgimIprocesoAlerta.setFeActualizacion(auditoriaDTO.getFecha());
    pgimIprocesoAlerta.setUsActualizacion("PROGRAMADOR");
    pgimIprocesoAlerta.setIpActualizacion(auditoriaDTO.getTerminal());
    iprocesoAlertaRepository.save(pgimIprocesoAlerta);
  }

  @Override
  @Transactional(readOnly = false)
  public List<PgimIprocesoAlertaDTO> notificarAlertasCumpliemientoSemanal(AuditoriaDTO auditoriaDTO) throws Exception {

    List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTO = this.iprocesoAlertaRepository.obtenerIProcesoAlertaActivosSemanal();
    List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTONotificado = new ArrayList<PgimIprocesoAlertaDTO>();

    for (PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO : lPgimIprocesoAlertaDTO) {

      List<String> lNoUsuariosPrevios = new ArrayList<String>();
      List<String> lNoUsuarios = new ArrayList<String>();
      Long idInstanciaPaso = null;
      String noUsuarioDestino = null;

      List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTOActuales = this.instanciaPasoRepository.obtenerInstanciaPasosActualesPorIdInstanciaProceso(pgimIprocesoAlertaDTO.getIdInstanciaProceso());
      
      for (PgimInstanciaPasoDTO pgimInstanciaPasoDTO : lPgimInstanciaPasoDTOActuales) {
        
        PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository
            .findById(pgimInstanciaPasoDTO.getIdInstanciaPaso()).orElse(null);
  
        // obtengo el usuario quien tiene asignado el objeto de trabajo
        final PgimEqpInstanciaPro pgimEqpInstanciaProDestino = this.equipoInstanciaProcesoRepository
            .findById(pgimInstanciaPasoActual.getPersonaEqpDestino().getIdEquipoInstanciaPro()).orElse(null);
  
        if (pgimEqpInstanciaProDestino.getPgimPersonalOsi() != null) {
          noUsuarioDestino = this.personalOsiRepository
              .findById(pgimEqpInstanciaProDestino.getPgimPersonalOsi().getIdPersonalOsi())
              .orElse(null)
              .getNoUsuario();
  
        } else {
          if (pgimEqpInstanciaProDestino.getPgimPersonalContrato() != null) {
            noUsuarioDestino = this.personalContratoRepository
                .findById(pgimEqpInstanciaProDestino.getPgimPersonalContrato().getIdPersonalContrato())
                .orElse(null)
                .getNoUsuario();
  
          }
        }
        idInstanciaPaso = pgimInstanciaPasoDTO.getIdInstanciaPaso();
        lNoUsuarios.add(noUsuarioDestino);
      }

      // obteniendo datos extras para las alertas
      Map<String, Object> lDatosAlerta = this.obtenerDatosExtraParaAlerta(pgimIprocesoAlertaDTO);
      pgimIprocesoAlertaDTO.setDescDeAlerta(lDatosAlerta.get("deAlerta").toString());
      pgimIprocesoAlertaDTO.setDescDetAlerta(lDatosAlerta.get("descDetalleAlerta").toString());
      List<String> lNoUsuariosNuevos = (List<String>) lDatosAlerta.get("lUsuario");
      lNoUsuarios.addAll(lNoUsuariosNuevos);
      
      List<String> lNoUsuariosAux = new ArrayList<String>();
      for (String noUsuario: lNoUsuarios) {
        if (!lNoUsuariosAux.contains(noUsuario) && (noUsuario != null)) {
          lNoUsuariosAux.add(noUsuario);
        }
      }

      // registro de alertas
      PgimAlerta pgimAlerta = this.alertaService.enviarAlerta(pgimIprocesoAlertaDTO, lNoUsuariosAux,
        idInstanciaPaso, lDatosAlerta.get("codTipoAlerta").toString(), auditoriaDTO);

      // condición para solo enviar una alerta en tiempo real al usuario destino
      if(!lNoUsuariosPrevios.contains(noUsuarioDestino)){ 
        lNoUsuariosPrevios.add(noUsuarioDestino);
        PgimIprocesoAlertaDTO pgimIprocesoAlertaDTONotificado = new  PgimIprocesoAlertaDTO();
        pgimIprocesoAlertaDTONotificado = pgimIprocesoAlertaDTO;
        pgimIprocesoAlertaDTONotificado.setDescNoAlerta(pgimAlerta.getNoAlerta());
        pgimIprocesoAlertaDTONotificado.setNoUsuarioDestino(noUsuarioDestino);;
        lPgimIprocesoAlertaDTONotificado.add(pgimIprocesoAlertaDTO);
      }
            
    }

    return lPgimIprocesoAlertaDTONotificado;
  }

  public Map<String, Object> obtenerDatosExtraParaAlerta(PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO) {
    Map<String, Object> lDatosAlerta = new HashMap<>();

    String codTipoAlerta = "";
    String deAlerta = "";
    String descDetalleAlerta = "";
    List<String> lNoUsuariosNuevos = new ArrayList<String>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd'/'MM'/'yyyy");
    
    PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
        .findById(pgimIprocesoAlertaDTO.getIdInstanciaProceso()).orElse(null);
    
    if (pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD_AMPLIACION) ||
        pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD)) {
      codTipoAlerta = EValorParametro.ALERT_CMPLMIENTO_PLZO_CDCDAD.toString();
      deAlerta = String.format(ConstantesUtil.PARAM_ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD, sdf.format(pgimIprocesoAlertaDTO.getFeIntervalo3Final()));

    } else if (pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR) ||
        pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR_AMPLIACION)) {
      
      LocalDate lFeIntervalo1Inicial = CommonsUtil.convertirALocalDate(pgimIprocesoAlertaDTO.getFeIntervalo1Inicial());
      LocalDate lFechaInicioAlertaLegal = lFeIntervalo1Inicial.plusMonths(1L);
      
      codTipoAlerta = EValorParametro.ALERT_CMPLMIENTO_PLZO_INCIAR_PAS_ARCHVAR.toString();
      deAlerta = String.format(ConstantesUtil.PARAM_ALERTA_CUMPLIMIENTO_PLAZO_INICIAR_PAS_ARCHIVAR, sdf.format(pgimIprocesoAlertaDTO.getFeIntervalo3Final()));

      if (lFeIntervalo1Inicial.isAfter(lFechaInicioAlertaLegal)) {
        List<String> lNoUsuarios =  this.obtenerNuevosUsuarios(pgimInstanciaProces.getIdInstanciaProceso(), ConstantesUtil.PROCESO_ROL_ESP_LEGAL);
        lNoUsuariosNuevos.addAll(lNoUsuarios);
      }
      
    } else if (pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_REVISION_INFORME)) {
      codTipoAlerta = EValorParametro.ALERT_CMPLMIENTO_PLZO_RVSAR_INFRME.toString();
      deAlerta = String.format(ConstantesUtil.PARAM_ALERTA_CUMPLIMIENTO_PLAZO_CADUCIDAD, sdf.format(pgimIprocesoAlertaDTO.getFeIntervalo3Final()));

    } else if (pgimIprocesoAlertaDTO.getIdTipoProcesoAlerta().equals(ConstantesUtil.ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFORME)) {
      codTipoAlerta = EValorParametro.ALERT_CMPLMIENTO_PLZO_PRESENTAR_INFRME.toString();
      deAlerta = String.format(ConstantesUtil.PARAM_ALERTA_CUMPLIMIENTO_PLAZO_PRESENTAR_INFRME, sdf.format(pgimIprocesoAlertaDTO.getFeIntervalo3Final()));

    }

    descDetalleAlerta = String.format(ConstantesUtil.PARAM_DETALLE_ALERTA_CUMPLEMIENTO,
        pgimInstanciaProces.getPgimProceso().getNoProceso().toLowerCase());

    lDatosAlerta.put("codTipoAlerta", codTipoAlerta);
    lDatosAlerta.put("deAlerta", deAlerta);
    lDatosAlerta.put("descDetalleAlerta", descDetalleAlerta);
    lDatosAlerta.put("lUsuario", lNoUsuariosNuevos);

    return lDatosAlerta;
  }
  
  public List<String> obtenerNuevosUsuarios(Long idInstanciaProceso, Long idRolProceso) {

    // Obtener los datos de la persona según un rol
    List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = this.instanciaProcesService
        .obtenerParticipantesInstanciaProXRol(idInstanciaProceso, idRolProceso);

    List<String> lNoUsuariosNuevos = new ArrayList<String>();
    for (PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO : lPgimEqpInstanciaProDTO) {
      if (!lNoUsuariosNuevos.contains(pgimEqpInstanciaProDTO.getDescNoUsuario())) {
        lNoUsuariosNuevos.add(pgimEqpInstanciaProDTO.getDescNoUsuario());
      }
    }

    return lNoUsuariosNuevos;

  }

  @Override
  public Page<PgimIprocesoAlertaDTO> listarAlertaCumple(
      AuditoriaDTO auditoriaDTO, Pageable paginador) {
    String userName = auditoriaDTO.getUsername();

    Page<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTO = this.iprocesoAlertaRepository
        .listarAlertasCumpleByNoUsuarioDestino(userName, paginador);

    return lPgimIprocesoAlertaDTO;
  }

  @Override
  public List<PgimIprocesoAlertaDTO> listarIProcesoAlertaPorIdInstancia(Long idInstanciaProceso) {

    List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTO = this.iprocesoAlertaRepository
        .listarIProcesoAlertaPorIdInstancia(idInstanciaProceso);

    return lPgimIprocesoAlertaDTO;
  }

  @Override
  @Transactional(readOnly = false)
  public List<PgimIprocesoAlertaDTO> notificarAlertasCumpliemientoDiario(AuditoriaDTO auditoriaDTO) throws Exception {

    List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTO = this.iprocesoAlertaRepository.obtenerIProcesoAlertaActivosDiario();
    List<PgimIprocesoAlertaDTO> lPgimIprocesoAlertaDTONotificado = new ArrayList<PgimIprocesoAlertaDTO>();
    
    for (PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO : lPgimIprocesoAlertaDTO) {

      List<String> lNoUsuariosPrevios = new ArrayList<String>();
      List<String> lNoUsuarios = new ArrayList<String>();
      Long idInstanciaPaso = null; 
      String noUsuarioDestino = null;

      List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTOActuales = this.instanciaPasoRepository.obtenerInstanciaPasosActualesPorIdInstanciaProceso(pgimIprocesoAlertaDTO.getIdInstanciaProceso());
      
      for (PgimInstanciaPasoDTO pgimInstanciaPasoDTO : lPgimInstanciaPasoDTOActuales) {
        
        PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository
            .findById(pgimInstanciaPasoDTO.getIdInstanciaPaso()).orElse(null);
  
        // obtengo el usuario quien tiene asignado el objeto de trabajo
        final PgimEqpInstanciaPro pgimEqpInstanciaProDestino = this.equipoInstanciaProcesoRepository
            .findById(pgimInstanciaPasoActual.getPersonaEqpDestino().getIdEquipoInstanciaPro()).orElse(null);
  
        if (pgimEqpInstanciaProDestino.getPgimPersonalOsi() != null) {
          noUsuarioDestino = this.personalOsiRepository
              .findById(pgimEqpInstanciaProDestino.getPgimPersonalOsi().getIdPersonalOsi())
              .orElse(null)
              .getNoUsuario();
  
        } else {
          if (pgimEqpInstanciaProDestino.getPgimPersonalContrato() != null) {
            noUsuarioDestino = this.personalContratoRepository
                .findById(pgimEqpInstanciaProDestino.getPgimPersonalContrato().getIdPersonalContrato())
                .orElse(null)
                .getNoUsuario();
  
          }
        }
        idInstanciaPaso = pgimInstanciaPasoDTO.getIdInstanciaPaso();
        lNoUsuarios.add(noUsuarioDestino);
      }

      // obteniendo datos extras para las alertas
      Map<String, Object> lDatosAlerta = this.obtenerDatosExtraParaAlerta(pgimIprocesoAlertaDTO);
      pgimIprocesoAlertaDTO.setDescDeAlerta(lDatosAlerta.get("deAlerta").toString());
      pgimIprocesoAlertaDTO.setDescDetAlerta(lDatosAlerta.get("descDetalleAlerta").toString());
      List<String> lNoUsuariosNuevos = (List<String>) lDatosAlerta.get("lUsuario");
      lNoUsuarios.addAll(lNoUsuariosNuevos);
      
      List<String> lNoUsuariosAux = new ArrayList<String>();
      for (String noUsuario: lNoUsuarios) {
        if (!lNoUsuariosAux.contains(noUsuario) && (noUsuario != null)) {
          lNoUsuariosAux.add(noUsuario);
        }
      }

      // registro de alertas
      PgimAlerta pgimAlerta = this.alertaService.enviarAlerta(pgimIprocesoAlertaDTO, lNoUsuariosAux,
          idInstanciaPaso, lDatosAlerta.get("codTipoAlerta").toString(), auditoriaDTO);

      // condición para solo enviar una alerta en tiempo real al usuario destino
      if(!lNoUsuariosPrevios.contains(noUsuarioDestino)){ 
        lNoUsuariosPrevios.add(noUsuarioDestino);
        PgimIprocesoAlertaDTO pgimIprocesoAlertaDTONotificado = new  PgimIprocesoAlertaDTO();
        pgimIprocesoAlertaDTONotificado = pgimIprocesoAlertaDTO;
        pgimIprocesoAlertaDTONotificado.setDescNoAlerta(pgimAlerta.getNoAlerta());
        pgimIprocesoAlertaDTONotificado.setNoUsuarioDestino(noUsuarioDestino);;
        lPgimIprocesoAlertaDTONotificado.add(pgimIprocesoAlertaDTO);
      }
            
    }

    return lPgimIprocesoAlertaDTONotificado;
  }


  /**
	 * Permite el calculo de la fecha ingresada(dia, mes, año) + cantidad de dias,
	 * no considera sabados, domingos y feriados.
	 * 
	 * @param dia
	 * @param mes
	 * @param anio
	 * @param cantDias
	 * @return
	 * @throws Exception
	 */
	public LocalDate calcularFechasHabiles(int dia, int mes, int anio, int cantDias) throws Exception {
		LocalDate fechaActual = LocalDate.of(anio, mes, dia);
		for (int i = 0; i < cantDias; i++) {
			fechaActual = fechaActual.plusDays(1);
			fechaActual = validarSabadosDomingosFeriados(fechaActual);
		}
		return fechaActual;
	}

	/**
	 * Valida si la fecha ingresada es sabado, domingo o feriado.
	 * 
	 * @param fecha
	 * @return
	 * @throws Exception
	 */
	private LocalDate validarSabadosDomingosFeriados(LocalDate fecha) throws Exception {

		LocalDate fechaModificada = null;
		if (fecha.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			LocalDate fechaPlusDay = fecha.plusDays(1);
			fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
		} else if (fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			LocalDate fechaPlusDay = fecha.plusDays(1);
			fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
		} else {
			String cadenaFecha = completarConCeros(fecha.getDayOfMonth()) + "/"
					+ completarConCeros(fecha.getMonthValue()) + "/" + fecha.getYear();
					
			final FechaFeriado esFeriado = sigedSoapService.esFeriado(cadenaFecha);
			if (esFeriado.getMensajeFeriado().equals("Es Feriado")) {
				LocalDate fechaPlusDay = fecha.plusDays(1);
				fechaModificada = validarSabadosDomingosFeriados(fechaPlusDay);
			} else {
				fechaModificada = fecha;
			}
		}

		return fechaModificada;
	}

	/**
	 * Permite completar con cero si el numero es de 1 al 9
	 * 
	 * @param diaMes
	 * @return
	 */
	private String completarConCeros(int diaMes) {
		String cadenaDiaMes = "";
		switch (diaMes) {
			case 1:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 2:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 3:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 4:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 5:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 6:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 7:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 8:
				cadenaDiaMes = "0" + diaMes;
				break;
			case 9:
				cadenaDiaMes = "0" + diaMes;
				break;
			default:
				cadenaDiaMes = String.valueOf(diaMes);
				break;
		}

		return cadenaDiaMes;
	}

}
