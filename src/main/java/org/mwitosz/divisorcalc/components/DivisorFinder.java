/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.components;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import static org.mwitosz.common.Utils.ASSERT;

/**
 *
 * @author mwito
 */
@Service
public class DivisorFinder {

    public static final Log log = LogFactory.getLog(DivisorFinder.class);
    
    public List<Integer> findDivisors(Integer number) {
        
        log.debug("Searching for " + number + " divisors");
        
        List<Integer> allDivisors = new LinkedList<Integer>();
        List<Integer> topDivisors = new LinkedList<Integer>();
        
        for (int divisor = 1; divisor<=(int)Math.sqrt((double)number); ++divisor) {
            if ((number % divisor) == 0) {
                
                log.debug("Divisor found: " + divisor);
                        
                allDivisors.add(divisor);
            }
        }
        
        for (int index=(allDivisors.size()-1); index >= 0; --index) {
            
            Integer lowDivisor = allDivisors.get(index);
            Integer topDivisor = (number / lowDivisor);
            
            ASSERT(number % lowDivisor == 0);
            
            log.debug("Low divisor: " + lowDivisor + ", Top divisor: " + topDivisor);
            
            if (!topDivisor.equals(lowDivisor)) {
                allDivisors.add(topDivisor);
            }
        }
        
        return allDivisors;
    }
}
