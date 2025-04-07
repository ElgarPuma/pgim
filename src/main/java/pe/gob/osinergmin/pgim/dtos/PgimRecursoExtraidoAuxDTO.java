package pe.gob.osinergmin.pgim.dtos;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* Clase Entidad para la tabla MV_OSIN_RECURSO_EXTRAIDO: 
* @descripción: Vista para reportes Estamin
*
* @author: LEGION
* @version 1.0
* @fecha_de_creación: 20/12/2022
*/

@Getter
@Setter
@NoArgsConstructor
public class PgimRecursoExtraidoAuxDTO {
  /*
  *Año
  */
  private String anioPro;

  /*
  *Mes
  */
  private String mes;

  /*
  *Identificador interno del cliente
  */
  private Long idCliente;

  /*
  *Documento RUC del cliente
  */
  private String rucCliente;

  /*
  *Nombre del titular minero
  */
  private String titularMinero;

  /*
  *Estrato del titular minero
  */
  private String estrato;
  
   /*
  *Código unidad minera
  */
  private String codigo;

  /*
  *Nombre de la unidad minera 
  */
  private String noUnidadMinera;

  /*
  *Código integrante UEA
  */
  private String codIntegranteUEA;

  /*
  *Nombre de la unidad minera integrante
  */
  private String noUnidadIntegrante;

  /*
  *Nombre de la región
  */
  private String noRegion;

  /*
  *Nombre de la provincia
  */
  private String noProvincia;

  /*
  *Nombre de la distrito
  */
  private String noDistrito;

  /*
  *Nombre del recurso extraido
  */
  private String noRecursoExtraido;

  /*
  *Procedencia del recurso extraido
  */
  private String procedencia;

  /*
  *Nombre del vendedor
  */
  private String noVendedor;

  /*
  *Identificador de unidad de medida
  */
  private Long idUnidadMedida;

  /*
  *Descripción
  */
  private String descripcion;

  /*
  *Cantidad de recurso extraido
  */
  private Long cantidad;

  /*
  *PCT_CU
  */
  private Double pct_CU;

  /*
  *PCT_PB
  */
  private Double pct_PB;

  /*
  *PCT_ZN
  */
  private Double pct_ZN;

  /*
  *AG_OZ_TC
  */
  private Double ag_OZ_TC;

  /*
  *AU_GR_TM
  */
  private Double au_GR_TM;

  /*
  *PCT_FE
  */
  private Double pct_FE;

  /*
  *PCT_MO
  */
  private Double pct_MO;

  /*
  *PCT_SN
  */
  private Double pct_SN;

   /**
   * PCT_CD
   */
  private Double pct_CD;	
  
  /**
   * PCT_WO3
   */
  private Double pct_WO3;	
  
  /**
   * PCT_SB
   */
  private Double pct_SB;	
  
  /**
   * PCT_AS
   */
  private Double pct_AS;	
  
  /**
   * PCT_MN
   */
  private Double pct_MN;	
  
  /**
   * PCT_BI
   */
  private Double pct_BI;	
  
  /**
   * PCT_HG
   */
  private Double pct_HG;	
  
  /**
   * PCT_IN
   */
  private Double pct_IN;	
  
  /**
   * PCT_SE
   */
  private Double pct_SE;	
  
  /**
   * PCT_TE
   */
  private Double pct_TE;	
  
  /**
   * H2SO4
   */
  private Double h2SO4;	
  
  /**
   * PCT_U
   */
  private Double pct_U;	
  
  /**
   * PCT_NI
   */
  private Double pct_NI;	
  
  /**
   * PCT_MG
   */
  private Double pct_MG; 

  /**
   * Fecha inicial filtro
   */
  private Date descFeInicial;

  /**
   * Fecha final filtro
   */
  private Date descFeFinal;

  /**
   * Nombre de unidad fiscalizable
   */
  private String descNoUnidadMinera;
}
