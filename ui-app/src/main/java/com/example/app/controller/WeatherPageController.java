package com.example.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("/secured")
@RequiredArgsConstructor
public class WeatherPageController {

    private final WebClient webClient;

    @GetMapping
    public String page(Model model, @CookieValue("SESSION") String sessionId) {
        String weatherResult = webClient.get().uri("/current-la-weather")
                .cookie("SESSION", sessionId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        model.addAttribute("currentWeather", weatherResult);
        return "secured";
    }
}
