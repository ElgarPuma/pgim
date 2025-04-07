package pe.gob.osinergmin.pgim.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.services.PasoProcesoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a los
 * pasos del proceso.
 * 
 * @descripción: Pasos del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 19/12/2023
 */
@RestController
@RequestMapping("/pasoProcesos")
public class PasoProcesoController extends BaseController {
    
    @Autowired
    PasoProcesoService pasoProcesoService;

    @PreAuthorize("hasAnyAuthority('cfg013_AC')")
    @PostMapping("/listarPasosProceso")
    public ResponseEntity<Page<PgimPasoProcesoDTO>> listarPasosProceso(@RequestBody Long idProceso, Pageable paginador) throws Exception {

        Page<PgimPasoProcesoDTO> pPgimPasoProcesoDTO = this.pasoProcesoService.listarPasosPorIdProceso(idProceso, paginador);

        return new ResponseEntity<Page<PgimPasoProcesoDTO>>(pPgimPasoProcesoDTO, HttpStatus.OK);
    }
}
