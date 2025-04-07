package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimEstandarProgramaDTOResultado extends PgimEstandarProgramaDTO{

	/**
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EstandarProgramaRepository#listarEstandarPrograma()
     */
	
	public PgimEstandarProgramaDTOResultado(Long idEstandarPrograma, Long idLineaPrograma, 
			Long idSubtipoSupervision, String deSubtipoSupervision, Long idMotivoSupervision, 
			String deMotivoSupervision, BigDecimal moPorSupervision, Integer caDiasSupervision,
			Integer caSupervisiones) {
        super();

        this.setIdEstandarPrograma(idEstandarPrograma);
        this.setIdLineaPrograma(idLineaPrograma);
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        
        if (idSubtipoSupervision != null) {
        	this.setDescSubtipoMotivo(deSubtipoSupervision);
        	this.setDescTipoSupervision("Programada");
        	/*if (idSubtipoSupervision.compareTo(1L) == 0 || idSubtipoSupervision.compareTo(1L) == 5 ) {
        		this.setDescTipoSupervision("No programada");
        	}
        	else{
        		this.setDescTipoSupervision("Programada");
        	}*/
        }
        else{
        	this.setDescSubtipoMotivo(deMotivoSupervision);
        	this.setDescTipoSupervision("No programada");
        	/*if (idMotivoSupervision.compareTo(2L) == 0 || idMotivoSupervision.compareTo(6L) == 5 ) {
        		this.setDescTipoSupervision("No programada");
        	}
        	else{
        		this.setDescTipoSupervision("Programada");
        	}*/
        }
        
        this.setIdSubtipoSupervision(idSubtipoSupervision);
        this.setIdMotivoSupervision(idMotivoSupervision);
        this.setMoPorSupervision(moPorSupervision);
        this.setCaDiasSupervision(caDiasSupervision);
        this.setCaSupervisiones(caSupervisiones);
       
	}
	
	/**
     * 
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EstandarProgramaRepository#listarEstandarPrograma()
     */
	
	public PgimEstandarProgramaDTOResultado(BigDecimal moPorSupervision, Integer caDiasSupervision) {
        super();
        this.setMoPorSupervision(moPorSupervision);
        this.setCaDiasSupervision(caDiasSupervision);
	}
	
	
	/**
     * Permite portar los datos necesarios.
     * 
     * En el repositorio usa el método:
     * 
     * @see pe.gob.osinergmin.pgim.models.repository.EstandarProgramaRepository#listarNoProgramada()
     * 
	 * @param idEstandarPrograma
	 * @param idProgramaSupervision
	 * @param idMotivoSupervision
	 * @param deMotivoSupervision
	 * @param moPorDiaSupervision
	 * @param caDiasSupervision
	 * @param caSupervisiones
	 */
	public PgimEstandarProgramaDTOResultado(Long idEstandarPrograma, Long idLineaPrograma,
			Long idMotivoSupervision, String deMotivoSupervision, BigDecimal moPorSupervision,
			Integer caDiasSupervision, Integer caSupervisiones, BigDecimal descCoTotalSupervisiones) {
		super();

		this.setIdEstandarPrograma(idEstandarPrograma);
		this.setIdLineaPrograma(idLineaPrograma);
		this.setIdMotivoSupervision(idMotivoSupervision);
		this.setDescSubtipoMotivo(deMotivoSupervision);
		this.setMoPorSupervision(moPorSupervision);
		this.setCaDiasSupervision(caDiasSupervision);
		this.setCaSupervisiones(caSupervisiones);
		this.setDescCoTotalSupervisiones(descCoTotalSupervisiones);

	}
	
	public PgimEstandarProgramaDTOResultado(Long idEstandarPrograma, Long idLineaPrograma, Long idSubtipoSupervision, 
			Long idMotivoSupervision,  BigDecimal moPorSupervision, Integer caDiasSupervision) {
		super();
		this.setIdEstandarPrograma(idEstandarPrograma); 
		this.setIdLineaPrograma(idLineaPrograma);
		this.setIdSubtipoSupervision(idSubtipoSupervision);
		this.setIdMotivoSupervision(idMotivoSupervision);
		this.setMoPorSupervision(moPorSupervision);
		this.setCaDiasSupervision(caDiasSupervision);
	}

	
}
