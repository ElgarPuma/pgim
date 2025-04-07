package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAutorizacion;

/**
 * 
 *
 * @descripción: consultas de la entidad Autorización
 * 
 * @author: gdelaguila
 * @version: 1.0
 * @fecha_de_creación: 03/09/2020
 * @fecha_de_ultima_actualización: 03/09/2020
 */
@Repository
public interface AutorizacionRepository extends JpaRepository<PgimAutorizacion, Long> {

        /**
         * Permite listar las autorizaciones
         * 
         * @param idUnidadMinera
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTOResultado( "
                        + "auto.idAutorizacion, auto.nuDocumento, " + "auto.tipoAutorizacion.noValorParametro, "
                        + "case when auto.pgimPersona.noCorto is not null then auto.pgimPersona.noCorto else auto.pgimPersona.noRazonSocial end , "
                        + "auto.pgimPersona.noCorto, " 
                        + "auto.feInicioAutorizacion) " 
                        + "FROM PgimAutorizacion auto "
                        + "WHERE auto.esRegistro = '1' "
                        + "AND auto.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ")
        Page<PgimAutorizacionDTO> listarAutorizacion(@Param("idUnidadMinera") Long idUnidadMinera, Pageable paginador);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTOResultado( "
                        + "a.idAutorizacion,a.pgimUnidadMinera.idUnidadMinera,a.tipoAutorizacion.idValorParametro,t.noValorParametro, "
                        + "a.nuDocumento,a.pgimPersona.idPersona,p.noRazonSocial,a.feInicioAutorizacion, "
                        + "a.feFinAutorizacion,a.deNota, " 
                        + "a.pgimInstanciaProces.idInstanciaProceso, "
                        + "a.esRegistro,a.usCreacion,a.ipCreacion, "
                        + "a.feCreacion,a.usActualizacion,a.ipActualizacion,a.feActualizacion, "
                        + "(SELECT tpipx.nuExpedienteSiged FROM PgimInstanciaProces tpipx "
                        + "WHERE tpipx.idInstanciaProceso = a.pgimInstanciaProces.instanciaProcesoPadre.idInstanciaProceso ) "
                        + ") " 
                        + "FROM PgimAutorizacion a "
                        + ", PgimPersona p, PgimValorParametro t " 
                        + "WHERE a.pgimPersona.idPersona = p.idPersona "
                        + "AND a.tipoAutorizacion.idValorParametro = t.idValorParametro "
                        + "AND (:idAutorizacion IS NULL OR ( a.idAutorizacion = :idAutorizacion )   )")
        public PgimAutorizacionDTO getAutorizacionById(@Param("idAutorizacion") Long idAutorizacion);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTOResultado( "
                + "a.idAutorizacion, a.pgimUnidadMinera.idUnidadMinera, tiau.idValorParametro, tiau.noValorParametro, "
                + "a.nuDocumento, per.idPersona, per.noRazonSocial, a.feInicioAutorizacion, "
                + "a.feFinAutorizacion, a.deNota, inpro.idInstanciaProceso "
                + ") " 
                + "FROM PgimAutorizacion a "
                + "LEFT JOIN PgimValorParametro tiau ON tiau = a.tipoAutorizacion "
                + "LEFT JOIN PgimPersona per ON per = a.pgimPersona "
                + "left join PgimInstanciaProces inpro on inpro = a.pgimInstanciaProces "
                + "WHERE a.esRegistro = '1' "
                + "AND a.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ")
    	public List<PgimAutorizacionDTO> getAutorizacionPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAutorizacionDTOResultado( "
                + "a.idAutorizacion ) " 
                + "FROM PgimAutorizacion a "
                + "WHERE a.esRegistro = '1' "
                + "AND a.pgimUnidadMinera.idUnidadMinera = :idUnidadMinera ")
    	public List<PgimAutorizacionDTO> listarAutorizacionPorUnidadMinera(@Param("idUnidadMinera") Long idUnidadMinera);
        
        
}
