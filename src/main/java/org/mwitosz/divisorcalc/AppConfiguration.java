/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc;

import static java.util.stream.Collectors.mapping;
import org.mwitosz.divisorcalc.services.WordMapperService;
import org.mwitosz.divisorcalc.services.WordMapperServiceImpl;
import org.mwitosz.divisorcalc.services.WordMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author mwito
 */
@Configuration
public class AppConfiguration {
    
    @Bean
    WebMvcConfigurer setupWebMvc() {
        return new WebConfiguration();
    }
    
    @Bean
    WordMapperService setupWordMapper() {
        
        WordMapperServiceImpl mapper = new WordMapperServiceImpl();
        
        WordMapping animals = new WordMapping("Animals");
        animals.addEntry(1, "Mouse");
        animals.addEntry(2, "Cat");
        animals.addEntry(3, "Dog");
        animals.addEntry(4, "Elephant");
        animals.addEntry(5, "Goose");
        animals.addEntry(6, "Bear");
        
        WordMapping furnitures = new WordMapping("Furnitures");
        furnitures.addEntry(1, "Chair");
        furnitures.addEntry(2, "Table");
        furnitures.addEntry(3, "Cabinet");
        furnitures.addEntry(4, "Bed");
        furnitures.addEntry(5, "Desk");
        furnitures.addEntry(6, "Armchair");
        
        WordMapping planets = new WordMapping("Planets");
        planets.addEntry(1, "Earth");
        planets.addEntry(2, "Mars");
        planets.addEntry(3, "Saturn");
        planets.addEntry(4, "Mercury");
        planets.addEntry(5, "Uran");
        planets.addEntry(6, "Neptun");
        
        mapper.registerMapping(animals);
        mapper.registerMapping(furnitures);
        mapper.registerMapping(planets);
        
        return mapper;
    }
}
