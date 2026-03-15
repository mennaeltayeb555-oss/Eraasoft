package org.example.oop.task5.hithub.nine;

import org.example.oop.task2.problem2.problemtwo1.Player;

public class PublicClub extends Club {
    public PublicClub(String clubName) {
        super(clubName);
    }

    @Override
    public void savePlayer(Player p) {
        players.add(p);
        System.out.println("Player added successfully in Public Club");
    }
}
