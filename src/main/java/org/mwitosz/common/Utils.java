package org.mwitosz.common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mwito
 */
public class Utils {

    public static final void ASSERT(boolean condition) {
        if (!condition) {
            throw new RuntimeException("ASSERT: condition failed");
        }
    }
}
