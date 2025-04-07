package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTarifarioReglaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimSubtipoSupervision;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioContrato;
import pe.gob.osinergmin.pgim.models.entity.PgimTarifarioRegla;
import pe.gob.osinergmin.pgim.models.repository.TarifarioReglaRepository;
import pe.gob.osinergmin.pgim.services.TarifarioReglaService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Tarifario regla
 * 
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */
@Service
@Transactional(readOnly = true)
public class TarifarioReglaServiceImpl implements TarifarioReglaService{

	@Autowired
    private TarifarioReglaRepository tarifarioReglaRepository;
	
	@Transactional(readOnly = false)
    @Override
    public PgimTarifarioReglaDTO crearTarifarioRegla(PgimTarifarioReglaDTO pgimTarifarioReglaDTO, AuditoriaDTO auditoriaDTO) {
		PgimTarifarioRegla pgimTarifarioRegla = new PgimTarifarioRegla();

        PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato(); 
        tarifarioContrato.setIdTarifarioContrato(pgimTarifarioReglaDTO.getIdTarifarioContrato());
        pgimTarifarioRegla.setPgimTarifarioContrato(tarifarioContrato);
        
        PgimSubtipoSupervision pgimSubtipoSupervision = new PgimSubtipoSupervision();
        pgimSubtipoSupervision.setIdSubtipoSupervision(pgimTarifarioReglaDTO.getIdSubtipoSupervision());
        pgimTarifarioRegla.setPgimSubtipoSupervision(pgimSubtipoSupervision);
        
        PgimMotivoSupervision pgimMotivoSupervision = new PgimMotivoSupervision();
        pgimMotivoSupervision.setIdMotivoSupervision(pgimTarifarioReglaDTO.getIdMotivoSupervision());
        pgimTarifarioRegla.setPgimMotivoSupervision(pgimMotivoSupervision);
        
        pgimTarifarioRegla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimTarifarioRegla.setFeCreacion(auditoriaDTO.getFecha());
        pgimTarifarioRegla.setUsCreacion(auditoriaDTO.getUsername());
        pgimTarifarioRegla.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimTarifarioRegla pgimTarifarioReglaCreado = tarifarioReglaRepository.save(pgimTarifarioRegla);

        PgimTarifarioReglaDTO pgimTarifarioReglaDTOCreado = this.obtenerTarifarioReglaPorId(pgimTarifarioReglaCreado.getIdTarifarioRegla());

        return pgimTarifarioReglaDTOCreado;
    }
	
	@Transactional(readOnly = false)
    @Override
    public PgimTarifarioReglaDTO modificarTarifarioRegla(PgimTarifarioReglaDTO pgimTarifarioReglaDTO, AuditoriaDTO auditoriaDTO) {
		PgimTarifarioRegla pgimTarifarioRegla = new PgimTarifarioRegla();

        PgimTarifarioContrato tarifarioContrato = new PgimTarifarioContrato(); 
        tarifarioContrato.setIdTarifarioContrato(pgimTarifarioReglaDTO.getIdTarifarioContrato());
        pgimTarifarioRegla.setPgimTarifarioContrato(tarifarioContrato);
        
        pgimTarifarioRegla.setIdTarifarioRegla(pgimTarifarioReglaDTO.getIdTarifarioRegla());

        PgimSubtipoSupervision pgimSubtipoSupervision = new PgimSubtipoSupervision();
        pgimSubtipoSupervision.setIdSubtipoSupervision(pgimTarifarioReglaDTO.getIdSubtipoSupervision());
        pgimTarifarioRegla.setPgimSubtipoSupervision(pgimSubtipoSupervision);
        
        PgimMotivoSupervision pgimMotivoSupervision = new PgimMotivoSupervision();
        pgimMotivoSupervision.setIdMotivoSupervision(pgimTarifarioReglaDTO.getIdMotivoSupervision());
        pgimTarifarioRegla.setPgimMotivoSupervision(pgimMotivoSupervision);
        
        pgimTarifarioRegla.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimTarifarioRegla.setFeActualizacion(auditoriaDTO.getFecha());
        pgimTarifarioRegla.setUsActualizacion(auditoriaDTO.getUsername());
        pgimTarifarioRegla.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimTarifarioRegla pgimTarifarioReglaCreado = tarifarioReglaRepository.save(pgimTarifarioRegla);

        PgimTarifarioReglaDTO pgimTarifarioReglaDTOCreado = this.obtenerTarifarioReglaPorId(pgimTarifarioReglaCreado.getIdTarifarioRegla());

        return pgimTarifarioReglaDTOCreado;
    }
	
	@Override
    public PgimTarifarioReglaDTO obtenerTarifarioReglaPorId(Long idTarifarioRegla) {
        return this.tarifarioReglaRepository.obtenerTarifarioReglaPorId(idTarifarioRegla);
    }
	
	@Override
    public List<PgimTarifarioReglaDTO> obtenerTarifarioReglaPorIdTarifarioContrato(Long idTarifarioContrato) {
        return this.tarifarioReglaRepository.obtenerTarifarioReglaPorIdTarifarioContrato(idTarifarioContrato);
    }
	
}
