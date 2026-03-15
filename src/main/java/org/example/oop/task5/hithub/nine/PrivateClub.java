package org.example.oop.task5.hithub.nine;

import org.example.oop.task2.problem2.problemtwo1.Player;

public class PrivateClub extends Club {
    public PrivateClub(String clubName) {
        super(clubName);
    }

    @Override
    public void savePlayer(Player p) {

        System.out.println("Player accepted in Private Club");

    }
}
