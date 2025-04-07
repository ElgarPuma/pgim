package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TD_PERSONAL_OSI_CARGO: 
* @descripción: Cargos del personal de Osinergmin
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 20/01/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonalOsiCargoDTO {

  /*
  *Identificador interno del personal de Osinergmin. Secuencia: PGIM_SEQ_PERSONAL_OSI_CARGO
  */
  private Long idPersonalOsiCargo;

  /*
  *Identificador interno del personal del Osinergin. Table padre: PGIM_TC_PERSONAL_OSI
  */
  private Long idPersonalOsi;

  /*
  *Identificador interno de la unidad orgánica. Table padre: PGIM_TM_UNIDAD_ORGANICA
  */
  private Long idUnidadOrganica;

  /*
  *Nombre del cargo que funge el personal del Osinergmin
  */
  private String noCargo;

  /*
  *Fecha de inicio de vigencia del cargo
  */
  private Date feInicio;

  /*
  *Fecha de inicio de vigencia del cargo
  */
  private String feInicioDesc;

  /*
  *Fecha final de vigencia del cargo
  */
  private Date feFin;

  /*
  *Fecha final de vigencia del cargo
  */
  private String feFinDesc;

  /*
  *Indicador lógico que determina  si el cargo es principal o no lo es. El valor "1" indica que sí es principal, por otro lado, el valor "0" indica que no lo es.
  */
  private String flPrincipal;

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
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Código de la unidad orgánica
   */
  private String descCoUnidadOrganica;

  /**
   * Nombre de la unidad orgánica
   */
  private String descNoUnidadOrganica;

    /*
  *Número de expediente siged de encargatura
  */
  private String nuExpedienteSiged;

  /*
  *Código del tipo de documento siged de encargatura
  */
  private Long coTipoDocumentoSiged;

  /*
  *Número de documento siged de encargatura
  */
  private String nuDocumentoSiged;
}