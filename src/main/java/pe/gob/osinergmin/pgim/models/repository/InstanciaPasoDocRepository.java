package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDocDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanciaPasoDoc;

/**
 * Interfaz que herada de JpaRepository para facilitar las operaciones sobre los objetos de base de datos.
 * 
 * @descripción: Lógica de negocio de la entidad Instancia paso de documento
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 20/08/2021
  */
@Repository
public interface InstanciaPasoDocRepository extends JpaRepository<PgimInstanciaPasoDoc, Long> {

    /**
     * Permite obtener la lista de documentos que fueron seleccionados en el conteto de una instancia de paso al momento de realizar la asignación.
     * @param idInstanciaPaso
     * @return
     */
     @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanciaPasoDocDTOResultado( "
            + "inpd.idInstanciaPasoDoc, inpd.pgimInstanciaPaso.idInstanciaPaso, inpd.pgimDocumento.idDocumento, "
            + "inpd.feTransicionPasoDoc, inpd.deMensaje) " 
            + "FROM PgimInstanciaPasoDoc inpd "
            + "WHERE inpd.pgimInstanciaPaso.idInstanciaPaso = :idInstanciaPaso")
    List<PgimInstanciaPasoDocDTO> obtenerInstanciaPasosActuales(@Param("idInstanciaPaso") Long idInstanciaPaso);

}