package pe.gob.osinergmin.pgim.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.UserAuthDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.security.UserPrincipal;
import pe.gob.osinergmin.pgim.security.model.SissegModulo;
import pe.gob.osinergmin.pgim.security.model.SissegRol;
import pe.gob.osinergmin.pgim.security.model.SissegUsuario;
import pe.gob.osinergmin.pgim.services.impl.FlujoTrabajoServiceImpl;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {	
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private RestApiSissegService restApiSissegService;	

	@Autowired
	private FlujoTrabajoServiceImpl flujoTrabajoServiceImpl;
		

	@Override	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		String password = request.getParameter("password");

		String sessionSiseg = request.getParameter("session_siseg");
		
		String refresh_token = request.getParameter("refresh_token");

		log.info("sessionSiseg =============" + sessionSiseg);
		//log.info("username =============" + username);
		
		if(refresh_token!=null) {
			try {
				return this.refrescarToken(refresh_token);
			}
			catch (Exception e) {
				log.error(e.getMessage());
				return null;
			}
		}
		

		if (StringUtils.isBlank(sessionSiseg)) {
			throw new UsernameNotFoundException("Falta enviar un parametro mas el session_siseg!");
		}

		if (StringUtils.isBlank(username)) {
			throw new UsernameNotFoundException("Falta enviar un parametro mas el username!");
		}

		if (StringUtils.isBlank(password)) {
			throw new UsernameNotFoundException("Falta enviar un parametro mas el password!");
		}
		
		//String token = restApiSissegService.validarCadenaSisseg(sessionSiseg); //Descomentar para el pase
		//SissegUsuario sissegUsuario = restApiSissegService.validarAccesoSisseg(sessionSiseg, token); //Descomentar para el pase
		SissegUsuario sissegUsuario = restApiSissegService.validarAccesoSisseg(username); //Descomentar para desarrollo
		
		if (sissegUsuario == null) {
			throw new UsernameNotFoundException("Usuario no encontrado!");
		}
		sissegUsuario.setPasswordSiged(password); //Descomentar para desarrollo
		sissegUsuario.setPassword(bcrypt.encode(password));

		// Se obtiene el código del usuario del Siged:
		String coUsuarioSiged = null;		
		
		try {
			String userName = sissegUsuario.getUsername();
			coUsuarioSiged = this.flujoTrabajoServiceImpl.obtenerCoUsuarioSigedPorNombreUsuario(userName);
		} catch (PgimException e) {
			// Si es una excepción controlada, entonces el coUsuarioSiged tendrá el valor null
			// No es necesario detener el proceso, debido a que es posible que algunos módulos de la PGIM
			// no requieran integración con el Siged. Para los casos que sí se requiera cada funcionalidad
			// específica tendrá que validar la existencia y necesidad del código de usuario Siged antes citado.
			log.error(e.getMensaje(), e);
		} catch (Exception e) {
			// Cualquier otro tipo de excepción tiene que detener el proceso de validación de la autenticación.
			String mensajeExcepcion = String.format(
					"No se ha podido determinar el identificador interno del Siged para el usuario '%s'",
					sissegUsuario.getUsername());
			log.error(mensajeExcepcion);
			log.error(e.getMessage(), e);
			throw new UsernameNotFoundException(mensajeExcepcion);
		}	
			
		sissegUsuario.setCoUsuarioSiged(coUsuarioSiged);	
		
		//List<SissegModulo> listaModulos = restApiSissegService.listarModulos(sissegUsuario.getIdUsuario(), token); //Descomentar para el pase
		List<SissegModulo> listaModulos = restApiSissegService.listarModulos(sissegUsuario.getIdUsuario()); //Descomentar para desarrollo
		
		if (listaModulos==null || listaModulos.isEmpty()) {
			throw new UsernameNotFoundException(
					"Error en el Login: usuario '" + username + "' no tiene módulos asignados!");
		}

		//List<SissegRol> authorities = restApiSissegService.listarPermisosPagina(sissegUsuario.getIdUsuario(), token); //Descomentar para el pase
		List<SissegRol> authorities = restApiSissegService.listarPermisosPagina(sissegUsuario.getIdUsuario()); //Descomentar para desarrollo
		
		if (authorities==null || authorities.isEmpty()) {
			throw new UsernameNotFoundException(
					"Error en el Login: usuario '" + username + "' no tiene roles asignados!");
		}

		sissegUsuario.setListaModulos(listaModulos);
		sissegUsuario.setRoles(authorities);
		
		log.info("todo ok =============");	
		
		return UserPrincipal.build(sissegUsuario);
	}
	
	
	private UserDetails refrescarToken(String refresh_token) throws Exception {
		
		SissegUsuario sissegUsuario = new SissegUsuario();
		
		UserAuthDTO userAuthDTO = CommonsUtil.deserializarToken(refresh_token);
		
		sissegUsuario.setIdUsuario(userAuthDTO.getId());
		sissegUsuario.setUsername(userAuthDTO.getUser_name());
		sissegUsuario.setDependencia(userAuthDTO.getDependencia());
		sissegUsuario.setCompania(userAuthDTO.getCompania());
		sissegUsuario.setNombres(userAuthDTO.getNombres());
		sissegUsuario.setPassword("_");
		sissegUsuario.setEmail(userAuthDTO.getEmail());		
		sissegUsuario.setCoUsuarioSiged(userAuthDTO.getCoUsuarioSiged());			
		sissegUsuario.setPasswordSiged(userAuthDTO.getPasswordSiged());		
		sissegUsuario.setAuthorities(userAuthDTO.getAuthorities());
		
		
		return UserPrincipal.build(sissegUsuario);
	}
	
}