package org.example.task2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext container =
                new ClassPathXmlApplicationContext(
                        "applicationContext-task2.xml"
                );

        AccountService accountService =
                container.getBean(
                        "accountService",
                        AccountService.class
                );

        accountService.getSavePerson("Menna");
    }
}
