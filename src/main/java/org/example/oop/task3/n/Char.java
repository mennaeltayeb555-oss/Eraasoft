package org.example.oop.task3.n;

public class Char {
    private char letter;   // attribute

    // Constructor
    public Char(char letter) {
        this.letter = letter;
    }

    // Method to convert case
    public char convert() {
        if (letter >= 'a' && letter <= 'z') {
            return (char)(letter - 32);  // convert to uppercase
        } else {
            return (char)(letter + 32);  // convert to lowercase
        }
    }
}
