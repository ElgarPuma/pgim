package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimCopiaDetalleUmDTOResultado extends PgimCopiaDetalleUmDTO{
	
	/**
	@see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarHistoricoUMPaginado()
	@see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarHistoricoUMReporte()	
	*/
	public PgimCopiaDetalleUmDTOResultado(
			Long idCopiaDetalleUm,
			Long idTrabajoCopiaUm,
			Long idUnidadMinera,
			Long idAgenteSupervisado,
			Long idDivisionSupervisora,
			Long idSituacion,
			Long idTipoUnidadMinera,
			Long idMetodoMinado,
			Long idTipoSustancia,
			Long idTipoActividad,
			String rucAgenteSupervisado,
			String razonSocialAs,
			String coUnidadMinera,
			String noUnidadMinera,
			String coAnonimizacion,
			BigDecimal nuCpcdadInstldaPlanta,
			String descNoDivisionSupervisora,
			String descNoSituacion,
			String descNoTipoUnidadMinera,
			String descNoMetodoMinado,
			String descNoTipoSustancia,
			String descNoTipoActividad,
			String coUnidadMineraPlanta,
			String noUnidadMineraPlanta,
			Date feInicioTitularidad,
			String periodoCopiaUm
			) {
        super();

        this.setIdCopiaDetalleUm(idCopiaDetalleUm);
        this.setIdTrabajoCopiaUm(idTrabajoCopiaUm);
        this.setIdUnidadMinera(idUnidadMinera);
        this.setIdAgenteSupervisado(idAgenteSupervisado);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setIdSituacion(idSituacion);
        this.setIdTipoUnidadMinera(idTipoUnidadMinera);
        this.setIdMetodoMinado(idMetodoMinado);
        this.setIdTipoSustancia(idTipoSustancia);
        this.setIdTipoActividad(idTipoActividad);
        this.setRucAgenteSupervisado(rucAgenteSupervisado);
        this.setRazonSocialAs(razonSocialAs);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoAnonimizacion(coAnonimizacion);
        this.setNuCpcdadInstldaPlanta(nuCpcdadInstldaPlanta);
        this.setDescNoDivisionSupervisora(descNoDivisionSupervisora);
        this.setDescNoSituacion(descNoSituacion);
        this.setDescNoTipoUnidadMinera(descNoTipoUnidadMinera);
        this.setDescNoMetodoMinado(descNoMetodoMinado);
        this.setDescNoTipoSustancia(descNoTipoSustancia);
        this.setDescNoTipoActividad(descNoTipoActividad);
        this.setTextoBusqueda(periodoCopiaUm);
        this.setCoUnidadMineraPlanta(coUnidadMineraPlanta);
        this.setNoUnidadMineraPlanta(noUnidadMineraPlanta);
        this.setFeInicioTitularidad(feInicioTitularidad);
        
    }

}
