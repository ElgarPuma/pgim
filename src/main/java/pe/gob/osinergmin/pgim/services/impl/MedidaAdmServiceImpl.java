package pe.gob.osinergmin.pgim.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaProcesDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMedidaAdmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonaosiAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimMedidaAdm;
import pe.gob.osinergmin.pgim.models.entity.PgimPas;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.MedidaAdmRepository;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.InstanciaProcesService;
import pe.gob.osinergmin.pgim.services.MedidaAdmService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad medidas administrativas
 * 
 * @author: juanvaleriomayta
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class MedidaAdmServiceImpl implements MedidaAdmService {

        @Autowired
        private MedidaAdmRepository medidaAdmRepository;

        @Autowired
        private InstanciaProcesRepository instanciaProcesRepository;

        @Autowired
        private InstanciaProcesService instanciaProcesService;

        @Autowired
        private FlujoTrabajoService flujoTrabajoService;

        @Override
        public Page<PgimMedidaAdmDTO> listarMedidaAdministrativa(PgimMedidaAdmDTO filtroMedidaAdmDTO,
                        Pageable paginador, AuditoriaDTO auditoriaDTO) throws Exception {

                // Obtenemos permiso "listar todas"
                boolean flagPermisoTodas = false;
                for (String permiso : auditoriaDTO.getAuthorities()) {

                        if (permiso.equals(ConstantesUtil.PERMISO_VER_MEDIDAS_ADM_TODAS)) {
                                flagPermisoTodas = true;
                        }
                }

                if (filtroMedidaAdmDTO.getDescFlagMisAsignaciones().equals("1")) {
                        filtroMedidaAdmDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());
                }

                if (filtroMedidaAdmDTO.getTextoBusqueda().equals("")) {
                        filtroMedidaAdmDTO.setTextoBusqueda(null);
                }
                
                //Si el usuario no tiene permiso para ver los registros de otros usaurios 
                //se le setea el mismo usaurio para los filtros 
                if ((filtroMedidaAdmDTO.getDescFlagMisAsignaciones() == null
                                || !filtroMedidaAdmDTO.getDescFlagMisAsignaciones().trim().equals("1"))
                                && !flagPermisoTodas) {
                        filtroMedidaAdmDTO.setDescUsuarioAsignado(auditoriaDTO.getUsername());
                        filtroMedidaAdmDTO.setDescNoPersonaAsignada("");
                        filtroMedidaAdmDTO.setDescFlagMisAsignaciones("1");
                }              
                
                Page<PgimMedidaAdmDTO> pPgimMedidaAdmDTO = this.medidaAdmRepository.listarMedidaAdministrativa(
                                filtroMedidaAdmDTO.getIdTipoObjeto(), filtroMedidaAdmDTO.getCoMedidaAdministrativa(),
                                filtroMedidaAdmDTO.getIdTipoMedidaAdministrativa(),
                                filtroMedidaAdmDTO.getDescNuExpedienteSiged(),
                                filtroMedidaAdmDTO.getDescFlagMisAsignaciones(),
                                filtroMedidaAdmDTO.getDescUsuarioAsignado(),
                                filtroMedidaAdmDTO.getDescNoPersonaAsignada(), filtroMedidaAdmDTO.getTextoBusqueda(),
                                paginador);

                return pPgimMedidaAdmDTO;
        }

        @Override
        public List<PgimMedidaAdmDTO> listarPorCoMedidaAdministrativa(String palabra) {
                List<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmRepository
                                .listarPorCoMedidaAdministrativa(palabra);

                return lPgimMedidaAdmDTO;
        }

        @Override
        public PgimMedidaAdmDTO obtenerMedidaAdministrativaPorId(Long idMedidaAdministrativa) {
                return this.medidaAdmRepository.obtenerMedidaAdministrativaPorId(idMedidaAdministrativa);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimMedidaAdmDTO crearMedidaAdministrativa(@Valid PgimMedidaAdmDTO pgimMedidaAdmDTO,
                        AuditoriaDTO auditoriaDTO) throws Exception {

                PgimMedidaAdm pgimMedidaAdm = new PgimMedidaAdm();

                String anio = new SimpleDateFormat("yyyy").format(new Date());
                String coMedidaAdministrativa = "";

                coMedidaAdministrativa = "MA-" + anio + "-";

                pgimMedidaAdm.setCoMedidaAdministrativa(coMedidaAdministrativa);
                pgimMedidaAdm.setDeMedidaAdministrativa(pgimMedidaAdmDTO.getDeMedidaAdministrativa());
                pgimMedidaAdm.setFeMedidaAdministrativa(pgimMedidaAdmDTO.getFeMedidaAdministrativa());

                pgimMedidaAdm.setTipoMedidaAdministrativa(new PgimValorParametro());
                pgimMedidaAdm.getTipoMedidaAdministrativa()
                                .setIdValorParametro(pgimMedidaAdmDTO.getIdTipoMedidaAdministrativa());

                if (pgimMedidaAdmDTO.getIdUnidadMinera() != null) {
                        pgimMedidaAdm.setPgimUnidadMinera(new PgimUnidadMinera());
                        pgimMedidaAdm.getPgimUnidadMinera().setIdUnidadMinera(pgimMedidaAdmDTO.getIdUnidadMinera());
                } else {
                        pgimMedidaAdm.setPgimUnidadMinera(null);
                }

                if (pgimMedidaAdmDTO.getIdSupervision() != null) {
                        pgimMedidaAdm.setPgimSupervision(new PgimSupervision());
                        pgimMedidaAdm.getPgimSupervision().setIdSupervision(pgimMedidaAdmDTO.getIdSupervision());
                } else {
                        pgimMedidaAdm.setPgimSupervision(null);
                }

                if (pgimMedidaAdmDTO.getIdPas() != null) {
                        pgimMedidaAdm.setPgimPas(new PgimPas());
                        pgimMedidaAdm.getPgimPas().setIdPas(pgimMedidaAdmDTO.getIdPas());
                } else {
                        pgimMedidaAdm.setPgimPas(null);
                }

                if (pgimMedidaAdmDTO.getIdTipoObjeto() != null) {
                        pgimMedidaAdm.setTipoObjeto(new PgimValorParametro());
                        pgimMedidaAdm.getTipoObjeto().setIdValorParametro(pgimMedidaAdmDTO.getIdTipoObjeto());
                } else {
                        pgimMedidaAdm.setTipoObjeto(null);
                }

                pgimMedidaAdm.setFeMedidaAdministrativa(pgimMedidaAdmDTO.getFeMedidaAdministrativa());

                pgimMedidaAdm.setEsRegistro(ConstantesUtil.IND_ACTIVO);
                pgimMedidaAdm.setFeCreacion(auditoriaDTO.getFecha());
                pgimMedidaAdm.setUsCreacion(auditoriaDTO.getUsername());
                pgimMedidaAdm.setIpCreacion(auditoriaDTO.getTerminal());

                PgimMedidaAdm pgimMedidaAdmCreado = null;
                pgimMedidaAdmCreado = medidaAdmRepository.save(pgimMedidaAdm);

                String correlativo = String.format("%05d", pgimMedidaAdm.getIdMedidaAdministrativa());

                pgimMedidaAdm.setCoMedidaAdministrativa(coMedidaAdministrativa + correlativo);

                // Se asegura la instancia del proceso
                List<PgimInstanciaProcesDTO> lPgimInstanciaProcesDTO = this.instanciaProcesService
                                .asegurarInstanciasProceso(ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM,
                                                pgimMedidaAdmCreado.getIdMedidaAdministrativa(), auditoriaDTO);

                PgimInstanciaProcesDTO pgimInstanciaProcesDTOActual = lPgimInstanciaProcesDTO.get(0);

                PgimInstanciaProces pgimInstanciaProcesActual = this.instanciaProcesRepository
                                .findById(pgimInstanciaProcesDTOActual.getIdInstanciaProceso()).orElse(null);

                // Se actualiza la instancia del proceso en el registro de la medida
                // administrativa
                this.instanciaProcesService.actualizarInstProcTablaInstancia(pgimInstanciaProcesActual, auditoriaDTO);

                PgimMedidaAdmDTO pgimMedidaAdmDTOCreada = medidaAdmRepository
                                .obtenerMedidaAdministrativaPorId(pgimMedidaAdmCreado.getIdMedidaAdministrativa());

                // Se crea la asignación
                pgimMedidaAdmDTO.setIdMedidaAdministrativa(pgimMedidaAdmDTOCreada.getIdMedidaAdministrativa());

                PgimPersonaosiAuxDTO pPersonaosiAuxDTO = this.flujoTrabajoService
                                .obtenerPersonalOsiNombreUsuarioWindows(auditoriaDTO.getUsername());

                PgimRelacionPasoDTO pgimRelacionPasoDTOInicial = this.flujoTrabajoService
                                .obtenerRelacionPasoInicial(ConstantesUtil.PARAM_PROCESO_MEDIDA_ADM);

                String mensaje = "Registrar medida administrativa";

                PgimInstanciaPasoDTO pgimInstanciaPasoDTO = new PgimInstanciaPasoDTO();
                pgimInstanciaPasoDTO.setIdRelacionPaso(pgimRelacionPasoDTOInicial.getIdRelacionPaso());
                pgimInstanciaPasoDTO.setDescIdPersonalOsiDestino(pPersonaosiAuxDTO.getIdPersonalOsi());
                pgimInstanciaPasoDTO.setDeMensaje(mensaje);

                pgimInstanciaPasoDTO = this.flujoTrabajoService.crearInstanciaPasoInicial(pgimInstanciaProcesActual, pgimInstanciaPasoDTO,
                                auditoriaDTO);

                pgimMedidaAdmDTOCreada.setDescIdInstanciaPaso(pgimInstanciaPasoDTO.getIdInstanciaPaso());
                
                return pgimMedidaAdmDTOCreada;
        }

        @Override
        public PgimMedidaAdm getByIdMedidaAdministrativa(Long idMedidaAdministrativa) {
                return this.medidaAdmRepository.findById(idMedidaAdministrativa).orElse(null);
        }

        @Transactional(readOnly = false)
        @Override
        public PgimMedidaAdmDTO modificarMedidaAdministrativa(PgimMedidaAdmDTO pgimMedidaAdmDTO,
                        PgimMedidaAdm pgimMedidaAdm, AuditoriaDTO auditoriaDTO) {

                if (pgimMedidaAdmDTO.getIdUnidadMinera() != null) {
                        pgimMedidaAdm.setPgimUnidadMinera(new PgimUnidadMinera());
                        pgimMedidaAdm.getPgimUnidadMinera().setIdUnidadMinera(pgimMedidaAdmDTO.getIdUnidadMinera());
                } else {
                        pgimMedidaAdm.setPgimUnidadMinera(null);
                }

                if (pgimMedidaAdmDTO.getIdTipoObjeto() != null) {
                        pgimMedidaAdm.setTipoObjeto(new PgimValorParametro());
                        pgimMedidaAdm.getTipoObjeto().setIdValorParametro(pgimMedidaAdmDTO.getIdTipoObjeto());
                } else {
                        pgimMedidaAdm.setTipoObjeto(null);
                }

                pgimMedidaAdm.setCoMedidaAdministrativa(pgimMedidaAdmDTO.getCoMedidaAdministrativa());
                pgimMedidaAdm.setDeMedidaAdministrativa(pgimMedidaAdmDTO.getDeMedidaAdministrativa());
                pgimMedidaAdm.setFeMedidaAdministrativa(pgimMedidaAdmDTO.getFeMedidaAdministrativa());

                pgimMedidaAdm.setTipoMedidaAdministrativa(new PgimValorParametro());
                pgimMedidaAdm.getTipoMedidaAdministrativa()
                                .setIdValorParametro(pgimMedidaAdmDTO.getIdTipoMedidaAdministrativa());

                pgimMedidaAdm.setFeActualizacion(auditoriaDTO.getFecha());
                pgimMedidaAdm.setUsActualizacion(auditoriaDTO.getUsername());
                pgimMedidaAdm.setIpActualizacion(auditoriaDTO.getTerminal());

                PgimMedidaAdm pgimMedidaAdmModificado = null;
                pgimMedidaAdmModificado = medidaAdmRepository.save(pgimMedidaAdm);

                PgimMedidaAdmDTO pgimMedidaAdmDTOResultado = this
                                .obtenerMedidaAdministrativaPorId(pgimMedidaAdmModificado.getIdMedidaAdministrativa());
                
                pgimMedidaAdmDTOResultado.setDescIdInstanciaPaso(pgimMedidaAdmDTO.getDescIdInstanciaPaso());

                return pgimMedidaAdmDTOResultado;
        }

        @Override
        public Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervPas(Long idTipoObjeto, Pageable paginador)
                        throws Exception {
                Page<PgimMedidaAdmDTO> pPgimMedidaAdmDTO = this.medidaAdmRepository
                                .listarMedidaAdministrativaSupervPas(idTipoObjeto, paginador);

                return pPgimMedidaAdmDTO;
        }

        @Override
        public Page<PgimMedidaAdmDTO> listarMedidaAdministrativaUm(Long idUnidadMinera, Pageable paginador) {
                Page<PgimMedidaAdmDTO> pPgimMedidaAdmDTO = this.medidaAdmRepository
                                .listarMedidaAdministrativaUm(idUnidadMinera, paginador);

                return pPgimMedidaAdmDTO;
        }

        @Override
        public List<PgimMedidaAdmDTO> listarPorNumeroExpediente(String nuExpediente) {
                List<PgimMedidaAdmDTO> lPgimMedidaAdmDTO = this.medidaAdmRepository
                                .listarPorNumeroExpediente(nuExpediente);

                return lPgimMedidaAdmDTO;
        }

        @Override
        public Page<PgimMedidaAdmDTO> listarMedidaAdministrativaSupervision(Long idSupervision, Pageable paginador) {
                Page<PgimMedidaAdmDTO> pPgimMedidaAdmDTO = this.medidaAdmRepository
                                .listarMedidaAdministrativaSupervision(idSupervision, paginador);

                return pPgimMedidaAdmDTO;
        }

        @Override
        public Page<PgimMedidaAdmDTO> listarMedidaAdministrativaPas(Long idPas, Pageable paginador) {
                Page<PgimMedidaAdmDTO> pPgimMedidaAdmDTO = this.medidaAdmRepository.listarMedidaAdministrativaPas(idPas,
                                paginador);

                return pPgimMedidaAdmDTO;
        }

        @Override
        public PgimMedidaAdmDTO obtenerMedidaAdministrativaPorIdPas(Long idPas) {
                return this.medidaAdmRepository.obtenerMedidaAdministrativaPorIdPas(idPas);
        }

        @Transactional(readOnly = false)
        @Override
        public void eliminarMedidaAdministrativa(PgimMedidaAdm pgimMedidaAdmActual, AuditoriaDTO auditoriaDTO) {
                pgimMedidaAdmActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
                pgimMedidaAdmActual.setFeActualizacion(auditoriaDTO.getFecha());
                pgimMedidaAdmActual.setUsActualizacion(auditoriaDTO.getUsername());
                pgimMedidaAdmActual.setIpActualizacion(auditoriaDTO.getTerminal());

                this.medidaAdmRepository.save(pgimMedidaAdmActual);

        }

}
