package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class APIGatewayApplication 
{
	private static Logger logger = LogManager.getLogger("api-gateway");
	
	public static void main(String[] args) {  
		logger.info("Initializing CygNet BOSS API Gateway Engine");
	    ApplicationContext applicationContext = SpringApplication.run(APIGatewayApplication.class);
	    logger.info("Initialized CygNet BOSS API Gateway Engine {}", applicationContext);

    }  
	
}
