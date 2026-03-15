package org.example.oop.task5.codeforce.u;

public class SomeSums {
    int n, a, b;

    public SomeSums(int n, int a, int b) {
        this.n = n;
        this.a = a;
        this.b = b;
    }

    public int digitSum(int num) {

        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int calculate() {

        int total = 0;

        for (int i = 1; i <= n; i++) {

            int s = digitSum(i);

            if (s >= a && s <= b) {
                total += i;
            }

        }

        return total;
    }
}
