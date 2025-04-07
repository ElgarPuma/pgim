package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimRelacionSubcat;

/**
 * En ésta interface RelacionSubcatRepository esta conformado pos sus metodos de
 * listar las Subcategoria doc.
 * 
 * @descripción: Lógica de negocio de la entidad Subcategoria doc
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Repository
public interface RelacionSubcatRepository extends JpaRepository<PgimRelacionSubcat, Long> {

        /**
         * Permite obtener las subcategorías de documentos que son obligatorias para la transición pero aún no fueron cargadas.
         * @param idRelacionPaso
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado("
                + "resc.idRelacionSubcat, resc.pgimRelacionPaso.idRelacionPaso, resc.pgimSubcategoriaDoc.idSubcatDocumento, "
                + "resc.flSubcatDocRequerido, scdo.coSubcatDocumento, scdo.noSubcatDocumento, "
                + "cado.coCategoriaDocumento, cado.noCategoriaDocumento, espe.idEspecialidad, resc.flValidaReservaActiva) " 
                + "FROM PgimRelacionSubcat resc "
                + "INNER JOIN resc.pgimSubcategoriaDoc scdo " 
                + "INNER JOIN scdo.pgimCategoriaDoc cado "
                + "LEFT JOIN scdo.pgimEspecialidad espe "
                + "WHERE resc.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
                + "AND resc.flSubcatDocRequerido = '1' " 
                + "AND resc.esRegistro = '1' "
                + "AND scdo.esRegistro = '1' " 
                + "AND cado.esRegistro = '1' "                        
                + "AND NOT EXISTS (SELECT 1 "
                + "FROM PgimDocumento docu " 
                + "WHERE docu.esRegistro = '1' "
                + "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = scdo.idSubcatDocumento)")
        List<PgimRelacionSubcatDTO> listarSubCategoriasMandatoriasNoCargadas(
                        @Param("idRelacionPaso") Long idRelacionPaso,
                        @Param("idInstanciaProceso") Long idInstanciaProceso);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado("
                        + "resc.idRelacionSubcat, resc.pgimRelacionPaso.idRelacionPaso, resc.pgimSubcategoriaDoc.idSubcatDocumento, "
                        + "resc.flSubcatDocRequerido, scdo.coSubcatDocumento, scdo.noSubcatDocumento, "
                        + "cado.coCategoriaDocumento, cado.noCategoriaDocumento, espe.idEspecialidad, resc.flValidaReservaActiva) " 
                        + "FROM PgimRelacionSubcat resc "
                        + "INNER JOIN resc.pgimSubcategoriaDoc scdo " 
                        + "INNER JOIN scdo.pgimCategoriaDoc cado "
                        + "LEFT JOIN scdo.pgimEspecialidad espe "
                        + "WHERE resc.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
                        + "AND espe.idEspecialidad = :idEspecialidad "
                        + "AND resc.flAlMenosUno = '1' " 
                        + "AND resc.esRegistro = '1' "
                        + "AND scdo.esRegistro = '1' " 
                        + "AND cado.esRegistro = '1' "                        
                        + "AND EXISTS (SELECT 1 "
                        + "FROM PgimDocumento docu " 
                        + "WHERE docu.esRegistro = '1' "
                        + "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = scdo.idSubcatDocumento)")
        List<PgimRelacionSubcatDTO> listarSubCategoriasParaFiscPropiaNoCargadas(
                                @Param("idRelacionPaso") Long idRelacionPaso,
                                @Param("idEspecialidad") Long idEspecialidad,
                                @Param("idInstanciaProceso") Long idInstanciaProceso);

        /**
         * Permite obtener las subcategorías de documentos que se requieren para la transición de pasos, con la condición
         * de que al menos una de ellas haya sido cargada.
         * @param idRelacionPaso
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado("
                        + "resc.idRelacionSubcat, resc.pgimRelacionPaso.idRelacionPaso, resc.pgimSubcategoriaDoc.idSubcatDocumento, "
                        + "resc.flSubcatDocRequerido, scdo.coSubcatDocumento, scdo.noSubcatDocumento, "
                        + "cado.coCategoriaDocumento, cado.noCategoriaDocumento, espe.idEspecialidad, resc.flValidaReservaActiva) " 
                        + "FROM PgimRelacionSubcat resc "
                        + "INNER JOIN resc.pgimSubcategoriaDoc scdo " 
                        + "INNER JOIN scdo.pgimCategoriaDoc cado "
                        + "LEFT JOIN scdo.pgimEspecialidad espe "
                        + "WHERE resc.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
                        + "AND resc.flAlMenosUno = '1' " 
                        + "AND resc.esRegistro = '1' "
                        + "AND scdo.esRegistro = '1' " 
                        + "AND cado.esRegistro = '1' " 
                        + "AND EXISTS (SELECT 1 "
                        + "FROM PgimDocumento docu " 
                        + "WHERE docu.esRegistro = '1' "
                        + "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = scdo.idSubcatDocumento) ")
        List<PgimRelacionSubcatDTO> listarSubCategoriasAlmenosUnoCargadas(
                        @Param("idRelacionPaso") Long idRelacionPaso,
                        @Param("idInstanciaProceso") Long idInstanciaProceso);

        /**
         * Permite obtener las subcategorías de documentos que se requieren para la transición de pasos, con la condición
         * de que al menos una de ellas aún no fue cargada como parte de los documentos del fluj de trabajo.
         * @param idRelacionPaso
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado("
                        + "resc.idRelacionSubcat, resc.pgimRelacionPaso.idRelacionPaso, resc.pgimSubcategoriaDoc.idSubcatDocumento, "
                        + "resc.flSubcatDocRequerido, scdo.coSubcatDocumento, scdo.noSubcatDocumento, "
                        + "cado.coCategoriaDocumento, cado.noCategoriaDocumento, espe.idEspecialidad, resc.flValidaReservaActiva) " 
                        + "FROM PgimRelacionSubcat resc "
                        + "INNER JOIN resc.pgimSubcategoriaDoc scdo " 
                        + "INNER JOIN scdo.pgimCategoriaDoc cado "
                        + "LEFT JOIN scdo.pgimEspecialidad espe "
                        + "WHERE resc.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
                        + "AND resc.flAlMenosUno = '1' " 
                        + "AND resc.esRegistro = '1' "
                        + "AND scdo.esRegistro = '1' " 
                        + "AND cado.esRegistro = '1' " 
                        + "AND cado.esRegistro = '1' " 
                        + "AND NOT EXISTS (SELECT 1 "
                        + "FROM PgimDocumento docu " 
                        + "WHERE docu.esRegistro = '1' "
                        + "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                        + "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = scdo.idSubcatDocumento) ORDER BY scdo.coSubcatDocumento")
        List<PgimRelacionSubcatDTO> listarSubCategoriasAlmenosUnoNoCargadas(
                        @Param("idRelacionPaso") Long idRelacionPaso,
                        @Param("idInstanciaProceso") Long idInstanciaProceso);


        /**
         * Permite obtener las subcategorías de documentos que tienen flValidaReservaActiva 
         * @param idRelacionPaso
         * @param idInstanciaProceso
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado("
                + "resc.idRelacionSubcat, resc.pgimRelacionPaso.idRelacionPaso, resc.pgimSubcategoriaDoc.idSubcatDocumento, "
                + "resc.flSubcatDocRequerido, scdo.coSubcatDocumento, scdo.noSubcatDocumento, "
                + "cado.coCategoriaDocumento, cado.noCategoriaDocumento, espe.idEspecialidad, resc.flValidaReservaActiva) " 
                + "FROM PgimRelacionSubcat resc "
                + "INNER JOIN resc.pgimSubcategoriaDoc scdo " 
                + "INNER JOIN scdo.pgimCategoriaDoc cado "
                + "LEFT JOIN scdo.pgimEspecialidad espe "
                + "WHERE resc.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
                + "AND resc.flValidaReservaActiva = '1' " 
                + "AND resc.esRegistro = '1' "
                + "AND scdo.esRegistro = '1' " 
                + "AND cado.esRegistro = '1' "                        
                + "AND EXISTS (SELECT 1 "
                +   "FROM PgimDocumento docu " 
                +   "WHERE docu.esRegistro = '1' "
                +   "AND docu.pgimInstanciaProces.idInstanciaProceso = :idInstanciaProceso "
                +   "AND docu.pgimSubcategoriaDoc.idSubcatDocumento = scdo.idSubcatDocumento "
                +   "AND docu.flReservaActiva = '1' )"
                )
        List<PgimRelacionSubcatDTO> listarSubCategoriasFlReservaNumero(
                        @Param("idRelacionPaso") Long idRelacionPaso,
                        @Param("idInstanciaProceso") Long idInstanciaProceso);
        
        /**
         * Permite obtener la configuración de una relación-subcategoría  
         * por idSubcategoria y idRelacionPaso
         * 
         * @param idSubcatDocumento
         * @param idRelacionPaso
         * @return
         */
	    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado(rs.idRelacionSubcat, "
			    + "rs.pgimRelacionPaso.idRelacionPaso, rs.pgimSubcategoriaDoc.idSubcatDocumento, rs.flSubcatDocRequerido, "
			    + "rs.flValidaReservaActiva, rs.flRegistrarFechaEnvio, rs.flActualizarFechaEnvio ) "
			    + "FROM PgimRelacionSubcat rs "
			    + "WHERE 1=1 "
			    + "AND rs.esRegistro = '1' "
			    + "AND rs.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
			    + "AND rs.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
			    )
	    PgimRelacionSubcatDTO obtenerConfigRelacionSubcat(
			    @Param("idSubcatDocumento") Long idSubcatDocumento, 
			    @Param("idRelacionPaso") Long idRelacionPaso
			    );
	    
	    /**
	     * Permite listar las configuraciones relacioPaso-subcategoría 
	     * para verificar las subcategorías documentales que tienen flag "Validar notificación de todos los responsables" en determinada relación de paso (transición)
	     * 
	     * @param idRelacionPaso
	     * @return
	     */
	    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimRelacionSubcatDTOResultado(rs.idRelacionSubcat, "
			    + "rs.pgimRelacionPaso.idRelacionPaso, rs.pgimSubcategoriaDoc.idSubcatDocumento, subc.noSubcatDocumento, "
			    + "rs.flValidarNotifResponsable "
			    + ") "
			    + "FROM PgimRelacionSubcat rs "
			    + "INNER JOIN rs.pgimSubcategoriaDoc subc "
			    + "WHERE 1=1 "
			    + "AND rs.esRegistro = '1' "
//			    + "AND rs.pgimSubcategoriaDoc.idSubcatDocumento = :idSubcatDocumento "
			    + "AND rs.pgimRelacionPaso.idRelacionPaso = :idRelacionPaso "
			    + "AND rs.flValidarNotifResponsable = '1' "
			    )
	    List<PgimRelacionSubcatDTO> listarConfigRelacionSubcatValidaNotifResponsables(
			    @Param("idRelacionPaso") Long idRelacionPaso
			    );

}