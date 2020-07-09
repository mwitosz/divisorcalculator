/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mwitosz.divisorcalc.services.WordMapping;

/**
 *
 * @author mwito
 */
public class WordMapperServiceTest {
    
    @Test
    public void testMappingConstructor() {
        WordMapping mapping = new WordMapping("Animals");
        assertEquals("Animals", mapping.getName());
    }
    
    @Test
    public void testNoMapping() {
        WordMapping emptyMapping = new WordMapping("Animals");
        assertEquals(null, emptyMapping.mapNumber(1));
        
        WordMapping insufficientMapping = new WordMapping("Furnitures");
        insufficientMapping.addEntry(1, "Chair");
        insufficientMapping.addEntry(5, "Bed");
        assertEquals("Chair", insufficientMapping.mapNumber(1));
        assertEquals("Bed", insufficientMapping.mapNumber(5));
        assertEquals(null, insufficientMapping.mapNumber(3));
        assertEquals(null, insufficientMapping.mapNumber(10));
    }
    
    @Test
    public void testGoodMapping() {
        WordMapping animalMapping = new WordMapping("Animals");
        animalMapping.addEntry(1, "Cat");
        animalMapping.addEntry(5, "Goose");
        animalMapping.addEntry(10, "Dog");
        assertEquals("Dog", animalMapping.mapNumber(10));
        assertEquals("Cat", animalMapping.mapNumber(1));
        assertEquals("Goose", animalMapping.mapNumber(5));
        
        WordMapping furnitureMapping = new WordMapping("Furnitures");
        furnitureMapping.addEntry(1, "Chair");
        furnitureMapping.addEntry(5, "Bed");
        assertEquals("Chair", furnitureMapping.mapNumber(1));
        assertEquals("Bed", furnitureMapping.mapNumber(5));
    }
}
