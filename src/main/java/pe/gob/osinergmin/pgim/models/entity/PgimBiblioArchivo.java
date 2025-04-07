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
* Clase Entidad para la tabla PGIM_TD_BIBLIO_ARCHIVO: 
* @descripción: Archivo bibliográfico
*
* @author: hdiaz
* @version 1.0
* @fecha_de_creación: 07/12/2023
*/
@Entity
@Table(name = "PGIM_TD_BIBLIO_ARCHIVO")
@Data
@NoArgsConstructor
public class PgimBiblioArchivo implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Identificador interno del archivo bibliográfico. Secuencia: PGIM_SEQ_BIBLIO_ARCHIVO
  */
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_BIBLIO_ARCHIVO")
   @SequenceGenerator(name = "PGIM_SEQ_BIBLIO_ARCHIVO", sequenceName = "PGIM_SEQ_BIBLIO_ARCHIVO", allocationSize = 1)
   @Column(name = "ID_BIBLIO_ARCHIVO", nullable = false)
  private Long idBiblioArchivo;

  /*
  *Identificador interno del documento bibliográfico. Tabla padre: PGIM_TD_BIBLIO_DOCUMENTO
  */
   @JsonIgnore
   @ManyToOne(fetch=FetchType.LAZY)
   @JoinColumn(name = "ID_BIBLIO_DOCUMENTO", nullable = false)
  private PgimBiblioDocumento pgimBiblioDocumento;

  /*
  *Número del archivo Alfresco
  */
   @Column(name = "NU_ARCHIVO_ECM", nullable = false)
  private String nuArchivoEcm;

  /*
  *Número de la carpeta Alfresco que contiene al archivo en Alfresco
  */
   @Column(name = "NU_CARPETA_ECM", nullable = false)
  private String nuCarpetaEcm;

  /*
  *Nombre del archivo bibliográfico retornado luego del procesamiento por parte de la ApiAlfresco
  */
   @Column(name = "NO_BIBILIO_ARCHIVO", nullable = false)
  private String noBibilioArchivo;

  /*
  *Valor que permite describir la naturaleza y el formato del archivo. Multipurpose Internet Mail Extensions = Extensiones multipropósito de correo de internet
  */
   @Column(name = "NO_TIPO_MIME", nullable = false)
  private String noTipoMime;

  /*
  *Descripción del motivo de la eliminación del archivo bibliográfico. Solo tiene valor cuando el valor de la columna ES_REGISTRO = ''0'', caso contrario debe tener el valor NULL
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