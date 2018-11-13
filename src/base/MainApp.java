package base;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainApp {

	public static void main(String[] args) {
		System.out.println(fetchResponse("http://httpbin.org/post").getBody().toString());
		System.out.println(fetchResponse("http://httpbin.org/post").getStatusText());
		System.out.println(fetchResponse("http://httpbin.org/post").getStatus());
		
		MyRestClient myRestClient = new MyRestClient();
	}
	
	private static HttpResponse<JsonNode> fetchResponse(String endpoint) {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post(endpoint)
					  .header("accept", "application/json")
					  .queryString("apiKey", "123")
					  .field("parameter", "value")
					  .field("foo", "bar")
					  .asJson();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}	
		return null;
	}
}
