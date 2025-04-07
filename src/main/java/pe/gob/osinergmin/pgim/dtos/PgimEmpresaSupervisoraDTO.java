package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
* DTO para la entidad PGIM_TM_EMPRESA_SUPERVISORA: 
* @descripción: Empresa supervisora
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 02/11/2022
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimEmpresaSupervisoraDTO {

  /*
  *Identificador interno de la empresa supervisora. Secuencia: PGIM_SEQ_EMPRESA_SUPERVISORA
  */
  private Long idEmpresaSupervisora;

  /*
  *Identificador interno de la persona. Tabla padre: PGIM_TM_PERSONA
  */
  private Long idPersona;

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
  *Principal actividad económica
  */
  private String dePrincipalActividadEcnmca;

  /*
  *Número de documento de identidad de la empresa supervisora
  */
  private String descCoDocumentoIdentidad;

  /*
  *Razón social de la empresa supervisora
  */
  private String descNoRazonSocial;

  /*
  *Teléfono de la empresa supervisora
  */
  private String descDeTelefono;

  /*
  *Teléfono 02 de la empresa supervisora
  */
  private String descDeTelefono2;

  /*
  *Correo electrónico de la empresa supervisora
  */
  private String descDeCorreo;

  /*
  *Correo electrónico 02 de la empresa supervisora
  */
  private String descDeCorreo2;

  /*
  *Número de contratos vigentes
  */
  private Long descNuContratoVigente;

  /*
  * Indicador si se requiere exportar o no a MS-Excel
  */
  private Integer exportaExcel;

  /*
  * Texto utilizado para una búsqueda genérica.
  */
  private String textoBusqueda;

  /**
   * Persona juridica si es consorcio o no
   */
  private String descFlConsorcio;
}