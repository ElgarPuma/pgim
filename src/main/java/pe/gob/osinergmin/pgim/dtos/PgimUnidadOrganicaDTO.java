package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_UNIDAD_ORGANICA: 
* @descripción: Unidad orgánica del Osinergmin
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 20/01/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimUnidadOrganicaDTO {

  /*
  *Identificador interno de la unidad orgánica del Osinergmin. Secuencia: PGIM_SEQ_UNIDAD_ORGANICA
  */
  private Long idUnidadOrganica;

  /*
  *Código del ubigeo
  */
  private String coUnidadOrganica;

  /*
  *Nombre del ubigeo
  */
  private String noUnidadOrganica;

  /*
  *Código iIdentificador del usuario Siged para la numeración de documentos (usuario ficticio)
  */
  private Long coUsuarioSigedNumerador;

  /*
  *Nombre del usuarioSiged para la numeración de documentos (usuario ficticio)
  */
  private String noUsuarioSigedNumerador;

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
  *Distrito
  */
  private String descDistrito;

  /*
  *Provincia
  */
  private String descProvincia;

  /*
  *Departamento
  */
  private String descDepartamento;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}