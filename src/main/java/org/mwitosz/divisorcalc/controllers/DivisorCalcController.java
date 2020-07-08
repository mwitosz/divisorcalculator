/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.mwitosz.divisorcalc.services.DivisorCalcServiceImpl;
import org.mwitosz.divisorcalc.services.WordMapperService;
import org.mwitosz.divisorcalc.services.DivisorCalcService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author mwito
 */
@RestController
@RequestMapping("/divisor_calculator")
public class DivisorCalcController {
    
    private static final Log log = LogFactory.getLog(DivisorCalcController.class);
    
    @Autowired
    private DivisorCalcService divisorFinder;
    
    @Autowired
    private WordMapperService mapper;
    
    // MW: The API between client and REST service is devined as below:
    //     - 
    //     - When the calculation and mapping was successfull then Map( number => list of words) is returned.
    //     - Otherwise (unknown mapping name, error) a null value is sent back.
    //
    @PostMapping(value="/calculate/{mapping}")
    Map<Integer, List<String>> calculateAndMap(@PathVariable("mapping") String mappingName, 
                                               @RequestBody List<Integer> numbers) {

        // MW: We don't need to implement transactions (there is no data in 
        //     database - just simple "math" services. Because of this - the application logic
        //     is implementd in REST API method.  
        
        Map<Integer, List<String>> mappingResult = new HashMap<Integer, List<String>>();
        
        log.debug("Going to map some numbers");
        log.debug("Mapping name: " + mappingName);
        
        for (Integer nextNumber: numbers) {
            
            log.debug("Number: " + nextNumber);
            
            List<Integer> divisors = divisorFinder.findDivisors(nextNumber);
            List<String> words = new LinkedList<String>();
            
            for (Integer nextDivisor: divisors) {
                String word = mapper.mapNumber(mappingName, nextDivisor);
                words.add(word);
            }
            
            mappingResult.put(nextNumber, words);
        }
        
        return mappingResult;
    }  
}
