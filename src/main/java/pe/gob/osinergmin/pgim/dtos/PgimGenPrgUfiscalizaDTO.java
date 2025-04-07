package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_GEN_PRG_UFISCALIZA: 
* @descripción: Generación de programa por unidad fiscalizable
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 27/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimGenPrgUfiscalizaDTO {

  /*
  *Identificador interno de la generación del programa por unidad fiscalizable. Secuencia: PGIM_SEQ_GEN_PRG_UFISCALIZA
  */
  private Long idGenPrgUfiscaliza;

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Tabla padre: PGIM_TD_GEN_PRG_RANKING
  */
  private Long idGenPrgRanking;

  /*
  *Identificador interno de la unidad minera dentro del ranking. Tabla padre: PGIM_TD_RANKING_UM
  */
  private Long idRankingUm;

  /*
  *Puntaje de riesgo de la unidad fiscalizable en el respectivo ranking de riesgo. Copiado desde la columna respectiva PGIM_TD_RANKING_UM.MO_PUNTAJE
  */
  private BigDecimal moPuntajeRiesgo;

  /*
  *Proporción del puntaje de riesgo de la unidad fiscalizable respecto a la sumatoria de los puntajes de riesgo de todas las unidades fiscalizables en el respectivo ranking de riesgo
  */
  private BigDecimal moPuntajeProporcion;

  /*
  *Proporción entera del puntaje de riesgo de la unidad fiscalizable respecto a la sumatoria de los puntajes de riesgo de todas las unidades fiscalizables en el respectivo ranking de riesgo
  */
  private Integer moProporcionEntera;

  /*
  *Cantidad de visitas a realizar por unidad fiscalizable. Si el valor de la columna MO_PROPORCION_ENTERA es >= 1 entonces se debe tomar el valor mínimo entre MO_PROPORCION_ENTERA y PGIM_TC_GEN_PROGRAMA.NU_MAX_FISCA_ANUAL_XUF; por otro lado, si es igual a cero, entonces se debe tomar el valor de 1
  */
  private Integer caVisitas;

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
  *Identificador interno de la unidad minera
  */
  private Long descIdUnidadMinera;
}