package org.example.config;

import org.example.Desktop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    // @Bean(name = {"com2", "Beast", "desktop1"})  //bean nameing
    @Bean
    @Scope("prototype")
    public Desktop desktop(){
        return new Desktop();
    }
}
