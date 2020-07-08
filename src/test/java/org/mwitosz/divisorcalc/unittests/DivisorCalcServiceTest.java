/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.unittests;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mwitosz.divisorcalc.services.DivisorCalcService;
import org.mwitosz.divisorcalc.services.DivisorCalcServiceImpl;

/**
 *
 * @author mwito
 */
public class DivisorCalcServiceTest {
    
    @Test
    public void testDivisorCalculation() {
        
        DivisorCalcService service = new DivisorCalcServiceImpl();
        
        List<Integer> divisors = service.findDivisors(1);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 1);
        assertEquals(divisors.get(0), 1);
        
        divisors = service.findDivisors(2);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 2);
        assertEquals(divisors.get(0), 1);
        assertEquals(divisors.get(1), 2);
        
        divisors = service.findDivisors(4);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 3);
        assertEquals(divisors.get(0), 1);
        assertEquals(divisors.get(1), 2);
        assertEquals(divisors.get(2), 4);
        
        divisors = service.findDivisors(10);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 4);
        assertEquals(divisors.get(0), 1);
        assertEquals(divisors.get(1), 2);
        assertEquals(divisors.get(2), 5);
        assertEquals(divisors.get(3), 10);
        
        divisors = service.findDivisors(13);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 2);
        assertEquals(divisors.get(0), 1);
        assertEquals(divisors.get(1), 13);
        
        divisors = service.findDivisors(20);
        assertNotEquals(divisors, null);
        assertEquals(divisors.size(), 6);
        assertEquals(divisors.get(0), 1);
        assertEquals(divisors.get(1), 2);
        assertEquals(divisors.get(2), 4);
        assertEquals(divisors.get(3), 5);
        assertEquals(divisors.get(4), 10);
        assertEquals(divisors.get(5), 20);
    }
}
