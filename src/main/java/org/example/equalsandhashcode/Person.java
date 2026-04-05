package org.example.equalsandhashcode;

public class Person {
        int id;
        String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            return this.id == p.id;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    }

