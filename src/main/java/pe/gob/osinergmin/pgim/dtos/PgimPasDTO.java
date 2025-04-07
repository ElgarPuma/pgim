package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TC_PAS: 
* @descripción: Instancia del procedimiento administrativo sancionador
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPasDTO {

  /*
  *Identificador interno del proceso administrativo sancionador. Secuencia: PGIM_SEQ_PAS
  */
  private Long idPas;

  /*
  *Identificador interno de la supervisión que dio lugar al PAS. Tabla padre: PGIM_TC_SUPERVISION
  */
  private Long idSupervision;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Razón social del agente fiscalizado al momento de la finalización del flujo de trabajo del PAS
  */
  private String noRazonSocialAfiscalizado;
  
  /*
  *Fecha de la creación del PAS desde el proceso de supervisión
  */
  private Date feCreacionPas;

  /*
  *Fecha de la creación del PAS desde el proceso de supervisión
  */
  private String feCreacionPasDesc;

  /*
  *Código de PAS. Formato ejemplo: PAS-2023-00001
  */
  private String coPas;

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
  *Id de la Especialidad
  */
  private Long descIdEspecialidad;

  /*
  *Nombre de la especialidad
  */
  private String descNoEspecialidad;

  /*
  *Número de expediente Siged
  */
  private String descNuExpedienteSiged;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Nombre de la empresa supervisora
  */
  private String descNoRazonSocial;

  /*
  *Nombre del agente supevisado
  */
  private String descNoAgenteSupervisado;

  /*
  *Fecha año de Fiscalización
  */
  private String descFeCreacionPasAnio;

  /*
  *Fecha año de Fiscalización
  */
  private String descCoSupervision;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * número de alertas de cumplimiento de plazo que tiene la fiscalización 
   */
  private Long nuAlertas;
  
  /**
   * numero de intervalo en la cual se encuentra la alerta de cumplimiento de plazo
   */
  private Long nuIntervaloAlertas;


}