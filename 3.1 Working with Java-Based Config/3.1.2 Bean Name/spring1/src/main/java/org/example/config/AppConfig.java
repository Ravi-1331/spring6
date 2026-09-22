package org.example.config;

import org.example.Desktop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean(name = {"com2", "Beast", "desktop1"})  //bean nameing
    @Bean
    public Desktop desktop(){
        return new Desktop();
    }
}
