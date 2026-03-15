package org.example.oop.task4.i;

public class PalindromeChecker {
    int n;

    PalindromeChecker(int n){
        this.n = n;
    }

    public void checkPalindrome(){

        int original = n;
        int reversed = 0;
//dh by3ks el (n)

        while(n > 0){
            int digit = n % 10;
            reversed = reversed * 10+ digit;
            n = n / 10;

        }
        System.out.println(reversed);

        if(original == reversed){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}
