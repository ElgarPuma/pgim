package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimCfgEtiquetaNotifDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimCfgEtiquetaNotif;

/**
 * @descripción: Lógica de negocio de la entidad PGIM_TM_CFG_ETIQUETA_NOTIF
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 09/02/2023
 */
@Repository
public interface CfgEtiquetaNotifRepository extends JpaRepository<PgimCfgEtiquetaNotif, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimCfgEtiquetaNotifDTOResultado( "
        + "etq.idCfgEtiquetaNotif, etq.pgimPasoProceso.idPasoProceso, etq.pgimRelacionPaso.idRelacionPaso ) " 
        + "FROM PgimCfgEtiquetaNotif etq " 
        + "WHERE etq.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
        + "AND etq.esRegistro = '1' "
        )
    List<PgimCfgEtiquetaNotifDTO> lCfgEtiquetaNotifDTOByIdRelacion(@Param("idRelacionPaso") Long idRelacionPaso);




    
}
