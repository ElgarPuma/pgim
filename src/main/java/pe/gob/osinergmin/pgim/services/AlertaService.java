package pe.gob.osinergmin.pgim.services;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAlertaDetalleDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIprocesoAlertaDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAlerta;
import pe.gob.osinergmin.pgim.models.entity.PgimAlertaDetalle;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;

public interface AlertaService {

        Page<PgimAlertaDetalleDTO> listarAlerta(PgimAlertaDetalleDTO filtroAlertaDetalleDTO, AuditoriaDTO auditoriaDTO, Pageable paginador);

        PgimAlertaDetalleDTO obtenerAlertaDetalleByIdAlerta(Long idAlerta);

        PgimAlertaDetalle getByIdDetalleAlerta(Long idAlertaDetalle);

        PgimAlertaDetalleDTO obtenerAlertaDetalleById(Long idAlertaDetalle);

        PgimAlertaDetalleDTO obtenerAlertaDetalleByInstanciaPaso(Long idInstanciaPaso, Long idTipoAlerta);

        PgimAlertaDetalleDTO modificarAlertaLeido(PgimAlertaDetalleDTO pgimAlertaDetalleDTO,
                        PgimAlertaDetalle pgimAlertaDetalle, AuditoriaDTO auditoriaDTO);

        /**
         * Permite enviar las alertas específicas para el proceso de supervisión.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param noUsuarioOrigen
         * @param auditoriaDTO
         * @return
         */
        public void enviarAlertaTransicionPaso(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso,
                        Map<String, String> lNoUsuarios, Long idTipoAlerta, Long idProceso, Long idInstanciaPaso, AuditoriaDTO auditoriaDTO);

        /***
         * Permite enviar las alertas a los interesados de la supervisión.
         * @param pgimInstanciaProces
         * @param pgimRelacionPaso
         * @param lNoInteresados
         * @param idTipoAlerta
         * @param auditoriaDTO
         * @param idProceso
         * @param lNoUsuarios
         * @param pgimInstanciaPaso
         */
        public void enviarAlertaParaInteresados(PgimInstanciaProces pgimInstanciaProces,
                        PgimRelacionPaso pgimRelacionPaso, List<String> lNoInteresados, Long idTipoAlerta,
                        AuditoriaDTO auditoriaDTO, Long idProceso, Map<String, String> lNoUsuarios, PgimInstanciaPaso pgimInstanciaPaso);

        /**
         * Permite enviar las alertas a los responsables de la supervisión.
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param lNoUsuario
         * @param auditoriaDTO
         */
        void enviarAlertaParaResponsables(PgimInstanciaProces pgimInstanciaProces, PgimRelacionPaso pgimRelacionPaso, Long idInstanciaPaso,
                        List<String> lNoResposables, Long idTipoAlerta, AuditoriaDTO auditoriaDTO, Long idProceso,
                        Map<String, String> lNoUsuarios);

        /**
         * Permite obtener la ruta de acceso del objeto de negocio en PGIM
         * 
         * @param pgimAlertaDetalleDTO
         * @return
         */
        ResponseEntity<ResponseDTO> obtenerRuta(PgimAlertaDetalleDTO pgimAlertaDetalleDTO);

        /**
         * Permite mostrar la cantidad de alertas que no fuero revisadas
         * @param noUsuarioDestino
         * @return
         */
        PgimAlertaDetalleDTO contarAlertasPendientes(AuditoriaDTO auditoriaDTO);

        /**
         * Permite el registro y envio de alertas provenientes de IprocesoAlerta
         * @param pgimInstanciaProces
         * @param lNoUsuarios
         * @param idTipoAlerta
         * @param idInstanciaPaso
         * @param idIprocesoAlerta
         * @param auditoriaDTO
         * @return
         */
        public PgimAlerta enviarAlerta(PgimIprocesoAlertaDTO pgimIprocesoAlertaDTO,
                List<String> lNoUsuarios, Long idInstanciaPaso, String codTipoAlerta, AuditoriaDTO auditoriaDTO) throws Exception;
        
        
        /**
         * Permite el registro de una alerta y su detalle de alerta con la misma 
         * descripción para todos los usuarios destino 
         * 
         * @param idInstanciaProceso
         * @param pgimAlertaDTO
         * @param lNoUsuariosDestino
         * @param auditoriaDTO
         * @return
         */
        public PgimAlerta registrarAlertaYDetalle(Long idInstanciaProceso, PgimAlertaDTO pgimAlertaDTO,
                List<String> lNoUsuariosDestino, AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite crear alertas específicas para determinada instancia de proceso
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @param auditoriaDTO
         * @throws Exception
         */
        public void crearAlertasEspecificas(PgimInstanciaProces pgimInstanciaProces,
                PgimInstanciaPaso pgimInstanciaPaso, AuditoriaDTO auditoriaDTO) throws Exception;
        
        /**
         * Permite obtener los destinatarios pertinentes para el envío de alertas/mensajes de notificación
         * de acuerdo a la instancia de proceso e instancia de paso 
         * 
         * @param pgimInstanciaProces
         * @param pgimInstanciaPaso
         * @throws Exception
         */
        public List<String> listarDestinatariosNotificacionAlerta(PgimInstanciaPaso pgimInstanciaPaso) throws Exception;
}
