package com.example.serviceRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryServer 
{
private static Logger logger = LoggerFactory.getLogger(RegistryServer.class);
	
	public static void main(String[] args) {  
        SpringApplication.run(RegistryServer.class, args);  
        logger.info("***Spring boot RegistryServer Started***");
    }  
	
}
