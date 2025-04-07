package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_COMPONENTE_MINERO: 
* @descripción: Componente minero, asociado a una unidad minera
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimComponenteMineroDTO {

  /*
  *Identificador interno del componente minero. Secuencia: PGIM_SEQ_COMPONENTE_MINERO
  */
  private Long idComponenteMinero;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Tipo de componente minero de la unidad minera. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_COMPONENTE_MINERO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
  private Long idTipoComponenteMinero;

  /*
  *Identificador interno del tipo de subcomponente minero. Tabla padre: PGIM_TM_TIPO_SUBCOMPONENTE
  */
  private Long idTipoSubcomponente;

  /*
  *Identificador interno del componente padre, cuando tiene valor se trata de un subcomponente. Tabla padre: PGIM_TM_COMPONENTE_MINERO
  */
  private Long idComponenteMineroPadre;

  /*
  *Código del componente minero
  */
  private String coComponente;

  /*
  *Nombre del componente minero
  */
  private String noComponente;

  /*
  *Capacidad de planta expresada en TM/d.
  */
  private BigDecimal nuCapacidadPlanta;

  /*
  *Nivel de aceptación
  */
  private Long nvlAceptacion;

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
  *Nombre del tipo de componente minero
  */
  private String descIdTipoComponenteMinero;

  /*
  *Nombre completo del componente minero (Auxiliar)
  */
  private String descNombreCompleto;

  /*
  *Flag que indica si el componente ha sido seleccionado (Auxiliar)
  */
  private boolean descSeleccionado;

  /*
  *Color que se le asignará al control del flag de selección (Auxiliar)
  */
  private String descColor;

  /*
  *Valor numérico del parámetro
  */
  private Long descNuValorNumerico;

  /*
  *Código y nombre de la unidad minera
  */
  private String descCoUnidadMinera;

  /*
  *Nombre de la unidad minera
  */
  private String descUnidadMinera;

  /*
  *RUC y razón social del agente fiscalizado
  */
  private String descAgenteFiscalizado;

  /*
  *Nombre tipo subcomponente minero
  */
  private String descNoTipoSubcomponente;

  /*
  *Nombre tipo subcomponente minero
  */
  private String descCoTipoSubcomponente;

  /*
  *Nombre tipo subcomponente minero
  */
  private String descCoTipocomponente;

  /*
  *DESC_RUC_AGENTE_FISCALIZADO
  */
  private String descRucAgenteFiscalizado;
  
  /*
  *Usuario en sesión
  */
  private String descUsSesion;

  /*
  *Terminal de usuario en sesión
  */
  private String descIpSesion;

  /**
   * codigo de supervision en donde fue tomado las mediciones 
   * del componente
   */
  private String descCoSupervision;

  private Long descIdSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

private List<PgimCmineroSprvsionDTO> descListaCmineroSprvsion;
private List<PgimComponenteHcDTO> descListaComponenteHcDTO;
private String tipoObjeto;
private Long idHechoConstatado;
}