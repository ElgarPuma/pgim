package pe.gob.osinergmin.pgim.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDemarcacionUmineraDTOResultado;
import pe.gob.osinergmin.pgim.dtos.PgimUbigeoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadMineraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.dtos.ResponseDTO;
import pe.gob.osinergmin.pgim.exception.NotFoundException;
import pe.gob.osinergmin.pgim.services.DemarcacionUMineraService;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Controlador del componente demarcacion de unidad minera, en el portal se encuentra en la ruta info maestra/unidad minera/ubigeo,
 * este componente contiene los metodos necesarios para el crud y validaciones respectivas.
 * contiene los siguientes metodos:
 * registrar
 * obtener
 * listarUbigeoPorNombre
 * listarUbigeo
 * modificarUMineraUbicacion
 * listarDemarcacionesPorUm
 * eliminar
 * existeDemarcacionUMinera
 * excedeLimiteDemarcacionUMinera
 * 
 * @descripción: Demarcacion unidad minera
 *
 * @author: hruiz
 * @version: 1.0
 * @fecha_de_creación: 17/07/2020
 *
 */
@RestController
@Slf4j
@RequestMapping("/demarcacionuminera")
public class DemarcacionUMineraController extends BaseController{


	@Autowired
	private DemarcacionUMineraService demarcacionUMineraService;
	
    @Autowired
    private ParametroService parametroService;

	/**
	 * Permite registrar una demarcación.
	 * 
	 * @param demarcacionUmineraDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-demarca_IN')")
	@PostMapping
	public ResponseEntity<ResponseDTO> registrar(@RequestBody PgimDemarcacionUmineraDTO demarcacionUmineraDTO)
			throws Exception {

		log.info("Entrando a registrar demarcacion uminera======");

		Long rpta = demarcacionUMineraService.registrarDemarcacionUMinera(demarcacionUmineraDTO, this.obtenerAuditoria());		

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"Empresa supervisora registrado correctamente", rpta));

	}
	
	/**
	 * Permite obtener una demarcación, es usado en el modo edicion y consulta del frontend.
	 * @param idDemarcacion
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('um-demarca_CO')")
	@GetMapping("/{id}")
	public ResponseEntity<PgimDemarcacionUmineraDTO> obtener(@PathVariable("id") Long idDemarcacion) {
		PgimDemarcacionUmineraDTO dem = demarcacionUMineraService.obtenerDemarcacionUMinera(idDemarcacion);
		if(dem.getIdDemarcacionUm() == null) {
			throw new NotFoundException("ID NO ENCONTRADO: " + idDemarcacion);
		}
		return new ResponseEntity<PgimDemarcacionUmineraDTO>(dem, HttpStatus.OK);
	}
	
	/**
	 * Permite obtener una lista de ubigeos por el nombre.
	 * @param noUbigeo
	 * @return
	 */
	@GetMapping("/ubigeo/{nombre}")
	public ResponseEntity<List<PgimUbigeoDTO>> listarUbigeoPorNombre(@PathVariable("nombre") String noUbigeo) {
		return ResponseEntity.ok(demarcacionUMineraService.listarUbigeoPorNombre(noUbigeo));
	}
	
	/**
	 * Permite obtener la lista de ubigeos.
	 * @return
	 */
	@GetMapping("/ubigeo")
	public ResponseEntity<List<PgimUbigeoDTO>> listarUbigeo() {
		return ResponseEntity.ok(demarcacionUMineraService.listarUbigeo());
	}
	

	/**
	 * Permite modificar el campo deUbicacionAcceso de la unidad minera.
	 * 
	 * @param unidadMineraDTO
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-lista_MO')")
	@PostMapping("modificar/uminera/ubicacion")
	public ResponseEntity<ResponseDTO> modificarUMineraUbicacion(@RequestBody PgimUnidadMineraDTO unidadMineraDTO)
			throws Exception {

		Long rpta = demarcacionUMineraService.modificarUnidadMineraUbicacion(unidadMineraDTO, this.obtenerAuditoria());		

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"Información de acceso a la unidad fiscalizable modificada correctamente", rpta));
	}

	@PreAuthorize("hasAnyAuthority('um-demarca_AC')")
	@GetMapping("/listar/{id}")
	public ResponseEntity<List<PgimDemarcacionUmineraDTOResultado>> listarDemarcacionesPorUm(@PathVariable("id") Long idDemarcacion) {
		return ResponseEntity.ok(demarcacionUMineraService.listarDemarcacionesPorUm(idDemarcacion));
	}
	
	/**
	 * Permite eliminar la demarcación.
	 * 
	 * @param idDemarcacionUMinera
	 * @return
	 * @throws Exception
	 */
	@PreAuthorize("hasAnyAuthority('um-demarca_EL')")
	@PostMapping("/eliminar/idDemarcacionUMinera/{id}")
	public ResponseEntity<ResponseDTO> eliminar(@PathVariable("id") Long idDemarcacionUMinera) throws Exception {
		Long rpta = demarcacionUMineraService.eliminarDemarcacionUMinera(idDemarcacionUMinera, this.obtenerAuditoria());	
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("success",
				"Demarcación unidad fiscalizable eliminado correctamente", rpta));
	}
	
	/**
	 * Permite validar si ya existe la demarcación ingresada para la unidad minera seleccionada.
	 * @param idUnidadMinera
	 * @param idUbigeo
	 * @param idDemarcacionUm
	 * @return
	 */
	@GetMapping("/existeDemarcacionUMinera/{idUnidadMinera}/{idUbigeo}/{idDemarcacionUm}")
    public ResponseEntity<ResponseDTO> existeDemarcacionUMinera(@PathVariable Long idUnidadMinera,
            @PathVariable Long idUbigeo, @PathVariable Long idDemarcacionUm) {
		String mensaje = "";
		Number existe = 0;
		
		if(idDemarcacionUm == 0) {
			idDemarcacionUm = null;
		}
		log.info("###ingreso a validar existeDemarcacionUMinera");
        List<PgimDemarcacionUmineraDTO> lDemarcacionUmineraDTO = null;

        try {
        	lDemarcacionUmineraDTO = this.demarcacionUMineraService.existeDemarcacionUMinera(idUnidadMinera, idUbigeo, idDemarcacionUm);
        	if(lDemarcacionUmineraDTO != null) {
        		existe = lDemarcacionUmineraDTO.size();
        	}
        	mensaje = "Si fue posible determinar la existencia o no de la demarcación";
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al realizar verificar si ya exite una demarcación";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error",
            		mensaje, existe));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success",
        		mensaje, existe));
	}
	
	/**
	 * Permite obtener la suma del valor del porcentaje para la unidad minera seleccionada.
	 * @param idUnidadMinera
	 * @param idDemarcacionUm
	 * @return
	 */
	@GetMapping("/excedeLimiteDemarcacionUMinera/{idUnidadMinera}/{idDemarcacionUm}")
    public ResponseEntity<ResponseDTO> excedeLimiteDemarcacionUMinera(@PathVariable Long idUnidadMinera, 
    		@PathVariable Long idDemarcacionUm) {
		String mensaje = "";
		Number existe = 0;
		
		if(idDemarcacionUm == 0) {
			idDemarcacionUm = null;
		}
		
        PgimDemarcacionUmineraDTO demarcacionUmineraDTO = null;

        try {
        	demarcacionUmineraDTO = this.demarcacionUMineraService.excedeLimiteDemarcacionUMinera(idUnidadMinera, idDemarcacionUm);
        	if(demarcacionUmineraDTO != null && demarcacionUmineraDTO.getPcUbigeo() != null) {
        		existe = demarcacionUmineraDTO.getPcUbigeo();
        	}
        	mensaje = "Si fue posible obtener el valor del porcentaje por unidad fiscalizable";
        } catch (DataAccessException e) {
            mensaje = "Ocurrió un error al verificar el valor del porcentaje por unidad fiscalizable";
            log.error(e.getMostSpecificCause().getMessage(), e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("error",
            		mensaje, existe));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("success",
        		mensaje, existe));
    }
	
	/**
	 * Permite obtener las configuraciones necesarias que se usaran en el frontend
	 * @return
	 */
	@GetMapping("/obtenerConfiguraciones")
    public ResponseEntity<?> obtenerConfiguraciones() {

        Map<String, Object> respuesta = new HashMap<>();

        List<PgimValorParametroDTO> lPgimDivisionDTO = null;
        List<PgimValorParametroDTO> lPgimValorParamDTOTipoUM = null;
        
        try {
            lPgimDivisionDTO = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_DIVISION_SUPERVISORA);

            lPgimValorParamDTOTipoUM = this.parametroService
                    .filtrarPorNombreParametro(ConstantesUtil.PARAM_TIPO_UNIDAD_MINERA);

        } catch (DataAccessException e) {
            respuesta.put("mensaje", "Ocurrió un error al realizar la consulta de los parámetros");
            respuesta.put("error", e.getMostSpecificCause().getMessage());
            log.error(e.getMostSpecificCause().getMessage(), e);

            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        respuesta.put("mensaje", "Se obtuvieron todas las configuraciones");
        respuesta.put("lPgimDivisionDTO", lPgimDivisionDTO);
        respuesta.put("lPgimValorParamDTOTipoUM", lPgimValorParamDTOTipoUM);
        

        return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
    }
	
    /**
     * Permite obtener la lista preparada de demarcaciones mineras usado en reporte correspondiente
     * @param filtroAgenteSupervisado
     * @param paginador
     * @return
     */
    @PostMapping("/listarDemarcacionesUmReportePaginado")
    public ResponseEntity<Page<PgimDemarcacionAuxDTO>> listarReporteDemarcacionesUMineroPaginado(
		@RequestBody PgimDemarcacionAuxDTO filtro, Pageable paginador) {

        Page<PgimDemarcacionAuxDTO> lPgimUnidadMineraDTO = this.demarcacionUMineraService.listarReporteDemarcacionesUMineraPaginado(filtro, paginador);
        
        return new ResponseEntity<Page<PgimDemarcacionAuxDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
	}
}
