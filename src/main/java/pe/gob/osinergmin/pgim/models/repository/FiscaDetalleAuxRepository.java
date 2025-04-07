package pe.gob.osinergmin.pgim.models.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.gob.osinergmin.pgim.dtos.PgimFiscaDetalleAuxDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimFiscaDetalleAux;

/**
 * @descripción: Lógica de negocio de la Vista para la lista de reporte de detalles de fiscalizaciones
 * 
 * @author: jvaleriom
 * @version: 1.0
 * @fecha_de_creación: 10/10/2020
 * @fecha_de_ultima_actualización: 10/11/2020
 */
public interface FiscaDetalleAuxRepository extends JpaRepository<PgimFiscaDetalleAux, Long>{
    
    /**
     * Me permite listar los detalles de las fiscalizaciones que estan paginadas y contiene criterios de filtros avanzados
     * @param nuAnioPlan
     * @param nuContrato
     * @param paginador
     * @return
     */
    @Query("SELECT new pe.gob.osinergmin.pgim.dtos.PgimFiscaDetalleAuxDTOResultado( "
			+ "aux.nuAnioPlan, aux.noEspecialidad, aux.noDivisionSupervisora, aux.coFiscalizacion, aux.nuExpedienteFisca, aux.feInicioPrevistaFisca, "
            + "aux.feFinPrevistaFisca, aux.feInicioRealFisca, aux.feFinRealFisca, aux.esPropia, aux.noTipoFiscaliza, aux.noSubtipoFiscaliza, " 
            + "aux.noMotivoFiscaliza, aux.coUnidadFiscalizable, aux.noUnidadFiscalizable, aux.rucAgenteFiscalizado, aux.noAgenteFiscalizado, aux.noFaseOrigen, " 
            + "aux.noTareaOrigen, aux.noFaseDestino, aux.noTareaDestino, aux.feAsignaTarea, aux.deMensajeTarea, aux.noTipoTransicion, " 
            + "aux.noTipoPersonaOrigen, aux.noPersonaOrigen, aux.noTipoPersonaDestino, aux.noPersonaDestino, aux.nuContrato, aux.rucEmpresaSupervisora, " 
            + "aux.noEmpresaSupervisora " 
            + ") "
            + "FROM PgimFiscaDetalleAux aux "  
            + "WHERE 1 = 1 "
            
			+ "AND (:nuAnioPlan IS NULL OR LOWER(aux.nuAnioPlan) LIKE LOWER(CONCAT('%', :nuAnioPlan, '%')) ) "
            + "AND (:idDivisionSupervisora IS NULL OR LOWER(aux.idDivisionSupervisora) LIKE LOWER(CONCAT('%', :idDivisionSupervisora, '%')) ) "
            + "AND (:idEspecialidad IS NULL OR LOWER(aux.idEspecialidad) LIKE LOWER(CONCAT('%', :idEspecialidad, '%')) ) "
            + "AND (:nuContrato IS NULL OR LOWER(aux.nuContrato) LIKE LOWER(CONCAT('%', :nuContrato, '%')) ) "
            + "AND (:idTipoFiscalizacion IS NULL OR LOWER(aux.idTipoFiscaliza) LIKE LOWER(CONCAT('%', :idTipoFiscalizacion, '%')) ) "
            + "AND (:propiedad IS NULL OR LOWER(aux.esPropia) LIKE LOWER(CONCAT('%', :propiedad, '%')) ) "
            + "AND ( " 
            + "     (:feInicioPrevistaFisca IS NULL OR TRUNC(aux.feInicioPrevistaFisca) BETWEEN :feInicioPrevistaFisca AND :feFinPrevistaFisca) "
            + " AND " 
            + "     (:feFinPrevistaFisca IS NULL OR TRUNC(aux.feFinPrevistaFisca) BETWEEN :feInicioPrevistaFisca AND :feFinPrevistaFisca) " 
            + "    ) " 
            + "AND ( " 
            + "     (:feInicioRealFisca IS NULL OR TRUNC(aux.feInicioRealFisca) BETWEEN :feInicioRealFisca AND :feFinRealFisca) "
            + " AND " 
            + "     (:feFinRealFisca IS NULL OR TRUNC(aux.feFinRealFisca) BETWEEN :feInicioRealFisca AND :feFinRealFisca) " 
            + "    ) " 
            // + "AND (( " 
            // + "     :feInicioPrevistaFisca IS NULL OR TRUNC(aux.feInicioPrevistaFisca) >= :feInicioPrevistaFisca) "
            // + " AND " 
            // + "     (:feFinPrevistaFisca IS NULL OR TRUNC(aux.feFinPrevistaFisca) <= :feFinPrevistaFisca " 
            // + "    )) " 
            // + "AND (( " 
            // + "     :feInicioRealFisca IS NULL OR TRUNC(aux.feInicioRealFisca) >= :feInicioRealFisca) "
            // + " AND " 
            // + "     (:feFinRealFisca IS NULL OR TRUNC(aux.feFinRealFisca) <= :feFinRealFisca " 
            // + "    )) " 
            + "AND (:noPersonaDestino IS NULL OR LOWER(aux.noPersonaDestino) LIKE LOWER(CONCAT('%', :noPersonaDestino, '%')) ) "
            )
    Page<PgimFiscaDetalleAuxDTO> listarDetalleFiscalizaciones (@Param("nuAnioPlan") Long nuAnioPlan, 
    @Param("idDivisionSupervisora") Long idDivisionSupervisora, 
    @Param("idEspecialidad") Long idEspecialidad, 
    @Param("nuContrato") String nuContrato, 
    @Param("idTipoFiscalizacion") Long idTipoFiscalizacion, 
    @Param("propiedad") String propiedad, 
    @Param("feInicioPrevistaFisca") Date feInicioPrevistaFisca, 
    @Param("feFinPrevistaFisca") Date feFinPrevistaFisca, 
    @Param("feInicioRealFisca") Date feInicioRealFisca, 
    @Param("feFinRealFisca") Date feFinRealFisca, 
    @Param("noPersonaDestino") String noPersonaDestino, 
    Pageable paginador
    );
}
