package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_CONFIGURACION_BASE: 
* @descripción: Configuración para datos base en la fiscalización
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimConfiguracionBaseDTO {

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Secuencia: PGIM_SEQ_CONFIGURACION_BASE
  */
  private Long idConfiguracionBase;

  /*
  *Identificador interno de la configuración origen desde la que se copió la actual configuración. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
  private Long idConfiguracionBaseOrigen;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Tipo de la relación del paso. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_CONFIGURACION_BASE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoConfiguracionBase;

  /*
  *Nombre de la configuración base que se utilizará en el marco de la fiscalización
  */
  private String noCofiguracionBase;

  /*
  *Descripción de la configuración base que se utilizará en el marco de la fiscalización
  */
  private String deCofiguracionBase;

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
  *DESC_NO_TIPO_CONFIGURACION_BASE
  */
  private String descNoTipoConfiguracionBase;

  /*
  *DESC_NO_ESPECIALIDAD
  */
  private String descNoEspecialidad;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  * Descriptor para obtener el CO_CLAVE_TEXTO del tipo de configuración
  */
  private String descCoClaveTextoTipoConfiguracionBase;

  /**
   * Descriptor para el nombre del objeto del hijo (TDR, cuadro de verificación, requerimiento de documentos)
   */
  private String descNoObjetoHijo;


}