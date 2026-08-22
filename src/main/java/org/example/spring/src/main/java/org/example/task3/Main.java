package org.example.task3;

import org.example.task1.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // create the container (context) using AppConfig class
        // this reads @Component and @Scope and prepares the beans
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // get first object from the bean (PersonService) which has scope = prototype
        UserService p1 = context.getBean(UserService.class);
        p1.save("menna3");

        // prototype = new instance each time, init() runs again
        // every time we ask for the bean, Spring creates a new object (not the same as before)
        // so init() will run again with this new object
        UserService p2 = context.getBean(UserService.class);
        p2.save("mohamed3");

        // NOTE: destroy() will NOT be called automatically for prototype beans
        // Spring does not manage the full lifecycle of prototype beans
        // so if we want destroy() to run, we must call it manually
        // context.close() only closes singleton beans, not prototype beans

        context.close();
    }
}

//person1 → Object 1
//person2 → Object 2
////p1 ≠ p2 (false)
// this means the two objects are completely different, proving the scope is really prototype