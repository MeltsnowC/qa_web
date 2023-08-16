package com.bingo.qa;

import com.bingo.qa.controller.MyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

/**
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class QaApplication {
    @Autowired
    private MyController myController;

    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class, args);
    }
    @GetMapping("/config")
    public String getConfig(){
        return myController.getConfig();
    }

}