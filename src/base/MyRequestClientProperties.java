package base;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class MyRequestClientProperties {

	public MyRequestClientProperties() {
	    
		headers.put("accept", "application/json");
		fields.put("oauth_consumer_key", "OauthKey");
		fields.put("oauth_version", "1.0");
		fields.put("oauth_nonce", generateNonce());
		fields.put("oauth_timestamp", generateTimeStamp());
		fields.put("oauth_signature_method", "RSA-SHA1");
		generateSignature();
		fields.put("oauth_signature", "this_is_the_signature");
		endpoint = "https://jira-test.begasoft.ch/plugins/servlet/oauth/request-token?";
	}
	
	private String endpoint;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, Object> fields = new HashMap<>();
	private String signature;
	
	private String generateBaseString() {
	    
	    StringBuilder baseString = new StringBuilder();
	    fields.forEach((key, value) -> baseString.append(key).append("=").append(value).append("&"));
	    return baseString.toString();
	}
	
	private String generateSignature() {
		
	    String baseString = generateBaseString();
	    System.out.println(baseString); //for debugging
	    
            return "";
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
	    System.out.println(randomNumber);
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
}
