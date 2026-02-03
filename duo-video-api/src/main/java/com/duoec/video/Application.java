package com.duoec.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xuwenzhen
 * @since 2026/02/01 10:59
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.duoec")
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}