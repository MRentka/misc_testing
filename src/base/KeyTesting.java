package base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.xml.bind.DatatypeConverter;

public class KeyTesting {
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
	
	byte[] fileBytes = Files.readAllBytes(new File("keystorage/jira_privatekey.pkcs8").toPath());
	String fileContent = new String(fileBytes);
	System.out.println(fileContent);
	fileContent = fileContent.replaceAll("\\r", "").replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
	System.out.println(fileContent);
	
	PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(DatatypeConverter.parseBase64Binary(fileContent));
	KeyFactory fac = KeyFactory.getInstance("RSA");
	PrivateKey pk = fac.generatePrivate(spec);
    }
    
    
}
