package pe.gob.osinergmin.pgim.controllers;

import java.util.Collection;
import java.util.Date;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalContratoDTO;
import pe.gob.osinergmin.pgim.dtos.UserAuthDTO;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestion base
 *
 * @descripción: Base
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 */
@RestController
@Slf4j
public class BaseController {

	@Autowired
	HttpServletRequest request;

	 @Autowired
	 private FlujoTrabajoService flujoTrabajoService;

	 
	/**
	 * Permite obtener el objeto de auditoría.
	 * 
	 * @return
	 * @throws Exception
	 */
	public AuditoriaDTO obtenerAuditoria() throws Exception {

		AuditoriaDTO auditoriaDTO = new AuditoriaDTO();

		String direccionRemota = request.getHeader("x-forwarded-for");

		String headerAuthorization = request.getHeader("authorization").replace("Bearer", "");

		UserAuthDTO userAuthDTO = this.deserializarToken(headerAuthorization);

		if (direccionRemota == null) {
			direccionRemota = request.getRemoteAddr();
		} else {
			direccionRemota = new StringTokenizer(direccionRemota, ",").nextToken().trim();
		}

		if (userAuthDTO != null) {
			auditoriaDTO.setIdUsuario(userAuthDTO.getId());
			auditoriaDTO.setUsername(userAuthDTO.getUser_name());
			auditoriaDTO.setCoUsuarioSiged(userAuthDTO.getCoUsuarioSiged());			
			auditoriaDTO.setPasswordSiged(userAuthDTO.getPasswordSiged());
			auditoriaDTO.setAuthorities(userAuthDTO.getAuthorities());
		}

		auditoriaDTO.setTerminal(direccionRemota);
		auditoriaDTO.setFecha(new Date());
		
		auditoriaDTO.setModuloPgimActual(ConstantesUtil.PARAM_MODULO_GENERICO);
		
		//Set ID Rol SIGED para el usuario 
		PgimPersonalContratoDTO pgimPersonalContratoDTO = 
				flujoTrabajoService.obtenerPersonaContratoPorUsuario(auditoriaDTO.getUsername());
		
		if (pgimPersonalContratoDTO != null) {
			auditoriaDTO.setIdRolSiged(ConstantesUtil.PARAM_ID_ROL_SUPERVISOR_CONCESIONARIA);
		}
		else
		{
			auditoriaDTO.setIdRolSiged(ConstantesUtil.PARAM_ID_ROL_USUARIO_FINAL);
		}
	
		return auditoriaDTO;
	}

	/**
	 * Permite deserializar el token priviamente serializado.
	 * 
	 * @param idToken Token que viaje entre el frontend y backend.
	 * @return
	 * @throws Exception
	 */
	public UserAuthDTO deserializarToken(String idToken) throws Exception {

		String jwtToken = idToken;
		UserAuthDTO userAuthDTO = new UserAuthDTO();
		String body = "";

		try {
			System.out.println("------------ Decode JWT ------------");
			String[] split_string = jwtToken.split("\\.");
			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// String base64EncodedHeader = split_string[0];
			// HDT Fin.
			String base64EncodedBody = split_string[1];

			System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
			Base64 base64Url = new Base64(true);			

			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// String header = new String(base64Url.decode(base64EncodedHeader));
			// System.out.println("JWT Header : " + header);
			// HDT Fin.

			body = new String(base64Url.decode(base64EncodedBody));

			// HDT Inicio.25.04.2022: Comentado para proteger la información de la clave del Siged
			// System.out.println("JWT Header : " + body);
			// HDT Fin.

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			userAuthDTO = mapper.readValue(body, UserAuthDTO.class);

		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
			throw new Exception(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		return userAuthDTO;
	}

	protected boolean verificarRole(String role) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		return authorities.contains(new SimpleGrantedAuthority(role));
	}

}
