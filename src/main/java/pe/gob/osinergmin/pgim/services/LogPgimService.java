package pe.gob.osinergmin.pgim.services;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;

/**
 * Interfaz para la gestión de los servicios relacionados con el Log
 * 
 * @descripción: Métodos de utilidad para el Log
 *
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 22/12/2023
 *
 */
public interface LogPgimService {
	
	/**
	 * Prepara y devuelve una cadena para ser usada como prefijo para un mensaje que se va registrar en el Log.
	 * Este método "Simple" solo considera el username en sesión. 
	 * Util para procesos y/o objetos de trabajo no misionales.
	 * 
	 * @param auditoriaDTO
	 * @return
	 */
	String obtenerPrefijoLogSimple(AuditoriaDTO auditoriaDTO);
	
	/**
	 * Prepara y devuelve una cadena para ser usada como prefijo para un mensaje que se va registrar en el Log.
	 * Este método considera el username en sesión y el identificador del objeto de trabajo 
	 * el cual se obtiene a partir del Id de la instancia de proceso.
	 * 
	 * @param idInstanciaProceso
	 * @param auditoriaDTO
	 * @return
	 */
	String obtenerPrefijoLogPorInstanProceso(Long idInstanciaProceso, AuditoriaDTO auditoriaDTO);
	
	/**
	 * Prepara y devuelve una cadena para ser usada como prefijo para un mensaje que se va registrar en el Log.
	 * Este método considera el username en sesión y el identificador del objeto de trabajo 
	 * el cual se obtiene a partir del Id de la instancia de paso.
	 * 
	 * @param idInstanciaPaso
	 * @param auditoriaDTO
	 * @return
	 */
	String obtenerPrefijoLogPorInstanPaso(Long idInstanciaPaso, AuditoriaDTO auditoriaDTO);

}
