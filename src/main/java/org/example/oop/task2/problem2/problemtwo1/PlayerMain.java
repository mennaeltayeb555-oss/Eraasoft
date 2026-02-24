package org.example.oop.task2.problem2.problemtwo1;

import java.util.Scanner;

public class PlayerMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player player = new Player();
        /////
        player.setName(sc.next());
        player.setNumber(sc.nextInt());
        ////
        player.getName();
        player.getNumber();
    }
}
