package iss.ssf.selfRevisionopenWeatherMapAPI.service;

import java.io.StringReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import iss.ssf.selfRevisionopenWeatherMapAPI.model.Weather;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {

    @Value("${weathermap.key}")
    private String WEATHERMAP_KEY;

    private final String URL = "https://api.openweathermap.org/data/2.5/weather";

    public Optional<Weather> getWeather(String city) {

        String cityName = "";
        if (city.contains(" ")) {
            cityName = city.replace(" ", "+");
        } else {
            cityName = city;
        }

        String url = UriComponentsBuilder
            .fromUriString(URL)
            .queryParam("q", cityName)
            .queryParam("appid", WEATHERMAP_KEY)
            .toUriString();

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .build();

        RestTemplate template = new RestTemplate();

        try {
            ResponseEntity<String> resp = template.exchange(req, String.class);

            String payload = resp.getBody();
            Integer status = resp.getStatusCode().value();
            Weather weather = new Weather();

            JsonReader jReader = Json.createReader(new StringReader(payload));
            JsonObject jObj = jReader.readObject();

            JsonArray weatherArr = jObj.getJsonArray("weather");
            for(int i = 0; i < weatherArr.size(); i++) {
                JsonObject jWeather = weatherArr.getJsonObject(i);
                weather.setId(jWeather.getJsonNumber("id").longValue());
                weather.setMain(jWeather.getString("main"));
                weather.setDesc(jWeather.getString("description"));
            }

            JsonObject jMain = jObj.getJsonObject("main");
            JsonObject jSys = jObj.getJsonObject("sys");

            Float humidity = (float) (jMain.getJsonNumber("humidity").doubleValue());
            weather.setHumidity(humidity);

            // convert from Kelvin to Celcius
            Float temp = (float) (jMain.getJsonNumber("temp").doubleValue() - 273.15);
            weather.setTemp(temp);

            Float temp_min = (float) (jMain.getJsonNumber("temp_min").doubleValue() - 273.15);
            weather.setTemp_min(temp_min);

            Float temp_max = (float) (jMain.getJsonNumber("temp_max").doubleValue() - 273.15);
            weather.setTemp_max(temp_max);

            String country = jSys.getString("country");
            weather.setLocation(country + "-" + cityName);

            return Optional.of(weather);

        } catch (HttpClientErrorException ex) {
            
            return Optional.empty();
        }
        
        
    }
    
    
}
