package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAgenteSupervisadoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDetaInfraccionesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfracciontop15AuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxespMesAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxtitularAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfraccionxumAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccion;
import pe.gob.osinergmin.pgim.models.entity.PgimInfraccionContra;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimOblgcnNrmtvaHchoc;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.repository.AgenteSupervisadoRepository;
import pe.gob.osinergmin.pgim.models.repository.DetaInfraccionesRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionContraRepository;
import pe.gob.osinergmin.pgim.models.repository.InfraccionRepository;
import pe.gob.osinergmin.pgim.models.repository.Infracciontop15AuxRepository;
import pe.gob.osinergmin.pgim.models.repository.InstanciaPasoRepository;
import pe.gob.osinergmin.pgim.services.HechoConstatadoService;
import pe.gob.osinergmin.pgim.services.InfraccionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad infracción
 * 
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020
 */
@Service
@Transactional(readOnly = true)
public class InfraccionServiceImpl implements InfraccionService {

        @Autowired
        private InfraccionAuxRepository infraccionAuxRepository;

        @Autowired
        private InfraccionRepository infraccionRepository;
        
        @Autowired
        private InfraccionContraRepository infraccionContraRepository;

        @Autowired
        private Infracciontop15AuxRepository infracciontop15AuxRepository;

        @Autowired
        private DetaInfraccionesRepository detaInfraccionesRepository;
        
        @Autowired
        private AgenteSupervisadoRepository agenteSupervisadoRepository;
        
        @Autowired
        private HechoConstatadoService hechoConstatadoService;        
        
        @Autowired
        private InstanciaPasoRepository instanciaPasoRepository;
        
        
        @Override
        public List<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitular(
                        PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO) throws Exception {
                List<PgimInfraccionxtitularAuxDTO> lPgimInfraccionxtitularAuxDTO = this.infraccionAuxRepository
                                .listarInfraccionxtitular(filtroPgimInfraccionxtitularAuxDTO.getIdEstrato(),
                                                filtroPgimInfraccionxtitularAuxDTO.getDescNuInfraccionesMin());
                return lPgimInfraccionxtitularAuxDTO;
        }

        @Override
        public Page<PgimInfraccionxtitularAuxDTO> listarInfraccionxtitularPaginado(
                        PgimInfraccionxtitularAuxDTO filtroPgimInfraccionxtitularAuxDTO, Pageable paginador)
                        throws Exception {
                Page<PgimInfraccionxtitularAuxDTO> lPgimInfraccionxtitularAuxDTO = this.infraccionAuxRepository
                                .listarInfraccionxtitularPaginado(filtroPgimInfraccionxtitularAuxDTO.getIdEstrato(),
                                                filtroPgimInfraccionxtitularAuxDTO.getDescNuInfraccionesMin(),
                                                paginador);
                return lPgimInfraccionxtitularAuxDTO;
        }

        @Override
        public List<PgimInfraccionxespAuxDTO> listarReporteInfraccionxesp(
                        PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO) throws Exception {
                List<PgimInfraccionxespAuxDTO> lPgimInfraccionxespAuxDTO = this.infraccionAuxRepository
                                .listarReporteInfraccionxesp();
                return lPgimInfraccionxespAuxDTO;
        }

        @Override
        public Page<PgimInfraccionxespAuxDTO> listarReporteInfraccionxespPaginado(
                        PgimInfraccionxespAuxDTO filtroPgimInfraccionxespAuxDTO, Pageable paginador) throws Exception {
                Page<PgimInfraccionxespAuxDTO> lPgimInfraccionxespAuxDTO = this.infraccionAuxRepository
                                .listarReporteInfraccionxespPaginado(paginador);
                return lPgimInfraccionxespAuxDTO;
        }

        @Override
        public Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesPaginado(
                        PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
                        throws Exception {

                Page<PgimDetaInfraccionesAuxDTO> pPgimDetaInfraccionesAuxDTO = this.detaInfraccionesRepository
                                .listarReporteInfraccionesPaginado(
                                                filtroPgimDetaInfraccionesAuxDTO.getFeInicioSupervisionReal(),
                                                filtroPgimDetaInfraccionesAuxDTO.getFeFinSupervisionReal(), paginador);
                return pPgimDetaInfraccionesAuxDTO;
        }

        @Override
        public Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesASPaginado(
                        PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
                        throws Exception {

                Page<PgimDetaInfraccionesAuxDTO> pPgimDetaInfraccionesAuxDTO = this.detaInfraccionesRepository
                                .listarReporteInfraccionesASPaginado(
                                                filtroPgimDetaInfraccionesAuxDTO.getEtiquetaAgenteSupervisado(),
                                                filtroPgimDetaInfraccionesAuxDTO.getDiAgenteSupervisado(), paginador);
                return pPgimDetaInfraccionesAuxDTO;
        }

        @Override
        public Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesUMPaginado(
                        PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
                        throws Exception {

                Page<PgimDetaInfraccionesAuxDTO> pPgimDetaInfraccionesAuxDTO = this.detaInfraccionesRepository
                                .listarReporteInfraccionesUMPaginado(
                                                filtroPgimDetaInfraccionesAuxDTO.getEtiquetaUnidadMinera(),
                                                filtroPgimDetaInfraccionesAuxDTO.getCoUnidadMinera(), paginador);
                return pPgimDetaInfraccionesAuxDTO;
        }

        @Override
        public Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesDSPaginado(
                        PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
                        throws Exception {

                Page<PgimDetaInfraccionesAuxDTO> pPgimDetaInfraccionesAuxDTO = this.detaInfraccionesRepository
                                .listarReporteInfraccionesDSPaginado(
                                                filtroPgimDetaInfraccionesAuxDTO.getIdDivisionSupervisora(), paginador);
                return pPgimDetaInfraccionesAuxDTO;
        }

        @Override
        public Page<PgimDetaInfraccionesAuxDTO> listarReporteInfraccionesEspecPaginado(
                        PgimDetaInfraccionesAuxDTO filtroPgimDetaInfraccionesAuxDTO, Pageable paginador)
                        throws Exception {
                Page<PgimDetaInfraccionesAuxDTO> pPgimDetaInfraccionesAuxDTO = this.detaInfraccionesRepository
                                .listarReporteInfraccionesEspecPaginado(
                                                filtroPgimDetaInfraccionesAuxDTO.getNoEspecialidad(), paginador);
                return pPgimDetaInfraccionesAuxDTO;
        }

        @Override
        public Page<PgimInfracciontop15AuxDTO> listarReporteInfraccionesTop15(
                        PgimInfracciontop15AuxDTO filtroPgimExppasdsuAnioAuxDTO, Pageable paginador) throws Exception {
                System.out.println(filtroPgimExppasdsuAnioAuxDTO.getAnioInfraccion());
                Page<PgimInfracciontop15AuxDTO> lPgimInfracciontop15AuxDTO = this.infracciontop15AuxRepository
                                .listarReporteInfraccionesTop15Paginado(
                                                filtroPgimExppasdsuAnioAuxDTO.getAnioInfraccion(), paginador);
                return lPgimInfracciontop15AuxDTO;
        }

        @Override
        public List<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMes(
                        PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO) throws Exception {
                List<PgimInfraccionxespMesAuxDTO> lPgimInfraccionxespMesAuxDTO = this.infraccionAuxRepository
                                .listarReporteInfraccionxespMes(filtroPgimInfraccionxespMesAuxDTO.getAnio());
                return lPgimInfraccionxespMesAuxDTO;
        }

        @Override
        public Page<PgimInfraccionxespMesAuxDTO> listarReporteInfraccionxespMesPaginado(
                        PgimInfraccionxespMesAuxDTO filtroPgimInfraccionxespMesAuxDTO, Pageable paginador)
                        throws Exception {
                Page<PgimInfraccionxespMesAuxDTO> lPgimInfraccionxespMesAuxDTO = this.infraccionAuxRepository
                                .listarReporteInfraccionxespMesPaginado(filtroPgimInfraccionxespMesAuxDTO.getAnio(),
                                                paginador);
                return lPgimInfraccionxespMesAuxDTO;
        }

        @Override
        public Page<PgimInfraccionxumAuxDTO> listarReporteInfraccionesUmAnioPaginado(
                        PgimInfraccionxumAuxDTO filtroPgimInfraccionxumAuxDTO, Pageable paginador) throws Exception {
                Page<PgimInfraccionxumAuxDTO> lPgimInfraccionxumAuxDTO = this.infraccionAuxRepository
                                .listarReporteInfraccionesUmAnioPaginado(
                                                filtroPgimInfraccionxumAuxDTO.getNroInfraccionTotal(),
                                                filtroPgimInfraccionxumAuxDTO.getIdTipoUnidadMinera(), paginador);
                return lPgimInfraccionxumAuxDTO;
        }

        @Override
        public List<PgimInfraccionDTO> listarHistorialInfraccion(Long idInfraccion) {

                List<PgimInfraccionDTO> lPgimInfraccionDTO = new ArrayList<PgimInfraccionDTO>();

                PgimInfraccion pgimInfraccion = this.infraccionRepository.findById(idInfraccion).orElse(null);
                PgimInfraccionDTO pgimInfraccionDTO = this.infraccionRepository
                                .obtenerInfraccionPorIdHistorial(idInfraccion);

                lPgimInfraccionDTO.add(pgimInfraccionDTO);

                while (pgimInfraccion.getInfraccionOrigen() != null) {
                        pgimInfraccion = this.infraccionRepository
                                        .findById(pgimInfraccion.getInfraccionOrigen().getIdInfraccion()).orElse(null);

                        pgimInfraccionDTO = this.infraccionRepository
                                        .obtenerInfraccionPorIdHistorial(pgimInfraccion.getIdInfraccion());

                        lPgimInfraccionDTO.add(pgimInfraccionDTO);
                }

                if (lPgimInfraccionDTO.size() <= 1) {
                        return lPgimInfraccionDTO;
                }

                return lPgimInfraccionDTO;
        }

        @Override
        public PgimInfraccionDTO obtenerInfraccionPorId(Long idInfraccion) {
                PgimInfraccionDTO pgimInfraccionDTO = this.infraccionRepository.obtenerInfraccionPorId(idInfraccion);

                return pgimInfraccionDTO;
        }
        
        @Override
        public void copiarInfraccionesSupervision(Long idSupervision, PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimPas pgimPas, 
        		Boolean hacerNoVigenteInfraccionBase, AuditoriaDTO auditoriaDTO) throws Exception {
        	
        	Long idPas = (pgimPas == null) ? null : pgimPas.getIdPas();

            List<PgimInfraccionAuxDTO> lPgimInfraccionAuxDTO = this.hechoConstatadoService
                    .listarInfraccionPorIdSupervision(idSupervision);

            for (PgimInfraccionAuxDTO pgimInfraccionAuxDTO : lPgimInfraccionAuxDTO) {   
            	this.realizarCopiaInfraccion(pgimInfraccionAuxDTO, pgimInstanciaPasoDTO.getIdInstanciaPaso(),
                   idPas, hacerNoVigenteInfraccionBase, auditoriaDTO);
            }
        }

        @Override
        public void copiarInfraccionesPas(PgimInstanciaPasoDTO pgimInstanciaPasoDTO, PgimPas pgimPas,
                AuditoriaDTO auditoriaDTO) throws Exception {

            List<PgimInfraccionAuxDTO> lPgimInfraccionAuxDTO = this.hechoConstatadoService
                    .listarInfraccionPorIdPas(pgimPas.getIdPas());

            for (PgimInfraccionAuxDTO pgimInfraccionAuxDTO : lPgimInfraccionAuxDTO) {            	
            	this.realizarCopiaInfraccion(pgimInfraccionAuxDTO, pgimInstanciaPasoDTO.getIdInstanciaPaso(),
                    pgimPas.getIdPas(), true, auditoriaDTO);
            }
        }
        
        @Override
        public PgimInfraccionDTO realizarCopiaInfraccion(PgimInfraccionAuxDTO pgimInfraccionAuxDTO, Long idInstanciaPasoActual,
                Long idPas, Boolean hacerNoVigenteInfraccionBase, AuditoriaDTO auditoriaDTO) throws Exception {

            PgimInfraccion pgimInfraccion2Insert;
            PgimInfraccion pgimInfraccion2Update;

            // Creamos la nueva infracción a partir de la existente
            pgimInfraccion2Insert = new PgimInfraccion();

            pgimInfraccion2Insert.setPgimOblgcnNrmtvaHchoc(new PgimOblgcnNrmtvaHchoc());
            pgimInfraccion2Insert.getPgimOblgcnNrmtvaHchoc()
                    .setIdOblgcnNrmtvaHchoc(pgimInfraccionAuxDTO.getIdOblgcnNrmtvaHchoc());

            pgimInfraccion2Insert.setPgimInstanciaPaso(new PgimInstanciaPaso());
            pgimInfraccion2Insert.getPgimInstanciaPaso().setIdInstanciaPaso(idInstanciaPasoActual);

            if(idPas != null) {
                pgimInfraccion2Insert.setPgimPas(new PgimPas());
                pgimInfraccion2Insert.getPgimPas().setIdPas(idPas);
            }

            pgimInfraccion2Insert.setInfraccionOrigen(new PgimInfraccion());
            pgimInfraccion2Insert.getInfraccionOrigen().setIdInfraccion(pgimInfraccionAuxDTO.getIdInfraccion());

            pgimInfraccion2Insert.setFlIncluirEnPas(pgimInfraccionAuxDTO.getFlIncluirEnPas());
            pgimInfraccion2Insert.setDeSustento(pgimInfraccionAuxDTO.getDeSustento());
            pgimInfraccion2Insert.setFeComisionDeteccion(pgimInfraccionAuxDTO.getFeComisionDeteccion());
            pgimInfraccion2Insert.setMoMultaUit(pgimInfraccionAuxDTO.getMoMultaUit());
            pgimInfraccion2Insert.setFlVigente("1");

            pgimInfraccion2Insert.setEsRegistro(ConstantesUtil.IND_ACTIVO);
            pgimInfraccion2Insert.setFeCreacion(auditoriaDTO.getFecha());
            pgimInfraccion2Insert.setUsCreacion(auditoriaDTO.getUsername());
            pgimInfraccion2Insert.setIpCreacion(auditoriaDTO.getTerminal());

            PgimInfraccion pgimInfraccionNuevo = this.infraccionRepository.save(pgimInfraccion2Insert);

            // Colocamos la infracción base como no vigente.
            if(hacerNoVigenteInfraccionBase) {
	            pgimInfraccion2Update = this.infraccionRepository.findById(pgimInfraccionAuxDTO.getIdInfraccion())
	                    .orElse(null);
	
	            pgimInfraccion2Update.setFlVigente("0");
	            pgimInfraccion2Update.setFeActualizacion(auditoriaDTO.getFecha());
	            pgimInfraccion2Update.setUsActualizacion(auditoriaDTO.getUsername());
	            pgimInfraccion2Update.setIpActualizacion(auditoriaDTO.getTerminal());
	
	            this.infraccionRepository.save(pgimInfraccion2Update);
            }
            
            // Realizamos la copia de los contratistas responsables, de tenerlo.
            List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO = this.infraccionContraRepository.
            		listarContratistaByIdInfraccion(pgimInfraccionAuxDTO.getIdInfraccion());
            
            for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
            	
            	PgimInfraccionContraDTO pgimInfraccionContraDTONuevo = new PgimInfraccionContraDTO();
            	
            	pgimInfraccionContraDTONuevo.setIdInfraccion(pgimInfraccionNuevo.getIdInfraccion());
            	pgimInfraccionContraDTONuevo.setIdPersona(pgimInfraccionContraDTO.getIdPersona());                	
            	// asignamos registro base como origen para el nuevo registro
            	pgimInfraccionContraDTONuevo.setIdInfraccionContraOrigen(pgimInfraccionContraDTO.getIdInfraccionContra());

            	this.crearInfraccionContratista(pgimInfraccionContraDTONuevo, auditoriaDTO);
            	
            	// Colocamos los registros base como no vigente	                
            	if(hacerNoVigenteInfraccionBase) {
					PgimInfraccionContra pgimInfraccionContraBase =  this.infraccionContraRepository.findById(pgimInfraccionContraDTO.getIdInfraccionContra()).orElse(null);
					pgimInfraccionContraBase.setFlVigente("0");
					pgimInfraccionContraBase.setFeActualizacion(auditoriaDTO.getFecha());
		        	pgimInfraccionContraBase.setUsActualizacion(auditoriaDTO.getUsername());
		        	pgimInfraccionContraBase.setIpActualizacion(auditoriaDTO.getTerminal());
		        	
		        	this.infraccionContraRepository.save(pgimInfraccionContraBase);
            	}
            }
                
            PgimInfraccionDTO pgimInfraccionDTOCreado = this.infraccionRepository.obtenerInfraccionPorId(pgimInfraccionNuevo.getIdInfraccion());
            
            return pgimInfraccionDTOCreado;
        }
        
        

        @Override
        public PgimInfraccionContra getByIdInfraccionContra(Long idInfraccionContra) {
            return this.infraccionContraRepository.findById(idInfraccionContra).orElse(null);
        }
        
        @Override
        public List<PgimInfraccionContraDTO> listarInfraccionResponsables(Long idInfraccion){
        	
        	List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO =  new ArrayList<PgimInfraccionContraDTO>();
        	lstPgimInfraccionContraDTO = this.infraccionContraRepository.listarContratistaByIdInfraccion(idInfraccion);
        	
        	// Añadimos al agente fiscalizado como responsable de la infracción
        	PgimInfraccionAuxDTO pgimInfraccionAuxDTO =  this.infraccionAuxRepository.obtenerInfraccionAuxPorId(idInfraccion);
        	
        	PgimAgenteSupervisadoDTO pgimAgenteSupervisadoDTO = this.agenteSupervisadoRepository.obtenerAgenteSupervisadoPorRuc(pgimInfraccionAuxDTO.getRucAs());
        	Long idPersonaAs = (pgimAgenteSupervisadoDTO != null) ? pgimAgenteSupervisadoDTO.getIdPersona() : 0L;
        	
        	PgimInfraccionContraDTO pgimInfraccionContraDTOAs = new PgimInfraccionContraDTO();
        	pgimInfraccionContraDTOAs.setIdInfraccion(idInfraccion);
        	pgimInfraccionContraDTOAs.setIdPersona(idPersonaAs);
        	pgimInfraccionContraDTOAs.setDescCoDocumentoIdentidad(pgimInfraccionAuxDTO.getRucAs());
        	pgimInfraccionContraDTOAs.setDescNoRazonSocial(pgimInfraccionAuxDTO.getNoRazonSocialAs());
        	pgimInfraccionContraDTOAs.setDescFlAgenteFiscalizado(ConstantesUtil.FL_IND_SI);
        	
        	lstPgimInfraccionContraDTO.add(0, pgimInfraccionContraDTOAs);
        	
        	if(pgimInfraccionAuxDTO == null ) {
        		String msjExcepcionControlada = String.format("No se encontró la infracción con id %s en la vista auxiliar de infracciones de la BD, "
        				+ "por lo que no se pudo obtener la información del agente fiscalizado responsable", idInfraccion);
        		throw new PgimException(TipoResultado.ERROR, msjExcepcionControlada);
        	}        	
        	 
        	return lstPgimInfraccionContraDTO;
        }
        
        @Transactional(readOnly = false)
        @Override
        public PgimInfraccionDTO crearLstInfraccionContratistas(Long idInfraccion, List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO, 
        		Long idInstanciaPasoActual, AuditoriaDTO auditoriaDTO) throws Exception {
        	
        	if(lstPgimInfraccionContraDTO.size() == 0 ) {
        		throw new PgimException(TipoResultado.WARNING, "Debe seleccionar al menos un contratista para registrar");
        	}
        	
        	PgimInfraccionDTO pgimInfraccionDTO = null;
        	PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository.findById(idInstanciaPasoActual).orElse(null);
        	PgimInfraccion pgimInfraccionActual = this.infraccionRepository.findById(idInfraccion).orElse(null);
        	String usuarioActualizadorInfraccion = (pgimInfraccionActual.getUsActualizacion() != null) ? pgimInfraccionActual.getUsActualizacion() : pgimInfraccionActual.getUsCreacion();
        	
    		// Si es el mismo paso y el mismo usuario se añade el constratista(s) a la infracción actual; caso contrario, se copia previamente la infracción y se añade a esta última
        	// para persistir el histórico
    		
    		if (pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso().equals(pgimInfraccionActual.getPgimInstanciaPaso().getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso())
    			&& auditoriaDTO.getUsername().equals(usuarioActualizadorInfraccion)
    			) {
        	
	        	for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {	        		
	        		this.crearInfraccionContratista(pgimInfraccionContraDTO, auditoriaDTO);
	        	}	        	
	        	
	    		pgimInfraccionDTO = this.infraccionRepository.obtenerInfraccionPorId(idInfraccion);
	        	
    		}else {
    			
    			PgimInfraccionAuxDTO pgimInfraccionAuxDTO = this.infraccionAuxRepository.obtenerInfraccionAuxPorId(idInfraccion);
    			
    			PgimInfraccionDTO pgimInfraccionDTONueva = this.realizarCopiaInfraccion(pgimInfraccionAuxDTO, pgimInstanciaPasoActual.getIdInstanciaPaso(), 
    					pgimInfraccionAuxDTO.getIdPas(), true, auditoriaDTO);
    			
    			for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
    				pgimInfraccionContraDTO.setIdInfraccion(pgimInfraccionDTONueva.getIdInfraccion()); 
    				this.crearInfraccionContratista(pgimInfraccionContraDTO, auditoriaDTO);
	        	}
    			
    			pgimInfraccionDTO = pgimInfraccionDTONueva;    			
    		}
        	
    		// Retornamos solo el objeto infracción, por el momento no es necesario retornar los contratistas creados.     		
        	return pgimInfraccionDTO;
        }
        
        @Transactional(readOnly = false)
        public PgimInfraccionContra crearInfraccionContratista(PgimInfraccionContraDTO pgimInfraccionContraDTO, 
        		AuditoriaDTO auditoriaDTO) throws Exception {
        	
    		PgimInfraccionContra pgimInfraccionContra = new PgimInfraccionContra();

    		pgimInfraccionContra.setPgimInfraccion(new PgimInfraccion());
    		pgimInfraccionContra.getPgimInfraccion().setIdInfraccion(pgimInfraccionContraDTO.getIdInfraccion());

    		pgimInfraccionContra.setPgimPersona(new PgimPersona());
    		pgimInfraccionContra.getPgimPersona().setIdPersona(pgimInfraccionContraDTO.getIdPersona());
    		
    		if(pgimInfraccionContraDTO.getIdInfraccionContraOrigen() != null) {
        		pgimInfraccionContra.setInfraccionContraOrigen(new PgimInfraccionContra());
        		pgimInfraccionContra.getInfraccionContraOrigen().setIdInfraccionContra(pgimInfraccionContraDTO.getIdInfraccionContraOrigen());
    		}
    		
    		pgimInfraccionContra.setFlVigente(ConstantesUtil.FL_IND_SI);

    		pgimInfraccionContra.setEsRegistro(ConstantesUtil.IND_ACTIVO);
    		pgimInfraccionContra.setFeCreacion(auditoriaDTO.getFecha());
    		pgimInfraccionContra.setUsCreacion(auditoriaDTO.getUsername());
    		pgimInfraccionContra.setIpCreacion(auditoriaDTO.getTerminal());
    		
    		PgimInfraccionContra pgimInfraccionContraCreado = this.infraccionContraRepository.save(pgimInfraccionContra);
			
        	return pgimInfraccionContraCreado;
        }
        
        @Transactional(readOnly = false)
        @Override
        public PgimInfraccionDTO eliminarInfraccionContratista(PgimInfraccionContra pgimInfraccionContraActual, 
        		Long idInstanciaPasoActual, AuditoriaDTO auditoriaDTO) throws Exception {
        	
        	PgimInfraccionDTO pgimInfraccionDTO = null;
        	Long idInfraccion = pgimInfraccionContraActual.getPgimInfraccion().getIdInfraccion();
        	PgimInstanciaPaso pgimInstanciaPasoActual = this.instanciaPasoRepository.findById(idInstanciaPasoActual).orElse(null);
        	PgimInfraccion pgimInfraccionActual = this.infraccionRepository.findById(idInfraccion).orElse(null);
        	String usuarioActualizadorInfraccion = (pgimInfraccionActual.getUsActualizacion() != null) ? pgimInfraccionActual.getUsActualizacion() : pgimInfraccionActual.getUsCreacion();
        	
    		// Si es el mismo paso y el mismo usuario se elimina el constratista de la infracción actual; caso contrario, se copia previamente la infracción y por ende sus contratistas, luego se elimina de esta última
        	// para persistir el histórico
    		
    		if (pgimInstanciaPasoActual.getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso().equals(pgimInfraccionActual.getPgimInstanciaPaso().getPgimRelacionPaso().getPasoProcesoDestino().getIdPasoProceso())
    			&& auditoriaDTO.getUsername().equals(usuarioActualizadorInfraccion)
    			) {
            
	        	pgimInfraccionContraActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
	        	pgimInfraccionContraActual.setFeActualizacion(auditoriaDTO.getFecha());
	        	pgimInfraccionContraActual.setUsActualizacion(auditoriaDTO.getUsername());
	        	pgimInfraccionContraActual.setIpActualizacion(auditoriaDTO.getTerminal());

	        	this.infraccionContraRepository.save(pgimInfraccionContraActual);
	        	
	        	pgimInfraccionDTO = this.infraccionRepository.obtenerInfraccionPorId(idInfraccion);
	            
    		}else {
    			
    			PgimInfraccionAuxDTO pgimInfraccionAuxDTO = this.infraccionAuxRepository.obtenerInfraccionAuxPorId(idInfraccion);

    			PgimInfraccionDTO pgimInfraccionDTONueva = this.realizarCopiaInfraccion(pgimInfraccionAuxDTO, pgimInstanciaPasoActual.getIdInstanciaPaso(), 
						pgimInfraccionAuxDTO.getIdPas(), true, auditoriaDTO);
    			
    			PgimInfraccionContraDTO pgimInfraccionContraDTOElim = this.infraccionContraRepository.obtenerContratistaByIdContratistaOrigen(pgimInfraccionDTONueva.getIdInfraccion(), 
						pgimInfraccionContraActual.getIdInfraccionContra());
    			
    			PgimInfraccionContra pgimInfraccionContraEliminar = this.infraccionContraRepository.findById(pgimInfraccionContraDTOElim.getIdInfraccionContra()).orElse(null);
    			pgimInfraccionContraEliminar.setEsRegistro(ConstantesUtil.IND_INACTIVO);
	        	pgimInfraccionContraEliminar.setFeActualizacion(auditoriaDTO.getFecha());
	        	pgimInfraccionContraEliminar.setUsActualizacion(auditoriaDTO.getUsername());
	        	pgimInfraccionContraEliminar.setIpActualizacion(auditoriaDTO.getTerminal());

	        	this.infraccionContraRepository.save(pgimInfraccionContraEliminar);
	        	
	        	pgimInfraccionDTO = pgimInfraccionDTONueva;
    			
    		}
    		
    		// Retornamos solo el objeto infracción, para actualizarlo en el front-end de ser necesario
    		return pgimInfraccionDTO;
        }
        
        
		@Transactional(readOnly = false)
        @Override
        public void registrarInfracionNoVigente(Long idOblgcnNrmtvaHchoc, AuditoriaDTO auditoriaDTO) {
        	
        	List<PgimInfraccionDTO> lPgimInfraccionDTO = this.infraccionRepository
    				.listarInfraccionXobligacionNorma(idOblgcnNrmtvaHchoc);
        	
        	for (PgimInfraccionDTO pgimInfraccionDTO : lPgimInfraccionDTO) {
        		// Colocamos la infracción como no vigente.
            	PgimInfraccion pgimInfraccion = this.infraccionRepository.findById(pgimInfraccionDTO.getIdInfraccion())
                        .orElse(null);

                pgimInfraccion.setFlVigente("0");
                pgimInfraccion.setFeActualizacion(auditoriaDTO.getFecha());
                pgimInfraccion.setUsActualizacion(auditoriaDTO.getUsername());
                pgimInfraccion.setIpActualizacion(auditoriaDTO.getTerminal());

                this.infraccionRepository.save(pgimInfraccion);
                
                //Colocamos la contratistas de la infración como no vigente
                List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO = this.infraccionContraRepository.
                		listarContratistaByIdInfraccion(pgimInfraccionDTO.getIdInfraccion());
                
                for (PgimInfraccionContraDTO pgimInfraccionContraDTO : lstPgimInfraccionContraDTO) {
                	
    				PgimInfraccionContra pgimInfraccionContra =  this.infraccionContraRepository.findById(pgimInfraccionContraDTO.getIdInfraccionContra()).orElse(null);
    				pgimInfraccionContra.setFlVigente("0");
    				pgimInfraccionContra.setFeActualizacion(auditoriaDTO.getFecha());
    	        	pgimInfraccionContra.setUsActualizacion(auditoriaDTO.getUsername());
    	        	pgimInfraccionContra.setIpActualizacion(auditoriaDTO.getTerminal());
    	        	
    	        	this.infraccionContraRepository.save(pgimInfraccionContra);                	
                }	
        	}
            
        }

}
