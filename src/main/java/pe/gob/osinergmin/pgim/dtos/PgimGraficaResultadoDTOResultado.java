package pe.gob.osinergmin.pgim.dtos;

public class PgimGraficaResultadoDTOResultado extends PgimGraficaResultadoDTO {

	/**
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorEspecialidad
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorTipoFiscalizacion
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorContrato
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorAgenteFiscalizado
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorDivisionSupervisora
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorMotivoFiscalizacion
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorUnidadFiscalizable
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorSubtipoFiscalizacion
	 * @see pe.gob.osinergmin.pgim.models.repository.IndAprobacionInformeRepository#obtenerDataPorEmpresaSupervisora
	 * 
	 * 
	 */
	public PgimGraficaResultadoDTOResultado(String descripcion, Long cantidadEntero
			//,Double cantidadDecimal
			) {
		  super();
	      
		  if(descripcion.length()>45)
			  this.setDescripcion(descripcion.substring(0,40)+"...");
		  else
			  this.setDescripcion(descripcion);
		  
	      this.setCantidadEntero(cantidadEntero);
	      //this.setCantidadDecimal(cantidadDecimal);
	      
    }
	
	
}
