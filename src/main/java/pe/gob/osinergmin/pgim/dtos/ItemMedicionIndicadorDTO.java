package pe.gob.osinergmin.pgim.dtos;

public interface ItemMedicionIndicadorDTO {
	
	/*
	 * ID_ESPECIALIDAD
	 */
	Long getIdEspecialidad();

	/*
	 * NO_ESPECIALIDAD
	 */
	String getNoEspecialidad();

	/*
	 * ID_DIVISION_SUPERVISORA
	 */
	Long getIdDivisionSupervisora();

	/*
	 * NO_DIVISION_SUPERVISORA
	 */
	String getNoDivisionSupervisora();

	/*
	 * ID_UNIDAD_MINERA
	 */
	Long getIdUnidadMinera();

	/*
	 * CO_UNIDAD_MINERA
	 */
	String getCoUnidadMinera();

	/*
	 * NO_UNIDAD_MINERA
	 */
	String getNoUnidadMinera();

	/*
	 * ID_AGENTE_SUPERVISADO
	 */
	Long getIdAgenteSupervisado();

	/*
	 * RUC_AGENTE_SUPERVISADO
	 */
	String getRucAgenteSupervisado();

	/*
	 * NO_AGENTE_SUPERVISADO
	 */
	String getNoAgenteSupervisado();
	
	/*
	 * Cantidad de registros
	 */
	Integer getCaRegistros();

	/*
	 * Máximo de la cantidad de días transcurridos 
	 */
	Double getMaxDiasTranscurridos();
	 
	/*
	 * Mínimo de la cantidad de días transcurridos 
	 */
	Double getMinDiasTranscurridos();
	 
	/*
	 * Promedio de la cantidad de días transcurridos 
	 */
	Double getAvgDiasTranscurridos();

	/*
	 * Máximo de la cantidad de semanas transcurridos 
	 */
	Double getMaxSemanasTranscurridos();
	 
	/*
	 * Mínimo de la cantidad de semanas transcurridos 
	 */
	Double getMinSemanasTranscurridos();
	 
	/*
	 * Promedio de la cantidad de semanas transcurridos 
	 */
	Double getAvgSemanasTranscurridos();

	/*
	 * Máximo de la cantidad de meses transcurridos 
	 */
	Double getMaxMesesTranscurridos();
	 
	/*
	 * Mínimo de la cantidad de meses transcurridos 
	 */
	Double getMinMesesTranscurridos();
	 
	/*
	 * Promedio de la cantidad de meses transcurridos 
	 */
	Double getAvgMesesTranscurridos();

	/*
	 * Máximo de la cantidad de años transcurridos 
	 */
	Double getMaxAniosTranscurridos();
	 
	/*
	 * Mínimo de la cantidad de años transcurridos 
	 */
	Double getMinAniosTranscurridos();
	 
	/*
	 * Promedio de la cantidad de años transcurridos 
	 */
	Double getAvgAniosTranscurridos();

}
