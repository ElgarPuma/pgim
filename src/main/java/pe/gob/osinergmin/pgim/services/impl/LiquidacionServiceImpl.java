package pe.gob.osinergmin.pgim.services.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.GrupoEntregableLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContrLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEntregableLiquidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemConsumoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimLiquidacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimConsumoContra;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimItemConsumo;
import pe.gob.osinergmin.pgim.models.entity.PgimItemLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ContrLiquidacionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.DocumentoRepository;
import pe.gob.osinergmin.pgim.models.repository.EntregableLiquidacionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemConsumoContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.ItemLiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.AgenteSupervisadoService;
import pe.gob.osinergmin.pgim.services.DocumentoService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.LiquidacionService;
import pe.gob.osinergmin.pgim.services.PersonalContratoService;
import pe.gob.osinergmin.pgim.siged.Archivo;
import pe.gob.osinergmin.pgim.siged.DescargaArchivo;
import pe.gob.osinergmin.pgim.siged.Documento;
import pe.gob.osinergmin.pgim.siged.DocumentoNuevo;
import pe.gob.osinergmin.pgim.siged.DocumentoOutRO;
import pe.gob.osinergmin.pgim.siged.Documentos;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstPasoProcesoLiquidacion;
import pe.gob.osinergmin.pgim.utils.ConstRelacionPasoLiquidacion;
import pe.gob.osinergmin.pgim.utils.ConstSubCategoriaDocumento;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la liquidación
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class LiquidacionServiceImpl implements LiquidacionService {

        @Autowired
        private LiquidacionRepository liquidacionRepository;

        @Autowired
        private InstanciaProcesService instanciaProcesService;

        @Autowired
        private InstanciaProcesRepository instanciaProcesRepository;

        @Autowired
        private ContratoRepository contratoRepository;

        @Autowired
        private FlujoTrabajoService flujoTrabajoService;

        @Autowired
        private PersonalContratoService personalContratoService;

        @Autowired
        private ItemLiquidacionRepository itemLiquidacionRepository;

        @Autowired
        private ItemConsumoContratoRepository itemConsumoContratoRepository;

        @Autowired
        private LiquidacionAuxRepository liquidacionAuxRepository;

        @Autowired
        private DocumentoRepository documentoRepository;

        @Autowired
        private SubcategoriaDocRepository subcategoriaDocRepository;

        @Autowired
        private InstanciaPasoRepository instanciaPasoRepository;

        @Autowired
        private EntregableLiquidacionAuxRepository entregableLiquidacionAuxRepository;

        @Autowired
        private ContrLiquidacionAuxRepository contrLiquidacionAuxRepository;

        @Autowired
        private DocumentoService documentoService;

        @Autowired
        private SupervisionRepository supervisionRepository;

        @Autowired
        private DocumentoServiceImpl documentoServiceImpl;

        @Autowired
        private AgenteSupervisadoService agenteSupervisadoService;

        @Transactional(readOnly = false)
        @Override
        public PgimLiquidacionDTO crearLiquidacion(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO)
                        throws Exception {

                Long ultimaSecuencia = this.generarSecuenciaLiquidacion(pgimLiquidacionDTO.getIdContrato());
                String nuLiquidacion = "LQ-" + String.format("%03d", ultimaSecuencia);

                PgimLiquidacion pgimLiquidacion = new PgimLiquidacion();
                pgimLiquidacion.setNuLiquidacion(nuLiquidacion);

                PgimContrato pgimContrato = new PgimContrato();
                pgimContrato.setIdContrato(pgimLiquidacionDTO.getIdContrato());
                pgimLiquidacion.setPgimContrato(pgimContrato);

                PgimValorParametro tipoEntregable = new PgimValorParametro();
                tipoEntregable.setIdValorParametro(pgimLiquidacionDTO.getIdTipoEntregable());
                pgimLiquidacion.setTipoEntregable(tipoEntregable);

                if (pgimLiquidacionDTO.getIdDivisionSupervisora() != null) {
                        pgimLiquidacion.setDivisionSupervisora(new PgimValorParametro());
                        pgimLiquidacion.getDivisionSupervisora()
                                        .setIdValorParametro(pgimLiquidacionDTO.getIdDivisionSupervisora());
                }

                if (pgimLiquidacionDTO.getIdSubtipoSupervision() != null) {
                        pgimLiquidacion.setPgimSubtipoSupervision(new PgimSubtipoSupervision());
                        pgimLiquidacion.getPgimSubtipoSupervision()
                                        .setIdSubtipoSupervision(pgimLiquidacionDTO.getIdSubtipoSupervision());
                }

                if (pgimLiquidacionDTO.getIdMotivoSupervision() != null) {
                        pgimLiquidacion.setPgimMotivoSupervision(new PgimMotivoSupervision());
                        pgimLiquidacion.getPgimMotivoSupervision()
                                        .setIdMotivoSupervision(pgimLiquidacionDTO.getIdMotivoSupervision());
                }

                pgimLiquidacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimLiquidacion.setFeCreacion(auditoriaDTO.getFecha());
                pgimLiquidacion.setUsCreacion(auditoriaDTO.getUsername());
                pgimLiquidacion.setIpCreacion(auditoriaDTO.getTerminal());

                PgimLiquidacion pgimLiquidacionCreado = null;

                pgimLiquidacionCreado = liquidacionRepository.save(pgimLiquidacion);
                
                // Validamos si ya se creó (o ya se encuentra en proceso) una liquidación del mismo contrato con el mismo número                 
                this.liquidacionRepository.flush(); // Permite la sincronización de los cambios en el contexto de persistencia con la base de datos antes de que la transacción finalice

    			List<PgimLiquidacionDTO> lPgimLiquidacionDTO = this.liquidacionRepository.listarLiquidacionPorContratoYNumero(nuLiquidacion, pgimLiquidacionDTO.getIdContrato());

    			if(lPgimLiquidacionDTO.size() > 1){
    				PgimLiquidacionDTO pgimLiquidacionDTOEncontrado = lPgimLiquidacionDTO.get(0);
    				String mensaje = String.format("La liquidación %s del contrato %s ya ha sido creada previamente o se encuentra en proceso, por favor actualice la ventana actual, verifique y/o vuelva a intentarlo.", 
    						nuLiquidacion, pgimLiquidacionDTOEncontrado.getDescNuContrato() );
    					throw new PgimException(TipoResultado.WARNING, mensaje);
    			}

                // Se asegura la instancia del proceso
                List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService
                                .asegurarInstanciasProceso(ConstantesUtil.PARAM_PROCESO_LIQUIDACION,
                                                pgimLiquidacionCreado.getIdLiquidacion(), auditoriaDTO);

                PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

                PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
                                .findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

                // Se actualiza la instancia del proceso en el registro de la supervión
                this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

                PgimLiquidacionDTO pgimLiquidacionDTOCreada = liquidacionRepository
                                .obtenerLiquidacionPorId(pgimLiquidacionCreado.getIdLiquidacion());

                // Se crea la asignación
                pgimLiquidacionDTO.setIdLiquidacion(pgimLiquidacionDTOCreada.getIdLiquidacion());

                PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
                pgimInstanciaPasoDTO.setIdRelacionPaso(pgimLiquidacionDTO.getDescIdRelacionPaso());

                List<PgimPersonalContratoDTO> lPgimPersonalContratoDTO = this.personalContratoService
                                .obtenerPersonalContratoPorNombre(auditoriaDTO.getUsername(),
                                                pgimLiquidacionDTO.getIdContrato());

                if (lPgimPersonalContratoDTO.size() == 0) {
                        throw new PgimException(TipoResultado.WARNING,
                                        "No se ha encontrado un/a fiscalizador/a con el nombre de usuario "
                                                        + auditoriaDTO.getUsername());
                }

                pgimInstanciaPasoDTO.setDescIdPersonalContratoDestino(
                                lPgimPersonalContratoDTO.get(0).getIdPersonalContrato());
                pgimInstanciaPasoDTO.setDeMensaje("Se ha creado la liquidación");

                pgimInstanciaProcesActual.setDescIdContrato(pgimContrato.getIdContrato());

                pgimInstanciaPasoDTO.setDescIdPersonalContratoDestino(pgimLiquidacionDTO.getDescIdPersonalContrato());

                PgimInstanciaPasoDTO pgimInstanciaPasoDTOCreada =  this.flujoTrabajoService.crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
                                auditoriaDTO);
                pgimLiquidacionDTOCreada.setDescIdInstanciaPaso(pgimInstanciaPasoDTOCreada.getIdInstanciaPaso());

                PgimItemLiquidacion pgimItemLiquidacion = null;
                List<Long> listaSupervisiones =  new ArrayList<Long>();

                for (PgimEntregableLiquidaAuxDTO pgimEntregableLiquidaAuxDTO : pgimLiquidacionDTO
                                .getDescListaEntregables()) {
                        pgimItemLiquidacion = new PgimItemLiquidacion();

                        pgimItemLiquidacion.setPgimLiquidacion(new PgimLiquidacion());
                        pgimItemLiquidacion.getPgimLiquidacion()
                                        .setIdLiquidacion(pgimLiquidacionDTOCreada.getIdLiquidacion());

                        pgimItemLiquidacion.setPgimItemConsumo(new PgimItemConsumo());
                        pgimItemLiquidacion.getPgimItemConsumo()
                                        .setIdItemConsumo(pgimEntregableLiquidaAuxDTO.getIdItemConsumo());

                        pgimItemLiquidacion.setPgimDocumento(new PgimDocumento());
                        pgimItemLiquidacion.getPgimDocumento()
                                        .setIdDocumento(pgimEntregableLiquidaAuxDTO.getIdDocumento());
  
                                        
                        pgimItemLiquidacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimItemLiquidacion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimItemLiquidacion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimItemLiquidacion.setIpCreacion(auditoriaDTO.getTerminal());
                        
                        this.itemLiquidacionRepository.save(pgimItemLiquidacion);
                        
                        listaSupervisiones.add(pgimEntregableLiquidaAuxDTO.getIdSupervision());
                        
                        // Validamos si ya se asignó el entregable en otra liquidación
                        this.itemLiquidacionRepository.flush(); // Permite la sincronización de los cambios en el contexto de persistencia con la base de datos antes de que la transacción finalice

            			List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTO = this.itemLiquidacionRepository.listarItemLiquidacionPorItemConsumo(pgimEntregableLiquidaAuxDTO.getIdItemConsumo());

            			if(lPgimItemLiquidacionDTO.size() > 1){
            				PgimItemLiquidacionDTO pgimItemLiquidacionDTOEncontrado = lPgimItemLiquidacionDTO.get(0);
            				String mensaje = String.format("El entegable %s (%s) ya ha sido asignado previamente en otra liquidación o se encuentra en proceso, por favor actualice la ventana actual, verifique y/o vuelva a intentarlo.", 
            						pgimItemLiquidacionDTOEncontrado.getDescCoSupervision(), pgimItemLiquidacionDTOEncontrado.getDescDeTipoEntregable());
            					throw new PgimException(TipoResultado.WARNING, mensaje);
            			}
                }

                // Copia de documentos
                if(pgimLiquidacionDTO.getIdTipoEntregable().equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES)){
                        Long subcategoriasDoc[][] = {
                                {ConstantesUtil.PARAM_SUBCAT_DOC_CIS, ConstantesUtil.PARAM_SC_COPIA_FICHA_CONFORMIDAD_INFOFISC},
                                {ConstantesUtil.PARAM_SUBCAT_DOC_OIS,ConstantesUtil.PARAM_SC_COPIA_FICHA_OBSERVAC_INFOFISC},
                                {ConstantesUtil.PARAM_SC_FICHA_JUSTIFICACION,ConstantesUtil.PARAM_SC_COPIA_INFORME_JUSTIFICACION},
                                {ConstantesUtil.PARAM_SC_INFORME_SUSTENTATORIO_APLIC_PENALIDAD, ConstantesUtil.PARAM_SC_COPIA_INFORME_SUSTENTATORIO_APLIC_PENALIDAD}
                        };

                        this.copiarDocumentos_fisc(subcategoriasDoc,pgimLiquidacionDTOCreada,listaSupervisiones,auditoriaDTO);
                }else if(pgimLiquidacionDTO.getIdTipoEntregable().equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS)){
                        Long subcategoriasDoc[][] = {
                                {ConstantesUtil.PARAM_SC_ACTA_FISCALIZACION, ConstantesUtil.PARAM_SC_COPIA_ACTA_FISCALIZAC}
                        };

                        this.copiarDocumentos_fisc(subcategoriasDoc,pgimLiquidacionDTOCreada,listaSupervisiones,auditoriaDTO);
                }else if(pgimLiquidacionDTO.getIdTipoEntregable().equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA)){
                        Long subcategoriasDoc[][] = {
                                {ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA, ConstantesUtil.PARAM_SC_COPIA_INFORME_FISCALIZAC_FALLIDA}
                        };

                        this.copiarDocumentos_fisc(subcategoriasDoc,pgimLiquidacionDTOCreada,listaSupervisiones,auditoriaDTO);
                }

                return pgimLiquidacionDTOCreada;
        }

        private void copiarDocumentos_fisc(Long[][] subcategoriasDoc,PgimLiquidacionDTO pgimLiquidacionDTO, List<Long> listaSupervisiones, AuditoriaDTO auditoriaDTO){

                List<List<PgimDocumentoDTO>> listaPgimDocumentoDTOSuperv = new ArrayList<List<PgimDocumentoDTO>>();
                PgimInstanciaProcesDTO pgimInstanciaProcesDTOLiquid = this.instanciaProcesRepository.obtenerInstanciaProceso(pgimLiquidacionDTO.getIdInstanciaProceso());
                for(Long idSupervision : listaSupervisiones){
                        PgimSupervisionDTO pgimSupervisionDTO = this.supervisionRepository.obtenerSupervisionByIdSupervision(idSupervision);                      
                        
                        List<PgimDocumentoDTO> listaPgimDocumentoDTO = new ArrayList<PgimDocumentoDTO>();
                        List<PgimDocumentoDTO> listaDocumentosSuperv = null;
                        for(Long[] subcategoria: subcategoriasDoc){
                                listaDocumentosSuperv = this.documentoRepository.obtenerDocumentosDescendentes(pgimSupervisionDTO.getIdInstanciaProceso(),subcategoria[0]);
                                
                                // Se copia uno o mas documentos de este tipo de subcategoria(Informe sustentario de aplicación de penalidad)
                                if(subcategoria[0].equals(ConstantesUtil.PARAM_SC_INFORME_SUSTENTATORIO_APLIC_PENALIDAD) && subcategoria[1].equals(ConstantesUtil.PARAM_SC_COPIA_INFORME_SUSTENTATORIO_APLIC_PENALIDAD)){

                                        for(PgimDocumentoDTO pgimDocumentoDTO : listaDocumentosSuperv){
                                                pgimDocumentoDTO.setIdSubcatDocumento(subcategoria[1]);
                                                pgimDocumentoDTO.setDeAsuntoDocumento(pgimDocumentoDTO.getDeAsuntoDocumento() + " ("+ pgimSupervisionDTO.getCoSupervision() +")");
                                                listaPgimDocumentoDTO.add(pgimDocumentoDTO);
                                        }
                                } else {
                                        for(PgimDocumentoDTO pgimDocumentoDTO : listaDocumentosSuperv){
                                                pgimDocumentoDTO.setIdSubcatDocumento(subcategoria[1]);
                                                pgimDocumentoDTO.setDeAsuntoDocumento(pgimDocumentoDTO.getDeAsuntoDocumento() + " ("+ pgimSupervisionDTO.getCoSupervision() +")");
                                                listaPgimDocumentoDTO.add(pgimDocumentoDTO);
                                                break;
                                        }
                                }
                                
                        }

                        listaPgimDocumentoDTOSuperv.add(listaPgimDocumentoDTO);                                
                        
                }
                
                this.documentoServiceImpl.procesarCopiadoDocumento_liquidacion(listaPgimDocumentoDTOSuperv,pgimInstanciaProcesDTOLiquid,auditoriaDTO);

        }

        /**
         * Permite generar el nuevo valo de secuencia de la liquidación.
         * 
         * @param idContrato
         * @return
         */
        private Long generarSecuenciaLiquidacion(Long idContrato) {

                PgimContrato pgimContrato = this.contratoRepository.findById(idContrato).orElse(null);

                Long ultimaSecuencia = 0L;

                if (pgimContrato.getSeLiquidacionContrato() != null) {
                        ultimaSecuencia = pgimContrato.getSeLiquidacionContrato();
                }

                Long nuevaSecuencia = ultimaSecuencia + 1;
                pgimContrato.setSeLiquidacionContrato(nuevaSecuencia);

                this.contratoRepository.save(pgimContrato);

                return nuevaSecuencia;
        }

        @Override
        public Page<PgimLiquidacionAuxDTO> listarLiquidaciones(PgimLiquidacionAuxDTO filtroLiquidacionAux,
                        Pageable paginador, AuditoriaDTO auditoriaDTO) {

                if (filtroLiquidacionAux.getDescFlagMisAsignaciones().equals("1")) {
                        filtroLiquidacionAux.setUsuarioAsignado(auditoriaDTO.getUsername());
                }

                if (filtroLiquidacionAux.getDescFlagMisAsignaciones().equals("2")) {
                        filtroLiquidacionAux.setNoUsuarioOrigen(auditoriaDTO.getUsername());
                }

                if (filtroLiquidacionAux.getTextoBusqueda().equals("")) {
                        filtroLiquidacionAux.setTextoBusqueda(null);
                }
                

                Page<PgimLiquidacionAuxDTO> lPgimLiquidacionAuxDTO = this.liquidacionAuxRepository.listarLiquidaciones(
                                filtroLiquidacionAux.getNuLiquidacion(),
                                filtroLiquidacionAux.getNuContrato(),
                                filtroLiquidacionAux.getNoRazonSocialSupervisora(),
                                filtroLiquidacionAux.getNuExpedienteSiged(),
                                filtroLiquidacionAux.getIdTipoEntregableLiquidacion(),
                                filtroLiquidacionAux.getIdFaseActual(),
                                filtroLiquidacionAux.getIdPasoActual(),
                                filtroLiquidacionAux.getIdEspecialidad(),
                                filtroLiquidacionAux.getDescFlagMisAsignaciones(),
                                filtroLiquidacionAux.getNoUsuarioOrigen(),
                                filtroLiquidacionAux.getUsuarioAsignado(),
                                filtroLiquidacionAux.getPersonaAsignada(),
                                filtroLiquidacionAux.getTextoBusqueda(),
                                paginador);

                return lPgimLiquidacionAuxDTO;
        }

        @Override
        public Page<PgimContrLiquidacionAuxDTO> listarContrLiquidacionAux(PgimContrLiquidacionAuxDTO filtroLiquidacionAux,
                        Pageable paginador) {
                
                Page<PgimContrLiquidacionAuxDTO> lPgimContrLiquidacionAuxDTO = this.contrLiquidacionAuxRepository.listarContrLiquidacionAuxPaginado(
                        filtroLiquidacionAux.getIdEspecialidad(),
                        filtroLiquidacionAux.getIdDivisionSupervisora(),
                        filtroLiquidacionAux.getCoSupervision(),
                        filtroLiquidacionAux.getCoUnidadMinera(),
                        filtroLiquidacionAux.getIdFaseDestinoFisc(),
                        filtroLiquidacionAux.getIdPasoDestinoFisc(),
                        filtroLiquidacionAux.getIdTipoEntregable(),
                        filtroLiquidacionAux.getNuLiquidacion(),
                        filtroLiquidacionAux.getCoExpSigedLiquidacion(),
                        filtroLiquidacionAux.getIdFaseDestinoLiquidacion(),
                        filtroLiquidacionAux.getIdPasoDestinoLiquidacion(),      
                        filtroLiquidacionAux.getIdEstadoConsumo(),                  
                        paginador);
        
                return lPgimContrLiquidacionAuxDTO;
        }      

        @Override
        public PgimLiquidacionDTO obtenerLiquidacionPorId(Long idLiquidacion) {
                return this.liquidacionRepository.obtenerLiquidacionPorId(idLiquidacion);
        }

        @Transactional(readOnly = false)
        @Override
        public void eliminarLiquidacion(PgimLiquidacion pgimLiquidacionActual, AuditoriaDTO auditoriaDTO) {
                pgimLiquidacionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                pgimLiquidacionActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimLiquidacionActual.setUsActualizacion(auditoriaDTO.getUsername());
                pgimLiquidacionActual.setIpActualizacion(auditoriaDTO.getTerminal());

                this.liquidacionRepository.save(pgimLiquidacionActual);
        }

        @Override
        public PgimLiquidacion findById(Long idLiquidacion) {
                return liquidacionRepository.findById(idLiquidacion).orElse(null);
        }

        @Override

        public List<PgimLiquidacionDTO> obtenerEntregablesALiquidar(Long idLiquidacion, Long idTipoEntregable)
                        throws Exception {

                Long iTipoEntregable = 0L;
                Long idSubcatDocumento = 0L;
                Long idRelacionPaso = 0L;

                List<PgimLiquidacionDTO> lPgimLiquidacionDTO = new LinkedList<PgimLiquidacionDTO>();
                if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS)) {
                        iTipoEntregable = ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS;
                        idSubcatDocumento = ConstantesUtil.PARAM_SUBCAT_DOC_AS;
                        idRelacionPaso = ConstantesUtil.PARAM_RELACION_PREACTASUPER_ELAINFO;

                        lPgimLiquidacionDTO = this.liquidacionRepository.obtenerActasALiquidar(idLiquidacion,
                                        iTipoEntregable, idSubcatDocumento, idRelacionPaso);
                } else if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES)) {
                        iTipoEntregable = ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES;
                        idSubcatDocumento = ConstantesUtil.PARAM_SUBCAT_DOC_IS;
                        idRelacionPaso = ConstantesUtil.PARAM_RP_REVISAR_APROBAR_INF_FISCALIZACION_MEMOOFICIO_CONFORMIDAD;

                        lPgimLiquidacionDTO = this.liquidacionRepository.obtenerInformesSupervisionALiquidar(
                                        idLiquidacion, iTipoEntregable, idSubcatDocumento, idRelacionPaso);
                } else if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA)) {
                        iTipoEntregable = ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA;
                        idSubcatDocumento = ConstantesUtil.PARAM_SUBCAT_DOC_IS;
                        idRelacionPaso = ConstantesUtil.PARAM_RELACION_FIRMARMEMO_COMPLETARSUPFALLIDA;

                        lPgimLiquidacionDTO = this.liquidacionRepository.obtenerInformesSupervisionALiquidar(
                                        idLiquidacion, iTipoEntregable, idSubcatDocumento, idRelacionPaso);
                } else {
                        throw new PgimException("error",
                                        "Aún no se ha implementado este tipo de entregable " + idTipoEntregable);
                }

                return lPgimLiquidacionDTO;
        }

        @Override
        public List<PgimLiquidacionDTO> listarPorNumeroLiquidacion(String palabra) {
                List<PgimLiquidacionDTO> lPgimLiquidacionDTO = this.liquidacionRepository
                                .listarPorNumeroLiquidacion(palabra);
                return lPgimLiquidacionDTO;
        }

        @Override
        public List<PgimItemLiquidacionDTO> listarItemLiquidacionActasInformes(Long idLiquidacion) {
                List<PgimItemLiquidacionDTO> lItemLiquidacionActasInformes = this.itemLiquidacionRepository.listarItemLiquidacionActasInformes(idLiquidacion);
                List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTO = new LinkedList<PgimItemLiquidacionDTO>();

                for (PgimItemLiquidacionDTO pgimItemLiquidacionDTO : lItemLiquidacionActasInformes){
                        PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoService.obtenerAgenteSupervisadoPorInstancProceso(pgimItemLiquidacionDTO.getDescIdInstanciaProceso());
                        pgimItemLiquidacionDTO.setDescNoRazonSocial(pgimAgenteSupervisadoDTO.getDescNoRazonSocial());
                        lPgimItemLiquidacionDTO.add(pgimItemLiquidacionDTO);
                }
                return lPgimItemLiquidacionDTO;
        }

        @Override
        public List<PgimEntregableLiquidaAuxDTO> listarEntregablesPorLiquidacion(Long idLiquidacion) {
                PgimLiquidacion pgimLiquidacion = this.liquidacionRepository.findById(idLiquidacion).orElse(null);
                Long idTipoEntregable = pgimLiquidacion.getTipoEntregable().getIdValorParametro();

                return this.entregableLiquidacionAuxRepository.listarEntregablesPorLiquidacion(idLiquidacion,
                                idTipoEntregable);
        }

        @Transactional(readOnly = false)
        @Override
        public void eliminarItemLiquidacion(PgimItemLiquidacion pgimItemLiquidacionActual, AuditoriaDTO auditoriaDTO) {
                pgimItemLiquidacionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

                pgimItemLiquidacionActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimItemLiquidacionActual.setUsActualizacion(auditoriaDTO.getUsername());
                pgimItemLiquidacionActual.setIpActualizacion(auditoriaDTO.getTerminal());

                this.itemLiquidacionRepository.save(pgimItemLiquidacionActual);

        }

        @Override
        public PgimItemLiquidacion findByIdIL(Long idItemLiquidacion) {
                return itemLiquidacionRepository.findById(idItemLiquidacion).orElse(null);
        }

        @Override
        public PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorInstanciaProceso(Long idInstanciaProceso) {
                return this.liquidacionAuxRepository.obtenerLiquidacionAuxPorInstanciaProceso(idInstanciaProceso);
        }

        @Transactional(readOnly = false)
        @Override
        public List<PgimItemLiquidacionDTO> crearItemLiquidacion(List<PgimLiquidacionDTO> lPgimLiquidacionDTO,
                        AuditoriaDTO auditoriaDTO) throws Exception {

                List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTOCreado = new LinkedList<PgimItemLiquidacionDTO>();

                for (PgimLiquidacionDTO pgimLiquidacionDTO : lPgimLiquidacionDTO) {
                        PgimItemLiquidacion pgimItemLiquidacion = new PgimItemLiquidacion();

                        PgimLiquidacion pgimLiquidacion = new PgimLiquidacion();
                        pgimLiquidacion.setIdLiquidacion(pgimLiquidacionDTO.getIdLiquidacion());
                        pgimItemLiquidacion.setPgimLiquidacion(pgimLiquidacion);

                        PgimItemConsumo pgimItemConsumo = new PgimItemConsumo();
                        pgimItemConsumo.setIdItemConsumo(pgimLiquidacionDTO.getDescIdItemConsumo());
                        pgimItemLiquidacion.setPgimItemConsumo(pgimItemConsumo);

                        PgimDocumento pgimDocumento = new PgimDocumento();
                        pgimDocumento.setIdDocumento(pgimLiquidacionDTO.getDescIdDocumento());
                        pgimItemLiquidacion.setPgimDocumento(pgimDocumento);

                        pgimItemLiquidacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimItemLiquidacion.setFeCreacion(auditoriaDTO.getFecha());
                        pgimItemLiquidacion.setUsCreacion(auditoriaDTO.getUsername());
                        pgimItemLiquidacion.setIpCreacion(auditoriaDTO.getTerminal());
                        PgimItemLiquidacion pgimItemLiquidacionCreado = itemLiquidacionRepository
                                        .save(pgimItemLiquidacion);
                        PgimItemLiquidacionDTO pgimItemLiquidacionDTOCreado = itemLiquidacionRepository
                                        .obtenerItemLiquidacionPorId(pgimItemLiquidacionCreado.getIdItemLiquidacion());
                        lPgimItemLiquidacionDTOCreado.add(pgimItemLiquidacionDTOCreado);
                }

                return lPgimItemLiquidacionDTOCreado;
        }

        @Override
        public void validarTransicionPasoProceso(PgimRelacionPaso pgimRelacionPaso,
                        PgimInstanciaPaso pgimInstanciaPaso) {

                String msjExcepcionControlada = "";

                Long idInstanciaProceso = pgimInstanciaPaso.getPgimInstanciaProces().getIdInstanciaProceso();
                PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso)
                                .orElse(null);

                PgimLiquidacion pgimLiquidacion = this.liquidacionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                Long idRelacionPaso = pgimRelacionPaso.getIdRelacionPaso();

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_REVISARSOLLIQ)) {

                        List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTO = this.itemLiquidacionRepository
                                        .obtenerItemsLiquidacionTipoEntregable(pgimLiquidacion.getIdLiquidacion(),
                                                        pgimLiquidacion.getTipoEntregable().getIdValorParametro());

                        if (lPgimItemLiquidacionDTO.size() == 0) {
                                msjExcepcionControlada = "No se puede asignar el paso porque aún no se han definido los ítems de la liquidación";
                                throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                        }
                }

                if (idRelacionPaso.equals(ConstRelacionPasoLiquidacion.ELABORARFIRMARLIQUIDACION_FIRMARPORSUPERVISORA)
                                || idRelacionPaso.equals(ConstRelacionPasoLiquidacion.FIRMARLIQUIDACION_TRAMITARLIQUIDACION)) {

                        List<Long> lIdSubcatDocFiltro = new ArrayList<Long>();

                        if (pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                        .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_ACTAS);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_ACTA_FISCALIZACION);

                        } else if (pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                        .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_SUPERVISION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_FICHA_CONFORMIDAD_FISCALIZACION);

                                String flPenalidadReemplazoPersona = (pgimLiquidacion.getFlPenalidadReemplazoPersona() == null ? "0": pgimLiquidacion.getFlPenalidadReemplazoPersona());
                                if (flPenalidadReemplazoPersona.equals("1")) {
                                        lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.INFORME_APLICACION_PENALIDAD_REEMPLAZO);
                                }

                        } else if (pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                        .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_SUPERVISION_FALLIDA);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_INFORME_FISCALIZACION_FALLIDA);

                        } else if (pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                        .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION)) {
                                lIdSubcatDocFiltro
                                                .add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_GESTION);
                        } else {
                                msjExcepcionControlada = "El tipo de entregable aún no fue implementado";
                                throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                        }

                        List<PgimDocumentoDTO> lPgimDocumentoDTO = null;

                        List<Long> lIdSubcatDocumentoNoEncontrados = new ArrayList<Long>();

                        for (Long idSubcatDocumento : lIdSubcatDocFiltro) {
                                lPgimDocumentoDTO = this.documentoRepository
                                                .obtenerDocPorInstanciaYSubCategoria(idInstanciaProceso,
                                                                idSubcatDocumento);

                                if (lPgimDocumentoDTO.size() > 0) {
                                        continue;
                                }

                                lIdSubcatDocumentoNoEncontrados.add(idSubcatDocumento);                                
                        }

                        PgimSubcategoriaDoc pgimSubcategoriaDoc = null;

                        for (Long idSubCategoriaDocumentoNoEncontrado : lIdSubcatDocumentoNoEncontrados) {
                                pgimSubcategoriaDoc = this.subcategoriaDocRepository
                                                .findById(idSubCategoriaDocumentoNoEncontrado).orElse(null);

                                if (msjExcepcionControlada.equals("")) {
                                        msjExcepcionControlada = String.format("%s: %s",
                                                        pgimSubcategoriaDoc.getCoSubcatDocumento(),
                                                        pgimSubcategoriaDoc.getNoSubcatDocumento());
                                } else {
                                        msjExcepcionControlada = String.format("%s<br /> %s: %s",
                                                        msjExcepcionControlada,
                                                        pgimSubcategoriaDoc.getCoSubcatDocumento(),
                                                        pgimSubcategoriaDoc.getNoSubcatDocumento());
                                }
                                
                        }

                        if (!msjExcepcionControlada.equals("")) {
                                msjExcepcionControlada = String.format(
                                                "Aún no adjuntó las subcategorías de documento siguientes: <br /><br /> %s",
                                                msjExcepcionControlada);
                                throw new PgimException(TipoResultado.WARNING, msjExcepcionControlada);
                        }
                }
        }

        @Override
        public PgimLiquidacionAuxDTO obtenerLiquidacionAuxPorId(Long idLiquidacion) {
                return this.liquidacionAuxRepository.obtenerLiquidacionAuxPorId(idLiquidacion);
        }

        @Override
        public void realizarAccionesPorTransicion(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) {
                PgimLiquidacion pgimLiquidacion = this.liquidacionRepository
                                .findById(pgimInstanciaProces.getCoTablaInstancia()).orElse(null);

                Long idRelacionPaso = pgimInstanciaPaso.getPgimRelacionPaso().getIdRelacionPaso();
                Long nuevoEstadio = null;

                if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REVISARPENAL_ENVIARLIQPAGO)
                                || idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_REVISAROBSPNENAL_ENVIARLIQPAGO)
                                || idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_TRAMILIQ_ENVIARLIQPAGO)) {
                        // Actualizar los ítems de la liquidación de Comprometido -> Por Liquidar
                        nuevoEstadio = ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_PORLIQUIDAR;
                } else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ENVIARLIQPAGO_REVISARSOLLIQ)) {
                        // Actualizar los ítems de la liquidación de Por Liquidado -> Comprometido
                        nuevoEstadio = ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_COMPROMETIDO;

                } else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ENVIARLIQPAGO_TRAMFACTURA)) {
                        // Actualizar los ítems de la liquidación de Por Liquidar -> Liquidado
                        nuevoEstadio = ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_LIQUIDADO;

                } else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_COMPLETARLIQ_LIQCOMPLETA)) {
                        // Actualizar los ítems de la liquidación de Por Liquidado -> Facturado
                        nuevoEstadio = ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_FACTURADO;
                        // PGIM 5002
                        this.copiarDocsLiquidacionAExpedienteContrato(pgimInstanciaProces, pgimInstanciaPaso,
                                        pgimLiquidacion, auditoriaDTO);
                } else if (idRelacionPaso.equals(ConstantesUtil.PARAM_RELACION_ELABORARSOLLQ_LIQANULADA)) {
                        // Actualizar los ítems de la liquidación de x -> Facturado
                        nuevoEstadio = ConstantesUtil.PARAM_TIPO_ESTADIO_CONSUMO_COMPROMETIDO;
                }

                if (nuevoEstadio != null) {
                        this.procesarCambioEstadio(pgimLiquidacion, nuevoEstadio, auditoriaDTO);
                }
        }

        /**
         * Permite procesar el cambio de estadio de los ítems de consumo del contrato.
         * 
         * @param pgimLiquidacion
         * @param nuevoEstadio
         * @param auditoriaDTO
         */
        private void procesarCambioEstadio(PgimLiquidacion pgimLiquidacion, Long nuevoEstadio,
                        AuditoriaDTO auditoriaDTO) {

                List<PgimItemLiquidacionDTO> lPgimItemLiquidacionDTO = this.itemLiquidacionRepository
                                .obtenerItemsLiquidacionTipoEntregable(pgimLiquidacion.getIdLiquidacion(),
                                                pgimLiquidacion.getTipoEntregable().getIdValorParametro());

                PgimItemConsumo pgimItemConsumo = null;
                PgimItemConsumo pgimItemConsumoNuevo = null;
                PgimItemLiquidacion pgimItemLiquidacion = null;

                for (PgimItemLiquidacionDTO pgimItemLiquidacionDTO : lPgimItemLiquidacionDTO) {
                        pgimItemConsumo = this.itemConsumoContratoRepository
                                        .findById(pgimItemLiquidacionDTO.getIdItemConsumo()).orElse(null);

                        pgimItemLiquidacion = this.itemLiquidacionRepository
                                        .findById(pgimItemLiquidacionDTO.getIdItemLiquidacion()).orElse(null);

                        if (!pgimItemConsumo.getEsVigente().equals("1")) {
                                continue;
                        }

                        pgimItemConsumo.setEsVigente("0");
                        pgimItemConsumo.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimItemConsumo.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimItemConsumo.setIpActualizacion(auditoriaDTO.getTerminal());

                        this.itemConsumoContratoRepository.save(pgimItemConsumo);

                        // Creamos el nuevo registro
                        pgimItemConsumoNuevo = new PgimItemConsumo();

                        pgimItemConsumoNuevo.setPgimConsumoContra(new PgimConsumoContra());
                        pgimItemConsumoNuevo.getPgimConsumoContra().setIdConsumoContra(
                                        pgimItemConsumo.getPgimConsumoContra().getIdConsumoContra());
                        pgimItemConsumoNuevo.setTipoEntregable(new PgimValorParametro());
                        pgimItemConsumoNuevo.getTipoEntregable()
                                        .setIdValorParametro(pgimItemConsumo.getTipoEntregable().getIdValorParametro());
                        pgimItemConsumoNuevo.setTipoEstadioConsumo(new PgimValorParametro());
                        pgimItemConsumoNuevo.getTipoEstadioConsumo().setIdValorParametro(nuevoEstadio);
                        pgimItemConsumoNuevo.setPcEntregable(pgimItemConsumo.getPcEntregable());
                        pgimItemConsumoNuevo.setMoItemConsumo(pgimItemConsumo.getMoItemConsumo());
                        pgimItemConsumoNuevo.setMoItemPenalidad(pgimItemConsumo.getMoItemPenalidad());
                        pgimItemConsumoNuevo.setMoItemSupervisionFallida(pgimItemConsumo.getMoItemSupervisionFallida());
                        pgimItemConsumoNuevo.setEsVigente("1");

                        pgimItemConsumoNuevo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimItemConsumoNuevo.setFeCreacion(auditoriaDTO.getFecha());
                        pgimItemConsumoNuevo.setUsCreacion(auditoriaDTO.getUsername());
                        pgimItemConsumoNuevo.setIpCreacion(auditoriaDTO.getTerminal());

                        pgimItemConsumoNuevo = this.itemConsumoContratoRepository.save(pgimItemConsumoNuevo);

                        // Actualizamos el nuevo item consumo del contrato sobre el item de la
                        // liquidación
                        pgimItemLiquidacion.setPgimItemConsumo(new PgimItemConsumo());
                        pgimItemLiquidacion.getPgimItemConsumo()
                                        .setIdItemConsumo(pgimItemConsumoNuevo.getIdItemConsumo());

                        pgimItemLiquidacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                        pgimItemLiquidacion.setFeActualizacion(auditoriaDTO.getFecha());
                        pgimItemLiquidacion.setUsActualizacion(auditoriaDTO.getUsername());
                        pgimItemLiquidacion.setIpActualizacion(auditoriaDTO.getTerminal());

                        this.itemLiquidacionRepository.save(pgimItemLiquidacion);
                }
        }

        @Override
        public List<PgimRelacionPasoDTO> filtrarPasosSiguientes(
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimInstanciaPaso pgimInstanciaPasoActual, PgimRelacionPaso pgimRelacionPasoActual) {

                Long idInstanciaProceso = pgimInstanciaPasoActual.getPgimInstanciaProces().getIdInstanciaProceso();
                PgimInstanciaProces pgimInstanciaProces = instanciaProcesRepository.findById(idInstanciaProceso)
                                .orElse(null);

                Long idLiquidacion = pgimInstanciaProces.getCoTablaInstancia();
                PgimLiquidacion pgimLiquidacion = this.liquidacionRepository.findById(idLiquidacion).orElse(null);

                lPgimRelacionPasoDTOSiguientes = this.filtrarPasosSiguientesPenalidades(pgimLiquidacion,
                                lPgimRelacionPasoDTOSiguientes, pgimRelacionPasoActual);

                return lPgimRelacionPasoDTOSiguientes;
        }

        private List<PgimRelacionPasoDTO> filtrarPasosSiguientesPenalidades(PgimLiquidacion pgimLiquidacion,
                        List<PgimRelacionPasoDTO> lPgimRelacionPasoDTOSiguientes,
                        PgimRelacionPaso pgimRelacionPasoActual) {
                if (pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS)
                                || pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                                .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA)
                                || pgimLiquidacion.getTipoEntregable().getIdValorParametro()
                                                .equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION)) {
                        // Entonces debemos quitar la relación
                        lPgimRelacionPasoDTOSiguientes = lPgimRelacionPasoDTOSiguientes.stream()
                                        .filter(pgimRelacionPasoDTO -> {
                                                boolean resultado = false;

                                                resultado = !(pgimRelacionPasoDTO.getIdRelacionPaso().equals(
                                                                ConstantesUtil.PARAM_RELACION_TRAMITARLIQ_REGISPENALIDAD));

                                                return resultado;
                                        }).collect(Collectors.toList());

                }

                return lPgimRelacionPasoDTOSiguientes;
        }

        @Override
        public Page<PgimLiquidacionAuxDTO> listarLiquidacionesContrato(Long idContrato,
                        PgimLiquidacionAuxDTO filtroLiquidacionAux, Pageable paginador, AuditoriaDTO auditoriaDTO) {

                // if (filtroLiquidacionAux.getDescFlagMisAsignaciones().equals("1")) {
                // filtroLiquidacionAux.setUsuarioAsignado(auditoriaDTO.getUsername());
                // }

                // if (filtroLiquidacionAux.getTextoBusqueda().equals("")) {
                // filtroLiquidacionAux.setTextoBusqueda(null);
                // }

                Page<PgimLiquidacionAuxDTO> lPgimLiquidacionAuxDTO = this.liquidacionAuxRepository
                                .listarLiquidacionesContrato(idContrato, filtroLiquidacionAux.getNuLiquidacion(),
                                                filtroLiquidacionAux.getNuContrato(),
                                                filtroLiquidacionAux.getNoRazonSocialSupervisora(),
                                                filtroLiquidacionAux.getNuExpedienteSiged(),
                                                filtroLiquidacionAux.getIdTipoEntregableLiquidacion(),
                                                filtroLiquidacionAux.getIdFaseActual(),
                                                filtroLiquidacionAux.getIdPasoActual(),
                                                filtroLiquidacionAux.getIdEspecialidad(),
                                                // filtroLiquidacionAux.getDescFlagMisAsignaciones(),
                                                // filtroLiquidacionAux.getUsuarioAsignado(),
                                                // filtroLiquidacionAux.getPersonaAsignada(),
                                                filtroLiquidacionAux.getTextoBusqueda(), paginador);

                return lPgimLiquidacionAuxDTO;
        }

        @Override
        public PgimItemLiquidacionDTO crearItemLiquidacionInformePenalidad(
                        @Valid PgimLiquidacionDTO lPgimLiquidacionDTO, AuditoriaDTO auditoriaDTO) throws Exception {
                return null;
        }

        @Transactional(readOnly = false)
        @Override
        public PgimItemLiquidacionDTO modificarItemLiquidacionInfoPenalidad(
                        @Valid PgimItemLiquidacionDTO pgimItemLiquidacionDTO, AuditoriaDTO auditoriaDTO) {
                PgimItemLiquidacion pgimItemLiquidacion = null;
                Optional<PgimItemLiquidacion> itemLiquidacion = itemLiquidacionRepository
                                .findById(pgimItemLiquidacionDTO.getIdItemLiquidacion());
                pgimItemLiquidacion = itemLiquidacion.get();

                this.configurarValores(pgimItemLiquidacionDTO, pgimItemLiquidacion);

                pgimItemLiquidacion.setFeActualizacion(auditoriaDTO.getFecha());
                pgimItemLiquidacion.setUsActualizacion(auditoriaDTO.getUsername());
                pgimItemLiquidacion.setIpActualizacion(auditoriaDTO.getTerminal());

                this.itemLiquidacionRepository.save(pgimItemLiquidacion);

                PgimItemLiquidacionDTO pgimPersonalContratoDTOModificada = itemLiquidacionRepository
                                .obtenerItemLiquidacionPorId(
                                                pgimItemLiquidacion.getIdItemLiquidacion());

                return pgimPersonalContratoDTOModificada;
        }

        @Transactional(readOnly = false)
        private void configurarValores(PgimItemLiquidacionDTO pgimItemLiquidacionDTO,
                        PgimItemLiquidacion pgimItemLiquidacion) {

                pgimItemLiquidacion.setPgimLiquidacion(new PgimLiquidacion());
                pgimItemLiquidacion.getPgimLiquidacion().setIdLiquidacion(pgimItemLiquidacionDTO.getIdLiquidacion());

                pgimItemLiquidacion.setPgimItemConsumo(new PgimItemConsumo());
                pgimItemLiquidacion.getPgimItemConsumo().setIdItemConsumo(pgimItemLiquidacionDTO.getIdItemConsumo());

                pgimItemLiquidacion.setPgimDocumento(new PgimDocumento());
                pgimItemLiquidacion.getPgimDocumento().setIdDocumento(pgimItemLiquidacionDTO.getIdDocumento());

                // Penalidad por incumplimiento de plazo
                if (pgimItemLiquidacionDTO.getMoPenalidadPlazo() == null) {
                        pgimItemLiquidacion.setMoPenalidadPlazo(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadPlazo(pgimItemLiquidacionDTO.getMoPenalidadPlazo());
                }

                // Penalidad por reincidencia de observaciones
                if (pgimItemLiquidacionDTO.getMoPenalidadReincidencia() == null) {
                        pgimItemLiquidacion.setMoPenalidadReincidencia(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadReincidencia(
                                        pgimItemLiquidacionDTO.getMoPenalidadReincidencia());
                }

                // Por realizar fiscalización sin EPP
                if (pgimItemLiquidacionDTO.getMoPenalidadSinEpp() == null) {
                        pgimItemLiquidacion.setMoPenalidadSinEpp(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadSinEpp(pgimItemLiquidacionDTO.getMoPenalidadSinEpp());
                }

                // Por usar equipos de protección personal (EPP) del agente fiscalizado
                if (pgimItemLiquidacionDTO.getMoPenalidadEppAFiscalizado() == null) {
                        pgimItemLiquidacion.setMoPenalidadEppAFiscalizado(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadEppAFiscalizado(
                                        pgimItemLiquidacionDTO.getMoPenalidadEppAFiscalizado());
                }

                // Contar con equipos defectuosos
                if (pgimItemLiquidacionDTO.getMoPenalidadEqpDefectuosos() == null) {
                        pgimItemLiquidacion.setMoPenalidadEqpDefectuosos(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadEqpDefectuosos(
                                        pgimItemLiquidacionDTO.getMoPenalidadEqpDefectuosos());
                }

                // Contar con instrumentos de medición defectuosos
                if (pgimItemLiquidacionDTO.getMoPenalidadEqpMedicionDefectuosos() == null) {
                        pgimItemLiquidacion.setMoPenalidadEqpMedicionDefectuosos(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadEqpMedicionDefectuosos(
                                        pgimItemLiquidacionDTO.getMoPenalidadEqpMedicionDefectuosos());
                }

                // Sin contar con equipos
                if (pgimItemLiquidacionDTO.getMoPenalidadSinEquipos() == null) {
                        pgimItemLiquidacion.setMoPenalidadSinEquipos(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadSinEquipos(
                                        pgimItemLiquidacionDTO.getMoPenalidadSinEquipos());
                }

                // Equipos sin certificado de calibración vigente
                if (pgimItemLiquidacionDTO.getMoPenalidadEqpCalibrNvigente() == null) {
                        pgimItemLiquidacion.setMoPenalidadEqpCalibrNvigente(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadEqpCalibrNvigente(
                                        pgimItemLiquidacionDTO.getMoPenalidadEqpCalibrNvigente());
                }

                // Sin contar con instrumentos de medición
                if (pgimItemLiquidacionDTO.getMoPenalidadSinInstrumentos() == null) {
                        pgimItemLiquidacion.setMoPenalidadSinInstrumentos(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadSinInstrumentos(
                                        pgimItemLiquidacionDTO.getMoPenalidadSinInstrumentos());
                }

                // Instrumentos de medición sin certificado de calibración vigente
                if (pgimItemLiquidacionDTO.getMoPenalidadInstrCalibrNvigente() == null) {
                        pgimItemLiquidacion.setMoPenalidadInstrCalibrNvigente(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadInstrCalibrNvigente(
                                        pgimItemLiquidacionDTO.getMoPenalidadInstrCalibrNvigente());
                }

                // Por alterar los formatos de las actas proporcionados
                if (pgimItemLiquidacionDTO.getMoPenalidadAlterarFormatos() == null) {
                        pgimItemLiquidacion.setMoPenalidadAlterarFormatos(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadAlterarFormatos(
                                        pgimItemLiquidacionDTO.getMoPenalidadAlterarFormatos());
                }

                // Por frustrar la fiscalización ordenada por causa imputable a la EST (Empresa supervisora técnica)
                if (pgimItemLiquidacionDTO.getMoPenalidadFrustrarFiscalizacion() == null) {
                        pgimItemLiquidacion.setMoPenalidadFrustrarFiscalizacion(ConstantesUtil.MO_PENALIDAD);
                } else {
                        pgimItemLiquidacion.setMoPenalidadFrustrarFiscalizacion(
                                        pgimItemLiquidacionDTO.getMoPenalidadFrustrarFiscalizacion());
                }

        }

        @Override
        public PgimItemLiquidacionDTO obtenerItemLiquidacionPorId(Long idItemLiquidacion) {
                return this.itemLiquidacionRepository.obtenerItemLiquidacionPorId(idItemLiquidacion);
        }

        @Override
        public PgimLiquidacionDTO validarPenalidadPorEspecialidad(Long idLiquidacion) {
                return this.liquidacionRepository.validarPenalidadPorEspecialidad(idLiquidacion);
        }

        @Override
        public PgimItemLiquidacion getByIdItemLiquidacion(Long idItemLiquidacion) {
                return this.itemLiquidacionRepository.findById(idItemLiquidacion).orElse(null);
        }

        @Override
        public List<PgimSubcategoriaDocDTO> filtrarSubCategoriasDoc(Long codTablaInstancia, Long idInstanciaPaso, 
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO) throws Exception {

                PgimLiquidacion pgimLiquidacion = this.liquidacionRepository.findById(codTablaInstancia).orElse(null);

                PgimInstanciaPaso pgimInstanciaPaso = this.instanciaPasoRepository
                                .findById(idInstanciaPaso).orElse(null);

                Long idPasoProceso = pgimInstanciaPaso.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso();

                if (idPasoProceso.equals(ConstPasoProcesoLiquidacion.SOLICITAR_LIQUIDACION)
                                || idPasoProceso.equals(ConstPasoProcesoLiquidacion.ELABORAR_FIRMAR_LIQUIDACION)
                                || idPasoProceso.equals(ConstPasoProcesoLiquidacion.FIRMAR_LIQUIDACION)
                                || idPasoProceso.equals(ConstPasoProcesoLiquidacion.TRAMITAR_SOLICITUD_LIQUIDACION)
                                || idPasoProceso.equals(ConstPasoProcesoLiquidacion.LIQUIDACION_ANULADA)) {

                        List<Long> lIdSubcatDocFiltro = new ArrayList<Long>();

                        Long idTipoEntregable = pgimLiquidacion.getTipoEntregable().getIdValorParametro();

                        if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_ACTAS)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_ACTAS);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_ACTA_FISCALIZACION);

                        } else if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_SUPERVISION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.INFORME_APLICACION_PENALIDAD_REEMPLAZO);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_INFORME_FISCALIZACION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_FICHA_CONFORMIDAD_FISCALIZACION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_FICHA_OBSERVACIONES_FISCALIZACION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_INFORME_JUSTIFICACION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_INFORME_SUSTENTATORIO_PENALIDAD);

                        } else if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMES_FALLIDA)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_SUPERVISION_FALLIDA);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.COPIA_INFORME_FISCALIZACION_FALLIDA);

                        } else if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION)) {

                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_DE_INFORMES_GESTION);
                                lIdSubcatDocFiltro.add(ConstSubCategoriaDocumento.LIQUIDACION_INFORME_DE_GESTION);

                        } else {
                                String mensajeExcepcion = String.format(
                                                "El Tipo de entregable aún no ha sido implementado: d%",
                                                idTipoEntregable);
                                throw new PgimException("error", mensajeExcepcion);
                        }

                        // Entonces debemos quitar la relación
                        lPgimSubcategoriaDocDTO = this.filtrarSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO,
                                        lIdSubcatDocFiltro);
                }

                return lPgimSubcategoriaDocDTO;
        }

        /**
         * Permite filtrar las subcategorías que estén mapeadas en la lista de
         * subcategorías filtro.
         * 
         * @param lPgimSubcategoriaDocDTO
         * @param idSubcatDocumentoFiltro
         * @return
         */
        private List<PgimSubcategoriaDocDTO> filtrarSubCategoriasPorIdSubcat(
                        List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO,
                        final List<Long> lIdSubcatDocumentoFiltro) {

                lPgimSubcategoriaDocDTO = lPgimSubcategoriaDocDTO.stream()
                                .filter(pgimSubcategoriaDocDTO -> lIdSubcatDocumentoFiltro.contains(pgimSubcategoriaDocDTO.getIdSubcatDocumento()))
                                .collect(Collectors.toList());

                return lPgimSubcategoriaDocDTO;
        }

        @Override
        public List<PgimEntregableLiquidaAuxDTO> obtenerEntregablesALiquidar(Long idContrato, Long idTipoEntregable,
                        Long idDivisionSupervisora, Long idSubtipoSupervision, Long idMotivoSupervision) {
                List<PgimEntregableLiquidaAuxDTO> lPgimEntregableLiquidaAuxDTO = null;

                if (idTipoEntregable.equals(ConstantesUtil.PARAM_TIPO_ENTREGABLE_INFORMESGESTION)) {
                        lPgimEntregableLiquidaAuxDTO = this.entregableLiquidacionAuxRepository
                                        .obtenerEntregablesALiquidar(idContrato, idTipoEntregable);
                } else {
                        lPgimEntregableLiquidaAuxDTO = this.entregableLiquidacionAuxRepository
                                        .obtenerEntregablesALiquidar(idContrato, idTipoEntregable,
                                                        idDivisionSupervisora, idSubtipoSupervision,
                                                        idMotivoSupervision);
                }

                return lPgimEntregableLiquidaAuxDTO;
        }

        @Override
        public Page<PgimItemConsumoDTO> listarEntregablesPorContrato(Long idContrato, Long descPreComprometido,
                        Long descComprometido, Long descPorLiquidar, Long descLiquidado, Long descFacturado,
                        PgimItemConsumoDTO filtro, Pageable paginador) {

                if (descPreComprometido == null) {
                        descPreComprometido = 0L;
                } else if (descComprometido == null) {
                        descComprometido = 0L;
                } else if (descPorLiquidar == null) {
                        descPorLiquidar = 0L;
                } else if (descLiquidado == null) {
                        descLiquidado = 0L;
                } else if (descFacturado == null) {
                        descFacturado = 0L;
                }

                Page<PgimItemConsumoDTO> pPgimItemConsumoDTO = this.itemConsumoContratoRepository
                                .listarEntregablesPorContrato(idContrato,
                                                descPreComprometido, descComprometido,
                                                descPorLiquidar, descLiquidado, descFacturado,
                                                filtro.getDescIdTipoEntregable(), paginador);
                return pPgimItemConsumoDTO;
        }

        /**
         * STORY: PGIM-6809: Lista de entregables disponibles para liquidar
         */
        @Override
        public Page<PgimItemConsumoDTO> listarEntregablesPorLiquidarPorContrato(PgimItemConsumoDTO filtro, Pageable paginador) {

                Page<PgimItemConsumoDTO> pPgimItemConsumoDTO = null;

                try {
                        pPgimItemConsumoDTO = this.itemConsumoContratoRepository
                                        .listarEntregablesPorLiquidarPorContrato(
                                                        filtro.getDescIdContrato(),
                                                        filtro.getDescNuContrato(), 
                                                        filtro.getDescIdTipoEntregable(),
                                                        filtro.getDescCoSupervision(),
                                                        filtro.getDescIdTipoSupervision(),
                                                        filtro.getDescIdSubtipoSupervision(),
                                                        filtro.getDescIdMotivoSupervision(),
                                                        filtro.getTextoBusqueda(), paginador);
                } catch (Exception e) {
                        throw e;
                }

                return pPgimItemConsumoDTO;
        }
        
        @Override
        public List<GrupoEntregableLiquidacionDTO> agruparEntregablesLiquidar(List<PgimItemConsumoDTO> lPgimItemConsumoDTO) throws Exception {

        		List<String> lGruposString = new ArrayList<String>();
        		
        		List<GrupoEntregableLiquidacionDTO> lGruposEntregables = new ArrayList<GrupoEntregableLiquidacionDTO>();

                try {    
            		
                    if(lPgimItemConsumoDTO.size() > 0) {
                    	
                    	// Obtenemos los distintos grupos posibles a partir de la lista de entregables dada 
    		        	lGruposString = lPgimItemConsumoDTO.stream()
    		        			.map(pgimItemConsumoDTO -> this.obtenerEtiquetaGrupo(pgimItemConsumoDTO))
    		        			.distinct()
    							.collect(Collectors.toList());		                        
                        
                        // Filtramos y asociamos los entregables para cada grupo
                    	for (String grupoString : lGruposString) {
							
                    		List<PgimItemConsumoDTO> lEntregablesXGrupo = lPgimItemConsumoDTO.stream()
                    				.filter((pgimItemConsumoDTO) -> {
			                              String etiquetaGrupo = this.obtenerEtiquetaGrupo(pgimItemConsumoDTO);
			                              return (grupoString.equals(etiquetaGrupo));
		                            })
		                            .collect(Collectors.toList());                    		                         
                            
                    		// tomamos en primer entregable_x_grupo como referente para desplegar las propiedades del Grupo
                            PgimItemConsumoDTO refGrupo = lEntregablesXGrupo.get(0); 
                            
                            GrupoEntregableLiquidacionDTO grupoEntregableLiquidacionDTO = new GrupoEntregableLiquidacionDTO(); 
                            
                            grupoEntregableLiquidacionDTO.setIdContrato(refGrupo.getDescIdContrato());
                            grupoEntregableLiquidacionDTO.setIdTipoEntregable(refGrupo.getDescIdTipoEntregable());
                            grupoEntregableLiquidacionDTO.setIdDivisionSupervisora(refGrupo.getDescIdDivisionSupervisora());
                            grupoEntregableLiquidacionDTO.setIdTipoSupervision(refGrupo.getDescIdTipoSupervision());
                            grupoEntregableLiquidacionDTO.setIdSubtipoSupervision(refGrupo.getDescIdSubtipoSupervision());
                            grupoEntregableLiquidacionDTO.setIdMotivoSupervision(refGrupo.getDescIdMotivoSupervision());
                            grupoEntregableLiquidacionDTO.setNuContrato(refGrupo.getDescNuContrato());
                            grupoEntregableLiquidacionDTO.setDeTipoEntregable(refGrupo.getDescDeTipoEntregable());
                            grupoEntregableLiquidacionDTO.setNoDivisionSupervisora(refGrupo.getDescNoDivisionSupervisora());
                            grupoEntregableLiquidacionDTO.setDeTipoSupervision(refGrupo.getDescDeTipoSupervision());
                            grupoEntregableLiquidacionDTO.setDeSubtipoSupervision(refGrupo.getDescDeSubtipoSupervision());
                            grupoEntregableLiquidacionDTO.setDeMotivoSupervision(refGrupo.getDescDeMotivoSupervision());
                            grupoEntregableLiquidacionDTO.setListaEntregables(lEntregablesXGrupo);  
                            
                            Integer cantEntregablesReal = this.itemConsumoContratoRepository.obtenerCantEntregablesXGrupo(
                            		refGrupo.getDescIdContrato(), refGrupo.getDescIdTipoEntregable(), refGrupo.getDescIdDivisionSupervisora(), 
                            		refGrupo.getDescIdTipoSupervision(), refGrupo.getDescIdSubtipoSupervision(), refGrupo.getDescIdMotivoSupervision());
                            
                            grupoEntregableLiquidacionDTO.setCantEntregables(cantEntregablesReal);
                            
                            lGruposEntregables.add(grupoEntregableLiquidacionDTO);

                          }
                    }
                    
                } catch (Exception e) {
                        throw e;
                }

                return lGruposEntregables;
        }
        
        public String obtenerEtiquetaGrupo(PgimItemConsumoDTO pgimItemConsumoDTO) {
        	String etiquetaGrupo = "contrato=" + pgimItemConsumoDTO.getDescIdContrato() + "&" +
  					"tipoEntregable=" + pgimItemConsumoDTO.getDescIdTipoEntregable() + "&" +
  					"ds=" + pgimItemConsumoDTO.getDescIdDivisionSupervisora() + "&" +
  					"tipoSup=" + pgimItemConsumoDTO.getDescIdTipoSupervision() + "&" +
  					"subtipoSup=" + pgimItemConsumoDTO.getDescDeSubtipoSupervision() + "&" +
  					"motivo=" + pgimItemConsumoDTO.getDescDeMotivoSupervision();        
        	
        	return etiquetaGrupo;
        }

        // PGIM 5002
        @Override
        public void copiarDocsLiquidacionAExpedienteContrato(PgimInstanciaProces pgimInstanciaProces,
                        PgimInstanciaPaso pgimInstanciaPaso, PgimLiquidacion pgimLiquidacion,
                        AuditoriaDTO auditoriaDTO) {
                List<PgimDocumentoDTO> lPgimDocumentoDTO = null;

                // OBTENER LISTA DOCUMENTOS PGIM
                try {
                        lPgimDocumentoDTO = this.documentoService
                                        .obtenerDocumentosPgim(pgimInstanciaProces.getIdInstanciaProceso());
                } catch (DataAccessException e) {
                		log.error(e.getMessage(), e);
                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                        "Ocurrió un error al realizar la consulta de los documentos PGIM");
                }

                Documentos documentosYArchivosSiged = new Documentos();

                // Llamar al servicio Siged para completar datos de los documentos
                try {
                        documentosYArchivosSiged = this.documentoService.obtenerExpedienteDocumentoSiged(
                                        pgimInstanciaProces.getNuExpedienteSiged(), "1", auditoriaDTO);
                } catch (Exception e1) {
                		log.error(e1.getMessage(), e1);
                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                        "Ocurrió un error al obtener los documentos del expediente Siged: "
                                                        + pgimInstanciaProces.getNuExpedienteSiged());
                }

                // OBTENER NUMERO EXPEDIENTE DEL CONTRATO
                String nuExpedienteSigedContrato = null;
                PgimInstanciaProcesDTO pgimInstanciaProcesDTOContrato = null;

                // Buscar la instancia proceso del contrato
                pgimInstanciaProcesDTOContrato = this.instanciaProcesRepository.obtenerInstanciaProceso(
                                ConstantesUtil.PARAM_PROCESO_CONTRATO, ConstantesUtil.PARAM_TABLA_TC_CONTRATO,
                                pgimLiquidacion.getPgimContrato().getIdContrato());

                nuExpedienteSigedContrato = pgimInstanciaProcesDTOContrato.getNuExpedienteSiged();
                
                if(nuExpedienteSigedContrato == null) {
                	String mensaje = "No se pudo copiar los documentos de la liquidación al contrato subyacente, debido a que este último no tiene número de expediente Siged, por favor regístrelo y vuelva a intentarlo.";
                	throw new PgimException(TipoResultado.WARNING, mensaje);
                }
                
                // AGREGAR DOCUMENTOS AL EXPEDIENTE SIGED DEL CONTRATO
                DescargaArchivo descargaArchivo = null;                
                InputStream inputStream = null;

                for (PgimDocumentoDTO pgimDocumentoDTO : lPgimDocumentoDTO) {
                        for (Documento docSiged : documentosYArchivosSiged.getListaDocumento()) {
                                if (pgimDocumentoDTO.getCoDocumentoSiged().toString()
                                                .equals(docSiged.getIdDocumento())) {
                                	
                                        for (Archivo archivoSiged : docSiged.getArchivos()) {
                                                try {
                                                        descargaArchivo = this.documentoService.descargaArchivo_old(
                                                                        archivoSiged.getIdArchivo().toString());
                                                } catch (IOException e) {
                                                		log.error(e.getMessage(), e);
                                                        throw new PgimException(
                                                                        TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                                                        "Ocurrió un error al descargar el archivo "
                                                                                        + archivoSiged.getIdArchivo()
                                                                                                        .toString());
                                                }
                                                inputStream = new ByteArrayInputStream(
                                                                descargaArchivo.getFile().toByteArray());

                                                // Obtener el nombre original del archivo
                                                String noOriginalArchivo = archivoSiged.getNombre();

                                                // Añadir como prefijo el numero de la liquidación al nombre del archivo
                                                String nombreCodificado = pgimLiquidacion.getNuLiquidacion() + "-"
                                                                + noOriginalArchivo;

                                                nombreCodificado = CommonsUtil.reemplazarCaracteresParticulares(nombreCodificado);
                                                nombreCodificado = CommonsUtil
                                                                .removerCaracteresEspeciales(nombreCodificado);

                                                // Cambiando nombre al archivo cargado
                                                MultipartFile multipartFile = null;
                                                try {
                                                        multipartFile = new MockMultipartFile(nombreCodificado,
                                                                        inputStream);
                                                } catch (IOException e) {
                                                		log.error(e.getMessage(), e);
                                                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                                                        e.getMessage());
                                                } catch (Exception e) {
                                                		log.error(e.getMessage(), e);
                                                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                                                        e.getMessage());
                                                }                                                

                                                DocumentoNuevo docNuevoSiged = new DocumentoNuevo(nuExpedienteSigedContrato,
                                                                multipartFile.getName(),
                                                                pgimLiquidacion.getNuLiquidacion() + ": "
                                                                                + pgimDocumentoDTO
                                                                                                .getDeAsuntoDocumento(),
                                                                String.valueOf(pgimDocumentoDTO
                                                                                .getCoTipoDocumentoSiged()),
                                                                docSiged.getNroDocumento(),
                                                                pgimDocumentoDTO.getFlNumeradoPorSiged(),
                                                                auditoriaDTO.getCoUsuarioSiged());

                                                // Registrar documento Siged (WS)
                                                DocumentoOutRO documentoOutRO;
                                                try {
                                                        documentoOutRO = this.documentoService
                                                                        .agregarReemplazoDocumentoSiged_old(docNuevoSiged,
                                                                                        multipartFile);
                                                } catch (Exception e) {
                                                		log.error(e.getMessage(), e);
                                                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                                                        "Ocurrió un error al registrar documento al expediente Siged del contrato subayecente a la liquidación, idArchivo: "
                                                                                        + docNuevoSiged.getIdArchivo());
                                                }

                                                // Si hay un error en el consumo del servicio SIGED, debe enviar el
                                                // error
                                                if (!documentoOutRO.getResultCode()
                                                                .equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
                                                        throw new PgimException(TipoResultado.ERROR, //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                                                                                documentoOutRO.getMessage());
                                                }

                                        }
                                }
                        }
                }
        }
        
        
        @Override
        public Integer contarLiquidacionesPendientes(AuditoriaDTO auditoriaDTO) {
        	
        	Integer cantidadPendientes = this.liquidacionAuxRepository.contarLiquidacionesPendientes(auditoriaDTO.getUsername()); 
        	
        	return cantidadPendientes;    	
        }

        @Transactional(readOnly = false)
        @Override
        public PgimLiquidacionDTO aplicarPenalidadReemplazoPersonal(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO) throws Exception{

                PgimLiquidacion pgimLiquidacion = this.findById(pgimLiquidacionDTO.getIdLiquidacion());

                pgimLiquidacion.setFlPenalidadReemplazoPersona(pgimLiquidacionDTO.getFlPenalidadReemplazoPersona());

                pgimLiquidacion.setFeActualizacion(auditoriaDTO.getFecha());
                pgimLiquidacion.setUsActualizacion(auditoriaDTO.getUsername());
                pgimLiquidacion.setIpActualizacion(auditoriaDTO.getTerminal());

                this.liquidacionRepository.save(pgimLiquidacion);

                PgimLiquidacionDTO pgimLiquidacionDTOModificado = this.obtenerLiquidacionPorId(pgimLiquidacion.getIdLiquidacion());

                return pgimLiquidacionDTOModificado;
        }
        
        @Transactional(readOnly = false)
        @Override
        public PgimLiquidacionDTO modificarPenalidadReemplazoPer(PgimLiquidacionDTO pgimLiquidacionDTO, AuditoriaDTO auditoriaDTO) throws Exception{

                PgimLiquidacion pgimLiquidacion = this.findById(pgimLiquidacionDTO.getIdLiquidacion());

                pgimLiquidacion.setMoPenalidadReemplazoPersona(pgimLiquidacionDTO.getMoPenalidadReemplazoPersona());
                
                pgimLiquidacion.setFeActualizacion(auditoriaDTO.getFecha());
                pgimLiquidacion.setUsActualizacion(auditoriaDTO.getUsername());
                pgimLiquidacion.setIpActualizacion(auditoriaDTO.getTerminal());

                this.liquidacionRepository.save(pgimLiquidacion);

                pgimLiquidacionDTO = this.obtenerLiquidacionPorId(pgimLiquidacion.getIdLiquidacion());

                return pgimLiquidacionDTO;
        }
}
