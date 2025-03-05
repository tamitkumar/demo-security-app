package com.org.security;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class SecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}