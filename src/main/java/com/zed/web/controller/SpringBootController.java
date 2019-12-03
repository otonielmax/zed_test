/******************************************************************
 *
 * This code is for the Complaints service project.
 *
 *
 * © 2018, Complaints Management All rights reserved.
 *
 *
 ******************************************************************/
package com.zed.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.zed.config.ApplicationConfig;

/***
 * Configuration class for Spring IOC
 *
 * @author omarquez
 * 
 * @version 1.0
 */

@SpringBootApplication
@Import({ApplicationConfig.class})
public class SpringBootController  {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.setProperty("ZED_HOME", "/zed");
        SpringApplication.run(SpringBootController.class, args);
    }

}
