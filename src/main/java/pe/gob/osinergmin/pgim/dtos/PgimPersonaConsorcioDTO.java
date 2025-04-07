package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_PERSONA_CONSORCIO: 
* @descripción: Configuración de las personas jurídicas que conforman un consorcio
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimPersonaConsorcioDTO {

  /*
  *Identificador interno de la persona. Secuencia: PGIM_SEQ_PERSONA_CONSORCIO
  */
  private Long idPersonaConsorcio;

  /*
  *Identificador interno de la persona jurídica que tiene el tipo consorcio. Se debe precisar que una persona jurídica consorcio no puede conformar parte de otro consorcio
  */
  private Long idPersonaJuridicaConsorcio;

  /*
  *Identificador interno de la persona jurídica que no es consorcio pero que forma parte de uno. Se debe precisar que una persona jurídica puede conformar más de un consorcio
  */
  private Long idPersona;

  /*
  *Valor lógico que señala si la persona es principal o representa al consorcio. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flEsPrincipal;

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
  *DESC_CO_DOCUMENTO_IDENTIDAD
  */
  private String descCoDocumentoIdentidad;

  /*
  *DESC_CO_DOCUMENTO_IDENTIDAD_CONSORCIO
  */
  private String descCoDocumentoIdentidadConsorcio;

  /*
  *DESC_NO_RAZON_SOCIAL
  */
  private String descNoRazonSocial;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}