package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_INFRACCION: 
* @descripción: Infracción por obligación normativa de hecho constatado
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimInfraccionDTO {

  /*
  *Identificador interno de la infracción asociada a la obligación normativa de la supervisión. Secuencia: PGIM_SEQ_INFRACCION
  */
  private Long idInfraccion;

  /*
  *Identificador interno del hecho constatado de la supervisión. Tabla padre: PGIM_TC_OBLGCN_NRMTVA_HCHOC
  */
  private Long idOblgcnNrmtvaHchoc;

  /*
  *Identificador interno de la instancia de paso. Tabla padre: PGIM_TC_INSTANCIA_PASO
  */
  private Long idInstanciaPaso;

  /*
  *Identificador interno del PAS al que está asociada la infracción en el marco del flujo de trabajo de fiscalización. Tabla padre: PGIM_TC_PAS
  */
  private Long idPas;

  /*
  *Identificador interno de la infracción origen (supervisión). Tabla padre: PGIM_TD_INFRACCION
  */
  private Long idInfraccionOrigen;

  /*
  *Flag que indica si la infracción será incluida en el PAS o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flIncluirEnPas;

  /*
  *Descripción de la decisión de incluir o no la infracción en el PAS
  */
  private String deSustento;

  /*
  *Fecha de la comisión o detección registrada por el Especialista legal en el paso "Formular el IFI"
  */
  private Date feComisionDeteccion;

  /*
  *Fecha de la comisión o detección registrada por el Especialista legal en el paso "Formular el IFI"
  */
  private String feComisionDeteccionDesc;

  /*
  *Monto exacto de la multa expresado en UIT determinado por el revisor económico
  */
  private BigDecimal moMultaUit;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
  private String esRegistro;

  /*
  *Flag que indica si la infracción se encuentra vigente o no. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flVigente;

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
  *Texto descriptivo del código de la infracción
  */
  private String descCodigo;

  /*
  *Texto descriptivo de la posible la infracción
  */
  private String descInfraccion;

  /*
  *Texto descriptivo del valor de la sanción
  */
  private String descSancionUit;

  /*
  *Identificador del hecho constatado
  */
  private Long descIdHechoConstatado;

  /*
  *Identificador interno de la configuración de criterios de la matriz de supervisión, tomada para una supervisión en particular
  */
  private Long descIdCriterioSprvsion;

  /*
  *Identificador del hecho constatado Reemplazado
  */
  private Long descIdHechoConstatadoReemplazado;

  /*
  *Nombre del rol en el proceso de la persona responsable
  */
  private String descNoRolProceso;

  /*
  *Nombre del responsable del registro o actualización de la infracción
  */
  private String descResponsable;

  /*
  *Nombre de la fase de la infracción
  */
  private String descNoFaseProceso;

  /*
  *Nombre del paso de la infracción
  */
  private String descNoPasoProceso;

  /*
  *Fecha de creación de la infracción
  */
  private Date descFeCreacion;

  /*
  *Fecha de creación de la infracción
  */
  private String descFeCreacionDesc;
  
  /*
  *Propiedad auxiliar: Id de la instancia de paso actual
  */
  private Long descIdInstanciaPasoActual;
  
  /*
  * Lista de contratistas responsables de la infracción 
  */
  private List<PgimInfraccionContraDTO> lstPgimInfraccionContraDTO;
  
  /*
  *Nombre del proceso de la infracción
  */
  private String descNoProceso;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}