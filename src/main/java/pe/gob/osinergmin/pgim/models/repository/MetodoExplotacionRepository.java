package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimMtdoExplotacionDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimMtdoExplotacion;

/**
 * Ésta interface MetodoExplotacionRepository incluye los metodos que permitirá listar sus
 * propiedades de metodo de esplotacion de la unidad minera.
 * 
 * @descripción: Logica de negocio de la entidad Método explotación
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 03/05/2020
 * @fecha_de_ultima_actualización: 10/07/2020
 */
@Repository
public interface MetodoExplotacionRepository extends JpaRepository<PgimMtdoExplotacion, Long> {

	@Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimMtdoExplotacionDTOResultado( "
            + "meex.idMetodoExplotacion, meex.noMetodoExplotacion, meex.deMetodoExplotacion, meex.metodoMinado.idValorParametro) "
            + "FROM PgimMtdoExplotacion meex WHERE meex.esRegistro = '1' ORDER BY meex.noMetodoExplotacion")
    public List<PgimMtdoExplotacionDTO> listar();
	

}