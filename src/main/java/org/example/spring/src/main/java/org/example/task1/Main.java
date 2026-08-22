package org.example.task1;

import org.example.task1.UserService;
import org.example.task1.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        // create the container (context) using AppConfig class
        // this scans the packages and registers the beans (PersonService, ManagerService)
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // get the bean named "personService" from the container
        // both classes implement UserService, so we cast it to UserService
        UserService person = (UserService) context.getBean("personService");

        // get the bean named "managerService" from the container
        UserService manager = (UserService) context.getBean("managerService");

        // call save() and update() on the PersonService bean
        person.save("menna");
        person.update("menna");

        // call save() and update() on the ManagerService bean
        manager.save("mohamed");
        manager.update("mohamed");
    }
}