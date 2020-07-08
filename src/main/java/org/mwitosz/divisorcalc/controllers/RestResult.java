/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.controllers;

/**
 *
 * @author mwito
 */
public enum RestResult {
    
    SUCCESS("SUCCESS");
    
    private String resultString;
    
    RestResult(String resultString) {
        this.resultString = resultString;
    }
}
