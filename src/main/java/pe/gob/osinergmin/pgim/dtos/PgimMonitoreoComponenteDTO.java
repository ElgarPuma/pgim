package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PgimMonitoreoComponenteDTO {

	  /*
	   *Identificador interno del monitoreo Secuencia: PGIM_SEQ_MONITOREO_COMPONENTE
	   */
	   private Long idMonitoreoComponente;

	   /*
	   *Identificador interno del componente minero sobre el que se realizará el monitoreo: Tabla padre: PGIM_TM_COMPONENTE_MINERO
	   */
	   private Long idComponenteMinero;

	   /*
	   *Fecha del monitoreo del componente minero
	   */
	   private String feMonitoreo;

	   /*
	   *Distancia entre el espejo de agua del depósito de relave al dique
	   */
	   private Double distancia;

	   /*
	   *Porcentaje del espejo de agua del depósito de relave
	   */
	   private Double porcentajeAgua;
	   
	   /*
	    *Nivel de Aceptacion del Componente Minero
	   */
	   private Long nivelAceptacion;


}
