package org.example.oop.task5.codeforce.V;

public class Pum {
    int n;

    public Pum(int n) {
        this.n = n;
    }

    public void printGame() {

        int num = 1;

        for (int i = 0; i < n; i++) {

            System.out.println(num + " " + (num + 1) + " " + (num + 2) + " PUM");

            num += 4;
        }
    }
}
