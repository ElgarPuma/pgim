package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionscNotifDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionscNotif;

/** 
 * @descripción: Lógica de negocio de la entidad relación notificacion
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 05/11/2020
 */
@Repository
public interface RelacionNotificacionRepository extends JpaRepository<PgimRelacionscNotif, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionscNotifDTOResultado( "
    + "reno.idRelacionscNotif, reno.subcatDocConstancia.idSubcatDocumento, "
    + "reno.subcatDocNotificable.idSubcatDocumento)" 
    + "FROM PgimRelacionscNotif reno " 
    + "WHERE reno.esRegistro = '1' "
    + "AND reno.subcatDocNotificable.idSubcatDocumento = :idSubcatDocNotificable "
    + "AND reno.tipoNotificacion.idValorParametro = :idTipoNotificacion "
    )
    PgimRelacionscNotifDTO obtenerRelacionNotificacion(
    		@Param("idSubcatDocNotificable") Long idSubcatDocNotificable,
    		@Param("idTipoNotificacion") Long idTipoNotificacion    		
    		);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionscNotifDTOResultado( "
    + "reno.idRelacionscNotif, reno.subcatDocConstancia.idSubcatDocumento, "
    + "reno.subcatDocNotificable.idSubcatDocumento)" 
    + "FROM PgimRelacionscNotif reno " 
    + "WHERE reno.esRegistro = '1' "
    + "AND reno.subcatDocConstancia.idSubcatDocumento = :idSubcatDocumento ")
    List<PgimRelacionscNotifDTO> obtenerRelacionConstNotificable(@Param("idSubcatDocumento") Long idSubcatDocumento);

	
}
