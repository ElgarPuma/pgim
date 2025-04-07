package pe.gob.osinergmin.pgim.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import pe.gob.osinergmin.pgim.security.model.SissegModulo;
import pe.gob.osinergmin.pgim.security.model.SissegUsuario;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Long id;

    private String nombres;
    
    private String dependencia;
    
    private String compania;   

    private String username;    
    
    private String email;

    /**
     * Código del usuario Siged que se encuentra en concordancia con el username
     */
    private String coUsuarioSiged;

    @JsonIgnore
    private String password;
    
    private List<SissegModulo> listaModulos;

    private Collection<? extends GrantedAuthority> authorities;   
    
    /**
     * Passord siged
     */
    private String passwordSiged;
    

    public UserPrincipal(Long id, String nombres, String dependencia, String compania, 
    		String username, String password, String email, String coUsuarioSiged, String passwordSiged,
    		List<SissegModulo> listaModulos, Collection<? extends GrantedAuthority> authorities) {		
		this.id = id;
		this.nombres = nombres;
		this.dependencia = dependencia;
		this.compania = compania;		
		this.username = username;
		this.password = password;
        this.email = email;
        this.coUsuarioSiged = coUsuarioSiged;
        this.passwordSiged = passwordSiged;
        this.listaModulos = listaModulos;
		this.authorities = authorities;
	}	

    public static UserPrincipal build(SissegUsuario user) {
    	
    	List<GrantedAuthority> authorities = null;
    	
    	if(user.getAuthorities()==null) {
	    	authorities = user.getRoles().stream().map(role ->
	                new SimpleGrantedAuthority(
	                		(StringUtils.isNotBlank(role.getCodigoPagina())?role.getCodigoPagina().trim():"")+"_"+
	                				(StringUtils.isNotBlank(role.getCodigoPermiso())?role.getCodigoPermiso().trim():"")
	               )
	        ).collect(Collectors.toList());
    	}
    	else {        
	        authorities = user.getAuthorities().stream().map(role ->
	        new SimpleGrantedAuthority(role)
	        		).collect(Collectors.toList());
    	}
    	/*
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(
                		(StringUtils.isNotBlank(role.getCodigoPagina())?role.getCodigoPagina().trim():"")+"_"+
                				(StringUtils.isNotBlank(role.getCodigoPermiso())?role.getCodigoPermiso().trim():"")
               )
        ).collect(Collectors.toList());*/

        return new UserPrincipal(
                user.getIdUsuario(),
                StringUtils.isNotBlank(user.getNombres())?user.getNombres().trim():"",
                StringUtils.isNotBlank(user.getDependencia())?user.getDependencia().trim():"",
                StringUtils.isNotBlank(user.getCompania())?user.getCompania().trim():"",               
                user.getUsername(),
                user.getPassword(),
                StringUtils.isNotBlank(user.getEmail())?user.getEmail().trim():"",
                user.getCoUsuarioSiged(),
                user.getPasswordSiged(),
                user.getListaModulos(),
                authorities
        );
    }

    public Long getId() {
        return id;
    } 

    public String getNombres() {
		return nombres;
	}

	public String getDependencia() {
		return dependencia;
	}

	public String getCompania() {
		return compania;
	}
	

	@Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
    
    public List<SissegModulo> getListaModulos() {
        return listaModulos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    

    public String getEmail() {
		return email;
    }
    
    public String getCodUsuarioSiged() {
		return coUsuarioSiged;
	}
    
    public String getPasswordSiged() {
		return passwordSiged;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }
}