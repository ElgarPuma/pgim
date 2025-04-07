package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
* DTO para la entidad PGIM_VW_EMPRESA_SUPERVISOR_AUX: 
* @descripción: Vista para obtener la lista de empresas supervisoras
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEmpresaSupervisorAuxDTO {

  /*
  *Secuencia: PGIM_SEQ_ID_EMP_SUPERV_AUX
  */
  private Long idEmpresaSupervisoraAux;

  /*
  *ID_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

  /*
  *ID_PERSONA
  */
  private Long idPersona;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
  private String noRazonSocial;

  /*
  *DE_TELEFONO
  */
  private String deTelefono;

  /*
  *DE_TELEFONO2
  */
  private String deTelefono2;

  /*
  *DE_CORREO
  */
  private String deCorreo;

  /*
  *DE_CORREO2
  */
  private String deCorreo2;

  /*
  *NU_CONTRATO_VIGENTE
  */
  private Long nuContratoVigente;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;


}