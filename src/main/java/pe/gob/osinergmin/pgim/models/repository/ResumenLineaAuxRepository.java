package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimResumenLineaAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimResumenLineaAux;

/**
 * Este repositorio permite gestionar la iteración de la PGIM con su esquema de
 * base de datos en el marco del resumen de la línea base del programa de
 * supervisión.
 * 
 * @descripción: Métodos específicos y personalizados para la obtención de la
 *               información del resumen de la línea base del programa de
 *               supervisión.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 01/06/2020
 * @fecha_de_ultima_actualización:
 */
@Repository
public interface ResumenLineaAuxRepository extends JpaRepository<PgimResumenLineaAux, Long> {

    /**
     * Permite encontrar el resumen del programa de supervisión dado su línea base.
     * @param idLineaProgramaAux
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimResumenLineaAuxDTOResultado( "
            + "reli.idLineaProgramaAux, reli.pgimLineaPrograma.idLineaPrograma, reli.pgimUnidadMinera.idUnidadMinera, "
            + "reli.coUnidadMinera, reli.noUnidadMinera, "
            + "reli.enero, reli.febrero, reli.marzo, "
            + "reli.abril, reli.mayo, reli.junio, "
            + "reli.julio, reli.agosto, reli.septiembre, "
            + "reli.octubre, reli.noviembre, reli.diciembre, "
            + "reli.eneroOtros, reli.febreroOtros, reli.marzoOtros, "
            + "reli.abrilOtros, reli.mayoOtros, reli.junioOtros, "
            + "reli.julioOtros, reli.agostoOtros, reli.septiembreOtros, "
            + "reli.octubreOtros, reli.noviembreOtros, reli.diciembreOtros "
            + ") "
            + "FROM PgimResumenLineaAux reli "
            + "WHERE reli.idLineaProgramaAux = :idLineaProgramaAux")
    List<PgimResumenLineaAuxDTO> obtenerListaResumenPrograma(@Param("idLineaProgramaAux") Long idLineaProgramaAux);


}