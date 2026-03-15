package org.example.oop.task5.hithub.nine;

import org.example.oop.task2.problem2.problemtwo1.Player;

import java.util.ArrayList;

public abstract class Club {
    protected String clubName;
    protected ArrayList<Player> players = new ArrayList<>();

    public Club(String clubName) {
        this.clubName = clubName;
    }

    public abstract void savePlayer(Player p);

    public void showPlayers() {
        System.out.println("Players in " + clubName);
        for (Player p : players) {
            System.out.println(p.getName() + " - " + p.getName());
        }
    }

}
