package pe.gob.osinergmin.pgim.security.model;

import java.util.ArrayList;
import java.util.List;


public class SissegUsuario {

	private Long idUsuario;
	
	private Long idUSuario;
	
	private String username;
	
	private String nombres;
	
	private String dependencia;
	
	private String compania;
	
	private String email;	
	
	private String emailInterno;	
	
	private String estado;
	
	private String password;
	
	private String passwordSiged;

	/**
	 * Código del usuario Siged que se obtiene desde la tabla de la PGIM (por lo
	 * pronto, luego de un método del servicio del Siged) que registra esta
	 * información. Esta dato será configurado al momento de la autenticación.
	 */	
	private String coUsuarioSiged;
	
	private List<SissegRol> roles;
	
	private List<SissegModulo> listaModulos;
	
	private ArrayList<String>  authorities;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Long getIdUSuario() {
		return idUSuario;
	}

	public void setIdUSuario(Long idUSuario) {
		this.idUSuario = idUSuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombres() {
		return nombres;
	}

	public String getPasswordSiged() {
		return passwordSiged;
	}

	public void setPasswordSiged(String passwordSiged) {
		this.passwordSiged = passwordSiged;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getCompania() {
		return compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public List<SissegRol> getRoles() {
		return roles;
	}

	public void setRoles(List<SissegRol> roles) {
		this.roles = roles;
	}

	public String getCoUsuarioSiged() {
		return this.coUsuarioSiged;
	}

	public void setCoUsuarioSiged(String coUsuarioSiged) {
		this.coUsuarioSiged = coUsuarioSiged;
	}

	public String getEmailInterno() {
		return emailInterno;
	}

	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public ArrayList<String> getAuthorities() {
		return authorities;
	}
	
	public List<SissegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SissegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public void setAuthorities(ArrayList<String> authorities) {
		this.authorities = authorities;
	}
	

}