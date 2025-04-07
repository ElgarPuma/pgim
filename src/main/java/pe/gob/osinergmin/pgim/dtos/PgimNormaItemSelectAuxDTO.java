package pe.gob.osinergmin.pgim.dtos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
* DTO para la entidad PGIM_TM_NORMA_OBLIGACION: 
* Obligación normativa
* Obtengo el listado de los item de norma seleccionado para su crud de tabla PGIM_TM_NORMA_OBLIGACION
* @author: palominovega
* @version: 1.0
* @fecha_de_creación: 03/06/2021
*/


@Getter
@Setter
@NoArgsConstructor
public class PgimNormaItemSelectAuxDTO {
	
  /*
  *Listado de NormaObligacion
  */
  private List<PgimNormaObligacionDTO> auxListaNormaObligacion;

}
