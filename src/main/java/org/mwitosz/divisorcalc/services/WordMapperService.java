/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.services;

/**
 *
 * @author mwito
 */
public interface WordMapperService {
    
    public boolean isValidMapping(String mappingType);
    
    public String mapNumber(String mappingType, Integer number);
    
}
