/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mwitosz.common.Utils.ASSERT;

/**
 *
 * @author mwito
 */
public class TestUtils {

    public static String convertToJson(Object obj) {
        
        String jsonString = null;
        
        try {
            jsonString = new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            ASSERT(false);
        }
        
        return jsonString;
    }
}
