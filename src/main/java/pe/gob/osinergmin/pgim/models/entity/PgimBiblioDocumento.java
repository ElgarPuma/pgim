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

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_TD_BIBLIO_DOCUMENTO: 
* @descripción: Documento bibliográfico
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 07/12/2023
*/
@Entity
@Table(name = "PGIM_TD_BIBLIO_DOCUMENTO")
@Data
@NoArgsConstructor
public class PgimBiblioDocumento implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del documento bibliográfico. Secuencia: PGIM_SEQ_BIBLIO_DOCUMENTO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_BIBLIO_DOCUMENTO")
   @SequenceGenerator(name = "PGIM_SEQ_BIBLIO_DOCUMENTO", sequenceName = "PGIM_SEQ_BIBLIO_DOCUMENTO", allocationSize = 1)
   @Column(name = "ID_BIBLIO_DOCUMENTO", nullable = false)
  private Long idBiblioDocumento;

  /*
  *Identificador interno de la subcategoría de documento. Tabla padre: PGIM_TM_SUBCATEGORIA_DOC
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_SUBCAT_DOCUMENTO", nullable = false)
  private PgimSubcategoriaDoc pgimSubcategoriaDoc;

  /*
  *Tipo del medio de ingreso del documento bibliográfico. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_MEDIO_INGRESO. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_MEDIO_INGRESO", nullable = false)
  private PgimValorParametro tipoMedioIngreso;

  /*
  *Identificador interno de la persona emisora del documento bibliográfico. Tabla padre: PGIM_TM_PERSONA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_PERSONA_EMISORA", nullable = false)
  private PgimPersona personaEmisora;

  /*
  *Número del documento bibliográfico
  */
   @Column(name = "NU_DOCUMENTO", nullable = true)
  private String nuDocumento;

  /*
  *Asunto del documento bibliográfico
  */
   @Column(name = "DE_ASUNTO_DOCUMENTO", nullable = false)
  private String deAsuntoDocumento;

  /*
  *Sumilla del documento bibliográfico
  */
   @Column(name = "DE_SUMILLA_DOCUMENTO", nullable = false)
  private String deSumillaDocumento;

  /*
  *Fecha de emisión del documento bibliográfico
  */
   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "FE_EMISION_DOCUMENTO", nullable = false)
  private Date feEmisionDocumento;

  /*
  *Número del expediente Siged. Solo debe tener valor cuando el valor de la columna ID_TIPO_MEDIO_INGRESO está referido al medio de ingreso Siged, caso contrario deberá tener el valor NULL
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *Descripción del motivo de la eliminación del documento bibliográfico. Solo tiene valor cuando el valor de la columna ES_REGISTRO = ''0'', caso contrario debe tener el valor NULL
  */
   @Column(name = "DE_MOTIVO_ELIMINACION", nullable = true)
  private String deMotivoEliminacion;

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