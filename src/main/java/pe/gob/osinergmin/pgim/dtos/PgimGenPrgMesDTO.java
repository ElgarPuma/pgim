package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

import java.math.BigDecimal;

/**
* DTO para la entidad PGIM_TD_GEN_PRG_MES: 
* @descripción: Generación de programa por unidad fiscalizable y mes
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 27/12/2023
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimGenPrgMesDTO {

  /*
  *Identificador interno de la generación de la propuesta de ítem de programa (fiscalización) por mes. Secuencia: PGIM_SEQ_GEN_PRG_MES
  */
  private Long idGenPrgMes;

  /*
  *Identificador interno de la generación del programa por ranking de riesgo. Tabla padre: PGIM_TD_GEN_PRG_UFISCALIZA
  */
  private Long idGenPrgUfiscaliza;

  /*
  *Estado de generación conforme. Los posibles valores son: "1" = Conforme y "0" = No conforme
  */
  private String flConforme;

  /*
  *Número del mes: Enero = 1, Febrero = 2, Marzo = 3, …, Octubre = 10, Noviembre = 11 y Diciembre = 12
  */
  private BigDecimal nuMes;

  /*
  *Mensaje de la generación de la propuesta del ítem del programa por unidad fiscalizable. Solo tiene valor cuando no se ha podido generar
  */
  private String deMensaje;

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
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}