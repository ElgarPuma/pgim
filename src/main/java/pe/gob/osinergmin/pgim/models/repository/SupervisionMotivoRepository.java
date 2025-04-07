package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionMotivo;

/**
 * En ésta interface SupervisionMotivoRepository esta conformado por sus metodos
 * de obtener, listar y crear sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Supervisón motivo
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020
 */
@Repository
public interface SupervisionMotivoRepository extends JpaRepository<PgimSupervisionMotivo, Long> {

    
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoDTOResultado("
            + "psm.idSupervisionMotivo, ps.idSupervision, psm.idObjetoMotivoInicio " 
            + " ) " 
            + "FROM PgimSupervisionMotivo psm " 
            + "INNER JOIN psm.pgimSupervision ps "
//            + "INNER JOIN psm.pgimEvento pe " 
//            + "INNER JOIN pe.tipoEvento te " 
//            + "INNER JOIN pe.pgimUnidadMinera pum "
            + "INNER JOIN ps.pgimSubtipoSupervision pss "
            + "WHERE psm.idSupervisionMotivo = :idSupervisionMotivo "
            + "AND psm.esRegistro = '1' ")
    PgimSupervisionMotivoDTO obtenerSupervisionMotivo(@Param("idSupervisionMotivo") Long idSupervisionMotivo);
 
}
