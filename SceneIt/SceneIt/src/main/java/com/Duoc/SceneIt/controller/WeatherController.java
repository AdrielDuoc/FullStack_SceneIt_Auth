package com.Duoc.SceneIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Duoc.SceneIt.dto.WeatherDTO;
import com.Duoc.SceneIt.service.WeatherService;

@Controller
@RequestMapping("/api/v1/clima")
public class WeatherController {

@Autowired
private WeatherService weatherService;

@GetMapping
public ResponseEntity<WeatherDTO> clima(
    @RequestParam(defaultValue = "-33.45") double lat,
    @RequestParam(defaultValue =  "70.65") double lon){

        System.out.println("[WeatherController] -> clima lat =" + lat + ", lon =" + lon);
        WeatherDTO resultado = weatherService.obtenerClima(lat, lon);
        return ResponseEntity.ok(resultado);
    }
    
}

