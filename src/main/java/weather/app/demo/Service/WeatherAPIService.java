package weather.app.demo.Service;

import java.util.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class WeatherAPIService {

    @Value("${apiKey}") String apiKey;
    @SuppressWarnings("unchecked")
    public HashMap<String,String> getWeather(String cityName) throws URISyntaxException{

        System.out.println(cityName);
        HttpRequest request;
        System.out.println(("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey).equals("https://api.openweathermap.org/data/2.5/forecast?q=seattle&appid=c8391656235ffafcb459c568d82afb55"));
        
        HttpResponse<String> response = null;
            try {
                request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String,String> myMap = new HashMap<String, String>();

            try {
                myMap = objectMapper.readValue(response.body(), HashMap.class);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
           
        return myMap;
    }
    
}
