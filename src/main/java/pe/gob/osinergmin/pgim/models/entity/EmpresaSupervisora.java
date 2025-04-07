package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="EMPRESA_SUPERVISORA")
@Data
@NoArgsConstructor
public class EmpresaSupervisora implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPRESA_SUPERVISORA_SEQ")
	@SequenceGenerator(name="EMPRESA_SUPERVISORA_SEQ", sequenceName = "EMPRESA_SUPERVISORA_SEQ", allocationSize=1)
	@Column(name="ID_EMPRESA_SUPERVISORA")
	private Long idEmpresaSupervisora;

	private String activo;

	//bi-directional many-to-one association to Persona	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PERSONA", nullable = false)
	private Persona persona;
	

}