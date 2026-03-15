package org.example.oop.task5.hithub.twelve;

public class MainFacebook {
    public static void main(String[] args) {

        FacebookPost post1 = new FacebookPost(1, "Hello Facebook");
        FacebookPost post2 = new FacebookPost(2, "My new photo", "photo.jpg");

        post1.showPost();
        System.out.println("--------------");
        post2.showPost();
    }
}
