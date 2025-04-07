package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimItemTipificacion;

/**
 * @descripción: Lógica de negocio de la entidad Item de tipificación de norma legal
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 14/04/2021
 */
public interface ItemTipificacionRepository extends JpaRepository<PgimItemTipificacion, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTOResultado( " 
            + "pit.idItemTipificacion, pit.pgimNorma.idNorma, " 
            + "pit.coTipificacion, pit.noItemTipificacion, pit.deSancionPecuniariaUit, pitaux.nuObligaciones, pit.deBaseLegal, pitaux.nuOrden "
            + " ) "
            + "FROM PgimItemTipificacionAux pitaux, PgimItemTipificacion pit " 
            + "WHERE pitaux.pgimItemTipificacion.idItemTipificacion = pit.idItemTipificacion " 
            + "AND pit.pgimNorma.idNorma = :idNorma "
            )
    Page<PgimItemTipificacionDTO> listarItemTipificacion(@Param("idNorma") Long idNorma, Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTOResultado(" 
            + "pit.idItemTipificacion, pit.pgimNorma.idNorma, " 
            + "pit.coTipificacion, pit.noItemTipificacion, pit.deSancionPecuniariaUit, pit.esVigente, pit.deBaseLegal, pit.nuOrden "
            + ") "
            + "FROM PgimItemTipificacion pit " 
            + "WHERE pit.esRegistro = '1' "
            + "AND pit.idItemTipificacion = :idItemTipificacion "
            )
    PgimItemTipificacionDTO obtenerItemTipificacionPorId(@Param("idItemTipificacion") Long idItemTipificacion);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimItemTipificacionDTOResultado( " 
            + "pit.idItemTipificacion, pit.pgimNorma.idNorma, " 
            + "pit.coTipificacion, pit.noItemTipificacion, pit.deSancionPecuniariaUit, pitaux.nuObligaciones, pit.deBaseLegal, pitaux.nuOrden "
            + " ) "
            + "FROM PgimItemTipificacionAux pitaux, PgimItemTipificacion pit " 
            + "WHERE pitaux.pgimItemTipificacion.idItemTipificacion = pit.idItemTipificacion " 
            + "AND pit.pgimNorma.idNorma = :idNorma "
            )
    List<PgimItemTipificacionDTO> listarItemTipificacionReporte(@Param("idNorma") Long idNorma, Sort sort);
}
