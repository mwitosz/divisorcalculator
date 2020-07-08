/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.services;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mwito
 */
public class WordMapping {
    
    private String mappingName = null;
    
    private Map<Integer, String> mapping = new HashMap<>();
    
    public WordMapping(String name) {
        this.mappingName = name;
    }
    
    public void addEntry(Integer number, String word) {
        mapping.put(number, word);
    }
    
    public String getName() {
        return mappingName;
    }
    
    public String mapNumber(Integer number) {
        if (mapping.containsKey(number)) {
            return mapping.get(number);
        } else {
            return null;
        }
    }
}
