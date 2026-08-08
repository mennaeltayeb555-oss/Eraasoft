package org.example.task3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext container =
                new ClassPathXmlApplicationContext(
                        "applicationContext-task3.xml"
                );

        PersonService person1 =
                container.getBean("personService", PersonService.class);

        PersonService person2 =
                container.getBean("personService", PersonService.class);

        person1.save("Menna");
        person2.save("Mohamed");

        //Spring مسؤول عن إنشاء prototype instances، لكن مسؤولية destruction بتقع على التطبيق
        System.out.println(person1 == person2);
    }
}
//person1 → Object 1
//person2 → Object 2
////p1 ≠ p2(false)