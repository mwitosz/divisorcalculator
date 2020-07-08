/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mwitosz.divisorcalc;

import javax.annotation.PostConstruct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 *
 * @author mwito
 */
@EnableAutoConfiguration
@ComponentScan({
    "org.mwitosz.divisorcalc.controllers",
    "org.mwitosz.divisorcalc.services"
})
@Import(AppConfiguration.class)
public class DivisorCalcApp {
    
    Log log = LogFactory.getLog(DivisorCalcApp.class);
     
    public static void main(String[] args) {
        SpringApplication.run(DivisorCalcApp.class, args);
    }

}
