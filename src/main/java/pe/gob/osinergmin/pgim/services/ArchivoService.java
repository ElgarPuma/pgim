package pe.gob.osinergmin.pgim.services;

import org.springframework.web.multipart.MultipartFile;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimArchivoDTO;

public interface ArchivoService {
	
	Long obtenerCorrelativoCodNombre();
	
	String codificarArchivoPgim(String nombreArchivo,Long idSubCategoria,Long correlativoArchivo);
	
	MultipartFile validarLongitudNombreArchivo(MultipartFile multipartFile);
	
	Long registrarArchivo(PgimArchivoDTO archivoDTO, AuditoriaDTO auditoriaDTO);

	Long eliminarArchivo(Long idArchivo);

	String obtenerArchivoPorIntanciaPro(String noOriginalArchivo, Long idInstanciaProceso);
	
}
