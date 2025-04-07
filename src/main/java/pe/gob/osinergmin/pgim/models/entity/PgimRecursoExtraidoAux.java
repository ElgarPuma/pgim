package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla MV_OSIN_RECURSO_EXTRAIDO
* @descripción: Vista para reportes Estamin
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 20/12/2022
*/
@Entity
@Table(name = "MV_OSIN_RECURSO_EXTRAIDO", schema = "ES_PGIM_ESTAMIN")
@Data
@NoArgsConstructor
public class PgimRecursoExtraidoAux implements Serializable{

private static final long serialVersionUID = 1L;

  /*
  *Año pro
  */
  @Id
   @Column(name = "ANOPRO", nullable = true)
  private String anioPro;

  /*
  *Mes
  */
   @Column(name = "MES", nullable = true)
  private String mes;

  /*
  *Identificador interno del cliente
  */
   @Column(name = "ID_CLIENTE", nullable = true)
  private Long idCliente;

  /*
  *Documento RUC del cliente
  */
   @Column(name = "RUC", nullable = true)
  private String rucCliente;

  /*
  *Nombre del titular minero
  */
  @Column(name = "TITULAR_MINERO", nullable = true)
  private String titularMinero;

  /*
  *Estrato del titular minero
  */
  @Column(name = "ESTRATO", nullable = true)
  private String estrato;
  
   /*
  *Código unidad minera
  */
  @Column(name = "CODIGO", nullable = true)
  private String codigo;

  /*
  *Nombre de la unidad minera 
  */
  @Column(name = "UNIDAD", nullable = true)
  private String noUnidadMinera;

  /*
  *Código integrante UEA
  */
  @Column(name = "CODIGO_INTEGRANTE_UEA", nullable = true)
  private String codIntegranteUEA;

  /*
  *Nombre de la unidad minera integrante
  */
  @Column(name = "UNIDAD_INTEGRANTE", nullable = true)
  private String noUnidadIntegrante;

  /*
  *Nombre de la región
  */
  @Column(name = "REGION", nullable = true)
  private String noRegion;

  /*
  *Nombre de la provincia
  */
  @Column(name = "PROVINCIA", nullable = true)
  private String noProvincia;

  /*
  *Nombre de la distrito
  */
  @Column(name = "DISTRITO", nullable = true)
  private String noDistrito;

  /*
  *Nombre del recurso extraido
  */
  @Column(name = "RECURSO_EXTRAIDO", nullable = true)
  private String noRecursoExtraido;

  /*
  *Procedencia del recurso extraido
  */
  @Column(name = "PROCEDENCIA", nullable = true)
  private String procedencia;

  /*
  *Nombre del vendedor
  */
  @Column(name = "NOMBRE_VENDEDOR", nullable = true)
  private String noVendedor;

  /*
  *Identificador de unidad de medida
  */
  @Column(name = "ID_UNIDAD_MEDIDA", nullable = true)
  private Long idUnidadMedida;

  /*
  *Descripción
  */
  @Column(name = "DESCRIPCION", nullable = true)
  private String descripcion;

  /*
  *Cantidad de recurso extraido
  */
  @Column(name = "CANTIDAD", nullable = true)
  private Long cantidad;

  /*
  *PCT_CU
  */
  @Column(name = "PCT_CU", nullable = true)
  private Double pct_CU;

  /*
  *PCT_PB
  */
  @Column(name = "PCT_PB", nullable = true)
  private Double pct_PB;

  /*
  *PCT_ZN
  */
  @Column(name = "PCT_ZN", nullable = true)
  private Double pct_ZN;

  /*
  *AG_OZ_TC
  */
  @Column(name = "AG_OZ_TC", nullable = true)
  private Double ag_OZ_TC;

  /*
  *AU_GR_TM
  */
  @Column(name = "AU_GR_TM", nullable = true)
  private Double au_GR_TM;

  /*
  *PCT_FE
  */
  @Column(name = "PCT_FE", nullable = true)
  private Double pct_FE;

  /*
  *PCT_MO
  */
  @Column(name = "PCT_MO", nullable = true)
  private Double pct_MO;

  /*
  *PCT_SN
  */
  @Column(name = "PCT_SN", nullable = true)
  private Double pct_SN;

   /**
   * PCT_CD
   */
  @Column(name = "PCT_CD", nullable = true)
  private Double pct_CD;	
  
  /**
   * PCT_WO3
   */
  @Column(name = "PCT_WO3", nullable = true)
  private Double pct_WO3;	
  
  /**
   * PCT_SB
   */
  @Column(name = "PCT_SB", nullable = true)
  private Double pct_SB;	
  
  /**
   * PCT_AS
   */
  @Column(name = "PCT_AS", nullable = true)
  private Double pct_AS;	
  
  /**
   * PCT_MN
   */
  @Column(name = "PCT_MN", nullable = true)
  private Double pct_MN;	
  
  /**
   * PCT_BI
   */
  @Column(name = "PCT_BI", nullable = true)
  private Double pct_BI;	
  
  /**
   * PCT_HG
   */
  @Column(name = "PCT_HG", nullable = true)
  private Double pct_HG;	
  
  /**
   * PCT_IN
   */
  @Column(name = "PCT_IN", nullable = true)
  private Double pct_IN;	
  
  /**
   * PCT_SE
   */
  @Column(name = "PCT_SE", nullable = true)
  private Double pct_SE;	
  
  /**
   * PCT_TE
   */
  @Column(name = "PCT_TE", nullable = true)
  private Double pct_TE;	
  
  /**
   * H2SO4
   */
  @Column(name = "H2SO4", nullable = true)
  private Double h2SO4;	
  
  /**
   * PCT_U
   */
  @Column(name = "PCT_U", nullable = true)
  private Double pct_U;	
  
  /**
   * PCT_NI
   */
  @Column(name = "PCT_NI", nullable = true)
  private Double pct_NI;	
  
  /**
   * PCT_MG
   */
  @Column(name = "PCT_MG", nullable = true)
  private Double pct_MG; 

  
}