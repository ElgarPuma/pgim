package pe.gob.osinergmin.pgim.survey;

public class SupervisionAgol {
	
	private String features;
	private String rollbackOnFailure;
	private String f;
	
	private String codSuperv;
	private String codUm;
	private String nomUm;
	private String codAgente;
	private String nomAgente;
	private String codSiged;
	private String especialidad;
	private String usuario;
	private String activo;
	private String supervisores;
	private String objectId;
	
	public String getFeatures() {
		return features;
	}
	public void setFeatures(String features) {
		this.features = features;
	}
	public String getRollbackOnFailure() {
		return rollbackOnFailure;
	}
	public void setRollbackOnFailure(String rollbackOnFailure) {
		this.rollbackOnFailure = rollbackOnFailure;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}	
	
	public String getCodSuperv() {
		return codSuperv;
	}
	public void setCodSuperv(String codSuperv) {
		this.codSuperv = codSuperv;
	}
	public String getCodUm() {
		return codUm;
	}
	public void setCodUm(String codUm) {
		this.codUm = codUm;
	}
	public String getNomUm() {
		return nomUm;
	}
	public void setNomUm(String nomUm) {
		this.nomUm = nomUm;
	}
	public String getCodAgente() {
		return codAgente;
	}
	public void setCodAgente(String codAgente) {
		this.codAgente = codAgente;
	}
	public String getNomAgente() {
		return nomAgente;
	}
	public void setNomAgente(String nomAgente) {
		this.nomAgente = nomAgente;
	}
	public String getCodSiged() {
		return codSiged;
	}
	public void setCodSiged(String codSiged) {
		this.codSiged = codSiged;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getSupervisores() {
		return supervisores;
	}
	public void setSupervisores(String supervisores) {
		this.supervisores = supervisores;
	}	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	public static String getStringAdd(SupervisionAgol obj) {
		
		String xmlSource = "features={"+
				   "\"attributes\":{"+
				      "\"COD_SUPERV\":\""+obj.getCodSuperv()+"\","+
				      "\"COD_UM\":\""+obj.getCodUm()+"\","+
				      "\"NOM_UM\":\""+obj.getNomUm()+"\","+
				      "\"COD_AGENTE\":\""+obj.getCodAgente()+"\","+
				      "\"NOM_AGENTE\":\""+obj.getNomAgente()+"\","+
				      "\"COD_SIGED\":\""+obj.getCodSiged()+"\","+
				      "\"ESPECIALIDAD\":\""+obj.getEspecialidad()+"\","+
				      "\"USUARIO\":\""+obj.getUsuario()+"\","+
				      "\"ACTIVO\":"+obj.getActivo()+","+
				      "\"SUPERVISORES\":\""+obj.getSupervisores()+"\""+
				   "}"+
				"}&rollbackOnFailure=false&F=json";
		
        return xmlSource;
    }
	
	public static String getStringUpdate(SupervisionAgol obj) {
			
			String sSource = "features={"+
					   "\"attributes\":{"+
					      "\"OBJECTID\":\""+obj.getObjectId()+"\","+					      
					      "\"ACTIVO\":"+obj.getActivo()+					      
					   "}"+
					"}&rollbackOnFailure=false&f=json";
						
	        return sSource;
	    }	

}
