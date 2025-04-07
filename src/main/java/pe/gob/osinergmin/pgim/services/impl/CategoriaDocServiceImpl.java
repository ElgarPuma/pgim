package pe.gob.osinergmin.pgim.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.PgimCategoriaDocDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionPaso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervision;
import pe.gob.osinergmin.pgim.models.repository.CategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.RelacionPasoRepository;
import pe.gob.osinergmin.pgim.models.repository.SubcategoriaDocRepository;
import pe.gob.osinergmin.pgim.models.repository.SupervisionRepository;
import pe.gob.osinergmin.pgim.services.CategoriaDocService;
import pe.gob.osinergmin.pgim.services.FlujoTrabajoService;
import pe.gob.osinergmin.pgim.services.LiquidacionService;
import pe.gob.osinergmin.pgim.utils.ConstPasoProcesoSupervision;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidades Categoría y Subcategoría de documentos
 * 
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 30/06/2020
 * @fecha_de_ultima_actualización: 30/06/2020 
 */
@Service
@Transactional(readOnly = true)
public class CategoriaDocServiceImpl implements CategoriaDocService {
	
	@Autowired
	private CategoriaDocRepository categoriaDocRepository;
	
	@Autowired
	private SubcategoriaDocRepository subCategoriaDocRepository;

	@Autowired
	private LiquidacionService liquidacionService;

	@Autowired
	private FlujoTrabajoService flujoTrabajoService;

	@Autowired
	private SupervisionRepository supervisionRepository;

	@Autowired
	private RelacionPasoRepository relacionPasoRepository;
	
	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaDoc() {
		List<PgimCategoriaDocDTO> lista = new ArrayList<>();
		lista = categoriaDocRepository.listarCategoriaDoc();

		return lista;
	}
	
	@Override
	public List<PgimSubcategoriaDocDTO> listarSubcategorias() {
		List<PgimSubcategoriaDocDTO> lista = new ArrayList<>();
		lista = subCategoriaDocRepository.listarSubCategorias();

		return lista;
	}


	@Override
	public List<PgimSubcategoriaDocDTO> listarSubcategoriasFase(Long idProceso, Long idFase, Long coTablaInstancia, 
			Long idInstanciaPaso, boolean flSoloPaso) throws Exception {
		List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO = new ArrayList<>();
		String superFlPropia = "";

		if (idFase > 0) {
			// Caso de subcategorías dinámicas
			if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION) ||
				idProceso.equals(ConstantesUtil.PARAM_PROCESO_FISCALIZACION) ||
				idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {

				PgimInstanciaPasoDTO pgimInstanciaPasoDTOActual = this.flujoTrabajoService
						.obtenerInstanciaPasoActualPorIdInstanciaPaso(idInstanciaPaso);
						
				PgimRelacionPaso pgimRelacion = this.relacionPasoRepository
						.findById(pgimInstanciaPasoDTOActual.getIdRelacionPaso()).orElse(null);
				
				if (flSoloPaso) {
					lPgimSubcategoriaDocDTO = this.subCategoriaDocRepository.listarSubCategoriasPorPaso(pgimRelacion.getPasoProcesoDestino().getIdPasoProceso());
					 
				} else {
					lPgimSubcategoriaDocDTO = this.subCategoriaDocRepository.listarSubCategoriasFasePorIdPaso(pgimRelacion.getPasoProcesoDestino().getIdPasoProceso());
					
					lPgimSubcategoriaDocDTO.forEach(pgimSubcategoriaDocDTO -> 
						pgimSubcategoriaDocDTO.setNoSubcatDocumento(pgimSubcategoriaDocDTO.getCoSubcatDocumento() + " - " + pgimSubcategoriaDocDTO.getNoSubcatDocumento())
					);					
				}
				
				if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_SUPERVISION)) { // obtener solamente el informe correpondiente al tipo de supervisión (propia y no propia)
					Long idSubcatDocumentoEliminar = 0L;
					Long idPasoProceso = pgimRelacion.getPasoProcesoDestino().getIdPasoProceso();
					PgimSupervision pgimSupervision = this.supervisionRepository.findById(coTablaInstancia).orElse(null);
					superFlPropia = pgimSupervision.getFlPropia();

					// Configurando la opción de carga manual de acuerdo con la propiedad de la fiscalización
					if (superFlPropia.equals("1")) {
						for (PgimSubcategoriaDocDTO pgimSubcategoriaDocDTO : lPgimSubcategoriaDocDTO) {
							if (pgimSubcategoriaDocDTO.getDescFlAdjuntadoManualPropia() != null) {
								pgimSubcategoriaDocDTO
										.setDescFlAdjuntadoManual(
												pgimSubcategoriaDocDTO.getDescFlAdjuntadoManualPropia());
							}
						}
					}
					//
					
					if (idPasoProceso.equals(ConstPasoProcesoSupervision.PRESENTAR_INFO_SUPER)) { 
						
						if(superFlPropia.equals("0")) idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_PROPIA; 
						else idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION;
						
						long[] idSubcatDocumentoFiltroEliminar = { idSubcatDocumentoEliminar};

						lPgimSubcategoriaDocDTO = this.eliminarItemSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO, idSubcatDocumentoFiltroEliminar);
						
					}else if (idPasoProceso.equals(ConstPasoProcesoSupervision.ELABORAR_INFORME_SUPERVISION_FALLIDA)) {

						if(superFlPropia.equals("0")) idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA_PROPIA; 
						else idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SC_INFORME_SUPERVISION_FALLIDA;

						long[] idSubcatDocumentoFiltroEliminar = { idSubcatDocumentoEliminar};

						lPgimSubcategoriaDocDTO = this.eliminarItemSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO, idSubcatDocumentoFiltroEliminar);

					}else if(idPasoProceso.equals(ConstPasoProcesoSupervision.GENERAR_CREDENCIAL_TDR)){
						
						if(superFlPropia.equals("0")) idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SUBCAT_DOC_CRITERIOS;  
						else idSubcatDocumentoEliminar = ConstantesUtil.PARAM_SUBCAT_DOC_TDR;

						long[] idSubcatDocumentoFiltroEliminar = { idSubcatDocumentoEliminar};

						lPgimSubcategoriaDocDTO = this.eliminarItemSubCategoriasPorIdSubcat(lPgimSubcategoriaDocDTO, idSubcatDocumentoFiltroEliminar);

					}

				}
				
			} else {
				lPgimSubcategoriaDocDTO = this.subCategoriaDocRepository.listarSubCategoriasFase(idFase);
			}			
		} 

		if (idProceso.equals(ConstantesUtil.PARAM_PROCESO_LIQUIDACION)) {
			lPgimSubcategoriaDocDTO = this.liquidacionService.filtrarSubCategoriasDoc(coTablaInstancia, idInstanciaPaso, lPgimSubcategoriaDocDTO);
		}

		return lPgimSubcategoriaDocDTO;
	}

	@Override
	public List<PgimSubcategoriaDocDTO> listarSubCategoriasIncluirByProceso(Long idProceso, Long idFase) {
		List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO =  this.subCategoriaDocRepository.listarSubCategoriasIncluirByProceso(idProceso, idFase);
		return lPgimSubcategoriaDocDTO;
	};


    private List<PgimSubcategoriaDocDTO> eliminarItemSubCategoriasPorIdSubcat(
            List<PgimSubcategoriaDocDTO> lPgimSubcategoriaDocDTO, long[] idSubcatDocumentoFiltro) {

	    for (int i = 0; i < idSubcatDocumentoFiltro.length; i++) {
	    	long id = idSubcatDocumentoFiltro[i];
            lPgimSubcategoriaDocDTO.removeIf(pgimSubcategoriaDocDTO -> pgimSubcategoriaDocDTO.getIdSubcatDocumento().equals(id));
	    }
	
	    return lPgimSubcategoriaDocDTO;
	}
    
	@Override
	public PgimSubcategoriaDoc obtenerSubcategoria(Long id) {
		return subCategoriaDocRepository.findById(id).orElse(null);
	}
    	
    
	@Override
	public PgimCategoriaDoc obtenerCategoria(Long id) {
		return categoriaDocRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaDocByIdProceso(Long idProceso) {
		List<PgimCategoriaDocDTO> lista = new ArrayList<>();
		lista = categoriaDocRepository.listarCategoriaDocByIdProceso(idProceso);

		return lista;
	}

	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaDocByIdProcesoIdFase(Long idProceso, Long idFase) {
		List<PgimCategoriaDocDTO> lista = new ArrayList<>();
		lista = categoriaDocRepository.listarCategoriaDocByIdProcesoIdFase(idProceso, idFase);

		return lista;
	}

	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProcesoIdFase(Long idProceso, Long idFase) {
		List<PgimCategoriaDocDTO> lista = new ArrayList<>();
		lista = categoriaDocRepository.listarCategoriaYSubCategoriaByIdProcesoIdFase(idProceso, idFase);

		return lista;
	}
	
	@Override
	public List<PgimCategoriaDocDTO> listarCategoriaYSubCategoriaByIdProceso(Long idProceso) {
		List<PgimCategoriaDocDTO> lista = new ArrayList<>();
		lista = categoriaDocRepository.listarCategoriaYSubCategoriaByIdProceso(idProceso);

		return lista;
	}

}
