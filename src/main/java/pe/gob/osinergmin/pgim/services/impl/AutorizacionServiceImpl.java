package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionCmDTO;
import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimComponenteMineroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAutorizacion;
import pe.gob.osinergmin.pgim.models.entity.PgimAutorizacionCm;
import pe.gob.osinergmin.pgim.models.entity.PgimComponenteMinero;
import pe.gob.osinergmin.pgim.models.entity.PgimPersona;
import pe.gob.osinergmin.pgim.models.entity.PgimUnidadMinera;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.AutorizacionCmRepository;
import pe.gob.osinergmin.pgim.models.repository.AutorizacionRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonaRepository;
import pe.gob.osinergmin.pgim.models.repository.PgimComponenteMinRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadMineraRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.AutorizacionService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Autorizacion
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 * @fecha_de_ultima_actualización: 03/09/2020 
 */
@Service
@Transactional(readOnly = true)
public class AutorizacionServiceImpl implements AutorizacionService {

	@Autowired
	private AutorizacionRepository autorizacionRepository;
	
	@Autowired
	private UnidadMineraRepository unidadMineraRepository;
	
	@Autowired
	private ValorParametroRepository valorParametroRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private PgimComponenteMinRepository componenteMineroRepository;
	
	@Autowired
	private AutorizacionCmRepository autorizacionCmRepository;
	
	@Override
    public Page<PgimAutorizacionDTO> listarAutorizacion(final Long idUnidadMinera, final Pageable paginador) {
        
		return this.autorizacionRepository.listarAutorizacion(idUnidadMinera,
                paginador);
    }
	// @Override
    // public List<PgimAutorizacionDTO> listarAutorizacion(Long idUnidadMinera) {
    //     List<PgimAutorizacionDTO> lista = new ArrayList<>();
    //     lista = autorizacionRepository.listarAutorizacion(idUnidadMinera);
    //     return lista;
    // }
	
	@Override
	public PgimAutorizacionDTO obtenerAutorizacionById(Long idAutorizacion) {
		PgimAutorizacionDTO autorizacion = autorizacionRepository.getAutorizacionById(idAutorizacion);
		return autorizacion;
	}

	
	@Transactional(readOnly = false)
    @Override    
    public PgimAutorizacionDTO crearAutorizacion(PgimAutorizacionDTO autorizacionDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimAutorizacion autorizacion = new PgimAutorizacion();

		PgimUnidadMinera unidadMinera = unidadMineraRepository.findById(autorizacionDTO.getIdUnidadMinera()).orElse(null);
		autorizacion.setPgimUnidadMinera(unidadMinera);		
		
		Optional<PgimValorParametro> tipoAutorizacion = valorParametroRepository.findById(autorizacionDTO.getIdTipoAutorizacion());
		autorizacion.setTipoAutorizacion(tipoAutorizacion.get());
		
		PgimPersona personaEE = personaRepository.findById(autorizacionDTO.getIdPersona()).orElse(null);
		autorizacion.setPgimPersona(personaEE);
		
		autorizacion.setNuDocumento(autorizacionDTO.getNuDocumento());
		autorizacion.setFeInicioAutorizacion(autorizacionDTO.getFeInicioAutorizacion());
		autorizacion.setFeFinAutorizacion(autorizacionDTO.getFeFinAutorizacion());
		autorizacion.setDeNota(autorizacionDTO.getDeNota());
		autorizacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);		
		autorizacion.setFeCreacion(auditoriaDTO.getFecha());
		autorizacion.setUsCreacion(auditoriaDTO.getUsername());
		autorizacion.setIpCreacion(auditoriaDTO.getTerminal());
		
		PgimAutorizacion pgimAutorizacion = autorizacionRepository.save(autorizacion);
		
		//registramos los componentes mineros seleccionados
		for (PgimComponenteMineroDTO componente : autorizacionDTO.getAuxListaComponentesMineros()) {
			
			if(componente.isDescSeleccionado()) {
				
				PgimAutorizacionCm autorizacionCm = new PgimAutorizacionCm();
				
				autorizacionCm.setPgimAutorizacion(autorizacion);				
				
				PgimComponenteMinero componenteMinero = componenteMineroRepository.findById(componente.getIdComponenteMinero()).orElse(null);
				autorizacionCm.setPgimComponenteMinero(componenteMinero);
				
				autorizacionCm.setEsRegistro(ConstantesUtil.IND_ACTIVO);		
				autorizacionCm.setFeCreacion(auditoriaDTO.getFecha());
				autorizacionCm.setUsCreacion(auditoriaDTO.getUsername());
				autorizacionCm.setIpCreacion(auditoriaDTO.getTerminal());
				
				autorizacionCmRepository.save(autorizacionCm);				
				
			}
			
		}
		
		
		PgimAutorizacionDTO pgimAutorizacionDTO = autorizacionRepository.getAutorizacionById(pgimAutorizacion.getIdAutorizacion());				
		
		return pgimAutorizacionDTO;
    }
	
	@Transactional(readOnly = false)
    @Override
    public PgimAutorizacionDTO modificarAutorizacion(PgimAutorizacionDTO autorizacionDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimAutorizacion autorizacion = autorizacionRepository.findById(autorizacionDTO.getIdAutorizacion()).orElse(null);		

		//PgimUnidadMinera unidadMinera = unidadMineraRepository.findById(autorizacionDTO.getIdUnidadMinera()).orElse(null);
		//autorizacion.setPgimUnidadMinera(unidadMinera);		
		
		Optional<PgimValorParametro> tipoAutorizacion = valorParametroRepository.findById(autorizacionDTO.getIdTipoAutorizacion());
		autorizacion.setTipoAutorizacion(tipoAutorizacion.get());
		
		PgimPersona personaEE = personaRepository.findById(autorizacionDTO.getIdPersona()).orElse(null);
		autorizacion.setPgimPersona(personaEE);
		
		autorizacion.setNuDocumento(autorizacionDTO.getNuDocumento());
		autorizacion.setFeInicioAutorizacion(autorizacionDTO.getFeInicioAutorizacion());
		autorizacion.setFeFinAutorizacion(autorizacionDTO.getFeFinAutorizacion());
		autorizacion.setDeNota(autorizacionDTO.getDeNota());
		autorizacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);		
		autorizacion.setFeActualizacion(auditoriaDTO.getFecha());
		autorizacion.setUsActualizacion(auditoriaDTO.getUsername());
		autorizacion.setIpActualizacion(auditoriaDTO.getTerminal());
		
		PgimAutorizacion pgimAutorizacion = autorizacionRepository.save(autorizacion);
		
		
		List<PgimAutorizacionCmDTO> listaACMold = autorizacionCmRepository.getListAutorizacionCmByIdAutorizacion(pgimAutorizacion.getIdAutorizacion());
		
		//Damos de baja los componentes que ya no se encuentran seleccionados
		for (PgimAutorizacionCmDTO acmo : listaACMold) {
			
			boolean flagEncontrado = false;
			//barremos los componenetes seleccionados
			for (PgimComponenteMineroDTO componente : autorizacionDTO.getAuxListaComponentesMineros()) {				
				if(componente.isDescSeleccionado() && acmo.getIdComponenteMinero().equals(componente.getIdComponenteMinero())) {
					flagEncontrado = true;
				}				
			}
			
			if(!flagEncontrado) {
				PgimAutorizacionCm autorizacionCm = autorizacionCmRepository.findById(acmo.getIdAutorizacionCminero()).orElse(null);
				autorizacionCm.setEsRegistro(ConstantesUtil.IND_INACTIVO);		
				autorizacionCm.setFeActualizacion(auditoriaDTO.getFecha());
				autorizacionCm.setUsActualizacion(auditoriaDTO.getUsername());
				autorizacionCm.setIpActualizacion(auditoriaDTO.getTerminal());				
				autorizacionCmRepository.save(autorizacionCm);
				
			}			
			
		}
		
		//Adicionamos los nuevos componentes seleccionados
		for (PgimComponenteMineroDTO componente : autorizacionDTO.getAuxListaComponentesMineros()) {				
			if(componente.isDescSeleccionado()) {
				
				boolean flagEncontrado = false;				
				
				for (PgimAutorizacionCmDTO acmo : listaACMold) {
					
					if(acmo.getIdComponenteMinero().equals(componente.getIdComponenteMinero())){
						flagEncontrado = true;
					}							
				
				}
				
				if(!flagEncontrado) {

					PgimAutorizacionCm autorizacionCm = new PgimAutorizacionCm();
					
					autorizacionCm.setPgimAutorizacion(autorizacion);	
					
					PgimComponenteMinero componenteMinero = componenteMineroRepository.findById(componente.getIdComponenteMinero()).orElse(null);
					autorizacionCm.setPgimComponenteMinero(componenteMinero);
					
					autorizacionCm.setEsRegistro(ConstantesUtil.IND_ACTIVO);		
					autorizacionCm.setFeCreacion(auditoriaDTO.getFecha());
					autorizacionCm.setUsCreacion(auditoriaDTO.getUsername());
					autorizacionCm.setIpCreacion(auditoriaDTO.getTerminal());
					
					autorizacionCmRepository.save(autorizacionCm);
					
					
				}	
				
			}				
		}
		
		
		PgimAutorizacionDTO pgimAutorizacionDTO = autorizacionRepository.getAutorizacionById(pgimAutorizacion.getIdAutorizacion());		
		
		return pgimAutorizacionDTO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void eliminarAutorizacion(PgimAutorizacionDTO pgimAutorizacionDTO, AuditoriaDTO auditoriaDTO) {
		
		PgimAutorizacion pgimAutorizacion = this.autorizacionRepository.findById(pgimAutorizacionDTO.getIdAutorizacion()) .orElse(null);
					
		pgimAutorizacion.setEsRegistro(ConstantesUtil.IND_INACTIVO);		
		pgimAutorizacion.setFeActualizacion(auditoriaDTO.getFecha());
		pgimAutorizacion.setUsActualizacion(auditoriaDTO.getUsername());
		pgimAutorizacion.setIpActualizacion(auditoriaDTO.getTerminal());

		this.autorizacionRepository.save(pgimAutorizacion);
	}
	

	@Override
	public List<PgimAutorizacionCmDTO> getListAutorizacionCmByIdAutorizacion(Long idAutorizacion){
		List<PgimAutorizacionCmDTO> lista = autorizacionCmRepository.getListAutorizacionCmByIdAutorizacion(idAutorizacion);
		return lista;		
	}
	
}
