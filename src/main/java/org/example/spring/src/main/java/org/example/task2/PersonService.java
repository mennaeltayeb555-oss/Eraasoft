package org.example.task2;

import org.springframework.stereotype.Component;

@Component
public class PersonService implements UserService {
    @Override
    public void save(String name) {
        System.out.println("PersonService: saved " + name);
    }
}
