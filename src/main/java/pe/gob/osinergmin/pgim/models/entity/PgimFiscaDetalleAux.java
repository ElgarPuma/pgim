package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase Entidad para la tabla PGIM_VC_FISCA_DETALLE:
 * 
 * @descripción: Vista para la lista de detalles de fiscalizaciones
 *
 * @author: hdiaz
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Entity
@Table(name = "PGIM_VC_FISCA_DETALLE")
@Data
@NoArgsConstructor
public class PgimFiscaDetalleAux implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * NU_ANIO_PLAN
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGIM_VC_FISCA_DETALLE_AUX")
    @SequenceGenerator(name = "PGIM_VC_FISCA_DETALLE_AUX", sequenceName = "PGIM_VC_FISCA_DETALLE_AUX", allocationSize = 1)
    @Column(name = "NU_ANIO_PLAN", nullable = true)
    private Long nuAnioPlan;

    /*
     * ID_ESPECIALIDAD
     */
    @Column(name = "ID_ESPECIALIDAD", nullable = true)
    private Long idEspecialidad;

    /*
     * NO_ESPECIALIDAD
     */
    @Column(name = "NO_ESPECIALIDAD", nullable = true)
    private String noEspecialidad;

    /*
     * ID_DIVISION_SUPERVISORA
     */
    @Column(name = "ID_DIVISION_SUPERVISORA", nullable = true)
    private Long idDivisionSupervisora;

    /*
     * NO_DIVISION_SUPERVISORA
     */
    @Column(name = "NO_DIVISION_SUPERVISORA", nullable = true)
    private String noDivisionSupervisora;

    /*
     * CO_FISCALIZACION
     */
    @Column(name = "CO_FISCALIZACION", nullable = true)
    private String coFiscalizacion;

    /*
     * NU_EXPEDIENTE_FISCA
     */
    @Column(name = "NU_EXPEDIENTE_FISCA", nullable = true)
    private String nuExpedienteFisca;

    /*
     * FE_INICIO_PREVISTA_FISCA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_INICIO_PREVISTA_FISCA", nullable = true)
    private Date feInicioPrevistaFisca;

    /*
     * FE_FIN_PREVISTA_FISCA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_FIN_PREVISTA_FISCA", nullable = true)
    private Date feFinPrevistaFisca;

    /*
     * FE_INICIO_REAL_FISCA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_INICIO_REAL_FISCA", nullable = true)
    private Date feInicioRealFisca;

    /*
     * FE_FIN_REAL_FISCA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_FIN_REAL_FISCA", nullable = true)
    private Date feFinRealFisca;

    /*
     * ES_PROPIA
     */
    @Column(name = "ES_PROPIA", nullable = true)
    private String esPropia;

    /*
     * ID_TIPO_FISCALIZA
     */
    @Column(name = "ID_TIPO_FISCALIZA", nullable = true)
    private Long idTipoFiscaliza;

    /*
     * NO_TIPO_FISCALIZA
     */
    @Column(name = "NO_TIPO_FISCALIZA", nullable = true)
    private String noTipoFiscaliza;

    /*
     * ID_SUBTIPO_FISCALIZA
     */
    @Column(name = "ID_SUBTIPO_FISCALIZA", nullable = true)
    private Long idSubtipoFiscaliza;

    /*
     * NO_SUBTIPO_FISCALIZA
     */
    @Column(name = "NO_SUBTIPO_FISCALIZA", nullable = true)
    private String noSubtipoFiscaliza;

    /*
     * ID_MOTIVO_FISCALIZA
     */
    @Column(name = "ID_MOTIVO_FISCALIZA", nullable = true)
    private Long idMotivoFiscaliza;

    /*
     * NO_MOTIVO_FISCALIZA
     */
    @Column(name = "NO_MOTIVO_FISCALIZA", nullable = true)
    private String noMotivoFiscaliza;

    /*
     * ID_UNIDAD_FISCALIZABLE
     */
    @Column(name = "ID_UNIDAD_FISCALIZABLE", nullable = true)
    private Long idUnidadFiscalizable;

    /*
     * CO_UNIDAD_FISCALIZABLE
     */
    @Column(name = "CO_UNIDAD_FISCALIZABLE", nullable = true)
    private String coUnidadFiscalizable;

    /*
     * NO_UNIDAD_FISCALIZABLE
     */
    @Column(name = "NO_UNIDAD_FISCALIZABLE", nullable = true)
    private String noUnidadFiscalizable;

    /*
     * RUC_AGENTE_FISCALIZADO
     */
    @Column(name = "RUC_AGENTE_FISCALIZADO", nullable = true)
    private String rucAgenteFiscalizado;

    /*
     * NO_AGENTE_FISCALIZADO
     */
    @Column(name = "NO_AGENTE_FISCALIZADO", nullable = true)
    private String noAgenteFiscalizado;

    /*
     * NO_FASE_ORIGEN
     */
    @Column(name = "NO_FASE_ORIGEN", nullable = true)
    private String noFaseOrigen;

    /*
     * NO_TAREA_ORIGEN
     */
    @Column(name = "NO_TAREA_ORIGEN", nullable = true)
    private String noTareaOrigen;

    /*
     * NO_FASE_DESTINO
     */
    @Column(name = "NO_FASE_DESTINO", nullable = true)
    private String noFaseDestino;

    /*
     * NO_TAREA_DESTINO
     */
    @Column(name = "NO_TAREA_DESTINO", nullable = true)
    private String noTareaDestino;

    /*
     * FE_ASIGNA_TAREA
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FE_ASIGNA_TAREA", nullable = true)
    private Date feAsignaTarea;

    /*
     * DE_MENSAJE_TAREA
     */
    @Column(name = "DE_MENSAJE_TAREA", nullable = true)
    private String deMensajeTarea;

    /*
     * NO_TIPO_TRANSICION
     */
    @Column(name = "NO_TIPO_TRANSICION", nullable = true)
    private String noTipoTransicion;

    /*
     * NO_TIPO_PERSONA_ORIGEN
     */
    @Column(name = "NO_TIPO_PERSONA_ORIGEN", nullable = true)
    private String noTipoPersonaOrigen;

    /*
     * NO_PERSONA_ORIGEN
     */
    @Column(name = "NO_PERSONA_ORIGEN", nullable = true)
    private String noPersonaOrigen;

    /*
     * NO_TIPO_PERSONA_DESTINO
     */
    @Column(name = "NO_TIPO_PERSONA_DESTINO", nullable = true)
    private String noTipoPersonaDestino;

    /*
     * NO_PERSONA_DESTINO
     */
    @Column(name = "NO_PERSONA_DESTINO", nullable = true)
    private String noPersonaDestino;

    /*
     * ID_CONTRATO
     */
    @Column(name = "ID_CONTRATO", nullable = true)
    private Long idContrato;

    /*
     * NU_CONTRATO
     */
    @Column(name = "NU_CONTRATO", nullable = true)
    private String nuContrato;

    /*
     * ID_PERSONA_SUPERVISORA
     */
    @Column(name = "ID_PERSONA_SUPERVISORA", nullable = true)
    private String idPersonaSupervisora;

    /*
     * RUC_EMPRESA_SUPERVISORA
     */
    @Column(name = "RUC_EMPRESA_SUPERVISORA", nullable = true)
    private String rucEmpresaSupervisora;

    /*
     * NO_EMPRESA_SUPERVISORA
     */
    @Column(name = "NO_EMPRESA_SUPERVISORA", nullable = true)
    private String noEmpresaSupervisora;
}

