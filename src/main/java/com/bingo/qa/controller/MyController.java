package com.bingo.qa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class MyController {

    @Autowired
    private Environment env;
    public MyController(Environment env) {
        this.env = env;
        System.out.println("Environment object has been injected: " + env);
    }

    @GetMapping("/config")
    public String getConfig() {
        String[] profiles = env.getActiveProfiles();
        String configLocation = env.getProperty("spring.config.location");
        String configName = env.getProperty("spring.config.name");
        return "Active profiles: " + Arrays.toString(profiles) + "\n" +
                "Config location: " + configLocation + "\n" +
                "Config name: " + configName;
    }
}
