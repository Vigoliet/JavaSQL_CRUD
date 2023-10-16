package org.example;

public class Main {
    public static void main(String[] args) {

        var myVolvo = new Car("Volvo", "V40", 2023);

        System.out.println(myVolvo);

        // creates a new db if it does not exist
        var db = new Database();

        // reuslt if the method to save the new car object worked or not
        var res = db.saveCar(myVolvo);

        if (res){
            System.out.println("Car saved");
        } else {
            System.out.println("Car not saved");
        }


    }


}