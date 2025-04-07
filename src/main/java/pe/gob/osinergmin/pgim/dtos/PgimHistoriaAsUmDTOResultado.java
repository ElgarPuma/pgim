package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimHistoriaAsUmDTOResultado extends PgimHistoriaAsUmDTO {

    public PgimHistoriaAsUmDTOResultado(Long idHistoriaAsUm, String coDocidAgenteSupervisadoPrevio,
            String descIdAgenteSupervisadoPrevio, Date feInicioTitularidad) {
        super();
        this.setIdHistoriaAsUm(idHistoriaAsUm);
        this.setCoDocidAgenteSupervisadoPrevio(coDocidAgenteSupervisadoPrevio);
        this.setDescAgenteSupervisadoPrevio(descIdAgenteSupervisadoPrevio);
        this.setFeInicioTitularidad(feInicioTitularidad);
    }

    public PgimHistoriaAsUmDTOResultado(Long idHistoriaAsUm, Long idAgenteSupervisadoPrevio,
            String descAgenteSupervisadoPrevio, Date feInicioTitularidad, String esRegistro) {
        super();
        this.setIdHistoriaAsUm(idHistoriaAsUm);
        this.setIdAgenteSupervisadoPrevio(idAgenteSupervisadoPrevio);
        this.setDescAgenteSupervisadoPrevio(descAgenteSupervisadoPrevio);
        this.setFeInicioTitularidad(feInicioTitularidad);
        this.setEsRegistro(esRegistro);
    }

    public PgimHistoriaAsUmDTOResultado(Date feInicioTitularidad) {
        super();
        this.setFeInicioTitularidad(feInicioTitularidad);
    }
}