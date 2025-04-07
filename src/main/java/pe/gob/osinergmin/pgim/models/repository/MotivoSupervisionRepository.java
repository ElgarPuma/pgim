package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMotivoSupervision;

/**
 * Ésta interface FichaRevisionRepository incluye los metodos de obtener Motivo supervision
 * 
 * @descripción: Lógica de negocio de la entidad Motivo supervision
 * 
 * @author hruiz
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 *
 */
public interface MotivoSupervisionRepository extends JpaRepository<PgimMotivoSupervision, Long> {


	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTOResultado( "
            + "pms.idMotivoSupervision, pms.deMotivoSupervision, pms.tipoSupervision.idValorParametro ) "
            + "FROM PgimMotivoSupervision pms "
            + "WHERE pms.esRegistro = '1' ")
	List<PgimMotivoSupervisionDTO> obtenerMotivoSupervision();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTOResultado( "
            + "pms.idMotivoSupervision, pms.deMotivoSupervision, pms.tipoSupervision.idValorParametro ) "
            + "FROM PgimMotivoSupervision pms "
            + "WHERE pms.esRegistro = '1' "
            + "AND pms.flDatoEstandar = '1' ")
	List<PgimMotivoSupervisionDTO> obtenerMotivoSupervisionParaDatosEstandar();
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMotivoSupervisionDTOResultado( "
            + "pms.idMotivoSupervision, pms.deMotivoSupervision, pms.tipoSupervision.idValorParametro ) "
            + "FROM PgimMotivoSupervision pms "
            + "WHERE pms.esRegistro = '1' "
            + "AND pms.tipoSupervision.idValorParametro = :idTipoSupervision ")
	List<PgimMotivoSupervisionDTO> listarMotivoSupervisionByTipoSupervision(@Param("idTipoSupervision") Long idTipoSupervision);
	
}
