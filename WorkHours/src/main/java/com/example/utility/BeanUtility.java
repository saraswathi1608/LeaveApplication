package com.example.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.wrapper.WorkHoursDAO;
import com.example.wrapper.WorkHoursRestControllerWrapper;

@Configuration
public class BeanUtility {
	
	@Bean
	public WorkHoursRestControllerWrapper workHoursRestControllerWrapper() {
        return new WorkHoursRestControllerWrapper();
    }
	
	@Bean
	public WorkHoursDAO workHoursDAO() {
        return new WorkHoursDAO();
    }
	
	@Bean
	public DAOUtil daoUtil() {
        return new DAOUtil();
    }
	
}
