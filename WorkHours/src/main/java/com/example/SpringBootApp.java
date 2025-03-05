package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.wrapper.WorkHoursRestControllerWrapper;

@SpringBootApplication  
@EnableEurekaClient
public class SpringBootApp {
	
	private static Logger logger = LoggerFactory.getLogger(SpringBootApp.class);
	
	public static void main(String[] args) {  
        SpringApplication.run(SpringBootApp.class, args);  
        logger.info("***Spring boot Application Started***");
    }  
	
	@Component
	class ScheduledTasks {
		
		@Autowired
		private WorkHoursRestControllerWrapper restWrapper;
		// This method will be triggered at midnight on January 1st every year
	    @Scheduled(cron = "0 0 0 1 1 ? *")
	    public void runTaskOnNewYear() {
	        System.out.println("Happy New Year! The task is running.");
	        // Your specific method logic here
	        restWrapper.updateNewLeaveDetails();
	    }
	}

}
