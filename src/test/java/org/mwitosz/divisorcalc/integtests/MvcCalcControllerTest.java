/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc.integtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mwitosz.common.TestUtils;
import org.mwitosz.divisorcalc.DivisorCalcApp;
import org.mwitosz.divisorcalc.controllers.ActionResult;
import org.mwitosz.divisorcalc.controllers.DivisorCalcController;
import org.mwitosz.divisorcalc.services.WordMapperService;
import org.mwitosz.divisorcalc.services.WordMapperServiceImpl;
import org.mwitosz.divisorcalc.services.WordMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author mwito
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MvcCalcControllerTest.class)
@ContextConfiguration(classes={DivisorCalcApp.class})
public class MvcCalcControllerTest {
    
    Log log = LogFactory.getLog(MvcCalcControllerTest.class);
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WordMapperService mapperService;
    
    public void setupMockBean() {
        
        when(mapperService.isValidMapping("Furnitures")).thenReturn(true);
        when(mapperService.mapNumber("Furnitures", 1)).thenReturn("Chair");
        when(mapperService.mapNumber("Furnitures", 2)).thenReturn("Table");
        when(mapperService.mapNumber("Furnitures", 3)).thenReturn("Cabinet");
        when(mapperService.mapNumber("Furnitures", 4)).thenReturn("Bed");
        when(mapperService.mapNumber("Furnitures", 5)).thenReturn("Desk");
        when(mapperService.mapNumber("Furnitures", 6)).thenReturn("Armchair");
        when(mapperService.mapNumber("Furnitures", 7)).thenReturn("Cupboard");
        when(mapperService.mapNumber("Furnitures", 8)).thenReturn("Rack");
        when(mapperService.mapNumber("Furnitures", 9)).thenReturn("Drawer");
        when(mapperService.mapNumber("Furnitures", 10)).thenReturn("Closet");
        
    }
    
    @BeforeEach
    public void setUp() {
        setupMockBean();        
    }
    
    @Test
    void testGoodMapping() throws Exception {

        List<Integer> numbers = Arrays.asList(1, 2, 4);
        
        String json = TestUtils.convertToJson(numbers);
        
        Map<Integer, List<String>> expectedMapping = new HashMap<Integer, List<String>>() {{
                put(1, Arrays.asList("Chair"));
                put(2, Arrays.asList("Chair", "Table"));
                put(4, Arrays.asList("Chair", "Table", "Bed"));
        }};
        
        mockMvc.perform(post("/divisor_calculator/calculate/Furnitures")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(content().string(TestUtils.convertToJson(ActionResult.Success(expectedMapping))));
    }
    
    @Test
    void testUnknownMapping() throws Exception {

        List<Integer> numbers = Arrays.asList(1, 2);
        
        String json = TestUtils.convertToJson(numbers);
        
        mockMvc.perform(post("/divisor_calculator/calculate/Songs")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(content().string(TestUtils.convertToJson(ActionResult.Failure("Unknown mapping type: " + "Songs"))));
    }
    
    @Test
    void testInvalidQueryParam() throws Exception {
        
        String cars = "[ \"Ford\", \"BMW\", \"Fiat\"]";
        
        mockMvc.perform(post("/divisor_calculator/calculate/Furnitures")
             .contentType(MediaType.APPLICATION_JSON)
             .content(cars))
             .andExpect(status().is4xxClientError());
    }
}
