package org.example.oop.task2.problem7;

class ClupRelPlayer extends Players {

    private String Rcode;

    public void setRcode(String Rcode) {
        this.Rcode = Rcode;
    }

    public void printFullData() {
        printBasicData();
        System.out.println("rcodee/"+ Rcode);
    }
}