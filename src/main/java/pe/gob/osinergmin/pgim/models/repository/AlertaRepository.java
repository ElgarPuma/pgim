package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAlertaDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAlerta;

/**
 * Interface AlertaRepository 
 * 
 * @descripci�n: L�gica de negocio de la gesti�n de alertas
 * 
 * @author: HRUIZ
 * @version: 1.0
 * @fecha_de_creaci�n: 02/09/2020
 * @fecha_de_ultima_actualizaci�n: 02/09/2020
 */
@Repository
public interface AlertaRepository extends JpaRepository<PgimAlerta, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAlertaDTOResultado("
            + "pa.idAlerta, pa.tipoAlerta.idValorParametro, pa.tipoAlerta.noValorParametro, "
            + "pa.noAlerta, pa.deAlerta, pa.feAlerta, pa.diEnlace, "
            + "(SELECT pad.flLeido FROM PgimAlertaDetalle pad "
            + "WHERE pad.pgimAlerta.idAlerta = pa.idAlerta AND pad.esRegistro = '1') ) "
            + "FROM PgimAlerta pa " 
            + "WHERE pa.esRegistro = '1' ")
	List<PgimAlertaDTO> listarAlerta();

}
