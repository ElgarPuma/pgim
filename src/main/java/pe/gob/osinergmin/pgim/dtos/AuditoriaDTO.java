package pe.gob.osinergmin.pgim.dtos;

import java.util.ArrayList;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuditoriaDTO {
	
	private Long idUsuario;
	
    private String username;    
    
    private String terminal;
    
    private Date fecha;

    private String moduloPgimActual;

    /**
     * Identificador interno del usuario Siged
     */
    String coUsuarioSiged;
    
    private String idRolSiged;  

    private String passwordSiged;
    
    private ArrayList<String>  authorities;

}
