package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.napier.sem.classes.*;

public class App {

    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 10000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

//        // Allow the user to input a custom value for 'n'
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the number of countries to retrieve(0 for all): ");
//
//        if (scanner.hasNextInt()) {
//            n = scanner.nextInt();
//        } else {
//            System.out.println("Invalid input using default value of " + n);
//        }

//        // Get top n populated countries in the world
//        System.out.println("___________________________________________________________________________");
//        System.out.println("The top n populated countries in the world where n is provided by the user.");
//        System.out.println("___________________________________________________________________________");
//        ArrayList<Country> countries = App.getAllCountriesSortedByPopulation(countryQuery.baseQuery, n);
//        App.printCountries(countries);
//
//        // Get top n populated cap cities in the world
//        System.out.println("___________________________________________________________________________");
//        System.out.println("The top n populated countries in the world where n is provided by the user.");
//        System.out.println("___________________________________________________________________________");
//        ArrayList<Country> cities = App.getTopNPopulatedCapitalCities(cityQuery.baseQuery, n);
//        App.printCountries(cities);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "group18");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());

                // Let's wait before attempting to reconnect
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
