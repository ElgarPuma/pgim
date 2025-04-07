package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @descripción: DTO para la agrupación de entregables listos para asignar liquidación 
*
* @author: PresleyRomero
* @version: 1.0
* @fecha_de_creación: 02/11/2020
*/
@Getter
@Setter
@NoArgsConstructor
public class GrupoEntregableLiquidacionDTO {
	
	private Long idContrato;
	
	private Long idTipoEntregable;
	
	private Long idDivisionSupervisora;

	private Long idTipoSupervision;

	private Long idSubtipoSupervision;

	private Long idMotivoSupervision;

	private String nuContrato;

	private String deTipoEntregable;
	
	private String noDivisionSupervisora;
	
	private String deTipoSupervision;
	
	private String deSubtipoSupervision;

	private String deMotivoSupervision;
	
	private Integer cantEntregables;

	private List<PgimItemConsumoDTO> listaEntregables;

}
