/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.services;

import java.util.HashMap;
import java.util.Map;
import static org.mwitosz.common.Utils.ASSERT;
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
        
        ASSERT(isValidMapping(mappingName));
        
        return mappingRepository.get(mappingName).mapNumber(number);
    }

    @Override
    public boolean isValidMapping(String mappingType) {
        return mappingRepository.containsKey(mappingType);
    }
    
}
