/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author mwito
 */
public class WordMapperServiceImpl implements WordMapperService {
    
    private Map<String, WordMapping> mappingRepository = new HashMap<>();

    public void registerMapping(WordMapping mapping) {
        mappingRepository.put(mapping.getName(), mapping);
    }
    
    @Override
    public String mapNumber(String mappingName, Integer number) {
        if (mappingRepository.containsKey(mappingName)) {
            return mappingRepository.get(mappingName).mapNumber(number);
        } else {
            return null;
        }
    }

    @Override
    public boolean isValidMapping(String mappingType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
