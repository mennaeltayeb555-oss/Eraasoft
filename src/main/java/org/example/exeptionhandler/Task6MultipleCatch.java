package org.example.exeptionhandler;

public class Task6MultipleCatch {
    //String = null → لو استخدمناها يحصل NullPointerException
    //قسمة على صفر → يحصل ArithmeticException
    public static void main(String[] args) {

        try {
            String text = null;
            System.out.println(text.length());

            int result = 10 / 0;
            System.out.println(result);

        } catch (NullPointerException e) {
            System.out.println("Error: string is null.");
        } catch (ArithmeticException e) {
            System.out.println("Error: cannot divide by zero.");
        }
    }
    //public static void main(String[] args) {
    //
    //        try {
    //            String text = "Java";
    //            System.out.println(text.length());
    //
    //            int result = 10 / 0;
    //            System.out.println(result);
    //
    //        } catch (NullPointerException e) {
    //            System.out.println("Error: string is null.");
    //        } catch (ArithmeticException e) {
    //            System.out.println("Error: cannot divide by zero.");
    //        }
    //    }
}

