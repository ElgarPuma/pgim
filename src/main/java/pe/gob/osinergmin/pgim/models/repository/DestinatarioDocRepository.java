package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDestinatarioDoc;

/**
 * @descripción: Lógica de negocio de la entidad Destinatario documento
 * 
 * @author jvalerio
 * @version: 1.0
 * @fecha_de_creación: 27/04/2023
 * @fecha_de_ultima_actualización: 27/04/2023
 *
 */
public interface DestinatarioDocRepository extends JpaRepository<PgimDestinatarioDoc, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, doc.idDocumento, docc.idDocumento, "
			+ "ddoc.pgimPersona.idPersona, doc.pgimSubcategoriaDoc.noSubcatDocumento, sdoc.noSubcatDocumento, ddoc.pgimPersona.noRazonSocial "
            + ") "
            + "FROM PgimDestinatarioDoc ddoc "
            + "INNER JOIN ddoc.pgimDocumento doc "
            + "LEFT OUTER JOIN ddoc.documentoConstancia docc "
            + "LEFT OUTER JOIN docc.pgimSubcategoriaDoc sdoc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND doc.esRegistro = '1' "
            + "AND doc.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
            + "AND doc.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
            )
	List<PgimDestinatarioDocDTO> listarDestinatarioDoc(@Param("idSubcatDocumento") Long idSubcatDocumento, @Param("idInstanciaProceso") Long idInstanciaProceso);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, doc.idDocumento, docc.idDocumento, "
			+ "per.idPersona, subdoc.noSubcatDocumento, sdoc.noSubcatDocumento, per.noRazonSocial, per.coDocumentoIdentidad "
            + ") "
            + "FROM PgimDestinatarioDoc ddoc "
            + "INNER JOIN ddoc.pgimPersona per "
            + "INNER JOIN ddoc.pgimDocumento doc "
            + "INNER JOIN doc.pgimSubcategoriaDoc subdoc "
            + "LEFT OUTER JOIN ddoc.documentoConstancia docc "
            + "LEFT OUTER JOIN docc.pgimSubcategoriaDoc sdoc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND doc.esRegistro = '1' "
            + "AND doc.idDocumento = :idDocumento "
            )
	List<PgimDestinatarioDocDTO> listarDestinatarioDocPorIdDocumento(@Param("idDocumento") Long idDocumento);
   
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, ddoc.pgimDocumento.idDocumento, ddoc.documentoConstancia.idDocumento, "
			+ "ddoc.pgimPersona.idPersona ) "
            + "FROM PgimDestinatarioDoc ddoc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND ddoc.pgimDocumento.idDocumento = :idDocumento "
            )
    List<PgimDestinatarioDocDTO> listaDestinatarioPorDoc(@Param("idDocumento") Long idDocumento );


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, ddoc.pgimDocumento.idDocumento, ddoc.documentoConstancia.idDocumento, "
			+ "ddoc.pgimPersona.idPersona ) "
            + "FROM PgimDestinatarioDoc ddoc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND ddoc.idDestinatarioDoc = :idDestinatarioDoc "
            )
	PgimDestinatarioDocDTO obtenerDestinatarioDoc(
        @Param("idDestinatarioDoc") Long idDestinatarioDoc
        );
  
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
        + "ddoc.idDestinatarioDoc, ddoc.pgimDocumento.idDocumento, ddoc.documentoConstancia.idDocumento, "
        + "ddoc.pgimPersona.idPersona ) "
        + "FROM PgimDestinatarioDoc ddoc "
        + "WHERE ddoc.esRegistro = '1' "
        + "AND ddoc.pgimDocumento.idDocumento = :idDocumento "
        + "AND ddoc.pgimPersona.idPersona = :idPersona "
        )
    PgimDestinatarioDocDTO obtenerDestinatarioPorIdDocYper(
        @Param("idDocumento") Long idDocumento,
        @Param("idPersona") Long idPersona );
  
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, doc.idDocumento, docc.idDocumento, "
			+ "ddoc.pgimPersona.idPersona, ddoc.pgimPersona.noRazonSocial, ddoc.pgimPersona.coDocumentoIdentidad "
            + ") "
            + "FROM PgimDestinatarioDoc ddoc "
            + "INNER JOIN ddoc.pgimDocumento doc "
            + "LEFT OUTER JOIN ddoc.documentoConstancia docc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND doc.esRegistro = '1' "
            + "AND doc.idDocumento = :idDocumento "
            )
	List<PgimDestinatarioDocDTO> listarDestinatarioPorIdDoc(@Param("idDocumento") Long idDocumento);
   
  
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "ddoc.idDestinatarioDoc, ddoc.pgimDocumento.idDocumento, ddoc.documentoConstancia.idDocumento, "
			+ "ddoc.pgimPersona.idPersona ) "
            + "FROM PgimDestinatarioDoc ddoc "
            + "WHERE ddoc.esRegistro = '1' "
            + "AND ddoc.documentoConstancia.idDocumento = :idDocumentoConstancia "
            )
	PgimDestinatarioDocDTO obtenerDestinatarioDocNotificable(@Param("idDocumentoConstancia") Long idDocumentoConstancia);
            
    /**
     * Permite ver si un destinatario pre-definido aún no ha sido notificado
     * 
     * @param idInstanciaProceso		Id de la instancia de proceso en cuestión
     * @param idPersona					Id de la persona destinataria que se verificará
     * @param idSubcategoriaConstancia	Id de la subcategoría documental de constancia o cargo de notificación que se verificará.
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDestinatarioDocDTOResultado( "
			+ "dest.idDestinatarioDoc, dest.pgimDocumento.idDocumento, dest.documentoConstancia.idDocumento, "
			+ "dest.pgimPersona.idPersona, dest.pgimPersona.noRazonSocial, dest.pgimPersona.coDocumentoIdentidad  "
			+ ") "
            + "FROM PgimRelacionscNotif rnot "
            + "INNER JOIN PgimDocumento doc ON (doc.pgimSubcategoriaDoc.idSubcatDocumento = rnot.subcatDocNotificable.idSubcatDocumento) "
            + "INNER JOIN PgimDestinatarioDoc dest ON (dest.pgimDocumento.idDocumento = doc.idDocumento)"
            + "WHERE 1=1 "
            + "AND rnot.esRegistro = '1' "
            + "AND doc.esRegistro = '1' "
            + "AND dest.esRegistro = '1' "
            + "AND rnot.subcatDocConstancia.idSubcatDocumento = :idSubcategoriaConstancia "
            + "AND dest.documentoConstancia.idDocumento IS NULL " //los que aún no han sido notificados
            + "AND dest.pgimPersona.idPersona = :idPersona "
            + "AND doc.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "            
            )
	List<PgimDestinatarioDocDTO> listarDestinatarioDocNoNotificado(
			@Param("idInstanciaProceso") Long idInstanciaProceso,			
			@Param("idPersona") Long idPersona, 
			@Param("idSubcategoriaConstancia") Long idSubcategoriaConstancia
			);
}
