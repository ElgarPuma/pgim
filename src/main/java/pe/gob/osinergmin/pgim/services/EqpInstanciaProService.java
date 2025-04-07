package pe.gob.osinergmin.pgim.services;

import java.util.List;

import pe.gob.osinergmin.pgim.dtos.PgimEqpInstanciaProDTO;

public interface EqpInstanciaProService {

    List<PgimEqpInstanciaProDTO> obtenerPersonalXRolOsi(Long idInstanciaProceso, Long idRolProceso);

    List<PgimEqpInstanciaProDTO> obtenerPersonalXRolContrato(Long idInstanciaProceso, Long idRolProceso);

    List<PgimEqpInstanciaProDTO> obtenerPersonalResponsableXRolOsi(Long idInstanciaProceso, Long idRolProceso);

}
