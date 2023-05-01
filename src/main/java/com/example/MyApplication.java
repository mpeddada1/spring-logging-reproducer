package com.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RefreshScope
@RestController
public class MyApplication {

    private static final Log LOGGER = LogFactory.getLog(MyApplication.class);

    @Value("hello")
    String welcomeText;

    public static void main(String[] args) {
        LOGGER.info("Logging a message");
        SpringApplication.run(MyApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String welcomeText() {
        return welcomeText;
    }
}