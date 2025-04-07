package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_UNIDAD_ORGANICA: 
* @descripción: Unidad orgánica del Osinergmin
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 20/01/2023
*/
@Entity
@Table(name = "PGIM_TM_UNIDAD_ORGANICA")
@Data
@NoArgsConstructor
public class PgimUnidadOrganica implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la unidad orgánica del Osinergmin. Secuencia: PGIM_SEQ_UNIDAD_ORGANICA
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_UNIDAD_ORGANICA")
   @SequenceGenerator(name = "PGIM_SEQ_UNIDAD_ORGANICA", sequenceName = "PGIM_SEQ_UNIDAD_ORGANICA", allocationSize = 1)
   @Column(name = "ID_UNIDAD_ORGANICA", nullable = false)
  private Long idUnidadOrganica;

  /*
  *Código del ubigeo
  */
   @Column(name = "CO_UNIDAD_ORGANICA", nullable = false)
  private String coUnidadOrganica;

  /*
  *Nombre del ubigeo
  */
   @Column(name = "NO_UNIDAD_ORGANICA", nullable = false)
  private String noUnidadOrganica;

  /*
  *Código iIdentificador del usuario Siged para la numeración de documentos (usuario ficticio)
  */
   @Column(name = "CO_USUARIO_SIGED_NUMERADOR", nullable = true)
  private Long coUsuarioSigedNumerador;

  /*
  *Nombre del usuarioSiged para la numeración de documentos (usuario ficticio)
  */
   @Column(name = "NO_USUARIO_SIGED_NUMERADOR", nullable = true)
  private String noUsuarioSigedNumerador;

  /*
  *Estado del registro. Los posibles valores son: "1" = Activo y "0" = Inactivo
  */
   @Column(name = "ES_REGISTRO", nullable = false)
  private String esRegistro;

  /*
  *Usuario creador
  */
   @Column(name = "US_CREACION", nullable = false)
  private String usCreacion;

  /*
  *Terminal de creación
  */
   @Column(name = "IP_CREACION", nullable = false)
  private String ipCreacion;

  /*
  *Fecha y hora de creación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_CREACION", nullable = false)
  private Date feCreacion;

  /*
  *Usuario modificador
  */
   @Column(name = "US_ACTUALIZACION", nullable = true)
  private String usActualizacion;

  /*
  *Terminal de modificación
  */
   @Column(name = "IP_ACTUALIZACION", nullable = true)
  private String ipActualizacion;

  /*
  *Fecha y hora de modificación
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_ACTUALIZACION", nullable = true)
  private Date feActualizacion;

  /*
  *Distrito
  */
 @Transient
  private String descDistrito;

  /*
  *Provincia
  */
 @Transient
  private String descProvincia;

  /*
  *Departamento
  */
 @Transient
  private String descDepartamento;


}