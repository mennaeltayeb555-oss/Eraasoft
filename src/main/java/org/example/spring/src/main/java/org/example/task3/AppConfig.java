package org.example.task3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
//دي هي نفس الـ XML بالظبط
@Configuration
public class AppConfig {

    @Bean(
            initMethod = "init",
            destroyMethod = "destroy"
    )
    @Scope("prototype")
    public PersonService personService() {
        return new PersonService();
    }
}