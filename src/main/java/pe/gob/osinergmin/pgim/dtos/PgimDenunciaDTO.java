package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_DENUNCIA: 
* @descripción: Registro de denuncia
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDenunciaDTO {

  /*
  *Identificador interno de la denuncia. Secuencia: PGIM_SEQ_DENUNCIA
  */
  private Long idDenuncia;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Medio utilizado para realizar la denuncia. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_NOMBRE>> = MEDIO_DENUNCIA. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>>. Tabla padre: PGIM_TP_PARAMETRO
  */
  private Long idMedioDenuncia;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA. Hace referencia a la persona que realiza la denuncia.
  */
  private Long idPersonaDenunciante;

  /*
  *Identificador interno de la instancia del proceso. Tabla padre: PGIM_TC_INSTANCIA_PROCES
  */
  private Long idInstanciaProceso;

  /*
  *Código de la denuncia
  */
  private String coDenuncia;

  /*
  *Fecha de presentación de la denuncia
  */
  private Date fePresentacion;

  /*
  *Fecha de presentación de la denuncia
  */
  private String fePresentacionDesc;

  /*
  *Descripción de la denuncia
  */
  private String deDenuncia;

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
  *Número del expediente Siged asociado a la denuncia
  */
  private String descNuExpedienteSiged;

  /*
  *Nombre completo de la persona denunciante
  */
  private String descNoPersonaDenunciante;

  /*
  *Nombre de medio de la denuncia
  */
  private String descMedioDenuncia;

  /*
  *Obtiene el máximo número de código de denuncia
  */
  private String descMaxCoDenuncia;

  /*
  *Nombre de la unidad minera
  */
  private String descNoUnidadMinera;

  /*
  *Tipo de documento de identidad
  */
  private Long descIdTipoDocIdentidad;

  /*
  *Rango de fecha final
  */
  private Date fePresentacionHasta;

  /*
  *Rango de fecha final
  */
  private String fePresentacionHastaDesc;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}