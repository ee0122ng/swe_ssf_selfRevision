package iss.ssf.selfRevisionopenWeatherMapAPI.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import iss.ssf.selfRevisionopenWeatherMapAPI.model.Weather;
import iss.ssf.selfRevisionopenWeatherMapAPI.service.WeatherService;

@Controller
@RequestMapping(path={"/api/weather"})
public class WeatherController {

    @Autowired
    WeatherService weatherSvc;

    @GetMapping()
    public String getWeather(@RequestParam(required = false, defaultValue="Singapore") String city, Model model) {

        Optional<Weather> opt = weatherSvc.getWeather(city);

        Weather weather = new Weather();

        if (!opt.isEmpty()) {

            weather = opt.get();

        }
        
        System.out.println(">> weather: " + weather.toString());

        model.addAttribute("weather", weather);
        model.addAttribute("city", city);

        return "weather";
    }

    
}
