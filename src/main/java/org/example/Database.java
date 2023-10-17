package org.example;

import java.sql.*;

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
    public static void DeleteCar(String make){
        String sql = "DELETE FROM cars WHERE make = ?";
        try {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,make);
            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void UpdateCar(String make, String newMake){

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");

            String sql = "UPDATE cars SET make = ? WHERE make = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, newMake);
            pstmt.setString(2, make);
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
}
