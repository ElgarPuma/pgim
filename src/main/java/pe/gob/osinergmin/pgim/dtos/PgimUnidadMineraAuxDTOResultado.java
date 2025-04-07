package pe.gob.osinergmin.pgim.dtos;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimUnidadMineraAuxDTOResultado extends PgimUnidadMineraAuxDTO {

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listar
     * @param idUnidadMinera
     * @param coUnidadMinera
     * @param noUnidadMinera
     * @param coDocumentoIdentidad
     * @param noRazonSocial
     * @param noTipoActividad
     * @param noDivisonSupervisora
     * @param noTipoSituacion
     * @param noTipoUnidadMinera
     * @param nuCpcdadInstldaPlanta
     * @param noUbigeo
     * @param moPuntaje
     * @param noRanking
     * @param feGeneracionRanking
     * @param idRankingRiesgo
     * @param idInstanciaPasoRanking
     */
    public PgimUnidadMineraAuxDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera,
            String coDocumentoIdentidad, String noRazonSocial, String noTipoActividad,
            String noDivisonSupervisora, String noTipoSituacion, String noTipoUnidadMinera,
            BigDecimal nuCpcdadInstldaPlanta, String noUbigeo,
            BigDecimal moPuntaje, String noRanking, Date feGeneracionRanking, Long idRankingRiesgo,
            Long idInstanciaPasoRanking ) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoRazonSocial(noRazonSocial);
        this.setNoTipoActividad(noTipoActividad);
        this.setNoDivisonSupervisora(noDivisonSupervisora);
        this.setNoTipoSituacion(noTipoSituacion);
        this.setNoTipoUnidadMinera(noTipoUnidadMinera);
        this.setNuCpcdadInstldaPlanta(nuCpcdadInstldaPlanta);
        this.setNoUbigeo(noUbigeo);
        this.setMoPuntaje(moPuntaje);
        this.setNoRanking(noRanking);
        this.setFeGeneracionRanking(feGeneracionRanking);
        this.setIdRankingRiesgo(idRankingRiesgo);
        this.setIdInstanciaPasoRanking(idInstanciaPasoRanking);
    }

    /**
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarUmAuxDisponiblePorRankingRPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarReporteUMPaginado()
     * @see pe.gob.osinergmin.pgim.models.repository.UnidadMineraAuxRepository#listarReporteUM()
     */
    public PgimUnidadMineraAuxDTOResultado(Long idUnidadMinera, String coUnidadMinera, String noUnidadMinera, String coDocumentoIdentidad,
            String noRazonSocial, String noTipoSituacion, String noTipoUnidadMinera, String nodDivisonSupervisora,
            String descIdTipoActividad, String coPlantaBeneficioDestino, String noPlantaBeneficioDestino,
            String noMetodoMinado, String noMetodoExplotacion, String indicioCamaraSubterranea,
            BigDecimal nuProfundidad, BigDecimal nuAlturaMaxima, BigDecimal nuAlturaMinima,
            String noTipoYacimiento, String mineralesSustancias, String requiereDatosRiesgo,
            String noSubactividad, String noEstadoUm, Long idRankingRiesgo,
            Long idDivisionSupervisora, Long idTipoActividad, Long idSituacion, Long idMetodoMinado, Long idEstadoUm) {
        super();
        this.setIdUnidadMinera(idUnidadMinera);
        this.setCoUnidadMinera(coUnidadMinera);
        this.setNoUnidadMinera(noUnidadMinera);
        this.setCoDocumentoIdentidad(coDocumentoIdentidad);
        this.setNoRazonSocial(noRazonSocial);
        this.setNoTipoSituacion(noTipoSituacion);
        this.setNoTipoUnidadMinera(noTipoUnidadMinera);
        this.setNoDivisonSupervisora(nodDivisonSupervisora);
        this.setNoTipoActividad(descIdTipoActividad);
        this.setCoPlantaBeneficioDestino(coPlantaBeneficioDestino);
        this.setNoPlantaBeneficioDestino(noPlantaBeneficioDestino);
        this.setNoMetodoMinado(noMetodoMinado);
        this.setNoMetodoExplotacion(noMetodoExplotacion);
        this.setIndicioCamaraSubterranea(indicioCamaraSubterranea);
        this.setNuProfundidad(nuProfundidad);
        this.setNuAlturaMaxima(nuAlturaMaxima);
        this.setNuAlturaMinima(nuAlturaMinima);
        this.setNoTipoYacimiento(noTipoYacimiento);
        this.setMineralesSustancias(mineralesSustancias);
        this.setRequiereDatosRiesgo(requiereDatosRiesgo);
        this.setNoSubactividad(noSubactividad);
        this.setNoEstadoUm(noEstadoUm);
        this.setDescIdRankingRiesgo(idRankingRiesgo);
        this.setIdDivisionSupervisora(idDivisionSupervisora);
        this.setIdTipoActividad(idTipoActividad);
        this.setIdSituacion(idSituacion);
        this.setIdMetodoMinado(idMetodoMinado);
        this.setIdEstadoUm(idEstadoUm);
    }

}
