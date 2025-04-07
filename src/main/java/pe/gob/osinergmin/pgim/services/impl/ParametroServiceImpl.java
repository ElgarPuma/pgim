package pe.gob.osinergmin.pgim.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimContratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEspecialidadDTO;
import pe.gob.osinergmin.pgim.dtos.PgimEstratoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimFaseProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimGrupoRiesgoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimParametroDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPasoProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimPrgrmSupervisionDTO;
import pe.gob.osinergmin.pgim.dtos.PgimUnidadOrganicaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimValorParametroDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimParametro;
import pe.gob.osinergmin.pgim.models.entity.PgimValorParametro;
import pe.gob.osinergmin.pgim.models.repository.ContratoRepository;
import pe.gob.osinergmin.pgim.models.repository.EspecialidadRepository;
import pe.gob.osinergmin.pgim.models.repository.EstratoRepository;
import pe.gob.osinergmin.pgim.models.repository.FaseProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.GrupoRiesgoRepository;
import pe.gob.osinergmin.pgim.models.repository.ParametroRepository;
import pe.gob.osinergmin.pgim.models.repository.PasoProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.PersonalOsiCargoRepository;
import pe.gob.osinergmin.pgim.models.repository.PrgrmSupervisionRepository;
import pe.gob.osinergmin.pgim.models.repository.UnidadOrganicaRepository;
import pe.gob.osinergmin.pgim.models.repository.ValorParametroRepository;
import pe.gob.osinergmin.pgim.services.ParametroService;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;
import pe.gob.osinergmin.pgim.utils.EValorParametro;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de las entidades Parámetro y valores de
 *               parámetros
 *
 * @author: hdiaz
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 24/05/2020
 */
@Service
@Transactional(readOnly = true)
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    private ValorParametroRepository valorParametroRepository;

    @Autowired
    private EstratoRepository estratoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @Autowired
    private PrgrmSupervisionRepository prgrmSupervisionRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private FaseProcesoRepository faseProcesoRepository;

    @Autowired
    private PasoProcesoRepository pasoProcesoRepository;

    @Autowired
    private GrupoRiesgoRepository grupoRiesgoRepository;
    
    @Autowired
    private ParametroRepository parametroRepository;
    
    @Autowired
    private UnidadOrganicaRepository unidadOrganicaRepository;
    
    @Autowired
    private PersonalOsiCargoRepository personalOsiCargoRepository;
    
    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreParametro(String nombre) {
        // String appKey = this.sisegLoginRepository.obtenerKeyAplicacionById(921);
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreParametro(nombre);

        return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreParametroPorCuadro(String nombre) {
        // String appKey = this.sisegLoginRepository.obtenerKeyAplicacionById(921);
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreParametroPorCuadro(nombre);

        return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreParametroPorHecho(String nombre) {
        // String appKey = this.sisegLoginRepository.obtenerKeyAplicacionById(921);
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreParametroPorHecho(nombre);

        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipEstadoConfig(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreTipEstadoConfig(nombre);

        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreParametro(String nombre, Long idValorParametro) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreParametro(nombre, idValorParametro);

        return lPgimValorParametroDTO;
    }

    @Override
    public PgimValorParametroDTO obtenerValorParametroPorID(Long idValorParametro) {
        PgimValorParametroDTO pgimValorParametroDTO = this.valorParametroRepository
                .obtenerValorParametroPorID(idValorParametro);

        return pgimValorParametroDTO;
    }

    @Override
    public List<PgimEstratoDTO> filtrarPorNombreEstrato(String nombre) {
        List<PgimEstratoDTO> lPgimEstratoDTO = this.estratoRepository.filtrarPorNombreEstrato(nombre);
        return lPgimEstratoDTO;
    }

    @Override
    public List<PgimEspecialidadDTO> filtrarPorNombreEspecialidad(String nombre) {
        List<PgimEspecialidadDTO> lPgimEspecialidadDTO = this.especialidadRepository
                .filtrarPorNombreEspecialidad(nombre);
        return lPgimEspecialidadDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoSupervision(String nombre) {
        List<PgimValorParametroDTO> lPgimSubtipoSupervisionDTO = this.valorParametroRepository
                .filtrarPorNombreTipoSupervision(EValorParametro.SUPER_FSCLZCION_NO_PRGRMDA.toString(), EValorParametro.SUPER_FSCLZCION_PRGRMDA.toString());
        return lPgimSubtipoSupervisionDTO;
    }

    @Override
    public List<PgimFaseProcesoDTO> filtrarPorNombreFaseProceso(String nombre) {
        List<PgimFaseProcesoDTO> lPgimFaseProcesoDTO = this.faseProcesoRepository.filtrarPorNombreFaseProceso(nombre);
        return lPgimFaseProcesoDTO;
    }

    @Override
    public List<PgimPasoProcesoDTO> filtrarPorNombrePasoProceso(String nombre) {
        List<PgimPasoProcesoDTO> lPgimPasoProcesoDTO = this.pasoProcesoRepository.filtrarPorNombrePasoProceso(nombre);
        return lPgimPasoProcesoDTO;
    }

    @Override
    public List<PgimPrgrmSupervisionDTO> filtrarPorNombreProgramaSupervision(String nombre) {
        List<PgimPrgrmSupervisionDTO> lPgimPrgrmSupervisionDTO = this.prgrmSupervisionRepository
                .filtrarPorNombreProgramaSupervision(nombre);
        return lPgimPrgrmSupervisionDTO;
    }

    @Override
    public List<PgimContratoDTO> filtrarPorNombreContratoSupervision(String nombre) {
        List<PgimContratoDTO> lPgimContratoDTO = this.contratoRepository.filtrarPorNombreContratoSupervision(nombre);
        return lPgimContratoDTO;
    }

    @Override
    public List<PgimContratoDTO> filtrarPorCodigoContrato(String nombre) {
        return null;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoInvolucrado(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreTipoInvolucrado(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoPrefijo(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreTipoPrefijo(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidad(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidad(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadRuc(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidadRuc(EValorParametro.DOIDE_RUC.toString());
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDniCe(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidadDniCe(EValorParametro.DOIDE_DNI.toString(), EValorParametro.DOIDE_CE.toString());
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDni(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidadDni(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadCe(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidadCe(nombre);
        return lPgimValorParametroDTO;
    }

    /**
     * Me permite listar por el nombre de tipo de norma
     * 
     * @param nombre
     * @return
     */
    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoNorma(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.filtrarPorTipoNorma(nombre);
        return lPgimValorParametroDTO;
    }

    /**
     * Me permite listar por el nombre de la division de item de normas
     * 
     * @param nombre
     * @return
     */
    @Override
    public List<PgimValorParametroDTO> filtrarPorDivisionItem(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorDivisionItem(nombre);
        return lPgimValorParametroDTO;
    }

    /**
     * Permite obtener la lista de la tabla grupo riesgo
     * @return
     */
    @Override
    public List<PgimGrupoRiesgoDTO> filtrarPorGrupoRiesgo() {
        List<PgimGrupoRiesgoDTO> lPgimGrupoRiesgoDTO = this.grupoRiesgoRepository.listarPgimGrupoRiesgoDTO();
        return lPgimGrupoRiesgoDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoMedidaAdm(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreTipoMedidaAdm(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoObjeto(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreTipoObjeto(nombre);
        return lPgimValorParametroDTO;

    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorMedioDenuncia(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorMedioDenuncia(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoDocIdentidadDenuncia(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoDocIdentidadDenuncia(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoMateria(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoMateria(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorTipoReporte(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorTipoReporte(nombre);
        return lPgimValorParametroDTO;
    }

    @Override
    public List<PgimValorParametroDTO> filtrarPorNombreTipoAlerta(String nombre) {
        List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository
                .filtrarPorNombreParametro(nombre);
        return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimParametroDTO> listarParametros() {
        List<PgimParametroDTO> lPgimParametroDTO = this.parametroRepository.listarParametros();

        return lPgimParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> listarValoresActivosParametro(Long idParametro){
    	List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.filtrarActivosPorIdParametro(idParametro);
    	
    	return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> listarValoresNoActivosParametro(Long idParametro){
    	List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.filtrarNoActivosPorIdParametro(idParametro);
    	
    	return lPgimValorParametroDTO;
    }
    
    @Transactional(readOnly = false)
	@Override
	public PgimValorParametroDTO crearValorParametro(PgimValorParametroDTO pgimValorParametroDTO, AuditoriaDTO auditoriaDTO) {

		PgimValorParametro pgimValorParametro = new PgimValorParametro();
		
		Long maxCoClave = this.obtenerMaximoCoClave(pgimValorParametroDTO.getIdParametro());
		
		pgimValorParametro.setCoClave(maxCoClave+1);
		pgimValorParametro.setCoClaveTexto(pgimValorParametroDTO.getCoClaveTexto());
        pgimValorParametro.setNoValorParametro(pgimValorParametroDTO.getNoValorParametro());
        pgimValorParametro.setDeValorParametro(pgimValorParametroDTO.getDeValorParametro());
        pgimValorParametro.setNuOrden(pgimValorParametroDTO.getNuOrden());
        pgimValorParametro.setNuValorNumerico(pgimValorParametroDTO.getNuValorNumerico());
        pgimValorParametro.setDeValorAlfanum(pgimValorParametroDTO.getDeValorAlfanum());
        pgimValorParametro.setFlActivo(pgimValorParametroDTO.getFlActivo());         
        
        PgimParametro parametro = new PgimParametro();
        parametro.setIdParametro(pgimValorParametroDTO.getIdParametro());
        pgimValorParametro.setPgimParametro(parametro);        
       
		pgimValorParametro.setEsRegistro(ConstantesUtil.IND_ACTIVO);
		pgimValorParametro.setFeCreacion(auditoriaDTO.getFecha());
		pgimValorParametro.setUsCreacion(auditoriaDTO.getUsername());
		pgimValorParametro.setIpCreacion(auditoriaDTO.getTerminal());

		PgimValorParametro pgimNormaItemCreado = this.valorParametroRepository.save(pgimValorParametro);

		PgimValorParametroDTO pgimNormaItemDTOCreado = this.obtenerValorParametroPorId(pgimNormaItemCreado.getIdValorParametro());

		return pgimNormaItemDTOCreado;
	}
	
	@Transactional(readOnly = false)
    @Override
    public PgimValorParametroDTO modificarValorParametro(PgimValorParametroDTO pgimValorParametroDTO, PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO) {
    	
		pgimValorParametro.setCoClaveTexto(pgimValorParametroDTO.getCoClaveTexto());
        pgimValorParametro.setNoValorParametro(pgimValorParametroDTO.getNoValorParametro());
        pgimValorParametro.setDeValorParametro(pgimValorParametroDTO.getDeValorParametro());
        pgimValorParametro.setNuOrden(pgimValorParametroDTO.getNuOrden());
        pgimValorParametro.setNuValorNumerico(pgimValorParametroDTO.getNuValorNumerico());
        pgimValorParametro.setDeValorAlfanum(pgimValorParametroDTO.getDeValorAlfanum());
        pgimValorParametro.setFlActivo(pgimValorParametroDTO.getFlActivo());        
        
        PgimParametro parametro = new PgimParametro();
        parametro.setIdParametro(pgimValorParametroDTO.getIdParametro());
        pgimValorParametro.setPgimParametro(parametro);   
        
        pgimValorParametro.setFeActualizacion(auditoriaDTO.getFecha());
        pgimValorParametro.setUsActualizacion(auditoriaDTO.getUsername());
        pgimValorParametro.setIpActualizacion(auditoriaDTO.getTerminal());
        
        PgimValorParametro pgimNormaItemModificado = this.valorParametroRepository.save(pgimValorParametro);    

        PgimValorParametroDTO pgimNormaItemDTOModificado = this.obtenerValorParametroPorId(pgimNormaItemModificado.getIdValorParametro());

        return pgimNormaItemDTOModificado;
    }
    
    @Override
    public PgimValorParametro getByIdValorParametro(Long idValorParametro) {
    	return this.valorParametroRepository.findById(idValorParametro).orElse(null);
    }
    
    @Override
    public PgimValorParametroDTO obtenerValorParametroPorId(Long idValorParametro) {
        return this.valorParametroRepository.obtenerValorParametroPorId(idValorParametro);
    }
    
    @Transactional(readOnly = false)
	@Override
	public void desactivarValor(PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO) {
    	pgimValorParametro.setFlActivo(ConstantesUtil.IND_INACTIVO);

    	pgimValorParametro.setFeActualizacion(auditoriaDTO.getFecha());
        pgimValorParametro.setUsActualizacion(auditoriaDTO.getUsername());
        pgimValorParametro.setIpActualizacion(auditoriaDTO.getTerminal());
        
        this.valorParametroRepository.save(pgimValorParametro);
    }
    
    @Transactional(readOnly = false)
	@Override
	public void activarValor(PgimValorParametro pgimValorParametro, AuditoriaDTO auditoriaDTO) {
    	pgimValorParametro.setFlActivo(ConstantesUtil.IND_ACTIVO);

    	pgimValorParametro.setFeActualizacion(auditoriaDTO.getFecha());
        pgimValorParametro.setUsActualizacion(auditoriaDTO.getUsername());
        pgimValorParametro.setIpActualizacion(auditoriaDTO.getTerminal());
        
        this.valorParametroRepository.save(pgimValorParametro);
    }
    
    @Override
    public Long obtenerMaximoCoClave(Long idParametro) {
    	return this.valorParametroRepository.obtenerMaximoCoClave(idParametro);
    }

    @Override
    public Long obtenerIdValorParametroDesdeClaveTexto(String coClaveTexto) {

        return this.valorParametroRepository.obtenerIdValorParametro(coClaveTexto);
    }
    
    @Override
    public List<PgimValorParametroDTO> listarValoresParametro(Long idParametro){
    	List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.filtrarPorIdParametro(idParametro);
    	
    	return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> existeCoClaveTexto(Long idValorParametro, String coClaveTexto){
    	List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.existeCoClaveTexto(idValorParametro, coClaveTexto);
    	
    	return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimValorParametroDTO> listarParametrosBycoClaveTexto(String parametro){
    	List<PgimValorParametroDTO> lPgimValorParametroDTO = this.valorParametroRepository.listarParametrosBycoClaveTexto(parametro);
    	
    	return lPgimValorParametroDTO;
    }
    
    @Override
    public List<PgimUnidadOrganicaDTO> listarUnidadOrganica() {
        List<PgimUnidadOrganicaDTO> lPgimUnidadOrganicaDTO = this.unidadOrganicaRepository.listarUnidadOrganica();
        return lPgimUnidadOrganicaDTO;
    }

    @Override
    public Integer cantidadCargoPrincipalPorPersonalOsi(Long idPersonalOsi) {
        Integer numeroCargoPrincipal = this.personalOsiCargoRepository.cantidadCargoPrincipalPorPersonalOsi(idPersonalOsi);
        return numeroCargoPrincipal;
    }
    
}