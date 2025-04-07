package pe.gob.osinergmin.pgim.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* DTO para la entidad PGIM_VW_MATRIZ_CRITERIO_AUX: 
* @descripción: Vista para la lista de matriz de criterio de supervisión
*
* @author: jvalerio
* @version: 1.0
* @fecha_de_creación: 09/04/2021
*/
@Getter
@Setter
@NoArgsConstructor
public class PgimMatrizCriterioAuxDTO {
    /*
     * Identificador interno de la vista PGIM_VW_MATRIZ_CRITERIO_AUX: Secuencia:
     * PGIM_SEQ_ABC
     */
    private Long idMatrizCriterioAux;

    /*
     * Identificador del criterio de la matriz de supervisión
     */
    private Long idMatrizCriterio;

    /*
     * Identificador interno de la Matriz de supervision
     */
    private Long idMatrizSupervision;

    /*
     * Identificador interno de la Matriz de supervision
     */
    private Long idMatrizGrpoCrtrio;

    /*
     * Código del grupo de criterio de la matriz de supervisión
     */
    private String coMatrizGrpoCrtrio;

    /*
     * Nombre del grupo de criterio de la matriz de supervisión
     */
    private Long nuOrdenMatrizGrpoCrtrio;

    /*
     * Nombre del grupo de criterio de la matriz de supervisión
     */
    private String noMatrizGrpoCrtrio;

    /*
     * Código del criterio de la matriz de supervisión
     */
    private String coMatrizCriterio;

    /*
     * Descripción del criterio de la matriz de supervisión
     */
    private String deMatrizCriterio;

    /*
     * Descripción del criterio de la matriz de supervisión
     */
    private Long nuOrdenMatrizCriterio;

    /*
     * Número de obligaciones
     */
    private Long nuObligaciones;

    /*
     * Descripción de base legal
     */
    private String deBaseLegal;

    /*
     * Norma
     */
    private String norma;

    /*
    * Nombre de tipificación
    */
    private String tipificacion;

    /*
     * vigencia del criterio de supervisión
     */
    private String esVigente;
}
