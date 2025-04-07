package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimPersonaconAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaProces;
import pe.gob.osinergmin.pgim.models.entity.PgimLiquidacion;
import pe.gob.osinergmin.pgim.models.entity.PgimRolProceso;
import pe.gob.osinergmin.pgim.models.repository.InstanciaProcesRepository;
import pe.gob.osinergmin.pgim.models.repository.LiquidacionRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalConAuxRepository;
import pe.gob.osinergmin.pgim.models.repository.RolProcesoRepository;
import pe.gob.osinergmin.pgim.services.PersonaconAuxService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Personal contrato
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 20/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020 
 */
@Service
@Transactional(readOnly = true)
public class PersonaconAuxServiceImpl implements PersonaconAuxService {
	
	@Autowired
    private PersonalConAuxRepository personalConAuxRepository;

	@Autowired
    private RolProcesoRepository rolProcesoRepository;

	@Autowired
    private InstanciaProcesRepository instanciaProcesRepository;

	@Autowired
    private LiquidacionRepository liquidacionRepository;
		
	@Override
    public List<PgimPersonaconAuxDTO> listarPersonalContratoSinDuplicadosXrol(Long idInstanciaProceso, Long idRolProceso) {

        PgimRolProceso pgimRolProceso = this.rolProcesoRepository.findById(idRolProceso).orElse(null);
        
        Long idProceso = pgimRolProceso.getPgimProceso().getIdProceso();

        List<PgimPersonaconAuxDTO> lPgimPersonaconAuxDTO = null;

        if (idProceso == ConstantesUtil.PARAM_PROCESO_SUPERVISION) {
            lPgimPersonaconAuxDTO = personalConAuxRepository.listarPersonalContratoSinDuplicadosXrol(idInstanciaProceso, idRolProceso);

        } else if (idProceso == ConstantesUtil.PARAM_PROCESO_LIQUIDACION) {
            PgimInstanciaProces pgimInstanciaProces = this.instanciaProcesRepository.findById(idInstanciaProceso).orElse(null);
            Long idLiquidacion = pgimInstanciaProces.getCoTablaInstancia();

            PgimLiquidacion pgimLiquidacion = liquidacionRepository.findById(idLiquidacion).orElse(null);

            Long idContrato = pgimLiquidacion.getPgimContrato().getIdContrato();

            lPgimPersonaconAuxDTO = personalConAuxRepository.listarPersonalContratoSinDuplicadosXrolContrato(idInstanciaProceso, idContrato, idRolProceso);
        }

        return lPgimPersonaconAuxDTO;
    }

}
