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
            repository.save(new Animal(40, new Date(2021,11,24),  "Danskerland"));
            repository.save(new Animal(20, new Date(2021,1,1),  "Svenskerland"));

            repository.findAll().forEach(employee -> log.info("Preloaded " + employee));
            };
    }

}
