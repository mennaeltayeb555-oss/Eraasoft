package org.example.task1;

import org.example.task1.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext container =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService person =
                container.getBean("personService", UserService.class);

        UserService manager =
                container.getBean("managerService", UserService.class);

        person.save("Menna");
        person.update("Menna");

        manager.save("Mohamed");
        manager.update("Mohamed");
    }
}