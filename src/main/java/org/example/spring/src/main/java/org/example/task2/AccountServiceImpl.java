package org.example.task2;

public class AccountServiceImpl implements AccountService{
    private PersonService personService;

    public AccountServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void getSavePerson(String name) {
        personService.save(name);
    }
}
