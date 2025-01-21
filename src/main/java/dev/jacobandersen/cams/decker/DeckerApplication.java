package dev.jacobandersen.cams.decker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableCaching
public class DeckerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeckerApplication.class, args);
    }
}
