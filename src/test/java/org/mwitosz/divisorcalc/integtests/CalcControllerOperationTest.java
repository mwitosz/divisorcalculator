/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.integtests;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.mwitosz.divisorcalc.controllers.ActionResult;
import org.mwitosz.divisorcalc.controllers.DivisorCalcController;
import org.mwitosz.divisorcalc.controllers.DivisorCalcController.CalcResult;
import org.mwitosz.divisorcalc.services.WordMapperService;
import org.mwitosz.divisorcalc.services.WordMapperServiceImpl;
import org.mwitosz.divisorcalc.services.WordMapping;

/**
 *
 * @author mwito
 */
public class CalcControllerOperationTest {
    
    private DivisorCalcController controller = null;
    
    public WordMapperService setupMapperService() {
        WordMapperServiceImpl mapper = new WordMapperServiceImpl();
        
        WordMapping animals = new WordMapping("Animals");
        animals.addEntry(1, "Mouse");
        animals.addEntry(2, "Cat");
        animals.addEntry(3, "Dog");
        animals.addEntry(4, "Elephant");
        animals.addEntry(5, "Goose");
        animals.addEntry(6, "Bear");
        animals.addEntry(7, "Horse");
        animals.addEntry(8, "Dophin");
        animals.addEntry(9, "Falcon");
        animals.addEntry(10, "Hamster");
        
        
        WordMapping furnitures = new WordMapping("Furnitures");
        furnitures.addEntry(1, "Chair");
        furnitures.addEntry(2, "Table");
        furnitures.addEntry(3, "Cabinet");
        furnitures.addEntry(4, "Bed");
        furnitures.addEntry(5, "Desk");
        furnitures.addEntry(6, "Armchair");
        furnitures.addEntry(7, "Cupboard");
        furnitures.addEntry(8, "Rack");
        furnitures.addEntry(9, "Drawer");
        furnitures.addEntry(10, "Closet");
        
        
        WordMapping planets = new WordMapping("Planets");
        planets.addEntry(1, "Earth");
        planets.addEntry(2, "Mars");
        planets.addEntry(3, "Saturn");
        planets.addEntry(4, "Mercury");
        planets.addEntry(5, "Uran");
        planets.addEntry(6, "Neptun");
        planets.addEntry(7, "Venus");
        planets.addEntry(8, "Juno");
        planets.addEntry(9, "Westa");
        planets.addEntry(10, "Hebe");
        
        mapper.registerMapping(animals);
        mapper.registerMapping(furnitures);
        mapper.registerMapping(planets);
        
        return mapper;
    }
    
    @BeforeEach
    public void setUp() {
        
        controller = new DivisorCalcController();
        
        // MW: Simulate "Spring" managed bean injection
        //
        controller.setWordMapperService(setupMapperService());        
    }
    
    @AfterEach
    public void tearDown() {
        controller = null;
    }
    
    @Test
    public void testCalculationAndMapping() {
        
        ActionResult<CalcResult> result = controller.calculateAndMap("Animals", Arrays.asList(1));
        
        assertEquals(ActionResult.Status.SUCCESS, result.getStatus());
        assertEquals(1, result.getObject().keySet().size());
        assertEquals(new HashMap<Integer, List<String>>() {{
                put(1, Arrays.asList("Mouse"));
        }}, result.getObject());
        
        result = controller.calculateAndMap("Furnitures", Arrays.asList(10, 7, 4));
        
        assertEquals(ActionResult.Status.SUCCESS, result.getStatus());
        assertEquals(3, result.getObject().keySet().size());
        assertEquals(new HashMap<Integer, List<String>>() {{
                put(10, Arrays.asList("Chair", "Table", "Desk", "Closet"));
                put(7, Arrays.asList("Chair", "Cupboard"));
                put(4, Arrays.asList("Chair", "Table", "Bed"));
        }}, result.getObject());
        
        result = controller.calculateAndMap("Planets", Arrays.asList(10, 7, 4));
        
        assertEquals(ActionResult.Status.SUCCESS, result.getStatus());
        assertEquals(3, result.getObject().keySet().size());
        assertEquals(new HashMap<Integer, List<String>>() {{
                put(10, Arrays.asList("Earth", "Mars", "Uran", "Hebe"));
                put(7, Arrays.asList("Earth", "Venus"));
                put(4, Arrays.asList("Earth", "Mars", "Mercury"));
        }}, result.getObject());
        
    }
    
    @Test
    public void testInsufficientMapping() {
        
        // MW: 22 divisors are: 1, 2, 11, 22
        //     We don't have mapping for 11 and 22 - so the controller should return null
        //
        ActionResult<CalcResult> result = controller.calculateAndMap("Furnitures", Arrays.asList(22));
        assertEquals(ActionResult.Status.FAILURE, result.getStatus());
        assertEquals("Can't find mapping for divisor: " + 11, result.getFailureInfo());
    }
    
    @Test
    public void testUnknowntMapping() {
        
        // MW: 22 divisors are: 1, 2, 11, 22
        //     We don't have mapping for 11 and 22 - so the controller should return null
        //
        ActionResult<CalcResult> result = controller.calculateAndMap("Streets", Arrays.asList(4));
        assertEquals(ActionResult.Status.FAILURE, result.getStatus());
        assertEquals("Unknown mapping type: " + "Streets", result.getFailureInfo());
    }
}
