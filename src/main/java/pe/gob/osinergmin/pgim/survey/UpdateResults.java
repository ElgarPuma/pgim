package pe.gob.osinergmin.pgim.survey;

public class UpdateResults {
	
	private String objectId;
	private String uniqueId;	
	private String globalId;	
	private String success;
	
	private String message;
	private String errorCode;
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getGlobalId() {
		return globalId;
	}
	public void setGlobalId(String globalId) {
		this.globalId = globalId;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	@Override
	public String toString() {
		return "UpdateResults [objectId=" + objectId + ", uniqueId=" + uniqueId 
				+ ", globalId="+ globalId 
				+ ", success="+success  + "]";
	}

}
