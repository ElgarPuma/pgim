package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_RELACION_ACCION: 
* @descripción: Acciones por relación de paso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimRelacionAccionDTO {

  /*
  *Identificador interno de la acción por relación de paso. Secuencia: PGIM_SEQ_RELACION_ACCION
  */
  private Long idRelacionAccion;

  /*
  *Identificador interno de la relación de paso. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPaso;

  /***
  * Identificador interno del paso con el que se creará la relación para asignaciones paralelas. Tabla padre: PGIM_TM_RELACION_PASO
  */
  private Long idRelacionPasoParalela;    

  /*
  *Valor lógico que señala si la relación dada por el valor en la columna PGIM_TM_RELACION_ACCION.ID_RELACION_PASO_PARALELA será creada con el valor del paso activo o no activo. Los posibles valores son: "1" = Activo y "0" = Inactivo; para los valores NULL quiere decir que no aplica porque el valor PGIM_TM_RELACION_ACCION.ID_RELACION_PASO_PARALELA = NULL
  */
  private String flActivarInstanciaPaso;
   
  /*
  *Descripción de la acción a realizarse durante la transición de paso
  */
  private String deAccion;

  /*
  *Número de orden de presentación
  */
  private Long nuOrden;

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

  /***
  * Nombre del paso del proceso asociado
  */
  private String descNoPasoProceso;  

  /***
  * Nombre del rol del paso de proceso
  */
  private String descNoRolPasoProceso;    

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}