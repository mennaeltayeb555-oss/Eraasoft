package org.example.task2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainJavaConfig {

    public static void main(String[] args) {

        ApplicationContext container =
                new AnnotationConfigApplicationContext(AppConfig.class);

        AccountService accountService =
                container.getBean(AccountService.class);

        accountService.getSavePerson("Menna");
    }
}