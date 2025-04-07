package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import gob.osinergmin.sne.domain.dto.rest.in.AccessRequestInRO;
import gob.osinergmin.sne.domain.dto.rest.in.AfiliacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.ArchivoNotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.DocumentoNotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.NotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.filter.FilterGenericInRO;
import gob.osinergmin.sne.domain.dto.rest.in.list.ListArchivoNotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.in.list.ListDocumentoNotificacionInRO;
import gob.osinergmin.sne.domain.dto.rest.out.AccessResponseOutRO;
import gob.osinergmin.sne.domain.dto.rest.out.AfiliacionOutROsne;
import gob.osinergmin.sne.domain.dto.rest.out.BaseNotificacionOutRO;
import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDocumentoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.services.NotificacionService;
import pe.gob.osinergmin.pgim.siged.Archivos;
import pe.gob.osinergmin.pgim.siged.wssoap.SigedSoapService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.siged.Archivo;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Notificación
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SigedSoapService sigedSoapService;

    @Override
    public AccessResponseOutRO iniciarSesion(AccessRequestInRO accessRequestInRO) {
        String uri = this.propertiesConfig.getUrlSneApiRest() + "login";
        AccessResponseOutRO accessResponseOutRO = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            // headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AccessRequestInRO> requestEntity = new HttpEntity<>(accessRequestInRO, headers);

            ResponseEntity<AccessResponseOutRO> reAccessResponseOutRO = restTemplate.exchange(uri, HttpMethod.POST,
                    requestEntity, AccessResponseOutRO.class);

            accessResponseOutRO = reAccessResponseOutRO.getBody();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException("error", "Ocurrió un error al intentar iniciar la sesión en la SNE");
        }

        return accessResponseOutRO;
    }

    @Override
    public AfiliacionOutROsne obtenerAfiliacion(AfiliacionInRO afiliacionInRO, String token) {
        String uri = this.propertiesConfig.getUrlSneApiRest() + "obtenerAfiliacion";
        AfiliacionOutROsne afiliacionOutROsne = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<AfiliacionInRO> requestEntity = new HttpEntity<>(afiliacionInRO, headers);

            ResponseEntity<AfiliacionOutROsne> reAfiliacionOutROsne = restTemplate.exchange(uri, HttpMethod.POST,
                    requestEntity, AfiliacionOutROsne.class);

            afiliacionOutROsne = reAfiliacionOutROsne.getBody();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException("error", "Ocurrió un error al intentar obtener la afiliación del RUC "
                    + afiliacionInRO.getNumeroDocumento());
        }

        return afiliacionOutROsne;
    }

    @Override
    public BaseNotificacionOutRO registrarNotificacion(NotificacionInRO notificacionInRO, String token) {
        String uri = this.propertiesConfig.getUrlSneApiRest() + "notificacion/registrar";
        BaseNotificacionOutRO baseNotificacionOutRO = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            String json = mapper.writeValueAsString(notificacionInRO);

            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

            RestTemplate restTemplateL = new RestTemplate();
            ResponseEntity<BaseNotificacionOutRO> reBaseNotificacionOutRO = restTemplateL.exchange(uri, HttpMethod.POST,
                    requestEntity, BaseNotificacionOutRO.class);

            baseNotificacionOutRO = reBaseNotificacionOutRO.getBody();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException(TipoResultado.ERROR, "Ocurrió un error al intentar registrar la notificación con el asunto " //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
                    + notificacionInRO.getAsuntoNotificacion() + ": " + e.getMessage());
        }

        return baseNotificacionOutRO;
    }

    @Override
    public byte[] obtenerConstanciaNotificacion(FilterGenericInRO filterGenericInRO, String token) {
        String uri = this.propertiesConfig.getUrlSneApiRest() + "notificacion/constancia/obtenerByNotificador";
        byte[] byteConstanciaNotificacion = null;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<FilterGenericInRO> requestEntity = new HttpEntity<>(filterGenericInRO, headers);

            ResponseEntity<byte[]> reDescarga = restTemplate.exchange(uri, HttpMethod.POST, requestEntity,
                    byte[].class);

            byteConstanciaNotificacion = reDescarga.getBody();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new PgimException(TipoResultado.ERROR,
                    "Ocurrió un error al intentar obtener la constancia de notificación para el archivo "
                            + filterGenericInRO.getId()); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        return byteConstanciaNotificacion;
    }

    @Override
    public Map<String, Object> notificarElectronicamente(PgimInstanciaProces pgimInstanciaProces,
            List<PgimDocumentoDTO> lPgimDocumentoAdjuntos,
            PgimPersona pgimPersonaDestino, AuditoriaDTO auditoriaDTO) {

        // Documentos a notificar
        ListDocumentoNotificacionInRO lDocumentosNotificacion = new ListDocumentoNotificacionInRO();
        List<DocumentoNotificacionInRO> lDocumentoNotificacionInRO = new ArrayList<DocumentoNotificacionInRO>();

        Boolean documentoPrincipal = false;
        Boolean archivoPrincipal = false;

        for (PgimDocumentoDTO pgimDocumentoAdjuntoElemento : lPgimDocumentoAdjuntos) {
        	List<Archivo> lstArchivos = new ArrayList<Archivo>();
        	if(pgimDocumentoAdjuntoElemento.getDescLstArchivos() != null && pgimDocumentoAdjuntoElemento.getDescLstArchivos().size() > 0 ) {
        		lstArchivos = pgimDocumentoAdjuntoElemento.getDescLstArchivos();
        	}else {
            String idDocumentoSigedCadena = pgimDocumentoAdjuntoElemento.getCoDocumentoSiged().toString();
            Archivos archivos = this.sigedSoapService.obtenerArhivosSiged(idDocumentoSigedCadena, auditoriaDTO);
            lstArchivos = archivos.getListaArchivo();
        	}

            // Archivos del documento
            ListArchivoNotificacionInRO archivosNotificacion = new ListArchivoNotificacionInRO();
            List<ArchivoNotificacionInRO> lArchivoNotificacionInRO = new ArrayList<>();
            ArchivoNotificacionInRO archivoNotificacionInRO = null;

            for (Archivo archivo : lstArchivos) {
                archivoNotificacionInRO = new ArchivoNotificacionInRO();
                archivoNotificacionInRO.setIdArchivoSigedArchivoNotificacion(Integer.parseInt(archivo.getIdArchivo()));
                archivoNotificacionInRO.setNombreArchivoNotificacion(archivo.getNombre());
                if (archivoPrincipal == false) {
                    archivoNotificacionInRO.setEsPrincipalArchivoNotificacion("S");
                    archivoPrincipal = true;
                } else {
                    archivoNotificacionInRO.setEsPrincipalArchivoNotificacion("N");
                }                

                lArchivoNotificacionInRO.add(archivoNotificacionInRO);
            }

            archivosNotificacion.setArchivoNotificacion(lArchivoNotificacionInRO);

            DocumentoNotificacionInRO documentoNotificacionInRO = new DocumentoNotificacionInRO();
            Integer idDocumentoSiged = Integer.parseInt(pgimDocumentoAdjuntoElemento.getCoDocumentoSiged().toString());

            documentoNotificacionInRO.setIdDocumentoSigedDocumentoNotificacion(idDocumentoSiged);
            if (documentoPrincipal == false) {
                documentoNotificacionInRO.setEsPrincipalDocumentoNotificacion("S");
                documentoPrincipal = true;
            } else {
                documentoNotificacionInRO.setEsPrincipalDocumentoNotificacion("N");
            }
            
            documentoNotificacionInRO.setNombreDocumentoNotificacion(pgimDocumentoAdjuntoElemento.getNumeroDocumento());
            documentoNotificacionInRO.setArchivosNotificacion(archivosNotificacion);

            lDocumentoNotificacionInRO.add(documentoNotificacionInRO);
        }

        lDocumentosNotificacion.setDocumentoNotificacion(lDocumentoNotificacionInRO);

        int tipoDocIdentidad = 1; // RUC
        String nroDocIdentidad = pgimPersonaDestino.getCoDocumentoIdentidad();

        NotificacionInRO notificacionInRO = new NotificacionInRO();
        notificacionInRO.setIdClienteSiged(Integer.parseInt(pgimPersonaDestino.getDescIdClienteSiged()));
        notificacionInRO.setExpedienteSigedNotificacion(pgimInstanciaProces.getNuExpedienteSiged());
        notificacionInRO.setAsuntoNotificacion(lPgimDocumentoAdjuntos.get(0).getNumeroDocumento());
        notificacionInRO.setDocumentosNotificacion(lDocumentosNotificacion);
        notificacionInRO.setIdUnidadOperativa(null);

        // Procesando la notificación
        Map<String, Object> notificacionConstancia = this.notificar(tipoDocIdentidad, nroDocIdentidad, notificacionInRO,
                auditoriaDTO);

        if (notificacionConstancia == null) {
            return null;
        }

        return notificacionConstancia;
    }

    private Map<String, Object> notificar(int tipoDocIdentidad, String nroDocIdentidad,
            NotificacionInRO notificacionInRO, AuditoriaDTO auditoriaDTO) {
        String appInvoker = this.propertiesConfig.getAppSneApiInvoker();

        // Se inicia la sesión para obtener el token
        AccessRequestInRO accessRequestInRO = new AccessRequestInRO();
        accessRequestInRO.setAppInvoker(appInvoker);
        accessRequestInRO.setLoginType("NO");
        accessRequestInRO.setSector(this.propertiesConfig.getSectorSneApi());
        accessRequestInRO.setUsername(auditoriaDTO.getUsername());
        accessRequestInRO.setPassword(auditoriaDTO.getPasswordSiged());

        AccessResponseOutRO accessResponseOutRO = this.iniciarSesion(accessRequestInRO);
        if (accessResponseOutRO.getResultCode().equals(gob.osinergmin.gnr.util.Constantes.RESULTADO_FAIL)) {
            throw new PgimException(TipoResultado.ERROR, "No se ha podido iniciar sesión para la integración con el SNE"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        String token = accessResponseOutRO.getToken();

        // Se valida que el destinatario esté afiliado al SNE
        AfiliacionInRO afiliacionInRO = new AfiliacionInRO();
        afiliacionInRO.setAppInvoker(appInvoker);
        afiliacionInRO.setTipoDocumento(tipoDocIdentidad);
        afiliacionInRO.setNumeroDocumento(nroDocIdentidad);
        afiliacionInRO.setVerificarData(true);

        AfiliacionOutROsne afiliacionOutROsne = this.obtenerAfiliacion(afiliacionInRO, token);

        if (afiliacionOutROsne.getResultCode() == null) {
            throw new PgimException(TipoResultado.ERROR, "No se ha podido determinar si el destinatario está registrado en el SNE"); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        if (!afiliacionOutROsne.getResultCode().equals(ConstantesUtil.PARAM_SNE_ACTIVO)) {

            String mensajeErrorSiged = "";

            mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSNE, "notificar",
                    afiliacionOutROsne.getResultCode(), afiliacionOutROsne.getMessage());

            log.error(mensajeErrorSiged);
            throw new PgimException(TipoResultado.ERROR,
                    "No se ha podido notificar al destinatario a través del SNE. Más detalles a continuación: "
                            + mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        // Se registra la notificación electrónica
        notificacionInRO.setAppInvoker(appInvoker);
        notificacionInRO.setIdProcedimiento(this.propertiesConfig.getIdSneApiProcedimiento());

        BaseNotificacionOutRO baseNotificacionOutRO = this.registrarNotificacion(notificacionInRO, token);

        if (baseNotificacionOutRO.getResultCode().equals(gob.osinergmin.gnr.util.Constantes.RESULTADO_FAIL)) {
            String mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSNE, "notificar",
                    baseNotificacionOutRO.getResultCode(), baseNotificacionOutRO.getMessage());
            log.error(mensajeErrorSiged);
            throw new PgimException(TipoResultado.ERROR,
                    "Ocurrió una excepción no prevista al intentar notificar. Estos son los datos: "
                            + mensajeErrorSiged); //STORY: PGIM-6667: Mejora de la gestión de mensajes para el usuario
        }

        // Si todo va bien se descarga la constancia de la notificación electrónica
        FilterGenericInRO filterGenericInRO = new FilterGenericInRO();
        filterGenericInRO.setAppInvoker(appInvoker);
        filterGenericInRO.setId(baseNotificacionOutRO.getIdNotificacion());

        byte[] byteConstanciaNotificacion = this.obtenerConstanciaNotificacion(filterGenericInRO, token);

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("baseNotificacionOutRO", baseNotificacionOutRO);
        respuesta.put("byteConstanciaNotificacion", byteConstanciaNotificacion);

        return respuesta;
    }

}
