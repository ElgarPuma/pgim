package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.util.List;

/**
* DTO para la entidad PGIM_TM_AUTORIZACION: 
* @descripción: Autorización, asociada a una unidad minera
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAutorizacionDTO {

  /*
  *Identificador interno de la Autorización. Secuencia: PGIM_SEQ_AUTORIZACION
  */
  private Long idAutorizacion;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Tipo Autorización. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_AUTORIZACION. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoAutorizacion;

  /*
  *Número de documento de la autorización
  */
  private String nuDocumento;

  /*
  *Identificador interno de la persona. Table padre: PGIM_TM_PERSONA; especifica la entidad emisora de la autorización.
  */
  private Long idPersona;

  /*
  *Fecha de inicio de la autorización
  */
  private Date feInicioAutorizacion;

  /*
  *Fecha de inicio de la autorización
  */
  private String feInicioAutorizacionDesc;

  /*
  *Fecha de fin de la autorización
  */
  private Date feFinAutorizacion;

  /*
  *Fecha de fin de la autorización
  */
  private String feFinAutorizacionDesc;

  /*
  *Nota de la autorización
  */
  private String deNota;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

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
  *Razón social de la entidad emisora
  */
  private String descNoRazonSocial;

  /*
  *Listado de componentes mineros
  */
  private List<PgimComponenteMineroDTO> auxListaComponentesMineros;

  /*
  *Tipo de la autorización
  */
  private String descTipoAutorizacion;

  /*
  *Nombre corto de la persona que emite la autorización
  */
  private String descNoCorto;

  /*
  *Número de expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}