package pe.gob.osinergmin.pgim.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;
import pe.gob.osinergmin.pgim.config.PropertiesConfig;
import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSectorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubsectorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.models.entity.PgimSubsector;
import pe.gob.osinergmin.pgim.models.repository.ProcesoRepository;
import pe.gob.osinergmin.pgim.models.repository.SectorRepository;
import pe.gob.osinergmin.pgim.models.repository.SubsectorRepository;
import pe.gob.osinergmin.pgim.services.ProcesoService;
import pe.gob.osinergmin.pgim.siged.ProcesoSiged;
import pe.gob.osinergmin.pgim.siged.ProcesosSiged;
import pe.gob.osinergmin.pgim.utils.CommonsUtil;
import pe.gob.osinergmin.pgim.utils.ConstantesUtil;

/**
 * Servicio para la gestión de la interacción con la base de datos y otros
 * servicios.
 * 
 * @descripción: Lógica de negocio de la entidad Proceso
 *
 * @author: ddelaguila
 * @version: 1.0
 * @fecha_de_creación: 24/05/2020
 * @fecha_de_ultima_actualización: 24/05/2020 
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class ProcesoServiceImpl implements ProcesoService  {

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private ProcesoRepository procesoRepository;

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private SubsectorRepository subsectorRepository;

	@Override
	public Iterable<PgimProceso> listarProcesos() {
		return procesoRepository.findAll();
	}

	@Override
	public PgimProceso ObtenerProceso(Long idProceso) {
		
		PgimProceso pgimProceso = procesoRepository.findById(idProceso).orElse(null);
		
		return pgimProceso;
	}    

	@Override
	public List<PgimSectorDTO> listaSectores(){
		return this.sectorRepository.listaSectores();
	}

    @Override
	public List<PgimSubsectorDTO> listaSubsectores(){
		return this.subsectorRepository.listaSubsectores();
	}

	@Override
	public ProcesosSiged listarProcesosSiged()
			throws Exception {
		
		ProcesosSiged procesosSiged = this.listarProcesosSiged_old();

		return procesosSiged;
	}

	
	private ProcesosSiged listarProcesosSiged_old()
			throws Exception {

		ProcesosSiged objResultado = new ProcesosSiged();

		// Ejecutar el procedimiento de búsqueda de cliente
		String serverUrl = propertiesConfig.getUrlSigedRestOld();
		String urlServicio = ConstantesUtil.PARAM_SIGED_PROCESOS;

		log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_INI,
				ConstantesUtil.PARAM_SIGED_NOM_METODO_PROCESOS));

		try {
			URL url = new URL(serverUrl + urlServicio);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(ConstantesUtil.PARAM_REQUEST_METHOD_GET);
			conn.setRequestProperty(ConstantesUtil.PARAM_REQUEST_PROPERTY_ACCEPT_ENCODING, "gzip, deflate, br");
			conn.setRequestProperty(ConstantesUtil.PARAM_REQUEST_PROPERTY_CONTENT_TYPE,
					"application/xml;charset=UTF-8");
			conn.addRequestProperty("Accept-Encoding", "identity");

			if (conn.getResponseCode() != 200) {
				objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
				objResultado.setErrorCode(String.valueOf(conn.getResponseCode()));
				objResultado.setMessage(conn.getResponseMessage());
				
				log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_FIN_ERROR,
						ConstantesUtil.PARAM_SIGED_NOM_METODO_PROCESOS ));
				log.error(String.format(ConstantesUtil.PARAM_SIGED_LOG_SERVICIO_NO_DISPONIBLE, objResultado.getErrorCode(), objResultado.getMessage()));
				
				return objResultado;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())), 100000);
			StringBuilder xmlString = new StringBuilder("");
			String output;
			while ((output = br.readLine()) != null) {
				output = CommonsUtil.getLimpiarString(output);
				xmlString.append(output);
			}

			Document doc = CommonsUtil.convertStringToXMLDocument(xmlString);
			doc.getDocumentElement().normalize();
			objResultado.setResultCode(CommonsUtil.getValue2Tag(doc, "procesos", "resultCode"));
			objResultado.setMessage(CommonsUtil.getValue2Tag(doc, "procesos", "message"));
			objResultado.setErrorCode(CommonsUtil.getValue2Tag(doc, "procesos", "errorCode"));

			if (objResultado.getResultCode().equals(ConstantesUtil.PARAM_RESULTADO_SUCCESS)) {
				
				List<ProcesoSiged> lProcesoSiged = new LinkedList<ProcesoSiged>();
				// Obtenemos la lista de las etiquetas "procesos"
				NodeList nodeListProcesosSiged = doc.getElementsByTagName("proceso");
				for (int i = 0; i < nodeListProcesosSiged.getLength(); i++) {
					Node node = nodeListProcesosSiged.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
	
						ProcesoSiged obj = new ProcesoSiged();
	
						Element element = (Element) nodeListProcesosSiged.item(i);
	
						obj.setCodigoProceso(CommonsUtil.getTagValue("codigoProceso", element));
						obj.setNombreProceso(CommonsUtil.getTagValue("nombreProceso", element));
	
						lProcesoSiged.add(obj);
					}
				}
	
				objResultado.setListProcesoSiged(lProcesoSiged);
	
	
			} else {
				String mensajeSiged = (objResultado.getErrorMessage() != null && !objResultado.getErrorMessage().trim().equals("")) ? 
						objResultado.getErrorMessage() : objResultado.getMessage();
				
				String mensajeErrorSiged = String.format(ConstantesUtil.MENSAJE_ErrorSiged,
						ConstantesUtil.PARAM_SIGED_NOM_METODO_PROCESOS, objResultado.getErrorCode(), mensajeSiged);
				
				log.error(mensajeErrorSiged);
				objResultado.setMessage(mensajeErrorSiged);
				
			}

			conn.disconnect();
		} catch (Exception e) {
			objResultado.setResultCode(ConstantesUtil.PARAM_RESULTADO_FAIL_DOS);
			objResultado.setErrorCode(ConstantesUtil.PARAM_RESULTADO_ERROR_EXCEPTION);
			objResultado.setMessage("Message: " + e.getMessage() + " || Localized: " + e.getLocalizedMessage()
					+ " || Cause: " + e.getCause());
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
		
		log.info(String.format(ConstantesUtil.PARAM_SIGED_LOG_DE_USO_FIN,
				ConstantesUtil.PARAM_SIGED_NOM_METODO_PROCESOS));

		return objResultado;
	}

	@Transactional(readOnly = false)
    @Override
    public PgimProcesoDTO crearProceso(PgimProcesoDTO pgimProcesoDTO,
            AuditoriaDTO auditoriaDTO) {
		PgimProceso pgimProceso = new PgimProceso();

        pgimProceso.setNoProceso(pgimProcesoDTO.getNoProceso());
        pgimProceso.setDeProceso(pgimProcesoDTO.getDeProceso());
		pgimProceso.setCoProcesoSiged(pgimProcesoDTO.getCoProcesoSiged());
		pgimProceso.setFlIndicador(pgimProcesoDTO.getFlIndicador());
		
		PgimSubsector pgimSubsector = new PgimSubsector();
        pgimSubsector.setIdSubsector(pgimProcesoDTO.getIdSubsector());
		pgimProceso.setPgimSubsector(pgimSubsector);

        pgimProceso.setEsRegistro(ConstantesUtil.IND_ACTIVO);
        pgimProceso.setFeCreacion(auditoriaDTO.getFecha());
        pgimProceso.setUsCreacion(auditoriaDTO.getUsername());
        pgimProceso.setIpCreacion(auditoriaDTO.getTerminal());
        PgimProceso pgimProcesoCreado = procesoRepository.save(pgimProceso);

        PgimProcesoDTO pgimProcesoDTOCreado = this
                .obtenerProcesoPorId(pgimProcesoCreado.getIdProceso());

        return pgimProcesoDTOCreado;
    }

	@Transactional(readOnly = false)
    @Override
    public PgimProcesoDTO modificarProceso(PgimProcesoDTO pgimProcesoDTO, PgimProceso pgimProcesoActual,
            AuditoriaDTO auditoriaDTO) {

        pgimProcesoActual.setNoProceso(pgimProcesoDTO.getNoProceso());
        pgimProcesoActual.setDeProceso(pgimProcesoDTO.getDeProceso());
		if(pgimProcesoDTO.getCoProcesoSiged() == null){
			pgimProcesoActual.setCoProcesoSiged(null);
		}else{
			pgimProcesoActual.setCoProcesoSiged(Long.valueOf(pgimProcesoDTO.getCoProcesoSiged()));
		}
		pgimProcesoActual.setFlIndicador(pgimProcesoDTO.getFlIndicador());

		PgimSubsector pgimSubsector = new PgimSubsector();
        pgimSubsector.setIdSubsector(pgimProcesoDTO.getIdSubsector());
		pgimProcesoActual.setPgimSubsector(pgimSubsector);

        pgimProcesoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimProcesoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimProcesoActual.setIpActualizacion(auditoriaDTO.getTerminal());
        PgimProceso pgimProcesoModificado = procesoRepository.save(pgimProcesoActual);

        PgimProcesoDTO pgimProcesoDTOModificado = this
                .obtenerProcesoPorId(pgimProcesoModificado.getIdProceso());

        return pgimProcesoDTOModificado;
    }

	@Transactional(readOnly = false)
    @Override
    public void eliminarProceso(PgimProceso pgimProcesoActual, AuditoriaDTO auditoriaDTO) {
		pgimProcesoActual.setEsRegistro(ConstantesUtil.IND_INACTIVO);
        pgimProcesoActual.setFeActualizacion(auditoriaDTO.getFecha());
        pgimProcesoActual.setUsActualizacion(auditoriaDTO.getUsername());
        pgimProcesoActual.setIpActualizacion(auditoriaDTO.getTerminal());
        this.procesoRepository.save(pgimProcesoActual);
    }

	@Override
	public List<PgimProcesoDTO> listarProceso(String campo, Sort.Direction direccion) {
		Sort sort = Sort.by(direccion, campo);
		List<PgimProcesoDTO> lPgimProcesoDTO = this.procesoRepository.listarProceso(sort);
		return lPgimProcesoDTO;
	}

	@Override
	public PgimProcesoDTO obtenerProcesoPorId(Long idProceso) {
		return this.procesoRepository.obtenerProcesoPorId(idProceso);
	}

	
}
