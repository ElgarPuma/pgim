package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_TDR_PLANTILLA: 
* @descripción: Plantilla de metadatos para las supervisiones
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimTdrPlantillaDTO {

  /*
  *Identificador interno de la plantilla de metadatos de los TDR para las supervisiones. Secuencia: PGIM_SEQ_TDR_PLANTILLA
  */
  private Long idTdrPlantilla;

  /*
  *Identificador interno de la instancia del proceso PGIM. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Identificador interno de la configuración base para los datos en el marco de la fiscalización. Tabla padre: PGIM_TM_CONFIGURACION_BASE
  */
  private Long idConfiguracionBase;

  /*
  *Objetivo del TDR
  */
  private String deTdrObjetivoTexto;

  /*
  *Alcance del servicio requerido en el TDR
  */
  private String deTdrAlcanceTexto;

  /*
  *Metodología del servicio definido para el TDR
  */
  private String deTdrMetodologiaTexto;

  /*
  *Informe de supervisión definido para el TDR
  */
  private String deTdrInformeSupervTexto;

  /*
  *Honorarios profesionales definidos para el TDR
  */
  private String deTdrHonorariosProf;

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
  *DESC_ID_ESPECIALIDAD
  */
  private Long descIdEspecialidad;

  /*
  *DESC_NO_ESPECIALIDAD
  */
  private String descNoEspecialidad;

  /*
  *DESC_NO_CONFIGURACION_BASE
  */
  private String descNoConfiguracionBase;

  /*
  *DESC_ID_TIPO_CONFIGURACION_BASE
  */
  private Long descIdTipoConfiguracionBase;

  /*
  *DESC_DE_CONFIGURACION_BASE
  */
  private String descDeConfiguracionBase;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}