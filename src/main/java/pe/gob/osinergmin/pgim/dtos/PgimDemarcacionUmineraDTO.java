package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TM_DEMARCACION_UMINERA: 
* @descripción: Demarcación de la unidad minera
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimDemarcacionUmineraDTO {

  /*
  *Identificador interno de la unidad minera. Secuencia: PGIM_SEQ_DEMARCACION_UMINERA
  */
  private Long idDemarcacionUm;

  /*
  *Identificador interno del ubigeo. Tabla padre: PGIM_TM_UBIGEO
  */
  private Long idUbigeo;

  /*
  *Identificador interno de la unidad minera. Tabla padre: PGIM_TM_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *Indicador lógico que señala si el ubigeo es principal. Los posibles valores son: "1" = Sí y "0" = No
  */
  private String flPrincipal;

  /*
  *Porcentaje del ubigeo dentro de la demarcación de una unidad minera
  */
  private BigDecimal pcUbigeo;

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
  *Departamento correspondiente al ubigeo
  */
  private String descDepartamento;

  /*
  *Provincia correspondiente al ubigeo
  */
  private String descProvincia;

  /*
  *Distrito correspondiente al ubigeo
  */
  private String descDistrito;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}