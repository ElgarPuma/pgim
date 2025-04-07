package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimResponsableSuperDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimResponsableSuper;

/**
 * En ésta interface ResponsableSuperRepository esta conformado pos sus metodos de listar
 * las unidades mineras, aplicacion de los filtros por Responsable super.
 * 
 * @descripción: Lógica de negocio de la entidad Responsable super
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface ResponsableSuperRepository extends JpaRepository<PgimResponsableSuper, Long>{
    
    /***
        * Permite obtener la lista de responsable super que coinciden con la palabra clave.
        * @param palabraClave
        * @return
        */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimResponsableSuperDTOResultado("
                + "resp.idResponsableSuper, resp.pgimPersonalOsi.pgimPersona.noRazonSocial ) "
                + "FROM PgimResponsableSuper resp "
                + "WHERE resp.esRegistro = '1' AND (:palabraClave IS NULL OR LOWER(resp.pgimPersonalOsi.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                + " ) ORDER BY resp.pgimPersonalOsi.pgimPersona.noRazonSocial")
        List<PgimResponsableSuperDTO> listarPorPalabraClave(@Param("palabraClave") String palabraClave);
}