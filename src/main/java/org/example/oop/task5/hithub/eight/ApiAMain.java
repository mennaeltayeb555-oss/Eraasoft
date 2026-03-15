package org.example.oop.task5.hithub.eight;

public class ApiAMain {
    public static void main(String[] args) {
        Api f = new FaceBook();
        Api l = new LinkedIn();

        f.showMessage();
        l.showMessage();

    }
}


