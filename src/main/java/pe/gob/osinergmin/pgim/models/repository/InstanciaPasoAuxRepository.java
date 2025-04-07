package pe.gob.osinergmin.pgim.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimInstanPasoAux;

/**
 * En ésta interface InstanciaPasoAuxRepository esta conformado pos sus metodos de listar
 * persona destino,
 * obtener sus propiedades.
 * 
 * @descripción: Lógica de negocio de la entidad Instancia paso proceso
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/06/2020
 * @fecha_de_ultima_actualización: 25/06/2020 
 */
@Repository
public interface InstanciaPasoAuxRepository extends JpaRepository<PgimInstanPasoAux, Long> {

    /**
     * Permite obtener el objeto de instancia de paso auxiliar.
     * @param idInstanciaPaso Identificador interno de la instancia de paso.
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado( "
            + "inpaaux.idInstanciaPasoAux, inpaaux.idInstanciaPaso, inpaaux.idInstanciaProceso, "
            + "inpaaux.idRelacionPaso, inpaaux.idPersonaEqpOrigen, inpaaux.idPersonaEqpDestino, "
            + "inpaaux.feInstanciaPaso, inpaaux.deMensaje, inpaaux.flIniciaProceso, "
            + "inpaaux.flRequiereDestinatario, inpaaux.flRequiereAprobacion, inpaaux.idTipoRelacion, "
            + "inpaaux.desNoTipoRelacion, inpaaux.idPasoProcesoOrigen, inpaaux.noPasoProcesoOrigen, "
            + "inpaaux.dePasoProcesoOrigen, inpaaux.idPasoProcesoDestino, inpaaux.noPasoProcesoDestino, "
            + "inpaaux.dePasoProcesoDestino, inpaaux.idFaseProcesoOrigen, inpaaux.noFaseProcesoOrigen, "
            + "inpaaux.deFaseProcesoOrigen, inpaaux.idFaseProcesoDestino, inpaaux.noFaseProcesoDestino, "
            + "inpaaux.deFaseProcesoDestino, inpaaux.idPersonaOrigen, inpaaux.noUsuarioOrigen, "
            + "inpaaux.coUsuarioSigedOrigen, inpaaux.noPersonaOrigen, inpaaux.apPaternoOrigen, "
            + "inpaaux.apMaternoOrigen, inpaaux.idPersonaDestino, inpaaux.noUsuarioDestino, "
            + "inpaaux.coUsuarioSigedDestino, inpaaux.noPersonaDestino, inpaaux.apPaternoDestino, "
            + "inpaaux.apMaternoDestino, inpaaux.idRolProcesoOrigen, inpaaux.noRolProcesoOrigen, "
            + "inpaaux.deRolProcesoOrigen, inpaaux.idRolProcesoDestino, inpaaux.noRolProcesoDestino, "
            + "inpaaux.deRolProcesoDestino, inpaaux.deEntidadPersonaOrigen, inpaaux.deEntidadPersonaDestino, "
            + "inpaaux.noPersonaOrigen || ' ' || inpaaux.apPaternoOrigen || ' ' || inpaaux.apMaternoOrigen, "
            + "inpaaux.noPersonaDestino || ' ' || inpaaux.apPaternoDestino || ' ' || inpaaux.apMaternoDestino, "
            + "inpaaux.tipoAccionSiged.idValorParametro, inpaaux.deTipoAccionSiged, inpaaux.flNotificableE, "
            + "inpaaux.noProceso, inpaaux.noEtiquetaOtrabajo, inpaaux.flEsPasoActivo, "
            + "inpaaux.idTipoSubflujo "
            + ") "
            + "FROM PgimInstanPasoAux inpaaux " 
            + "WHERE inpaaux.idInstanciaPaso = :idInstanciaPaso")
    PgimInstanPasoAuxDTO obtenerInstanciaPasoAuxPorId(@Param("idInstanciaPaso") Long idInstanciaPaso);

    /**
     * Permite obtener la trazabilidad de acuerdo con la instancia de proceso.
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado( "
            + "inpaaux.idInstanciaPasoAux, inpaaux.idInstanciaPaso, inpaaux.idInstanciaProceso, "
            + "inpaaux.idRelacionPaso, inpaaux.idPersonaEqpOrigen, inpaaux.idPersonaEqpDestino, "
            + "inpaaux.feInstanciaPaso, inpaaux.deMensaje, inpaaux.flIniciaProceso, "
            + "inpaaux.flRequiereDestinatario, inpaaux.flRequiereAprobacion, inpaaux.idTipoRelacion, "
            + "inpaaux.desNoTipoRelacion, inpaaux.idPasoProcesoOrigen, inpaaux.noPasoProcesoOrigen, "
            + "inpaaux.dePasoProcesoOrigen, inpaaux.idPasoProcesoDestino, inpaaux.noPasoProcesoDestino, "
            + "inpaaux.dePasoProcesoDestino, inpaaux.idFaseProcesoOrigen, inpaaux.noFaseProcesoOrigen, "
            + "inpaaux.deFaseProcesoOrigen, inpaaux.idFaseProcesoDestino, inpaaux.noFaseProcesoDestino, "
            + "inpaaux.deFaseProcesoDestino, inpaaux.idPersonaOrigen, inpaaux.noUsuarioOrigen, "
            + "inpaaux.coUsuarioSigedOrigen, inpaaux.noPersonaOrigen, inpaaux.apPaternoOrigen, "
            + "inpaaux.apMaternoOrigen, inpaaux.idPersonaDestino, inpaaux.noUsuarioDestino, "
            + "inpaaux.coUsuarioSigedDestino, inpaaux.noPersonaDestino, inpaaux.apPaternoDestino, "
            + "inpaaux.apMaternoDestino, inpaaux.idRolProcesoOrigen, inpaaux.noRolProcesoOrigen, "
            + "inpaaux.deRolProcesoOrigen, inpaaux.idRolProcesoDestino, inpaaux.noRolProcesoDestino, "
            + "inpaaux.deRolProcesoDestino, inpaaux.deEntidadPersonaOrigen, inpaaux.deEntidadPersonaDestino, "
            + "inpaaux.noPersonaOrigen || ' ' || inpaaux.apPaternoOrigen || ' ' || inpaaux.apMaternoOrigen, "
            + "inpaaux.noPersonaDestino || ' ' || inpaaux.apPaternoDestino || ' ' || inpaaux.apMaternoDestino, "
            + "inpaaux.tipoAccionSiged.idValorParametro, inpaaux.deTipoAccionSiged, inpaaux.flNotificableE, "
            + "inpaaux.noProceso, inpaaux.noEtiquetaOtrabajo, inpaaux.flEsPasoActivo, "
            + "inpaaux.idTipoSubflujo "
            + ") "
            + "FROM PgimInstanPasoAux inpaaux " 
            + "WHERE inpaaux.idInstanciaProceso = :idInstanciaProceso "
            )
    Page<PgimInstanPasoAuxDTO> obtenerInstanciaPasoAuxPorInstanciaProceso(@Param("idInstanciaProceso") Long idInstanciaProceso, Pageable paginador);
    
    
    /**
     * Permite obtener la trazabilidad completa de acuerdo con la instancia de proceso en un List 
     * para ser usado en la descarga del reporte
     * @param idInstanciaProceso Identificador interno de la instancia del proceso.
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado( "
            + "inpaaux.idInstanciaPasoAux, inpaaux.idInstanciaPaso, inpaaux.idInstanciaProceso, "
            + "inpaaux.idRelacionPaso, inpaaux.idPersonaEqpOrigen, inpaaux.idPersonaEqpDestino, "
            + "inpaaux.feInstanciaPaso, inpaaux.deMensaje, inpaaux.flIniciaProceso, "
            + "inpaaux.flRequiereDestinatario, inpaaux.flRequiereAprobacion, inpaaux.idTipoRelacion, "
            + "inpaaux.desNoTipoRelacion, inpaaux.idPasoProcesoOrigen, inpaaux.noPasoProcesoOrigen, "
            + "inpaaux.dePasoProcesoOrigen, inpaaux.idPasoProcesoDestino, inpaaux.noPasoProcesoDestino, "
            + "inpaaux.dePasoProcesoDestino, inpaaux.idFaseProcesoOrigen, inpaaux.noFaseProcesoOrigen, "
            + "inpaaux.deFaseProcesoOrigen, inpaaux.idFaseProcesoDestino, inpaaux.noFaseProcesoDestino, "
            + "inpaaux.deFaseProcesoDestino, inpaaux.idPersonaOrigen, inpaaux.noUsuarioOrigen, "
            + "inpaaux.coUsuarioSigedOrigen, inpaaux.noPersonaOrigen, inpaaux.apPaternoOrigen, "
            + "inpaaux.apMaternoOrigen, inpaaux.idPersonaDestino, inpaaux.noUsuarioDestino, "
            + "inpaaux.coUsuarioSigedDestino, inpaaux.noPersonaDestino, inpaaux.apPaternoDestino, "
            + "inpaaux.apMaternoDestino, inpaaux.idRolProcesoOrigen, inpaaux.noRolProcesoOrigen, "
            + "inpaaux.deRolProcesoOrigen, inpaaux.idRolProcesoDestino, inpaaux.noRolProcesoDestino, "
            + "inpaaux.deRolProcesoDestino, inpaaux.deEntidadPersonaOrigen, inpaaux.deEntidadPersonaDestino, "
            + "inpaaux.noPersonaOrigen || ' ' || inpaaux.apPaternoOrigen || ' ' || inpaaux.apMaternoOrigen, "
            + "inpaaux.noPersonaDestino || ' ' || inpaaux.apPaternoDestino || ' ' || inpaaux.apMaternoDestino, "
            + "inpaaux.tipoAccionSiged.idValorParametro, inpaaux.deTipoAccionSiged, inpaaux.flNotificableE, "
            + "inpaaux.noProceso, inpaaux.noEtiquetaOtrabajo, inpaaux.flEsPasoActivo, "
            + "inpaaux.idTipoSubflujo "
            + ") "
            + "FROM PgimInstanPasoAux inpaaux " 
            + "WHERE inpaaux.idInstanciaProceso = :idInstanciaProceso "
            )
    List<PgimInstanPasoAuxDTO> obtenerInstanciaPasoAuxPorInstanciaProcesoList(@Param("idInstanciaProceso") Long idInstanciaProceso, Sort sort);
    

    /**
     * Permite listar los nombres completos de la persona destino
     * 
     * @param palabraClave
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado(" + "pers.idPersona, "
                    + "pers.noPersona || ' ' || pers.apPaterno || ' ' || pers.apMaterno ) " + "FROM PgimPersona pers  "
                    + "WHERE pers.idPersona IN (SELECT inpaaux.idPersonaDestino FROM PgimInstanPasoAux inpaaux ) "
                    + "AND (:palabraClave IS NULL OR LOWER(pers.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                    + "OR LOWER(pers.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                    + "OR LOWER(pers.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  ) ")
    List<PgimInstanPasoAuxDTO> listarPorPersonaDestino(@Param("palabraClave") String palabraClave);

      /**
       * Permite listar los nombres completos de las personas destinos del Osinergmin en el reporte detallado de las fiscalizaciones
       * @param palabraClave
       * @return
       */
      @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado("
                      + "peosi.pgimPersona.idPersona, peosi.idPersonalOsi, "
                      + "peosi.pgimPersona.noPersona || ', ' || peosi.pgimPersona.apPaterno || ' ' || peosi.pgimPersona.apMaterno ) "
                      + "FROM PgimPersonalOsi peosi  "
                      + "WHERE peosi.esRegistro = '1' "
                      + "AND peosi.pgimPersona.esRegistro = '1' "
                      + "AND (:palabraClave IS NULL OR LOWER(peosi.pgimPersona.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                      + "OR LOWER(peosi.pgimPersona.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                      + "OR LOWER(peosi.pgimPersona.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  ) "
                      )
      List<PgimInstanPasoAuxDTO> listarPorPersonaOsiDestino(@Param("palabraClave") String palabraClave);

      /**
       * Permite listar los nombres completos de las personas destinos de los contratos en el reporte detallado de las fiscalizaciones
       * @param palabraClave
       * @return
       */
      @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado("
                      + "peco.pgimPersona.idPersona, peco.idPersonalContrato, peco.pgimContrato.nuContrato, "
                      + "peco.pgimPersona.noPersona || ', ' || peco.pgimPersona.apPaterno || ' ' || peco.pgimPersona.apMaterno ) "
                      + "FROM PgimPersonalContrato peco  "
                      + "WHERE peco.esRegistro = '1' AND peco.pgimContrato.esRegistro = '1' AND peco.pgimContrato.flEstado = '1' "
                      + "AND (:palabraClave IS NULL OR LOWER(peco.pgimPersona.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                      + "OR LOWER(peco.pgimPersona.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                      + "OR LOWER(peco.pgimPersona.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  ) "
                      )
      List<PgimInstanPasoAuxDTO> listarPorPersonaContratoDestino(@Param("palabraClave") String palabraClave);
     
      /**
       * Permite listar los nombres completos de la persona destino que son del contrato de acuerdo al filtro seleccionado en el reporte detallado de las fiscalizaciones
       * @param idContrato
       * @param palabraClave
       * @return
       */
      @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimInstanPasoAuxDTOResultado("
                      + "peco.pgimPersona.idPersona, peco.idPersonalContrato, peco.pgimContrato.nuContrato, "
                      + "peco.pgimPersona.noPersona || ', ' || peco.pgimPersona.apPaterno || ' ' || peco.pgimPersona.apMaterno ) "
                      + "FROM PgimPersonalContrato peco  "
                      + "WHERE peco.esRegistro = '1' AND peco.pgimContrato.esRegistro = '1' AND peco.pgimContrato.flEstado = '1' "
                      + "AND peco.pgimContrato.idContrato = :idContrato "
                      + "AND (:palabraClave IS NULL OR LOWER(peco.pgimPersona.noPersona) LIKE LOWER(CONCAT('%', :palabraClave, '%')) "
                      + "OR LOWER(peco.pgimPersona.apPaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  "
                      + "OR LOWER(peco.pgimPersona.apMaterno) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  ) "
                      )
      List<PgimInstanPasoAuxDTO> listarPersonaDestinoPorContrato(@Param("idContrato") Long idContrato, @Param("palabraClave") String palabraClave);

}