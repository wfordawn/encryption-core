package com.dawn.encryption;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EncryptionCoreApplication {


    public static void main(String[] args) {
        SpringApplication.run(EncryptionCoreApplication.class, args);
    }

}
