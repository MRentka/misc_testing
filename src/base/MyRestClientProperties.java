package base;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.util.UriUtils;
public class MyRestClientProperties {

	public MyRestClientProperties() {
	    
	    headers.put("accept", "application/json");
	    fields.put("oauth_consumer_key", "OauthKey");
	    fields.put("oauth_version", "1.0");
	    fields.put("oauth_nonce", generateNonce());
	    fields.put("oauth_timestamp", generateTimeStamp());
	    fields.put("oauth_signature_method", "RSA-SHA1");
	    fields.put("oauth_signature", generateSignature());
	    endpoint = "https://jira-test.begasoft.ch/plugins/servlet/oauth/request-token?";
	}
	
	private String endpoint;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, Object> fields = new HashMap<>();
	private String signature;
	
	private String generateBaseString() {
	    
	    StringBuilder baseString = new StringBuilder();
	    fields.forEach((key, value) -> {
		try {
		    baseString.append(UriUtils.encode(key, "UTF8")).append("=") //brauchts wahrsch. nicht
		    .append(UriUtils.encode((String) value, "UTF8")).append("&");
		} catch (UnsupportedEncodingException e) {
		    e.printStackTrace();
		}
	    });
	    return baseString.toString();
	}
	
	private String generateSignature() {
	    
	    try {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(KeyReader.getPrivateKey());
		signature.update(generateBaseString().getBytes());
		return Base64.getUrlEncoder().encodeToString(signature.sign());
		
	    } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
		e.printStackTrace();
	    }
	    return null;
	}
	
	/*
	 * Unix-Timestamp: Entspricht verstrichenen Sekunden
	 * seit 1. Jan. 1970
	 */
	private String generateTimeStamp() {
	    
	    return Long.toString(System.currentTimeMillis()/1000);
	}
	
	private String generateNonce() {
	    
	    SecureRandom secureRandom = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder();
	    for (int i = 0; i < 6; i++) {
	        stringBuilder.append(secureRandom.nextInt(10));
	    }
	    String randomNumber = stringBuilder.toString();
	    return randomNumber;
	}

	public String getEndpoint() {
	    
		return endpoint;
	}
	
	public void setEndpoint(String endpoint) {
	    
		this.endpoint = endpoint;
	}
	
	public Map<String, String> getHeaders() {
	    
		return headers;
	}
	
	public void setHeaders(Map<String, String> headers) {
	    
		this.headers = headers;
	}
	
	public Map<String, Object> getFields() {
	    
		return fields;
	}
	
	public void setFields(Map<String, Object> fields) {
	    
		this.fields = fields;
	}

	
	public String getSignature() {
	
	    return signature;
	}

	
	public void setSignature(String signature) {
	
	    this.signature = signature;
	}
}
