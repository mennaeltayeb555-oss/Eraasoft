package org.example.oop.task4.z;

public class ThreeNumbers {

    int k;
    int s;

    ThreeNumbers(int k, int s) {
        this.k = k;
        this.s = s;
    }

    public int countWays() {
        int count = 0;

        for (int x = 0; x <= k; x++) {
            for (int y = 0; y <= k; y++) {
                int z = s - x - y;

                if (z >= 0 && z <= k) {
                    count++;
                }
            }
        }

        return count;
    }
}