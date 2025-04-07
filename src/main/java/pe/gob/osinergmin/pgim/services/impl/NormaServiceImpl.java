package pe.gob.osinergmin.pgim.services.impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaEnlaceDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOblgcnNrmtvaItemDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimNormaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemTipificacion;
import pe.gob.osinergmin.pgim.models.entity.PgimNorma;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaEnlace;
import pe.gob.osinergmin.pgim.models.entity.PgimNormaItem;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ItemTipificacionRepository;
import pe.gob.osinergmin.pgim.models.repository.NormaEnlaceRepository;
import pe.gob.osinergmin.pgim.models.repository.NormaItemRepository;
import pe.gob.osinergmin.pgim.models.repository.NormaRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.NormaService;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Norma legal
 * 
 * @author: PresleyRomero
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020 
 */

@Service
@Transactional(readOnly = true)
public class NormaServiceImpl implements NormaService {
	
	@Autowired
    private NormaRepository normaRepository;
	
	@Autowired
	private NormaEnlaceRepository normaEnlaceRepository;
	
	@Autowired
	private NormaItemRepository normaItemRepository;

	@Autowired
	private ItemTipificacionRepository itemTipificacionRepository;

	@Autowired
	private ParametroServiceImpl parametroService;

	@Autowired
	private ValorParametroRepository valorParametroRepository;
	
	
	@Override
    public List<PgimNormaDTO> listarPorPalabraClave(String palabra) {
        List<PgimNormaDTO> lPgimNormaDTO = this.normaRepository.listarPorPalabraClave(palabra);

        return lPgimNormaDTO;
    }
	
	@Override
	public List<PgimNormaDTO> listarNormaPorNoCortoONoNormaYTipoNorma(PgimNormaAuxDTO pgimNormaAuxDTO) {
	
		String coTipoCuadroverificacion = EValorParametro.NORMA_RCDCT.toString();
		List<PgimNormaDTO> lPgimNormaDTO = this.normaRepository.listarNormaPorNoCortoONoNormaYTipoNorma(pgimNormaAuxDTO.getNoCortoNorma(), pgimNormaAuxDTO.getNoNorma(), pgimNormaAuxDTO.getDescCoTipoNorma(), coTipoCuadroverificacion);
		return lPgimNormaDTO;

	}

	
	@Override
	public Page<PgimNormaAuxDTO> filtrar(PgimNormaDTO filtroNorma, Pageable paginador) {
		Page<PgimNormaAuxDTO> pPgimNormaAuxDTO = this.normaRepository.listar(filtroNorma.getNoCortoNorma(), filtroNorma.getNoNorma(), filtroNorma.getIdTipoNorma(), filtroNorma.getTextoBusqueda(), paginador);

        return pPgimNormaAuxDTO;
	}
	
	@Transactional(readOnly = false)
    @Override
    public PgimNormaDTO crearNorma(PgimNormaDTO pgimNormaDTO, AuditoriaDTO auditoriaDTO) {
        PgimNorma pgimNorma = new PgimNorma();
        
        pgimNorma.setNoCortoNorma(pgimNormaDTO.getNoCortoNorma());
        pgimNorma.setNoNorma(pgimNormaDTO.getNoNorma());
        pgimNorma.setFePublicacion(pgimNormaDTO.getFePublicacion());
        pgimNorma.setFlVigente(pgimNormaDTO.getFlVigente());
        
        PgimValorParametro tipoNorma = new PgimValorParametro();
        tipoNorma.setIdValorParametro(pgimNormaDTO.getIdTipoNorma());
        pgimNorma.setTipoNorma(tipoNorma);   
        
        pgimNorma.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimNorma.setFeCreacion(auditoriaDTO.getFecha());
        pgimNorma.setUsCreacion(auditoriaDTO.getUsername());
        pgimNorma.setIpCreacion(auditoriaDTO.getTerminal());
        
        PgimNorma pgimNormaCreado = normaRepository.save(pgimNorma);
        
        
        PgimNormaDTO pgimNormaDTOCreado = this.obtenerNormaPorId(pgimNormaCreado.getIdNorma());

        return pgimNormaDTOCreado;
    }
	
	
	@Transactional(readOnly = false)
    @Override
    public PgimNormaDTO modificarNorma(PgimNormaDTO pgimNormaDTO, PgimNorma pgimNorma, AuditoriaDTO auditoriaDTO) {
    	
		pgimNorma.setNoCortoNorma(pgimNormaDTO.getNoCortoNorma());
        pgimNorma.setNoNorma(pgimNormaDTO.getNoNorma());
        pgimNorma.setFePublicacion(pgimNormaDTO.getFePublicacion());
        pgimNorma.setFlVigente(pgimNormaDTO.getFlVigente());
        
        PgimValorParametro tipoNorma = new PgimValorParametro();
        tipoNorma.setIdValorParametro(pgimNormaDTO.getIdTipoNorma());
        pgimNorma.setTipoNorma(tipoNorma);                  
    	
        pgimNorma.setFeActualizacion(auditoriaDTO.getFecha());
        pgimNorma.setUsActualizacion(auditoriaDTO.getUsername());
        pgimNorma.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimNorma pgimNormaModificada = this.normaRepository.save(pgimNorma);    

        PgimNormaDTO pgimNormaDTOModificado = this.obtenerNormaPorId(pgimNormaModificada.getIdNorma());

        return pgimNormaDTOModificado;
    }
	
	@Transactional(readOnly = false)
    @Override
    public void eliminarNorma(PgimNorma pgimNormaActual, AuditoriaDTO auditoriaDTO) {
        
    	pgimNormaActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
    	pgimNormaActual.setFeActualizacion(auditoriaDTO.getFecha());
    	pgimNormaActual.setUsActualizacion(auditoriaDTO.getUsername());
    	pgimNormaActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.normaRepository.save(pgimNormaActual);
    }
	
	
	@Override
    public PgimNormaDTO obtenerNormaPorId(Long idNorma) {
        return this.normaRepository.obtenerNormaPorId(idNorma);
    }
	
	@Override
    public PgimNormaAuxDTO obtenerNormaAuxPorId(Long idNorma) {
        return this.normaRepository.obtenerNormaAuxPorId(idNorma);
    }
		
	@Override
	public PgimNorma getByIdNorma(Long idNorma) {
		return this.normaRepository.findById(idNorma).orElse(null);
	}
	
	@Override
	public List<PgimNormaEnlaceDTO> listarEnlacesNorma(Long idNorma) throws Exception {
		List<PgimNormaEnlaceDTO> lPgimNormaEnlaceDTO = this.normaEnlaceRepository
				.listarNormaEnlace(idNorma);
		return lPgimNormaEnlaceDTO;
	}	
	
	@Transactional(readOnly = false)
	@Override
	public PgimNormaEnlaceDTO crearEnlace(PgimNormaEnlaceDTO pgimNormaEnlaceDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimNormaEnlace pgimNormaEnlace = new PgimNormaEnlace();
		
		PgimNorma pgimNorma = new PgimNorma();
		pgimNorma.setIdNorma(pgimNormaEnlaceDTO.getIdNorma());
        pgimNormaEnlace.setPgimNorma(pgimNorma);

        pgimNormaEnlace.setDeTitulo(pgimNormaEnlaceDTO.getDeTitulo());
        pgimNormaEnlace.setDeEnlace(pgimNormaEnlaceDTO.getDeEnlace());

		pgimNormaEnlace.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimNormaEnlace.setFeCreacion(auditoriaDTO.getFecha());
		pgimNormaEnlace.setUsCreacion(auditoriaDTO.getUsername());
		pgimNormaEnlace.setIpCreacion(auditoriaDTO.getTerminal());

		PgimNormaEnlace pgimNormaEnlaceCreado = this.normaEnlaceRepository.save(pgimNormaEnlace);

		PgimNormaEnlaceDTO pgimNormaEnlaceDTOCreado = this.normaEnlaceRepository
				.obtenerEnlacePorId(pgimNormaEnlaceCreado.getIdNormaEnlace());

		return pgimNormaEnlaceDTOCreado;
	}
	
	
	@Transactional(readOnly = false)
	@Override
	public Long eliminarEnlace(Long idEnlace, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimNormaEnlace pgimNormaEnlace = null;
		if (CommonsUtil.isNullOrZeroLong(idEnlace)) {
			pgimNormaEnlace = this.normaEnlaceRepository.findById(idEnlace).orElse(null);
			if (pgimNormaEnlace != null) {
				pgimNormaEnlace.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				pgimNormaEnlace.setFeActualizacion(auditoriaDTO.getFecha());
				pgimNormaEnlace.setUsActualizacion(auditoriaDTO.getUsername());
				pgimNormaEnlace.setIpActualizacion(auditoriaDTO.getTerminal());
				this.normaEnlaceRepository.save(pgimNormaEnlace);
				rpta = pgimNormaEnlace.getIdNormaEnlace();
			}
		}
		return rpta;
	}
	
	
	@Override
	public Page<PgimNormaItemAuxDTO> listarItemsNorma(Long idNorma, PgimNormaItemDTO filtroPgimNormaItemDTO, Pageable paginador) throws Exception {
		Page<PgimNormaItemAuxDTO> lPgimNormaItemAuxDTO = this.normaItemRepository
				.listarNormaItem(idNorma, filtroPgimNormaItemDTO.getIdDivisionItem(), filtroPgimNormaItemDTO.getDeContenidoT(), filtroPgimNormaItemDTO.getFlVigente(),  paginador);
		return lPgimNormaItemAuxDTO;
	}
	
	@Override
	public PgimNormaItem getByIdNormaItem(Long idNormaItem) {
		return this.normaItemRepository.findById(idNormaItem).orElse(null);
	}	
	
	@Transactional(readOnly = false)
	@Override
	public PgimNormaItemDTO crearItem(PgimNormaItemDTO pgimNormaItemDTO,
			AuditoriaDTO auditoriaDTO) {

		PgimNormaItem pgimNormaItem = new PgimNormaItem();
		        
        pgimNormaItem.setCoItem(pgimNormaItemDTO.getCoItem());
        pgimNormaItem.setDeContenidoT(pgimNormaItemDTO.getDeContenidoT());
        pgimNormaItem.setFlVigente(pgimNormaItemDTO.getFlVigente());        
        
        PgimNorma norma = new PgimNorma();
        norma.setIdNorma(pgimNormaItemDTO.getIdNorma());
        pgimNormaItem.setPgimNorma(norma); 
        
        PgimValorParametro divisionItem = new PgimValorParametro();
        divisionItem.setIdValorParametro(pgimNormaItemDTO.getIdDivisionItem());
        pgimNormaItem.setDivisionItem(divisionItem); 
        
        if(pgimNormaItemDTO.getIdNormaItemPadre() != null) {
        	PgimNormaItem itemPadre = new PgimNormaItem();
            itemPadre.setIdNormaItem(pgimNormaItemDTO.getIdNormaItemPadre());
            pgimNormaItem.setNormaItemPadre(itemPadre);         	
        }

		pgimNormaItem.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimNormaItem.setFeCreacion(auditoriaDTO.getFecha());
		pgimNormaItem.setUsCreacion(auditoriaDTO.getUsername());
		pgimNormaItem.setIpCreacion(auditoriaDTO.getTerminal());

		PgimNormaItem pgimNormaItemCreado = this.normaItemRepository.save(pgimNormaItem);

		PgimNormaItemDTO pgimNormaItemDTOCreado = this.obtenerItemPorId(pgimNormaItemCreado.getIdNormaItem());

		return pgimNormaItemDTOCreado;
	}
	
	@Transactional(readOnly = false)
    @Override
    public PgimNormaItemDTO modificarNormaItem(PgimNormaItemDTO pgimNormaItemDTO, PgimNormaItem pgimNormaItem, AuditoriaDTO auditoriaDTO) {
    	
		pgimNormaItem.setCoItem(pgimNormaItemDTO.getCoItem());
        pgimNormaItem.setDeContenidoT(pgimNormaItemDTO.getDeContenidoT());
        pgimNormaItem.setFlVigente(pgimNormaItemDTO.getFlVigente());        
        
        PgimNorma norma = new PgimNorma();
        norma.setIdNorma(pgimNormaItemDTO.getIdNorma());
        pgimNormaItem.setPgimNorma(norma); 
        
        PgimValorParametro divisionItem = new PgimValorParametro();
        divisionItem.setIdValorParametro(pgimNormaItemDTO.getIdDivisionItem());
        pgimNormaItem.setDivisionItem(divisionItem); 
        
        if(pgimNormaItemDTO.getIdNormaItemPadre() != null) {
        	PgimNormaItem itemPadre = new PgimNormaItem();
            itemPadre.setIdNormaItem(pgimNormaItemDTO.getIdNormaItemPadre());
            pgimNormaItem.setNormaItemPadre(itemPadre);         	
        }
    	
        pgimNormaItem.setFeActualizacion(auditoriaDTO.getFecha());
        pgimNormaItem.setUsActualizacion(auditoriaDTO.getUsername());
        pgimNormaItem.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimNormaItem pgimNormaItemModificado = this.normaItemRepository.save(pgimNormaItem);    

        PgimNormaItemDTO pgimNormaItemDTOModificado = this.obtenerItemPorId(pgimNormaItemModificado.getIdNormaItem());

        return pgimNormaItemDTOModificado;
    }
	
	@Transactional(readOnly = false)
	@Override
	public Long eliminarItem(Long idItem, AuditoriaDTO auditoriaDTO) {
		Long rpta = 0L;
		PgimNormaItem normaItem = null;
		
		if (CommonsUtil.isNullOrZeroLong(idItem)) {
			normaItem = this.normaItemRepository.findById(idItem).orElse(null);
			if (normaItem != null) {
				normaItem.setEsRegistro(ConstantesUtil.IND_INACTIVO);
				normaItem.setFeActualizacion(auditoriaDTO.getFecha());
				normaItem.setUsActualizacion(auditoriaDTO.getUsername());
				normaItem.setIpActualizacion(auditoriaDTO.getTerminal());
				this.normaItemRepository.save(normaItem);
				rpta = normaItem.getIdNormaItem();
			}
		}
		return rpta;
	}
		
	@Transactional(readOnly = false)
	@Override
	public ArrayList<Long> eliminarItemEHijos(Long idItemPadre, AuditoriaDTO auditoriaDTO, ArrayList<Long> idItemsEliminados) {
		
		// Eliminar item padre
		Long itemEliminado = this.eliminarItem(idItemPadre, auditoriaDTO);
		idItemsEliminados.add(itemEliminado);
				
		// Obtener items hijos y proceder a eliminar recursivamente
		List<PgimNormaItemDTO> lPgimNormaItemsHijos = null;
		lPgimNormaItemsHijos = this.obtenerItemsHijos(idItemPadre);
		
		for (PgimNormaItemDTO itemHijo : lPgimNormaItemsHijos) {
		    Long idItemHijo = itemHijo.getIdNormaItem();
		    this.eliminarItemEHijos(idItemHijo, auditoriaDTO, idItemsEliminados);
		}		
		
		return idItemsEliminados;
	}
	
	@Override
    public PgimNormaItemDTO obtenerItemPorId(Long idNormaItem) {
        return this.normaItemRepository.obtenerItemPorId(idNormaItem);
    }
	
	@Override
	public List<PgimNormaItemDTO> obtenerItemsHijos(Long idNormaItemPadre) {
        return this.normaItemRepository.obtenerItemsHijos(idNormaItemPadre);
    }


	@Override
	public Page<PgimNormaDTO> listarTipificacion(PgimNormaDTO filtro, Pageable paginador) throws Exception {

		Page<PgimNormaDTO> lPgimNormaDTO = this.normaRepository.listarTipificacion(
			filtro.getNoCortoNorma(), 
			filtro.getFePublicacion(), 
			filtro.getFlVigente(), 
			paginador);

		return lPgimNormaDTO;
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarTipificacion(PgimNorma pgimNormaActual, AuditoriaDTO auditoriaDTO) {
		pgimNormaActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimNormaActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimNormaActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimNormaActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.normaRepository.save(pgimNormaActual);
	}


	@Override
	public PgimNormaDTO obtenerTipificacionPorId(Long idNorma) {
		return this.normaRepository.obtenerTipificacionPorId(idNorma);
	}

	@Override
	public Page<PgimItemTipificacionDTO> listarItemTipificacion(Long idNorma, Pageable paginador) throws Exception {
		Page<PgimItemTipificacionDTO> pPgimItemTipificacionDTO = this.itemTipificacionRepository
                .listarItemTipificacion(idNorma, paginador);

        return pPgimItemTipificacionDTO;
	}


	@Override
	public PgimItemTipificacion getByIdItemTipificacion(Long idItemTipificacion) {
		return this.itemTipificacionRepository.findById(idItemTipificacion).orElse(null);
	}

	@Transactional(readOnly = false)
	@Override
	public void eliminarItemTipificacion(PgimItemTipificacion pgimItemTipificacionActual, AuditoriaDTO auditoriaDTO) {
		pgimItemTipificacionActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);

        pgimItemTipificacionActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimItemTipificacionActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimItemTipificacionActual.setIpActualizacion(auditoriaDTO.getTerminal());

        this.itemTipificacionRepository.save(pgimItemTipificacionActual);
		
	}


	@Override
	public PgimItemTipificacionDTO obtenerItemTipificacionPorId(Long idItemTipificacion) {
		return this.itemTipificacionRepository.obtenerItemTipificacionPorId(idItemTipificacion);
	}

	@Transactional(readOnly = false)
	@Override
	public PgimItemTipificacionDTO crearItemTipificacion(@Valid PgimItemTipificacionDTO pgimItemTipificacionDTO,
			AuditoriaDTO auditoriaDTO) {
		PgimItemTipificacion pgimItemTipificacion = new PgimItemTipificacion();

		pgimItemTipificacion.setPgimNorma(new PgimNorma());
		pgimItemTipificacion.getPgimNorma().setIdNorma(pgimItemTipificacionDTO.getIdNorma());

		pgimItemTipificacion.setCoTipificacion(pgimItemTipificacionDTO.getCoTipificacion());
		pgimItemTipificacion.setNoItemTipificacion(pgimItemTipificacionDTO.getNoItemTipificacion());
		pgimItemTipificacion.setDeSancionPecuniariaUit(pgimItemTipificacionDTO.getDeSancionPecuniariaUit());
		pgimItemTipificacion.setEsVigente(pgimItemTipificacionDTO.getEsVigente());
		pgimItemTipificacion.setDeBaseLegal(pgimItemTipificacionDTO.getDeBaseLegal());
		pgimItemTipificacion.setNuOrden(pgimItemTipificacionDTO.getNuOrden());

		pgimItemTipificacion.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimItemTipificacion.setFeCreacion(auditoriaDTO.getFecha());
		pgimItemTipificacion.setUsCreacion(auditoriaDTO.getUsername());
		pgimItemTipificacion.setIpCreacion(auditoriaDTO.getTerminal());

		PgimItemTipificacion pgimItemTipificacionCreado = this.itemTipificacionRepository.save(pgimItemTipificacion);

		PgimItemTipificacionDTO pgimItemTipificacionDTOCreado = this.obtenerItemTipificacionPorId(pgimItemTipificacionCreado.getIdItemTipificacion());

		return pgimItemTipificacionDTOCreado;
	}

	@Transactional(readOnly = false)
	@Override
	public PgimItemTipificacionDTO modificarItemTipificacion(PgimItemTipificacionDTO pgimItemTipificacionDTO,
			PgimItemTipificacion pgimItemTipificacion, AuditoriaDTO auditoriaDTO) {

		pgimItemTipificacion.setCoTipificacion(pgimItemTipificacionDTO.getCoTipificacion());
		pgimItemTipificacion.setNoItemTipificacion(pgimItemTipificacionDTO.getNoItemTipificacion());
		pgimItemTipificacion.setDeSancionPecuniariaUit(pgimItemTipificacionDTO.getDeSancionPecuniariaUit());
		pgimItemTipificacion.setEsVigente(pgimItemTipificacionDTO.getEsVigente());
		pgimItemTipificacion.setDeBaseLegal(pgimItemTipificacionDTO.getDeBaseLegal());
		pgimItemTipificacion.setNuOrden(pgimItemTipificacionDTO.getNuOrden());
		
		pgimItemTipificacion.setFeActualizacion(auditoriaDTO.getFecha());
		pgimItemTipificacion.setUsActualizacion(auditoriaDTO.getUsername());
		pgimItemTipificacion.setIpActualizacion(auditoriaDTO.getTerminal());

		PgimItemTipificacion pgimMatrizSupervisionModificado = this.itemTipificacionRepository
				.save(pgimItemTipificacion);

		PgimItemTipificacionDTO pgimMatrizSupervisionDTOResultado = this
				.obtenerItemTipificacionPorId(pgimMatrizSupervisionModificado.getIdItemTipificacion());

		return pgimMatrizSupervisionDTOResultado;
	}	
	
	@Override
	public List<PgimNormaItemAuxDTO> listarItemsNormaParaObligacion(Long idNorma) throws Exception {
		
		return this.normaItemRepository.listarNormaItemParaObligacion(idNorma);
	}
	

	@Override
	public Page<PgimNormaItemAuxDTO> listarItemsParaObligacionFilter(PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO, Pageable paginador) throws Exception {
		
		Long idTipoDivisionArticulo =  this.valorParametroRepository.obtenerIdValorParametro(EValorParametro.DIITE_ARTCLO.toString());
		
		PgimValorParametroDTO divisionItemArticulo = this.parametroService.obtenerValorParametroPorID(idTipoDivisionArticulo);
		
		String filtroArticulo=null;
		
		if(filtroPgimNormaItemAuxDTO.getCoItem()!=null && filtroPgimNormaItemAuxDTO.getCoItem() != "" ) {
			filtroArticulo = divisionItemArticulo.getDeValorParametro() + " " + filtroPgimNormaItemAuxDTO.getCoItem(); 
		}
		
		Page <PgimNormaItemAuxDTO> pPgimNormaItemAuxDTO = this.normaItemRepository.obtenerIdArticuloParaObligacionFilter(filtroPgimNormaItemAuxDTO.getIdNorma(), filtroPgimNormaItemAuxDTO.getDescNoNorma(), filtroArticulo, paginador);
		
		return pPgimNormaItemAuxDTO;
	}
	
	@Override
	public List<PgimNormaItemAuxDTO> obtenerItemNormasHijosPorUbicacion(PgimNormaItemAuxDTO filtroPgimNormaItemAuxDTO) throws Exception {
			
		List <PgimNormaItemAuxDTO> lPgimNormaItemAuxDTO = this.normaItemRepository.obtenerItemNormasHijosPorUbicacion(filtroPgimNormaItemAuxDTO.getUbicacionItem());
		
		return lPgimNormaItemAuxDTO;
	}
	
	@Override
	public List<PgimOblgcnNrmtvaItemDTO> obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(PgimOblgcnNrmtvaItemDTO filtroPgimOblgcnNrmtvaItemDTO) throws Exception {
			
		List <PgimOblgcnNrmtvaItemDTO> lPgimOblgcnNrmtvaItemDTO = this.normaItemRepository.obtenerItemNormasHijosPorUbicacionPorObligacionNormativa(filtroPgimOblgcnNrmtvaItemDTO.getDescUbicacionNormaItem(), filtroPgimOblgcnNrmtvaItemDTO.getIdOblgcnNrmtvaHchoc());
		
		return lPgimOblgcnNrmtvaItemDTO;
	}
	
	@Override
	public Page<PgimItemTipificacionDTO> listarItemTipificacionReportePaginado(PgimItemTipificacionDTO filtro, Pageable paginador) throws Exception {
		System.out.println(filtro.getIdNorma());
		Page<PgimItemTipificacionDTO> pPgimItemTipificacionDTO = this.itemTipificacionRepository
                .listarItemTipificacion(filtro.getIdNorma(), paginador);

        return pPgimItemTipificacionDTO;
	}

	@Override
	public Page<PgimNormaAuxDTO> listarNormasDeCuadroVerificacion(PgimNormaAuxDTO pgimNormaAuxDTO, Pageable paginador){
		
		String coTipoCuadroverificacion = EValorParametro.NORMA_RCDCT.toString();
		
		Page<PgimNormaAuxDTO> pPgimNormaAuxDTO = this.normaRepository
                .listarNormasDeCuadroVerificacion(pgimNormaAuxDTO.getDescIdMatrizSupervision(), coTipoCuadroverificacion, paginador);

		return pPgimNormaAuxDTO;
	}

	@Override
    public List<PgimNormaDTO> listarCuadroTipificacion() {
		
		Long idTipoNorma = ConstantesUtil.PARAM_TIPO_NORMA_TIPIFICACION; 
		
        List<PgimNormaDTO> lPgimNormaDTO = this.normaRepository.listarCuadroTipificacion(idTipoNorma);

        return lPgimNormaDTO;
    }

	
}
