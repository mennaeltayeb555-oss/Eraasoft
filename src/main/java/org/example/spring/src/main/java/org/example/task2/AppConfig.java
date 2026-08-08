package org.example.task2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(personService());
    }
}