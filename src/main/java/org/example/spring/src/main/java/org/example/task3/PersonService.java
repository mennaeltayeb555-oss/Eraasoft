package org.example.task3;



import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("PersonService: saved " + name);
    }

    @PostConstruct
    public void init() {
        System.out.println("PersonService: init() called - bean created");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PersonService: destroy() called - bean destroyed");
    }
}
