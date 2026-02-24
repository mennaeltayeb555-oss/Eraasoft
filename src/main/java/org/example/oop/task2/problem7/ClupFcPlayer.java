package org.example.oop.task2.problem7;

class ClupFcPlayer extends Players {

    private String Fcode;

    public void setFcode(String Fcode) {
        this.Fcode = Fcode;
    }

    public void printFullData() {
        printBasicData();
        System.out.println("Fcode: " + Fcode);
    }
}