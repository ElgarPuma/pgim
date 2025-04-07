package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

public class PgimMedicionDTOResultado extends PgimMedicionDTO {

  /**
   * @see pe.gob.osinergmin.pgim.models.repository.MedicionRepository#obtenerMedicionPorId()
   * @see pe.gob.osinergmin.pgim.models.repository.MedicionRepository#listarMediciones()
   * 
   */
  public PgimMedicionDTOResultado(Long idMedicion, Long idIndicador, Long idEspecialidad, 
    Long idDivisionSupervisora, String coMedicion, String noMedicion, 
    String deMedicion, String esPublicacion, Date feInicial, Date feFinal, 
    Long descIdProceso, String descNoProceso, Long descIdTipoUnidadMedida, String descNoTipoUnidadMedida, 
    String descNoRelacionPasoOrigen, String descNoRelacionPasoDestino, Long descIdTipoAgrupadoPor, String descNoTipoAgrupadoPor,
    Long descIdTipoActorNegocio, String descNoTipoActorNegocio, Long descIdTipoCaracterFisc, 
    String descNoTipoCaracterFisc, String descCoClaveTextoTipoAgrupadoPor, 
    String usCreacion, String descCoIndicador, String descNoIndicador, 
    String descNoEspecialidad, String descNoDivisionSupervisora) {

      super();
      setIdMedicion(idMedicion);
      setIdIndicador(idIndicador);
      setIdEspecialidad(idEspecialidad);
      setIdDivisionSupervisora(idDivisionSupervisora);
      setCoMedicion(coMedicion);
      setNoMedicion(noMedicion);
      setDeMedicion(deMedicion);
      setEsPublicacion(esPublicacion);
      setFeInicial(feInicial);
      setFeFinal(feFinal);
      setUsCreacion(usCreacion);
      
      setDescIdProceso(descIdProceso);
      setDescNoProceso(descNoProceso);
      setDescIdTipoUnidadMedida(descIdTipoUnidadMedida);
      setDescNoTipoUnidadMedida(descNoTipoUnidadMedida);
      setDescNoRelacionPasoOrigen(descNoRelacionPasoOrigen);
      setDescNoRelacionPasoDestino(descNoRelacionPasoDestino);
      setDescIdTipoAgrupadoPor(descIdTipoAgrupadoPor);
      setDescNoTipoAgrupadoPor(descNoTipoAgrupadoPor);
      setDescIdTipoActorNegocio(descIdTipoActorNegocio);
      setDescNoTipoActorNegocio(descNoTipoActorNegocio);
      setDescIdTipoCaracterFisc(descIdTipoCaracterFisc);
      setDescNoTipoCaracterFisc(descNoTipoCaracterFisc);
      setDescCoClaveTextoTipoAgrupadoPor(descCoClaveTextoTipoAgrupadoPor);
      setDescCoIndicador(descCoIndicador);
      setDescNoIndicador(descNoIndicador);

      this.setDescNoEsPublicacion("Publicada");
      if(esPublicacion.equals(ConstantesUtil.IND_INACTIVO)) {
        this.setDescNoEsPublicacion("No publicada");
      }

      setDescNoEspecialidad(descNoEspecialidad);
      setDescNoDivisionSupervisora(descNoDivisionSupervisora);
  }

}
