package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_COSTO_UNITARIO: 
* @descripción: Costo unitario en S/ detallado por día para le regla del contrato
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimCostoUnitarioDTO {

  /*
  *Identificador interno del costo unitario por día del contrato. Secuencia: PGIM_SEQ_COSTO_UNITARIO
  */
  private Long idCostoUnitario;

  /*
  *Identificador interno del tarifario del contrato. Tabla padre: PGIM_TD_TARIFARIO_CONTRATO
  */
  private Long idTarifarioContrato;

  /*
  *Número de día para el costo unitario
  */
  private Integer unDiaCostoUnitario;

  /*
  *Monto del costo unitario del día
  */
  private BigDecimal moCostoUnitario;

  /*
  *Monto del costo del acta
  */
  private BigDecimal moCostoActa;

  /*
  *Monto del costo del informe de supervisión
  */
  private BigDecimal moCostoInformeSupervision;

  /*
  *Monto del costo del informe de supervisión
  */
  private BigDecimal moCostoInformeGestion;

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
  *Costo S/ del acta de supervisión
  */
  private String descCostoActa;

  /*
  *Costo S/ del informe de supervisión
  */
  private String descCostoInformeSupervision;

  /*
  *Costo S/ del informe de gestión
  */
  private String descCostoInformeGestion;

  /*
  *Monto del costo unitario del día
  */
  private String descMoCostoUnitario;

  /*
  *Nombre del tarifario del contrato
  */
  private String descNoTarifario;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}