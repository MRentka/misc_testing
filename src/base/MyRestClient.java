package base;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MyRestClient {
	
	public MyRestClient() {
	    
		properties = new MyRestClientProperties();
	}
	
	private MyRestClientProperties properties;

	public HttpResponse<JsonNode> customResponseAsJSON() {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post(this.properties.getEndpoint())
					.headers(this.properties.getHeaders())
					.fields(this.properties.getFields())
					.asJson();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
		return null;
	}

	public HttpResponse<String> customResponseAsString() {
		try {
			HttpResponse<String> jsonResponse = Unirest.post(this.properties.getEndpoint())
					.headers(this.properties.getHeaders())
					.fields(this.properties.getFields())
					.asString();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
		return null;
	}

	
	public MyRestClientProperties getProperties() {
	
	    return properties;
	}

	
	public void setProperties(MyRestClientProperties properties) {
	
	    this.properties = properties;
	}
}
