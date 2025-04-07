package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSubcategoriaDoc;

/**
 * Ésta interface SubcategoriaDocRepository incluye el metodo que permite
 * mostrar
 * una lista de sus Subcategorias de los documentos.
 * 
 * @descripción: Logica de negocio de la entidad Subcategoria doc
 * 
 * @author: gusdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 15/12/2023
 */
@Repository
public interface SubcategoriaDocRepository extends JpaRepository<PgimSubcategoriaDoc, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sc.idSubcatDocumento, sc.coSubcatDocumento ||' - ' || sc.noSubcatDocumento, sc.pgimCategoriaDoc.idCategoriaDocumento, "
            + "sc.flNumeradoPorSiged,sc.coTipoDocumentoSiged,sc.tipoOrigenDocumento.idValorParametro, sc.pgimEspecialidad.idEspecialidad,"
            + "sc.deExtensionesPermitidas, sc.flReservaNumero "
            + ") "
            + "FROM PgimSubcategoriaDoc sc "
            + "WHERE sc.esRegistro = '1' "
            + "ORDER BY sc.coSubcatDocumento ")
    public List<PgimSubcategoriaDocDTO> listarSubCategorias();

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sc.idSubcatDocumento, sc.coSubcatDocumento ||' - ' || sc.noSubcatDocumento, sc.pgimCategoriaDoc.idCategoriaDocumento, "
            + "sc.flNumeradoPorSiged,sc.coTipoDocumentoSiged,sc.tipoOrigenDocumento.idValorParametro, sc.pgimEspecialidad.idEspecialidad,"
            + "sc.deExtensionesPermitidas, sc.flReservaNumero "
            + ") "
            + "FROM PgimSubcategoriaDoc sc "
            + "WHERE sc.esRegistro = '1' "
            + "AND sc.idSubcatDocumento "
            + "IN (SELECT pgimSubcategoriaDoc.idSubcatDocumento FROM PgimSubcatFasepro WHERE pgimFaseProceso.idFaseProceso = :idFase)"
            + "ORDER BY sc.coSubcatDocumento ")
    public List<PgimSubcategoriaDocDTO> listarSubCategoriasFase(@Param("idFase") Long idFase);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sudo.idSubcatDocumento, sudo.coSubcatDocumento ||' - ' || sudo.noSubcatDocumento, sudo.pgimCategoriaDoc.idCategoriaDocumento, "
            + "sudo.flNumeradoPorSiged,sudo.coTipoDocumentoSiged,sudo.tipoOrigenDocumento.idValorParametro, sudo.pgimEspecialidad.idEspecialidad, "
            + "sudo.deExtensionesPermitidas, sudo.flReservaNumero, pasc.flAdjuntadoManual, " 
            + "pasc.flAdjuntadoManualPropia " 
            + ") "
            + "FROM PgimPasoSubcat pasc "
            + "     INNER JOIN pasc.pgimPasoProceso papr "
            + "     INNER JOIN pasc.pgimSubcategoriaDoc sudo "
            + "WHERE papr.idPasoProceso = :idPasoProceso "
            + "AND pasc.esRegistro = '1' "
            + "AND papr.esRegistro = '1' "
            + "AND sudo.esRegistro = '1' "
            + "ORDER BY sudo.coSubcatDocumento ")
    public List<PgimSubcategoriaDocDTO> listarSubCategoriasPorPaso(@Param("idPasoProceso") Long idPasoProceso);
    
    /**
     * Permite listar las subcategorías de una fase en función a la configuración en BD de la tabla PASO_SUBCAT
     * 
     * @param idPasoProceso	Id del paso del cual se obtendrá la fase de la que se requiere las subcategorías 
     * @return
     */
    @Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sudo.idSubcatDocumento, sudo.coSubcatDocumento, sudo.noSubcatDocumento, sudo.pgimCategoriaDoc.idCategoriaDocumento, "
            + "sudo.flNumeradoPorSiged,sudo.coTipoDocumentoSiged,sudo.tipoOrigenDocumento.idValorParametro, sudo.pgimEspecialidad.idEspecialidad, "
            + "sudo.deExtensionesPermitidas, sudo.flReservaNumero, pasc.flAdjuntadoManual, " 
            + "pasc.flAdjuntadoManualPropia " 
            + ") "
            + "FROM PgimPasoSubcat pasc "
            + "     INNER JOIN pasc.pgimPasoProceso papr "
            + "     INNER JOIN pasc.pgimSubcategoriaDoc sudo "
            + "     INNER JOIN papr.pgimFaseProceso fapr "
            + "WHERE fapr.idFaseProceso = (SELECT pgimFaseProceso.idFaseProceso FROM PgimPasoProceso WHERE idPasoProceso = :idPasoProceso) "
            + "AND pasc.esRegistro = '1' "
            + "AND papr.esRegistro = '1' "
            + "AND sudo.esRegistro = '1' "
            + "ORDER BY sudo.coSubcatDocumento ")
    public List<PgimSubcategoriaDocDTO> listarSubCategoriasFasePorIdPaso(@Param("idPasoProceso") Long idPasoProceso);

    @Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sudo.idSubcatDocumento, sudo.coSubcatDocumento, sudo.noSubcatDocumento, sudo.pgimCategoriaDoc.idCategoriaDocumento, "
            + "sudo.flNumeradoPorSiged,sudo.coTipoDocumentoSiged,sudo.tipoOrigenDocumento.idValorParametro, sudo.pgimEspecialidad.idEspecialidad, "
            + "sudo.deExtensionesPermitidas, sudo.flReservaNumero, pasc.flAdjuntadoManual, " 
            + "pasc.flAdjuntadoManualPropia, fapr.idFaseProceso " 
            + ") "
            + "FROM PgimPasoSubcat pasc "
            + "     INNER JOIN pasc.pgimPasoProceso papr "
            + "     INNER JOIN pasc.pgimSubcategoriaDoc sudo "
            + "     INNER JOIN papr.pgimFaseProceso fapr "
            + "     INNER JOIN fapr.pgimProceso prc "
            + "WHERE prc.idProceso = :idProceso "
            + "AND fapr.idFaseProceso <= :idFase "
            + "AND pasc.esRegistro = '1' "
            + "AND papr.esRegistro = '1' "
            + "AND sudo.esRegistro = '1' "
            + "AND prc.esRegistro = '1' "
            + "AND fapr.esRegistro = '1' "
            + "ORDER BY sudo.coSubcatDocumento ")
    public List<PgimSubcategoriaDocDTO> listarSubCategoriasIncluirByProceso(@Param("idProceso") Long idProceso, @Param("idFase") Long idFase  );


    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sc.idSubcatDocumento, sc.pgimCategoriaDoc.idCategoriaDocumento, cat.coCategoriaDocumento, cat.noCategoriaDocumento, "
            + "sc.pgimEspecialidad.idEspecialidad, sc.tipoOrigenDocumento.idValorParametro, sc.tipoFirma.idValorParametro, "
            + "sc.coSubcatDocumento, sc.noSubcatDocumento, sc.flNumeradoPorSiged, sc.coTipoDocumentoSiged, tiex.idValorParametro, "
            + "tiex.noValorParametro, sc.deExtensionesPermitidas, sc.noArchivoReserva, "
            + " CASE " 
            + "      WHEN sc.flNumeradoPorSiged = '1' THEN 'S' "
            + "      ELSE 'N' "
            + " END AS flNumeradoPorSiged " 
            + ") "
            + "FROM PgimSubcategoriaDoc sc "
            + "INNER JOIN sc.pgimCategoriaDoc cat "
            + "LEFT OUTER JOIN sc.tipoExtensionGen tiex "
            + "WHERE sc.esRegistro = '1' "
            + "and sc.idSubcatDocumento = :idSubcatDocumento")
    public PgimSubcategoriaDocDTO obtenerSubcategoriaDocPorId(@Param("idSubcatDocumento") Long idSubcatDocumento);

    /**
     * Permite obtener las extensiones permitidas de una subcategoria documental.
     * 
     * @param idSubcatDocumento
     * @return
     */
    @Query("SELECT sc.deExtensionesPermitidas "
            + "FROM PgimSubcategoriaDoc sc "
            + "WHERE sc.idSubcatDocumento = :idSubcatDocumento "
            + "AND sc.esRegistro = '1'")
    public String obtenerExtensionesPermitidasByIdSubcatDocumento(@Param("idSubcatDocumento") Long idSubcatDocumento);

    /***
     * Permite obtener la lista de subcategorías documentales en las que, según la
     * configuración del flujo de fiscalización, debe firmar
     * la persona fiscalizadora.
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
    		+ "suca.idSubcatDocumento, cado.idCategoriaDocumento, "
            + "suca.coSubcatDocumento, suca.noSubcatDocumento, cado.coCategoriaDocumento, cado.noCategoriaDocumento) "
            + "FROM PgimSubcatFasepro scfp "
            + "INNER JOIN scfp.pgimFaseProceso fapr "
            + "INNER JOIN scfp.pgimSubcategoriaDoc suca "
            + "INNER JOIN suca.pgimCategoriaDoc cado "
            + "LEFT OUTER JOIN suca.pgimEspecialidad espe "
            + "WHERE fapr.pgimProceso.idProceso = pe.gob.osinergmin.pgim.utils.ConstantesUtil.PARAM_PROCESO_SUPERVISION "
            + "AND suca.flFirmaFiscalizador = '1' "
            + "AND (espe.idEspecialidad IS NULL OR espe.idEspecialidad = :idEspecialidad) "
            + "AND scfp.esRegistro = '1' "
            + "AND fapr.esRegistro = '1' "
            + "AND suca.esRegistro = '1' "
            + "AND cado.esRegistro = '1' "
            + "ORDER BY cado.noCategoriaDocumento, suca.noSubcatDocumento")
    public List<PgimSubcategoriaDocDTO> listarSubcategoriasFirmadasPorFiscalizador(@Param("idEspecialidad") Long idEspecialidad);    

    /**
     * Permite obtener un listado de las subcategorias según una palabra clave ingresada
     * requerido en campos autocomplete del frontend, adecuados para documentos bibliograficos
     * @param palabraClave
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
            + "sc.idSubcatDocumento, cat.idCategoriaDocumento, cat.coCategoriaDocumento, cat.noCategoriaDocumento, "
            + "sc.pgimEspecialidad.idEspecialidad, sc.tipoOrigenDocumento.idValorParametro, sc.tipoFirma.idValorParametro, "
            + "sc.coSubcatDocumento, cat.coCategoriaDocumento || ': ' || cat.noCategoriaDocumento || ' / ' || sc.coSubcatDocumento || ':' ||sc.noSubcatDocumento, sc.flNumeradoPorSiged, sc.coTipoDocumentoSiged, tiex.idValorParametro, "
            + "tiex.noValorParametro, sc.deExtensionesPermitidas, sc.noArchivoReserva, "
            + " CASE " 
            + "      WHEN sc.flNumeradoPorSiged = '1' THEN 'S' "
            + "      ELSE 'N' "
            + " END AS flNumeradoPorSiged " 
            + ") "
            + "FROM PgimSubcategoriaDoc sc "
            + "INNER JOIN sc.pgimCategoriaDoc cat "
            + "LEFT OUTER JOIN sc.tipoExtensionGen tiex "
            + "WHERE sc.esRegistro = '1' "
            + "AND  sc.flBibiografia = '1'"
            + "AND (LOWER(cat.coCategoriaDocumento) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            +   "OR LOWER(cat.noCategoriaDocumento) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            +   "OR LOWER(sc.coSubcatDocumento) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            +   "OR LOWER(sc.noSubcatDocumento) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
            + ") "
            + "ORDER BY cat.noCategoriaDocumento, sc.noSubcatDocumento "
                        )
    public List<PgimSubcategoriaDocDTO> listarSubcatBiblioPorPalabraClave(@Param("palabraClave") String palabraClave);
    
    /**
     * Permite listar las subcategorías correspondientes a bibliografía
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSubcategoriaDocDTOResultado( "
    		+ "suca.idSubcatDocumento, cado.idCategoriaDocumento, "
            + "suca.coSubcatDocumento, suca.noSubcatDocumento, cado.coCategoriaDocumento, cado.noCategoriaDocumento) "
            + "FROM PgimSubcategoriaDoc suca "
            + "INNER JOIN suca.pgimCategoriaDoc cado "
            + "WHERE 1 = 1 "
            + "AND suca.esRegistro = '1' "
            + "AND suca.flBibiografia = '1' "
            + "ORDER BY suca.coSubcatDocumento")
    List<PgimSubcategoriaDocDTO> listarSubcatBibliografia();    

}
