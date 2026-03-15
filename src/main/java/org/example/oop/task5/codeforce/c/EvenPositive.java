package org.example.oop.task5.codeforce.c;

public class EvenPositive {
    int[] arr;

    public EvenPositive(int[] arr) {
        this.arr = arr;
    }

    public void countNumbers() {

        int even = 0;
        int odd = 0;
        int positive = 0;
        int negative = 0;

        for (int num : arr) {

            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }

            if (num > 0) {
                positive++;
            }

            if (num < 0) {
                negative++;
            }
        }

        System.out.println("Even: " + even);
        System.out.println("Odd: " + odd);
        System.out.println("Positive: " + positive);
        System.out.println("Negative: " + negative);
    }
}
