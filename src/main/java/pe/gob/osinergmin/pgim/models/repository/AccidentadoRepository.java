package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimAccidentado;

/** 
 * Ésta interface de AccidentadoRepository incluye 
 * los metodos para obtener y listar los accidentados.
 * 
 * @descripción: Lógica de negocio de la entidad Accidentado
 * 
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 10/06/2020
*/
@Repository
public interface AccidentadoRepository extends JpaRepository<PgimAccidentado, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTOResultado("
			+ "acci.idAccidentado, acci.pgimPersona.coDocumentoIdentidad, acci.pgimPersona.noPersona, "
			+ "acci.pgimPersona.apPaterno, acci.pgimPersona.apMaterno, acci.feFallecimiento, "
			+ "acci.pgimEmpresaEvento.pgimPersona.coDocumentoIdentidad, acci.pgimEmpresaEvento.pgimPersona.noRazonSocial, acci.pgimEmpresaEvento.tipoEmpresaInvolucrada.noValorParametro) "
			+ "FROM PgimAccidentado acci WHERE acci.esRegistro = '1' AND acci.pgimEmpresaEvento.esRegistro = '1' AND acci.pgimEmpresaEvento.pgimEvento.idEvento = :idEvento "
			+ "ORDER BY acci.pgimEmpresaEvento.tipoEmpresaInvolucrada.noValorParametro DESC, acci.pgimPersona.noPersona, acci.feFallecimiento DESC")
	List<PgimAccidentadoDTO> listarAccidentado(@Param("idEvento") Long idEvento);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTOResultado("
			+ "acci.idAccidentado, acci.pgimPersona.tipoDocIdentidad.idValorParametro, acci.pgimPersona.tipoDocIdentidad.noValorParametro, "
			+ "acci.pgimPersona.coDocumentoIdentidad, acci.pgimPersona.noPersona, "
			+ "acci.pgimPersona.apPaterno, acci.pgimPersona.apMaterno, "
			+ "acci.pgimPersona.feNacimiento, acci.pgimPersona.diPersona, acci.pgimPersona.tiSexo, ubig.idUbigeo,  "
			+ "((SELECT TRIM(ubde.noUbigeo) FROM PgimUbigeo ubde WHERE ubde.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 2) || '0000') || ', ' || "
			+ "(SELECT TRIM(ubdi.noUbigeo) FROM PgimUbigeo ubdi where ubdi.coUbigeo = SUBSTR(ubig.coUbigeo, 0, 4) || '00') || ', ' || "
			+ "TRIM(ubig.noUbigeo)), "
			+ "acci.pgimPersona.deTelefono, acci.feFallecimiento, acci.categoriaOcupacional.idValorParametro, "
			+ "acci.pgimEmpresaEvento.idEmpresaEvento) " 
			+ "FROM PgimAccidentado acci "
			//+ "INNER JOIN acci.pgimPersona.pgimUbigeo ubig "
			+ "LEFT OUTER JOIN acci.pgimPersona.pgimUbigeo ubig "
			+ "WHERE acci.idAccidentado = :idAccidentado " 
			+ "AND acci.esRegistro = '1' ")
	PgimAccidentadoDTO obtenerAccidentado(@Param("idAccidentado") Long idAccidentado);

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTOResultado("
			+ "acci.idAccidentado, acci.pgimPersona.tipoDocIdentidad.idValorParametro, acci.pgimPersona.tipoDocIdentidad.noValorParametro, "
			+ "acci.pgimPersona.coDocumentoIdentidad, acci.pgimPersona.noPersona, "
			+ "acci.pgimPersona.apPaterno, acci.pgimPersona.apMaterno, "
			+ "acci.pgimPersona.feNacimiento, acci.pgimPersona.diPersona, "
			+ "acci.pgimPersona.deTelefono, acci.feFallecimiento, acci.categoriaOcupacional.idValorParametro, "
			+ "acci.pgimEmpresaEvento.idEmpresaEvento) " 
			+ "FROM PgimAccidentado acci "
			+ "WHERE acci.esRegistro = '1' "
			+ "AND (:idAccidentado IS NULL OR acci.idAccidentado != :idAccidentado) "
			+ "AND acci.pgimPersona.tipoDocIdentidad.idValorParametro = :idTipoDocumento "
			+ "AND acci.pgimPersona.coDocumentoIdentidad = :numeroDocumento ")
	List<PgimAccidentadoDTO> existeAccidentado(@Param("idAccidentado") Long idAccidentado, @Param("idTipoDocumento") Long idTipoDocumento, @Param("numeroDocumento") String numeroDocumento);
	
	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimAccidentadoDTOResultado("
			+ "acci.idAccidentado ) " 
			+ "FROM PgimAccidentado acci "
			+ "WHERE acci.esRegistro = '1' "
			+ "AND acci.pgimPersona.idPersona = :idPersona ")
	List<PgimAccidentadoDTO> listarAccidentadoPorPersona(@Param("idPersona") Long idPersona);

}
