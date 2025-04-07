package pe.gob.osinergmin.pgim.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimEstandarProgramaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimItemProgramaSupeDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubtipoSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemProgramaSupe;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.services.PrgrmSupervisionService;
import pe.gob.osinergmin.pgim.services.UnidadMineraService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la
 * selección de unidades mineras en un programa
 * 
 * @descripción: Unidad minera / Programa
 *
 * @author: lbarrantes
 * @version: 1.0
 * @fecha_de_creación: 30/11/2020
 */
@RestController
@Slf4j
@RequestMapping("/itemprograma")
public class ItemProgramaController extends BaseController {

	@Autowired
	private UnidadMineraService unidadMineraService;

	@Autowired
	private ParametroService parametroService;
    
    @Autowired
    private PrgrmSupervisionService prgrmSupervisionService;
   
    /***
     * Permite obtener la configuración en este caso Situación de UM
     * 
     * @return
     */
    @GetMapping("/obtenerConfiguraciones/{idLineaPrograma}")
    public ResponseEntity<?> obtenerConfiguraciones(@PathVariable Long idLineaPrograma) {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimValorParamDTOSituacion = null;
        List<PgimSubtipoSupervisionDTO> lPgimSubtipoSupervisionDTO = new ArrayList<PgimSubtipoSupervisionDTO>();
        List<PgimValorParametroDTO> lPgimValorParametroDTOTipoSupervision = null;
        
        try {
            lPgimValorParamDTOSituacion = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_SITUACION_UNIDAD_MINERA);
            lPgimValorParametroDTOTipoSupervision = this.parametroService
            		.filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_SUPERVISION, ConstantesUtil.PARAM_TIPO_SUPERVISION_PROGRAMADA);
            
            List<PgimEstandarProgramaDTO> lpgimEstandarProgramaDTO = this.prgrmSupervisionService
    				.listarEstandarPrograma(idLineaPrograma);

			for (PgimEstandarProgramaDTO pgimEstandarProgramaDTO : lpgimEstandarProgramaDTO) {

				if (pgimEstandarProgramaDTO.getDescTipoSupervision().equals("Programada")) {

					if (pgimEstandarProgramaDTO.getCaDiasSupervision() != null
							& pgimEstandarProgramaDTO.getMoPorSupervision() != null) {

						if (pgimEstandarProgramaDTO.getCaDiasSupervision() > 0
								& !pgimEstandarProgramaDTO.getMoPorSupervision().equals(new BigDecimal(0))) {

							PgimSubtipoSupervisionDTO pgimSubtipoSupervisionDTO = new PgimSubtipoSupervisionDTO();
							pgimSubtipoSupervisionDTO
									.setIdSubtipoSupervision(pgimEstandarProgramaDTO.getIdSubtipoSupervision());
							pgimSubtipoSupervisionDTO
									.setDescTipoSupervision(pgimEstandarProgramaDTO.getDescSubtipoMotivo());
							lPgimSubtipoSupervisionDTO.add(pgimSubtipoSupervisionDTO);
						}

					}
				}
			}
            

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());

            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimValorParamDTOSituacion", lPgimValorParamDTOSituacion);
        respuesta.put("lPgimSubtipoSupervisionDTO", lPgimSubtipoSupervisionDTO);
        respuesta.put("lPgimValorParametroDTOTipoSupervision", lPgimValorParametroDTOTipoSupervision);
        
        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
    /***
     * Permite obtener un listado de unidades mineras en el contexto de la
     * paginación de resultados requerida en la lista den el frontend.
     * 
     * @param filtroUnidadMinera Objeto filtro que porta las propiedades que de
     *                           tener valor, representan criterios filtro
     *                           específicos esto siempre que la propiedad esté
     *                           configurada para aplicarse como criterio al momento
     *                           de las consultas.
     * @param paginador          Objeto paginador que tiene la información de la
     *                           página actual, tamaño de la página y criterios de
     *                           ordenamiento.
     * @return
     */
    //@PreAuthorize("hasAnyAuthority('um-lista_AC')")     
    @PostMapping("/filtrar")
    public ResponseEntity<Page<PgimUnidadMineraAuxDTO>> filtrarUnidadesMineras(
            @RequestBody PgimUnidadMineraAuxDTO filtroUnidadMinera, Pageable paginador) {
   	

        Page<PgimUnidadMineraAuxDTO> lPgimUnidadMineraDTO = this.unidadMineraService.filtrarSeleccionablesParaPrograma(filtroUnidadMinera,
                paginador);
        return new ResponseEntity<Page<PgimUnidadMineraAuxDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
    }
    
    /***
     * Permite crear el item de supervisión programa de unidad(es) minera(s).
     * 
     * @param pgimUnidadMineraDTO Portador de los datos para la creación de la
     *                            unidad minera.
     * @param resultadoValidacion Resultado de la validación aplicada a nivel de la
     *                            entidad del modelo de datos.
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAnyAuthority('pr-sp-prog_IN')")
    @PostMapping("/crearItemPrograma")
    public ResponseEntity<?> crearItemPrograma(@Valid @RequestBody PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO,
            BindingResult resultadoValidacion) throws Exception {

    	PgimItemProgramaSupeDTO pgimItemProgramaSupeDTOcreada = null;
        Map<String, Object> respuesta = new HashMap<>();

        if (resultadoValidacion.hasErrors()) {
            List<String> errores = null;

            errores = resultadoValidacion.getFieldErrors().stream()
                    .map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
                    .collect(Collectors.toList());

            respuesta.put("mensaje", "Se han encontrado inconsistencias para crear la Unidad fiscalizable");
            respuesta.put("error", errores.toString());

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
        }

        try {
        	BigDecimal moPorSupervision;
        	List<PgimEstandarProgramaDTO> lpgimEstandarProgramaDTO; 
        	// Grabar
        	String cadenaIdUnidadMinera = pgimItemProgramaSupeDTO.getDescCoUnidadMinera();
        	pgimItemProgramaSupeDTO.setDescCoUnidadMinera(null);
        	if(cadenaIdUnidadMinera.contains("U")) {
    			String[] textSplit = cadenaIdUnidadMinera.split("U");
    			for (int j = 0; j < textSplit.length; j++) {
        			pgimItemProgramaSupeDTO.setIdUnidadMinera(Long.parseLong(textSplit[j]));
                	// Costos por subtipo de supervisión
                    lpgimEstandarProgramaDTO = this.prgrmSupervisionService
            				.listarCostosEstimados(pgimItemProgramaSupeDTO.getIdLineaPrograma(), pgimItemProgramaSupeDTO.getIdSubtipoSupervision());
                    moPorSupervision = lpgimEstandarProgramaDTO.get(0).getMoPorSupervision();
                    pgimItemProgramaSupeDTO.setMoCostoEstimadoSupervision(moPorSupervision);
                	pgimItemProgramaSupeDTOcreada = this.unidadMineraService.crearUnidadMineraPrograma(pgimItemProgramaSupeDTO, this.obtenerAuditoria());
    			}
    		}else {
            	// Costos por subtipo de supervisión
                lpgimEstandarProgramaDTO = this.prgrmSupervisionService
        				.listarCostosEstimados(pgimItemProgramaSupeDTO.getIdLineaPrograma(), pgimItemProgramaSupeDTO.getIdSubtipoSupervision());
                moPorSupervision = lpgimEstandarProgramaDTO.get(0).getMoPorSupervision();
                pgimItemProgramaSupeDTO.setMoCostoEstimadoSupervision(moPorSupervision);
    			pgimItemProgramaSupeDTO.setIdUnidadMinera(Long.parseLong(cadenaIdUnidadMinera));
            	pgimItemProgramaSupeDTOcreada = this.unidadMineraService.crearUnidadMineraPrograma(pgimItemProgramaSupeDTO, this.obtenerAuditoria());
    		}
        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al intentar crear la selección de unidad(es) minera(s)");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Las(s) unidad(es) minera(s) ha(n) sido seleccionadas");
        respuesta.put("pgimUnidadMineraDTO", pgimItemProgramaSupeDTOcreada);

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAnyAuthority('pr-sp-prog_MO')")
	@PutMapping("/modificarItemPrograma")
	public ResponseEntity<?> modificarItemPrograma(@Valid @RequestBody PgimItemProgramaSupeDTO pgimItemProgramaSupeDTO, 
			BindingResult resultadoValidacion)
			throws Exception {
		PgimItemProgramaSupeDTO PgimItemProgramaSupeDTOModificado = null;
		PgimItemProgramaSupe PgimItemProgramaSupeActual = null;
		String mensaje;
		Map<String, Object> respuesta = new HashMap<>();

		if (resultadoValidacion.hasErrors()) {
			List<String> errores = null;

			errores = resultadoValidacion.getFieldErrors().stream()
					.map(err -> String.format("La propiedad '%s' %s", err.getField(), err.getDefaultMessage()))
					.collect(Collectors.toList());

			respuesta.put("mensaje", "Se han encontrado inconsistencias para modificar el item de programación.");
			respuesta.put("error", errores.toString());

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.BAD_REQUEST);
		}

		try {
			PgimItemProgramaSupeActual = this.prgrmSupervisionService
					.getItemProgramaSupeById(pgimItemProgramaSupeDTO.getIdItemProgramaSupe());

			if (PgimItemProgramaSupeActual == null) {
				mensaje = String.format("El estandar programa %s que intenta actualizar no existe en la base de datos",
						pgimItemProgramaSupeDTO.getIdItemProgramaSupe());
				respuesta.put("mensaje", mensaje);

				return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.NOT_FOUND);
			}
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error intentar recuperar los datos del item del programa a actualizar");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {

			PgimItemProgramaSupeDTOModificado = this.prgrmSupervisionService.procesarModificarItemPrograma(pgimItemProgramaSupeDTO, PgimItemProgramaSupeActual, this.obtenerAuditoria());
			
		} catch (DataAccessException e) {
			respuesta.put("mensaje", "Ocurrió un error al intentar modificar el item del programa");
			respuesta.put("error", e.getMostSpecificCause().getMessage());
			log.error(e.getMostSpecificCause().getMessage(), e);

			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		respuesta.put("mensaje", "La cantidad de supervisiones ha sido modificada");
		respuesta.put("PgimItemProgramaSupeDTOModificado", PgimItemProgramaSupeDTOModificado);

		return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.CREATED);
	}
	

	
}
