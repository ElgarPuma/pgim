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

import pe.gob.osinergmin.pgim.dtos.PgimRelacionPasoDTO;
import pe.gob.osinergmin.pgim.services.RelacionPasoService;

/**
 * Controlador para la gestión de las funcionalidades relacionadas a la
 * relación paso del proceso.
 * 
 * @descripción: Relación paso del proceso
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 20/12/2023
 */
@RestController
@RequestMapping("/relacionPasos")
public class RelacionPasoController extends BaseController {

    @Autowired
    RelacionPasoService relacionPasoService;

    @PreAuthorize("hasAnyAuthority('cfg014_AC')")
    @PostMapping("/listarRelacionPasoPorIdProceso")
    public ResponseEntity<Page<PgimRelacionPasoDTO>> listarRelacionPasoPorIdProceso(@RequestBody Long idProceso, Pageable paginador)
            throws Exception {

        Page<PgimRelacionPasoDTO> pPgimRelacionPasoDTO = this.relacionPasoService.listarRelacionPasoPorIdProceso(idProceso,
                paginador);

        return new ResponseEntity<Page<PgimRelacionPasoDTO>>(pPgimRelacionPasoDTO, HttpStatus.OK);
    }
}
