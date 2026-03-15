package org.example.oop.task5.hithub.seventeen.package2;

public class MainPackageTwo {
    public static void main(String[] args) {
        Teacher t = new Teacher();

        t.id = 10;
        t.name = "moustafa";
        t.active = true;

        System.out.println(t.id);
        System.out.println(t.name);
        System.out.println(t.active);
    }
}
