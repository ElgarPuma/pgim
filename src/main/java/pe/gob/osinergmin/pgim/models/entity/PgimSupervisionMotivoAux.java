package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_SUPERVISION_MOTIVO_AUX: 
* @descripción: Vista de motivos para una fiscalización no programada
*
* @author: promero
* @version 1.0
* @fecha_de_creación: 24/02/2023
*/
@Entity
@Table(name = "PGIM_VW_SUPERVISION_MOTIVO_AUX")
@Data
@NoArgsConstructor
public class PgimSupervisionMotivoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SUPERVISION_MOTIVO_AUX. Secuencia: PGIM_SEQ_SUPERVISION_MOTIVO_AUX
  */
   @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SUPERVISION_MOTIVO_AUX")
  //  @SequenceGenerator(name = "PGIM_SEQ_SUPERVISION_MOTIVO_AUX", sequenceName = "PGIM_SEQ_SUPERVISION_MOTIVO_AUX", allocationSize = 1)
   @Column(name = "ID_MOTIVO_AUX", nullable = false)
  private Long idMotivoAux;

  /*
  *ID_MOTIVO
  */
   @Column(name = "ID_MOTIVO", nullable = true)
  private Long idMotivo;

  /*
  *ID_OBJETO_MOTIVO_INICIO
  */
   @Column(name = "ID_OBJETO_MOTIVO_INICIO", nullable = true)
  private Long idObjetoMotivoInicio;

  /*
  *ID_TIPO_MOTIVO_INICIO
  */
   @Column(name = "ID_TIPO_MOTIVO_INICIO", nullable = true)
  private Long idTipoMotivoInicio;

  /*
  *ID_SUPERVISION
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *ID_SUPERVISION_MOTIVO
  */
   @Column(name = "ID_SUPERVISION_MOTIVO", nullable = true)
  private Long idSupervisionMotivo;

  /*
  *DE_MOTIVO
  */
   @Column(name = "DE_MOTIVO", nullable = true)
  private String deMotivo;

  /*
  *FE_MOTIVO
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MOTIVO", nullable = true)
  private Date feMotivo;

  /*
  *CO_MOTIVO
  */
   @Column(name = "CO_MOTIVO", nullable = true)
  private String coMotivo;

  /*
  *DE_SUBTIPO_MOTIVO
  */
   @Column(name = "DE_SUBTIPO_MOTIVO", nullable = true)
  private String deSubtipoMotivo;

  /*
  *ID_SUBTIPO_MOTIVO
  */
   @Column(name = "ID_SUBTIPO_MOTIVO", nullable = true)
  private Long idSubtipoMotivo;

  /*
  *ID_INSTANCIA_PROCESO_MOTIVO
  */
   @Column(name = "ID_INSTANCIA_PROCESO_MOTIVO", nullable = true)
  private Long idInstanciaProcesoMotivo;

  /*
  *DE_TIPO_MOTIVO
  */
   @Column(name = "DE_TIPO_MOTIVO", nullable = true)
  private String deTipoMotivo;

  /*
  *ID_TIPO_MOTIVO
  */
   @Column(name = "ID_TIPO_MOTIVO", nullable = true)
  private Long idTipoMotivo;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;


}