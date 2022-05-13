package com.example.demo.config;

import com.example.demo.model.Location;
import com.example.demo.repository.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LocationConfig {

    @Bean
    CommandLineRunner commandLineRunner2(LocationRepository repository){
        return args -> {
            Location CA = new Location(
                    "California",
                    List.of("food"),
                    9.75
            );
            Location NY = new Location(
                    "New York",
                    List.of("food", "clothing"),
                    8.875
            );
            repository.saveAll(
                    List.of(CA, NY)
            );
        };
    }

}
