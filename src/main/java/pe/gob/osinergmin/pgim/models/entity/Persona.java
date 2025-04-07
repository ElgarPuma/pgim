package pe.gob.osinergmin.pgim.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name="PERSONA")
@Data
@NoArgsConstructor
public class Persona implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_SEQ")
	@SequenceGenerator(name="PERSONA_SEQ", sequenceName = "PERSONA_SEQ", allocationSize=1)
	@Column(name="ID_PERSONA")
	private Long idPersona;

	private String activo;

	private String correo;

	private String direccion;

	@Temporal(TemporalType.DATE)
	@Column(name="FEC_NACIMIENTO")
	private Date fecNacimiento;

	@Column(name="NOMBRE_O_RAZON_SOCIAL")
	private String nombreORazonSocial;

	private String nota;

	@Column(name="NRO_DOC_IDENTIDAD")
	private String nroDocIdentidad;

	private String sexo;

	private String telefono;

	@Column(name="TIP_DOC_IDENTIDAD")
	private String tipDocIdentidad;

	//bi-directional many-to-one association to EmpresaSupervisora
	@OneToMany(mappedBy="persona")
	private List<EmpresaSupervisora> empresaSupervisoras;

	//bi-directional many-to-one association to Ubigeo	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_UBIGEO", nullable = false)	
	private Ubigeo ubigeo;

	

}