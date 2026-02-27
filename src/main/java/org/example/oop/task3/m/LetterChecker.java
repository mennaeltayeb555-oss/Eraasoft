package org.example.oop.task3.m;

public class LetterChecker {
    private char x;

    public LetterChecker(char x) {
        this.x = x;
    }

    public void check() {

        if (x>='0'&& x<='9') {
            System.out.println("IS DIGIT");
        } else {
            System.out.println("ALPHA");

            if (x >='A'&& x <= 'Z') {
                System.out.println("IS CAPITAL");
            } else {
                System.out.println("IS SMALL");
            }
        }
    }
}
