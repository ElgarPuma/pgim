package pe.gob.osinergmin.pgim.controllers;

import javax.activation.MimetypesFileTypeMap;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.pgim.dtos.PgimProduccionObtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProgramaInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTitularAuxDTO;
import pe.gob.osinergmin.pgim.services.EstaminService;
import pe.gob.osinergmin.pgim.dtos.PgimCarboniferaDestinoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestRecursoExtAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimDestinoProduccionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstadisticoIncidenteAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimExploDesarrolloAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIdentUnidMineraAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimIndicadorDesempenioAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimInfoHechosImportAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimMineralRecibPlantaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimOtroIndicadorAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrincipalProyectoInversionAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProdCarbonObtenidaAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProduccionNoMetalicaAuxDTO;

/**
 * Controlador para la gestión de las funcionalidades relacionados con los reportes de Estamin.
 * 
 * @descripción: Reporte Estamin.
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 05/08/2020
 */
@RestController
@RequestMapping("/estamin")
public class EstaminController extends BaseController {

	@Autowired
	private EstaminService estaminService;

	// @PreAuthorize("hasAnyAuthority('es-lista_AC')")
	@PostMapping("/ListarProduccionObtenida")
	public ResponseEntity<Page<PgimProduccionObtAuxDTO>> ListarProduccionObtenida(
			@RequestBody PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO, Pageable paginador) throws Exception {

		Page<PgimProduccionObtAuxDTO> lPgimProduccionObtAuxDTO = this.estaminService
				.ListarProduccionObtenida(filtroProduccionObtAuxDTO, paginador);
		return new ResponseEntity<Page<PgimProduccionObtAuxDTO>>(lPgimProduccionObtAuxDTO, HttpStatus.OK);
	}

	@PostMapping("/descargarReporteProduccionObtenida")
	public ResponseEntity<byte[]> descargarReporteProduccionObtenida(
			@Valid @RequestBody PgimProduccionObtAuxDTO filtroProduccionObtAuxDTO) throws Exception {

		byte[] archivo = estaminService.generarReporteProduccionObtenidaEXCEL(filtroProduccionObtAuxDTO);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroProduccionObtAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroProduccionObtAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	// @PreAuthorize("hasAnyAuthority('es-lista_AC')")
	@PostMapping("/ListarDestinoMineralesMetalicos")
	public ResponseEntity<Page<PgimDestRecursoExtAuxDTO>> ListarDestinoMineralesMetalicos(
			@RequestBody PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO, Pageable paginador) throws Exception {

		Page<PgimDestRecursoExtAuxDTO> lPgimDestRecursoExtAuxDTO = this.estaminService
				.ListarDestinoMineralesMetalicos(filtroDestRecursoExtAuxDTO, paginador);
		return new ResponseEntity<Page<PgimDestRecursoExtAuxDTO>>(lPgimDestRecursoExtAuxDTO, HttpStatus.OK);
	}

	@PostMapping("/descargarReporteDestinoMineralesMetalicos")
	public ResponseEntity<byte[]> descargarReporteDestinoMineralesMetalicos(
			@Valid @RequestBody PgimDestRecursoExtAuxDTO filtroDestRecursoExtAuxDTO) throws Exception {

		byte[] archivo = estaminService.generarReporteDestinoMineralesMetalicosEXCEL(filtroDestRecursoExtAuxDTO);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtroDestRecursoExtAuxDTO.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtroDestRecursoExtAuxDTO.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarIndicadoresDesempenioOperativo")
	public ResponseEntity<Page<PgimIndicadorDesempenioAuxDTO>> listarIndicadoresDesempenioOperativo(@RequestBody PgimIndicadorDesempenioAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimIndicadorDesempenioAuxDTO> lPgimIndicadorDesempenioAuxDTO = this.estaminService.listarIndicadoresDesempenioOperativo(filtro, paginador);
		
		return new ResponseEntity<Page<PgimIndicadorDesempenioAuxDTO>>(lPgimIndicadorDesempenioAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteIndicadoresDesempenioOperativo")
	public ResponseEntity<byte[]> descargarReporteIndicadoresDesempenioOperativo(@Valid @RequestBody PgimIndicadorDesempenioAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteIndicadoresDesempenioOperativoEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarProduccionNoMetalica")
	public ResponseEntity<Page<PgimProduccionNoMetalicaAuxDTO>> listarProduccionNoMetalica(@RequestBody PgimProduccionNoMetalicaAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimProduccionNoMetalicaAuxDTO> lPgimProduccionNoMetalicaAuxDTO = this.estaminService.listarProduccionNoMetalica(filtro, paginador);
		
		return new ResponseEntity<Page<PgimProduccionNoMetalicaAuxDTO>>(lPgimProduccionNoMetalicaAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteProduccionNoMetalica")
	public ResponseEntity<byte[]> descargarReporteProduccionNoMetalica(@Valid @RequestBody PgimProduccionNoMetalicaAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteProduccionNoMetalicaEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarOtrosIndicadores")
	public ResponseEntity<Page<PgimOtroIndicadorAuxDTO>> listarOtrosIndicadores(@RequestBody PgimOtroIndicadorAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimOtroIndicadorAuxDTO> lPgimOtroIndicadorAuxDTO = this.estaminService.listarOtrosIndicadores(filtro, paginador);
		
		return new ResponseEntity<Page<PgimOtroIndicadorAuxDTO>>(lPgimOtroIndicadorAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteOtrosIndicadores")
	public ResponseEntity<byte[]> descargarReporteOtrosIndicadores(@Valid @RequestBody PgimOtroIndicadorAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteOtrosIndicadoresEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarEstadisticosIncidente")
	public ResponseEntity<Page<PgimEstadisticoIncidenteAuxDTO>> listarEstadisticosIncidente(@RequestBody PgimEstadisticoIncidenteAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimEstadisticoIncidenteAuxDTO> lPgimEstadisticoIncidenteAuxDTO = this.estaminService.listarEstadisticosIncidente(filtro, paginador);
		
		return new ResponseEntity<Page<PgimEstadisticoIncidenteAuxDTO>>(lPgimEstadisticoIncidenteAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteEstadisticosIncidente")
	public ResponseEntity<byte[]> descargarReporteEstadisticosIncidente(@Valid @RequestBody PgimEstadisticoIncidenteAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteEstadisticosIncidenteEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarReporteProgramaInversiones")
	public ResponseEntity<Page<PgimProgramaInversionAuxDTO>> listarReporteProgramaInversiones(@RequestBody PgimProgramaInversionAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimProgramaInversionAuxDTO> lPgimProgramaInversionAuxDTO = this.estaminService.listarReporteProgramaInversiones(filtro, paginador);
		
		return new ResponseEntity<Page<PgimProgramaInversionAuxDTO>>(lPgimProgramaInversionAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteProgramaInversiones")
	public ResponseEntity<byte[]> descargarReporteProgramaInversiones(@Valid @RequestBody PgimProgramaInversionAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteProgramaInversionesEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarReportePrincipalesProyectosInvers")
	public ResponseEntity<Page<PgimPrincipalProyectoInversionAuxDTO>> listarReportePrincipalesProyectosInvers(@RequestBody PgimPrincipalProyectoInversionAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimPrincipalProyectoInversionAuxDTO> lPgimPrincipalProyectoInversionAuxDTO = this.estaminService.listarReportePrincipalesProyectosInvers(filtro, paginador);
		
		return new ResponseEntity<Page<PgimPrincipalProyectoInversionAuxDTO>>(lPgimPrincipalProyectoInversionAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReportePrincipalesProyectosInvers")
	public ResponseEntity<byte[]> descargarReportePrincipalesProyectosInvers(@Valid @RequestBody PgimPrincipalProyectoInversionAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReportePrincipalesProyectosInversEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarReporteExploDesarrollo")
	public ResponseEntity<Page<PgimExploDesarrolloAuxDTO>> listarReporteExploDesarrollo(@RequestBody PgimExploDesarrolloAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimExploDesarrolloAuxDTO> lPgimExploDesarrolloAuxDTO = this.estaminService.listarExploDesarrollo(filtro, paginador);
		
		return new ResponseEntity<Page<PgimExploDesarrolloAuxDTO>>(lPgimExploDesarrolloAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteExploDesarrolloEXCEL")
	public ResponseEntity<byte[]> descargarReporteExploDesarrolloEXCEL(@Valid @RequestBody PgimExploDesarrolloAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteExploDesarrolloEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarIdentUnidMineras")
	public ResponseEntity<Page<PgimIdentUnidMineraAuxDTO>> listarIdentUnidMineras(@RequestBody PgimIdentUnidMineraAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimIdentUnidMineraAuxDTO> lPgimIdentUnidMineraAuxDTO = this.estaminService.listarIdentUnidMineras(filtro, paginador);
		
		return new ResponseEntity<Page<PgimIdentUnidMineraAuxDTO>>(lPgimIdentUnidMineraAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteIdentUnidMineraEXCEL")
	public ResponseEntity<byte[]> descargarReporteIdentUnidMineraEXCEL(@Valid @RequestBody PgimIdentUnidMineraAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteIdentUnidMineraEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarDestinoProduccion")
	public ResponseEntity<Page<PgimDestinoProduccionAuxDTO>> listarDestinoProduccion(@RequestBody PgimDestinoProduccionAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimDestinoProduccionAuxDTO> lPgimDestinoProduccionAuxDTO = this.estaminService.listarDestinoProduccion(filtro, paginador);
		
		return new ResponseEntity<Page<PgimDestinoProduccionAuxDTO>>(lPgimDestinoProduccionAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteDestinoProduccionEXCEL")
	public ResponseEntity<byte[]> descargarReporteDestinoProduccionEXCEL(@Valid @RequestBody PgimDestinoProduccionAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteDestinoProduccionEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarTitular")
	public ResponseEntity<Page<PgimTitularAuxDTO>> listarTitular(@RequestBody PgimTitularAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimTitularAuxDTO> lPgimTitularAuxDTO = this.estaminService.listarTitular(filtro, paginador);
		
		return new ResponseEntity<Page<PgimTitularAuxDTO>>(lPgimTitularAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteTitularEXCEL")
	public ResponseEntity<byte[]> descargarReporteTitularEXCEL(@Valid @RequestBody PgimTitularAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteTitularEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarMineralRecibPlanta")
	public ResponseEntity<Page<PgimMineralRecibPlantaAuxDTO>> listarMineralRecibPlanta(@RequestBody PgimMineralRecibPlantaAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimMineralRecibPlantaAuxDTO> lPgimMineralRecibPlantaAuxDTO = this.estaminService.listarMineralRecibPlanta(filtro, paginador);
		
		return new ResponseEntity<Page<PgimMineralRecibPlantaAuxDTO>>(lPgimMineralRecibPlantaAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteMineralRecibPlantaEXCEL")
	public ResponseEntity<byte[]> descargarReporteMineralRecibPlantaEXCEL(@Valid @RequestBody PgimMineralRecibPlantaAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteMineralRecibPlantaEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
	
	@PostMapping("/listarInfoHechosImport")
	public ResponseEntity<Page<PgimInfoHechosImportAuxDTO>> listarInfoHechosImport(@RequestBody PgimInfoHechosImportAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimInfoHechosImportAuxDTO> lPgimInfoHechosImportAuxDTO = this.estaminService.listarInfoHechosImport(filtro, paginador);
		
		return new ResponseEntity<Page<PgimInfoHechosImportAuxDTO>>(lPgimInfoHechosImportAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteInfoHechosImportEXCEL")
	public ResponseEntity<byte[]> descargarReporteInfoHechosImportEXCEL(@Valid @RequestBody PgimInfoHechosImportAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteInfoHechosImportEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/listarProdCarbonObtenida")
	public ResponseEntity<Page<PgimProdCarbonObtenidaAuxDTO>> listarProdCarbonObtenida(@RequestBody PgimProdCarbonObtenidaAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimProdCarbonObtenidaAuxDTO> lPgimProdCarbonObtenidaAuxDTO = this.estaminService.listarProdCarbonObtenida(filtro, paginador);
		
		return new ResponseEntity<Page<PgimProdCarbonObtenidaAuxDTO>>(lPgimProdCarbonObtenidaAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteProdCarbonObtenidaEXCEL")
	public ResponseEntity<byte[]> descargarReporteProdCarbonObtenidaEXCEL(@Valid @RequestBody PgimProdCarbonObtenidaAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteProdCarbonObtenidaEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}

	@PostMapping("/listarCarboniferaDestino")
	public ResponseEntity<Page<PgimCarboniferaDestinoAuxDTO>> listarCarboniferaDestino(@RequestBody PgimCarboniferaDestinoAuxDTO filtro, Pageable paginador) throws Exception {

		Page<PgimCarboniferaDestinoAuxDTO> lPgimCarboniferaDestinoAuxDTO = this.estaminService.listarCarboniferaDestino(filtro, paginador);
		
		return new ResponseEntity<Page<PgimCarboniferaDestinoAuxDTO>>(lPgimCarboniferaDestinoAuxDTO, HttpStatus.OK);
	}
	
	@PostMapping("/descargarReporteCarboniferaDestinoEXCEL")
	public ResponseEntity<byte[]> descargarReporteCarboniferaDestinoEXCEL(@Valid @RequestBody PgimCarboniferaDestinoAuxDTO filtro) throws Exception {

		byte[] archivo = estaminService.generarReporteCarboniferaDestinoEXCEL(filtro);

		HttpHeaders headers = new HttpHeaders();

		MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
		String mimeType = fileTypeMap.getContentType(filtro.getDescNoArchivo());
		headers.setContentType(MediaType.valueOf(mimeType));
		headers.set("NombreArchivo", filtro.getDescNoArchivo());
		headers.setContentLength(archivo.length);

		ResponseEntity<byte[]> respuestaArchivo = new ResponseEntity<byte[]>(archivo, headers, HttpStatus.OK);

		return respuestaArchivo;
	}
}
