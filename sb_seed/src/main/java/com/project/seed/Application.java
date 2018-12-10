package com.project.seed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * scanBasePackages = "com.project",对于引入了其他模块的话(会自动装载此package下的)
 *
 * @author s
 *
 */
@SpringBootApplication(scanBasePackages = "com.project")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

