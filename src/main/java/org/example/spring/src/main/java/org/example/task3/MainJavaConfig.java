package org.example.task3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainJavaConfig {

    public static void main(String[] args) {

        ApplicationContext container =
                new AnnotationConfigApplicationContext(AppConfig.class);

        PersonService person1 =
                container.getBean(PersonService.class);

        PersonService person2 =
                container.getBean(PersonService.class);

        person1.save("Menna");
        person2.save("Mohamed");

        System.out.println(person1 == person2);
    }
}