package org.example.task2;

public class PersonService implements UserService {

    @Override
    public void save(String name) {
        System.out.println("Person saved: " + name);
    }
}
