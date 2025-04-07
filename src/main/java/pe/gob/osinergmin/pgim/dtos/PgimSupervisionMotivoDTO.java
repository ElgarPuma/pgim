package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_SUPERVISION_MOTIVO: 
* @descripción: Motivos de la supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimSupervisionMotivoDTO {

  /*
  *Identificador interno del motivo de la supervisión. Secuencia: PGIM_SEQ_SUPERVISION_MOTIVO
  */
  private Long idSupervisionMotivo;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;
  
  /*
  *Tipo de motivio de inicio. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MOTIVO_INICIO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoMotivoInicio; 
  
  /*
  *Identificador del objeto del motivo de inicio de una fiscalización.
  */
  private Long idObjetoMotivoInicio; 

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
  *Código del evento
  */
  private String descCoEvento;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Nombre del tipo del evento
  */
  private String descNoTipoEvento;

  /*
  *Nombre del tipo de evento asociado a la UM
  */
  private String descNoTipoEventoUm;

  /*
  *Fecha del evento
  */
  private Date descFeEvento;

  /*
  *Fecha del evento
  */
  private String descFeEventoDesc;

  /*
  *Descripción del evento
  */
  private String descDeEvento;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}