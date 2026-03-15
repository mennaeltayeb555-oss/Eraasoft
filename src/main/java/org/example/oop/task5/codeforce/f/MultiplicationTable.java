package org.example.oop.task5.codeforce.f;

public class MultiplicationTable {
    int n;

    public MultiplicationTable(int n) {
        this.n = n;
    }

    public void printTable() {

        for (int i = 1; i <= 12; i++) {

            System.out.println(n + " * " + i + " = " + (n * i));

        }

    }
}
