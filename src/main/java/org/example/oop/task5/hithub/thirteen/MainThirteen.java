package org.example.oop.task5.hithub.thirteen;

public class MainThirteen {
    public static void main(String[] args){
        Shirt s1 = new Shirt(10,"red");
        Player p1= new Player(1,"mSalah",s1);

        System.out.println(p1.name);
        System.out.println(p1.shirt.shirtNumber);
    }
}
