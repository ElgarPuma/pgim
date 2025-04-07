package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimTitularAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTitularAux;

/**
 * @descripción: Logica de negocio de la Vista para obtener la lista de reporte de identificación y ubicación del titular
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 02/10/2020
 * @fecha_de_ultima_actualización: 
 */
public interface TitularAuxRepository extends JpaRepository<PgimTitularAux, Long>{
    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTitularAuxDTOResultado("
            + "ig.idCliente, ig.anioPro, ig.mes, ig.ruc, ig.titularMinero, ig.estrato, ig.idRepresentante, ig.nombreRepresentanteLegal, ig.email, "
            + "ig.cargoRepresentante, ig.nombreresponsable, ig.cargoResponsable, ig.descripcion, ig.respNoDocumento, ig.respEmail, ig.respTelefono, " 
            + "ig.respTelefonoMovil, ig.idCargo " 
            + ") "
            + "FROM PgimTitularAux ig "
            + "WHERE 1 = 1 "
            + "AND (:feInicio IS NULL OR (TRIM(ig.anioPro) || '/' || TRIM(ig.mes) BETWEEN :feInicio AND :feFin)) ORDER BY TRIM(ig.anioPro) DESC, TRIM(ig.mes) DESC ")
    Page<PgimTitularAuxDTO> listarTitular(@Param("feInicio") String feInicio, @Param("feFin") String feFin, Pageable paginador);

}
