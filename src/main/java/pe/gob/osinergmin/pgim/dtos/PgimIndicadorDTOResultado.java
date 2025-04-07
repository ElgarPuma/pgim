package pe.gob.osinergmin.pgim.dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

@Slf4j
public class PgimIndicadorDTOResultado extends PgimIndicadorDTO {

	
	/**
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#listarIndicadorBycoClavetexto()
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#obtenerIndicadorById()
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#validarCoClaveUnicaIndicadorFt()
     * 
     */
    public PgimIndicadorDTOResultado(Long idIndicador, String coIndicador, String noIndicador, String deIndicador,
            Long idTipoIndicador, String deTipoIndicador, String deFormula, String deUnidadMedida, Integer caMesesAtras,
            String deUrlRelativo ) {
        this.setIdIndicador(idIndicador);
        this.setCoIndicador(coIndicador);
        this.setNoIndicador(noIndicador);
        this.setDeIndicador(deIndicador);
        this.setIdTipoIndicador(idTipoIndicador);
        this.setDescDeTipoIndicador(deTipoIndicador);
        this.setDeFormula(deFormula);
        this.setDescDeUnidadMedida(deUnidadMedida);
        this.setCaMesesAtras(caMesesAtras);
        this.setDeUrlRelativo(deUrlRelativo);
        
        //
        Date fechaActual = new Date();
        this.setDescFechaHasta(fechaActual);
        
        
        
        try {
        	if(caMesesAtras != null) {
	        	Calendar calendar = Calendar.getInstance();
	            calendar.setTime(fechaActual);	          
	            calendar.add(Calendar.MONTH, -caMesesAtras );
	            Date fechaDesde = calendar.getTime();
	            
	            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	            String descFechaDesde = df.format(fechaDesde);
	           
	            Date fechaDesde2=new SimpleDateFormat("dd/MM/yyyy").parse("01"+descFechaDesde.substring(2, 10)); 
	            this.setDescFechaDesde(fechaDesde2);
        	}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.error("ERROR - No se puede calcular la fecha de desde: "+ e.getMessage(), e);
		}
        
        
    }
    
    /**
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#listarIndicadoresFt()
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#obtenerIndicadorFtById()
     * 
     * @param idIndicador
     * @param coIndicador
     * @param noIndicador
     * @param deIndicador
     * @param idTipoIndicador
     * @param deTipoIndicador
     * @param deFormula
     * @param coClaveIndicador
     * @param esIndicador
     * @param idTipoUnidadMedida
     * @param idProceso
     * @param idTipoAgrupadoPor
     * @param idTipoActorNegocio
     * @param idTipoCaracterFisc
     * @param descCoTipoAgrupadoPor
     * @param descDeUnidadMedida
     */
    public PgimIndicadorDTOResultado(Long idIndicador, String coIndicador, String noIndicador, String deIndicador,
            Long idTipoIndicador, String deTipoIndicador, String deFormula, 
            String coClaveIndicador, String esIndicador, Long idTipoUnidadMedida,
            Long idProceso, Long idTipoAgrupadoPor,
            Long idTipoActorNegocio, Long idTipoCaracterFisc, String descCoTipoAgrupadoPor,
            String descDeUnidadMedida, Long idRelacionPasoDestino, Long idRelacionPasoOrigen ) {
    	
        this.setIdIndicador(idIndicador);
        this.setCoIndicador(coIndicador);
        this.setNoIndicador(noIndicador);
        this.setDeIndicador(deIndicador);
        this.setIdTipoIndicador(idTipoIndicador);
        this.setDescDeTipoIndicador(deTipoIndicador);
        this.setDeFormula(deFormula);
        
        this.setCoClaveIndicador(coClaveIndicador);
        this.setEsIndicador(esIndicador);
        this.setIdTipoUnidadMedida(idTipoUnidadMedida);
        this.setIdProceso(idProceso);
        this.setIdTipoAgrupadoPor(idTipoAgrupadoPor);
        this.setIdTipoActorNegocio(idTipoActorNegocio);
        this.setIdTipoCaracterFisc(idTipoCaracterFisc);
        this.setDescCoTipoAgrupadoPor(descCoTipoAgrupadoPor);
        this.setDescDeUnidadMedida(descDeUnidadMedida);
        
        this.setDescDeEstado("Activo");
        if(esIndicador.equals(ConstantesUtil.IND_INACTIVO_I)) {
        	this.setDescDeEstado("Inactivo");
        }

        this.setIdRelacionPasoDestino(idRelacionPasoDestino);
        this.setIdRelacionPasoOrigen(idRelacionPasoOrigen);      

    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#listarIndicadoresByPalabra()
     * @see pe.gob.osinergmin.pgim.models.repository.IndicadorRepository#listarIndicadoresFt()
     * 
     * @param idIndicador
     * @param coIndicador
     * @param noIndicador
     * @param deIndicador
     * @param idTipoIndicador
     * @param deTipoIndicador
     * @param deFormula
     * @param coClaveIndicador
     * @param esIndicador
     * @param idTipoUnidadMedida
     * @param idProceso
     * @param idTipoAgrupadoPor
     * @param idTipoActorNegocio
     * @param idTipoCaracterFisc
     * @param descCoTipoAgrupadoPor
     * @param descDeUnidadMedida
     * @param descNoProceso
     * @param descNoTipoUnidadMedida
     * @param descNoTipoAgrupadoPor
     * @param descNoTipoActorNegocio
     * @param descNoTipoCaracterFisc
     */
    public PgimIndicadorDTOResultado(Long idIndicador, String coIndicador, String noIndicador, String deIndicador,
            Long idTipoIndicador, String deTipoIndicador, String deFormula, 
            String coClaveIndicador, String esIndicador, Long idTipoUnidadMedida,
            Long idProceso, Long idTipoAgrupadoPor,
            Long idTipoActorNegocio, Long idTipoCaracterFisc, String descCoTipoAgrupadoPor,
            String descDeUnidadMedida, String descNoProceso, String descNoTipoUnidadMedida, 
            String descNoTipoAgrupadoPor, String descNoTipoActorNegocio, String descNoTipoCaracterFisc, 
            String descNoRelacionPasoOrigen, String descNoRelacionPasoDestino) {
    	
        this.setIdIndicador(idIndicador);
        this.setCoIndicador(coIndicador);
        this.setNoIndicador(noIndicador);
        this.setDeIndicador(deIndicador);
        this.setIdTipoIndicador(idTipoIndicador);
        this.setDescDeTipoIndicador(deTipoIndicador);
        this.setDeFormula(deFormula);
        
        this.setCoClaveIndicador(coClaveIndicador);
        this.setEsIndicador(esIndicador);
        this.setIdTipoUnidadMedida(idTipoUnidadMedida);
        this.setIdProceso(idProceso);
        this.setIdTipoAgrupadoPor(idTipoAgrupadoPor);
        this.setIdTipoActorNegocio(idTipoActorNegocio);
        this.setIdTipoCaracterFisc(idTipoCaracterFisc);
        this.setDescCoTipoAgrupadoPor(descCoTipoAgrupadoPor);
        this.setDescDeUnidadMedida(descDeUnidadMedida);
        
        this.setDescDeEstado("Activo");
        if(esIndicador.equals(ConstantesUtil.IND_INACTIVO_I)) {
        	this.setDescDeEstado("Inactivo");
        }

        this.setDescNoProceso(descNoProceso);
        this.setDescNoTipoUnidadMedida(descNoTipoUnidadMedida);
        this.setDescNoTipoAgrupadoPor(descNoTipoAgrupadoPor);
        this.setDescNoTipoActorNegocio(descNoTipoActorNegocio);
        this.setDescNoTipoCaracterFisc(descNoTipoCaracterFisc);

        this.setDescNoRelacionPasoOrigen(descNoRelacionPasoOrigen);
        this.setDescNoRelacionPasoDestino(descNoRelacionPasoDestino);
    }
}
