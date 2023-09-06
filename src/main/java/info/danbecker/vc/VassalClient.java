package info.danbecker.vc;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class VassalClient {
    public static final String URL = "https://vassalengine.org";
    public static final String API = "/api/v1";
    public static final String PROJECTS = "/projects";
    public static final String API_KEY_NAME = "x-rapidapi-key";
    public static final String API_KEY_VALUE = "4b49c6b973mshf6e97423e8978fbp1d33aejsn287ed2a6d10d";

    public static void main(String[] args) throws URISyntaxException {
        System.out.println( "Hello World" );
        invokeGet( new URI( URL + API + PROJECTS ));
    }

    public static void invokeGet(URI uri) {
        HttpClient client = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri( uri )
                .GET()
                .header( API_KEY_NAME, API_KEY_VALUE)
                .timeout(Duration.ofSeconds(10))
                .build();


        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
