package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDocEtiquetaNotif;

/**
 *  
 * @descripción: Logica de negocio de la entidad PgimDocEtiquetaNotif
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 07/02/2023
 * 
 */
@Repository
public interface DocEtiquetaNotifRepository extends JpaRepository<PgimDocEtiquetaNotif, Long>{

    
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTOResultado( "
        + "etiq.idDocEtiquetaNotif, etiq.documentoEtiquetado.idDocumento, etiq.instanciaPaso.idInstanciaPaso, "
        + "etiq.flEtiquetaNotifActiva ) "
        + "FROM PgimDocEtiquetaNotif etiq "
        + "WHERE etiq.esRegistro = '1' "
        + "AND etiq.documentoEtiquetado.idDocumento = :idDocumento "
        + "AND etiq.instanciaPaso.idInstanciaPaso = :idInstanciaPasoActual "
        )
    List<PgimDocEtiquetaNotifDTO> listarPgimDocEtiquetaNotifByDocAndPaso(@Param("idDocumento") Long idDocumento, @Param("idInstanciaPasoActual") Long idInstanciaPasoActual);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTOResultado( "
        + "etiq.idDocEtiquetaNotif, etiq.documentoEtiquetado.idDocumento, etiq.instanciaPaso.idInstanciaPaso, "
        + "etiq.flEtiquetaNotifActiva ) "
        + "FROM PgimDocEtiquetaNotif etiq "
        + "WHERE etiq.esRegistro = '1' "
        + "AND etiq.idDocEtiquetaNotif = :idDocEtiquetaNotif "
        )
    PgimDocEtiquetaNotifDTO pgimDocEtiquetaNotifById(@Param("idDocEtiquetaNotif") Long idDocEtiquetaNotif);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDocEtiquetaNotifDTOResultado( "
        + "etiq.idDocEtiquetaNotif, etiq.documentoEtiquetado.idDocumento, etiq.instanciaPaso.idInstanciaPaso, "
        + "etiq.flEtiquetaNotifActiva) "
        + "FROM PgimDocEtiquetaNotif etiq "
        + "INNER JOIN etiq.instanciaPaso inpa "
        + "WHERE etiq.esRegistro = '1' AND etiq.flEtiquetaNotifActiva = '1' "
        + "AND inpa.pasoProcesoDestino.idPasoProceso = :idPasoProceso "
        )
    List<PgimDocEtiquetaNotifDTO> listarPgimDocEtiquetadosByInstaPaso(@Param("idPasoProceso") Long idPasoProceso);


        
}
