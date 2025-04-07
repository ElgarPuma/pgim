package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Clase Entidad para la tabla PGIM_VW_MATRIZ_CRITERIO_AUX: 
* @descripción: Vista de matriz criterio
*
* @author: jvalerio
* @version 1.0
* @fecha_de_creación: 09/04/2021
*/

@Entity
@Table(name = "PGIM_VW_MATRIZ_CRITERIO_AUX")
@Data
@NoArgsConstructor
public class PgimMatrizCriterioAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Identificador interno de la vista PGIM_VW_MATRIZ_CRITERIO_AUX: Secuencia:
     * PGIM_SEQ_ABC
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_SEQ_ABC")
    @SequenceGenerator(name = "PGIM_SEQ_ABC", sequenceName = "PGIM_SEQ_ABC", allocationSize = 1)
    @Column(name = "ID_MATRIZ_CRITERIO_AUX", nullable = false)
    private Long idMatrizCriterioAux;

    /*
     * Identificador del criterio de la matriz de supervisión
     */
    @Column(name = "ID_MATRIZ_CRITERIO", nullable = true)
    private Long idMatrizCriterio;

    /*
     * Identificador interno de la Matriz de supervision
     */
    @Column(name = "ID_MATRIZ_SUPERVISION", nullable = true)
    private Long idMatrizSupervision;

    /*
     * Identificador interno de la Matriz de supervision
     */
    @Column(name = "ID_MATRIZ_GRPO_CRTRIO", nullable = true)
    private Long idMatrizGrpoCrtrio;

    /*
     * Código del grupo de criterio de la matriz de supervisión
     */
    @Column(name = "CO_MATRIZ_GRPO_CRTRIO", nullable = true)
    private String coMatrizGrpoCrtrio;

    /*
     * Nombre del grupo de criterio de la matriz de supervisión
     */
    @Column(name = "NO_MATRIZ_GRPO_CRTRIO", nullable = true)
    private String noMatrizGrpoCrtrio;

    /*
     * Orden del grupo de matriz criterio
     */
    @Column(name = "NU_ORDEN_MATRIZ_GRPO_CRTRIO", nullable = true)
    private Long nuOrdenMatrizGrpoCrtrio;

    /*
     * Código del criterio de la matriz de supervisión
     */
    @Column(name = "CO_MATRIZ_CRITERIO", nullable = true)
    private String coMatrizCriterio;

    /*
     * Descripción del criterio de la matriz de supervisión
     */
    @Column(name = "DE_MATRIZ_CRITERIO", nullable = true)
    private String deMatrizCriterio;

    /*
     * Orden de matriz criterio
     */
    @Column(name = "NU_ORDEN_MATRIZ_CRITERIO", nullable = true)
    private Long nuOrdenMatrizCriterio;

    /*
     * Número de obligaciones
     */
    @Column(name = "NU_OBLIGACIONES", nullable = true)
    private Long nuObligaciones;

    /*
     * Nombre de tipificación
     */
    @Column(name = "TIPIFICACION", nullable = true)
    private String tipificacion;

    /*
     * Descripción de base legal
     */
    @Column(name = "DE_BASE_LEGAL", nullable = true)
    private String deBaseLegal;

    /*
     * Norma
     */
    @Column(name = "NORMA", nullable = true)
    private String norma;

    /*
     * vigencia del criterio de supervisión
     */
    @Column(name = "ES_VIGENTE", nullable = true)
    private String esVigente;
}
