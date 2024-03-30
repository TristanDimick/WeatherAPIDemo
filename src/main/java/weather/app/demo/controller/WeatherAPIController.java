package weather.app.demo.controller;


import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import weather.app.demo.Service.WeatherAPIService;

@RestController
public class WeatherAPIController {
    @Autowired
    private WeatherAPIService weatherAPIService;


    @Value("${apiKey}") String apiKey;
    @GetMapping("/")
    public ModelAndView FormSubmission(ModelAndView model) {
        model.setViewName("index");
        model.addObject("form", new FormSubmission());
        return model;
    }

    @PostMapping("/")
    public String postMethod(@ModelAttribute FormSubmission form) {
        try {
            return "<h1>Results:</h1> <br>" + weatherAPIService.getWeather(form.getCityName());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "error";
    }   
}
