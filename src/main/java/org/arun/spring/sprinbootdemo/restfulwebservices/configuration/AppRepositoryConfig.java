package org.arun.spring.sprinbootdemo.restfulwebservices.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


// Arun this is used to not expose entity methods and configure various repository settings.
@Configuration
public class AppRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        config.setExposeRepositoryMethodsByDefault(false);

    }
}
