package pe.gob.osinergmin.pgim.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.security.TokenUtil;
import pe.gob.osinergmin.pgim.security.model.SissegAsOauth2Token;
import pe.gob.osinergmin.pgim.security.model.SissegListaModulo;
import pe.gob.osinergmin.pgim.security.model.SissegListaPermiso;
import pe.gob.osinergmin.pgim.security.model.SissegModulo;
import pe.gob.osinergmin.pgim.security.model.SissegRol;
import pe.gob.osinergmin.pgim.security.model.SissegUsuario;
import pe.gob.osinergmin.pgim.security.model.SissegUsuarioResponse;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.security.utils.CadenaUtils;
import pe.gob.osinergmin.security.utils.Crypto;
import pe.gob.osinergmin.security.utils.DateUtils;

@Slf4j
@Service
public class RestApiSissegServiceImpl implements RestApiSissegService {

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private RestTemplate restTemplate;	
	
	
	@Override
	public String validarCadenaSisseg(String cadenaSession) {

		String serverUrlAS = propertiesConfig.getUrlSissegAs();

		Integer idAplicacion =propertiesConfig.getAplicactionId();
		
		String encryptionKey = propertiesConfig.getSecurityEncKey();
		
		String token = null;		
		
		log.info("Ingresando a validar cadena sisseg");

        try {
            String urlServicio = serverUrlAS + ConstantesUtil.PARAM_SISSEG_AS_OAUTH2_TOKEN; 
            String clientId = encryptionKey + "::" +  idAplicacion + "::" + cadenaSession;
            String clientSecret = cadenaSession;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = (MultiValueMap<String, String>)new LinkedMultiValueMap();
            body.add(ConstantesUtil.PARAM_REQUEST_PROPERTY_GRANT_TYPE, TokenUtil.SECURITY_SISSEG_AS_GRANT_TYPE);
            body.add(ConstantesUtil.PARAM_REQUEST_PROPERTY_CLIENT_ID, clientId);
            body.add(ConstantesUtil.PARAM_REQUEST_PROPERTY_CLIENT_SECRET, clientSecret);
            body.add(ConstantesUtil.PARAM_REQUEST_PROPERTY_SCOPE, TokenUtil.SECURITY_SISSEG_AS_SCOPE);

            HttpEntity<MultiValueMap<String, String>> request = (HttpEntity<MultiValueMap<String, String>>)new HttpEntity((Object)body, (MultiValueMap)headers);

            ResponseEntity<SissegAsOauth2Token> response = (ResponseEntity<SissegAsOauth2Token>)restTemplate.postForEntity(urlServicio, (Object)request, (Class)SissegAsOauth2Token.class, new Object[0]);

            if (!response.getStatusCode().equals((Object)HttpStatus.OK)) {
                log.error("No se ha podido validar la cadena del SISSEG: "+response.getStatusCode());    
          	  	return null;
            }

            token = ((SissegAsOauth2Token)response.getBody()).getAccess_token();
            log.info("token======="+token);
            
        }catch (Exception e){
        	log.error("Error al validar cadena de sessionSisseg: " + e.getMessage());
            log.error(e.getMessage(), e);
            return null;
        }
        
        return token;				
	}
	

	@Override
	public List<SissegRol> listarPermisosPagina(Long idUsuario) {

		log.info("Ingresando a validar permisos sisseg"); 			

		String serverUrl = propertiesConfig.getUrlSisseg();

		String idAplicacion = String.valueOf(propertiesConfig.getAplicactionId());	

		String listaPermisos = ConstantesUtil.PARAM_SISSEG_PERMISOS_PAGINA;		

		String urlListaPersmisosPagina = listaPermisos.replace("{idUsuario}", String.valueOf(idUsuario)).replace("{idAplicacion}",
				idAplicacion); 

		SissegListaPermiso permisos = restTemplate.getForObject(serverUrl+urlListaPersmisosPagina, SissegListaPermiso.class);	

		if(permisos.getCodigo()==1) {			
			return permisos.getPersmisos();		
		}else {
			log.info("no se encontraron permisos...");
			return null;
		}	

	}
	
	@Override
	public List<SissegRol> listarPermisosPagina(Long idUsuario, String token) {

		log.info("Ingresando a listar permisos sisseg mediante microservicio SISSEG-RESOURCES"); 		

		List<SissegRol> lstSissegRol = null;		
		
		String serverUrl = propertiesConfig.getUrlSissegResources();

		Integer idAplicacion =propertiesConfig.getAplicactionId();
		
        try {
            String urlServicio = serverUrl + ConstantesUtil.PARAM_SISSEG_RESOURCES_PERMISOS; 
            urlServicio = urlServicio.replace("{idUsuario}", String.valueOf(idUsuario)).replace("{idAplicacion}", String.valueOf(idAplicacion));

            HttpHeaders headers = new HttpHeaders();            
            headers.set(ConstantesUtil.PARAM_REQUEST_PROPERTY_AUTHORIZATION, "Bearer "+token);

            HttpEntity request = new HttpEntity(headers);

        	ResponseEntity<SissegListaPermiso> response = restTemplate.exchange(
        			urlServicio, HttpMethod.GET, request, SissegListaPermiso.class, 1);
        	
        	if (!response.getStatusCode().equals((Object)HttpStatus.OK)) {
                log.error("No se ha podido obtener los permisos del usuario: "+response.getStatusCode());    
          	  	return null;
            }
        	
        	SissegListaPermiso permisos = response.getBody();
        	
        	if(permisos.getCodigo()==1) {
        		List<SissegRol> lstPermisos = permisos.getPermisos();
        		
        		// Depuramos repetidos
        		List<SissegRol> lstPermisosDepurada = new ArrayList<SissegRol>();

                for (SissegRol permiso : lstPermisos) {
                    Boolean encontrado = false;
                    for (SissegRol permisoAux : lstPermisosDepurada) {
                        if (permisoAux.getPermisoPagina().equals(permiso.getPermisoPagina())) {
                            encontrado = true;
                            break;
                        }              
                    }
                    if(!encontrado) lstPermisosDepurada.add(permiso);
                }
                lstSissegRol = lstPermisosDepurada;
        		
    		}else {
    			lstSissegRol = new ArrayList<SissegRol>();
    		}
            
        }catch (Exception e){
        	log.error("Error al obtener los permisos del usuario: " + e.getMessage());
            log.error(e.getMessage(), e);
            return null;
        }	

		return lstSissegRol;		

	}


	@Override
	public List<SissegModulo> listarModulos(Long idUsuario) {

		log.info("Ingresando a listar modulos sisseg"); 		

		String serverUrl = propertiesConfig.getUrlSisseg();

		String idAplicacion = String.valueOf(propertiesConfig.getAplicactionId());	

		String listaModulos = ConstantesUtil.PARAM_SISSEG_MODULOS;		

		String urlListaModulos = listaModulos.replace("{idUsuario}", String.valueOf(idUsuario)).replace("{idAplicacion}",idAplicacion); 

		SissegListaModulo modulos = restTemplate.getForObject(serverUrl+urlListaModulos, SissegListaModulo.class);		

		if(modulos.getCodigo()==1) {
			return modulos.getModulos();
		}else {
			return new ArrayList<SissegModulo>();
		}		

	}
	
	@Override
	public List<SissegModulo> listarModulos(Long idUsuario, String token) {

		log.info("Ingresando a listar modulos sisseg mediante microservicio SISSEG-RESOURCES"); 		

		List<SissegModulo> lstSissegModulo = null;		
		
		String serverUrl = propertiesConfig.getUrlSissegResources();

		Integer idAplicacion =propertiesConfig.getAplicactionId();
		
        try {
            String urlServicio = serverUrl + ConstantesUtil.PARAM_SISSEG_RESOURCES_MODULOS; 
            urlServicio = urlServicio.replace("{idUsuario}", String.valueOf(idUsuario)).replace("{idAplicacion}", String.valueOf(idAplicacion));

            HttpHeaders headers = new HttpHeaders();            
            headers.set(ConstantesUtil.PARAM_REQUEST_PROPERTY_AUTHORIZATION, "Bearer "+token);

            HttpEntity request = new HttpEntity(headers);

        	ResponseEntity<SissegListaModulo> response = restTemplate.exchange(
        			urlServicio, HttpMethod.GET, request, SissegListaModulo.class, 1);
        	
        	if (!response.getStatusCode().equals((Object)HttpStatus.OK)) {
                log.error("No se ha podido obtener los módulos del usuario: "+response.getStatusCode());    
          	  	return null;
            }
        	
        	SissegListaModulo modulos = response.getBody();
        	
        	if(modulos.getCodigo()==1) {
        		lstSissegModulo = modulos.getModulos();
    		}else {
    			lstSissegModulo = new ArrayList<SissegModulo>();
    		}
            
        }catch (Exception e){
        	log.error("Error al obtener los módulos del usuario: " + e.getMessage());
            log.error(e.getMessage(), e);
            return null;
        }	

		return lstSissegModulo;		

	}


	/**
	 * Método para pase
	 */
	@Override
	public SissegUsuario validarAccesoSisseg(String cadenaSession, String token) {

		SissegUsuario usuario = null;		

		String cadenaDes = "";

		String aplicationKey = "";		

		String serverUrl = propertiesConfig.getUrlSisseg();

		Integer idAplicacion =propertiesConfig.getAplicactionId();	
		
		String encryptionKey = propertiesConfig.getSecurityEncKey();

		try {	

			aplicationKey =  this.obtenerKeyAplicacion(serverUrl, idAplicacion);	
			aplicationKey = encryptionKey + aplicationKey.trim();		

			cadenaDes = Crypto.decrypt(aplicationKey, cadenaSession);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.error("Error al desencriptar cadena de sessionSisseg: " + e.getMessage());
			cadenaDes = "";			
		}

		if (StringUtils.isBlank(cadenaDes)) {
			log.error("no se ha podido desencriptar la cadena del  SISSEG..");
			return null;
		}	
		
		// primero validamos la expiracion del token del siseg

		String fecha = CadenaUtils.getFechaFromCadena(cadenaDes);		

		if (!DateUtils.validarFecha(fecha)) {
			log.error("fecha cadena: ===> "+fecha);
			log.error("fecha servidor: ===> "+new Date());
			log.error("Validación de fecha erronea...");
			return null;
		}

		// Obtenemos el resto de datos de la cadena desencriptada

		String username = CadenaUtils.getUsernameFromCadena(cadenaDes);		

		// Obtener id usuario SISSEG
		Long idUsuario = this.obtenerIdUsuario(serverUrl, username);

		if (idUsuario.equals(0L)) { // si el usuario no existe o esta inactivo
			log.error("El usuario no existe o esta inactivo");
			return null;
		}		

		// Si todo esta ok obtengo los datos del usuario		

		usuario = this.obtenerDatosUsuario(idUsuario, token); 

		if(usuario==null || usuario.getIdUsuario()==0L) {
			log.error("Usuario no encontrado....");
			return null;
		}		

//		usuario.setUsername(username); //seteamos el username
//		usuario.setPasswordSiged(CadenaUtils.getPasswordFromCadena(cadenaDes));// seteamos el password

		return usuario;			
	}
	
	
	private String obtenerKeyAplicacion(String serverUrl, Integer idAplicacion) {

		log.info("Ingresando a obtener key aplicacion"); 	

		String KeyId = "";		

		String keyAplicacion = ConstantesUtil.PARAM_SISSEG_KEY_APLICACION;		

		String urlkeyAplicacion = keyAplicacion.replace("{idAplicacion}",String.valueOf(idAplicacion)); 	

		KeyId =  restTemplate.getForObject(serverUrl+urlkeyAplicacion, String.class);		

		return KeyId;		

	}
	
	
	private Long obtenerIdUsuario(String serverUrl, String username) {

		log.info("Ingresando a obtener id usuario"); 	

		Long idUsuario = 0L;		

		String idUsu = ConstantesUtil.PARAM_SISSEG_ID_USUARIO;		

		String urlIdUsuario = idUsu.replace("{username}",username); 			
		
		idUsuario = restTemplate.getForObject(serverUrl+urlIdUsuario, Long.class);

		return idUsuario;		

	}	
	
	private Integer verificarAcceso(String serverUrl, Long idUsuario, Integer idAplicacion, Integer idPagina) {

		log.info("Ingresando a verificar acceso"); 	

		Integer acceso = 0;		

		String idAcceso = ConstantesUtil.PARAM_SISSEG_VERIFICAR_ACCESO;		

		String urlAcceso = idAcceso.replace("{idUsuario}",String.valueOf(idUsuario)).replace("{idAplicacion}",String.valueOf(idAplicacion))
				.replace("{idPagina}",String.valueOf(idPagina));		

		acceso = restTemplate.getForObject(serverUrl+urlAcceso, Integer.class);	

		return acceso;		

	}
	
	/**
	 * Método para ambiente local
	 * 
	 * @param serverUrl
	 * @param idUsuario
	 * @return
	 */
	private SissegUsuario obtenerDatosUsuario(String serverUrl, Long idUsuario) {
		
		log.info("Ingresando a obtener datos de usuario "); 	
		
		SissegUsuario usuario = null;
		
		String usua = ConstantesUtil.PARAM_SISSEG_DATOS_USUARIO;		

		String urlUsuario = usua.replace("{idUsuario}",String.valueOf(idUsuario));	
		
		usuario = restTemplate.getForObject(serverUrl+urlUsuario, SissegUsuario.class);
		
		return usuario;
		
	}
	
	/**
	 * Método para pase 
	 * 
	 * @param idUsuario
	 * @param token
	 * @return
	 */
	private SissegUsuario obtenerDatosUsuario(Long idUsuario, String token) {

		log.info("Ingresando a obtener datos de usuario"); 	

		SissegUsuario usuario = null;
		
		String serverUrl = propertiesConfig.getUrlSissegResources();

        try {
            String urlServicio = serverUrl + ConstantesUtil.PARAM_SISSEG_RESOURCES_USUARIO; 
            urlServicio = urlServicio.replace("{idUsuario}",String.valueOf(idUsuario));

            HttpHeaders headers = new HttpHeaders();            
            headers.set(ConstantesUtil.PARAM_REQUEST_PROPERTY_AUTHORIZATION, "Bearer "+token);

            HttpEntity request = new HttpEntity(headers);

        	ResponseEntity<SissegUsuarioResponse> response = restTemplate.exchange(
        			urlServicio, HttpMethod.GET, request, SissegUsuarioResponse.class, 1);
        	
        	if (!response.getStatusCode().equals((Object)HttpStatus.OK)) {
                log.error("No se ha podido obtener datos del usuario: "+response.getStatusCode());    
          	  	return null;
            }            

	        List<SissegUsuario> usuarioResult = response.getBody().getUsuario();
	          
            if(usuarioResult != null && usuarioResult.size() > 0) {
            	  usuario = usuarioResult.get(0);
            	  usuario.setIdUsuario(usuario.getIdUSuario()); //porque el servicio retorna idUSuario ('S' mayus)
	        	  usuario.setUsername(usuario.getUsername().toUpperCase()); //en la PGIM lo manejamos con mayus
	        	  usuario.setPasswordSiged(usuario.getPassword());
            	  
            }	
            
        }catch (Exception e){
        	log.error("Error al obtener datos del usuario: " + e.getMessage());
            log.error(e.getMessage(), e);
        }	

		return usuario;	
	}
	
	
	/**
	 * Metodo para ambiente local
	 */
	
	@Override
	public SissegUsuario validarAccesoSisseg(String username) {

		SissegUsuario usuario = null;		

		String serverUrl = propertiesConfig.getUrlSisseg();	

		//OBTENER ID USUARIO SISSEG
		Long idUsuario = this.obtenerIdUsuario(serverUrl, username);

		if (idUsuario.equals(0L)) { // si el usuario no existe o esta inactivo
			log.info("El usuario no existe o esta inactivo");
			return null;
		}			

		// Si todo esta ok obtengo los datos del usuario		

		usuario = this.obtenerDatosUsuario(serverUrl, idUsuario);

		if(usuario==null || usuario.getIdUsuario()==0L) {
			log.info("Usuario no encontrado....");
			return null;
		}		

		usuario.setUsername(username); //seteamos el username
		
		return usuario;			
	}	
	


}