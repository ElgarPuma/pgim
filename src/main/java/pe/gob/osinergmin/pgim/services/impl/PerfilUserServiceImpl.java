package pe.gob.osinergmin.pgim.services.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.PgimPersonalOsiDTO;
import pe.gob.osinergmin.pgim.exception.PgimException;
import pe.gob.osinergmin.pgim.exception.PgimException.TipoResultado;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.PerfilUserService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad perfil user
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 20/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020 
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class PerfilUserServiceImpl implements PerfilUserService {
	
	@Autowired
	private PropertiesConfig propertiesConfig;
	
	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Override
	@Transactional(readOnly = false)
	public void guardarFotoUsuario(MultipartFile fileFoto, String noUsuario)
			throws Exception {
		
		PgimPersonalOsiDTO pgimPersonalOsiDTO = this.flujoTrabajoService.obtenerPersonaOsiPorUsuario(noUsuario);
		
		if(pgimPersonalOsiDTO == null) {
			String mensaje = String.format("El usuario %s no forma parte del personal de Osinergmin, motivo por el cual no puede actualizar su foto de perfil", noUsuario);
			throw new PgimException(TipoResultado.WARNING, mensaje);
		}
		
		String nombreFoto = String.format(ConstantesUtil.FORMATO_NOMBRE_FOTO_USUARIO, pgimPersonalOsiDTO.getIdPersona());
		
		this.almacenarFoto(fileFoto, nombreFoto);

	}	
	
	@Override
	public String obtenerFotoPersona(Long idPersona) throws Exception {
		
		String carpetaPhotos = propertiesConfig.getCarpetaPhotos();
		String nombreArchivo = String.format(ConstantesUtil.FORMATO_NOMBRE_FOTO_USUARIO + ConstantesUtil.EXTENSION_NOMBRE_FOTO_USUARIO, idPersona);
		String photoBase64 = "";
		
		String filePath = carpetaPhotos + nombreArchivo;
		
        File file = new File(filePath);
        
        Resource resource = new UrlResource(file.toURI());

        if (resource.exists() && resource.isReadable()) {        
	        byte[] photoBytes = Files.readAllBytes(file.toPath());
	        photoBase64 = Base64Utils.encodeToString(photoBytes);
        }
        
        return photoBase64;
	}
	
	@Override
	public void eliminarFotoPersona(Long idPersona) throws Exception {
		
		String carpetaPhotos = propertiesConfig.getCarpetaPhotos();
		String nombreArchivo = String.format(ConstantesUtil.FORMATO_NOMBRE_FOTO_USUARIO + ConstantesUtil.EXTENSION_NOMBRE_FOTO_USUARIO, idPersona);
		
		String filePath = carpetaPhotos + nombreArchivo;
		
		new File(filePath).delete();
	}
	
	/**
	 * Permite almacenar el archivo de foto con un nombre dado
	 * 
	 * @param fileFoto
	 * @param nombreFoto
	 * @throws Exception
	 */
	public void almacenarFoto(MultipartFile fileFoto, String nombreFoto)
			throws Exception {				
		
		String extension = ConstantesUtil.EXTENSION_NOMBRE_FOTO_USUARIO; //Para uniformizar y poder recuperar luego la foto con esta extensión
		
		// Depurando nombre que se le asignará al archivo
		String nombreFotoDepurado = CommonsUtil.reemplazarCaracteresParticulares(nombreFoto);
		nombreFotoDepurado = CommonsUtil.removerCaracteresEspeciales(nombreFotoDepurado);
		
		String nombreArchivo = nombreFotoDepurado + extension;

		// Cambiando nombre al archivo 
		MultipartFile multipartFile = new MockMultipartFile(nombreArchivo, fileFoto.getInputStream());
		
		// Comprimir la imagen antes de almacenarla
		BufferedImage imagenComprimida = this.comprimirImagen(multipartFile.getBytes(), nombreArchivo);		
		
		// Almacenamos la imagen comprimida en formato JPEG 
		String outputFilePath = propertiesConfig.getCarpetaPhotos() + nombreArchivo; 		
        
        ImageIO.write(imagenComprimida, "jpeg", new File(outputFilePath));		
	}
	
	/**
	 * Permite comprimir una imagen, escalando su tamaño, 
	 * obteniendo así un archivo con peso mucho menor al original. 
	 * 
	 * @param photoBytes	Foto en formato array de bytes
	 * @param nombreArchivo	Nombre para conformar la ruta del archivo temporal
	 * @return
	 * @throws Exception
	 */
    private BufferedImage comprimirImagen(byte[] photoBytes, String nombreArchivo) throws Exception {
    	
    	log.info("Inicia método de comprimir imagen");
    	
        String inputFilePath = propertiesConfig.getCarpetaTmp() + "tmpin_" + nombreArchivo; // Ruta temporal para el archivo de entrada
        
        // Guardar los bytes de la foto en un archivo temporal
        Files.write(new File(inputFilePath).toPath(), photoBytes);

        // Cargar la imagen original
        BufferedImage originalImage = ImageIO.read(new File(inputFilePath));

        // Escalar la imagen a un tamaño más pequeño y con una calidad del 90%
        BufferedImage scaledImage = Scalr.resize(originalImage, Method.QUALITY, Mode.AUTOMATIC, 900);
//		BufferedImage scaledImg = Scalr.resize(originalImage, 1280, 960);

        // Eliminar los archivos temporales
        new File(inputFilePath).delete();
        
        log.info("Finaliza método de comprimir imagen");

        return scaledImage;
    }
}
