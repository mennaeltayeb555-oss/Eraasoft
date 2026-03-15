package org.example.oop.task2.problem2.problemtwo1;
public class Player {
     private String name;
    private int number;

    //setter and getter:
    public void setName(String name){
        if(name.length()>5){
            this.name=name;
        }else{
            System.out.println("please make sure that name's chars > 5");
        }

    }
    public void setNumber(int number){
        if(number>0){
            this.number=number;
        }else {
            System.out.println("invalid number , try again");
        }
    }
    public String getName(){
        return name;
    }

    public int getNumber() {
        return number;
    }
}
