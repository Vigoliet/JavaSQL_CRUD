package org.example;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static Connection conn;

    public Database() {

        try {
            // Create a new database
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            Statement stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INTEGER PRIMARY KEY," +
                    "make TEXT NOT NULL," +
                    "model TEXT NOT NULL, " +
                    "year INTEGER NOT NULL)";

            stmt.execute(sql);

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean saveCar(Car car){
        String sql = "INSERT INTO cars(make, model, year) VALUES (?, ?, ?)";

        boolean result = false;
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());

            result = stmt.executeUpdate() > 0;

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    // Delete car by car name
    public static void DeleteCar(int id){
        String sql = "DELETE FROM cars WHERE id = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void UpdateCar(int id, String newMake, String newModel, int newYear){

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            String sql = "UPDATE cars SET make = ?, model = ?, year =? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newMake);
            pstmt.setString(2, newModel);
            pstmt.setInt(3, newYear);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void FetchCars(){

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement stmt = conn.createStatement();
            String sql = "SELECT id,make,model,year FROM cars";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");

                System.out.println("Id:" + id + " Make:" + make + " Model:" + model + " Year:" + year);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Car> getCars(){
        String sql = "SELECT * FROM cars"; // * Gets all columns

        ArrayList<Car> cars = null; // Create list outside of try catch so we can return it

        try {
            // Prepare and execute our database query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            cars = new ArrayList<>(); // Set cars to an empty list

            while(rs.next()){ // For every row (car)

                // Get all properties
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                int year = rs.getInt("year");

                Car car = new Car(id, make, model, year); // Create new car object

                cars.add(car); // Add our car object in the list
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars; // Returns all cars (if it worked) or null (if not)
    }


}
