package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.napier.sem.classes.Country;

public class App {
    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();

        if (args.length == 2) {
            app.connect(args[0], Integer.parseInt(args[1]));
        } else {
            app.connect("localhost:33060", 10000);
        }

        // Test print countries
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("ABC", "SampleCountry", "Asia", "Southeast", 123456, "SampleCapital"));
        for (Country c : countries) {
            System.out.println(c.toString());
        }

        // Disconnect from database
        app.disconnect();
    }

    public void connect(String location, int delay) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 5;
        boolean shouldWait = false;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                if (shouldWait) {
                    Thread.sleep(delay);
                }
                con = DriverManager.getConnection(
                    "jdbc:mysql://" + location + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                    "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
                shouldWait = true;
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Disconnected from database");
            }
        } catch (SQLException e) {
            System.out.println("Failed to disconnect from database");
            System.out.println(e.getMessage());
        }
    }
}