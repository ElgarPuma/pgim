package pe.gob.osinergmin.pgim.dtos;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Entidad para la tabla PGIM_VC_FISCA_DETALLE:
 * 
 * @descripción: Vista para la lista de detalles de fiscalizaciones
 *
 * @author: jvalerio
 * @version 1.0
 * @fecha_de_creación: 02/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
public class PgimFiscaDetalleAuxDTO {

    /*
     * NU_ANIO_PLAN
     */
    private Long nuAnioPlan;

     /*
     * ID_ESPECIALIDAD
     */
    private Long idEspecialidad;

    /*
     * NO_ESPECIALIDAD
     */
    private String noEspecialidad;

    /*
     * ID_DIVISION_SUPERVISORA
     */
    private Long idDivisionSupervisora;

    /*
     * NO_DIVISION_SUPERVISORA
     */
    private String noDivisionSupervisora;

    /*
     * CO_FISCALIZACION
     */
    private String coFiscalizacion;

    /*
     * NU_EXPEDIENTE_FISCA
     */
    private String nuExpedienteFisca;

    /*
     * FE_INICIO_PREVISTA_FISCA
     */
    private Date feInicioPrevistaFisca;

    /*
     * FE_FIN_PREVISTA_FISCA
     */
    private Date feFinPrevistaFisca;

    /*
     * FE_INICIO_REAL_FISCA
     */
    private Date feInicioRealFisca;

    /*
     * FE_FIN_REAL_FISCA
     */
    private Date feFinRealFisca;

    /*
     * ES_PROPIA
     */
    private String esPropia;

    /*
     * ID_TIPO_FISCALIZA
     */
    private Long idTipoFiscaliza;

    /*
     * NO_TIPO_FISCALIZA
     */
    private String noTipoFiscaliza;

    /*
     * ID_SUBTIPO_FISCALIZA
     */
    private Long idSubtipoFiscaliza;

    /*
     * NO_SUBTIPO_FISCALIZA
     */
    private String noSubtipoFiscaliza;

    /*
     * ID_MOTIVO_FISCALIZA
     */
    private Long idMotivoFiscaliza;

    /*
     * NO_MOTIVO_FISCALIZA
     */
    private String noMotivoFiscaliza;

    /*
     * ID_UNIDAD_FISCALIZABLE
     */
    private Long idUnidadFiscalizable;

    /*
     * CO_UNIDAD_FISCALIZABLE
     */
    private String coUnidadFiscalizable;

    /*
     * NO_UNIDAD_FISCALIZABLE
     */
    private String noUnidadFiscalizable;

    /*
     * RUC_AGENTE_FISCALIZADO
     */
    private String rucAgenteFiscalizado;

    /*
     * NO_AGENTE_FISCALIZADO
     */
    private String noAgenteFiscalizado;

    /*
     * NO_FASE_ORIGEN
     */
    private String noFaseOrigen;

    /*
     * NO_TAREA_ORIGEN
     */
    private String noTareaOrigen;

    /*
     * NO_FASE_DESTINO
     */
    private String noFaseDestino;

    /*
     * NO_TAREA_DESTINO
     */
    private String noTareaDestino;

    /*
     * FE_ASIGNA_TAREA
     */
    private Date feAsignaTarea;

    /*
     * DE_MENSAJE_TAREA
     */
    private String deMensajeTarea;

    /*
     * NO_TIPO_TRANSICION
     */
    private String noTipoTransicion;

    /*
     * NO_TIPO_PERSONA_ORIGEN
     */
    private String noTipoPersonaOrigen;

    /*
     * NO_PERSONA_ORIGEN
     */
    private String noPersonaOrigen;

    /*
     * NO_TIPO_PERSONA_DESTINO
     */
    private String noTipoPersonaDestino;

    /*
     * NO_PERSONA_DESTINO
     */
    private String noPersonaDestino;

    /*
     * ID_CONTRATO
     */
    private Long idContrato;

    /*
     * NU_CONTRATO
     */
    private String nuContrato;

    /*
     * ID_PERSONA_SUPERVISORA
     */
    private String idPersonaSupervisora;

    /*
     * RUC_EMPRESA_SUPERVISORA
     */
    private String rucEmpresaSupervisora;

    /*
     * NO_EMPRESA_SUPERVISORA
     */
    private String noEmpresaSupervisora;

    /*
     * Descripción titulo del reporte
     */
    private String deTituloReporte;

    /*
     * Nombre de archivo
     */
    private String descNoArchivo;

    /*
     * Tipo de extensión
     */
    private String descExtension;

    /*
     * Cantidad de registros
     */
    private Integer cantidadRegistros;
}
