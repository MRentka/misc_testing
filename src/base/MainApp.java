package base;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MainApp {

    /*
     * Endpoints:
     * http://httpbin.org/post
     * https://jira-test.begasoft.ch/plugins/servlet/oauth/
     */
    public static void main(String[] args) {

//	System.out.println(fetchResponse("https://jira-test.begasoft.ch/plugins/servlet/oauth/").getBody().toString());
//	System.out.println(fetchResponse("https://jira-test.begasoft.ch/plugins/servlet/oauth/").getStatusText());
//	System.out.println(fetchResponse("https://jira-test.begasoft.ch/plugins/servlet/oauth/").getStatus() + "\n");
	MyRestClient myRestClient = new MyRestClient();
	HttpResponse<String> response = myRestClient.customResponseAsString();
//	System.out.println(response.getHeaders().toString());
	System.out.println(response.getBody().toString());
	
    }
	
    /*
     * Zum testen der Unirest Lib
     */
    @SuppressWarnings("unused")
    private static HttpResponse<String> fetchResponse(String endpoint) {
	
	try {
	    HttpResponse<String> response = Unirest.post(endpoint)
		    .header("accept", "application/json")
		    .queryString("apiKey", "123")
		    .field("parameter", "value")
		    .field("foo", "bar")
		    .asString();
	    return response;
	} catch (UnirestException e) {
	    e.printStackTrace();
	}	
	return null;
    }
}
