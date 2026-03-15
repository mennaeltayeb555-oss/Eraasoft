package org.example.oop.task5.hithub.nine;

import org.example.oop.task2.problem2.problemtwo1.Player;

public class NineMain {
    public static void main(String[] args) {

        Player p1 = new Player();
        Player p2 = new Player();

        Club c1 = new PublicClub("Public Club");
        Club c2 = new PrivateClub("Private Club");

        c1.savePlayer(p1);
        c1.savePlayer(p2);

        c2.savePlayer(p1);
        c2.savePlayer(p2);

        c1.showPlayers();
        c2.showPlayers();
    }
}
