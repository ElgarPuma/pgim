package pe.gob.osinergmin.pgim.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz para la gestión de los servicios relacionados con el perfil del usuario
 * 
 * @descripción: Perfil de usuario
 *
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 19/05/2023
 *
 */
public interface PerfilUserService {

	/**
	 * Permite guardar la foto de usuario
	 * 
	 * @param fileDocumento
	 * @param noUsuario
	 * @throws Exception
	 */
	void guardarFotoUsuario(MultipartFile fileDocumento, String noUsuario) throws Exception;
	
	/**
	 * Permite obtener la foto de usuario a partir de su idPersona, en formato base64
	 * 
	 * @param idPersona
	 * @return
	 * @throws Exception
	 */
	String obtenerFotoPersona(Long idPersona) throws Exception;
	
	/**
	 * Permite quitar la foto de usuario a partir de su idPersona
	 * 
	 * @param idPersona
	 * @throws Exception
	 */
	void eliminarFotoPersona(Long idPersona) throws Exception;
}
