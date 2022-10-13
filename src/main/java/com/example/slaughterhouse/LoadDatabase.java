package com.example.slaughterhouse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AnimalRepository repository){
        return args -> {
            repository.save(new Animal(40, "24-12-2022",  "Danskerland"));
            repository.save(new Animal(20, "1-1-2021",  "Svenskerland"));

            repository.findAll().forEach(employee -> log.info("Preloaded " + employee));
            };
    }

}
