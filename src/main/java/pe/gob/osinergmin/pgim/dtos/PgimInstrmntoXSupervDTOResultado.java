package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

public class PgimInstrmntoXSupervDTOResultado extends PgimInstrmntoXSupervDTO{
  
  /**
   * @see pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository#listarAll()
   * @see pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository#obtenerInstrmntoXSupervDTOPorId()
   * 
   * @param idInstrmntoXSuperv
   * @param idTipoInstrumento
   * @param idSupervision
   * @param idEstadoInstrumento
   * @param coInstrumento
   * @param coSerieInstrumento
   * @param noMarcaInstrumento
   * @param noModeloInstrumento
   * @param feCalibracion
   * @param feVencimientoCalibracion
   * @param descNoTipoInstrumento
   * @param descNoEstadoInstrumento
   */
  public PgimInstrmntoXSupervDTOResultado(Long idInstrmntoXSuperv, Long idTipoInstrumento, Long idSupervision, 
    Long idEstadoInstrumento, String coInstrumento, String coSerieInstrumento, String noMarcaInstrumento, 
    String noModeloInstrumento, Date feCalibracion, Date feVencimientoCalibracion, String descNoTipoInstrumento,
    String descNoEstadoInstrumento, String descCoEstadoInstrumento, String coCertificadoCalibracion, String noLaboratorioCalibracion){
    super();

    this.setIdInstrmntoXSuperv(idInstrmntoXSuperv);
    this.setIdTipoInstrumento(idTipoInstrumento);
    this.setIdSupervision(idSupervision);
    this.setIdEstadoInstrumento(idEstadoInstrumento);
    this.setCoInstrumento(coInstrumento);
    this.setCoSerieInstrumento(coSerieInstrumento);
    this.setNoMarcaInstrumento(noMarcaInstrumento);
    this.setNoModeloInstrumento(noModeloInstrumento);
    this.setFeCalibracion(feCalibracion);
    this.setFeVencimientoCalibracion(feVencimientoCalibracion);
    this.setDescNoTipoInstrumento(descNoTipoInstrumento);
    this.setDescNoEstadoInstrumento(descNoEstadoInstrumento);
    this.setDescCoEstadoInstrumento(descCoEstadoInstrumento);
    this.setCoCertificadoCalibracion(coCertificadoCalibracion);
    this.setNoLaboratorioCalibracion(noLaboratorioCalibracion);
    
  }

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.SupervInstrumentoRepository#listar()
   * @param idInstrmntoXSuperv
   * @param idTipoInstrumento
   * @param idSupervision
   * @param idEstadoInstrumento
   * @param coInstrumento
   * @param coSerieInstrumento
   * @param noMarcaInstrumento
   * @param noModeloInstrumento
   * @param feCalibracion
   * @param feVencimientoCalibracion
   * @param descNoTipoInstrumento
   * @param descNoEstadoInstrumento
   * @param descCoEstadoInstrumento
   * @param coCertificadoCalibracion
   * @param noLaboratorioCalibracion
   * @param descNoPasoProceso
   */
  public PgimInstrmntoXSupervDTOResultado(Long idInstrmntoXSuperv, Long idTipoInstrumento, Long idSupervision, 
    Long idEstadoInstrumento, String coInstrumento, String coSerieInstrumento, String noMarcaInstrumento, 
    String noModeloInstrumento, Date feCalibracion, Date feVencimientoCalibracion, String descNoTipoInstrumento,
    String descNoEstadoInstrumento, String descCoEstadoInstrumento, String coCertificadoCalibracion, String noLaboratorioCalibracion, 
    String descNoPasoProceso, Long idInstrmntoXSupervRmplzo, String descNoInstrumentoReemplazado, String descNoInstrmntoXSupervRmplzdo, Long descIdInstrmntoXSupervRmplzdo ){
    super();

    this.setIdInstrmntoXSuperv(idInstrmntoXSuperv);
    this.setIdTipoInstrumento(idTipoInstrumento);
    this.setIdSupervision(idSupervision);
    this.setIdEstadoInstrumento(idEstadoInstrumento);
    this.setCoInstrumento(coInstrumento);
    this.setCoSerieInstrumento(coSerieInstrumento);
    this.setNoMarcaInstrumento(noMarcaInstrumento);
    this.setNoModeloInstrumento(noModeloInstrumento);
    this.setFeCalibracion(feCalibracion);
    this.setFeVencimientoCalibracion(feVencimientoCalibracion);
    this.setDescNoTipoInstrumento(descNoTipoInstrumento);
    this.setDescNoEstadoInstrumento(descNoEstadoInstrumento);
    this.setDescCoEstadoInstrumento(descCoEstadoInstrumento);
    this.setCoCertificadoCalibracion(coCertificadoCalibracion);
    this.setNoLaboratorioCalibracion(noLaboratorioCalibracion);
    this.setDescNoPasoProceso(descNoPasoProceso);
    this.setDescNoInstrmntoXSupervRmplzo(descNoInstrumentoReemplazado);
    this.setIdInstrmntoXSupervRmplzo(idInstrmntoXSupervRmplzo);
    this.setDescNoInstrmntoXSupervRmplzdo(descNoInstrmntoXSupervRmplzdo);
    this.setDescIdInstrmntoXSupervRmplzdo(descIdInstrmntoXSupervRmplzdo);

    
  }

}
