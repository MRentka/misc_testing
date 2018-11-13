package base;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

public class MyRequestClientProperties {

	private static final String PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\r\n" + 
			"MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALRiMLAh9iimur8V\r\n" + 
			"A7qVvdqxevEuUkW4K+2KdMXmnQbG9Aa7k7eBjK1S+0LYmVjPKlJGNXHDGuy5Fw/d\r\n" + 
			"7rjVJ0BLB+ubPK8iA/Tw3hLQgXMRRGRXXCn8ikfuQfjUS1uZSatdLB81mydBETlJ\r\n" + 
			"hI6GH4twrbDJCR2Bwy/XWXgqgGRzAgMBAAECgYBYWVtleUzavkbrPjy0T5FMou8H\r\n" + 
			"X9u2AC2ry8vD/l7cqedtwMPp9k7TubgNFo+NGvKsl2ynyprOZR1xjQ7WgrgVB+mm\r\n" + 
			"uScOM/5HVceFuGRDhYTCObE+y1kxRloNYXnx3ei1zbeYLPCHdhxRYW7T0qcynNmw\r\n" + 
			"rn05/KO2RLjgQNalsQJBANeA3Q4Nugqy4QBUCEC09SqylT2K9FrrItqL2QKc9v0Z\r\n" + 
			"zO2uwllCbg0dwpVuYPYXYvikNHHg+aCWF+VXsb9rpPsCQQDWR9TT4ORdzoj+Nccn\r\n" + 
			"qkMsDmzt0EfNaAOwHOmVJ2RVBspPcxt5iN4HI7HNeG6U5YsFBb+/GZbgfBT3kpNG\r\n" + 
			"WPTpAkBI+gFhjfJvRw38n3g/+UeAkwMI2TJQS4n8+hid0uus3/zOjDySH3XHCUno\r\n" + 
			"cn1xOJAyZODBo47E+67R4jV1/gzbAkEAklJaspRPXP877NssM5nAZMU0/O/NGCZ+\r\n" + 
			"3jPgDUno6WbJn5cqm8MqWhW1xGkImgRk+fkDBquiq4gPiT898jusgQJAd5Zrr6Q8\r\n" + 
			"AO/0isr/3aa6O6NLQxISLKcPDk2NOccAfS/xOtfOz4sJYM3+Bs4Io9+dZGSDCA54\r\n" + 
			"Lw03eHTNQghS0A==\r\n" + 
			"-----END PRIVATE KEY-----";
		
	public MyRequestClientProperties() {
		headers.put("accept", "application/json");
		fields.put("oauth_version", "1.0");
		fields.put("oauth_nonce", generateNonce());
		fields.put("oauth_timestamp", generateTimeStamp());
		fields.put("oauth_signature_method", "RSA-SHA1");
		generateSignature();
		fields.put("oauth_signature", "this_is_the_signature");
		endpoint = "http://term.ie/oauth/example/request_token.php?";
	}
	
	private String endpoint;
	private Map<String, String> headers = new HashMap<>();
	private Map<String, Object> fields = new HashMap<>();
	private String signature;
	
	private String generateSignature() {
		StringBuilder baseString = new StringBuilder();
		fields.forEach((key, value) -> baseString.append(key).append("=").append(value).append("&"));
		System.out.println(baseString);
		
		//TODO: get Private Key from String and sign the baseString with the Private Key  
		
		try {
			Signature privateSignature = Signature.getInstance("SHA1withRSA");
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		
		return "";
	}
	
	private String generateTimeStamp() {
		return Long.toString(System.currentTimeMillis());
	}
	
	private String generateNonce() {
		SecureRandom secureRandom = new SecureRandom();
	    StringBuilder stringBuilder = new StringBuilder();
	    for (int i = 0; i < 15; i++) {
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
}
