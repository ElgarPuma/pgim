package pe.gob.osinergmin.pgim.services.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gob.osinergmin.siged.remote.rest.ro.base.BaseOutRO;
import gob.osinergmin.siged.remote.rest.ro.in.ReenviarSubFlujoInRO;
import gob.osinergmin.siged.remote.rest.ro.in.UsuarioInRO;
import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.DetalleExcepcionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCfgEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConfiguraRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimHechoConstatadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRankingRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionAccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionFirmaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRolProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimDocEtiquetaNotif;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimEqpInstanciaPro;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPasoDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimPersonalOsi;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.CfgEtiquetaNotifRepository;
import pe.gob.osinergmin.pgim.models.repository.ConfiguraRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.DestinatarioDocRepository;
import pe.gob.osinergmin.pgim.models.repository.DocEtiquetaNotifRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.EquipoInstanciaProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.HechoConstatadoRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoDocRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PasRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.RankingRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionAccionRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionFirmaRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionSubcatRepository;
import pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubTipoSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AlertaService;
import pe.gob.osinergmin.pgim.services.ConfiguraRiesgoService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.IprocesoAlertaService;
import pe.gob.osinergmin.pgim.services.LiquidacionService;
import pe.gob.osinergmin.pgim.services.PasService;
import pe.gob.osinergmin.pgim.services.PerfilUserService;
import pe.gob.osinergmin.pgim.services.PersonaService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.RankingRiesgoService;
import pe.gob.osinergmin.pgim.services.SupervisionService;
import pe.gob.osinergmin.pgim.siged.Archivo;
import pe.gob.osinergmin.pgim.siged.DevolverExpedienteInRO;
import pe.gob.osinergmin.pgim.siged.DevolverExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.siged.ExpedienteAprobadoIn;
import pe.gob.osinergmin.pgim.siged.ExpedienteOutRO;
import pe.gob.osinergmin.pgim.siged.ExpedienteReenvio;
import pe.gob.osinergmin.pgim.siged.FirmaDigitalSiged;
import pe.gob.osinergmin.pgim.siged.ReenviarSubflujo;
import pe.gob.osinergmin.pgim.siged.Usuario;
import pe.gob.osinergmin.pgim.siged.UsuarioSiged;
import pe.gob.osinergmin.pgim.siged.Usuarios;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstPasoProcesoPas;
import pe.gob.osinergmin.pgim.utils.ConstRelacionPasoLiquidacion;
import pe.gob.osinergmin.pgim.utils.ConstSubCategoriaDocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;
import pe.gob.osinergmin.soa.service.expediente.documentos.listartrazdoc.v1.ListarTrazabilidadDocumentosResponse;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio del flujo de trabajo
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 * @fecha_de_ultima_actualización: 05/08/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class FlujoTrabajoServiceImpl implements FlujoTrabajoService {
	
	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private SupervisionService supervisionService;

	@Autowired
	private AlertaService alertaService;

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PrgrmSupervisionService prgrmSupervisionService;

	@Autowired
	private LiquidacionService liquidacionService;

	@Autowired
	private PasService pasService;

	@Autowired
	private RankingRiesgoService rankingRiesgosService;

	@Autowired
	private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
	private InstanciaPasoRepository instanciaPasoRepository;

	@Autowired
	private InstanciaPasoAuxRepository instanciaPasoAuxRepository;

	@Autowired
	private RelacionPasoRepository relacionPasoRepository;

	@Autowired
	private PersonalOsiAuxRepository personalOsiAuxRepository;

	@Autowired
	private PersonalOsiRepository personalOsiRepository;

	@Autowired
	private PersonalContratoRepository personalContratoRepository;

	@Autowired
	private EquipoInstanciaProcesoRepository equipoInstanciaProcesoRepository;

	@Autowired
	private RolProcesoRepository rolProcesoRepository;

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private RelacionSubcatRepository relacionSubcatRepository;

	@Autowired
	private FaseProcesoRepository faseProcesoRepository;

	@Autowired
	private ValorParametroRepository valorParametroRepository;

	@Autowired
	private RelacionFirmaRepository relacionFirmaRepository;

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private ConfiguraRiesgoService configuraRiesgoService;

	@Autowired
	private InstanciaPasoDocRepository instanciaPasoDocRepository;

	@Autowired
	private RelacionAccionRepository relacionAccionRepository;
	
	@Autowired
	private IprocesoAlertaService iprocesoAlertaService;

	@Autowired
	private HechoConstatadoRepository hechoConstatadoRepository;

	@Autowired
    private PrgrmSupervisionAuxRepository prgrmSupervisionAuxRepository;

    @Autowired
    private RankingRiesgoRepository rankingRiesgoRepository;

    @Autowired
    private ConfiguraRiesgoRepository configuraRiesgoRepository;

    @Autowired
    private SupervisionRepository supervisionRepository;

    @Autowired
    private PasRepository pasRepository;
    
    @Autowired
    private InstanciaProcesService instanciaProcesService;

    @Autowired
    private LiquidacionAuxRepository liquidacionAuxRepository;

	@Autowired
	private InfraccionAuxRepository infraccionAuxRepository;

	@Autowired
	private InfraccionContraRepository infraccionContraRepository;
	
	@Autowired
    private AgenteSupervisadoRepository agenteSupervisadoRepository;

	@Autowired
	private DocEtiquetaNotifRepository docEtiquetaNotifRepository;

	@Autowired
	private CfgEtiquetaNotifRepository cfgEtiquetaNotifRepository;

	@Autowired
	private SubTipoSupervisionRepository subTipoSupervisionRepository;
	
	@Autowired
	private HechoConstatadoService hechoConstatadoService;
	
	
	@Autowired
	private DestinatarioDocRepository destinatarioDocRepository;

	@Autowired
    private FlujoTrabajoService flujoTrabajoService;
	
	@Autowired
    private PerfilUserService perfilUserService;
	
	@Override
	public PgimRelacionPasoDTO obtenerRelacionPasoInicial(final Long idProceso) {
		return this.relacionPasoRepository.obtenerRelacionPasoInicial(idProceso);
	}
	
	@Override
	public List<PgimRelacionAccionDTO> obtenerRelacionAccion(final Long idRelacionPaso) {
		return this.relacionAccionRepository.obtenerRelacionAccion(idRelacionPaso);
	}

	@Override
	public PgimRelacionPasoDTO obtenerRelacionPaso(final Long idInstanciaPaso) {

		final PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);

		return this.relacionPasoRepository
				.obtenerRelacionPasoPorId(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso());
	}

	@Override
	public List<PgimRelacionPasoDTO> obtenerPasosSiguientes(final Long idPasoProcesoActual,
			final PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual) {

		List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes = this.relacionPasoRepository
				.obtenerListaPasosSiguientes(idPasoProcesoActual);

		PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository
				.findById(pgimInstanciaPasoDTOActual.getIdInstanciaPaso()).orElse(null);
		Long idProceso = pgimInstanciaPasoActual.getPgimInstanciaProces().getPgimProceso().getIdProceso();

		Long idRelacionPasoActual = pgimInstanciaPasoActual.getPgimRelacionPaso().getIdRelacionPaso();
		PgimRelacionPaso pgimRelacionPasoActual = this.relacionPasoRepository.findById(idRelacionPasoActual)
				.orElse(null);

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
			lPgimRelacionPasoDTOSiguientes = this.supervisionService.filtrarPasosSiguientes(
					lPgimRelacionPasoDTOSiguientes, pgimInstanciaPasoActual, pgimRelacionPasoActual);
		} else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
			lPgimRelacionPasoDTOSiguientes = this.liquidacionService.filtrarPasosSiguientes(
					lPgimRelacionPasoDTOSiguientes, pgimInstanciaPasoActual, pgimRelacionPasoActual);
		} else if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
			lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesYRetrocesoSubFlujo(lPgimRelacionPasoDTOSiguientes,
					pgimInstanciaPasoActual, pgimRelacionPasoActual);
		}

		return lPgimRelacionPasoDTOSiguientes;
	}

	/**
	 * Filtra los pasos siguientes y de retroceso del PAS
	 * @param lPgimRelacionPasoDTOSiguientes
	 * @param pgimInstanciaPasoActual
	 * @return
	 */
	private List<PgimRelacionPasoDTO> filtrarPasosSiguientesPAS(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,PgimInstanciaPaso pgimInstanciaPasoActual){

		List<PgimDocumentoDTO> lPgimDocumentoRCalf = this.documentoRepository
			.obtenerDocPorInstanciaYSubCategoria(
				pgimInstanciaPasoActual.getPgimInstanciaProces().getIdInstanciaProceso(),
				ConstSubCategoriaDocumento.RESOLUCION_DE_CALIFICACION);
		Long idRelacionPaso;
		if(lPgimDocumentoRCalf.size()>0){
			idRelacionPaso = ConstantesUtil.PARAM_RP_FIRMAR_RES_MEMO_TASTEM_CONTINUAR_TRAMITE_PAS;
		}else{
			idRelacionPaso = ConstantesUtil.PARAM_RP_FIRMAR_RES_MEMO_TASTEM_NOTIFICAR_RES_DE_CALIFICACION;
		}

		lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                                .filter(pgimRelacionPasoDTO -> {
                                                        return (!pgimRelacionPasoDTO.getIdRelacionPaso().equals(idRelacionPaso));
                                                }).collect(Collectors.toList());

        return lPgimRelacionPasoDTOSiguientes;

	}

    /**
     * Filtra los pasos siguientes y de retroceso, según el paso que se encuentre, marco de un subflujo configurado. 
     * Actualmente solo sucede para PAS
     * 
     * @param lPgimRelacionPasoDTOSiguientes
     * @param pgimInstanciaPasoActual
     * @param pgimRelacionPasoActual
     * @return
     */
    private List<PgimRelacionPasoDTO> filtrarPasosSiguientesYRetrocesoSubFlujo(List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
    		PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual) {
    	
        PgimInstanciaPaso pgimInstanciaPasoIter = pgimInstanciaPasoActual;
        Long idPasoProcesoOrigenInicial = null;
        List<Long> lIdPasoProceso = new ArrayList<Long>();
        String coSubproceso = "";
    	String tipoPasoSubproceso = "";
        if(pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getCoTipoPasoSubproceso() != null) {
        	tipoPasoSubproceso = pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getCoTipoPasoSubproceso();
        }
        
        if(pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getCoSubproceso() != null) {
        	coSubproceso = pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getCoSubproceso();
        }

        if(coSubproceso.equals("")) {
        	
        	return lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesPAS(lPgimRelacionPasoDTOSiguientes,
					pgimInstanciaPasoActual);
        }
        else { /* Cuando la instancia paso pertenece a un subflujo. */ 
        	
        	/* Identifica si el paso del subflujo incial, debe listarse solamente un sola transición de retroceso, al paso origen de donde se inicion el sublujo. */
        	if(tipoPasoSubproceso.equals("I")) { 
        		
        		/* Excluimos los pasos de retroceso. */ 
                for (PgimRelacionPasoDTO pgimRelacionPasoDTO: lPgimRelacionPasoDTOSiguientes) {
                	
                	PgimRelacionPaso pgimRelacionPasoAContinuar = this.relacionPasoRepository
                            .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);
                	
                	if(!pgimRelacionPasoAContinuar.getTipoRelacion().getCoClaveTexto().equals(EValorParametro.REPAS_REGRSAR_FLJO.toString())) {
                		lIdPasoProceso.add(pgimRelacionPasoAContinuar.getPasoProcesoDestino().getIdPasoProceso());
                	}

        		}
                
        	}
        	/* Identifica si el paso del subflujo final, debe listarse solamente un sola transición de continuación, al paso origen de donde se inicion el sublujo. */
        	else if(tipoPasoSubproceso.equals("F")) {
        	
        		/* Excluimos los pasos de continuación. */ 
                for (PgimRelacionPasoDTO pgimRelacionPasoDTO: lPgimRelacionPasoDTOSiguientes) {
                	
                	PgimRelacionPaso pgimRelacionPasoAContinuar = this.relacionPasoRepository
                            .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);
                	
                	if(!pgimRelacionPasoAContinuar.getTipoRelacion().getCoClaveTexto().equals(EValorParametro.REPAS_CONTINUAR_FLJO.toString())) {
                		lIdPasoProceso.add(pgimRelacionPasoAContinuar.getPasoProcesoDestino().getIdPasoProceso());
                	}
        		}
                
        	}else {
        		return lPgimRelacionPasoDTOSiguientes;
        	}
        	
        }        
        
        do {
            pgimInstanciaPasoIter = pgimInstanciaPasoIter.getInstanciaPasoPadre();

            String coSubprocesoPadre = "";  
            if(pgimInstanciaPasoIter.getPgimRelacionPaso().getPasoProcesoDestino().getCoSubproceso() != null) {
           	 coSubprocesoPadre = pgimInstanciaPasoIter.getPgimRelacionPaso().getPasoProcesoDestino().getCoSubproceso();
            }
            
            if (!coSubprocesoPadre.equals(coSubproceso)) {
            	
                idPasoProcesoOrigenInicial = pgimInstanciaPasoIter.getPgimRelacionPaso().getPasoProcesoDestino()
                        .getIdPasoProceso();
            }
        } while (idPasoProcesoOrigenInicial == null);
        
        lIdPasoProceso.add(idPasoProcesoOrigenInicial);

        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream().filter(pgimRelacionPasoDTO -> {

            PgimRelacionPaso pgimRelacionPasoAverificar = this.relacionPasoRepository
                    .findById(pgimRelacionPasoDTO.getIdRelacionPaso()).orElse(null);

            return lIdPasoProceso.contains(pgimRelacionPasoAverificar.getPasoProcesoDestino().getIdPasoProceso());
        }).collect(Collectors.toList());

        return lPgimRelacionPasoDTOSiguientes;
    }
    
    
	@Override
	public List<PgimRelacionSubcatDTO> obtenerSubcategoriaDocParaFiscPropia(final Long idInstanciaProces, final List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes) {

		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = null;

		for (PgimRelacionPasoDTO pgimRelacionPasoDTO: lPgimRelacionPasoDTOSiguientes) {

			/* condicional solo para la tarea actual "Realizar mediciones y verificar hechos" hacia "Presentar acta de fiscalización" */
			if(pgimRelacionPasoDTO.getIdRelacionPaso().equals(ConstantesUtil.PARAM_RELACION_REALIZARMEDICIONES_PRESENTARACTAFISC)){

				PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProces);

				/* Obtendré el/los documento(s) que ha(n) sido cargado(s) y no entraria a la condicional; en caso contrario no haya documentos, procederia a la dicha condición */
				lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarSubCategoriasParaFiscPropiaNoCargadas(pgimRelacionPasoDTO.getIdRelacionPaso(), pgimSupervisionDTO.getDescIdEspecialidad(), idInstanciaProces);

				if(lPgimRelacionSubcatDTO.size() == 0){

					/* Obtendré el/los documento(s) que no ha(n) sido cargado(s) */
					lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarSubCategoriasAlmenosUnoNoCargadas(pgimRelacionPasoDTO.getIdRelacionPaso(), idInstanciaProces);

					/* Obtendré el/los documento(s) que no ha(n) sido cargado(s) por especialidad de la fiscalización */
					lPgimRelacionSubcatDTO = this.depurarPorEspecialidad(lPgimRelacionSubcatDTO, pgimSupervisionDTO.getDescIdEspecialidad());

					for(PgimRelacionSubcatDTO pgimRelacionSubcatDTO: lPgimRelacionSubcatDTO){
	
						/* Reuniria los datos de los documentos de la subcategoria para la información(info) que se va mostrar en el frontend */
						String cadena = String.format("+ <b>%s - </b> %s (%s)", pgimRelacionSubcatDTO.getDescCoSubcatDocumento(), pgimRelacionSubcatDTO.getDescNoSubcatDocumento(), pgimRelacionSubcatDTO.getDescNoCategoriaDocumento());
	
						pgimRelacionSubcatDTO.setNombreDocCadena(cadena);
					}
				}
			}

		}

		return lPgimRelacionSubcatDTO;
	}

	@Override
	public PgimInstanciaPasoDTO obtenerInstanciaPasoActualPorIdInstanciaPaso(final Long idInstanciaPaso) throws Exception {

		PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = null;

		final List<PgimInstanciaPasoDTO> lPgimInstanciaPasoDTO = this.instanciaPasoRepository.obtenerInstanciaPasosActuales(idInstanciaPaso);

		final int nroPasosActuales = lPgimInstanciaPasoDTO.size();

		if (nroPasosActuales == 1) {
			pgimInstanciaPasoDTOActual = lPgimInstanciaPasoDTO.get(0);
		} else if (nroPasosActuales > 1) {
			log.error("Error al obtener el paso actual: id_instancia_paso " + idInstanciaPaso);
			throw new PgimException(TipoResultado.WARNING, "Se ha encontrado más de un paso actual ");
		} else {
			
			log.error("Error al obtener el paso actual: id_instancia_paso " + idInstanciaPaso);
			throw new PgimException(TipoResultado.WARNING, "No se ha encontrado ningún paso actual");
		}

		return pgimInstanciaPasoDTOActual;
	}

	@Override
	public PgimInstanPasoAuxDTO obtenerInstanciaPasoAuxPorId(final Long idInstanciaPaso) throws Exception {

		PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO = this.instanciaPasoAuxRepository.obtenerInstanciaPasoAuxPorId(idInstanciaPaso);

		String fotoDestino = this.perfilUserService.obtenerFotoPersona(pgimInstanPasoAuxDTO.getIdPersonaDestino());
		String fotoOrigen = this.perfilUserService.obtenerFotoPersona(pgimInstanPasoAuxDTO.getIdPersonaOrigen());
		String fotoBase64Destino = null;
		String fotoBase64Origen = null;

		/*
		 * ----------------------------------------
		 * Foto de la persona destino
		 * ----------------------------------------
		 */
		if (fotoDestino != "") {
			if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Osinergmin")){
				fotoBase64Destino = "data:image/jpeg;base64," + fotoDestino;
			} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Supervisora")){
				fotoBase64Destino = "assets/images/ico_es.png";
			}
			
			pgimInstanPasoAuxDTO.setDescFotoBase64Destino(fotoBase64Destino);

		} else {
			if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Osinergmin")){
				fotoBase64Destino = "assets/images/ico_user.png";
			} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Supervisora")){
				fotoBase64Destino = "assets/images/ico_es.png";
			}
			pgimInstanPasoAuxDTO.setDescFotoBase64Destino(fotoBase64Destino);
		}

		/*
		 * ----------------------------------------
		 * Foto de la persona origen
		 * ----------------------------------------
		 */
		if (fotoOrigen != "") {
			
			if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Osinergmin")){
				fotoBase64Origen = "data:image/jpeg;base64," + fotoOrigen;
			} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Supervisora")){
				fotoBase64Origen = "assets/images/ico_es.png";
			}

			pgimInstanPasoAuxDTO.setDescFotoBase64Origen(fotoBase64Origen);
		} else {

			if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Osinergmin")){
				fotoBase64Origen = "assets/images/ico_user.png";
			} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Supervisora")){
				fotoBase64Origen = "assets/images/ico_es.png";
			}

			pgimInstanPasoAuxDTO.setDescFotoBase64Origen(fotoBase64Origen);
		}

		return pgimInstanPasoAuxDTO;
	}

	@Override
	public List<PgimPersonaosiAuxDTO> listarPersonalOsi(final String palabra) {

		return this.personalOsiAuxRepository.listarPersonalOsi(palabra);
	}

	@Override
	public PgimPersonaosiAuxDTO obtenerPersonalOsiNombreUsuarioWindows(final String nombreUsuarioWindows) {

		return this.personalOsiAuxRepository.obtenerPersonalOsiNombreUsuarioWindows(nombreUsuarioWindows);
	}

	@Override
	@Transactional(readOnly = false)
	public PgimInstanciaPasoDTO crearInstanciaPasoInicial(final PgimInstanciaProces pgimInstanciaProcesActual,
			final PgimInstanciaPasoDTO pgimInstanciaPasoDTO, final AuditoriaDTO auditoriaDTO) throws Exception {

		PgimInstanciaPaso pgimInstanciaPaso = new PgimInstanciaPaso();

		pgimInstanciaPaso.setPgimInstanciaProces(new PgimInstanciaProces());
		pgimInstanciaPaso.getPgimInstanciaProces()
				.setIdInstanciaProceso(pgimInstanciaProcesActual.getIdInstanciaProceso());

		pgimInstanciaPaso.setPgimRelacionPaso(new PgimRelacionPaso());
		pgimInstanciaPaso.getPgimRelacionPaso().setIdRelacionPaso(pgimInstanciaPasoDTO.getIdRelacionPaso());

		PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;
		PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);

		Long idProceso = pgimInstanciaProcesActual.getPgimProceso().getIdProceso();

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM)) {

			// Configurando al emisor de la asignación
			PgimPersonalOsiDTO pgimPersonalOsiDTOOrigen = this.obtenerPersonaOsiPorUsuario(auditoriaDTO.getUsername());

			if (pgimPersonalOsiDTOOrigen == null) {
				throw new PgimException(TipoResultado.WARNING,
						String.format("No se ha encontrado ningún usuario del Osinergmin con el nombre '%s'",
								auditoriaDTO.getUsername()));
			}

			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
					pgimInstanciaProcesActual.getIdInstanciaProceso(), pgimPersonalOsiDTOOrigen.getIdPersonalOsi(),
					true, pgimRelacionPaso.getPasoProcesoOrigen().getPgimRolProceso().getIdRolProceso(), false,
					auditoriaDTO);

			pgimInstanciaPaso.setPersonaEqpOrigen(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpOrigen().setIdEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

			// Configurando al receptor de la asignación
			final PgimPersonalOsiDTO pgimPersonalOsiDTODestino = this
					.obtenerPersonaOsiPorId(pgimInstanciaPasoDTO.getDescIdPersonalOsiDestino());

			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
					pgimInstanciaProcesActual.getIdInstanciaProceso(), pgimPersonalOsiDTODestino.getIdPersonalOsi(),
					true, pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso(), true,
					auditoriaDTO);

			pgimInstanciaPaso.setPersonaEqpDestino(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpDestino()
					.setIdEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

		} else if (pgimInstanciaProcesActual.getPgimProceso().getIdProceso()
				.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
			// Configurando al emisor de la asignación
			final PgimPersonalContratoDTO pgimPersonalContratoDTOOrigen = this.obtenerPersonaContratoPorUsuario(
					auditoriaDTO.getUsername(), pgimInstanciaProcesActual.getDescIdContrato());

			if (pgimPersonalContratoDTOOrigen == null) {
				throw new PgimException(TipoResultado.WARNING,
						String.format(
								"No se ha encontrado ningún usuario con el nombre '%s' en el contrato relacionado",
								auditoriaDTO.getUsername()));
			}

			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
					pgimInstanciaProcesActual.getIdInstanciaProceso(),
					pgimPersonalContratoDTOOrigen.getIdPersonalContrato(), false,
					pgimRelacionPaso.getPasoProcesoOrigen().getPgimRolProceso().getIdRolProceso(), false, auditoriaDTO);

			pgimInstanciaPaso.setPersonaEqpOrigen(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpOrigen()
					.setIdEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

			// Configurando al receptor de la asignación
			Long idPersonal = pgimInstanciaPasoDTO.getDescIdPersonalContratoDestino();
			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
					pgimInstanciaProcesActual.getIdInstanciaProceso(), idPersonal, false,
					pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso(), false,
					auditoriaDTO);

			Long idPersonaEqpDestino = pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro();

			pgimInstanciaPaso.setPersonaEqpDestino(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpDestino().setIdEquipoInstanciaPro(idPersonaEqpDestino);
		} else {
			throw new PgimException(TipoResultado.WARNING,
					String.format("El proceso con identificador %d aún no fue implementado para la creación del paso",
							pgimInstanciaProcesActual.getPgimProceso()));
		}

		pgimInstanciaPaso.setFeInstanciaPaso(new Date());
		pgimInstanciaPaso.setDeMensaje(pgimInstanciaPasoDTO.getDeMensaje());
		pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_NO); // el paso inicial se crea como "No leído"
		pgimInstanciaPaso.setFlEsPasoActivo(ConstantesUtil.FL_IND_SI);
		
		pgimInstanciaPaso.setPasoProcesoOrigen(pgimRelacionPaso.getPasoProcesoOrigen());
		pgimInstanciaPaso.setPasoProcesoDestino(pgimRelacionPaso.getPasoProcesoDestino());

		pgimInstanciaPaso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstanciaPaso.setFeCreacion(new Date());
		pgimInstanciaPaso.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstanciaPaso.setIpCreacion(auditoriaDTO.getTerminal());

		pgimInstanciaPaso = this.instanciaPasoRepository.save(pgimInstanciaPaso);

		final PgimInstanciaPasoDTO pgimInstanciaPasoDTONueva = this.instanciaPasoRepository
				.obtenerInstanciaPasoPorId(pgimInstanciaPaso.getIdInstanciaPaso());

		// Generar alertas
		// Grabar alertas para el(los) usuario(s)
		this.enviarAlerta(pgimInstanciaProcesActual, pgimInstanciaPaso, auditoriaDTO);

		return pgimInstanciaPasoDTONueva;
	}

	@Override
	public PgimEqpInstanciaProDTO asegurarPersonalInstanciaProceso(final Long idInstanciaProceso, final Long idPersonal,
			final boolean esOsinergmin, final Long idRolProceso, final Boolean esAsignacionInicial,
			final AuditoriaDTO auditoriaDTO) throws Exception {

		PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;

		if (esOsinergmin) {
			lPgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository
					.obtenerPersonaOsiEqpPorRol(idInstanciaProceso, idPersonal, idRolProceso);
		} else {
			lPgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository
					.obtenerPersonaContraEqpPorRol(idInstanciaProceso, idPersonal, idRolProceso);
		}

		PgimEqpInstanciaPro pgimEqpInstanciaPro = null;

		if (lPgimEqpInstanciaProDTO.size() == 0) {
			// Entonces se crea nuevo miembro del equipo.
			pgimEqpInstanciaPro = new PgimEqpInstanciaPro();

			pgimEqpInstanciaPro.setPgimInstanciaProces(new PgimInstanciaProces());
			pgimEqpInstanciaPro.getPgimInstanciaProces().setIdInstanciaProceso(idInstanciaProceso);

			pgimEqpInstanciaPro.setPgimRolProceso(new PgimRolProceso());
			pgimEqpInstanciaPro.getPgimRolProceso().setIdRolProceso(idRolProceso);

			if (esOsinergmin) {
				pgimEqpInstanciaPro.setPgimPersonalOsi(new PgimPersonalOsi());
				pgimEqpInstanciaPro.getPgimPersonalOsi().setIdPersonalOsi(idPersonal);
			} else {
				pgimEqpInstanciaPro.setPgimPersonalContrato(new PgimPersonalContrato());
				pgimEqpInstanciaPro.getPgimPersonalContrato().setIdPersonalContrato(idPersonal);
			}

			pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
			pgimEqpInstanciaPro.setFeCreacion(new Date());
			pgimEqpInstanciaPro.setUsCreacion(auditoriaDTO.getUsername());
			pgimEqpInstanciaPro.setIpCreacion(auditoriaDTO.getTerminal());

			if (esAsignacionInicial) {
				pgimEqpInstanciaPro.setFlEsResponsable(ConstantesUtil.IND_ACTIVO);
			} else {
				pgimEqpInstanciaPro.setFlEsResponsable(ConstantesUtil.IND_INACTIVO);
			}

			pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);

			pgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository.obtenerPorId(pgimEqpInstanciaPro.getIdEquipoInstanciaPro());
			
		} else {
			// Se toma el primero de la lista porque cualquiera de ellos cumple con el
			// perfil.
			pgimEqpInstanciaProDTO = lPgimEqpInstanciaProDTO.get(0);
		}

		return pgimEqpInstanciaProDTO;
	}

	/**
	 * STORY: PGIM-7277: Abogado/a y Coordinador/a de empresa supervisora por defecto
	 */
	@Override
	public PgimEqpInstanciaProDTO asegurarPersonalContratoInstanciaProceso(final Long idInstanciaProceso, final Long idContrato, final Long idProgramaSupervision, final AuditoriaDTO auditoriaDTO) throws Exception {

		PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;

		PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository.obtenerProgramaAuxById(idProgramaSupervision);
		
		List<PgimPersonalContratoDTO> lPgimPersonalContratoRolAbogadoDTO = this.personalContratoRepository.listarPersonalContratoPorRoles(
			idContrato, ConstantesUtil.PROCESO_ROL_ABOGADO, pgimPrgrmSupervisionAuxDTO.getIdDivisionSupervisora());

		List<PgimPersonalContratoDTO> lPgimPersonalContratoRolCoordinadorEsDTO = this.personalContratoRepository.listarPersonalContratoPorRoles(
			idContrato, ConstantesUtil.PROCESO_ROL_COORDINADOR_SUPERVISION, pgimPrgrmSupervisionAuxDTO.getIdDivisionSupervisora());

		if (lPgimPersonalContratoRolAbogadoDTO.size() > 1) {
			
			String mensajeAviso = String.format(
					"Para asignar la fiscalización, es necesario que en el ámbito del contrato y de la división supervisora solo exista una persona fungiendo el rol de abogado/a; actualmente existen %d.",
					lPgimPersonalContratoRolAbogadoDTO.size());
			throw new PgimException(TipoResultado.WARNING, mensajeAviso);
		}

		if (lPgimPersonalContratoRolCoordinadorEsDTO.size() > 1) {
			String mensajeAviso = String.format(
					"Para asignar la fiscalización, es necesario que en el ámbito del contrato y de la división supervisora solo exista una persona fungiendo el rol de coordinador/a de empresa supervisora; actualmente existen %d.",
					lPgimPersonalContratoRolCoordinadorEsDTO.size());
			throw new PgimException(TipoResultado.WARNING, mensajeAviso);
		}

		if(lPgimPersonalContratoRolAbogadoDTO.size() > 0){

			for (PgimPersonalContratoDTO pgimPersonalContratoDTO : lPgimPersonalContratoRolAbogadoDTO) {

				// Se crea nuevo miembro del equipo persona que fungen el rol de abogado/a.
				PgimEqpInstanciaPro pgimEqpInstanciaPro = new PgimEqpInstanciaPro();
	
				pgimEqpInstanciaPro.setPgimInstanciaProces(new PgimInstanciaProces());
				pgimEqpInstanciaPro.getPgimInstanciaProces().setIdInstanciaProceso(idInstanciaProceso);
	
				pgimEqpInstanciaPro.setPgimRolProceso(new PgimRolProceso());
				pgimEqpInstanciaPro.getPgimRolProceso().setIdRolProceso(pgimPersonalContratoDTO.getIdRolProceso());
	
				pgimEqpInstanciaPro.setPgimPersonalContrato(new PgimPersonalContrato());
				pgimEqpInstanciaPro.getPgimPersonalContrato().setIdPersonalContrato(pgimPersonalContratoDTO.getIdPersonalContrato());
	
				pgimEqpInstanciaPro.setNoCargoPersonaEquipo(pgimPersonalContratoDTO.getNoCargoEnContrato());
				pgimEqpInstanciaPro.setNoPerfilPersonaEquipo(pgimPersonalContratoDTO.getNoPerfilEnContrato());

				pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimEqpInstanciaPro.setFeCreacion(new Date());
				pgimEqpInstanciaPro.setUsCreacion(auditoriaDTO.getUsername());
				pgimEqpInstanciaPro.setIpCreacion(auditoriaDTO.getTerminal());
	
				pgimEqpInstanciaPro.setFlEsResponsable(ConstantesUtil.IND_INACTIVO);
	
				pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);
	
				pgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository.obtenerPorId(pgimEqpInstanciaPro.getIdEquipoInstanciaPro());
			}
		}

		if(lPgimPersonalContratoRolCoordinadorEsDTO.size() > 0){

			for (PgimPersonalContratoDTO pgimPersonalContratoDTO : lPgimPersonalContratoRolCoordinadorEsDTO) {

				// Se crea nuevo miembro del equipo personas que fungen el rol de coordinador/a de empresa supervisora.
				PgimEqpInstanciaPro pgimEqpInstanciaPro = new PgimEqpInstanciaPro();
	
				pgimEqpInstanciaPro.setPgimInstanciaProces(new PgimInstanciaProces());
				pgimEqpInstanciaPro.getPgimInstanciaProces().setIdInstanciaProceso(idInstanciaProceso);
	
				pgimEqpInstanciaPro.setPgimRolProceso(new PgimRolProceso());
				pgimEqpInstanciaPro.getPgimRolProceso().setIdRolProceso(pgimPersonalContratoDTO.getIdRolProceso());
	
				pgimEqpInstanciaPro.setPgimPersonalContrato(new PgimPersonalContrato());
				pgimEqpInstanciaPro.getPgimPersonalContrato().setIdPersonalContrato(pgimPersonalContratoDTO.getIdPersonalContrato());
	
				pgimEqpInstanciaPro.setNoCargoPersonaEquipo(pgimPersonalContratoDTO.getNoCargoEnContrato());
				pgimEqpInstanciaPro.setNoPerfilPersonaEquipo(pgimPersonalContratoDTO.getNoPerfilEnContrato());

				pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
				pgimEqpInstanciaPro.setFeCreacion(new Date());
				pgimEqpInstanciaPro.setUsCreacion(auditoriaDTO.getUsername());
				pgimEqpInstanciaPro.setIpCreacion(auditoriaDTO.getTerminal());
	
				pgimEqpInstanciaPro.setFlEsResponsable(ConstantesUtil.IND_INACTIVO);
	
				pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);
	
				pgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository.obtenerPorId(pgimEqpInstanciaPro.getIdEquipoInstanciaPro());
			}
		}

		return pgimEqpInstanciaProDTO;
	}

	/**
	 * Permite obtener el personal del Osinergmin de acuerdo con su nombre de
	 * usuario windows.
	 * 
	 * @param noUsuario Nombre de usuario windows.
	 * @return
	 * @throws Exception
	 */
	@Override
	public PgimPersonalOsiDTO obtenerPersonaOsiPorUsuario(String noUsuario) throws Exception {
		PgimPersonalOsiDTO pgimPersonalOsiDTO = null;

		List<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = this.personalOsiRepository
				.obtenerPersonalOsiPorUsuario(noUsuario);

		int nroPersonalOsi = lPgimPersonalOsiDTO.size();

		if (nroPersonalOsi == 1) {
			pgimPersonalOsiDTO = lPgimPersonalOsiDTO.get(0);
		} else if (nroPersonalOsi > 1) {
			throw new PgimException(TipoResultado.WARNING, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
					String.format("Se ha encontrado más de un usuario con el mismo nombre ('%s')", noUsuario));
		}

		return pgimPersonalOsiDTO;
	}

	/**
	 * Permite obtener el personal del Contrato de acuerdo con su nombre de usuario
	 * windows. Se va a buscar en toda la tabla del personal de contrato sin
	 * discriminar el contrato.
	 * 
	 * @param noUsuario  Nombre de usuario Windows
	 * @param idContrato
	 * @return
	 * @throws Exception
	 */
	private PgimPersonalContratoDTO obtenerPersonaContratoPorUsuario(String noUsuario, Long idContrato)
			throws Exception {
		PgimPersonalContratoDTO pgimPersonalContratoDTO = null;

		List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = null;

		if (idContrato == null) {
			lPgimPersonalContratoDTO = this.personalContratoRepository.obtenerPersonalContratoPorUsuario(noUsuario);
		} else {
			lPgimPersonalContratoDTO = this.personalContratoRepository.obtenerPersonalContratoPorUsuario(noUsuario,
					idContrato);
		}

		int nroPersonalContrato = lPgimPersonalContratoDTO.size();

		if (nroPersonalContrato >= 1) {
			// De acuerdo con lo señalado por el Osinergmin, más de una persona de los
			// contratos puede tener asignado el mismo nombre de usuario, por tanto, no se
			// debe validar su unicidad.
			pgimPersonalContratoDTO = lPgimPersonalContratoDTO.get(0);
		} else {
			throw new PgimException(TipoResultado.WARNING,
					String.format("No se ha encontrado ningún usuario con el nombre '%s'", noUsuario));
		}

		return pgimPersonalContratoDTO;
	}

	/**
	 * Permite obtener el personal del Contrato de acuerdo con su nombre de usuario
	 * windows. Se va a buscar en toda la tabla del personal de contrato sin
	 * discriminar el contrato.
	 * 
	 * @param noUsuario Nombre de usuario Windows
	 * @return
	 * @throws Exception
	 */
	@Override
	public PgimPersonalContratoDTO obtenerPersonaContratoPorUsuario(String noUsuario) {
		PgimPersonalContratoDTO pgimPersonalContratoDTO = null;

		List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = null;

		lPgimPersonalContratoDTO = this.personalContratoRepository.obtenerPersonalContratoPorUsuario(noUsuario);

		int nroPersonalContrato = lPgimPersonalContratoDTO.size();

		if (nroPersonalContrato >= 1) {
			// De acuerdo con lo señalado por el Osinergmin, más de una persona de los
			// contratos puede tener asignado el mismo nombre de usuario, por tanto, no se
			// debe validar su unicidad.
			pgimPersonalContratoDTO = lPgimPersonalContratoDTO.get(0);
		}

		return pgimPersonalContratoDTO;
	}

	/**
	 * Permite obtener el personal del Osinergmin de acuerdo con su identiticador
	 * interno.
	 * 
	 * @param idPersonalOsi Identificador interno del personal del Osinergmin.
	 * @return
	 * @throws Exception
	 */
	private PgimPersonalOsiDTO obtenerPersonaOsiPorId(final Long idPersonalOsi) throws Exception {
		final PgimPersonalOsiDTO pgimPersonalOsiDTO = this.personalOsiRepository.obtenerPersonalOsiPorId(idPersonalOsi);

		if (pgimPersonalOsiDTO == null) {
			throw new PgimException("error", "No se ha encontrado ningún usuario Osinergmin");
		}

		return pgimPersonalOsiDTO;
	}

	@Override
	public List<PgimEqpInstanciaProDTO> listarPersonalAsignable(final Long idInstanciaProceso, final Long idContrato,
			final Long idRelacionPaso, final String palabra) {

		final List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOTotal = new ArrayList<PgimEqpInstanciaProDTO>();

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository.findById(idRelacionPaso).orElse(null);
		final Long idRolProceso = pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso();

		if (idContrato != 0
				&& !pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getFlSoloOsinergmin().equals("1")) {
			// - Indica que sí hay contrato, por tanto se obtienen las personas del contrato
			// incluidas o no en el equipo de la instancia del proceso.

			List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOContraRol = null;
			lPgimEqpInstanciaProDTOContraRol = this.equipoInstanciaProcesoRepository
					.listarPersonalAsignableContraConRol(idInstanciaProceso, idRolProceso, palabra);

			lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOContraRol);

			List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOContraSinRol = null;
			lPgimEqpInstanciaProDTOContraSinRol = this.equipoInstanciaProcesoRepository
					.listarPersonalAsignableContraSinRol(idInstanciaProceso, idContrato, idRolProceso, palabra);

			lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOContraSinRol);
		}

		if (!pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getFlSoloOsinergmin().equals("2")) {
			List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiRol = null;
			lPgimEqpInstanciaProDTOOsiRol = this.equipoInstanciaProcesoRepository
					.listarPersonalAsignableOsiConRol(idInstanciaProceso, idRolProceso, palabra);

			lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOOsiRol);

			List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiSinRol = null;
			lPgimEqpInstanciaProDTOOsiSinRol = this.equipoInstanciaProcesoRepository
					.listarPersonalAsignableOsiSinRol(idInstanciaProceso, idRolProceso, palabra);

			lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOOsiSinRol);
		}

		return lPgimEqpInstanciaProDTOTotal;
	}

	@Override
	@Transactional(readOnly = false, timeout = 1200)
	public PgimInstanciaPaso asignarPasoProceso(final PgimInstanciaPasoDTO pgimInstanciaPasoDTO,
			final String metodoPadre , final AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimInstanciaPaso pgimInstanciaPasoResultado = null;
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(pgimInstanciaPasoDTO.getIdInstanciaProceso()).orElse(null);

		// SI SE TRATA DE UNA TAREA AGRUPADORA
		if (pgimInstanciaPasoDTO.getDescTareaFlAgrupadoraDestino() != null &&
			pgimInstanciaPasoDTO.getDescTareaFlAgrupadoraDestino().equals("1")) {

			String noUsuarioRemitente = auditoriaDTO.getUsername();
			String noUsuarioDestinatario = null;
			String noUsuarioDestinatarioAdicional = null;

			Integer contador = 0;

			List<String> lUsuarioDestinatario = new ArrayList<String>();
			
			for (PgimInstanciaPasoDTO pgimInstanciaPasoDTOParalela : pgimInstanciaPasoDTO.getDescListInstanciaPaso()) {
				
				if (contador == 0) {
					// Identificando y asignando al destintario principal
					pgimInstanciaPasoDTOParalela.setIdTipoSubflujo(ConstantesUtil.PARAM_TIPO_SUBFLUJO_PRINCIPAL);
					pgimInstanciaPasoResultado = this.asignarPasoProcesoInterno(pgimInstanciaPasoDTOParalela, true, metodoPadre, auditoriaDTO);	
					noUsuarioDestinatario = this.obtenerNombreUsuarioWindows(pgimInstanciaPasoResultado, null);
				} else {
					// Coleccionando y asignando a los otros destintarios:
					pgimInstanciaPasoDTOParalela.setIdTipoSubflujo(ConstantesUtil.PARAM_TIPO_SUBFLUJO_SECUNDARIO);
					pgimInstanciaPasoResultado = this.asignarPasoProcesoInterno(pgimInstanciaPasoDTOParalela, true, metodoPadre, auditoriaDTO);
					noUsuarioDestinatarioAdicional = this.obtenerNombreUsuarioWindows(pgimInstanciaPasoResultado, null);
					lUsuarioDestinatario.add(noUsuarioDestinatarioAdicional);
				}
				
				if (pgimInstanciaPasoResultado == null) {
					throw new PgimException(TipoResultado.ERROR, "No se ha podido crear la instancia del paso");
				}
				
				contador ++;
			}

			if (pgimInstanciaPasoResultado == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha podido crear la instancia del paso");
			}

			// Enviamos expediente Siged con subflujo 
			this.reenviarSubflujoExpediente(pgimInstanciaProces.getNuExpedienteSiged(), noUsuarioRemitente,
					noUsuarioDestinatario, lUsuarioDestinatario,
					"Asignación paralela (en subflujo) desde la PGIM", pgimInstanciaPasoResultado.getDeMensaje(),
					auditoriaDTO);

		}else 
		// SI SE TRATA DE UNA TAREA EN SUBFLUJO SECUNDARIO
		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null && 
				pgimInstanciaPasoDTO.getIdTipoSubflujo().equals(ConstantesUtil.PARAM_TIPO_SUBFLUJO_SECUNDARIO)){
			
			String noUsuarioRemitente = auditoriaDTO.getUsername();
			String noUsuarioDestinatario = null;
			String noUsuarioDestinatarioAdicional = null;

			List<String> lUsuarioDestinatario = new ArrayList<String>();
			
			pgimInstanciaPasoResultado = this.asignarPasoProcesoInterno(pgimInstanciaPasoDTO, true, metodoPadre, auditoriaDTO);	

			// Tomamos el usuario principal actual de la instancia de proceso
			noUsuarioDestinatario = pgimInstanciaProces.getNoUsuarioPrincipal();
					
			// Seguimos enviando en subflujo al expediente SIGED
			noUsuarioDestinatarioAdicional = this.obtenerNombreUsuarioWindows(pgimInstanciaPasoResultado, null);
			lUsuarioDestinatario.add(noUsuarioDestinatarioAdicional);

			if (pgimInstanciaPasoResultado == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha podido crear la instancia del paso");
			}
			
			if (noUsuarioDestinatario == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha obtenido el usuario principal para envío del subflujo");
			}

			this.reenviarSubflujoExpediente(pgimInstanciaProces.getNuExpedienteSiged(), noUsuarioRemitente,
					noUsuarioDestinatario, lUsuarioDestinatario,
					"Asignación de subflujo desde la PGIM", pgimInstanciaPasoResultado.getDeMensaje(),
					auditoriaDTO);
			
		} else {
		// SI SE TRATA DE UNA TAREA CONVENCIONAL O TAREA EN FLUJO PRINCIPAL	
			pgimInstanciaPasoResultado = this.asignarPasoProcesoInterno(pgimInstanciaPasoDTO, false, metodoPadre, auditoriaDTO);
		}

		return pgimInstanciaPasoResultado;		
	}

	/**
	 * Permite obtener el nombre de usuario windows de la persona destino de una instancia de paso, 
	 * Ó de cualquier otro usuario a partir de su Id de equipo de instancia de proceso
	 * 
	 * @param pgimInstanciaPaso		
	 * @param idEquipoInstanciaPro	
	 * @return
	 */
	private String obtenerNombreUsuarioWindows(final PgimInstanciaPaso pgimInstanciaPaso, final Long idEquipoInstanciaPro ) {
		String noUsuarioDestinatario = null;
		
		Long idEquipoInstanciaProConsulta = (pgimInstanciaPaso != null)? 
				pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro() : idEquipoInstanciaPro;
		
		if(idEquipoInstanciaProConsulta != null) {

			PgimEqpInstanciaPro pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository.findById(idEquipoInstanciaProConsulta).orElse(null);
	
			if (pgimEqpInstanciaPro.getPgimPersonalOsi() != null) {
				PgimPersonalOsi pgimPersonalOsi = this.personalOsiRepository.findById(pgimEqpInstanciaPro.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);
				noUsuarioDestinatario = pgimPersonalOsi.getNoUsuario();
			} else {
				PgimPersonalContrato pgimPersonalContrato = this.personalContratoRepository.findById(pgimEqpInstanciaPro.getPgimPersonalContrato().getIdPersonalContrato()).orElse(null);
				noUsuarioDestinatario = pgimPersonalContrato.getNoUsuario();
			}
		}

		return noUsuarioDestinatario;
	}

	@Transactional(readOnly = false)
	private void desactivarEtiquetasNotif(Long idRelacionPaso, AuditoriaDTO auditoriaDTO){
		List<PgimCfgEtiquetaNotifDTO> lPgimCfgEtiquetaNotifDTO = this.cfgEtiquetaNotifRepository.lCfgEtiquetaNotifDTOByIdRelacion(idRelacionPaso);

		for (PgimCfgEtiquetaNotifDTO pgimCfgEtiquetaNotifDTO :lPgimCfgEtiquetaNotifDTO){

			List<PgimDocEtiquetaNotifDTO> lPgimDocEtiquetaNotifDTO = this.docEtiquetaNotifRepository.listarPgimDocEtiquetadosByInstaPaso(pgimCfgEtiquetaNotifDTO.getIdPasoProceso());
	
			for(PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTO: lPgimDocEtiquetaNotifDTO){
				PgimDocEtiquetaNotif pgimDocEtiquetaNotif = this.docEtiquetaNotifRepository.findById(pgimDocEtiquetaNotifDTO.getIdDocEtiquetaNotif()).orElse(null);
				
				pgimDocEtiquetaNotif.setFlEtiquetaNotifActiva(ConstantesUtil.IND_INACTIVO);
				//pgimDocEtiquetaNotif.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimDocEtiquetaNotif.setFeActualizacion(auditoriaDTO.getFecha());
				pgimDocEtiquetaNotif.setUsActualizacion(auditoriaDTO.getUsername());
				pgimDocEtiquetaNotif.setIpActualizacion(auditoriaDTO.getTerminal());

				PgimDocEtiquetaNotif pgimDocEtiquetaActualIna = this.docEtiquetaNotifRepository.save(pgimDocEtiquetaNotif);
				PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifDTOina = this.docEtiquetaNotifRepository.pgimDocEtiquetaNotifById(pgimDocEtiquetaActualIna.getIdDocEtiquetaNotif()) ;
			}
						
		}

	}

	private PgimInstanciaPaso asignarPasoProcesoInterno(final PgimInstanciaPasoDTO pgimInstanciaPasoDTO, final Boolean esParalela,
			final String metodoPadre, final AuditoriaDTO auditoriaDTO) throws Exception {
		
				final PgimRelacionPaso pgimRelacionPasoDestino = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);
 
		//Quitar etiquetas a documentos según el paso de proceso
		//-------------------------------------------------------//
		this.desactivarEtiquetasNotif(pgimRelacionPasoDestino.getIdRelacionPaso(),auditoriaDTO);
		//-------------------------------------------------------//

		final Long idPersonaEqpOrigen = pgimInstanciaPasoDTO.getIdPersonaEqpOrigen();
		Long idPersonaEqpDestino = pgimInstanciaPasoDTO.getIdPersonaEqpDestino();

		if (idPersonaEqpDestino == null) {
			// Como no existe entonces se debe asegurar la existencia de la persona destino
			// como miembro del equipo
			// de la instancia del proceso.
			PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;

			boolean esOsinergmin = false;
			Long idPersonal = null;

			if (pgimInstanciaPasoDTO.getDescIdPersonalOsiDestino() != null) {
				esOsinergmin = true;
				idPersonal = pgimInstanciaPasoDTO.getDescIdPersonalOsiDestino();
			} else if (pgimInstanciaPasoDTO.getDescIdPersonalContratoDestino() != null) {
				esOsinergmin = false;
				idPersonal = pgimInstanciaPasoDTO.getDescIdPersonalContratoDestino();
			}

			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(pgimInstanciaPasoDTO.getIdInstanciaProceso(),
					idPersonal, esOsinergmin,
					pgimRelacionPasoDestino.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso(), false,
					auditoriaDTO);

			idPersonaEqpDestino = pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro();
		}
		
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
				.findById(pgimInstanciaPasoDTO.getIdInstanciaProceso()).orElse(null);
		
		//Primero actualizamos usuario principal en instancia de proceso, de ser el caso, 
		//para evitar riesgo de incongruencia con el Siged cuando haya concurrencia de uso de reenviarSubflujo		
		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null && 
		   pgimInstanciaPasoDTO.getIdTipoSubflujo().equals(ConstantesUtil.PARAM_TIPO_SUBFLUJO_PRINCIPAL)) {
			
			String noUsuarioPrincipal = this.obtenerNombreUsuarioWindows(null, idPersonaEqpDestino);
			pgimInstanciaProces.setNoUsuarioPrincipal(noUsuarioPrincipal);				
			pgimInstanciaProces = this.instanciaProcesService.modificarInstanciaProcesEntity(pgimInstanciaProces, auditoriaDTO);
		}		
		
		PgimInstanciaPaso pgimInstanciaPaso = new PgimInstanciaPaso();

		pgimInstanciaPaso.setPgimInstanciaProces(new PgimInstanciaProces());
		pgimInstanciaPaso.getPgimInstanciaProces().setIdInstanciaProceso(pgimInstanciaPasoDTO.getIdInstanciaProceso());

		pgimInstanciaPaso.setPgimRelacionPaso(new PgimRelacionPaso());
		pgimInstanciaPaso.getPgimRelacionPaso().setIdRelacionPaso(pgimInstanciaPasoDTO.getIdRelacionPaso());

		pgimInstanciaPaso.setPersonaEqpOrigen(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpOrigen().setIdEquipoInstanciaPro(idPersonaEqpOrigen);

		pgimInstanciaPaso.setPersonaEqpDestino(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpDestino().setIdEquipoInstanciaPro(idPersonaEqpDestino);

		pgimInstanciaPaso.setPasoProcesoOrigen(pgimRelacionPasoDestino.getPasoProcesoOrigen());
		pgimInstanciaPaso.setPasoProcesoDestino(pgimRelacionPasoDestino.getPasoProcesoDestino());

		pgimInstanciaPaso.setFeInstanciaPaso(new Date());
		pgimInstanciaPaso.setDeMensaje(pgimInstanciaPasoDTO.getDeMensaje());

		pgimInstanciaPaso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstanciaPaso.setFeCreacion(new Date());
		pgimInstanciaPaso.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstanciaPaso.setIpCreacion(auditoriaDTO.getTerminal());

		pgimInstanciaPaso.setInstanciaPasoPadre(new PgimInstanciaPaso());
		pgimInstanciaPaso.getInstanciaPasoPadre().setIdInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaPasoPadre());
		
		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null) {
			pgimInstanciaPaso.setTipoSubflujo(new PgimValorParametro());
			pgimInstanciaPaso.getTipoSubflujo().setIdValorParametro(pgimInstanciaPasoDTO.getIdTipoSubflujo());
		}

		// Flag que indica si es el último paso dado en la instancia de proceso. Desactivamos el paso anterior
		PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository
		.findById(pgimInstanciaPasoDTO.getIdInstanciaPasoPadre()).orElse(null);
		if(pgimInstanciaPasoActual != null){
			pgimInstanciaPasoActual.setFlEsPasoActivo(ConstantesUtil.FL_IND_NO);
			this.instanciaPasoRepository.save(pgimInstanciaPasoActual);
		}
		
		// Indicamos si el último paso estará activo o no para su visualización en el FE (algunas tareas paralelas no estarán activas)
		if(pgimInstanciaPasoDTO.getFlEsPasoActivo() != null) {
			pgimInstanciaPaso.setFlEsPasoActivo(pgimInstanciaPasoDTO.getFlEsPasoActivo());
		}else {
			pgimInstanciaPaso.setFlEsPasoActivo(ConstantesUtil.FL_IND_SI);
		}
		
		// Indicar si la asignación por defecto será leído o no
		if (pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro().equals(pgimInstanciaPaso.getPersonaEqpOrigen().getIdEquipoInstanciaPro())){
			pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_SI);	
			pgimInstanciaPaso.setFeLectura(new Date());				
		}else {
			pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_NO);
		}

		// Validar si el paso representa una solicitud de aprobación
		PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);

		if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro().equals(ConstantesUtil.PARAM_REENVIAR)
				&& pgimRelacionPaso.getFlRequiereAprobacion().equals(ConstantesUtil.IND_REQ_APROBACION_SI)) {
			pgimInstanciaPaso.setPersonaEqpSolic(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpSolic().setIdEquipoInstanciaPro(idPersonaEqpOrigen);
		}

		// Validar si el paso previo ya tenía un usuario solicitante y el actual sea una
		// reasignación
		Long idInstanciaPasoPrevio = pgimInstanciaPaso.getInstanciaPasoPadre().getIdInstanciaPaso();
		PgimInstanciaPaso pgimInstanciaPasoPrevio = this.instanciaPasoRepository.findById(idInstanciaPasoPrevio)
				.orElse(null);
		PgimRelacionPaso pgimRelacionPasoPrevio = this.relacionPasoRepository
				.findById(pgimInstanciaPasoPrevio.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);

		if (pgimRelacionPasoPrevio.getFlRequiereAprobacion().equals(ConstantesUtil.IND_REQ_APROBACION_SI)
				&& pgimRelacionPasoPrevio.getTipoAccionSiged().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_REENVIAR)
				&& pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro().equals(ConstantesUtil.PARAM_REENVIAR)) {
			pgimInstanciaPaso.setPersonaEqpSolic(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpSolic()
					.setIdEquipoInstanciaPro(pgimInstanciaPasoPrevio.getPersonaEqpSolic().getIdEquipoInstanciaPro());
		}

		// Si el paso previo tiene un usuario solicitante lo obtenemos para la relación
		// de paso actual
		if (pgimInstanciaPasoPrevio.getPersonaEqpSolic() != null) {
			pgimInstanciaPaso.setPersonaEqpSolic(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpSolic()
					.setIdEquipoInstanciaPro(pgimInstanciaPasoPrevio.getPersonaEqpSolic().getIdEquipoInstanciaPro());
		}

		pgimInstanciaPaso = this.instanciaPasoRepository.save(pgimInstanciaPaso);

		/*
		 * Validar duplicidad de objetos de trabajos
		 */
		this.validarDuplicidadPasosActivos(pgimInstanciaProces);

		// Llamando al proceso de validación:
		// =================================
		this.validarTransicionPasoProceso(pgimRelacionPasoDestino, pgimInstanciaPaso, pgimInstanciaPasoDTO,
				pgimInstanciaProces, auditoriaDTO);

		if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_SUPERVISION)) {
			this.supervisionService.realizarAccionesPorTransicion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
		} else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_PAS)) {
			this.pasService.realizarAccionesPorTransicion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
		} else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_PRGRM_SUPERVISION)) {
			this.prgrmSupervisionService.realizarAccionesPorTransicion(pgimInstanciaProces, pgimInstanciaPaso,
					auditoriaDTO);
		} else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION)) {
			this.liquidacionService.realizarAccionesPorTransicion(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
		} else if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TM_CONFIGURA_RIESGO)) {
			this.configuraRiesgoService.realizarAccionesPorTransicion(pgimInstanciaProces, pgimInstanciaPaso,
					auditoriaDTO);
		}

		// Grabar alertas para el(los) usuario(s)
		this.enviarAlerta(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);
		
		// Grabar alertas específicas 
		this.alertaService.crearAlertasEspecificas(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

		// permite determinar que tipo de alerta se va a grabar alertas de cumplimiento (PgimIprocesoAlerta)
		this.iprocesoAlertaService.determinarTipAlertInstanciaACrear(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

		// Acciones de envío/reenvío de expediente SIGED		
		// ==============================================
		if (pgimInstanciaProces.getNuExpedienteSiged() != null) {
			// Si ya se cuenta con expediente Siged entonces se envía el expediente, siempre
			// que la persona origen y destino sean diferentes.
			// Asimismo se envía siempre y cuando sea asignación convencional o del flujo principal. Si es subflujo secundario se envía con el método reenviarSubflujo
			ExpedienteOutRO expedienteOutRO = null;

			if (esParalela == false) {
				expedienteOutRO = this.enviarExpedienteSiged(pgimInstanciaProces, pgimInstanciaPaso,
						auditoriaDTO, false);

				if (!expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					DetalleExcepcionDTO detalleExcepcionDTO = expedienteOutRO.getDetalleExcepcionDTO();
					if(detalleExcepcionDTO != null) detalleExcepcionDTO.setUbicacion(metodoPadre);
					throw new PgimException(TipoResultado.ERROR, expedienteOutRO.getMessage(), detalleExcepcionDTO);
				}
			}

			// Traslado de expediente a contabilidad, al finalizar la liquidación
			if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION)
					&& pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()
							.equals(ConstRelacionPasoLiquidacion.COMPLETARLIQUIDACION_LIQUIDACIONCOMPLETADA)) {

				final String mensajeAsunto = String.format("PGIM: %s ---> %s",
						pgimRelacionPasoDestino.getPasoProcesoOrigen().getNoPasoProceso(),
						pgimRelacionPasoDestino.getPasoProcesoDestino().getNoPasoProceso());

				// Usuario remitente
				Long idEquipoIntanciaPro = pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro();
				PgimEqpInstanciaPro pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository
						.findById(idEquipoIntanciaPro).orElse(null);
				Long idPersonalOsi = pgimEqpInstanciaPro.getPgimPersonalOsi().getIdPersonalOsi();
				PgimPersonalOsi pgimPersonalOsi = this.personalOsiRepository.findById(idPersonalOsi).orElse(null);
				String nombreUsuarioWindowsOrigen = pgimPersonalOsi.getNoUsuario();
				String coUsuarioSigedOrigen = this.obtenerCoUsuarioSigedPorNombreUsuario(nombreUsuarioWindowsOrigen);
				Long coUsuarioSigedRemitente = new Long(coUsuarioSigedOrigen);

				// Usuario destino final
				PgimValorParametro pgimValorParametro = this.valorParametroRepository
						.findById(ConstantesUtil.PARAM_USUARIO_SIGED_CONTABILIDAD).orElse(null);

				String nombreUsuarioWindowsDestino = pgimValorParametro.getDeValorAlfanum();
				String coUsuarioSigedDestino = this.obtenerCoUsuarioSigedPorNombreUsuario(nombreUsuarioWindowsDestino);
				Long coUsuarioSigedDestinatario = new Long(coUsuarioSigedDestino);

				final ExpedienteReenvio expedienteReenvio = new ExpedienteReenvio();
				expedienteReenvio.setNumeroExpediente(pgimInstanciaProces.getNuExpedienteSiged());
				expedienteReenvio.setIdRemitente(coUsuarioSigedRemitente);
				expedienteReenvio.setIdDestinatario(coUsuarioSigedDestinatario);
				expedienteReenvio.setAsunto(mensajeAsunto);
				expedienteReenvio.setContenido(pgimInstanciaPaso.getDeMensaje());
				expedienteReenvio.setAprobacion(pgimRelacionPasoDestino.getFlRequiereAprobacion());

				AuditoriaDTO auditoriaDTOTmp = auditoriaDTO;
				auditoriaDTOTmp.setUsername(nombreUsuarioWindowsOrigen);
				auditoriaDTOTmp.setCoUsuarioSiged(coUsuarioSigedRemitente.toString());

				try {
					expedienteOutRO = this.documentoService.reenviarExpedienteSiged(expedienteReenvio, auditoriaDTOTmp);

					if (!expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
						final String mensajeExcepcionControlada = String.format(
								"Ha ocurrido un problema al reenviar el expediente Siged a Contabilidad (Error: %s, Descripción: %s)",
								expedienteOutRO.getErrorCode(), expedienteOutRO.getMessage());
						
						DetalleExcepcionDTO detalleExcepcionDTO = expedienteOutRO.getDetalleExcepcionDTO();
						if(detalleExcepcionDTO != null) detalleExcepcionDTO.setUbicacion(ConstantesUtil.METODO_REENVIAR_EXP_SIGED_CONTABILIDAD);
						
						throw new PgimException(TipoResultado.ERROR, mensajeExcepcionControlada, detalleExcepcionDTO);  //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
					}
				} catch (final PgimException e) {
					log.error(e.getMessage(), e);
					throw e;
					
				} catch (final Exception e) {
					final String mensajeExcepcionNoControlada = String
							.format("Ha ocurrido un problema al reenviar el expediente Siged a Contabilidad");
					log.error(e.getMessage(), e);
					throw new PgimException(TipoResultado.ERROR, mensajeExcepcionNoControlada);  //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
				}

			}
		}

		// Si todo va bien y existen documentos adjuntos procedemos a registrarlos:
		if (pgimInstanciaPasoDTO.getDescListIdDocumentos() != null) {
			if (!pgimInstanciaPasoDTO.getDescListIdDocumentos().isEmpty()) {
				for (PgimDocumentoDTO pgimDocumentoDTO : pgimInstanciaPasoDTO.getDescListIdDocumentos()) {
					PgimInstanciaPasoDoc pgimInstanciaPasoDoc = new PgimInstanciaPasoDoc();

					pgimInstanciaPasoDoc.setPgimDocumento(new PgimDocumento());
					pgimInstanciaPasoDoc.getPgimDocumento().setIdDocumento(pgimDocumentoDTO.getIdDocumento());
					pgimInstanciaPasoDoc.setPgimInstanciaPaso(pgimInstanciaPaso);
					pgimInstanciaPasoDoc.setFeTransicionPasoDoc(new Date());
					pgimInstanciaPasoDoc.setDeMensaje("Asignación");

					pgimInstanciaPasoDoc.setEsRegistro(ConstantesUtil.IND_ACTIVO);
					pgimInstanciaPasoDoc.setFeCreacion(new Date());
					pgimInstanciaPasoDoc.setUsCreacion(auditoriaDTO.getUsername());
					pgimInstanciaPasoDoc.setIpCreacion(auditoriaDTO.getTerminal());

					this.instanciaPasoDocRepository.save(pgimInstanciaPasoDoc);
				}
			}
		}
		
		// Acciones finales con el expediente SIGED (casos de archivado)
		this.documentoService.archivarExpedienteEnFlujo(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);		

		return pgimInstanciaPaso;
	}

	public void validarDuplicidadPasosActivos(PgimInstanciaProces pgimInstanciaProces) {

		// Permite la sincronización de los cambios en el contexto de persistencia con la base de datos antes de que la transacción finalice
		this.instanciaPasoRepository.flush();

		int contadorPasoPrincipal = 0;

		String mensajeObjeto = "";
		
		List<PgimInstanciaPasoDTO> lIntanciaPasoDTOActuales = this.instanciaPasoRepository
				.obtenerInstanciaPasosActualesPorIdInstanciaProceso(pgimInstanciaProces.getIdInstanciaProceso());

		if (pgimInstanciaProces.getNoTablaInstancia().equals(ConstantesUtil.PARAM_TABLA_TC_PAS)) {

			/**
			 * ------------------------------------------------------------------------
			 * Validamos los pasos activos que se van a duplicar en el PAS
			 * ------------------------------------------------------------------------
			 */
			for (PgimInstanciaPasoDTO entidad : lIntanciaPasoDTOActuales) {

				if (entidad.getIdTipoSubflujo() != null
						&& entidad.getIdTipoSubflujo().equals(ConstantesUtil.PARAM_TIPO_SUBFLUJO_PRINCIPAL)) {
					contadorPasoPrincipal++;
				}

			}

			if ((lIntanciaPasoDTOActuales.size() > 1 && contadorPasoPrincipal == 0) || contadorPasoPrincipal > 1) {
				throw new PgimException(TipoResultado.WARNING,
						"La tarea que deseas transitar ya ha sido ejecutado previamente, por favor actualizar el PAS.");
			}
			/** * Fin validación */
		} else {

			switch (pgimInstanciaProces.getNoTablaInstancia()) {
				case ConstantesUtil.PARAM_TABLA_TC_SUPERVISION:
					mensajeObjeto = "por favor actualizar la fiscalización.";
					break;

				case ConstantesUtil.PARAM_TABLA_TC_RANKING_RIESGO:
					mensajeObjeto = "por favor actualizar el ranking de riesgo.";
					break;

				case ConstantesUtil.PARAM_TABLA_TM_CONFIGURA_RIESGO:
					mensajeObjeto = "por favor actualizar la configuración de riesgo.";
					break;

				case ConstantesUtil.PARAM_TABLA_TC_PRGRM_SUPERVISION:
					mensajeObjeto = "por favor actualizar el programa de supervisión.";
					break;

				case ConstantesUtil.PARAM_TABLA_TC_LIQUIDACION:
					mensajeObjeto = "por favor actualizar la liquidación.";
					break;

				default:
					mensajeObjeto = "por favor actualizar el objeto de trabajo.";
					break;
			}

			if (lIntanciaPasoDTOActuales.size() > 1) {

					throw new PgimException(TipoResultado.WARNING, "La tarea que deseas transitar ya ha sido ejecutado previamente, " + mensajeObjeto);
			}
			/** * Fin validación */
		}

	}

	@Override
	public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso,
			PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimInstanciaProces pgimInstanciaProces,
			AuditoriaDTO auditoriaDTO) throws Exception {
		// Se realiza la validación de los tipos documentales esperados para la
		// transición.

		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();

		Long idProceso = this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null).getPgimProceso()
				.getIdProceso();

		this.validarFlReservaActivaDocumentos(pgimRelacionPaso, pgimInstanciaProces, auditoriaDTO);

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
			this.supervisionService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}
		
		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
			this.pasService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)) {
			this.prgrmSupervisionService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
			this.liquidacionService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)) {
			this.rankingRiesgosService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)) {
			this.configuraRiesgoService.validarTransicionPasoProceso(pgimRelacionPaso, pgimInstanciaPaso);
		}

		if (!idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
			this.validarDocumentosMandatorios(pgimRelacionPaso, pgimInstanciaPaso,
			pgimInstanciaPasoDTO.getDescIdEspecialidad());
		}

		this.validarFirmaDocumentos(pgimRelacionPaso, pgimInstanciaProces, auditoriaDTO);
		
		this.validarNotificacionDocResponsables(pgimRelacionPaso, pgimInstanciaProces, auditoriaDTO);
	}

	/**
	 * Permite validar los documentos mandatorios en la transición de un flujo de
	 * trabajo.
	 * 
	 * @param pgimRelacionPaso
	 * @param pgimInstanciaPaso
	 * @param idEspecialidad
	 */
	private void validarDocumentosMandatorios(PgimRelacionPaso pgimRelacionPaso, PgimInstanciaPaso pgimInstanciaPaso,
			Long idEspecialidad) {
		Long idRelacionPaso = pgimRelacionPaso.getIdRelacionPaso();
		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
		String flPropia = "";
		String flNoIniciadaAfiscalizado = "";

		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = null;
		PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso());

		if(pgimSupervisionDTO != null){
			flPropia = pgimSupervisionDTO.getFlPropia();
			flNoIniciadaAfiscalizado = (pgimSupervisionDTO.getFlNoIniciadaAfiscalizado() == null ? "0" : pgimSupervisionDTO.getFlNoIniciadaAfiscalizado());
		}

		lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarSubCategoriasMandatoriasNoCargadas(idRelacionPaso,
				idInstanciaProceso);

		lPgimRelacionSubcatDTO = this.depurarPorEspecialidad(lPgimRelacionSubcatDTO, idEspecialidad);
		
		lPgimRelacionSubcatDTO = this.depurarSubcatCasosEspecificos(idRelacionPaso, idInstanciaProceso, lPgimRelacionSubcatDTO);

		this.analizarPosibleExcepcion(lPgimRelacionSubcatDTO, false);

		lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarSubCategoriasAlmenosUnoCargadas(idRelacionPaso,
				idInstanciaProceso);

		if (lPgimRelacionSubcatDTO.size() == 0) {
			lPgimRelacionSubcatDTO = this.relacionSubcatRepository
					.listarSubCategoriasAlmenosUnoNoCargadas(idRelacionPaso, idInstanciaProceso);

			lPgimRelacionSubcatDTO = this.depurarPorEspecialidad(lPgimRelacionSubcatDTO, idEspecialidad);

			/* condicional solo para la tarea actual "Realizar mediciones y verificar hechos" hacia "Presentar acta de fiscalización" y que sea fiscalización propia */
			if(idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REALIZARMEDICIONES_PRESENTARACTAFISC) 
				&& (
					(flPropia.equals("1")) 
					|| flNoIniciadaAfiscalizado.equals("1")
					)){
				log.info("Se trata de una fiscalización propia o una fiscalización impedida por el agente fiscalizado, por tanto, no se valida los documentos");
			}else {
				this.analizarPosibleExcepcion(lPgimRelacionSubcatDTO, true);
			}
		}
	}

	@Override
	public List<PgimRelacionSubcatDTO> depurarPorEspecialidad(List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO,
			Long idEspecialidad) {

		if (idEspecialidad != null) {
			lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
				if (pgimRelacionSubcatDTO.getDescIdEspecialidad() != null) {
					return pgimRelacionSubcatDTO.getDescIdEspecialidad().equals(idEspecialidad);
				} else {
					return true;
				}
			}).collect(Collectors.toList());
		}

		return lPgimRelacionSubcatDTO;
	}
	
	@Override
	public List<PgimRelacionSubcatDTO> depurarSubcatCasosEspecificos(Long idRelacionPaso, Long idInstanciaProceso, 
			List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO) {

		// En caso de ser Accidente mortal, NO es obligatorio el "Anexo del Informe de Instrucción (Anexo I)"
		
		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARPAS) || 
			idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARARCH) 
			) {
		
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
			
			if (pgimSupervisionDTO.getIdMotivoSupervision().equals(ConstantesUtil.MOTIVO_ACCIDENTE_MORTAL)) {
				
				lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
						return !pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ANEXO_INFORME_INSTRUCCION);
				}).collect(Collectors.toList());								
			}
		}
		
		// En caso de ser Fisc. propia, NO es obligatorio la DJIM en fase 1
		
		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES)) {
			
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
			
			if (pgimSupervisionDTO.getFlPropia().equals("1")) {
				
				lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
						return !pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_DJIM);
				}).collect(Collectors.toList());								
			}
		}
		
		// En caso de no tener incumplimientos, NO es obligatorio la Ficha de hechos verificados - incumplimientos
		
		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARINFORME_APROBARINFORME)) {
			
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
			List<PgimHechoConstatadoDTO> lPgimHechoConstatadoDTOIncumpl = this.hechoConstatadoRepository.
					listarHechosConstatadosConNoCumplimientos(pgimSupervisionDTO.getIdSupervision(), ConstantesUtil.PARAM_HC_ROL_SUPERVISOR);
			
			if (lPgimHechoConstatadoDTOIncumpl.size() == 0) {
				
				lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
						return !pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_HC);
				}).collect(Collectors.toList());								
			}
		}

		// En caso de ser Fiscalización de estabilidad física no requiere acta de medición
		
		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REALIZARMEDICIONES_PRESENTARACTAFISC) ) 
		{
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

			Long idSubtipoSuperv = pgimSupervisionDTO.getIdSubtipoSupervision();
			PgimSubtipoSupervisionDTO tipoSuperv = this.subTipoSupervisionRepository.obtenerSubTipoSupervisionById(idSubtipoSuperv);
			Long idTipoSuperv = tipoSuperv.getIdTipoSupervision();
			
			if(( idTipoSuperv.equals(ConstantesUtil.PARAM_TIPO_SUPERVISION_PROGRAMADA) && idSubtipoSuperv.equals(ConstantesUtil.PARAM_SUBTIPO_PROG_CON_EST_ESTABILIDAD) ) || 
				( idTipoSuperv.equals(ConstantesUtil.PARAM_TIPO_SUPERVISION_NOPROGRAMADA) && idSubtipoSuperv.equals(ConstantesUtil.PARAM_SUBTIPO_NOPROG_CON_EST_ESTABILIDAD) )
			){
				lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
					return !(pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_CONTROL_INSPECCION_DEPOSITOS_DESMONTE) ||
					pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_CONTROL_INSPECCION_DEPOSITOS_RELAVES) ||
					pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_CONTROL_INSPECCION_PILAS_LIXIVIACION) ||
					pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SC_ACTA_MEDICION_CAMPO) );
				}).collect(Collectors.toList());

			}
		
		}

		// En caso de ser Fiscalización de estabilidad física no requiere DJ de instrumentos de medición
		
		if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_FIRMARDJI_GENTDR) || 
			idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REVISAR_ANTECEDENTES_APROBAR_REV_ANTECEDENTES ) ||
			idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO)
		) 
		{
			PgimSupervisionDTO pgimSupervisionDTO = this.supervisionService.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

			Long idSubtipoSuperv = pgimSupervisionDTO.getIdSubtipoSupervision();
			PgimSubtipoSupervisionDTO tipoSuperv = this.subTipoSupervisionRepository.obtenerSubTipoSupervisionById(idSubtipoSuperv);
			Long idTipoSuperv = tipoSuperv.getIdTipoSupervision();
			
			if(( idTipoSuperv.equals(ConstantesUtil.PARAM_TIPO_SUPERVISION_PROGRAMADA) && idSubtipoSuperv.equals(ConstantesUtil.PARAM_SUBTIPO_PROG_CON_EST_ESTABILIDAD) ) || 
				( idTipoSuperv.equals(ConstantesUtil.PARAM_TIPO_SUPERVISION_NOPROGRAMADA) && idSubtipoSuperv.equals(ConstantesUtil.PARAM_SUBTIPO_NOPROG_CON_EST_ESTABILIDAD) )
			){
				lPgimRelacionSubcatDTO = lPgimRelacionSubcatDTO.stream().filter(pgimRelacionSubcatDTO -> {
					return !(pgimRelacionSubcatDTO.getIdSubcatDocumento().equals(ConstantesUtil.PARAM_SUBCAT_DOC_DJIM) );
				}).collect(Collectors.toList());

			}
		
		}

		return lPgimRelacionSubcatDTO;
	}

	@Override
	public void analizarPosibleExcepcion(List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO, boolean alMenosUno) {
		String msjExcepcionControlada = null;

		if (lPgimRelacionSubcatDTO.size() > 0) {

			for (PgimRelacionSubcatDTO pgimRelacionSubcatDTO : lPgimRelacionSubcatDTO) {
				if (msjExcepcionControlada == null) {
					msjExcepcionControlada = String.format("+ %s: %s (%s: %s)",
							pgimRelacionSubcatDTO.getDescCoSubcatDocumento(),
							pgimRelacionSubcatDTO.getDescNoSubcatDocumento(),
							pgimRelacionSubcatDTO.getDescCoCategoriaDocumento(),
							pgimRelacionSubcatDTO.getDescNoCategoriaDocumento());
				} else {
					msjExcepcionControlada = msjExcepcionControlada + "<br>"
							+ String.format("+ %s: %s (%s: %s)", pgimRelacionSubcatDTO.getDescCoSubcatDocumento(),
									pgimRelacionSubcatDTO.getDescNoSubcatDocumento(),
									pgimRelacionSubcatDTO.getDescCoCategoriaDocumento(),
									pgimRelacionSubcatDTO.getDescNoCategoriaDocumento());
				}
			}

			if (lPgimRelacionSubcatDTO.size() == 1) {
				msjExcepcionControlada = "No se puede asignar el paso porque <b>el documento requerido por la asignación aún no fue adjuntado</b>, a saber:<br><br>"
						+ msjExcepcionControlada;
			} else {
				if (alMenosUno) {
					msjExcepcionControlada = "No se puede asignar el paso porque al menos uno de <b>los documentos requeridos para la asignación aún no fueron adjuntados</b>, a saber:<br><br>"
							+ msjExcepcionControlada;
				} else {
					msjExcepcionControlada = "No se puede asignar el paso porque <b>los documentos requeridos para la asignación aún no fueron adjuntados</b>, a saber:<br><br>"
							+ msjExcepcionControlada;
				}
			}

			throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
		}
	}

	/**
	 * Permite validar que los documentos requeridos para la transición de paso
	 * hayan sido firmados.
	 * 
	 * @throws Exception
	 * 
	 */
	public void validarFirmaDocumentos(PgimRelacionPaso pgimRelacionPaso,
			PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO) throws Exception {
		

		List<PgimRelacionFirmaDTO> lPgimRelacionFirmaDTO = this.relacionFirmaRepository
				.listarRelacionFirmaPorIdRelacion(pgimRelacionPaso.getIdRelacionPaso(), "1");

		if (lPgimRelacionFirmaDTO.size() > 0) {

			String sValidacion = this.verificarFirmaDocumentos(pgimRelacionPaso.getIdRelacionPaso(), pgimInstanciaProces, "1", auditoriaDTO);			

			if (!sValidacion.equals("")) {
				String submsj = (lPgimRelacionFirmaDTO.size() == 1) ? "el documento siguiente" : "los siguientes documentos"; 
				sValidacion = "No se puede asignar el paso porque <b>se requiere firmar digitalmente " + submsj + "</b>: <br/> <br/>"
						+ sValidacion;
				
				throw new PgimException(TipoResultado.WARNING, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
										sValidacion);
			}

		}

	}
	
	
	/**
	 * Permite verificar si hay documentos pasibles de firma obligatoria u opcional
	 * previo a una transición de paso, que no fueron firmados.
	 * 
	 * @param idRelacionPaso
	 * @param pgimInstanciaProces
	 * @param flObligatorio	
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public String verificarFirmaDocumentos(Long idRelacionPaso, PgimInstanciaProces pgimInstanciaProces, 
			String flObligatorio, AuditoriaDTO auditoriaDTO) throws Exception {

		List<PgimRelacionFirmaDTO> lPgimRelacionFirmaDTO = this.relacionFirmaRepository
				.listarRelacionFirmaPorIdRelacion(idRelacionPaso, flObligatorio);
		
		String sValidacion = "";

		if (lPgimRelacionFirmaDTO.size() > 0) {

			Documentos documentos = documentoService
					.obtenerExpedienteDocumentoSiged(pgimInstanciaProces.getNuExpedienteSiged(), "1", auditoriaDTO);
			if (documentos == null || !documentos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)
					|| documentos.getListaDocumento().size() == 0) {
				throw new PgimException(TipoResultado.WARNING,
						"No se encontró la lista de documentos SIGED para validar si fueron firmados.");
			}
			
			for (PgimRelacionFirmaDTO pgimRelacionFirmaDTO : lPgimRelacionFirmaDTO) {

				List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
						pgimInstanciaProces.getIdInstanciaProceso(), pgimRelacionFirmaDTO.getIdSubcatDocumento());				

				for (PgimDocumentoDTO documentoDTO : lPgimDocumentoDTO) {

					boolean fDocFirmado = false;
					Documento docSigedNoFirmado = null;

					for (Documento documentoSiged : documentos.getListaDocumento()) {						

						if (documentoSiged.getIdDocumento().equals(documentoDTO.getCoDocumentoSiged().toString())) {
							docSigedNoFirmado = documentoSiged;

							if (documentoSiged.getArchivos() != null) {
								for (Archivo archivo : documentoSiged.getArchivos()) {
									if (archivo.getFirmaDigitalSiged() != null) {
										for (FirmaDigitalSiged firmaDigitalSiged : archivo.getFirmaDigitalSiged()) {
											if (firmaDigitalSiged.getIdUsuarioFirma().equals(auditoriaDTO.getCoUsuarioSiged())) {
												fDocFirmado = true;
												docSigedNoFirmado = null;
											}
										}
									}
								}
							}

							break;
						}

					}

					if (!fDocFirmado && docSigedNoFirmado != null) {
						if (sValidacion.equals(""))
							sValidacion = String.format("+ %s <b>%s</b> (%s)", 
										pgimRelacionFirmaDTO.getDescNoSubcatDocumento(),
										docSigedNoFirmado.getNroDocumento(),
										pgimRelacionFirmaDTO.getDescNoCategoriaDocumento());							
							
						else
							sValidacion = sValidacion + "<br/>"+ String.format("+ %s <b>%s</b> (%s)", 
										pgimRelacionFirmaDTO.getDescNoSubcatDocumento(),
										docSigedNoFirmado.getNroDocumento(),
										pgimRelacionFirmaDTO.getDescNoCategoriaDocumento());
					}

				}
			}
		}
		
		return sValidacion;
	}	

	@Override
	@Transactional(readOnly = false)
	public void enviarAlerta(PgimInstanciaProces pgimInstanciaProces, PgimInstanciaPaso pgimInstanciaPaso,
			AuditoriaDTO auditoriaDTO) {
		Map<String, String> lNoUsuarios = this.enviarAlertaTransicionPaso(pgimInstanciaProces, pgimInstanciaPaso,
				auditoriaDTO);
		this.enviarAlertaParaInteresados(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO, lNoUsuarios);
		this.enviarAlertaParaResponsables(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO, lNoUsuarios);
	}

	@Override
	@Transactional(readOnly = false)
	public Map<String, String> enviarAlertaTransicionPaso(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {
		final PgimEqpInstanciaPro pgimEqpInstanciaProOrigen = this.equipoInstanciaProcesoRepository
				.findById(pgimInstanciaPaso.getPersonaEqpOrigen().getIdEquipoInstanciaPro()).orElse(null);

		final PgimEqpInstanciaPro pgimEqpInstanciaProDestino = this.equipoInstanciaProcesoRepository
				.findById(pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro()).orElse(null);

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);

		Long idInstanciaPaso = pgimInstanciaPaso.getIdInstanciaPaso();

		String noUsuarioOrigen = null;
		String noUsuarioDestino = null;
		PgimPersonalOsi pgimPersonalOsiOrigen = null;
		PgimPersonalContrato pgimPersonalContratoOrigen = null;

		PgimPersonalOsi pgimPersonalOsiDestino = null;
		PgimPersonalContrato pgimPersonalContratoDestino = null;

		Map<String, String> lNoUsuarios = new HashMap<>();

		if (pgimEqpInstanciaProOrigen.getPgimPersonalOsi() != null) {
			pgimPersonalOsiOrigen = this.personalOsiRepository
					.findById(pgimEqpInstanciaProOrigen.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);

			noUsuarioOrigen = pgimPersonalOsiOrigen.getNoUsuario();
		} else {
			if (pgimEqpInstanciaProOrigen.getPgimPersonalContrato() != null) {
				pgimPersonalContratoOrigen = this.personalContratoRepository
						.findById(pgimEqpInstanciaProOrigen.getPgimPersonalContrato().getIdPersonalContrato())
						.orElse(null);
				noUsuarioOrigen = pgimPersonalContratoOrigen.getNoUsuario();
			}
		}

		if (pgimEqpInstanciaProDestino.getPgimPersonalOsi() != null) {
			pgimPersonalOsiDestino = this.personalOsiRepository
					.findById(pgimEqpInstanciaProDestino.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);

			noUsuarioDestino = pgimPersonalOsiDestino.getNoUsuario();
		} else {
			if (pgimEqpInstanciaProDestino.getPgimPersonalContrato() != null) {
				pgimPersonalContratoDestino = this.personalContratoRepository
						.findById(pgimEqpInstanciaProDestino.getPgimPersonalContrato().getIdPersonalContrato())
						.orElse(null);
				noUsuarioDestino = pgimPersonalContratoDestino.getNoUsuario();
			}
		}
		try {
			Long idProceso = this.instanciaProcesRepository.findById(pgimInstanciaProces.getIdInstanciaProceso())
					.orElse(null).getPgimProceso().getIdProceso();

			lNoUsuarios.put("USUORIGEN", noUsuarioOrigen);
			lNoUsuarios.put("USUDESTINO", noUsuarioDestino);

			if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)
					|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)
					|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_PROGRAMACION)
					|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)
					|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_RANKING_RIESGOS)
					|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_CONFIGURACION_RIESGO)) {
				this.alertaService.enviarAlertaTransicionPaso(pgimInstanciaProces, pgimRelacionPaso, lNoUsuarios,
						ConstantesUtil.PARAM_ALERTA_PASO, idProceso, idInstanciaPaso, auditoriaDTO);
			}
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			
			throw new PgimException(TipoResultado.ERROR, String.format("Ha ocurrido un problema al crear la alerta por las acciones en la PGIM"));
		}
		return lNoUsuarios;
	}

	@Override
	public void enviarAlertaParaResponsables(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO, Map<String, String> lNoUsuarios) {

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);
		Long idTipoAlerta = 0L;
		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiResponsable = null;
		lPgimEqpInstanciaProDTOOsiResponsable = this.equipoInstanciaProcesoRepository
				.listarPersonalOsiResponsable(pgimInstanciaProces.getIdInstanciaProceso());

		List<String> lNoResposables = new ArrayList<String>();
		for (PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO : lPgimEqpInstanciaProDTOOsiResponsable) {
			if (!lNoResposables.contains(pgimEqpInstanciaProDTO.getDescNoUsuario())) {
				lNoResposables.add(pgimEqpInstanciaProDTO.getDescNoUsuario());
			}
		}

		// Remover si existe(n) lo(s) destinatarios en la lista de interesados
		lNoResposables.remove(lNoUsuarios.get("USUDESTINO"));

		Long idProceso = this.instanciaProcesRepository.findById(pgimInstanciaProces.getIdInstanciaProceso())
				.orElse(null).getPgimProceso().getIdProceso();

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
			this.alertaService.enviarAlertaParaResponsables(pgimInstanciaProces, pgimRelacionPaso, pgimInstanciaPaso.getIdInstanciaPaso(), lNoResposables,
					idTipoAlerta, auditoriaDTO, idProceso, lNoUsuarios);
		}
	}

	@Override
	public void enviarAlertaParaInteresados(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO, Map<String, String> lNoUsuarios) {

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);
		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiResponsable = null;
		lPgimEqpInstanciaProDTOOsiResponsable = this.equipoInstanciaProcesoRepository
				.listarPersonalOsiInteresado(pgimInstanciaProces.getIdInstanciaProceso());

		List<String> lNoInteresados = new ArrayList<String>();
		for (PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO : lPgimEqpInstanciaProDTOOsiResponsable) {
			if (!lNoInteresados.contains(pgimEqpInstanciaProDTO.getDescNoUsuario())) {
				lNoInteresados.add(pgimEqpInstanciaProDTO.getDescNoUsuario());
			}
		}
		// Remover si existe(n) lo(s) destinatarios en la lista de interesados
		lNoInteresados.remove(lNoUsuarios.get("USUDESTINO"));

		Long idProceso = this.instanciaProcesRepository.findById(pgimInstanciaProces.getIdInstanciaProceso())
				.orElse(null).getPgimProceso().getIdProceso();

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)
				|| idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
			this.alertaService.enviarAlertaParaInteresados(pgimInstanciaProces, pgimRelacionPaso, lNoInteresados,
					ConstantesUtil.PARAM_ALERTA_PASO, auditoriaDTO, idProceso, lNoUsuarios, pgimInstanciaPaso);
		}

	}

	@Override
	public ExpedienteOutRO enviarExpedienteSiged(PgimInstanciaProces pgimInstanciaProces,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO, boolean reasignacion) throws Exception {
		
		ExpedienteOutRO expedienteOutROFinal = new ExpedienteOutRO();
		expedienteOutROFinal.setResultCode(ConstantesUtil.PARAM_RESULTADO_SUCCESS);

		final PgimEqpInstanciaPro pgimEqpInstanciaProOrigen = this.equipoInstanciaProcesoRepository
				.findById(pgimInstanciaPaso.getPersonaEqpOrigen().getIdEquipoInstanciaPro()).orElse(null);

		final PgimEqpInstanciaPro pgimEqpInstanciaProDestino = this.equipoInstanciaProcesoRepository
				.findById(pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro()).orElse(null);

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);

		String noUsuarioOrigen = null;
		PgimPersonalOsi pgimPersonalOsiOrigen = null;
		PgimPersonalContrato pgimPersonalContratoOrigen = null;

		if (pgimEqpInstanciaProOrigen.getPgimPersonalOsi() != null) {
			pgimPersonalOsiOrigen = this.personalOsiRepository
					.findById(pgimEqpInstanciaProOrigen.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);

			noUsuarioOrigen = pgimPersonalOsiOrigen.getNoUsuario();
		} else {
			if (pgimEqpInstanciaProOrigen.getPgimPersonalContrato() != null) {
				pgimPersonalContratoOrigen = this.personalContratoRepository
						.findById(pgimEqpInstanciaProOrigen.getPgimPersonalContrato().getIdPersonalContrato())
						.orElse(null);

				noUsuarioOrigen = pgimPersonalContratoOrigen.getNoUsuario();
			}
		}

		if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro() != null) {

			PgimPersonalOsi pgimPersonalOsi = null;
			PgimPersonalContrato pgimPersonalContrato = null;
			Long coUsuarioSigedRemitente = null;
			Long coUsuarioSigedDestinatario = null;

			if (pgimEqpInstanciaProOrigen.getPgimPersonalOsi() != null) {
				pgimPersonalOsi = this.personalOsiRepository
						.findById(pgimEqpInstanciaProOrigen.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);

				coUsuarioSigedRemitente = new Long(
						this.obtenerCoUsuarioSigedPorNombreUsuario(pgimPersonalOsi.getNoUsuario()));

			} else if (pgimEqpInstanciaProOrigen.getPgimPersonalContrato() != null) {
				pgimPersonalContrato = this.personalContratoRepository
						.findById(pgimEqpInstanciaProOrigen.getPgimPersonalContrato().getIdPersonalContrato())
						.orElse(null);

				coUsuarioSigedRemitente = new Long(
						this.obtenerCoUsuarioSigedPorNombreUsuario(pgimPersonalContrato.getNoUsuario()));

			} else {
				throw new PgimException(TipoResultado.WARNING, "La asignación del paso no ha definido una persona origen"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			}

			if (pgimEqpInstanciaProDestino.getPgimPersonalOsi() != null) {
				pgimPersonalOsi = this.personalOsiRepository
						.findById(pgimEqpInstanciaProDestino.getPgimPersonalOsi().getIdPersonalOsi()).orElse(null);

				coUsuarioSigedDestinatario = new Long(
						this.obtenerCoUsuarioSigedPorNombreUsuario(pgimPersonalOsi.getNoUsuario()));

			} else if (pgimEqpInstanciaProDestino.getPgimPersonalContrato() != null) {
				pgimPersonalContrato = this.personalContratoRepository
						.findById(pgimEqpInstanciaProDestino.getPgimPersonalContrato().getIdPersonalContrato())
						.orElse(null);

				coUsuarioSigedDestinatario = new Long(
						this.obtenerCoUsuarioSigedPorNombreUsuario(pgimPersonalContrato.getNoUsuario()));
				
			} else {
				throw new PgimException(
						TipoResultado.WARNING, "La asignación del paso no ha definido una persona destino"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
			}

			final String mensajeAsunto = String.format("PGIM: %s ---> %s",
					pgimRelacionPaso.getPasoProcesoOrigen().getNoPasoProceso(),
					pgimRelacionPaso.getPasoProcesoDestino().getNoPasoProceso());

			// Cambio en el manejo de la auditoría para los casos de reenvio
			AuditoriaDTO auditoriaDTOTmp = auditoriaDTO;
			if (reasignacion) {
				auditoriaDTOTmp.setCoUsuarioSiged(coUsuarioSigedRemitente.toString());
				auditoriaDTOTmp.setUsername(noUsuarioOrigen);
			}
			try {
				if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro().equals(ConstantesUtil.PARAM_REENVIAR)
						|| (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro()
								.equals(ConstantesUtil.PARAM_APROBAR) && reasignacion)) {

					boolean flagReenvioSimple = true;

					// Verificamos si se requerirá revertir, para el caso de las reasignaciones
					if (reasignacion && pgimInstanciaPaso.getInstanciaPasoPadre() != null
							&& pgimInstanciaPaso.getInstanciaPasoPadre().getIdInstanciaPaso() != null) {

						PgimInstanciaPaso pgimInstanciaPasoPrevio = this.instanciaPasoRepository
								.findById(pgimInstanciaPaso.getInstanciaPasoPadre().getIdInstanciaPaso()).orElse(null);
						PgimRelacionPaso pgimRelacionPasoPrevio = this.relacionPasoRepository
								.findById(pgimInstanciaPasoPrevio.getPgimRelacionPaso().getIdRelacionPaso())
								.orElse(null);

						if (pgimRelacionPasoPrevio.getFlRequiereAprobacion()
								.equals(ConstantesUtil.IND_REQ_APROBACION_SI)
								&& pgimRelacionPasoPrevio.getTipoAccionSiged().getIdValorParametro()
										.equals(ConstantesUtil.PARAM_REENVIAR)) {
							flagReenvioSimple = false;
						}

					}

					if (flagReenvioSimple) {
						// Acción regular reenvio simple
						this.reenviarExpediente(pgimInstanciaProces, coUsuarioSigedRemitente,
								coUsuarioSigedDestinatario, mensajeAsunto, pgimInstanciaPaso, pgimRelacionPaso,
								auditoriaDTOTmp);
					} else {
						// obtenemos el idUsuarioSiged que remitió para aprobación en la trazabilidad
						// anterior
						Long idRemitenteSigedInicial = this.obtenerIdRemitentePorExpediente(
								pgimInstanciaProces.getNuExpedienteSiged(), auditoriaDTO);
						if (idRemitenteSigedInicial == 0) {
							throw new PgimException(TipoResultado.WARNING, "No se pudo obtener el usuario SIGED remitente (inicial)"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
						}

						// Devolvemos el expediente
						this.devolverExpediente(pgimInstanciaProces, mensajeAsunto, pgimInstanciaPaso, auditoriaDTO,
								auditoriaDTOTmp);

						// Reenviar el expediente al nuevo usuario destino
						pgimRelacionPaso.setFlRequiereAprobacion(ConstantesUtil.IND_REQ_APROBACION_SI);
						auditoriaDTOTmp.setCoUsuarioSiged(idRemitenteSigedInicial.toString());
						// auditoriaDTOTmp.setUsername(pgimPersonalPrevio.getNoUsuario());

						this.reenviarExpediente(pgimInstanciaProces, idRemitenteSigedInicial,
								coUsuarioSigedDestinatario, mensajeAsunto, pgimInstanciaPaso, pgimRelacionPaso,
								auditoriaDTOTmp);

					}
				} else if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_APROBAR)) {
					final ExpedienteAprobadoIn expedienteAprobadoIn = new ExpedienteAprobadoIn();
					expedienteAprobadoIn.setNumeroExpediente(pgimInstanciaProces.getNuExpedienteSiged());
					expedienteAprobadoIn.setAsunto(mensajeAsunto);
					expedienteAprobadoIn.setContenido(pgimInstanciaPaso.getDeMensaje());
					expedienteAprobadoIn.setDestinatario(coUsuarioSigedDestinatario.toString());
					expedienteAprobadoIn.setAprobacion(pgimRelacionPaso.getFlRequiereAprobacion());
					final ExpedienteOutRO expedienteOutRO = this.documentoService
							.aprobarExpedienteSiged(expedienteAprobadoIn, auditoriaDTOTmp);
					expedienteOutROFinal = expedienteOutRO;

				} else if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_DEVOLVER)) {

					// PgimPersonal pgimPersonalPrevio = new PgimPersonal();
					// pgimPersonalPrevio = this.obtenerUsuarioEnvioPrevio_old(pgimInstanciaPaso,
					// true);

					// obtenemos el ID siged del usaurio remitente (antes de realizar la devolución)
					Long idRemitenteSigedInicial = this
							.obtenerIdRemitentePorExpediente(pgimInstanciaProces.getNuExpedienteSiged(), auditoriaDTO);

					// Verificar si el usuario destino es el mismo que el que originalmente envió el
					// expediente
					/*
					 * if (!pgimPersonalPrevio.getCoUsuarioSiged().equals(0L) &&
					 * !pgimPersonalPrevio.getCoUsuarioSiged().equals(coUsuarioSigedDestinatario)) {
					 */
					if (!idRemitenteSigedInicial.equals(0L)
							&& !idRemitenteSigedInicial.equals(coUsuarioSigedDestinatario)) {
						// Devolver expediente
						this.devolverExpediente(pgimInstanciaProces, mensajeAsunto, pgimInstanciaPaso, auditoriaDTO,
								auditoriaDTOTmp);

						// Reenviar el expediente al nuevo usuario destino
						pgimRelacionPaso.setFlRequiereAprobacion(ConstantesUtil.IND_REQ_APROBACION_NO);
						// auditoriaDTOTmp.setCoUsuarioSiged(pgimPersonalPrevio.getCoUsuarioSiged().toString());
						auditoriaDTOTmp.setCoUsuarioSiged(idRemitenteSigedInicial.toString());
						// auditoriaDTOTmp.setUsername(pgimPersonalPrevio.getNoUsuario());
						this.reenviarExpediente(pgimInstanciaProces, idRemitenteSigedInicial,
								// this.reenviarExpediente(pgimInstanciaProces,
								// pgimPersonalPrevio.getCoUsuarioSiged(),
								coUsuarioSigedDestinatario, mensajeAsunto, pgimInstanciaPaso, pgimRelacionPaso,
								auditoriaDTOTmp);
					} else {
						// Acción regular es decir el usuario destino de la devolución es el mismo que
						// envió el expediente
						this.devolverExpediente(pgimInstanciaProces, mensajeAsunto, pgimInstanciaPaso, auditoriaDTO,
								auditoriaDTOTmp);
					}

				} else if (pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_EXCEPCION)) {
					// Se ha adecuado el siged, para permitir las excepciones, en las que el flujo
					// opta por una camino alterno
					// throw new Exception(
					// "La relación de paso requiere que se concluyan adecuaciones en el Siged que
					// actualmente están siendo implementadas");
					// Acción regular reenvio simple
					pgimRelacionPaso.setFlRequiereAprobacion(ConstantesUtil.IND_REQ_APROBACION_NO);
					this.reenviarExpediente(pgimInstanciaProces, coUsuarioSigedRemitente, coUsuarioSigedDestinatario,
							mensajeAsunto, pgimInstanciaPaso, pgimRelacionPaso, auditoriaDTOTmp);
				}
			} catch (final PgimException e) {
				expedienteOutROFinal.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				expedienteOutROFinal.setMessage(e.getMensaje());
				expedienteOutROFinal.setDetalleExcepcionDTO(e.getDetalleExcepcionDTO());
				log.error(e.getMessage(), e);
			} catch (final Exception e) {
				expedienteOutROFinal.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				expedienteOutROFinal.setMessage(e.getMessage());
				log.error(e.getMessage(), e);
			}
		}
		return expedienteOutROFinal;
	}

	private Long obtenerIdRemitentePorExpediente(String nroExp, AuditoriaDTO auditoriaDTO) throws Exception {

		Long idRemitente = 0L;
		Documento documentoSeleccionado = null;

		Documentos documentos = documentoService.obtenerExpedienteDocumentoSiged(nroExp, "0", auditoriaDTO);

		if (documentos != null && documentos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)
				&& documentos.getListaDocumento().size() > 0) {
			
			for (Documento documento : documentos.getListaDocumento()) {				
				if(documento.getNroExpediente().equals(nroExp) ) {
					documentoSeleccionado = documento;
					break;
				}
			}
			
			if(documentoSeleccionado != null) {

				ListarTrazabilidadDocumentosResponse trazabilidad = documentoService
						.listarTrazabilidad(documentoSeleccionado.getIdDocumento(), auditoriaDTO);
	
				if (trazabilidad != null
						&& trazabilidad.getResultCode().equals(new BigInteger(ConstantesUtil.PARAM_RESULTADO_SUCCESS))
						&& trazabilidad.getTrazabilidadDocumentoOutRO().size() > 0) {
					idRemitente = Long
							.parseLong(trazabilidad.getTrazabilidadDocumentoOutRO().get(0).getIdRemitente().toString());
				}
			}

		}

		return idRemitente;

	}

	private void reenviarExpediente(PgimInstanciaProces pgimInstanciaProces, Long coUsuarioSigedRemitente,
			Long coUsuarioSigedDestinatario, String mensajeAsunto, PgimInstanciaPaso pgimInstanciaPaso,
			PgimRelacionPaso pgimRelacionPaso, AuditoriaDTO auditoriaDTOTmp) throws Exception {
		final ExpedienteReenvio expedienteReenvio = new ExpedienteReenvio();
		expedienteReenvio.setNumeroExpediente(pgimInstanciaProces.getNuExpedienteSiged());
		expedienteReenvio.setIdRemitente(coUsuarioSigedRemitente);
		expedienteReenvio.setIdDestinatario(coUsuarioSigedDestinatario);
		expedienteReenvio.setAsunto(mensajeAsunto);
		expedienteReenvio.setContenido(pgimInstanciaPaso.getDeMensaje());
		expedienteReenvio.setAprobacion(pgimRelacionPaso.getFlRequiereAprobacion());
		
		final ExpedienteOutRO expedienteOutRO = this.documentoService.reenviarExpedienteSiged(expedienteReenvio,
				auditoriaDTOTmp);
		
		if (!expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			final String mensajeExcepcionControlada = String.format(
					"Ha ocurrido un problema al reenviar el expediente Siged (Error: %s, Descripción: %s)",
					expedienteOutRO.getErrorCode(), expedienteOutRO.getMessage());

			DetalleExcepcionDTO detalleExcepcionDTO = this.obtenerDetalleExcepcion(pgimInstanciaProces, expedienteOutRO.getDetalleExcepcionDTO(), 
					"Reenviar expediente", "", null);
			
			throw new PgimException(TipoResultado.ERROR, mensajeExcepcionControlada, detalleExcepcionDTO); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}
	}
	
	/**
	 * Permite realizar el reenvío con subflujo del expediente Siged,
	 * haciendo uso del método reenviarSubflujo del Siged-Rest-Old
	 * 
	 * @param numeroExpedienteSiged Número del expediente Siged
	 * @param noUsuarioSigedRemitente Nombre de usuario windows del remitente
	 * @param noUsuarioSigedDestinatario	Nombre de usuario windows del destinatario del flujo principal
	 * @param lUsuarioSigedDestinatarioSF	Lista de nombres de usuario windows de los destinatarios de subflujos secundarios
	 * @param asuntoAsignacion	Título del envío a través del Siged
	 * @param mensajeAsignacion Mensaje de la asignación a través del Siged
	 * @param auditoriaDTO
	 * @throws Exception
	 */
	@Override
	public void reenviarSubflujoExpediente(String numeroExpedienteSiged, String noUsuarioSigedRemitente,
			String noUsuarioSigedDestinatario, List<String> lUsuarioSigedDestinatarioSF, String asuntoAsignacion, 
			String mensajeAsignacion, AuditoriaDTO auditoriaDTO) throws Exception {
		
		String keyEncAccesoSigedRest = propertiesConfig.getKeyEncAccesoSigedRest();
		
		final ReenviarSubFlujoInRO  reenviarSubFlujoInRO = new ReenviarSubFlujoInRO ();    	  
        reenviarSubFlujoInRO.setKeyval(ReenviarSubflujo.getKeyval(ConstantesUtil.PARAM_appNameInvokes, keyEncAccesoSigedRest));
        reenviarSubFlujoInRO.setNombreAppInvoca(ConstantesUtil.PARAM_appNameInvokes);
        reenviarSubFlujoInRO.setUsuarioRemitente(noUsuarioSigedRemitente);
        reenviarSubFlujoInRO.setNumeroExpediente(numeroExpedienteSiged);
        reenviarSubFlujoInRO.setAsunto(asuntoAsignacion);
        reenviarSubFlujoInRO.setContenido(mensajeAsignacion);
        
        List<UsuarioInRO> usuarios=new  ArrayList<UsuarioInRO> ();
        
        // USUARIO PRINCIPAL 
        UsuarioInRO usuario =new UsuarioInRO(); 
	    usuario.setUsuario(noUsuarioSigedDestinatario);
	    usuarios.add(usuario);
        
        for (String username : lUsuarioSigedDestinatarioSF) {
        	// USUARIO SUBFLUJO		    
		    usuario = new UsuarioInRO();    
		    usuario.setUsuario(username);
		    usuarios.add(usuario);
        }
        
        reenviarSubFlujoInRO.setUsuarios(usuarios);        
		
		final BaseOutRO baseOutRO = this.documentoService.reenviarSubflujoExpedienteSIGED_old(reenviarSubFlujoInRO, auditoriaDTO);
		
		if (!baseOutRO.getResultCode().toString().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			final String mensajeExcepcionControlada = String.format(
					"Ha ocurrido un problema al reenviar con subflujo el expediente Siged (Error: %s, Descripción: %s)",
					baseOutRO.getErrorCode(), baseOutRO.getMessage());
			throw new PgimException(TipoResultado.ERROR, mensajeExcepcionControlada); 
		}
	}

	private void devolverExpediente(PgimInstanciaProces pgimInstanciaProces, String mensajeAsunto,
			PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO, AuditoriaDTO auditoriaDTOTmp)
			throws Exception {
		final DevolverExpedienteInRO devolverExpedienteInRO = new DevolverExpedienteInRO();
		devolverExpedienteInRO.setNroExpediente(pgimInstanciaProces.getNuExpedienteSiged());
		devolverExpedienteInRO.setAsunto(mensajeAsunto);
		devolverExpedienteInRO.setMotivo(pgimInstanciaPaso.getDeMensaje());
		devolverExpedienteInRO.setIdUsuario(auditoriaDTO.getCoUsuarioSiged());
		final DevolverExpedienteOutRO devolverExpedienteOutRO = this.documentoService
				.devolverExpedienteSiged(devolverExpedienteInRO, auditoriaDTOTmp);
		if (!devolverExpedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			final String mensajeExcepcionControlada = String.format(
					"Ha ocurrido un problema al reenviar el expediente Siged (Error: %s, Descripción: %s)",
					devolverExpedienteOutRO.getErrorCode(), devolverExpedienteOutRO.getMessage());
			throw new PgimException(TipoResultado.ERROR, mensajeExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
		}
	}

	@Override
	public PgimEqpInstanciaProDTO obtenerPersonaEqpPorRolDestino(final Long idInstanciaProceso,
			final Long idRelacionPaso, final Long idContrato) {

		final String palabra = "";
		PgimEqpInstanciaProDTO pgimEqpInstanciaProDTOElegido = null;
		final List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOTotal = new ArrayList<PgimEqpInstanciaProDTO>();

		final PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository.findById(idRelacionPaso).orElse(null);
		final Long idRolProceso = pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso();
		final Long idPasoOrigen = pgimRelacionPaso.getPasoProcesoOrigen().getIdPasoProceso();

		if((idRolProceso.equals(ConstantesUtil.PROCESO_ROL_ADMINISTRATIVO_TASTEM) ||
		idRolProceso.equals(ConstantesUtil.PROCESO_ROL_ADMINISTRATIVO_COACTIVA)) && 
		(idPasoOrigen.equals(ConstantesUtil.PARAM_PROCESO_PASO_CONTINUAR_PAS) || 
		idPasoOrigen.equals(ConstantesUtil.PARAM_PROCESO_PASO_DERIVAR_EJECUTORIA_COACTIVA)
		)){
			PgimValorParametroDTO paramUsuarioAsistente = this.valorParametroRepository.obtenerValorParametroPorId(ConstantesUtil.PARAM_NO_USUARIO_TASTEM);
		
			if(idRolProceso.equals(ConstantesUtil.PROCESO_ROL_ADMINISTRATIVO_COACTIVA)){
				paramUsuarioAsistente  = this.valorParametroRepository.obtenerValorParametroPorId(ConstantesUtil.PARAM_NO_USUARIO_COACTIVA);
			}
            
            PgimPersonaosiAuxDTO pgimPersonaosiAuxDTO = this.flujoTrabajoService.obtenerPersonalOsiNombreUsuarioWindows(paramUsuarioAsistente.getNoValorParametro());

			String nombre = pgimPersonaosiAuxDTO.getNoPersona() != null ? pgimPersonaosiAuxDTO.getNoPersona() : "";
			String apPaterno = pgimPersonaosiAuxDTO.getApPaterno() != null ? pgimPersonaosiAuxDTO.getApPaterno() : "";
			String apMaterno = pgimPersonaosiAuxDTO.getApMaterno() != null ? pgimPersonaosiAuxDTO.getApMaterno() : "";

			String NombreCompleto = nombre+ ' ' + apPaterno + ' ' + apMaterno;

			PgimEqpInstanciaProDTO pgimEqpInstanciaProDTOAst = new PgimEqpInstanciaProDTO();
			pgimEqpInstanciaProDTOAst.setIdInstanciaProceso(idInstanciaProceso);
			pgimEqpInstanciaProDTOAst.setIdRolProceso(idRolProceso);
			pgimEqpInstanciaProDTOAst.setIdPersonalOsi(pgimPersonaosiAuxDTO.getIdPersonalOsi());
			pgimEqpInstanciaProDTOAst.setFlEsResponsable("0");
			pgimEqpInstanciaProDTOAst.setDescNoCompletoPersona(NombreCompleto);
			pgimEqpInstanciaProDTOElegido = pgimEqpInstanciaProDTOAst;
		}else{

			List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOOsiRol = null;
			lPgimEqpInstanciaProDTOOsiRol = this.equipoInstanciaProcesoRepository
					.listarPersonalAsignableOsiConRol(idInstanciaProceso, idRolProceso, palabra);
	
			lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOOsiRol);
	
			if (idContrato != 0
					&& !pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getFlSoloOsinergmin().equals("1")) {
				// - Indica que sí hay contrato, por tanto se obtienen las personas del contrato
				// incluidas o no en él.
	
				List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTOContraRol = null;
				lPgimEqpInstanciaProDTOContraRol = this.equipoInstanciaProcesoRepository
						.listarPersonalAsignableContraConRol(idInstanciaProceso, idRolProceso, palabra);
	
				lPgimEqpInstanciaProDTOTotal.addAll(lPgimEqpInstanciaProDTOContraRol);
			}
	
			
	
			if (lPgimEqpInstanciaProDTOTotal.size() > 0) {
				pgimEqpInstanciaProDTOElegido = lPgimEqpInstanciaProDTOTotal.get(0);
			}
		}


		return pgimEqpInstanciaProDTOElegido;
	}

	@Override
	public Page<PgimInstanPasoAuxDTO> obtenerInstanciaPasoAuxPorInstanciaProceso(final Long idInstanciaProceso, final Pageable paginador) throws Exception {

		Page<PgimInstanPasoAuxDTO> pPgimInstanPasoAuxDTO = this.instanciaPasoAuxRepository.obtenerInstanciaPasoAuxPorInstanciaProceso(idInstanciaProceso, paginador);

		for(PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO: pPgimInstanPasoAuxDTO.getContent()){

			String fotoDestino = this.perfilUserService.obtenerFotoPersona(pgimInstanPasoAuxDTO.getIdPersonaDestino());
			String fotoOrigen = this.perfilUserService.obtenerFotoPersona(pgimInstanPasoAuxDTO.getIdPersonaOrigen());
			String fotoBase64Destino = null;
			String fotoBase64Origen = null;

			/* 
			 * ----------------------------------------
			 * Foto de la persona destino
			 * ----------------------------------------
			 */
			if(fotoDestino != ""){

				if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Osinergmin")){
					fotoBase64Destino = "data:image/jpeg;base64," + fotoDestino;
				} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Supervisora")){
					fotoBase64Destino = "assets/images/ico_es.png";
				}

				pgimInstanPasoAuxDTO.setDescFotoBase64Destino(fotoBase64Destino);

			}else{
				if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Osinergmin")){
					fotoBase64Destino = "assets/images/ico_user.png";
				} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaDestino().equals("Supervisora")){
					fotoBase64Destino = "assets/images/ico_es.png";
				}
				pgimInstanPasoAuxDTO.setDescFotoBase64Destino(fotoBase64Destino);
			}

			/* 
			 * ----------------------------------------
			 * Foto de la persona origen
			 * ----------------------------------------
			 */
			if(fotoOrigen != ""){
				if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Osinergmin")){
					fotoBase64Origen = "data:image/jpeg;base64," + fotoOrigen;
				} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Supervisora")){
					fotoBase64Origen = "assets/images/ico_es.png";
				}
				pgimInstanPasoAuxDTO.setDescFotoBase64Origen(fotoBase64Origen);

			}else{
				if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Osinergmin")){
					fotoBase64Origen = "assets/images/ico_user.png";
				} else if(pgimInstanPasoAuxDTO.getDeEntidadPersonaOrigen().equals("Supervisora")){
					fotoBase64Origen = "assets/images/ico_es.png";
				}
				pgimInstanPasoAuxDTO.setDescFotoBase64Origen(fotoBase64Origen);
			}
		}

		return pPgimInstanPasoAuxDTO;
	}

	@Override
	public List<PgimInstanPasoAuxDTO> listarPorPersonaDestino(String palabra) {
		List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTODestino = this.instanciaPasoAuxRepository
				.listarPorPersonaDestino(palabra);

		return lPgimInstanPasoAuxDTODestino;
	}
	
	@Override
	public List<PgimInstanPasoAuxDTO> listarPorPersonaDestinoDelRepDetalladoFisc(Long idContrato, String palabra) {

		final List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTO = new ArrayList<PgimInstanPasoAuxDTO>();

		List<PgimInstanPasoAuxDTO> contrato = null;
		contrato = this.instanciaPasoAuxRepository.listarPersonaDestinoPorContrato(idContrato, palabra);
		lPgimInstanPasoAuxDTO.addAll(contrato);

		List<PgimInstanPasoAuxDTO> osinergmin = null; 
		osinergmin = this.instanciaPasoAuxRepository.listarPorPersonaOsiDestino(palabra);
		lPgimInstanPasoAuxDTO.addAll(osinergmin);

		return lPgimInstanPasoAuxDTO;
	}
	
	@Override
	public List<PgimInstanPasoAuxDTO> listarPorPersonaOsiDestinoDelRepDetalladoFisc(String palabra) {

		final List<PgimInstanPasoAuxDTO> lPgimInstanPasoAuxDTO = new ArrayList<PgimInstanPasoAuxDTO>();
		
		List<PgimInstanPasoAuxDTO> osinergmin = null; 
		osinergmin = this.instanciaPasoAuxRepository.listarPorPersonaOsiDestino(palabra);

		lPgimInstanPasoAuxDTO.addAll(osinergmin);

		List<PgimInstanPasoAuxDTO> contrato = null; 
		contrato = this.instanciaPasoAuxRepository.listarPorPersonaContratoDestino(palabra);

		lPgimInstanPasoAuxDTO.addAll(contrato);

		return lPgimInstanPasoAuxDTO;
	}

	public String obtenerCoUsuarioSigedPorNombreUsuario(String noUsuario) throws Exception {
		String coUsuarioSiged = "";

		Usuario usuario = new Usuario(noUsuario);

		UsuarioSiged usuariosiged = this.personaService.obtenerUsuarioSiged(usuario);

		int nroPersonalOsi = usuariosiged.getListaUsuarios().size();

		Usuarios usuarios = null;

		if (!usuariosiged.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
			throw new PgimException("error", usuariosiged.getMessage());
		}
		if (nroPersonalOsi == 1) {
			usuarios = usuariosiged.getListaUsuarios().get(0);
		}

		coUsuarioSiged = usuarios.getUsuarios().get(0).getIdUsuario();
		return coUsuarioSiged;
	}

	@Override
	public PgimPersonaDTO obtenerPersonaPorNombreUsuario(String noUsuario) throws Exception {
		PgimPersonaDTO pgimPersonaDTO = null;
		Long idPersona = null;

		List<PgimPersonalOsiDTO> lPgimPersonalOsiDTO = this.personalOsiRepository
				.obtenerPersonalOsiPorUsuario(noUsuario);

		int nroPersonalOsi = lPgimPersonalOsiDTO.size();

		if (nroPersonalOsi > 0) {
			PgimPersonalOsiDTO pgimPersonalOsiDTO = lPgimPersonalOsiDTO.get(0);
			idPersona = pgimPersonalOsiDTO.getIdPersona();
		} else {
			List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = this.personalContratoRepository
					.obtenerPersonalContratoPorUsuario(noUsuario);

			int nroPersonalContrato = lPgimPersonalContratoDTO.size();

			if (nroPersonalContrato >= 1) {
				idPersona = lPgimPersonalContratoDTO.get(0).getIdPersona();
			}
		}

		pgimPersonaDTO = this.personaRepository.obtenerPersonaNaturalPorId(idPersona);

		return pgimPersonaDTO;
	}

	@Override
	@Transactional(readOnly = false)
	public void asegurarInteresEnInstanciaProceso(Long idInstanciaPaso, Long idPersonaEqpDestino,
			String flPersDestinoInteresada, AuditoriaDTO auditoriaDTO) throws Exception {

		String mensajeExcepcionControlada = null;
		String noUsuario = auditoriaDTO.getUsername();

		PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);
		Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = this.obtenerPersonalEqpInteresado(idInstanciaProceso,
				noUsuario);

		Boolean estaInteresadaActualmente = false;
		if (lPgimEqpInstanciaProDTO.size() > 0) {
			estaInteresadaActualmente = true;
		}

		PgimEqpInstanciaPro pgimEqpInstanciaPro = null;
		if (flPersDestinoInteresada.equals(ConstantesUtil.IND_ACTIVO)) {

			if (!estaInteresadaActualmente) {
				// Entonces se debe crear le interés de la persona destino.
				PgimRolProcesoDTO pgimRolProcesoDTO = this.rolProcesoRepository
						.obtenerRolInteresadoPorInstanciaProceso(idInstanciaProceso);

				if (pgimRolProcesoDTO == null) {
					throw new PgimException("error", "No se ha encontrado el rol interesado para el proceso");
				}

				Boolean esOsinergmin = false;
				Long idPersonal = null;

				PgimPersonalOsiDTO pgimPersonalOsiDTO = this.obtenerPersonaOsiPorUsuario(noUsuario);

				if (pgimPersonalOsiDTO == null) {
					PgimPersonalContratoDTO pgimPersonalContratoDTO = this.obtenerPersonaContratoPorUsuario(noUsuario,
							null);

					if (pgimPersonalContratoDTO == null) {
						mensajeExcepcionControlada = String
								.format("No se ha encontrado el personal con el nombre de usuario %s", noUsuario);
						throw new PgimException("error", mensajeExcepcionControlada);
					}

					idPersonal = pgimPersonalContratoDTO.getIdPersonalContrato();
				} else {
					idPersonal = pgimPersonalOsiDTO.getIdPersonalOsi();
					esOsinergmin = true;
				}

				PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
						idInstanciaProceso, idPersonal, esOsinergmin, pgimRolProcesoDTO.getIdRolProceso(), false,
						auditoriaDTO);

				if (pgimEqpInstanciaProDTO == null) {
					mensajeExcepcionControlada = String
							.format("No se ha podido marcar el interés de %s en la instancia del proceso", noUsuario);
					throw new PgimException("error", mensajeExcepcionControlada);
				}
			}

		} else if (flPersDestinoInteresada.equals(ConstantesUtil.IND_INACTIVO)) {
			// - Entonces se debe desactivar el interés de la persona destino.
			PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = lPgimEqpInstanciaProDTO.get(0);

			pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository
					.findById(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro()).orElse(null);

			pgimEqpInstanciaPro.setEsRegistro(ConstantesUtil.IND_INACTIVO);
			pgimEqpInstanciaPro.setFeActualizacion(new Date());
			pgimEqpInstanciaPro.setUsActualizacion(auditoriaDTO.getUsername());
			pgimEqpInstanciaPro.setIpActualizacion(auditoriaDTO.getTerminal());

			pgimEqpInstanciaPro = this.equipoInstanciaProcesoRepository.save(pgimEqpInstanciaPro);
		}
	}

	@Override
	public List<PgimEqpInstanciaProDTO> obtenerPersonalEqpInteresado(Long idInstanciaProceso, String noUsuario)
			throws Exception {
		String mensajeExcepcionControlada = "";

		Boolean esOsinergmin = false;
		Long idPersonal = null;

		PgimPersonalOsiDTO pgimPersonalOsiDTO = null;

		pgimPersonalOsiDTO = this.obtenerPersonaOsiPorUsuario(noUsuario);

		if (pgimPersonalOsiDTO == null) {
			PgimPersonalContratoDTO pgimPersonalContratoDTO = this.obtenerPersonaContratoPorUsuario(noUsuario, null);

			if (pgimPersonalContratoDTO == null) {
				mensajeExcepcionControlada = String
						.format("No se ha encontrado el personal con el nombre de usuario %s", noUsuario);
				throw new PgimException("error", mensajeExcepcionControlada);
			}

			idPersonal = pgimPersonalContratoDTO.getIdPersonalContrato();
		} else {
			idPersonal = pgimPersonalOsiDTO.getIdPersonalOsi();
			esOsinergmin = true;
		}

		PgimRolProcesoDTO pgimRolProcesoDTO = this.rolProcesoRepository
				.obtenerRolInteresadoPorInstanciaProceso(idInstanciaProceso);

		if (pgimRolProcesoDTO == null) {
			throw new PgimException("error", "No se ha encontrado el rol interesado para el proceso");
		}

		Long idRolProcesoInteresado = pgimRolProcesoDTO.getIdRolProceso();

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = null;

		if (esOsinergmin) {
			lPgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository
					.obtenerPersonaOsiEqpPorRol(idInstanciaProceso, idPersonal, idRolProcesoInteresado);

		} else {
			lPgimEqpInstanciaProDTO = this.equipoInstanciaProcesoRepository
					.obtenerPersonaContraEqpPorRol(idInstanciaProceso, idPersonal, idRolProcesoInteresado);
		}

		return lPgimEqpInstanciaProDTO;
	}

	@Override
	public Boolean personaEstaInteresada(Long idInstanciaProceso, String noUsuario) throws Exception {
		Boolean estaActualmenteInteresada = false;

		List<PgimEqpInstanciaProDTO> lPgimEqpInstanciaProDTO = this.obtenerPersonalEqpInteresado(idInstanciaProceso,
				noUsuario);

		if (lPgimEqpInstanciaProDTO.size() > 0) {
			estaActualmenteInteresada = true;
		}

		return estaActualmenteInteresada;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PgimInstanciaPaso reasignarPasoProceso(final PgimInstanciaPasoDTO pgimInstanciaPasoDTO,
			final AuditoriaDTO auditoriaDTO) throws Exception {
		
		PgimInstanciaPaso pgimInstanciaPasoResultado = null;
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(pgimInstanciaPasoDTO.getIdInstanciaProceso()).orElse(null);

		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null && 
				pgimInstanciaPasoDTO.getIdTipoSubflujo().equals(ConstantesUtil.PARAM_TIPO_SUBFLUJO_SECUNDARIO)){
			
			String noUsuarioRemitente = null; 
			String noUsuarioDestinatario = null;
			String noUsuarioDestinatarioAdicional = null;

			List<String> lUsuarioDestinatario = new ArrayList<String>();
			
			pgimInstanciaPasoResultado = this.reasignarPasoProcesoInterno(pgimInstanciaPasoDTO, true, auditoriaDTO);	
			
			// Tomamos el usuario principal actual de la instancia de proceso
			noUsuarioDestinatario = pgimInstanciaProces.getNoUsuarioPrincipal();
			
			// Tomamos el usuario origen como usuario remitente del subflujo
			noUsuarioRemitente = this.obtenerNombreUsuarioWindows(null, pgimInstanciaPasoResultado.getPersonaEqpOrigen().getIdEquipoInstanciaPro());
					
			// Seguimos enviando en subflujo al expediente SIGED
			noUsuarioDestinatarioAdicional = this.obtenerNombreUsuarioWindows(pgimInstanciaPasoResultado, null);
			lUsuarioDestinatario.add(noUsuarioDestinatarioAdicional);

			if (pgimInstanciaPasoResultado == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha podido crear la instancia del paso");
			}
			
			if (noUsuarioDestinatario == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha obtenido el usuario principal para envío del subflujo");
			}
			
			if (noUsuarioRemitente == null) {
				throw new PgimException(TipoResultado.ERROR, "No se ha obtenido el usuario remitente para envío del subflujo");
			}

			this.reenviarSubflujoExpediente(pgimInstanciaProces.getNuExpedienteSiged(), noUsuarioRemitente,
					noUsuarioDestinatario, lUsuarioDestinatario,
					"Reasignación de subflujo desde la PGIM", pgimInstanciaPasoResultado.getDeMensaje(),
					auditoriaDTO);
			
		} else {
			pgimInstanciaPasoResultado = this.reasignarPasoProcesoInterno(pgimInstanciaPasoDTO, false, auditoriaDTO);
		}

		return pgimInstanciaPasoResultado;		
	}

	public PgimInstanciaPaso reasignarPasoProcesoInterno(final PgimInstanciaPasoDTO pgimInstanciaPasoDTO, final Boolean esParalela, 
			final AuditoriaDTO auditoriaDTO) throws Exception {

		PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this
				.obtenerInstanciaPasoActualPorIdInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaPaso());

		final PgimRelacionPaso pgimRelacionPasoDestino = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);

		Long idPersonaEqpOrigen = pgimInstanciaPasoDTOActual.getIdPersonaEqpDestino();
		Long idPersonaEqpDestino = null;

		if (idPersonaEqpDestino == null) {
			// Como no existe entonces se debe asegurar la existencia de la persona destino
			// como miembro del equipo
			// de la instancia del proceso.
			PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;

			boolean esOsinergmin = false;
			Long idPersonal = null;

			if (pgimInstanciaPasoDTO.getDescIdPersonalOsiDestino() != null) {
				esOsinergmin = true;
				idPersonal = pgimInstanciaPasoDTO.getDescIdPersonalOsiDestino();
			} else if (pgimInstanciaPasoDTO.getDescIdPersonalContratoDestino() != null) {
				esOsinergmin = false;
				idPersonal = pgimInstanciaPasoDTO.getDescIdPersonalContratoDestino();
			}

			pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(pgimInstanciaPasoDTO.getIdInstanciaProceso(),
					idPersonal, esOsinergmin,
					pgimRelacionPasoDestino.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso(), false,
					auditoriaDTO);

			idPersonaEqpDestino = pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro();
		}
		
		PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository
				.findById(pgimInstanciaPasoDTO.getIdInstanciaProceso()).orElse(null);
		
		//Primero actualizamos usuario principal en instancia de proceso, de ser el caso, 
		//para evitar riesgo de incongruencia con el Siged cuando haya concurrencia de uso de reenviarSubflujo		
		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null && 
		   pgimInstanciaPasoDTO.getIdTipoSubflujo().equals(ConstantesUtil.PARAM_TIPO_SUBFLUJO_PRINCIPAL)) {
			
			String noUsuarioPrincipal = this.obtenerNombreUsuarioWindows(null, idPersonaEqpDestino);
			pgimInstanciaProces.setNoUsuarioPrincipal(noUsuarioPrincipal);				
			pgimInstanciaProces = this.instanciaProcesService.modificarInstanciaProcesEntity(pgimInstanciaProces, auditoriaDTO);
		}

		PgimInstanciaPaso pgimInstanciaPaso = new PgimInstanciaPaso();

		pgimInstanciaPaso.setPgimInstanciaProces(new PgimInstanciaProces());
		pgimInstanciaPaso.getPgimInstanciaProces().setIdInstanciaProceso(pgimInstanciaPasoDTO.getIdInstanciaProceso());

		pgimInstanciaPaso.setPgimRelacionPaso(new PgimRelacionPaso());
		pgimInstanciaPaso.getPgimRelacionPaso().setIdRelacionPaso(pgimInstanciaPasoDTO.getIdRelacionPaso());

		pgimInstanciaPaso.setPersonaEqpOrigen(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpOrigen().setIdEquipoInstanciaPro(idPersonaEqpOrigen);

		pgimInstanciaPaso.setPersonaEqpDestino(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpDestino().setIdEquipoInstanciaPro(idPersonaEqpDestino);

		pgimInstanciaPaso.setPasoProcesoOrigen(pgimRelacionPasoDestino.getPasoProcesoOrigen());
		pgimInstanciaPaso.setPasoProcesoDestino(pgimRelacionPasoDestino.getPasoProcesoDestino());

		pgimInstanciaPaso.setFeInstanciaPaso(new Date());
		pgimInstanciaPaso.setDeMensaje(pgimInstanciaPasoDTO.getDeMensaje());

		pgimInstanciaPaso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstanciaPaso.setFeCreacion(new Date());
		pgimInstanciaPaso.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstanciaPaso.setIpCreacion(auditoriaDTO.getTerminal());

		pgimInstanciaPaso.setInstanciaPasoPadre(new PgimInstanciaPaso());
		pgimInstanciaPaso.getInstanciaPasoPadre().setIdInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaPasoPadre());
		
		if(pgimInstanciaPasoDTO.getIdTipoSubflujo() != null) {
			pgimInstanciaPaso.setTipoSubflujo(new PgimValorParametro());
			pgimInstanciaPaso.getTipoSubflujo().setIdValorParametro(pgimInstanciaPasoDTO.getIdTipoSubflujo());
		}

		// Flag que indica si es el último paso dado en la instancia de proceso. Desactivamos el paso anterior
		PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository.findById(pgimInstanciaPasoDTO.getIdInstanciaPasoPadre()).orElse(null);
		if(pgimInstanciaPasoActual != null){
			pgimInstanciaPasoActual.setFlEsPasoActivo(ConstantesUtil.FL_IND_NO);
			this.instanciaPasoRepository.save(pgimInstanciaPasoActual);
		}
				
		// Indicamos si el último paso estará activo o no para su visualización en el FE (algunas tareas paralelas no estarán activas)
		if(pgimInstanciaPasoDTO.getFlEsPasoActivo() != null) {
			pgimInstanciaPaso.setFlEsPasoActivo(pgimInstanciaPasoDTO.getFlEsPasoActivo());
		}else {
			pgimInstanciaPaso.setFlEsPasoActivo(ConstantesUtil.FL_IND_SI);
		}				

		// Indicar si la asignación por defecto será leído o no
		if (pgimInstanciaPaso.getPersonaEqpDestino().getIdEquipoInstanciaPro().equals(pgimInstanciaPaso.getPersonaEqpOrigen().getIdEquipoInstanciaPro())){
			pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_SI);
			pgimInstanciaPaso.setFeLectura(new Date());		
		}else {
			pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_NO);			
		}		

		// Validar si el paso previo ya tenía un usuario solicitante y el actual sea una
		// reasignación
		PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);

		Long idInstanciaPasoPrevio = pgimInstanciaPaso.getInstanciaPasoPadre().getIdInstanciaPaso();
		PgimInstanciaPaso pgimInstanciaPasoPrevio = this.instanciaPasoRepository.findById(idInstanciaPasoPrevio)
				.orElse(null);
		PgimRelacionPaso pgimRelacionPasoPrevio = this.relacionPasoRepository
				.findById(pgimInstanciaPasoPrevio.getPgimRelacionPaso().getIdRelacionPaso()).orElse(null);
		if (pgimRelacionPasoPrevio.getFlRequiereAprobacion() != null
				&& pgimRelacionPasoPrevio.getFlRequiereAprobacion().equals(ConstantesUtil.IND_REQ_APROBACION_SI)
				&& pgimRelacionPasoPrevio.getTipoAccionSiged().getIdValorParametro()
						.equals(ConstantesUtil.PARAM_REENVIAR)
				&& pgimRelacionPaso.getTipoAccionSiged().getIdValorParametro().equals(ConstantesUtil.PARAM_REENVIAR)) {
			pgimInstanciaPaso.setPersonaEqpSolic(new PgimEqpInstanciaPro());
			pgimInstanciaPaso.getPersonaEqpSolic()
					.setIdEquipoInstanciaPro(pgimInstanciaPasoPrevio.getPersonaEqpSolic().getIdEquipoInstanciaPro());
		}

		pgimInstanciaPaso = this.instanciaPasoRepository.save(pgimInstanciaPaso);
		
		// Grabar alertas para el(los) usuario(s)
		this.enviarAlerta(pgimInstanciaProces, pgimInstanciaPaso, auditoriaDTO);

		if (pgimInstanciaProces.getNuExpedienteSiged() != null) {
			// Si ya se cuenta con expediente Siged entonces se envía el expediente, siempre
			// que la persona origen y destino sean diferentes.
			// Asimismo se envía siempre y cuando sea asignación convencional o del flujo principal. Si es subflujo secundario se envía con el método reenviarSubflujo
			if(!esParalela) {
				ExpedienteOutRO expedienteOutRO = this.enviarExpedienteSiged(pgimInstanciaProces, pgimInstanciaPaso,
						auditoriaDTO, true);				
	
				if (!expedienteOutRO.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
					throw new PgimException(TipoResultado.ERROR, expedienteOutRO.getMessage(), expedienteOutRO.getDetalleExcepcionDTO()); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
				}
			}
		}

		return pgimInstanciaPaso;
	}

	@Override
	public List<PgimFaseProcesoDTO> obtenerFasesInstanciaProceso(Long idInstanciaProceso) {

		return this.faseProcesoRepository.obtenerFasesInstanciaProceso(idInstanciaProceso);
	}

	@Override
	@Transactional(readOnly = false)
	public PgimInstanciaPasoDTO crearInstanciaPasoFinalConfiguraRiesgo(
			final PgimInstanciaProces pgimInstanciaProcesActual, final PgimInstanciaPasoDTO pgimInstanciaPasoDTO,
			final AuditoriaDTO auditoriaDTO) throws Exception {

		// Asignamos el usuario por defecto ccalero
		// auditoriaDTO.setUsername("CCALERO");

		PgimInstanciaPaso pgimInstanciaPaso = new PgimInstanciaPaso();

		pgimInstanciaPaso.setPgimInstanciaProces(new PgimInstanciaProces());
		pgimInstanciaPaso.getPgimInstanciaProces()
				.setIdInstanciaProceso(pgimInstanciaProcesActual.getIdInstanciaProceso());

		pgimInstanciaPaso.setPgimRelacionPaso(new PgimRelacionPaso());
		pgimInstanciaPaso.getPgimRelacionPaso().setIdRelacionPaso(pgimInstanciaPasoDTO.getIdRelacionPaso());

		PgimEqpInstanciaProDTO pgimEqpInstanciaProDTO = null;
		PgimRelacionPaso pgimRelacionPaso = this.relacionPasoRepository
				.findById(pgimInstanciaPasoDTO.getIdRelacionPaso()).orElse(null);

		// Configurando al emisor de la asignación
		PgimPersonalOsiDTO pgimPersonalOsiDTOOrigen = this.obtenerPersonaOsiPorUsuario(auditoriaDTO.getUsername());

		if (pgimPersonalOsiDTOOrigen == null) {
			throw new PgimException("error",
					String.format("No se ha encontrado ningún usuario del Osinergmin con el nombre '%s'",
							auditoriaDTO.getUsername()));
		}

		pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
				pgimInstanciaProcesActual.getIdInstanciaProceso(), pgimPersonalOsiDTOOrigen.getIdPersonalOsi(), true,
				pgimRelacionPaso.getPasoProcesoOrigen().getPgimRolProceso().getIdRolProceso(), false, auditoriaDTO);

		pgimInstanciaPaso.setPersonaEqpOrigen(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpOrigen()
				.setIdEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

		// Configurando al receptor de la asignación
		final PgimPersonalOsiDTO pgimPersonalOsiDTODestino = this
				.obtenerPersonaOsiPorId(pgimPersonalOsiDTOOrigen.getIdPersonalOsi());

		pgimEqpInstanciaProDTO = this.asegurarPersonalInstanciaProceso(
				pgimInstanciaProcesActual.getIdInstanciaProceso(), pgimPersonalOsiDTODestino.getIdPersonalOsi(), true,
				pgimRelacionPaso.getPasoProcesoDestino().getPgimRolProceso().getIdRolProceso(), true, auditoriaDTO);

		pgimInstanciaPaso.setPersonaEqpDestino(new PgimEqpInstanciaPro());
		pgimInstanciaPaso.getPersonaEqpDestino()
				.setIdEquipoInstanciaPro(pgimEqpInstanciaProDTO.getIdEquipoInstanciaPro());

		pgimInstanciaPaso.setFeInstanciaPaso(new Date());
		pgimInstanciaPaso.setDeMensaje(pgimInstanciaPasoDTO.getDeMensaje());

		pgimInstanciaPaso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimInstanciaPaso.setFeCreacion(new Date());
		pgimInstanciaPaso.setUsCreacion(auditoriaDTO.getUsername());
		pgimInstanciaPaso.setIpCreacion(auditoriaDTO.getTerminal());

		pgimInstanciaPaso = this.instanciaPasoRepository.save(pgimInstanciaPaso);

		final PgimInstanciaPasoDTO pgimInstanciaPasoDTOFinal = this.instanciaPasoRepository
				.obtenerInstanciaPasoPorId(pgimInstanciaPaso.getIdInstanciaPaso());

		this.enviarAlerta(pgimInstanciaProcesActual, pgimInstanciaPaso, auditoriaDTO);

		return pgimInstanciaPasoDTOFinal;
	}

	@Override
	public PgimInstanciaProcesDTO obtenerInstanciaProceso(Long idInstanciaProceso) {
		return this.instanciaProcesRepository.obtenerInstanciaProceso(idInstanciaProceso);
	}
	
	@Override
	@Transactional(readOnly = false)
	public PgimInstanciaPaso marcarFlLecturaInstanciaPaso(Long idInstanciaPaso, String flLeidoNuevo, 
			AuditoriaDTO auditoriaDTO) throws Exception{
		
		PgimInstanciaPaso pgimInstanciaPasoModificado = null;
		
		PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);		
		
		if(pgimInstanciaPaso.getFlLeido() == null) {
			pgimInstanciaPaso.setFlLeido(ConstantesUtil.FL_IND_NO);	
			pgimInstanciaPaso.setFeLectura(null);
		} 
		
		if (!pgimInstanciaPaso.getFlLeido().equals(flLeidoNuevo)) {
			pgimInstanciaPaso.setFlLeido(flLeidoNuevo);	
			
			if(flLeidoNuevo.equals(ConstantesUtil.FL_IND_SI)) {
				pgimInstanciaPaso.setFeLectura(new Date());
			}
			
			pgimInstanciaPaso.setFeActualizacion(new Date());
			pgimInstanciaPaso.setUsActualizacion(auditoriaDTO.getUsername());
			pgimInstanciaPaso.setIpActualizacion(auditoriaDTO.getTerminal());
	
			pgimInstanciaPasoModificado = this.instanciaPasoRepository.save(pgimInstanciaPaso);
		}
		
		return pgimInstanciaPasoModificado;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PgimInstanciaPaso finalizarInstanciaPaso(Long idInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception{
		
		PgimInstanciaPaso pgimInstanciaPasoModificado = null;
		
		PgimInstanPasoAuxDTO pgimInstanPasoAuxDTO = this.instanciaPasoAuxRepository.obtenerInstanciaPasoAuxPorId(idInstanciaPaso);		 
		
		if(pgimInstanPasoAuxDTO == null ) {
			String mensaje = String.format("No se encontró la instancia de paso con id %s", idInstanciaPaso);
			throw new PgimException(TipoResultado.ERROR, mensaje);
		}
		
		List<PgimInstanciaPasoDTO> lIntanciaPasoDTOActuales = this.instanciaPasoRepository
				.obtenerPasosActualesPorInstanciaProceso(pgimInstanPasoAuxDTO.getIdInstanciaProceso());
		
		int cantTareasConvergidas = lIntanciaPasoDTOActuales.stream().filter(pgimInstanciaPasoDTO ->
					                      pgimInstanciaPasoDTO.getIdPasoProcesoDestino().equals(ConstPasoProcesoPas.VERIFICAR_PAGO_MULTA_INFRACCIONES)
					                ).collect(Collectors.toList()).size();
		
		if(cantTareasConvergidas > 1) {	
			
			PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository.findById(idInstanciaPaso).orElse(null);	
			
			pgimInstanciaPaso.setFlEsPasoActivo(ConstantesUtil.FL_IND_NO);
			pgimInstanciaPaso.setDeMensaje(pgimInstanciaPaso.getDeMensaje() + "...[Tarea paralela finalizada]");
				
			pgimInstanciaPaso.setFeActualizacion(new Date());
			pgimInstanciaPaso.setUsActualizacion(auditoriaDTO.getUsername());
			pgimInstanciaPaso.setIpActualizacion(auditoriaDTO.getTerminal());
	
			pgimInstanciaPasoModificado = this.instanciaPasoRepository.save(pgimInstanciaPaso);
			
		}else {
			String mensaje = String.format("No se puede desactivar debido a que se encontró solamente %s instancia activa de esta tarea '%s'", 
					cantTareasConvergidas, pgimInstanPasoAuxDTO.getNoPasoProcesoDestino());
			throw new PgimException(TipoResultado.WARNING, mensaje);
		}
		
		return pgimInstanciaPasoModificado;
	}

	@Override
	public String verificarWarningsAsignacion(Long idRelacionPaso, Long idInstanciaProceso, 
			AuditoriaDTO auditoriaDTO) throws Exception {
		
		String sWarnings = "";
		
		PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null); 
		
		sWarnings = this.verificarFirmaDocumentos(idRelacionPaso, pgimInstanciaProcesActual, "0", auditoriaDTO);
		
		return sWarnings;
	}	

	@Override
	public List<PgimInfraccionAuxDTO> verificarContratistas(Long idRelacionPaso, Long idInstanciaProceso, 
	AuditoriaDTO auditoriaDTO){

		List<PgimInfraccionContraDTO> lContratistas = new ArrayList<PgimInfraccionContraDTO>();
		List<PgimInfraccionAuxDTO> lPgimInfraccion  = new ArrayList<PgimInfraccionAuxDTO>();
		List<PgimInfraccionAuxDTO> lInfracionesSC = new ArrayList<PgimInfraccionAuxDTO>();

		PgimSupervisionDTO pgimSupervisionDTO = supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);

		if(	idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORAR_FEHV_APROBAR_OCAF)||
			idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_CONFIRMARHC_CONTINUARPAS)
		){
			if(pgimSupervisionDTO.getIdMotivoSupervision().equals(ConstantesUtil.MOTIVO_ACCIDENTE_MORTAL)){
				lPgimInfraccion = infraccionAuxRepository.listarInfraccionPorIdSupervision(pgimSupervisionDTO.getIdSupervision());
				for (PgimInfraccionAuxDTO infraccionDTO : lPgimInfraccion ){
					if(infraccionDTO.getFlIncluirEnPas().equals(ConstantesUtil.FL_IND_SI)){
						lContratistas = infraccionContraRepository.listarContratistaByIdInfraccion(infraccionDTO.getIdInfraccionAux());
						if(lContratistas.size() == 0){
							lInfracionesSC.add(infraccionDTO);
						}

					}
				}
			}
		}

		return lInfracionesSC;
	}

	/**
	 * Permite validar que los documentos no tengan Reserva de Numero Activa al momento de hacer la transición de paso
	 * 
	 * @throws Exception
	 * 
	 */
	public void validarFlReservaActivaDocumentos(PgimRelacionPaso pgimRelacionPaso,
			PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO) throws Exception {
		
		Long idRelacionPaso = pgimRelacionPaso.getIdRelacionPaso();
		Long idInstanciaProceso = pgimInstanciaProces.getIdInstanciaProceso();

		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = null;

		lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarSubCategoriasFlReservaNumero(idRelacionPaso,
				idInstanciaProceso);

		if (lPgimRelacionSubcatDTO.size() > 0) {
			String sValidacion = "";
			sValidacion = this.verificarFlReservaNumero(pgimRelacionPaso.getIdRelacionPaso(), pgimInstanciaProces, auditoriaDTO);			
			String submsj = (lPgimRelacionSubcatDTO.size() == 1) ? "el documento siguiente" : "los siguientes documentos"; 
			sValidacion = "No se puede asignar el paso porque <b> se requiere reemplazar el archivo que reservó el número en " + submsj + "</b>: <br/> <br/>"
					+ sValidacion;
			
			throw new PgimException(TipoResultado.WARNING, sValidacion);

		}
	}

	/**
	 * Permite obtener el número del documento que se debe de reemplazar por que está con archivo que reservo el numero
	 * 
	 * @param idRelacionPaso
	 * @param pgimInstanciaProces
	 * @param auditoriaDTO
	 * @return
	 * @throws Exception
	 */
	public String verificarFlReservaNumero(Long idRelacionPaso, PgimInstanciaProces pgimInstanciaProces, 
			AuditoriaDTO auditoriaDTO) throws Exception {

		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = this.relacionSubcatRepository
				.listarSubCategoriasFlReservaNumero(idRelacionPaso, pgimInstanciaProces.getIdInstanciaProceso());
		
		String sValidacion = "";

		if (lPgimRelacionSubcatDTO.size() > 0) {

			Documentos documentos = documentoService
					.obtenerExpedienteDocumentoSiged(pgimInstanciaProces.getNuExpedienteSiged(), "0", auditoriaDTO);
			if (documentos == null || !documentos.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)
					|| documentos.getListaDocumento().size() == 0) {
				throw new PgimException(TipoResultado.WARNING,
						"No se encontró la lista de documentos SIGED para validar si reemplazaron el archivo que reserva el número.");
			}
			
			for (PgimRelacionSubcatDTO pgimRelacionSubcatDTO : lPgimRelacionSubcatDTO) {

				List<PgimDocumentoDTO> lPgimDocumentoDTO = this.documentoRepository.listarDocPorInstanciaYSubCategoria(
						pgimInstanciaProces.getIdInstanciaProceso(), pgimRelacionSubcatDTO.getIdSubcatDocumento());				

				for (PgimDocumentoDTO documentoDTO : lPgimDocumentoDTO) {

					boolean flReservaNumero = false;
					Documento docSigedReservaNumero = null;

					for (Documento documentoSiged : documentos.getListaDocumento()) {						
						if(documentoDTO.getFlReservaActiva() == null)
								documentoDTO.setFlReservaActiva(ConstantesUtil.IND_INACTIVO);

						if (documentoSiged.getIdDocumento().equals(documentoDTO.getCoDocumentoSiged().toString()) && documentoDTO.getFlReservaActiva().equals(ConstantesUtil.IND_ACTIVO)) {
							docSigedReservaNumero = documentoSiged;
							flReservaNumero = true;					
							break;
						}

					}

					if (flReservaNumero && docSigedReservaNumero != null) {
						if (sValidacion.equals(""))
							sValidacion = String.format("+ %s <b>%s</b> (%s)", 
										pgimRelacionSubcatDTO.getDescNoSubcatDocumento(),
										docSigedReservaNumero.getNroDocumento(),
										pgimRelacionSubcatDTO.getDescNoCategoriaDocumento());							
							
						else
							sValidacion = sValidacion + "<br/>"+ String.format("+ %s <b>%s</b> (%s)", 
										pgimRelacionSubcatDTO.getDescNoSubcatDocumento(),
										docSigedReservaNumero.getNroDocumento(),
										pgimRelacionSubcatDTO.getDescNoCategoriaDocumento());
					}

				}
			}
		}
		
		return sValidacion;
	}	
	
	/**
	 * Permite validar si se ha notificado a todos los responsables de infracción determinado documento
	 * en determinada transición, para los casos de fiscalizaciones/PAS de motivo Accidente mortal
	 * 
	 * @param pgimRelacionPaso
	 * @param pgimInstanciaProces
	 * @param auditoriaDTO
	 * @throws Exception
	 */
	public void validarNotificacionDocResponsables(PgimRelacionPaso pgimRelacionPaso,
			PgimInstanciaProces pgimInstanciaProces, AuditoriaDTO auditoriaDTO) throws Exception {
		
		Long idProceso = pgimInstanciaProces.getPgimProceso().getIdProceso();
		Long idRelacionPaso = pgimRelacionPaso.getIdRelacionPaso();
		Long idInstanciaProceso = pgimInstanciaProces.getIdInstanciaProceso();
		
		PgimSupervision pgimSupervision = null;
		Long idAgenteFiscalizado = null;
				
		if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) {
			pgimSupervision = this.supervisionRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
			idAgenteFiscalizado = pgimSupervision.getPgimUnidadMinera().getPgimAgenteSupervisado().getIdAgenteSupervisado();			
			
		}else if(idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION)) {
			PgimPas pgimPas = this.pasRepository.findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);
			pgimSupervision = pgimPas.getPgimSupervision();
			idAgenteFiscalizado = pgimSupervision.getPgimUnidadMinera().getPgimAgenteSupervisado().getIdAgenteSupervisado();
		
		}else {
			return;
		}
		
		// Verificamos si se trata de una fiscalización/PAS con motivo Accidente mortal
		if(!pgimSupervision.getPgimMotivoSupervision().getIdMotivoSupervision().equals(ConstantesUtil.MOTIVO_ACCIDENTE_MORTAL)) {
			return;
		}
		
		// Verificamos si se trata de una transición configurada para esta validación (antes de finalización de subproceso de notificación)
		List<PgimRelacionSubcatDTO> lPgimRelacionSubcatDTO = this.relacionSubcatRepository.listarConfigRelacionSubcatValidaNotifResponsables(idRelacionPaso);	
		
		if(lPgimRelacionSubcatDTO.size() == 0) {
			return;
		}
				
		PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.obtenerAgenteSupervisadoPorIdAs(idAgenteFiscalizado);
		
		for (PgimRelacionSubcatDTO pgimRelacionSubcatDTO : lPgimRelacionSubcatDTO) {
			// Verificamos si ya se notificó al agente fiscalizado
			List<PgimDestinatarioDocDTO> lstDestinatarioDocNoNotificadoAs = this.destinatarioDocRepository.listarDestinatarioDocNoNotificado(idInstanciaProceso, 
					pgimAgenteSupervisadoDTO.getIdPersona(), pgimRelacionSubcatDTO.getIdSubcatDocumento());
			
			if(lstDestinatarioDocNoNotificadoAs.size() > 0) { 
				String mensaje = String.format("No se ha encontrado el documento <b>%s</b> del agente fiscalizado. Por favor, notifique el documento correspondiente al mismo y adjunte su respectiva constancia o cargo de notificación, asimismo, cerciórese que este se haya relacionado con el documento notificado correcto.",
						pgimRelacionSubcatDTO.getDescNoSubcatDocumento());
				
				throw new PgimException(TipoResultado.WARNING, mensaje);
			}	
			
			// Verificamos si ya se notificó a los contratistas
			String sValidacion = "";
			List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO =  this.infraccionContraRepository.listarContratistaByIdInstanciaProceso(idInstanciaProceso);
			
			for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
				
				List<PgimDestinatarioDocDTO> lstDestinatarioDocNoNotificado = this.destinatarioDocRepository.listarDestinatarioDocNoNotificado(idInstanciaProceso, 
						pgimInfraccionContraDTO.getIdPersona(), pgimRelacionSubcatDTO.getIdSubcatDocumento());
				
				if(lstDestinatarioDocNoNotificado.size() > 0) {
					
//					for (PgimDestinatarioDocDTO pgimDestinatarioDocDTO : lstDestinatarioDocNoNotificado) {
						sValidacion = sValidacion + String.format("+ %s (%s)", 
								pgimInfraccionContraDTO.getDescNoRazonSocial(),
								pgimInfraccionContraDTO.getDescCoDocumentoIdentidad() ) + "<br/>";
//					}				
					
				}			
				
			}
			
			if (!sValidacion.equals("")) {
				String mensaje = String.format("Los siguientes responsables de las infracciones aún no fueron notificados, no podrá continuar hasta que realice la notificación a todos ellos; "
						+ "asimismo, cerciórese que la respectiva constancia o cargo de notificación se haya relacionado con el documento notificado correcto: <br/> <br/> %s",
						sValidacion);
			
				throw new PgimException(TipoResultado.WARNING, mensaje);
			}
		}
		
	}
	
	@Override
	public DetalleExcepcionDTO obtenerDetalleExcepcion(PgimInstanciaProces pgimInstanciaProces, DetalleExcepcionDTO detalleExcepcionDTOPevio, 
			String metodo, String mensajeInfraResumen, List<String> lstAcciones ){
		
		DetalleExcepcionDTO detalleExcepcionDTO = new DetalleExcepcionDTO();
		
		try {			
		
			final String noProceso = CommonsUtil.obtenerNombreProceso(pgimInstanciaProces.getPgimProceso().getIdProceso());
			final String objetoTrabajo = this.obtenerEtiquetaObjetoTrabajo(pgimInstanciaProces.getIdInstanciaProceso());

			detalleExcepcionDTO.setProceso(noProceso);
			detalleExcepcionDTO.setObjetoTrabajo(objetoTrabajo);
			detalleExcepcionDTO.setUbicacion(metodo);
			
			if(detalleExcepcionDTOPevio != null && detalleExcepcionDTOPevio.getMensajeInfraResumen() != null )
				detalleExcepcionDTO.setMensajeInfraResumen(detalleExcepcionDTOPevio.getMensajeInfraResumen());
			else {
				detalleExcepcionDTO.setMensajeInfraResumen(mensajeInfraResumen);				
			}
			
			if(detalleExcepcionDTOPevio != null && detalleExcepcionDTOPevio.getListAcciones() != null )
				detalleExcepcionDTO.setListAcciones(detalleExcepcionDTOPevio.getListAcciones());
			else {
				detalleExcepcionDTO.setListAcciones(lstAcciones);				
			}
			
		}catch(Exception e) {
			throw new PgimException(TipoResultado.ERROR, "Ocurrió un problema al obtener el detalle de la excepción: "+ e.getMessage());
		}
		
		return detalleExcepcionDTO;
	}
	
	public String obtenerEtiquetaObjetoTrabajo(Long idInstanciaProceso) {
		
		String objetoTrabajo = null;

        PgimRankingRiesgoDTO pgimRankingRiesgoDTO = this.rankingRiesgoRepository.obtenerRankingRiesgoByIdInstanciaProceso(idInstanciaProceso);
        PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoRepository.obtenerConfiguraRiesgoAuxByIdInstanciaProceso(idInstanciaProceso);
        PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository.obtenerProgramaAuxByIdInstanciaProceso(idInstanciaProceso);
        PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
        PgimLiquidacionAuxDTO pgimLiquidacionAuxDTO = this.liquidacionAuxRepository.obtenerLiquidacionAuxPorInstanciaProceso(idInstanciaProceso);
        
        PgimPasDTO pgimPasDTO = this.pasRepository.obtenerPasByidInstanciaProceso(idInstanciaProceso);
                
        // RANKING DE RIESGO
        if(pgimRankingRiesgoDTO != null){
            objetoTrabajo = String.format("%s-%s.%s", ConstantesUtil.PREFIJO_RANK_RIESGO, pgimRankingRiesgoDTO.getIdRankingRiesgo(), pgimRankingRiesgoDTO.getNoRanking());
            
        }else        
        // CONFIGURACIÓN DE RIESGO
        if(pgimConfiguraRiesgoDTO != null){
            objetoTrabajo = String.format("%s-%s.%s", ConstantesUtil.PREFIJO_CFG_RIESGO, pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo(), pgimConfiguraRiesgoDTO.getNoConfiguracion());
            
        }else        
        // PROGRAMACIÓN
        if(pgimPrgrmSupervisionAuxDTO != null){
            objetoTrabajo = String.format("%s-%s.%s", ConstantesUtil.PREFIJO_PROGRAMA_SUP, pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervisionAux(), pgimPrgrmSupervisionAuxDTO.getNoProgramaSupervision());
            
        }else        
        // FISCALIZACIÓN
        if(pgimSupervisionDTO != null){
            objetoTrabajo = String.format("%s.%s", pgimSupervisionDTO.getCoSupervision(), pgimSupervisionDTO.getDescNoUnidadMinera());
            
        }else        
        // PAS
        if(pgimPasDTO != null){
            objetoTrabajo = String.format("%s-%s.%s", ConstantesUtil.PREFIJO_PAS, pgimPasDTO.getIdPas(), pgimPasDTO.getDescNoUnidadMinera());
                   
        }else        
        // LIQUIDACIONES
        if(pgimLiquidacionAuxDTO != null){
            objetoTrabajo = String.format("%s.%s", pgimLiquidacionAuxDTO.getNuLiquidacion(), pgimLiquidacionAuxDTO.getNuContrato());
            
        }else {
        	objetoTrabajo = "";
        }
        
        return objetoTrabajo;		
	}

	@Override
    public Map<String, Object> mostrarLog(Long idInstanciaProceso, String username){
				
        final Map<String, Object> respuesta = new HashMap<>();
				
		if(idInstanciaProceso == null){
			respuesta.put("codigoObjeto", username);
		}

		try {
		
				String codigoObjeto = null;

				PgimRankingRiesgoDTO pgimRankingRiesgoDTO = this.rankingRiesgoRepository.obtenerRankingRiesgoByIdInstanciaProceso(idInstanciaProceso);
				PgimConfiguraRiesgoDTO pgimConfiguraRiesgoDTO = this.configuraRiesgoRepository.obtenerConfiguraRiesgoAuxByIdInstanciaProceso(idInstanciaProceso);
				PgimPrgrmSupervisionAuxDTO pgimPrgrmSupervisionAuxDTO = this.prgrmSupervisionAuxRepository.obtenerProgramaAuxByIdInstanciaProceso(idInstanciaProceso);
				PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByidInstanciaProceso(idInstanciaProceso);
				PgimLiquidacionAuxDTO pgimLiquidacionAuxDTO = this.liquidacionAuxRepository.obtenerLiquidacionAuxPorInstanciaProceso(idInstanciaProceso);
				
				// RANKING DE RIESGO
				if(pgimRankingRiesgoDTO != null){
						codigoObjeto = String.format("%s.RANK-%s.%s", username.toUpperCase(), pgimRankingRiesgoDTO.getIdRankingRiesgo(), pgimRankingRiesgoDTO.getNoRanking());
						respuesta.put("codigoObjeto", codigoObjeto);
				}else
				
				// CONFIGURACIÓN DE RIESGO
				if(pgimConfiguraRiesgoDTO != null){
						codigoObjeto = String.format("%s.CFGR-%s.%s", username.toUpperCase(), pgimConfiguraRiesgoDTO.getIdConfiguraRiesgo(), pgimConfiguraRiesgoDTO.getNoConfiguracion());
						respuesta.put("codigoObjeto", codigoObjeto);
				}else
				
				// PROGRAMACIÓN
				if(pgimPrgrmSupervisionAuxDTO != null){
						codigoObjeto = String.format("%s.PRGS-%s.%s", username.toUpperCase(), pgimPrgrmSupervisionAuxDTO.getIdProgramaSupervisionAux(), pgimPrgrmSupervisionAuxDTO.getNoProgramaSupervision());
						respuesta.put("codigoObjeto", codigoObjeto);
				}else
				
				// FISCALIZACIÓN
				if(pgimSupervisionDTO != null){
						codigoObjeto = String.format("%s.%s.%s", username.toUpperCase(), pgimSupervisionDTO.getCoSupervision(), pgimSupervisionDTO.getDescNoUnidadMinera());
						respuesta.put("codigoObjeto", codigoObjeto);
				}else
				
				// LIQUIDACIONES
				if(pgimLiquidacionAuxDTO != null){
						codigoObjeto = String.format("%s.%s.%s", username.toUpperCase(), pgimLiquidacionAuxDTO.getNuLiquidacion(), pgimLiquidacionAuxDTO.getNuContrato());
						respuesta.put("codigoObjeto", codigoObjeto);
				}else {
					respuesta.put("codigoObjeto", username);
				}
        
		}catch(Exception e) {
			throw new PgimException(TipoResultado.ERROR, "Ocurrió un problema al obtener el detalle de la excepción: "+ e.getMessage());
		}
        
        return respuesta;
    }
	
	@Override
    public Map<String, Object> mostrarLogPorInstanciaPaso(Long idInstanciaProceso, Long idInstanciaPaso, String username){

        Map<String, Object> respuesta = new HashMap<>();

        String codigoObjeto = null;

        PgimPasDTO pgimPasDTO = this.pasRepository.obtenerPasByidInstanciaPaso(idInstanciaProceso, idInstanciaPaso);
        
        // PAS
        if(pgimPasDTO != null){
            codigoObjeto = String.format("%s.PASA-%s.%s", username.toUpperCase(), pgimPasDTO.getIdPas(), pgimPasDTO.getDescCoSupervision());
            respuesta.put("codigoObjeto", codigoObjeto);
            
        }else {
        	respuesta = this.mostrarLog(idInstanciaProceso, username);
        }
        
        return respuesta;
    }

	@Override
	public List<PgimRelacionPasoDTO> obtenerTodosRelacionPaso(){
		List<PgimRelacionPasoDTO> lPgimRelacionPasoDTO = this.relacionPasoRepository
				.obtenerTodosRelacionPaso();
		return lPgimRelacionPasoDTO;
	}
}
