package org.example.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements UserService, AccountService {

    private final PersonService personService;

    @Autowired
    public AccountServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void save(String name) {
        personService.save(name);
    }

    @Override
    public void getSavePerson(String name) {
        System.out.println("AccountService: delegating save for " + name);
        personService.save(name);
    }
}
