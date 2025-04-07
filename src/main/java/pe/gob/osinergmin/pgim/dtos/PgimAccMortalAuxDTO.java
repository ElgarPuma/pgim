package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_VW_ACC_MORTAL_AUX: 
* @descripción: Vista para obtener la lista de los accidentes mortales
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimAccMortalAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_EVENTO_AUX
  */
  private Long idEventoAux;

  /*
  *ID_EVENTO
  */
  private Long idEvento;

  /*
  *ID_UNIDAD_MINERA
  */
  private Long idUnidadMinera;

  /*
  *ID_TIPO_EVENTO
  */
  private Long idTipoEvento;

  /*
  *NO_TIPO_EVENTO
  */
  private String noTipoEvento;

  /*
  *CO_EVENTO
  */
  private String coEvento;

  /*
  *DE_EVENTO
  */
  private String deEvento;

  /*
  *FE_PRESENTACION
  */
  private Date fePresentacion;

  /*
  *FE_PRESENTACION
  */
  private String fePresentacionDesc;

  /*
  *NO_ESPECIALIDAD
  */
  private String noEspecialidad;

  /*
  *CA_ACCIDENTADOS
  */
  private Long caAccidentados;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}