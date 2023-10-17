package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
   // switch for interface
    public static void menu(){
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
                    // Update a car make by id
                    case 2:
                        System.out.println("ID: ");
                        int idToChange = scanner.nextInt();
                        System.out.println("New make: ");
                        String newMake = scanner.next();

                        Database.UpdateCar(idToChange, newMake);
                        break;

                    // Delete a car by its id
                    case 3:
                        System.out.println("Id: ");
                        int intToDelete = scanner.nextInt();
                        Database.DeleteCar(intToDelete);
                        break;

                    // Print all information from db
                    case 4:
                        Database.FetchCars();
                        break;

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
    }
}
