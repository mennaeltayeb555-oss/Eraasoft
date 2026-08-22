package org.example.task2;

import org.example.task1.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        AccountServiceImpl accountService = context.getBean(AccountServiceImpl.class);
        accountService.save("Omar");
    }
}
