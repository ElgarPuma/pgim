package pe.gob.osinergmin.pgim.models.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="UBIGEO")
@Data
@NoArgsConstructor
public class Ubigeo  {	

	@Id	
	@Size(min = 6, message = "Id de Ubigeo debe tener minimo 6 caracteres")
	@Column(name = "ID_UBIGEO", nullable = false, length = 6)
	private String idUbigeo;

	
	@Size(min = 3, message = "Nombre de Ubigeo debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 80)
	private String nombre;	

	//bi-directional many-to-one association to Persona	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ubigeo")
	private List<Persona> personas;
	
	
	
	

}