package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimSegAccidentadoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimSegAccidentado;

import java.util.List;

/**
 * Ésta interface SeguroAccidentadoRepository incluye el metodo listar el seguro del accidentado.
 * 
 * @descripción: Logica de negocio de la entidad Seguro accidentado
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface SeguroAccidentadoRepository extends JpaRepository<PgimSegAccidentado, Long> {

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimSegAccidentadoDTOResultado(seac.idSeguroAccidentado, seac.pgimAccidentado.idAccidentado, seac.tipoSeguro.idValorParametro) "
            + "FROM PgimSegAccidentado seac WHERE seac.pgimAccidentado.idAccidentado = :idAccidentado AND seac.esRegistro = '1' "
            + "ORDER BY seac.tipoSeguro.nuOrden")
    List<PgimSegAccidentadoDTO> listar(@Param("idAccidentado") Long idAccidentado);

}