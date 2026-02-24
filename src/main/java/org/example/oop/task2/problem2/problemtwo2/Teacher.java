package org.example.oop.task2.problem2.problemtwo2;

public class Teacher {
    private Long id; // id > 0
    private String name; // size >=3 and all digits char(a-z)
    private float age; // age >= 25 and age <= 60
    private String phoneNumber; //  +20111390660 13 cher and start with +20
    private float salary;  // salary >= 3000

    public void setId(Long id) {
        if (id < 0) {
            System.out.println("invalid id");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (isValidName(name)) {
            this.name = name;
        } else {
            System.out.println("invalid name");
        }
        this.name = name;
    }

    public void setAge(float age) {
        if (age >= 25 && age <= 60) {
            this.age = age;
        } else {
            System.out.println("(invalid age) Age must be between 25 and 60");
        }
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber.length()!=13 ){
            System.out.println("sorry check the phone again! it must be 13 digits.");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setSalary(float salary) {
        if(salary<3000){
            System.out.println("invalid salary it must (3000) at least");
        }
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public float getSalary() {
        return salary;
    }

    private boolean isValidName(String name) {

        if (name == null) return false;

        if (name.length() < 3) return false;

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);

            if (ch < 'a' || ch > 'z') {
                return false;
            }
        }

        return true;
    }


}



