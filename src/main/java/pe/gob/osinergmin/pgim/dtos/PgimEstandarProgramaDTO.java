package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_ESTANDAR_PROGRAMA: 
* @descripción: Datos estándares del programa
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEstandarProgramaDTO {

  /*
  *Identificador interno del dato estándar del programa. Secuencia: PGIM_SEQ_ESTANDAR_PROGRAMA
  */
  private Long idEstandarPrograma;

  /*
  *Identificador interno del subtipo de supervisión. Tabla padre: PGIM_TM_SUBTIPO_SUPERVISION
  */
  private Long idSubtipoSupervision;

  /*
  *Identificador interno del motivo de la supervisión. Tabla padre PGIM_TM_MOTIVO_SUPERVISION
  */
  private Long idMotivoSupervision;

  /*
  *Identificador interno de la línea base del programa. Tabla padre: PGIM_TC_LINEA_PROGRAMA
  */
  private Long idLineaPrograma;

  /*
  *Identificador interno del dato estándar del programa base desde donde se copiaron los datos para la creación de este registro. Tabla padre PGIM_TD_ESTANDAR_PROGRAMA
  */
  private Long idEstandarProgramaBase;

  /*
  *Monto por supervisión S/
  */
  private BigDecimal moPorSupervision;

  /*
  *Cantidad de días de supervisión
  */
  private Integer caDiasSupervision;

  /*
  *Cantidad de supervisiones. Solo aplica para algunos tipos de no programadas, para las demás se dejará en NULL
  */
  private Integer caSupervisiones;

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
  *Descripción del tipo de supervisión
  */
  private String descTipoSupervision;

  /*
  *Descripción del subtipo de supervisión y el motivo
  */
  private String descSubtipoMotivo;

  /*
  *Costo total de las supervisiones
  */
  private BigDecimal descCoTotalSupervisiones;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}