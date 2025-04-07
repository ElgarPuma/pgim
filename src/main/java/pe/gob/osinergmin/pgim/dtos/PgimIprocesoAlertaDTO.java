package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_IPROCESO_ALERTA: 
* @descripción: Alerta a nivel de la instancia de proceso
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 14/12/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimIprocesoAlertaDTO {

  /*
  *Identificador interno de la alerta a nivel de la instancia del proceso. Secuencia: PGIM_SEQ_IPROCESO_ALERTA
  */
  private Long idIprocesoAlerta;

  /*
  *Identificador interno del tipo de alerta del proceso. Tabla padre: PGIM_TM_TIPO_PROCESO_ALERTA
  */
  private Long idTipoProcesoAlerta;

  /*
  *Identificador interno padre de la alerta a nivel de la instancia del proceso. Tabla padre: PGIM_TC_IPROCESO_ALERTA
  */
  private Long idIprocesoAlertaPadre;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Fecha y hora inicial del intervalo 1
  */
  private Date feIntervalo1Inicial;

  /*
  *Fecha y hora inicial del intervalo 1
  */
  private String feIntervalo1InicialDesc;

  /*
  *Fecha y hora final del intervalo 1
  */
  private Date feIntervalo1Final;

  /*
  *Fecha y hora final del intervalo 1
  */
  private String feIntervalo1FinalDesc;

  /*
  *Fecha y hora inicial del intervalo 2
  */
  private Date feIntervalo2Inicial;

  /*
  *Fecha y hora inicial del intervalo 2
  */
  private String feIntervalo2InicialDesc;

  /*
  *Fecha y hora final del intervalo 2
  */
  private Date feIntervalo2Final;

  /*
  *Fecha y hora final del intervalo 2
  */
  private String feIntervalo2FinalDesc;

  /*
  *Fecha y hora inicial del intervalo 3
  */
  private Date feIntervalo3Inicial;

  /*
  *Fecha y hora inicial del intervalo 3
  */
  private String feIntervalo3InicialDesc;

  /*
  *Fecha y hora final del intervalo 3
  */
  private Date feIntervalo3Final;

  /*
  *Fecha y hora final del intervalo 3
  */
  private String feIntervalo3FinalDesc;

  /*
  *Indicador lógico que determina si la alerta de la instancia de proceso se encuentra activa o no
  */
  private String flActivo;

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

  /*
  *Etiqueta del objeto del flujo de trabajo
  */
  private String descNoEtiquetaOtrabajo;

  /**
   * Descriptor del nombre del tipo de proeceso alerta 
   */
  private String descNoTipoProcesoAlerta;

  /*
  *Dirección URL de enlace del objeto de negocio
  */
  private String diEnlace;
  
  /**
   * descriptor de nombre de alerta. Tabla : PGIM_TC_ALERTA
   */
  private String descNoAlerta;
  
  /**
   * descriptor de la descripcion de la alerta. Tabla : PGIM_TC_ALERTA
   */
  private String descDeAlerta; 
  
  /**
   * descriptor de la descripción del detalle de la alerta. Tabla: PGIM_TD_ALERTA_DETALLE
   */
  private String descDetAlerta;

  /**
   * numero de intervalo en la cual se encuentra la alerta de cumplimiento de plazo
   */
  private Long nuIntervaloAlertas;
  
  /**
   * nombre del usuario actual del objeto del trabajo
   */
  private String noUsuarioDestino;

}