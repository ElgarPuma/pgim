package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimDenuncia;

import java.util.Date;
import java.util.List;

/**
 * @descripción: Lógica de negocio de la entidad Denuncia
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 05/11/2020
 */
public interface DenunciaRepository extends JpaRepository<PgimDenuncia, Long> {

    /**
     * Permite listar las denuncias de la unidad minera.
     * @param idUnidadMinera
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado("
            + "den.idDenuncia, den.pgimUnidadMinera.idUnidadMinera, den.pgimUnidadMinera.noUnidadMinera, den.coDenuncia, " 
            + "den.deDenuncia, den.fePresentacion, den.personaDenunciante.tipoDocIdentidad.idValorParametro, " 
            + "case when den.personaDenunciante.tipoDocIdentidad.coClaveTexto " 
            + "= :DOIDE_RUC then den.personaDenunciante.noRazonSocial else (den.personaDenunciante.noPersona ||' '|| den.personaDenunciante.apPaterno ||' '|| den.personaDenunciante.apMaterno) end, "
            + "den.medioDenuncia.noValorParametro "
            + ") " 
            + "FROM PgimDenuncia den "
            + "WHERE den.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
            + "AND den.esRegistro = '1'"
            )
    Page<PgimDenunciaDTO> listarDenuncia(@Param("idUnidadMinera") Long idUnidadMinera, @Param("DOIDE_RUC") String DOIDE_RUC, Pageable paginador);

    /**
     * Permite listar las denuncias en general de la unidad minera.
     * @param coDenuncia
     * @param idMedioDenuncia
     * @param idTipoDocIdentidad
     * @param textoBusqueda
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado("
            + "den.idDenuncia, den.pgimUnidadMinera.idUnidadMinera, (den.pgimUnidadMinera.coUnidadMinera || ' - ' || den.pgimUnidadMinera.noUnidadMinera), " 
            + "den.coDenuncia, den.deDenuncia, den.fePresentacion, den.personaDenunciante.tipoDocIdentidad.idValorParametro, " 
            + "case when den.personaDenunciante.tipoDocIdentidad.coClaveTexto " 
            + "= :claveRUC then den.personaDenunciante.noRazonSocial else (den.personaDenunciante.noPersona ||' '|| den.personaDenunciante.apPaterno ||' '|| den.personaDenunciante.apMaterno) end, "
            + "den.medioDenuncia.noValorParametro "
            + ") " 
            + "FROM PgimDenuncia den "
            + "WHERE den.esRegistro = '1' "
            + "AND (:coDenuncia IS NULL OR LOWER(den.coDenuncia) LIKE LOWER(CONCAT('%', :coDenuncia, '%'))) "
            + "AND (:idMedioDenuncia IS NULL OR den.medioDenuncia.idValorParametro = :idMedioDenuncia) "
            + "AND (:idTipoDocIdentidad IS NULL OR den.personaDenunciante.tipoDocIdentidad.idValorParametro = :idTipoDocIdentidad) "
            + "AND (:fePresentacionDesde IS NULL OR den.fePresentacion >= :fePresentacionDesde) "
            + "AND (:fePresentacionHasta IS NULL OR den.fePresentacion <= :fePresentacionHasta) "
            + "AND (:descNoUnidadMinera IS NULL OR LOWER(den.pgimUnidadMinera.coUnidadMinera) LIKE LOWER(CONCAT('%', :descNoUnidadMinera, '%'))  "
            + "OR LOWER(den.pgimUnidadMinera.noUnidadMinera) LIKE LOWER(CONCAT('%', :descNoUnidadMinera, '%')) ) "
            + "AND (:descNoRazonSocial IS NULL OR LOWER(den.personaDenunciante.noRazonSocial) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) " 
            + "OR LOWER (den.personaDenunciante.noPersona || ' ' || den.personaDenunciante.apPaterno || ' ' || den.personaDenunciante.apMaterno) LIKE LOWER(CONCAT('%', :descNoRazonSocial, '%')) ) "
            + "AND (:textoBusqueda IS NULL OR (LOWER(den.deDenuncia) LIKE LOWER(CONCAT('%', :textoBusqueda, '%'))) " 
            + "OR LOWER (den.coDenuncia) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
            + "OR LOWER (den.medioDenuncia.noValorParametro) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
            + ") " 
            )
    Page<PgimDenunciaDTO> listarDenunciaGeneral(@Param("coDenuncia") String coDenuncia, 
                                                @Param("idMedioDenuncia") Long idMedioDenuncia, 
                                                @Param("idTipoDocIdentidad") Long idTipoDocIdentidad, 
                                                @Param("fePresentacionDesde") Date fePresentacionDesde, 
                                                @Param("fePresentacionHasta") Date fePresentacionHasta, 
                                                @Param("descNoUnidadMinera") String descNoUnidadMinera, 
                                                @Param("descNoRazonSocial") String descNoRazonSocial, 
                                                @Param("textoBusqueda") String textoBusqueda, 
                                                @Param("claveRUC") String claveRUC,
                                                Pageable paginador
                                                );

    /**
     * Permite obtener la lista de denuncias de la unidad minera.
     * @param idDenuncia
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado("
            + "den.idDenuncia, den.pgimUnidadMinera.idUnidadMinera, den.medioDenuncia.idValorParametro, " 
            + "den.personaDenunciante.idPersona, den.medioDenuncia.noValorParametro, " 
            + "case when den.personaDenunciante.tipoDocIdentidad.coClaveTexto " 
            + "= :DOIDE_RUC then den.personaDenunciante.noRazonSocial else (den.personaDenunciante.noPersona ||' '|| den.personaDenunciante.apPaterno ||' '|| den.personaDenunciante.apMaterno) end, "
            + "den.coDenuncia, den.fePresentacion, den.deDenuncia, den.pgimUnidadMinera.noUnidadMinera, "
            + "den.pgimInstanciaProces.idInstanciaProceso, "
            + "(SELECT tpipx.nuExpedienteSiged FROM PgimInstanciaProces tpipx "
            + "WHERE tpipx.idInstanciaProceso = den.pgimInstanciaProces.instanciaProcesoPadre.idInstanciaProceso ) "
            + ") " 
            + "FROM PgimDenuncia den "
            + "WHERE den.idDenuncia = :idDenuncia "
            )
    PgimDenunciaDTO obtenerDenunciaPorId(@Param("idDenuncia") Long idDenuncia, @Param("DOIDE_RUC") String DOIDE_RUC);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado(" 
                    + "pers.idPersona, "
                    + "case when pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC then pers.noRazonSocial else (pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) end " 
                    + ") "
                    + "FROM PgimPersona pers " 
                    + "WHERE pers.esRegistro = '1' "
                    + "AND NOT EXISTS (SELECT 1 " 
                    + "FROM PgimDenuncia den "
                    + "WHERE den.esRegistro = '1' AND den.pgimUnidadMinera.esRegistro = '1' "
                    + "AND den.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
                    + "AND den.personaDenunciante = pers) "
                    + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noRazonSocial) "
                    + "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
                    + "OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                    + " ) "
                    )
    List<PgimDenunciaDTO> listarPorPersona(@Param("idUnidadMinera") Long idUnidadMinera, String palabraClave, @Param("DOIDE_RUC") String DOIDE_RUC);
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado(" 
                    + "pers.idPersona, "
                    + "case when pers.tipoDocIdentidad.coClaveTexto = :DOIDE_RUC then pers.noRazonSocial else (pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) end " 
                    + ") "
                    + "FROM PgimPersona pers " 
                    + "WHERE pers.esRegistro = '1' "
                    + "AND NOT EXISTS (SELECT 1 " 
                    + "FROM PgimDenuncia den "
                    + "WHERE den.esRegistro = '1' AND den.pgimUnidadMinera.esRegistro = '1' "
                    // + "AND den.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera "
                    + "AND den.personaDenunciante = pers) "
                    + "AND (:palabraClave IS NULL OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noRazonSocial) "
                    + "LIKE LOWER(CONCAT('%', :palabraClave, '%')) " 
                    + "OR LOWER(pers.coDocumentoIdentidad ||' '|| pers.noPersona ||' '|| pers.apPaterno ||' '|| pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                    + " ) "
                    )
    List<PgimDenunciaDTO> listarPorPersonaGeneral(String palabraClave, @Param("DOIDE_RUC") String DOIDE_RUC);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado("
                    + "MAX(TO_CHAR(den.idDenuncia + 1, 'FM0000')) " 
                    + ") " 
                    + "FROM PgimDenuncia den "
                    + "WHERE den.esRegistro = '1' ")
    PgimDenunciaDTO obtenerMaxCodigoDenuncia();
    //     + "MAX('DEN' || '-' || EXTRACT(YEAR FROM sysdate) || '-' || TO_CHAR(den.idDenuncia, 'FM00000')) "

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimDenunciaDTOResultado(" 
                    + "MAX(TO_CHAR(den.idDenuncia, 'FM00000')) "
                    + ") "
                    + "FROM PgimDenuncia den " 
                    + "WHERE den.esRegistro = '1' "
                    + "AND den.idDenuncia =:idDenuncia"
                    )
    PgimDenunciaDTO obtenerMaxCodigoDenunciaPorId(@Param("idDenuncia") Long idDenuncia);
    
}
