package pe.gob.osinergmin.pgim.security;

public class TokenUtil {
			
	
	public static final String SECURITY_SIGNING_KEY = "MaYzkSjmkzPC57LlmjusXYjk=";
	
	public static final String SECURITY_REALM = "JWT PGIM";
	
	public static final String SECURITY_JWT_CLIENTE_ID = "pgimapp";
	public static final String SECURITY_JWT_CLIENTE_SECRET = "Pgimapp_2020$&";
	
	public static final String SECURITY_JWT_GRANT_TYPE = "password";
	public static final String SECURITY_JWT_REFRESH_TOKEN = "refresh_token";
	public static final String SECURITY_JWT_SCOPE_READ = "read";
	public static final String SECURITY_JWT_SCOPE_WRITE = "write";
	public static final String SECURITY_JWT_RESOURCE_ID = "pgimappresourceid";
	
	//Valores usados para consumir microservicio SISSEG-AS y obtener el Token de auntenticación
	
	public static final String SECURITY_SISSEG_AS_GRANT_TYPE = "client_credentials";
	public static final String SECURITY_SISSEG_AS_SCOPE = "sisseg_as";
	
	      
}
