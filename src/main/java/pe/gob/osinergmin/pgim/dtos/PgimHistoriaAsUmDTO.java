package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TZ_HISTORIA_AS_UM: 
* @descripción: Trazabilidad de agente supervisado titular de la UM en el tiempo
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimHistoriaAsUmDTO {

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_HISTORIA_AS_UM
  */
  private Long idHistoriaAsUm;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Identificador interno del agente supervisado previo. Tabla padre: PGIM_TM_AGENTE_SUPERVISADO
  */
  private Long idAgenteSupervisadoPrevio;

  /*
  *Fecha del inicio de la titularidad del nuevo titular
  */
  private Date feInicioTitularidad;

  /*
  *Fecha del inicio de la titularidad del nuevo titular
  */
  private String feInicioTitularidadDesc;

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
  *Identificador interno del nuevo agente supervisado.
  */
  private Long idAgenteSupervisadoNuevo;

  /*
  *Código del documento de identidad del agente supervisado previo
  */
  private String coDocidAgenteSupervisadoPrevio;

  /*
  *Razón social del agente supervisado previo
  */
  private String descAgenteSupervisadoPrevio;

  /*
  *Razón social del nuevo agente supervisado
  */
  private String descAgenteSupervisadoNuevo;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}