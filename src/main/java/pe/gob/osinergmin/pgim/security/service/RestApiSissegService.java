package pe.gob.osinergmin.pgim.security.service;

import java.util.List;

import pe.gob.osinergmin.pgim.security.model.SissegModulo;
import pe.gob.osinergmin.pgim.security.model.SissegRol;
import pe.gob.osinergmin.pgim.security.model.SissegUsuario;

public interface RestApiSissegService {	
	
	String validarCadenaSisseg(String sessionSisseg);
	
	SissegUsuario validarAccesoSisseg(String sessionSisseg);
	
	SissegUsuario validarAccesoSisseg(String sessionSisseg, String token);

	//SissegUsuario validarAccesoSisseg(String sessionSisseg, boolean paraQAoProd);
	
	List<SissegRol> listarPermisosPagina(Long idUsuario);
	
	List<SissegRol> listarPermisosPagina(Long idUsuario, String token);
	
	List<SissegModulo> listarModulos(Long idUsuario);
	
	List<SissegModulo> listarModulos(Long idUsuario, String token);

}
