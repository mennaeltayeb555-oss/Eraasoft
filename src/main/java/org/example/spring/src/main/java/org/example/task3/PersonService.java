package org.example.task3;

public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }

    public void init() {
        System.out.println("PersonService initialized");
    }

    public void destroy() {
        System.out.println("PersonService destroyed");
    }

}
