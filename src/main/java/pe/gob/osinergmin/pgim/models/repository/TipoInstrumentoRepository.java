package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimTipoInstrumento;

/**
 * TipoInstrumentoRepository incluye el metodo para listar en el maestro
 * los tipos de instrumentos de medición.
 * 
 * @descripción: Lógica de negocio de la entidad PgimTipoInstrumento
 * 
 * @author: LEGION
 * @version: 1.0
 * @fecha_de_creación: 17/08/2022
 */
@Repository
public interface TipoInstrumentoRepository extends JpaRepository<PgimTipoInstrumento, Long>{

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTOResultado(" 
	    + "tipo.idTipoInstrumento, "
		+ "tipo.pgimEspecialidad.idEspecialidad, tipo.pgimEspecialidad.noEspecialidad, "
        + "tipo.coTipoInstrumento, tipo.noTipoInstrumento, "
        + "tipo.deTipoInstrumento ) " 
        + "FROM PgimTipoInstrumento tipo WHERE tipo.esRegistro = '1'  "
        + "AND (:noTipoInstrumento IS NULL OR (LOWER(tipo.noTipoInstrumento) LIKE LOWER(CONCAT('%', :noTipoInstrumento, '%')) "
        + ")) "
        + "AND (:idEspecialidad IS NULL OR (tipo.pgimEspecialidad.idEspecialidad = :idEspecialidad )) "
        + "AND (:textoBusqueda IS NULL OR ( LOWER(tipo.pgimEspecialidad.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
        + "OR LOWER(tipo.noTipoInstrumento) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) " 
        + ")) "
        )
	Page<PgimTipoInstrumentoDTO> listarTipoInstrumento(
        @Param("textoBusqueda") String textoBusqueda,
        @Param("idEspecialidad") Long idEspecialidad,
        @Param("noTipoInstrumento") String noTipoInstrumento,
        Pageable paginador);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTOResultado(" 
	    + "tipo.idTipoInstrumento, "
		+ "tipo.pgimEspecialidad.idEspecialidad, tipo.pgimEspecialidad.noEspecialidad, "
        + "tipo.coTipoInstrumento, tipo.noTipoInstrumento, "
        + "tipo.deTipoInstrumento ) " 
        + "FROM PgimTipoInstrumento tipo WHERE tipo.esRegistro = '1'  "
        + "AND tipo.idTipoInstrumento = :idTipoInstrumento"
        )
	PgimTipoInstrumentoDTO obtenerTipoInstrumento(@Param("idTipoInstrumento") Long idTipoInstrumento);

    @Query("SELECT max(tipo.coTipoInstrumento) "
        + "FROM PgimTipoInstrumento tipo "
        + "INNER JOIN tipo.pgimEspecialidad esp "
        + "WHERE tipo.esRegistro = '1' "
        + "AND esp.idEspecialidad = :idEspecialidad "
        + "ORDER BY tipo.idTipoInstrumento desc"
        )
    String obtenerUtimoCoorelativoTI(@Param("idEspecialidad") Long idEspecialidad);

    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimTipoInstrumentoDTOResultado(" 
        + "tipo.idTipoInstrumento, "
        + "esp.idEspecialidad, esp.noEspecialidad, "
        + "tipo.coTipoInstrumento, tipo.noTipoInstrumento, "
        + "tipo.deTipoInstrumento ) " 
        + "FROM PgimTipoInstrumento tipo " 
        + "INNER JOIN tipo.pgimEspecialidad esp "
        + "WHERE tipo.esRegistro = '1'  "
        + "AND esp.idEspecialidad = :idEspecialidad "
        + "AND LOWER(TRIM(tipo.noTipoInstrumento)) LIKE LOWER(TRIM(:noTipoInstrumento)) "
        )
    PgimTipoInstrumentoDTO obtenerInstrumentoExistente(@Param("idEspecialidad") Long idEspecialidad, @Param("noTipoInstrumento") String noTipoInstrumento);


}