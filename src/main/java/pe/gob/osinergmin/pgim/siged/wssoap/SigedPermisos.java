package pe.gob.osinergmin.pgim.siged.wssoap;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * * Clase para recibir los permisos que tiene un usuario respecto a un
 * expediente. Todos los métodos que se vayan migrando en el OSB deben tener su
 * propio permiso en un atributo
 *
 * Autor: lbarrantes Versión: 1.0 Creado el: 0501/2021
 */
@Getter
@Setter
@NoArgsConstructor
public class SigedPermisos {

	/***
	 * Número del expediente Siged
	 */
	
	private String descNuExpedienteSiged;
	
	/***
	 * Código de resultado
	 */
	private String resultCode;
	
	/***
	 * Mensaje del servicio
	 */
	private String message;

	/***
	 * Acción Listar documentos
	 */
	private boolean listarDocumentos;

	/***
	 * Acción crear expediente
	 */
	private boolean crearExpediente;

	/***
	 * Acción listar archivos
	 */
	private boolean listarArchivos;

	/***
	 * Acción agregar documento
	 */
	private boolean agregarDocumento;

	/***
	 * Acción agregar archivo a documento
	 */
	private boolean agregarArchivoADocumento;

	/***
	 * Acción descargar archivo
	 */
	private boolean descargarArchivo;

	/***
	 * Acción anular archivo
	 */
	private boolean anularArchivo;

	/***
	 * Acción anular documento
	 */
	private boolean anularDocumento;

	/***
	 * Acción obtener usuario
	 */
	private boolean obtenerUsuario;

}
