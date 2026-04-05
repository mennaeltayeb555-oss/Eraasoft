package org.example.equalsandhashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Person p1 = new Person(2, "saif");
        //Person p2 = new Person(2, "saif");


        //product before overrideing:

        /*System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));*/

        //product after overrideing:
        //System.out.println(p1.equals(p2));
        // after useing hashset:
        /*Set<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);

        System.out.println(set.size());*/

        //level 2 :
        /*Set<Person> set = new HashSet<>();

        set.add(new Person(1, "Ali"));
        set.add(new Person(2, "Mona"));
        set.add(new Person(1, "Ahmed"));
        set.add(new Person(3, "Sara"));
        set.add(new Person(2, "Hana"));

        System.out.println(set.size());
    }*/

        /*Map<Person, String> map = new HashMap<>();

        Person p1 = new Person(1, "Ali");
        Person p2 = new Person(1, "Ahmed");

        map.put(p1, "Employee");
        map.put(p2, "Manager");

        System.out.println(map.size());
        System.out.println(map);

        System.out.println(map.get(p1));
        System.out.println(map.get(p2));
        //bug creation
        p1.id = 5;

        System.out.println(map.get(p1));*/
        //output is null.

      //  <<<<<<<<product main>>>>>>>>>
        /*Set<Product> products = new HashSet<>();

        products.add(new Product("A1", 100));
        products.add(new Product("A1", 200));

        System.out.println(products.size());*/
        //  <<<<<<<<student main>>>>>>>>>
        Set<Student> set = new HashSet<>();

        set.add(new Student(1, "a@gmail.com"));
        set.add(new Student(2, "a@gmail.com"));

        System.out.println(set.size());



        //  <<<<<<<<car main>>>>>>>>>
        Map<Car, String> map = new HashMap<>();

        Car c1 = new Car("123", "Red");
        Car c2 = new Car("123", "Blue");

        map.put(c1, "First");
        map.put(c2, "Second");

        System.out.println(map.size());
        System.out.println(map.get(c1));




    }
}


