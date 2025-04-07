package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_CONSUMO_CONTRA: 
* @descripción: Consumo del contrato de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimConsumoContraDTO {

  /*
  *Identificador interno del consumo del contrato. Secuencia: PGIM_SEQ_CONSUMO_CONTRA
  */
  private Long idConsumoContra;

  /*
  *Identificador interno del contrato. Tabla padre: PGIM_TC_CONTRATO
  */
  private Long idContrato;

  /*
  *Identificador interno de la supervisión. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Monto de consumo del contrato
  */
  private BigDecimal moConsumoContrato;

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
  *Nombre del tarifario del contrato
  */
  private String descNoTarifario;

  /*
  *Nombre del tarifario del contrato
  */
  private BigDecimal descMoSupervisionFallida;

  /*
  *Costo unitario determinado en el tarifario
  */
  private BigDecimal descMoCostoUnitario;

  /*
  *Cantidad de días calculados entre el inicio y final de la supervisión
  */
  private Long descDiasCalculadosInicioFin;

  /*
  *Saldo S/ del contrato
  */
  private BigDecimal descSaldoContrato;

  /*
  *Monto S/ faltante
  */
  private BigDecimal descMontoFaltante;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}