package org.example.oop.task5.codeforce.s;

public class OddSum {
    public int calculateSum(int x, int y) {

        int start = Math.min(x, y) + 1;
        int end = Math.max(x, y) - 1;
        int sum = 0;

        for (int i = start; i <= end; i++) {

            if (i % 2 != 0) {
                sum += i;
            }

        }
        return sum;
    }
}
