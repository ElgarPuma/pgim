package pe.gob.osinergmin.pgim.dtos;

import java.util.List;

public class RevisionInforme {

	private PgimDocumentoDTO pgimDocumentoDTO;
	
	private PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO;
	
	private PgimFichaRevisionDTO pgimFichaRevisionDTO;

	private List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO;
	
	private int plazoAbsolucion;
	
	private String feMaxPresentacion;

	private String feRealPresentacion;

	private String feObservacion1;	

	private String feDesdeParaPresentacionDesc;
	
	private int caDiasParaPresentacion;
	
	private int caDiasDemoraInforme1;

	private int caDiasDemoraDesde1ObsHastaInfConforme;

	private int caDiasDemoraCalculados;
	
	private PgimContratoDTO pgimContratoDTO; 

	private String flGenerarDocumento;

	
	public String getFlGenerarDocumento() {
		return flGenerarDocumento;
	}

	public void setFlGenerarDocumento(String flGenerarDocumento) {
		this.flGenerarDocumento = flGenerarDocumento;
	}

	public PgimDocumentoDTO getPgimDocumentoDTO() {
		return pgimDocumentoDTO;
	}

	public void setPgimDocumentoDTO(PgimDocumentoDTO pgimDocumentoDTO) {
		this.pgimDocumentoDTO = pgimDocumentoDTO;
	}

	public PgimDocumentoRelacionDTO getPgimDocumentoRelacionDTO() {
		return pgimDocumentoRelacionDTO;
	}

	public void setPgimDocumentoRelacionDTO(PgimDocumentoRelacionDTO pgimDocumentoRelacionDTO) {
		this.pgimDocumentoRelacionDTO = pgimDocumentoRelacionDTO;
	}

	public PgimFichaRevisionDTO getPgimFichaRevisionDTO() {
		return pgimFichaRevisionDTO;
	}

	public void setPgimFichaRevisionDTO(PgimFichaRevisionDTO pgimFichaRevisionDTO) {
		this.pgimFichaRevisionDTO = pgimFichaRevisionDTO;
	}

	public List<PgimFichaObservacionDTO> getlPgimFichaObservacionDTO() {
		return lPgimFichaObservacionDTO;
	}

	public void setlPgimFichaObservacionDTO(List<PgimFichaObservacionDTO> lPgimFichaObservacionDTO) {
		this.lPgimFichaObservacionDTO = lPgimFichaObservacionDTO;
	}

	public String getFeMaxPresentacion() {
		return feMaxPresentacion;
	}

	public void setFeMaxPresentacion(String feMaxPresentacion) {
		this.feMaxPresentacion = feMaxPresentacion;
	}

	public String getFeDesdeParaPresentacionDesc() {
		return feDesdeParaPresentacionDesc;
	}

	public void setFeDesdeParaPresentacionDesc(String feDesdeParaPresentacionDesc) {
		this.feDesdeParaPresentacionDesc = feDesdeParaPresentacionDesc;
	}

	public int getCaDiasParaPresentacion() {
		return caDiasParaPresentacion;
	}

	public void setCaDiasParaPresentacion(int caDiasParaPresentacion) {
		this.caDiasParaPresentacion = caDiasParaPresentacion;
	}

	public int getCaDiasDemoraCalculados() {
		return caDiasDemoraCalculados;
	}

	public void setCaDiasDemoraCalculados(int caDiasDemoraCalculados) {
		this.caDiasDemoraCalculados = caDiasDemoraCalculados;
	}

	public PgimContratoDTO getPgimContratoDTO() {
		return pgimContratoDTO;
	}

	public void setPgimContratoDTO(PgimContratoDTO pgimContratoDTO) {
		this.pgimContratoDTO = pgimContratoDTO;
	}

	public int getPlazoAbsolucion() {
		return plazoAbsolucion;
	}

	public void setPlazoAbsolucion(int plazoAbsolucion) {
		this.plazoAbsolucion = plazoAbsolucion;
	}
	
	public void setCaDiasDemoraInforme1(int caDiasDemoraInforme1) {
		this.caDiasDemoraInforme1 = caDiasDemoraInforme1;
	}

	public void setCaDiasDemoraDesde1ObsHastaInfConforme(int caDiasDemoraDesde1ObsHastaInfConforme) {
		this.caDiasDemoraDesde1ObsHastaInfConforme = caDiasDemoraDesde1ObsHastaInfConforme;
	}

	public int getCaDiasDemoraInforme1() {
		return this.caDiasDemoraInforme1;
	}

	public int getCaDiasDemoraDesde1ObsHastaInfConforme() {
		return this.caDiasDemoraDesde1ObsHastaInfConforme;
	}

	public String getFeObservacion1() {
		return feObservacion1;
	}

	public void setFeObservacion1(String feObservacion1) {
		this.feObservacion1 = feObservacion1;
	}

	public String getFeRealPresentacion() {
		return feRealPresentacion;
	}

	public void setFeRealPresentacion(String feRealPresentacion) {
		this.feRealPresentacion = feRealPresentacion;
	}
	
}
