package pe.gob.osinergmin.pgim.services;
import java.util.List;

import org.springframework.data.domain.Sort;

import pe.gob.osinergmin.pgim.dtos.AuditoriaDTO;
import pe.gob.osinergmin.pgim.dtos.PgimProcesoDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSectorDTO;
import pe.gob.osinergmin.pgim.dtos.PgimSubsectorDTO;
import pe.gob.osinergmin.pgim.models.entity.PgimProceso;
import pe.gob.osinergmin.pgim.siged.ProcesosSiged;

public interface ProcesoService {

	/**
	 * Lista los procesos existentes.
	 * @param palabra
	 * @return
	 */

	Iterable<PgimProceso> listarProcesos();

	PgimProceso ObtenerProceso(Long idProceso);

	List<PgimProcesoDTO> listarProceso(String campo, Sort.Direction direccion);

	PgimProcesoDTO obtenerProcesoPorId(Long idProceso);
	
	List<PgimSectorDTO> listaSectores();

    List<PgimSubsectorDTO> listaSubsectores();

	ProcesosSiged listarProcesosSiged() throws Exception;
	
	PgimProcesoDTO crearProceso(PgimProcesoDTO pgimProcesoDTO, AuditoriaDTO auditoriaDTO);

	PgimProcesoDTO modificarProceso(PgimProcesoDTO pgimProcesoDTO, PgimProceso pgimProcesoActual, AuditoriaDTO auditoriaDTO);

	void eliminarProceso(PgimProceso pgimProcesoActual, AuditoriaDTO auditoriaDTO);

}
