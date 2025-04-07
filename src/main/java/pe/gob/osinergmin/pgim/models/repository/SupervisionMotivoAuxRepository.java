package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSupervisionMotivoAux;

/**
 * En ésta interface SupervisionMotivoAuxRepository está conformado por sus métodos
 * de obtener y listar sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Supervisón motivo aux
 * 
 * @author promero
 *
 */
public interface SupervisionMotivoAuxRepository extends JpaRepository<PgimSupervisionMotivoAux, Long> {
	
	/**
	 * Permite listar los motivos asociados a una fiscalización
	 * 
	 * @param idSupervision
	 * @param paginador
	 * @return
	 */
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTOResultado("
            + "psm.idMotivo, psm.idUnidadMinera, psm.idTipoMotivo, "
            + "psm.deTipoMotivo, psm.deMotivo, psm.feMotivo, psm.coMotivo, "
            + "psm.deSubtipoMotivo, psm.idSubtipoMotivo, psm.idInstanciaProcesoMotivo, "
            + "psm.idSupervisionMotivo, psm.idSupervision, psm.idObjetoMotivoInicio, "
            + "psm.idTipoMotivoInicio, ma.idInstanciaPaso "
            + " ) " 
            + "FROM PgimSupervisionMotivoAux psm "
            + "LEFT JOIN PgimMedidaAdmAux ma ON (psm.idTipoMotivo = pe.gob.osinergmin.pgim.utils.ConstantesUtil.TIPO_MOTIVO_MEDIDA_ADM "
            + 	"AND ma.idInstanciaProceso = psm.idInstanciaProcesoMotivo AND ma.flPasoActivo = '1')"
            + "WHERE psm.idSupervision = :idSupervision "
            )
    Page<PgimSupervisionMotivoAuxDTO> listarSupervisionMotivoAuxPorSupervision(
    		@Param("idSupervision") Long idSupervision,
            Pageable paginador);
	
	
	/**
	 * Permite obtener una lista paginada de los motivos pasibles de selección para una fiscalización 
	 * determinada, por su unidad minera
	 * 
	 * @param idUnidadMinera
	 * @param idSupervision
	 * @param paginador
	 * @return
	 */
	@Query("SELECT DISTINCT new pe.gob.osinergmin.pgim.dtos.PgimSupervisionMotivoAuxDTOResultado("
			+ "psm.idMotivo, psm.idUnidadMinera, psm.idTipoMotivo, "
            + "psm.deTipoMotivo, psm.deMotivo, psm.feMotivo, psm.coMotivo, "
            + "psm.deSubtipoMotivo, psm.idSubtipoMotivo, psm.idInstanciaProcesoMotivo "
            + " ) "
            + "FROM PgimSupervisionMotivoAux psm " 
            + "WHERE psm.idUnidadMinera = :idUnidadMinera "
            + "AND (psm.idSupervision IS NULL OR psm.idSupervision <> :idSupervision) "
            + "AND NOT EXISTS (SELECT 1 " 
            + 	"FROM PgimSupervisionMotivoAux psma " 
            + 	"WHERE (psma.idTipoMotivoInicio = psm.idTipoMotivoInicio AND psma.idObjetoMotivoInicio = psm.idObjetoMotivoInicio) "
            + 	"AND psma.idSupervision = :idSupervision "
            + ") "
            )
	Page<PgimSupervisionMotivoAuxDTO> filtrarSeleccionMotivosPorUM(
            @Param("idUnidadMinera") Long idUnidadMinera, 
            @Param("idSupervision") Long idSupervision,
            Pageable paginador);	
	
}
