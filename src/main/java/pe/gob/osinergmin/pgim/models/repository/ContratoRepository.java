package pe.gob.osinergmin.pgim.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;

import java.util.List;

/**
 * @descripción: Logica de negocio de la entidad contrato.
 * 
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 15/07/2020
 * @fecha_de_ultima_actualización: 17/07/2020
 */
@Repository
public interface ContratoRepository extends JpaRepository<PgimContrato, Long> {

        /**
         * Se utiliza para visualizar la lista seleccionable(Contrato) en el dialogo
         * Asignación de supervisión descripcion [Contrato] - [Supervisora]
         * 
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado(" 
             + "pc.idContrato,  "
             + "pc.nuContrato,  "
             + "pc.nuContrato || ' - ' || pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, "
             + "pc.pgimEspecialidad.idEspecialidad, " 
             + "pc.flEstado "
             + ") " 
             + "FROM PgimContrato pc "
             + "WHERE pc.flEstado = '1' " + "AND pc.esRegistro = '1'")
        List<PgimContratoDTO> obtenerContratos();

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado(" 
             + "pc.idContrato,  "
             + "pc.nuContrato,  "
             + "pc.nuContrato || ' - ' || pc.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, "
             + "pc.pgimEspecialidad.idEspecialidad, " 
             + "pc.flEstado " 
             + ") " 
             + "FROM PgimContrato pc "
             + "WHERE EXISTS (SELECT 1 " 
             + "FROM PgimPersonalContrato peco "
             + "WHERE peco.pgimContrato = pc " 
             + "AND peco.noUsuario = :userName "
             + "AND peco.esRegistro = '1') "
             + "AND pc.esRegistro = '1'"
             + "ORDER BY pc.flEstado DESC, pc.feFinContrato DESC, pc.feInicioContrato DESC"
             )
        List<PgimContratoDTO> obtenerContratos(@Param("userName") String userName);

        /**
         * Me permite filtrar por nombre de contrato de la supervision
         * 
         * @param nombre
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado( "
                        + "contr.idContrato, contr.deContrato || ' - ' || contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial ) "
                        + "FROM PgimContrato contr WHERE contr.esRegistro = '1' ")
        List<PgimContratoDTO> filtrarPorNombreContratoSupervision(@Param("nombre") String nombre);

        /**
         * Permite listar los contratos de acuerdo con los criterios filtro
         * especificados.
         *
         * @param nuContrato
         * @param noRazonSocial
         * @param nuExpedienteSiged
         * @param idEspecialidad
         * @param textoBusqueda
         * @param paginador
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado(" 
                        + "contr.idContrato, contr.nuContrato, contr.deContrato, "
                        + "contr.feInicioContrato, contr.feFinContrato, contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, "
                        + "contr.pgimEspecialidad.noEspecialidad, contr.pgimInstanciaProces.nuExpedienteSiged, contr.moImporteContrato, "
                        + "contr.flEstado "
                        + ") " 
                        + "FROM PgimContrato contr " 
                        + "WHERE contr.esRegistro = '1' "
                        + "AND (:nuContrato IS NULL OR LOWER(contr.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
                        + "AND (:noRazonSocial IS NULL OR LOWER(contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :noRazonSocial, '%')) ) "
                        + "AND (:nuExpedienteSiged IS NULL OR LOWER(contr.pgimInstanciaProces.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :nuExpedienteSiged, '%')) ) "
                        + "AND (:idEspecialidad IS NULL OR contr.pgimEspecialidad.idEspecialidad = :idEspecialidad) "
                        + "AND (:textoBusqueda IS NULL OR ( "
                        + "LOWER(contr.nuContrato ||' - '|| contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(contr.pgimInstanciaProces.nuExpedienteSiged) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + "OR LOWER(contr.pgimEspecialidad.noEspecialidad) LIKE LOWER(CONCAT('%', :textoBusqueda, '%')) "
                        + " )) ")
        Page<PgimContratoDTO> listarContratos(
                        @Param("nuContrato") String nuContrato, @Param("noRazonSocial") String noRazonSocial,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged,
                        @Param("idEspecialidad") Long idEspecialidad, @Param("textoBusqueda") String textoBusqueda,
                        Pageable paginador);

        /**
         * Permite obtener la lista de contratos que coinciden con la palabra clave.
         * 
         * @param palabraClave Código o numero de contrato.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.nuContrato, contr.deContrato, "
                        + "contr.pgimInstanciaProces.idInstanciaProceso "
                        + ") "
                        + "FROM PgimContrato contr "
                        + "WHERE contr.esRegistro = '1' "
                        + "AND (:palabraClave IS NULL OR LOWER(contr.nuContrato) LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
        List<PgimContratoDTO> listarPorCodigoContrato(@Param("palabraClave") String palabraClave);

        /**
         * Permite obtener la lista de contratos que coinciden con el numero de
         * expediente.
         * 
         * @param palabraClave Código o numero de contrato.
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.pgimInstanciaProces.idInstanciaProceso, "
                        + "contr.pgimInstanciaProces.nuExpedienteSiged ) " + "FROM PgimContrato contr "
                        + "WHERE contr.esRegistro = '1' "
                        + "AND (:palabraClave IS NULL OR LOWER(contr.pgimInstanciaProces.nuExpedienteSiged) "
                        + "LIKE LOWER(CONCAT('%', :palabraClave, '%'))  )")
        List<PgimContratoDTO> listarPorNumeroExpediente(@Param("palabraClave") String palabraClave);

        /**
         * Obtener las propiedades del contrato por el idContrato
         * 
         * @param idContrato identificador del contrato
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.pgimEmpresaSupervisora.pgimPersona.idPersona, contr.nuContrato, " 
                        + "contr.feInicioContrato, contr.feFinContrato, "
                        + "iproc.idInstanciaProceso, " 
                        + "iproc.nuExpedienteSiged, "
                        + "contr.pgimEmpresaSupervisora.idEmpresaSupervisora, "
                        + "UPPER(contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial), " 
                        + "contr.deContrato, "
                        + "contr.pgimEspecialidad.idEspecialidad, contr.pgimEspecialidad.noEspecialidad, "
                        + "contr.pcEntregableActa, contr.pcEntregableInforme, contr.pcEntregableFinal, "
                        + "contr.moImporteContrato, contr.flEstado, contr.nuDiasEntregaInforme, contr.nuDiasAbsolucionInforme, "
                        + "contr.pgimEmpresaSupervisora.pgimPersona.flConsorcio, contr.nuDiasRevisionInforme, contr.noUsuarioXDefecto " 
                        + ") "
                        + "FROM PgimContrato contr, PgimInstanciaProces iproc "
                        + "WHERE contr.pgimInstanciaProces = iproc " 
                        + "AND contr.esRegistro = '1' "
                        + "AND contr.idContrato = :idContrato ")
        PgimContratoDTO obtenerContratoPorId(@Param("idContrato") Long idContrato);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.nuContrato, "
                        + "contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial, "
                        + "contr.pgimEspecialidad.noEspecialidad, "
                        + "case when contr.flEstado = '1' then 'Vigente' else 'No vigente' end "
                        + ") "
                        + "FROM PgimContrato contr " 
                        + "WHERE contr.esRegistro = '1' "
                        + "AND contr.idContrato = :idContrato ")
        PgimContratoDTO obtenerContrato(@Param("idContrato") Long idContrato);

        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.nuContrato ) " 
                        + "FROM PgimContrato contr "
                        + "WHERE contr.esRegistro = '1' " 
                        + "AND (:idContrato IS NULL OR contr.idContrato != :idContrato)  "
                        + "AND LOWER(contr.nuContrato) = LOWER(:nuContrato)")
        List<PgimContratoDTO> existeContrato(@Param("idContrato") Long idContrato,
                        @Param("nuContrato") String nuContrato);

        /**
         * Permite validar valores que sean unicos como el número de expediente Siged
         *
         * @param idInstanciaProceso
         * @param nuExpedienteSiged
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, iproc.idInstanciaProceso, iproc.nuExpedienteSiged, contr.deContrato ) "
                        + "FROM PgimContrato contr, PgimInstanciaProces iproc "
                        + "WHERE contr.esRegistro = '1' AND contr.pgimInstanciaProces = iproc "
                        // + "AND contr.idContrato = :idContrato "
                        + "AND (:idInstanciaProceso IS NULL OR iproc.idInstanciaProceso != :idInstanciaProceso) "
                        + "AND LOWER(iproc.nuExpedienteSiged) = LOWER(:nuExpedienteSiged)")
        List<PgimContratoDTO> existeNuExpediente(@Param("idInstanciaProceso") Long idInstanciaProceso,
                        @Param("nuExpedienteSiged") String nuExpedienteSiged);
        
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado( "
    			+ "coco.pgimContrato.idContrato, sum(coco.moConsumoContrato) ) "
    			+ "FROM PgimConsumoContra coco "
                + "WHERE coco.pgimContrato.idContrato = :idContrato "
                + "AND coco.esRegistro = '1' "
                + "GROUP BY coco.pgimContrato.idContrato"
                )
        PgimContratoDTO obtenerMontoConsumoContrato(@Param("idContrato") Long idContrato);
        
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado( "
    			+ "coco.pgimContrato.idContrato, sum(icc.moItemConsumo) ) "
    			+ "FROM PgimItemConsumo icc "
    			+ "JOIN PgimConsumoContra coco ON icc.pgimConsumoContra.idConsumoContra = coco.idConsumoContra "    			
                + "WHERE coco.pgimContrato.idContrato = :idContrato "
                + "AND icc.tipoEstadioConsumo.idValorParametro = :idTipoEstadioConsumo "
                + "AND coco.esRegistro = '1' "
                + "AND icc.esRegistro = '1' AND icc.esVigente = '1' "
                + "GROUP BY coco.pgimContrato.idContrato "
                )
        PgimContratoDTO obtenerMontoConsumoContratoPorEstadio(@Param("idContrato") Long idContrato,@Param("idTipoEstadioConsumo") Long idTipoEstadioConsumo);
        
        
        /**
         * Obtener las propiedades del contrato por el idSupervision
         * 
         * @param idSupervision identificador de la supervisión
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                        + "contr.idContrato, contr.pgimEmpresaSupervisora.pgimPersona.idPersona, contr.nuContrato, " 
                        + "contr.feInicioContrato, contr.feFinContrato, "
                        + "iproc.idInstanciaProceso, " 
                        + "iproc.nuExpedienteSiged, "
                        + "contr.pgimEmpresaSupervisora.idEmpresaSupervisora, "
                        + "UPPER(contr.pgimEmpresaSupervisora.pgimPersona.noRazonSocial), " 
                        + "contr.deContrato, "
                        + "contr.pgimEspecialidad.idEspecialidad, contr.pgimEspecialidad.noEspecialidad, "
                        + "contr.pcEntregableActa, contr.pcEntregableInforme, contr.pcEntregableFinal, "
                        + "contr.moImporteContrato, contr.flEstado, contr.nuDiasEntregaInforme, contr.nuDiasAbsolucionInforme, " 
                        + "contr.pgimEmpresaSupervisora.pgimPersona.flConsorcio, contr.nuDiasRevisionInforme, contr.noUsuarioXDefecto "
                        + ") "
                        + "FROM PgimContrato contr, PgimInstanciaProces iproc "
                        + "WHERE contr.pgimInstanciaProces = iproc " 
                        + "AND contr.esRegistro = '1' "
                        + "AND contr.idContrato in ("
                        + "select cc.pgimContrato.idContrato from PgimConsumoContra cc where cc.pgimSupervision.idSupervision = :idSupervision ) "
                        )
        PgimContratoDTO obtenerContratoPorIdSupervision(@Param("idSupervision") Long idSupervision);
        
        
        /**
         * Obtener las propiedades del contrato por el idLiquidacion
         * 
         * @param idLiquidacion identificador de la liquidación
         * @return
         */
        @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimContratoDTOResultado("
                		+ "contr.idContrato, contr.nuContrato, contr.deContrato, contr.pgimInstanciaProces.idInstanciaProceso ) "
                		+ "FROM PgimContrato contr "
                        + "WHERE contr.esRegistro = '1' "
                        + "AND contr.idContrato = ("
                        + "select liq.pgimContrato.idContrato from PgimLiquidacion liq where liq.idLiquidacion = :idLiquidacion ) "
                        )
        PgimContratoDTO obtenerContratoPorIdLiquidacion(@Param("idLiquidacion") Long idLiquidacion);
        

}
