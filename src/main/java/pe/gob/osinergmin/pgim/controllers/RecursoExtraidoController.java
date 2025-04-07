package pe.gob.osinergmin.pgim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.pgim.dtos.PgimRecursoExtraidoAuxDTO;
import pe.gob.osinergmin.pgim.services.RecursoExtraidoService;
/**
 * @descripción: Controlador para recurso extraido (minerales metálicos)
 *
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 21/12/2022
 */
@RestController
@RequestMapping("/recursoExtraido")
public class RecursoExtraidoController {
    
    @Autowired
	private RecursoExtraidoService recursoExtraidoService;

    /**
     * Permite obtener la lista preparada de demarcaciones mineras usado en reporte correspondiente
     * @param filtroAgenteSupervisado
     * @param paginador
     * @return
     */
    @PostMapping("/listarRecursosExtraidosPaginado")
    public ResponseEntity<Page<PgimRecursoExtraidoAuxDTO>> listarRecursosExtraidosPaginado(
		@RequestBody PgimRecursoExtraidoAuxDTO filtro, Pageable paginador) {

        Page<PgimRecursoExtraidoAuxDTO> lPgimUnidadMineraDTO = this.recursoExtraidoService.listarRecursosExtraidosPaginado(filtro, paginador);
        
        return new ResponseEntity<Page<PgimRecursoExtraidoAuxDTO>>(lPgimUnidadMineraDTO, HttpStatus.OK);
	}
}
