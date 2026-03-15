package org.example.oop.task5.hithub.twelve;

public class FacebookPost {
    private int id;
    private String text;
    private String image;
    //constructor without img
    public FacebookPost(int id, String text) {
        this.id = id;
        this.text = text;
    }
    //constructor with img
    public FacebookPost(int id, String text, String image) {
        this.id = id;
        this.text = text;
        this.image = image;
    }

    public void showPost() {
        System.out.println("Facebook Post");
        System.out.println("Id: " + id);
        System.out.println("text: " + text);

        if (image != null) {
            System.out.println("image:" + image);
        }
    }
}
