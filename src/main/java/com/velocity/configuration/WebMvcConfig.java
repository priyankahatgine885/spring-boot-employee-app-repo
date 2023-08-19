package com.velocity.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The type Web mvc config.
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * The constant MAX_AGE.
     */
    public static final int          MAX_AGE = 3600;
    private static final Logger      LOGGER  = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        LOGGER.info("adding cors mappings to CorsRegistry");
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE").maxAge(MAX_AGE)
                .allowedHeaders("Content-Disposition", "Content-Type", "X-Requested-With", "accept", "Origin",
                        "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization");
    }

}
