package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCostoUnitario;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;
import pe.gob.osinergmin.pgim.models.repository.CostoUnitarioRepository;
import pe.gob.osinergmin.pgim.services.CostoUnitarioService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Costo unitario
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class CostoUnitarioServiceImpl implements CostoUnitarioService{

	@Autowired
    private CostoUnitarioRepository costoUnitarioRepository;
	
	@Transactional(readOnly = false)
    @Override
    public PgimCostoUnitarioDTO crearCostoUnitario(PgimCostoUnitarioDTO pgimCostoUnitarioDTO, AuditoriaDTO auditoriaDTO) {
        PgimCostoUnitario pgimCostoUnitario = new PgimCostoUnitario();

        PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato(); 
        tarifarioContrato.setIdTarifarioContrato(pgimCostoUnitarioDTO.getIdTarifarioContrato());
        pgimCostoUnitario.setPgimTarifarioContrato(tarifarioContrato);
        
        pgimCostoUnitario.setUnDiaCostoUnitario(pgimCostoUnitarioDTO.getUnDiaCostoUnitario());
        pgimCostoUnitario.setMoCostoUnitario(pgimCostoUnitarioDTO.getMoCostoUnitario());
        pgimCostoUnitario.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimCostoUnitario.setFeCreacion(auditoriaDTO.getFecha());
        pgimCostoUnitario.setUsCreacion(auditoriaDTO.getUsername());
        pgimCostoUnitario.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimCostoUnitario pgimCostoUnitarioCreado = costoUnitarioRepository.save(pgimCostoUnitario);

        PgimCostoUnitarioDTO pgimCostoUnitarioDTOCreado = this.obtenerCostoUnitarioPorId(pgimCostoUnitarioCreado.getIdCostoUnitario());

        return pgimCostoUnitarioDTOCreado;
    }
	
	@Transactional(readOnly = false)
    @Override
    public PgimCostoUnitarioDTO modificarCostoUnitario(PgimCostoUnitarioDTO pgimCostoUnitarioDTO, AuditoriaDTO auditoriaDTO) {
        PgimCostoUnitario pgimCostoUnitario = new PgimCostoUnitario();
        
        PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato(); 
        tarifarioContrato.setIdTarifarioContrato(pgimCostoUnitarioDTO.getIdTarifarioContrato());
        pgimCostoUnitario.setPgimTarifarioContrato(tarifarioContrato);
        
        pgimCostoUnitario.setIdCostoUnitario(pgimCostoUnitarioDTO.getIdCostoUnitario());
        pgimCostoUnitario.setUnDiaCostoUnitario(pgimCostoUnitarioDTO.getUnDiaCostoUnitario());
        pgimCostoUnitario.setMoCostoUnitario(pgimCostoUnitarioDTO.getMoCostoUnitario());
        pgimCostoUnitario.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimCostoUnitario.setFeActualizacion(auditoriaDTO.getFecha());
        pgimCostoUnitario.setUsActualizacion(auditoriaDTO.getUsername());
        pgimCostoUnitario.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimCostoUnitario pgimCostoUnitarioModificado = costoUnitarioRepository.save(pgimCostoUnitario);

        PgimCostoUnitarioDTO pgimCostoUnitarioDTOModificado = this.obtenerCostoUnitarioPorId(pgimCostoUnitarioModificado.getIdCostoUnitario());

        return pgimCostoUnitarioDTOModificado;
    }
	
	@Override
    public PgimCostoUnitarioDTO obtenerCostoUnitarioPorId(Long idCostoUnitario) {
        return this.costoUnitarioRepository.obtenerCostoUnitarioPorId(idCostoUnitario);
    }
	
	@Override
    public List<PgimCostoUnitarioDTO> obtenerCostoUnitarioPorIdTarifarioContrato(Long idTarifarioContrato) {
        return this.costoUnitarioRepository.obtenerCostoUnitarioPorIdTarifarioContrato(idTarifarioContrato);
    }
}
