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

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_MEDIDA_ADM_AUX: 
* @descripción: Vista para la lista de medidas administrativas y filtros avanzados
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_MEDIDA_ADM_AUX")
@Data
@NoArgsConstructor
public class PgimMedidaAdmAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la infracción. Secuencia: PGIM_SEQ_MEDIDA_ADM_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_MEDIDA_ADM_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_MEDIDA_ADM_AUX", sequenceName = "PGIM_SEQ_MEDIDA_ADM_AUX", allocationSize = 1)
   @Column(name = "ID_MEDIDA_ADMINISTRATIVA_AUX", nullable = false)
  private Long idMedidaAdministrativaAux;

  /*
  *ID_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "ID_MEDIDA_ADMINISTRATIVA", nullable = true)
  private Long idMedidaAdministrativa;

  /*
  *ID_INSTANCIA_PASO
  */
   @Column(name = "ID_INSTANCIA_PASO", nullable = true)
  private Long idInstanciaPaso;

  /*
  *ID_TIPO_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "ID_TIPO_MEDIDA_ADMINISTRATIVA", nullable = true)
  private Long idTipoMedidaAdministrativa;

  /*
  *ID_INSTANCIA_PROCESO
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *ID_TIPO_OBJETO
  */
   @Column(name = "ID_TIPO_OBJETO", nullable = true)
  private Long idTipoObjeto;

  /*
  *ID_RELACION_PASO
  */
   @Column(name = "ID_RELACION_PASO", nullable = true)
  private Long idRelacionPaso;

  /*
  *ID_TIPO_RELACION
  */
   @Column(name = "ID_TIPO_RELACION", nullable = true)
  private Long idTipoRelacion;

  /*
  *CO_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "CO_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String coMedidaAdministrativa;

  /*
  *ID_SUPERVISION
  */
   @Column(name = "ID_SUPERVISION", nullable = true)
  private Long idSupervision;

  /*
  *CO_SUPERVISION
  */
   @Column(name = "CO_SUPERVISION", nullable = true)
  private String coSupervision;

  /*
  *NO_UNIDAD_MINERA_SUP
  */
   @Column(name = "NO_UNIDAD_MINERA_SUP", nullable = true)
  private String noUnidadMineraSup;

  /*
  *ID_PAS
  */
   @Column(name = "ID_PAS", nullable = true)
  private Long idPas;

  /*
  *CO_SUPERVISION_PAS
  */
   @Column(name = "CO_SUPERVISION_PAS", nullable = true)
  private String coSupervisionPas;

  /*
  *NO_UNIDAD_MINERA_PAS
  */
   @Column(name = "NO_UNIDAD_MINERA_PAS", nullable = true)
  private String noUnidadMineraPas;

  /*
  *ID_UNIDAD_MINERA
  */
   @Column(name = "ID_UNIDAD_MINERA", nullable = true)
  private Long idUnidadMinera;

  /*
  *CO_UNIDAD_MINERA
  */
   @Column(name = "CO_UNIDAD_MINERA", nullable = true)
  private String coUnidadMinera;

  /*
  *NO_UNIDAD_MINERA
  */
   @Column(name = "NO_UNIDAD_MINERA", nullable = true)
  private String noUnidadMinera;

  /*
  *TIPO_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "TIPO_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String tipoMedidaAdministrativa;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *REFERIDA_A
  */
   @Column(name = "REFERIDA_A", nullable = true)
  private String referidaA;

  /*
  *DE_MEDIDA_ADMINISTRATIVA
  */
   @Column(name = "DE_MEDIDA_ADMINISTRATIVA", nullable = true)
  private String deMedidaAdministrativa;

  /*
  *FE_MEDIDA_ADMINISTRATIVA
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_MEDIDA_ADMINISTRATIVA", nullable = true)
  private Date feMedidaAdministrativa;

  /*
  *NO_USUARIO_ASIGNADO
  */
   @Column(name = "NO_USUARIO_ASIGNADO", nullable = true)
  private String noUsuarioAsignado;

  /*
  *NO_PERSONA_ASIGNADA
  */
   @Column(name = "NO_PERSONA_ASIGNADA", nullable = true)
  private String noPersonaAsignada;

  /*
  *FE_INSTANCIA_PASO
  */
   @Column(name = "FE_INSTANCIA_PASO", nullable = true)
  private Date feInstanciaPaso;

  /*
  *DE_MENSAJE
  */
   @Column(name = "DE_MENSAJE", nullable = true)
  private String deMensaje;

  /*
  *FL_PASO_ACTIVO
  */
   @Column(name = "FL_PASO_ACTIVO", nullable = true)
  private String flPasoActivo;


}