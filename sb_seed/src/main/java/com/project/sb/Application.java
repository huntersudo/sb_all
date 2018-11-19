package com.project.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * scanBasePackages = "com.project",对于引入了其他模块
 *
 */
@SpringBootApplication(scanBasePackages = "com.project")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

