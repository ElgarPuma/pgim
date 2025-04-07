package pe.gob.osinergmin.pgim.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimArchivoDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.models.entity.PgimArchivo;
import pe.gob.osinergmin.pgim.models.entity.PgimDocumento;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.repository.ArchivoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.services.ArchivoService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Archivo
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 25/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class ArchivoServiceImpl implements ArchivoService{	
	
	@Autowired
	private ArchivoRepository archivoRepository;
	
	@Autowired
	private SubcategoriaDocRepository subCategoriaDocRepository;
	
    
	@Override
	public Long obtenerCorrelativoCodNombre() {
		return this.archivoRepository.obtenerCorrelativoCodNombre();
	}
	
	@Override
	public String codificarArchivoPgim(String nombreArchivo,Long idSubCategoria,Long correlativoArchivo){
		 
		String nombreArchivoNuevo ="";
		
		try {
			
			PgimSubcategoriaDoc subcategoria = subCategoriaDocRepository.findById(idSubCategoria).orElse(null);
			String codCategoria = subcategoria.getPgimCategoriaDoc().getCoCategoriaDocumento();
			nombreArchivo = CommonsUtil.reemplazarCaracteresParticulares(nombreArchivo);
			nombreArchivo = CommonsUtil.removerCaracteresEspeciales(nombreArchivo);
			nombreArchivo = CommonsUtil.extensionArchivoToLowerCase(nombreArchivo);
			nombreArchivoNuevo = correlativoArchivo.toString() + "-" + codCategoria + "-"+ nombreArchivo;
		} catch (Exception e) {		
			log.error(e.getMessage(), e);	
			throw new PgimException(TipoResultado.ERROR, "Hay problemas al codificar el archivo PGIM" + e.getMessage());
		}	
		
		return nombreArchivoNuevo;
	}
	
	@Override
	public MultipartFile validarLongitudNombreArchivo(MultipartFile multipartFile) {
		
		//TRUNCAMOS NOMBRE DEL ARCHIVO EN CASO SUPERE LA LONGITUD PERMITIDA POR EL SIGED
		String nombreArchivo = multipartFile.getName().trim();
		try {
			if(nombreArchivo.length() > ConstantesUtil.PARAM_LONGITUD_MAX_NOMBRE_ARCHIVO) {
				String extensionArchivo = CommonsUtil.obtenerExtensionNombreArchivo(nombreArchivo).toLowerCase();
				String nombreArchivoSinExtension = nombreArchivo.substring(0, nombreArchivo.length()-extensionArchivo.length()-1); //sin el punto
				String nombreFinal = nombreArchivoSinExtension.substring(0, ConstantesUtil.PARAM_LONGITUD_MAX_NOMBRE_ARCHIVO - extensionArchivo.length() -1).trim() + "." +extensionArchivo;
				multipartFile = new MockMultipartFile(nombreFinal, multipartFile.getInputStream());				
			}
		
		} catch (IOException e) {
			String mensaje = "Ocurrió un problema al validar la longitud del nombre del archivo: ";
			log.error(mensaje + e.getMessage(), e);
			throw new PgimException(TipoResultado.ERROR, mensaje + e.getMessage());
			
		}catch (Exception e) {
			String mensaje = "Ocurrió un problema al validar la longitud del nombre del archivo: ";
			log.error(mensaje + e.getMessage(), e);
			throw new PgimException(TipoResultado.ERROR, mensaje + e.getMessage());
		}
		
		return multipartFile;
	}
	
	@Transactional(readOnly = false)
	public Long registrarArchivo(PgimArchivoDTO archivoDTO, AuditoriaDTO auditoriaDTO){
		PgimArchivo archivo = new PgimArchivo();
		
		PgimDocumento documento = new PgimDocumento();		
		documento.setIdDocumento(archivoDTO.getIdDocumento());
		archivo.setPgimDocumento(documento);
			
		archivo.setNoOriginalArchivo(archivoDTO.getNoOriginalArchivo());
		archivo.setNoNuevoArchivo(archivoDTO.getNoNuevoArchivo());
		archivo.setSeArchivo(archivoDTO.getSeArchivo());	
		archivo.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		archivo.setFeCreacion(auditoriaDTO.getFecha());
		archivo.setUsCreacion(auditoriaDTO.getUsername());
		archivo.setIpCreacion(auditoriaDTO.getTerminal());
			
		archivo = archivoRepository.save(archivo);

		return archivo.getIdArchivo();
	}

	@Override
	public Long eliminarArchivo(Long idArchivo) {
		
		return null;
	}

	@Override
	public String obtenerArchivoPorIntanciaPro(String noOriginalArchivo, Long idInstanciaProceso) {

		List<PgimArchivoDTO> pgimArchivoDTO = this.archivoRepository.obtenerArchivoPorIntanciaPro(idInstanciaProceso);

		String ultimosCaracteres = null;
		String archivo = null;

		// Carácter delimitador después del cual deseas obtener los últimos caracteres
		String delimitador = "_";

		for(PgimArchivoDTO obj: pgimArchivoDTO){

			int indiceDelimitador = obj.getNoNuevoArchivo().lastIndexOf(delimitador);

			if (indiceDelimitador != -1) {

				ultimosCaracteres = obj.getNoNuevoArchivo().substring(indiceDelimitador + 1);	

				if(ultimosCaracteres.equals(noOriginalArchivo)){
					archivo = ultimosCaracteres; // Imprime por ejemplo "3114.pdf"
				}

			}
		}

		return archivo;
	}
	
}
