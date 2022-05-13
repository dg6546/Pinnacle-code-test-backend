package com.example.demo.config;

import com.example.demo.model.Good;
import com.example.demo.repository.GoodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GoodConfig {

    @Bean
    CommandLineRunner commandLineRunner(GoodRepository repository){
        return args -> {
            Good book = new Good(
                    "book",
                    17.99,
                    "Stationery"
            );
            Good pencil = new Good(
                    "pencil",
                    2.99,
                    "Stationery"
            );
            Good potatoChips = new Good(
                    "potato chips",
                    3.99,
                    "food"
            );
            Good shirt = new Good(
                    "shirt",
                    29.99,
                    "clothing"
            );
            repository.saveAll(
                    List.of(book, pencil, potatoChips, shirt)
            );
        };
    }

}
