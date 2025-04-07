package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TM_PROCESO: 
* @descripción: Proceso
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_TM_PROCESO")
@Data
@NoArgsConstructor
public class PgimProceso implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del proceso PGIM. Secuencia: PGIM_SEQ_PROCESO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_PROCESO")
   @SequenceGenerator(name = "PGIM_SEQ_PROCESO", sequenceName = "PGIM_SEQ_PROCESO", allocationSize = 1)
   @Column(name = "ID_PROCESO", nullable = false)
  private Long idProceso;

  /*
  *Nombre del proceso PGIM
  */
   @Column(name = "NO_PROCESO", nullable = false)
  private String noProceso;

  /*
  *Descripción del proceso PGIM
  */
   @Column(name = "DE_PROCESO", nullable = true)
  private String deProceso;

  /*
  *Identificador interno del proceso Siged. Este valor se utiliza para crear un nuevo expediente en el Siged a partir de las acciones de adjuntar o incluir lanzadas desde la PGIM. Si es que tiene no tiene valor entonces no podrá crearse un nuevo expediente desde la PGIM
  */
   @Column(name = "CO_PROCESO_SIGED", nullable = true)
  private Long coProcesoSiged;

  /*
  *Estado que permite conocer si el proceso es o no seleccionable para la generación de indicadores. Los posibles valores son: "1" = Sí y "0" = No
  */
  @Column(name = "FL_SELECCIONABLE_INDICADOR", nullable = false)
  private String flIndicador;

  /*
  *Identificador interno del subsector. Tabla padre: PGIM_TM_SUBSECTOR
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBSECTOR", nullable = true)
  private PgimSubsector pgimSubsector;

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


}