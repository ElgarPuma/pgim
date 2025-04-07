package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_EMPRESA_SUPERVISOR_AUX: 
* @descripción: Vista para obtener la lista de empresas supervisoras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_EMPRESA_SUPERVISOR_AUX")
@Data
@NoArgsConstructor
public class PgimEmpresaSupervisorAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Secuencia: PGIM_SEQ_ID_EMP_SUPERV_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ID_EMP_SUPERV_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_ID_EMP_SUPERV_AUX", sequenceName = "PGIM_SEQ_ID_EMP_SUPERV_AUX", allocationSize = 1)
   @Column(name = "ID_EMPRESA_SUPERVISORA_AUX", nullable = false)
  private Long idEmpresaSupervisoraAux;

  /*
  *ID_EMPRESA_SUPERVISORA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_EMPRESA_SUPERVISORA", nullable = true)
  private PgimEmpresaSupervisora pgimEmpresaSupervisora;

  /*
  *ID_PERSONA
  */
   @Column(name = "ID_PERSONA", nullable = true)
  private Long idPersona;

  /*
  *CO_DOCUMENTO_IDENTIDAD
  */
   @Column(name = "CO_DOCUMENTO_IDENTIDAD", nullable = true)
  private String coDocumentoIdentidad;

  /*
  *NO_RAZON_SOCIAL
  */
   @Column(name = "NO_RAZON_SOCIAL", nullable = true)
  private String noRazonSocial;

  /*
  *DE_TELEFONO
  */
   @Column(name = "DE_TELEFONO", nullable = true)
  private String deTelefono;

  /*
  *DE_TELEFONO2
  */
   @Column(name = "DE_TELEFONO2", nullable = true)
  private String deTelefono2;

  /*
  *DE_CORREO
  */
   @Column(name = "DE_CORREO", nullable = true)
  private String deCorreo;

  /*
  *DE_CORREO2
  */
   @Column(name = "DE_CORREO2", nullable = true)
  private String deCorreo2;

  /*
  *NU_CONTRATO_VIGENTE
  */
   @Column(name = "NU_CONTRATO_VIGENTE", nullable = true)
  private Long nuContratoVigente;


}