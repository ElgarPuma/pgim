package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CabeceraReporteHistoricoUmDTO {

	private Long idUnidadMinera;
	
	private String coUnidadMinera;
	
	private String noUnidadMinera;
	
	private String TipoUnidadMinera;
		
	private String asCoDocumentoIdentidad;

	private String asNoRazonSocial;
	
}
