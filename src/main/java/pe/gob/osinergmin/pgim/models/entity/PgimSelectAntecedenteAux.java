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
* Clase Entidad para la tabla PGIM_VW_SELECT_ANTECEDENTE_AUX: 
* @descripción: Vista para seleccionar antecedentes ya registradas en las tablas maestras
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 02/11/2022
*/
@Entity
@Table(name = "PGIM_VW_SELECT_ANTECEDENTE_AUX")
@Data
@NoArgsConstructor
public class PgimSelectAntecedenteAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno de la vista PGIM_VW_SELECT_ANTECEDENTE_AUX Secuencia: PGIM_SEQ_SELECT_ANTECEDENTE_AUX
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_SELECT_ANTECEDENTE_AUX")
   @SequenceGenerator(name = "PGIM_SEQ_SELECT_ANTECEDENTE_AUX", sequenceName = "PGIM_SEQ_SELECT_ANTECEDENTE_AUX", allocationSize = 1)
   @Column(name = "ID_DOCUMENTO_AUX", nullable = false)
  private Long idDocumentoAux;

  /*
  *ID_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_DOCUMENTO", nullable = true)
  private PgimDocumento pgimDocumento;

  /*
  *CO_TABLA_INSTANCIA
  */
   @Column(name = "CO_TABLA_INSTANCIA", nullable = true)
  private Long coTablaInstancia;

  /*
  *FECHA
  */
   @Column(name = "FECHA", nullable = true)
  private String fecha;

  /*
  *CODIGO
  */
   @Column(name = "CODIGO", nullable = true)
  private String codigo;

  /*
  *NU_EXPEDIENTE_SIGED
  */
   @Column(name = "NU_EXPEDIENTE_SIGED", nullable = true)
  private String nuExpedienteSiged;

  /*
  *ID_INSTANCIA_PROCESO
  */
   @Column(name = "ID_INSTANCIA_PROCESO", nullable = true)
  private Long idInstanciaProceso;

  /*
  *DESCRIPCION
  */
   @Column(name = "DESCRIPCION", nullable = true)
  private String descripcion;

  /*
  *NO_TIPO_ANTECEDENTE
  */
   @Column(name = "NO_TIPO_ANTECEDENTE", nullable = true)
  private String noTipoAntecedente;

  /*
  *Tipo de antecedente. Los valores admisibles se encuentran en la tabla <<PGIM_TP_VALOR_PARAMETRO>> cuando el parámetro relacionado cumple con la condición <<PGIM_TP_PARAMETRO.CO_PARAMETRO>> = TIPO_ANTECEDENTE. Finalmente, el valor persistido en esta columna es el valor obtenido de <<PGIM_TP_VALOR_PARAMETRO.ID_VALOR_PARAMETRO>> Tabla padre: PGIM_TP_VALOR_PARAMETRO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_TIPO_ANTECEDENTE", nullable = true)
  private PgimValorParametro tipoAntecedente;

  /*
  *ID_UNIDAD_MINERA
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_UNIDAD_MINERA", nullable = true)
  private PgimUnidadMinera pgimUnidadMinera;

  /*
  *ID_ESPECIALIDAD
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_ESPECIALIDAD", nullable = true)
  private PgimEspecialidad pgimEspecialidad;


}