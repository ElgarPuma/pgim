package pe.gob.osinergmin.pgim.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimConsumoContraDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSegumntoAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoSiafAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimCostoUnitarioDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPenalidadAuxDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrspstoGastoSuperDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimTprmtroXTinstrmntoDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimContrato;

/**
 * Interfaz para la gestión de los servicios relacionados con los contratos.
 * 
 * @descripción: Contrato
 *
 * @author: jvalerio
 * @version: 1.0
 * @fecha_de_creación: 22/08/2020
 * @fecha_de_ultima_actualización: 30/08/2020
 */
public interface ContratoService {

	List<PgimContratoDTO> obtenerContratos();

	/**
     * Permite listar los contratos con las propiedades de filtros según corresponda.
	 * @param filtroContrato
	 * @param paginador
	 * @return
	 */
	Page<PgimContratoDTO> listarContratos(PgimContratoDTO filtroContrato, Pageable paginador);
	
	 /***
     * Permite listar por numero de contrato.
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de contratos
     * @return
     */
	List<PgimContratoDTO> listarPorCodigoContrato(String palabra);
	
	 /***
     * Permite listar por numero de expediente.
     * 
     * @param palabra Palabra clave utilizada para buscar en la lista de contratos
     * @return
     */
	List<PgimContratoDTO> listarPorNumeroExpediente(String palabra);
	
	PgimContratoDTO obtenerContratoPorId(Long idContrato);

	PgimContratoDTO obtenerContrato(Long idContrato);
	
	/**
	 * Permite obtener contrato para seguimiento
	 *
	 * @param pgimContratoDTO
	 * @param idContrato
	 * @return
	 */
	PgimContratoDTO obtenerContratoSeguimientoPorId(Long idContrato);
	
	/**
	 * Permirte crear un contrato
	 *
	 * @param pgimContratoDTO
	 * @param auditoriaDTO
	 * @return
	 */
	PgimContratoDTO crearContrato(PgimContratoDTO pgimContratoDTO, AuditoriaDTO auditoriaDTO) throws Exception;
	
	/***
     * Permite obtener un objeto entidad del contrato con el valor idContrato.
     * @param idContrato Identificador del contrato.
     * @return
     */
	PgimContrato getByIdContrato(Long idContrato);
	
	/***
     * Permite modificar un contrato.
     * @param pgimContratoDTO Contrato DT que porta los datos con los nuevos valores para la actualización.
     * @param pgimContrato Contrato a la que se actualizarán los nuevos valores para la actualización.
     * @return
     */
	PgimContratoDTO modificarContrato(PgimContratoDTO pgimContratoDTO, PgimContrato pgimContrato, AuditoriaDTO auditoriaDTO);
	
	/***
     * Permite eliminar un contrato dado su id.
     * @param pgimContratoActual
     */
	void eliminarContrato(PgimContrato pgimContratoActual, AuditoriaDTO auditoriaDTO);

	List<PgimContratoDTO> existeContrato(Long idContrato, String nuContrato);

	List<PgimContratoDTO> existeNuExpediente(Long idInstanciaProceso, String nuExpedienteSiged);

	/**
	 * Permite calcular el costo de la supervisión.
	 * @param pgimSupervisionDTO
	 * @return
	 */
	PgimConsumoContraDTO calcularCostoSupervision(PgimSupervisionDTO pgimSupervisionDTO,boolean flagFechaReal);

	/**
	 * Permite obtener los contratos vigentes asociado a un determinado nombre de usuario
	 * @param obtenerAuditoria
	 * @return
	 */
	List<PgimContratoDTO> obtenerContratos(AuditoriaDTO obtenerAuditoria);

	/**
	 * Permite obtener el saldo de un contrato para la validación en la asignación y ejecución de las supervisiones
	 * @param pgimContratoDTO
	 * @return
	 */
	PgimContratoDTO calcularSaldoContrato(PgimContratoDTO pgimContratoDTO);

	/**
	 * Permite obtener el monto total comprometido de un contrato
	 * @param idContrato
	 * @return
	 */
	BigDecimal obtenerMontoConsumoTotal(Long idContrato);
	
	/**
	 * Permite validar el saldo del contrato para una supervisión.
	 * @param pgimSupervisionDTO
	 * @param pgimContratoDTO
	 * @return
	 */
	PgimConsumoContraDTO validarSaldoContratoXsupervision(PgimContratoDTO pgimContratoDTO,PgimSupervisionDTO pgimSupervisionDTO);
	

	String validarExpedienteSiged(String nuExpedienteSiged, AuditoriaDTO auditoriaDTO);

	/**
	 * Permite listar los contratos para el reporte "Control de saldos por contrato y supervisora"
	 * @param filtroContratoSegumntoAux
	 * @param paginador
	 * @return
	 */
	Page<PgimContratoSegumntoAuxDTO> listarContratoSeguimientoAux(PgimContratoSegumntoAuxDTO filtroContratoSegumntoAux, Pageable paginador);
	
	/**
	 * Permite listar los contratos para el reporte "Ejecución presupuestal"
	 * @param filtroContratoSiafAuxDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimContratoSiafAuxDTO> listarReporteEjecucionPresupuestal(PgimContratoSiafAuxDTO filtroContratoSiafAuxDTO, Pageable paginador);
	
	/**
	 * Permite listar los contratos para el reporte "Penalidades por periodo contrato y supervisora"
	 * @param filtroPenalidadAuxDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimPenalidadAuxDTO> listarReportePenalidadesPeriodoContratoSupervisora(PgimPenalidadAuxDTO filtroPenalidadAuxDTO, Pageable paginador);
	
	/**
	 * Permite listar los contratos para el reporte "ad-hoc de presupuesto y gasto de supervisión"
	 * @param filtroPgimPrspstoGastoSuperDTO
	 * @param paginador
	 * @return
	 */
	Page<PgimPrspstoGastoSuperDTO> listarReportePresupuestoGastoSupervision(PgimPrspstoGastoSuperDTO filtroPgimPrspstoGastoSuperDTO, Pageable paginador);

	/**
	 * Permite calcular la cantidad de días calendario entre las dos fechas de la supervisión.
	 * @param pgimSupervisionDTO
	 * @param flagFechaReal
	 * @return
	 */
	Long diasCalendarioSupervision(PgimSupervisionDTO pgimSupervisionDTO, boolean flagFechaReal);

	/**
	 * Permite obtener el costo unitario
	 * @param pgimSupervisionDTO
	 * @param flagFechaReal
	 * @param cantidadDiasEntre
	 * @return
	 */
	PgimCostoUnitarioDTO obtenerCostoUnitario(PgimSupervisionDTO pgimSupervisionDTO, boolean flagFechaReal, Long cantidadDiasEntre);

	/**
	 * Permite seleccionar (relacionad) los tipos de parámetros por instrumento de medición con el contrato dado.
	 * @param idContrato
	 * @param lPgimTprmtroXTinstrmntoDTO
	 * @param obtenerAuditoria
	 */
    void seleccionarTParametrosXTTipoInstrumento(Long idContrato,
            List<PgimTprmtroXTinstrmntoDTO> lPgimTprmtroXTinstrmntoDTO, AuditoriaDTO obtenerAuditoria);
}
