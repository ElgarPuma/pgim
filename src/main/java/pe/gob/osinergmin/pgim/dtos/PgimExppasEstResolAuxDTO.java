package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EXPPAS_EST_RESOL_AUX: 
* @descripción: Vista de expedientes con PAS por estado de resolución, por división supervisora, especialidad y año
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimExppasEstResolAuxDTO {

  /*
  *Identificador interno de la vista PGIM_VW_EXPPAS_EST_RESOL_AUX Secuencia: PGIM_SEQ_EST_RESOL_AUX
  */
  private Long idEstResolAux;

  /*
  *ID_DIVISION_SUPERVISORA
  */
  private Long idDivisionSupervisora;

  /*
  *NO_DIVISION_SUPERVISORA
  */
  private String noDivisionSupervisora;

  /*
  *DE_DIVISION_SUPERVISORA
  */
  private String deDivisionSupervisora;

  /*
  *CO_ESTADO
  */
  private String coEstado;

  /*
  *NO_ESTADO
  */
  private String noEstado;

  /*
  *ANIO_SUPER
  */
  private String anioSuper;

  /*
  *NU_GEOMECANIA
  */
  private Long nuGeomecania;

  /*
  *NU_GEOTECANIA
  */
  private Long nuGeotecania;

  /*
  *NU_PLANTA_BENE
  */
  private Long nuPlantaBene;

  /*
  *NU_TRANSPORTE
  */
  private Long nuTransporte;

  /*
  *NU_VENTILACION
  */
  private Long nuVentilacion;

  /*
  *NU_TOTAL
  */
  private Long nuTotal;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}