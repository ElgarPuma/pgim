package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_INSTANCIA_PROCES: 
* @descripción: Instancia de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInstanciaProcesDTO {

  /*
  *Identificador interno de la instancia del proceso PGIM. Secuencia: PGIM_SEQ_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la instancia del proceso padre. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProcesoPadre;

  /*
  *Identificador interno del proceso. Tabla padre: PGIM_TM_PROCESO
  */
  private Long idProceso;

  /*
  *Nombre de la tabla en donde se encuentra la instancia de negocio
  */
  private String noTablaInstancia;

  /*
  *Identificador interno de la instancia de negocio en la tabla señalada por NO_TABLA_INSTANCIA
  */
  private Long coTablaInstancia;

  /*
  *Número de expediente Siged
  */
  private String nuExpedienteSiged;
  
  /*
  *Nombre del usuario Windows del flujo principal; este se actualiza al realizar una asignación convecional (reenvío simple) o asignación en subflujo inicial (aquel que se realiza desde una instancia de paso cuya columna ID_TIPO_SUBFLUJO = NULL ) colocando, en este último caso, al usuario del subflujo que se defina como principal en el asignación
  */
  private String noUsuarioPrincipal;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Usuario creador
  */
  private String usCreacion;

  /*
  *Terminal de creación
  */
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
  private Date feCreacion;

  /*
  *Fecha y hora de creación
  */
  private String feCreacionDesc;

  /*
  *Usuario modificador
  */
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private Date feActualizacion;

  /*
  *Fecha y hora de modificación
  */
  private String feActualizacionDesc;

  /*
  *Nombre de tabla de la instancia
  */
  private String descNoTablaInstancia;

  /*
  *Identificador del contrato relacionado con la instancia de proceso
  */
  private Long descIdContrato;

  /*
  *Identificador de la especialidad relacionada con la instancia de proceso
  */
  private Long descIdEspecialidad;

 /***
  * Identificador de la fase actual del PAS para realizar la copia de documentos sobre la instancia del proceso PAS
  */
  Long descIdFaseActualPas;  

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}