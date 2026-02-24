package org.example.oop.task2.Problem3;

public class SumEven {
    private int n1;
    private int n2;
    private int n3;

    public void setN1(int n1) {
        if (n1%2 == 0) {
            this.n1 = n1;
        } else {
            System.out.println("the number must be even");
        }
    }

    public void setN2(int n2) {
        if (n2%2 == 0) {
            this.n2 = n2;
        } else {
            System.out.println("the number must be even");
        }
    }

    public void setN3(int n3) {
        if (n3 %2== 0) {
            this.n3 = n3;
        } else {
            System.out.println("the number must be even");
        }
    }

    public void printSum() {
        System.out.println("Sum = " + (n1+n2+n3));
    }
}
