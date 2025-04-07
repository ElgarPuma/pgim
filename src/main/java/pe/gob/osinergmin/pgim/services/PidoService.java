/**
 * 
 */
package pe.gob.osinergmin.pgim.services;

import javax.xml.bind.JAXBException;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.pido.IntegracionPido;
import pe.gob.osinergmin.pgim.pido.PidoBeanOutRO;

public interface PidoService {
	
	IntegracionPido procesaObtenerCiudadano(String numeroDNIoCE, AuditoriaDTO auditoriaDTO) throws JAXBException;
	IntegracionPido procesaObtenerContribuyente(String numeroRUC, AuditoriaDTO auditoriaDTO) throws JAXBException;
	PidoBeanOutRO procesaConsultarCiudadano(String numeroDNIoCE, AuditoriaDTO auditoriaDTO) throws Exception;
}
