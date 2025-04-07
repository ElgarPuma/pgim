package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;

import java.util.List;

/**
 * Ésta interface ProcesoRepository incluye el metodo que permite aplicar la lista de procesos.
 * 
 * @descripción: Logica de negocio de la entidad Proceso
 * 
 * @author: barrantesluis
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface ProcesoRepository extends JpaRepository<PgimProceso, Long>{
	
	@Query("select u from PgimProceso u")
	public List<PgimProceso> listarProcesos(String nombre);//elozano metodo no se utiliza

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProcesoDTOResultado( "
            + "proc.idProceso, proc.noProceso ) " 
            + "FROM PgimProceso proc "
            + "WHERE proc.esRegistro = '1' "
            + "AND proc.idProceso IN (2,4,9,10,11,12) "
            + "ORDER BY proc.noProceso desc"
            )
    List<PgimProcesoDTO> listarProcesos();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProcesoDTOResultado( "
            + "proc.idProceso, proc.noProceso ) " 
            + "FROM PgimProceso proc "
            + "WHERE proc.esRegistro = '1' "
            + "AND proc.flIndicador = '1' "
            + "ORDER BY proc.noProceso desc"
            )
    List<PgimProcesoDTO> listarProcesosForIndicar();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProcesoDTOResultado( "
            + "proc.idProceso, proc.noProceso, proc.deProceso, proc.coProcesoSiged ) " 
            + "FROM PgimProceso proc "
            + "WHERE proc.esRegistro = '1' "
            )
    List<PgimProcesoDTO> listarProceso(Sort sort);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimProcesoDTOResultado( "
            + "proc.idProceso, proc.noProceso, proc.deProceso, proc.coProcesoSiged, " 
            + "proc.pgimSubsector.pgimSector.idSector, proc.pgimSubsector.idSubsector, proc.flIndicador ) " 
            + "FROM PgimProceso proc "
            + "LEFT JOIN proc.pgimSubsector ssct "
            + "WHERE proc.esRegistro = '1' "
            + "AND proc.idProceso =:idProceso "
        )
    PgimProcesoDTO obtenerProcesoPorId(@Param("idProceso") Long idProceso);
}

