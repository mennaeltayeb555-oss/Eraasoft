package org.example.oop.task4.o;

public class Pyramid {
    int n;

    public Pyramid(int n) {
        this.n = n;
    }
    public void printStars(){
        for(int i = 1; i<=n; i++){
            for(int j =1;j<=i;  j++){
                System.out.print(" * ");// no (ln)
                                        // 3lshan maynzlsh satr
            }
            System.out.println();
        }
    }
}
