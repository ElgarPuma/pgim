package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimBiblioArchivo;

/**
 * @descripción: Consultas de la entidad BiblioArchivo
 * 
 * @author: promero
 * @version: 1.0
 * @fecha_de_creación: 13/12/2023
 * @fecha_de_ultima_actualización: 13/12/2023
 *
 */
@Repository
public interface BiblioArchivoRepository extends JpaRepository<PgimBiblioArchivo, Long> {
	
	/**
	 * Pemite obtener un archivo bibliográfico por su ID 
	 * 
	 * @param idBiblioArchivo
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTOResultado("
		      + "barc.idBiblioArchivo, bdoc.idBiblioDocumento, barc.nuArchivoEcm, "
		      + "barc.nuCarpetaEcm, barc.noBibilioArchivo, bdoc.nuDocumento, "
		      + "bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento, bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento, "
		      + "bdoc.pgimSubcategoriaDoc.noSubcatDocumento, bdoc.pgimSubcategoriaDoc.coSubcatDocumento, "
		      + "bdoc.deAsuntoDocumento, bdoc.tipoMedioIngreso.noValorParametro, bdoc.feEmisionDocumento, "
		      + "emi.noRazonSocial, emi.coDocumentoIdentidad "
		      + ") "
		      + "FROM PgimBiblioArchivo barc "
		      + "INNER JOIN barc.pgimBiblioDocumento bdoc "
		      + "INNER JOIN bdoc.personaEmisora emi "
		      + "WHERE 1 = 1 "
		      + "AND barc.idBiblioArchivo = :idBiblioArchivo "
		      )
	  PgimBiblioArchivoDTO obtenerBiblioArchivoPorId(@Param("idBiblioArchivo") Long idBiblioArchivo);
	
	/**
	 * Permite listar los archivos bibliográficos de un documento determinado
	 * 
	 * @param idBiblioDocumento
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTOResultado("
		      + "barc.idBiblioArchivo, barc.pgimBiblioDocumento.idBiblioDocumento, barc.nuArchivoEcm, "
		      + "barc.nuCarpetaEcm, barc.noBibilioArchivo "
		      + ") "
		      + "FROM PgimBiblioArchivo barc "
		      + "WHERE 1 = 1 "
		      + "AND barc.esRegistro = '1' "
		      + "AND barc.pgimBiblioDocumento.idBiblioDocumento = :idBiblioDocumento "
		      )
	  List<PgimBiblioArchivoDTO> listarBiblioArchivoPorIdDoc(@Param("idBiblioDocumento") Long idBiblioDocumento);

	/**
	 * Permite listar los archivos bibliográficos de acuerdo con los parámetros filtro.
	 * Se complementa con el método obtenerCantidadBiblioDocumentos()
	 * 
	 * @param nuDocumento
	 * @param deAsuntoDocumento
	 * @param deSumillaDocumento
	 * @param idCategoriaDocumento
	 * @param idSubcatDocumento
	 * @param feEmisionDesde
	 * @param feEmisionHasta
	 * @param idPersonaEmisora
	 * @param idAgenteSupervisado
	 * @param idUnidadMinera
	 * @param idComponenteMinero
	 * @param textoBusqueda
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimBiblioArchivoDTOResultado("
	      + "barc.idBiblioArchivo, bdoc.idBiblioDocumento, barc.nuArchivoEcm, "
	      + "barc.nuCarpetaEcm, barc.noBibilioArchivo, bdoc.nuDocumento, "
	      + "bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento, bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento, "
	      + "bdoc.pgimSubcategoriaDoc.noSubcatDocumento, bdoc.pgimSubcategoriaDoc.coSubcatDocumento, "
	      + "bdoc.deAsuntoDocumento, bdoc.tipoMedioIngreso.noValorParametro, bdoc.feEmisionDocumento, "
	      + "emi.noRazonSocial, emi.coDocumentoIdentidad "
	      + ") "
	      + "FROM PgimBiblioArchivo barc "
	      + "INNER JOIN barc.pgimBiblioDocumento bdoc "
	      + "INNER JOIN bdoc.personaEmisora emi "
	      + "WHERE 1 = 1 "
	      + "AND barc.esRegistro = '1' "
	      + "AND bdoc.esRegistro = '1' "
	      + "AND (:nuDocumento IS NULL OR (LOWER(bdoc.nuDocumento) = LOWER(:nuDocumento))) "
	      + "AND (:deAsuntoDocumento IS NULL OR LOWER(bdoc.deAsuntoDocumento) LIKE LOWER(CONCAT('%', :deAsuntoDocumento, '%'))) "
	      + "AND (:deSumillaDocumento IS NULL OR LOWER(bdoc.deSumillaDocumento) LIKE LOWER(CONCAT('%', :deSumillaDocumento, '%'))) "
	      + "AND (:idCategoriaDocumento IS NULL OR (bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.idCategoriaDocumento = :idCategoriaDocumento)) "
	      + "AND (:idSubcatDocumento IS NULL OR (bdoc.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento)) "
	      + "AND (:feEmisionDesde IS NULL OR (:feEmisionDesde <= TRUNC(bdoc.feEmisionDocumento))) "
	      + "AND (:feEmisionHasta IS NULL OR (:feEmisionHasta >= TRUNC(bdoc.feEmisionDocumento))) "
	      + "AND (:idPersonaEmisora IS NULL OR (emi.idPersona = :idPersonaEmisora)) "
	      + "AND (:textoBusqueda IS NULL OR ( "
	      + "LOWER(bdoc.nuDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + "OR LOWER(bdoc.deAsuntoDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + "OR LOWER(bdoc.deSumillaDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + "OR LOWER(bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento || '-' || bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + "OR LOWER(bdoc.pgimSubcategoriaDoc.coSubcatDocumento || '-' || bdoc.pgimSubcategoriaDoc.noSubcatDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + "OR LOWER(emi.coDocumentoIdentidad || '-' || emi.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
	      + ")) "
	      // Filtramos por entidad de negocio Agente fiscalizado
	      + "AND (:idAgenteSupervisado IS NULL OR ( "
	      + "	EXISTS ( "
	      + "     SELECT 1 "
	      + "     FROM PgimBiblioEntidad be "
	      + "     WHERE 1 = 1 "
	      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO "
	      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
	      + "     AND be.idRegistroEntidadNegocio = :idAgenteSupervisado " 
	      + "     AND be.esRegistro = '1' "
	      + "	)"
	      + ")) "
	      // Filtramos por entidad de negocio Unidad fiscalizable
	      + "AND (:idUnidadMinera IS NULL OR ( "
	      + "	EXISTS ( "
	      + "     SELECT 1 "
	      + "     FROM PgimBiblioEntidad be "
	      + "     WHERE 1 = 1 "
	      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA "
	      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
	      + "     AND be.idRegistroEntidadNegocio = :idUnidadMinera " 
	      + "     AND be.esRegistro = '1' "
	      + "	)"
	      + ")) "
	      // Filtramos por entidad de negocio Componente minero
	      + "AND (:idComponenteMinero IS NULL OR ( "
	      + "	EXISTS ( "
	      + "     SELECT 1 "
	      + "     FROM PgimBiblioEntidad be "
	      + "     WHERE 1 = 1 "
	      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO "
	      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
	      + "     AND be.idRegistroEntidadNegocio = :idComponenteMinero " 
	      + "     AND be.esRegistro = '1' "
	      + "	)"
	      + ")) "
	      )
	  Page<PgimBiblioArchivoDTO> listarBiblioArchivosPaginado(
			  @Param("nuDocumento") String nuDocumento,
			  @Param("deAsuntoDocumento") String deAsuntoDocumento,
			  @Param("deSumillaDocumento") String deSumillaDocumento,
			  @Param("idCategoriaDocumento") Long idCategoriaDocumento,
			  @Param("idSubcatDocumento") Long idSubcatDocumento,
			  @Param("feEmisionDesde") Date feEmisionDesde,
			  @Param("feEmisionHasta") Date feEmisionHasta,
			  @Param("idPersonaEmisora") Long idPersonaEmisora,
			  @Param("idAgenteSupervisado") Long idAgenteSupervisado,
			  @Param("idUnidadMinera") Long idUnidadMinera,
			  @Param("idComponenteMinero") Long idComponenteMinero,
			  @Param("textoBusqueda") String textoBusqueda,
			  Pageable paginador);
	
	/**
	 * Permite obtener la cantidad de documentos bibliográficos de acuerdo con los parámetros filtro.
	 * Complementa al método listarBiblioArchivosPaginado()
	 * 
	 * @param nuDocumento
	 * @param deAsuntoDocumento
	 * @param deSumillaDocumento
	 * @param idCategoriaDocumento
	 * @param idSubcatDocumento
	 * @param feEmisionDesde
	 * @param feEmisionHasta
	 * @param idPersonaEmisora
	 * @param idAgenteSupervisado
	 * @param idUnidadMinera
	 * @param idComponenteMinero
	 * @param textoBusqueda
	 * @return
	 */
	@Query("SELECT COUNT(DISTINCT bdoc.idBiblioDocumento) "
		      + "FROM PgimBiblioArchivo barc "
		      + "INNER JOIN barc.pgimBiblioDocumento bdoc "
		      + "INNER JOIN bdoc.personaEmisora emi "
		      + "WHERE 1 = 1 "
		      + "AND barc.esRegistro = '1' "
		      + "AND bdoc.esRegistro = '1' "
		      + "AND (:nuDocumento IS NULL OR (LOWER(bdoc.nuDocumento) = LOWER(:nuDocumento))) "
		      + "AND (:deAsuntoDocumento IS NULL OR LOWER(bdoc.deAsuntoDocumento) LIKE LOWER(CONCAT('%', :deAsuntoDocumento, '%'))) "
		      + "AND (:deSumillaDocumento IS NULL OR LOWER(bdoc.deSumillaDocumento) LIKE LOWER(CONCAT('%', :deSumillaDocumento, '%'))) "
		      + "AND (:idCategoriaDocumento IS NULL OR (bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.idCategoriaDocumento = :idCategoriaDocumento)) "
		      + "AND (:idSubcatDocumento IS NULL OR (bdoc.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento)) "
		      + "AND (:feEmisionDesde IS NULL OR (:feEmisionDesde <= TRUNC(bdoc.feEmisionDocumento))) "
		      + "AND (:feEmisionHasta IS NULL OR (:feEmisionHasta >= TRUNC(bdoc.feEmisionDocumento))) "
		      + "AND (:idPersonaEmisora IS NULL OR (emi.idPersona = :idPersonaEmisora)) "
		      + "AND (:textoBusqueda IS NULL OR ( "
		      + "LOWER(bdoc.nuDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + "OR LOWER(bdoc.deAsuntoDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + "OR LOWER(bdoc.deSumillaDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + "OR LOWER(bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.coCategoriaDocumento || '-' || bdoc.pgimSubcategoriaDoc.pgimCategoriaDoc.noCategoriaDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + "OR LOWER(bdoc.pgimSubcategoriaDoc.coSubcatDocumento || '-' || bdoc.pgimSubcategoriaDoc.noSubcatDocumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + "OR LOWER(emi.coDocumentoIdentidad || '-' || emi.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
		      + ")) "
		      // Filtramos por entidad de negocio Agente fiscalizado
		      + "AND (:idAgenteSupervisado IS NULL OR ( "
		      + "	EXISTS ( "
		      + "     SELECT 1 "
		      + "     FROM PgimBiblioEntidad be "
		      + "     WHERE 1 = 1 "
		      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_AGENTE_SUPERVISADO "
		      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
		      + "     AND be.idRegistroEntidadNegocio = :idAgenteSupervisado " 
		      + "     AND be.esRegistro = '1' "
		      + "	)"
		      + ")) "
		      // Filtramos por entidad de negocio Unidad fiscalizable
		      + "AND (:idUnidadMinera IS NULL OR ( "
		      + "	EXISTS ( "
		      + "     SELECT 1 "
		      + "     FROM PgimBiblioEntidad be "
		      + "     WHERE 1 = 1 "
		      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_UNIDAD_MINERA "
		      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
		      + "     AND be.idRegistroEntidadNegocio = :idUnidadMinera " 
		      + "     AND be.esRegistro = '1' "
		      + "	)"
		      + ")) "
		      // Filtramos por entidad de negocio Componente minero
		      + "AND (:idComponenteMinero IS NULL OR ( "
		      + "	EXISTS ( "
		      + "     SELECT 1 "
		      + "     FROM PgimBiblioEntidad be "
		      + "     WHERE 1 = 1 "
		      + "	  AND be.pgimEntidadNegocio.noTablaEntidadNegocio = pe.gob.osinergmin.pgim.utils.ConstantesUtil.NO_TABLA_ENEGOCIO_COMPONENTE_MINERO "
		      + "     AND be.pgimBiblioDocumento.idBiblioDocumento = bdoc.idBiblioDocumento " 
		      + "     AND be.idRegistroEntidadNegocio = :idComponenteMinero " 
		      + "     AND be.esRegistro = '1' "
		      + "	)"
		      + ")) "
		      )
		  Integer obtenerCantidadBiblioDocumentos(
				  @Param("nuDocumento") String nuDocumento,
				  @Param("deAsuntoDocumento") String deAsuntoDocumento,
				  @Param("deSumillaDocumento") String deSumillaDocumento,
				  @Param("idCategoriaDocumento") Long idCategoriaDocumento,
				  @Param("idSubcatDocumento") Long idSubcatDocumento,
				  @Param("feEmisionDesde") Date feEmisionDesde,
				  @Param("feEmisionHasta") Date feEmisionHasta,
				  @Param("idPersonaEmisora") Long idPersonaEmisora,
				  @Param("idAgenteSupervisado") Long idAgenteSupervisado,
				  @Param("idUnidadMinera") Long idUnidadMinera,
				  @Param("idComponenteMinero") Long idComponenteMinero,
				  @Param("textoBusqueda") String textoBusqueda
				  );	
	
}
