package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean loop = true;
        while(loop) {
            try {
                System.out.println("Choose an option: \n" +
                        "(1) Add a new car\n" +
                        "(2) Update a car\n" +
                        "(3) Delete a car\n" +
                        "(4) Print all cars\n" +
                        "(5) Exit program\n");
                Scanner scanner = new Scanner(System.in);
                int inputOption = scanner.nextInt();

                switch (inputOption){
                    // Add a new car
                    case 1:
                        System.out.println("Make: ");
                        String make = scanner.next();

                        System.out.println("Model: ");
                        String model = scanner.next();

                        System.out.println("Year: ");
                        int year = scanner.nextInt();

                        var db = new Database();
                        var newCarToAdd = new Car(make, model, year);
                        db.saveCar(newCarToAdd);

                        break;
                    // Update a car
                    case 2:
                        System.out.println("Make: ");
                        String makeToChange = scanner.next();
                        System.out.println("New make: ");
                        String newMake = scanner.next();

                        Database.UpdateCar(makeToChange, newMake);
                        break;

                    // Delete a car by its make
                    case 3:
                        System.out.println("Make: ");
                        String makeToDelete = scanner.next();
                        Database.DeleteCar(makeToDelete);
                        break;

                    // Print all information
                    case 4:
                        Database.FetchCars();

                    case 5:
                        loop = false;
                        break;
                    default:
                        System.out.println("Write a correct number next time");
                        break;
                }
            } catch (InputMismatchException e){
                System.out.println("Write a number next time");
            }


        }

        /*
        // result if the method to save the new car object worked or not
        var res = db.saveCar(myVolvo);

        if (res){
            System.out.println("Car saved");
        } else {
            System.out.println("Car not saved");
        } */

        // Removes the car based on the make
        //Database.DeleteCar("Volvo");



    }


}