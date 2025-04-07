package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
* DTO para la entidad PGIM_TC_GEN_PROGRAMA: 
* @descripción: Generación de programa de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 27/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimGenProgramaDTO {

  /*
  *Identificador interno de la propuesta de programa. Secuencia: PGIM_SEQ_GEN_PROGRAMA
  */
  private Long idGenPrograma;

  /*
  *Identificador interno del programa de supervisión. Tabla padre: PGIM_TC_PRGRM_SUPERVISION
  */
  private Long idProgramaSupervision;

  /*
  *Estado de generación conforme. Los posibles valores son: "1" = Conforme y "0" = No conforme
  */
  private String flConforme;

  /*
  *Fecha de la generación de la propuesto del programa
  */
  private Date feGeneracion;

  /*
  *Fecha de la generación de la propuesto del programa
  */
  private String feGeneracionDesc;

  /*
  *Cantidad anual máxima de fiscalizaciones por unidad fiscalizable
  */
  private Integer nuMaxFiscaAnualXuf;

  /*
  *Cantidad mensual máxima de fiscalizaciones
  */
  private Integer nuMaxFiscaMensual;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de enero
  */
  private Integer nuEnero;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de febrero
  */
  private Integer nuFebrero;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de marzo
  */
  private Integer nuMarzo;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de abril
  */
  private Integer nuAbril;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de mayo
  */
  private Integer nuMayo;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de junio
  */
  private Integer nuJuno;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de julio
  */
  private Integer nuJulio;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de agosto
  */
  private Integer nuAgosto;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de septiembre
  */
  private Integer nuSeptiembre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de octubre
  */
  private Integer nuOctubre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de noviembre
  */
  private Integer nuNoviembre;

  /*
  *Cantidad de fiscalizaciones programadas en el mes de diciembre
  */
  private Integer nuDiciembre;

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
   * descriptor del nombre de la unidad minera 
   */
  private String descNoUnidadMinera;

  /**
   * descriptor del identificador interno de la unidad minera 
   */
  private Long descIdUnidadMinera;

  /*
  * Descriptor del Número del mes: Enero = 1, Febrero = 2, Marzo = 3, …, Octubre = 10, Noviembre = 11 y Diciembre = 12
  */
  private BigDecimal descNuMes;

  /*
  * Descriptor del Identificador interno del ranking de riesgo. Secuencia: PGIM_SEQ_RANKING_RIESGO
  */
  private Long descIdRankingRiesgo;

  /*
  * Descriptor del Nombre del ranking de riesgo
  */
  private String descNoRankingRiesgo;

}