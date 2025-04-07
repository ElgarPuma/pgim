package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @descripción: Filtro documento
*
* @author: hdiaz
* @version: 1.0
* @fecha_de_creación: 24/02/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimFiltroDocumentoDTO {

    private PgimFiltroItemDocumentoDTO[] pgimFiltroItemDocumento;
}
