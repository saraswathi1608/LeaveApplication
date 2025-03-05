package com.example.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.utility.CommonsUtil;
import com.example.wrapper.Constants;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	/*Properties properties = CommonsUtil.getValueFromPropertyDynamically(Constants.EXTERNAL_CONFIG_FILE);
		String httpMethod = properties.getProperty(Constants.HTTP_METHOD);
		String ip = properties.getProperty(Constants.HOSTNAME);
		String port = properties.getProperty(Constants.PORT);
		String apiUrl = httpMethod + Constants.WEB_DELIMITER+ip+Constants.COLON+port;*/
        registry.addMapping("/**") // Allow all paths
                .allowedOrigins("http://localhost:3000") // Replace with your React app's origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials
    }
}
