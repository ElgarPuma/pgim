package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TC_CONTRATO: 
* @descripción: Instancia de contrato de supervisión
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimContratoDTO {

  /*
  *Identificador interno del contrato. Secuencia: PGIM_SEQ_CONTRATO
  */
  private Long idContrato;

  /*
  *Identificador interno de la empresa supervisora. Tabla padre: PGIM_TM_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la especialidad de supervisión. Tabla padre: PGIM_TM_ESPECIALIDAD
  */
  private Long idEspecialidad;

  /*
  *Número de contrato
  */
  private String nuContrato;

  /*
  *Descripción de contrato
  */
  private String deContrato;

  /*
  *Porcentaje del costo para el entregable tipo acta
  */
  private Long pcEntregableActa;

  /*
  *Porcentaje del costo para el entregable tipo informe
  */
  private Long pcEntregableInforme;

  /*
  *Porcentaje del costo para el entregable tipo informe
  */
  private Long pcEntregableFinal;

  /*
  *Fecha de inicio del contrato de acuerdo con lo firmado
  */
  private Date feInicioContrato;

  /*
  *Fecha de inicio del contrato de acuerdo con lo firmado
  */
  private String feInicioContratoDesc;

  /*
  *Fecha fin del contrato de acuerdo con lo firmado
  */
  private Date feFinContrato;

  /*
  *Fecha fin del contrato de acuerdo con lo firmado
  */
  private String feFinContratoDesc;

  /*
  *Monto del importe del contrato de acuerdo con lo firmado
  */
  private BigDecimal moImporteContrato;

  /*
  *Número de días para que el supervisor/a presente el informe de supervisión
  */
  private Integer nuDiasEntregaInforme;

  /*
  *Número de días calendario para que el fiscalizador realice la revisión del informe de fiscalización y determine su aprobación u observación
  */
  private Integer nuDiasRevisionInforme;

  /*
  *Número de días para que el supervisor/a presente el informe de supervisión subsanado
  */
  private Integer nuDiasAbsolucionInforme;

  /*
  *Último valor de la secuencia de la liquidación generado para el contrato, el valor NULL se tomará como 0
  */
  private Long seLiquidacionContrato;

  /*
  *Flag que indica si el contrato se encuentra vigente. Los posibles valores son: "1" = Vigente y "0" = No vigente
  */
  private String flEstado;

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
  *Número de expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  *Razón social de la empresa supervisora
  */
  private String descNoRazonSocial;

  /*
  *Nombre de la especialidad asociada al contrato de supervisión
  */
  private String descNoEspecialidad;

  /*
  *Saldo del contrato
  */
  private BigDecimal descSaldoContrato;

  /*
  *Mensaje de la validación al validar saldo de contrato
  */
  private String descMensajeValidacionSaldoContrato;

  /*
  *Monto total comprometido del contrato
  */
  private BigDecimal descMontoTotalComprometido;

  /*
  *Resumen del consumo del contrato S/
  */
  private BigDecimal descResumenConsumoContrato;

  /*
  *Monto total pre-comprometido del contrato S/
  */
  private BigDecimal descMontoTotalPreComprometido;

  /*
  *Monto total por liquidar del contrato S/
  */
  private BigDecimal descMontoTotalPorLiquidar;

  /*
  *Monto total liquidado del contrato S/
  */
  private BigDecimal descMontoTotalLiquidado;

  /*
  *Monto total facturado del contrato S/
  */
  private BigDecimal descMontoTotalFacturado;

  /*
  *DESC_ID_PERSONA
  */
  private Long descIdPersona;

  /*
  *DESC_FL_CONSORCIO
  */
  private String descFlConsorcio;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /*
  * Nombre Windows de usuario que se utilizará para todos los usuarios del contrato. Este dato se utilizará para obtener el identificador interno del usuario Siged.
  */
  private String noUsuarioXDefecto;
}