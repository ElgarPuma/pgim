package pe.gob.osinergmin.pgim.siged;

import java.io.StringWriter;
import java.security.Key;
import java.sql.Timestamp;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;

import gob.osinergmin.siged.remote.enums.InvocationResult;
import gob.osinergmin.siged.remote.rest.ro.base.BaseOutRO;
import gob.osinergmin.siged.remote.rest.ro.in.ReenviarSubFlujoInRO;
import gob.osinergmin.siged.rest.util.Invoker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReenviarSubflujo extends Invoker {
	
//	private static final Logger LOG = LoggerFactory.getLogger(SeguridadDAOImpl.class);
    private static final String ALGORITHM = "AES";
    private static final String ENC = "ENC";
    private static final String DES = "DES"; 
    
    public static String encrypt(String encryptionKey, String valueToEncrypt) throws Exception {
        return make(encryptionKey, valueToEncrypt, ENC);
    }
    
    public static String decrypt(String encryptionKey, String valueToDecrypt) throws Exception {
        return make(encryptionKey, valueToDecrypt, DES);
    }
    
    private static String make(String encryptionKey, String value, String type) throws Exception {
        Key key = new SecretKeySpec(encryptionKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        if (ENC.equals(type)) {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return new String(new Base64().encode(cipher.doFinal(value.getBytes())));
        } else if (DES.equals(type)) {
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(new Base64().decode(value.getBytes())));
        } else {
            return null;
        }
    }
    
    public static String getKeyval (String NOMBRE_APLICACION, String LLAVE_ENCRIPTACION){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String valueToEncrypt = NOMBRE_APLICACION+"_"+timestamp.getTime();
        String keyVal ="";
        try {
            keyVal = encrypt(LLAVE_ENCRIPTACION,valueToEncrypt);
        }
        catch(Exception exc)
        {
            keyVal ="";            
            log.error(exc.getMessage(), exc);
        }
        return keyVal;
    }
    
    public static BaseOutRO getEnviar(String serviceURL, ReenviarSubFlujoInRO reenviarSubFlujoInRO) 
    		throws Exception {
        BaseOutRO baseOutRO = null;
        try {
            JAXBContext c = JAXBContext.newInstance(new Class[]{ReenviarSubFlujoInRO.class});
            StringWriter swExpediente = new StringWriter();
            Marshaller marshaller = c.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_CHARACTER_ENCODING);
            marshaller.marshal(reenviarSubFlujoInRO, swExpediente);
            baseOutRO = getResultBean(serviceURL, swExpediente.toString(), BaseOutRO.class);
        } catch (Exception ex) {
        	throw ex;
        }
        if(baseOutRO.getErrorCode()!=null){
            baseOutRO.setResultCode(InvocationResult.FAILED.getCode());
        }
        return baseOutRO; 
    }

}
