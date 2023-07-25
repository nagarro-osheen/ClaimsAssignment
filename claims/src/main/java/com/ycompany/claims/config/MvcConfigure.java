package com.ycompany.claims.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configurer class to configure WebMvc related components
 */
@Component
public class MvcConfigure implements WebMvcConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MvcConfigure.class);

	@Value("${allowed.origins:*}")
	private String allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		LOGGER.info("Configure Cors Origin,allowed origins:{}", allowedOrigins);
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
				.allowedOrigins(allowedOrigins.split(","))
				.allowedHeaders("*");
	}
}
