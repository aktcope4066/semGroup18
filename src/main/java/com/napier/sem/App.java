package com.napier.sem;

import java.sql.*;

public class App {

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee (From test DB) (remove later)
        // Employee emp = a.getEmployee(255530);
        // Display results (From test DB) (remove later)
        // a.displayEmployee(emp);

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
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "group18");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
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

    /**
     * Test to print countries
     */
    public Country getCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT *"
                            + "FROM country"
                            + "WHERE Code = 'ABW'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // (From test DB) (remove later)
            // Return new country if valid.
            // Check one is returned
            /*
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            }
            else
                return null;

             */
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }
        return null;
    }

    // public void displayCountry(Country name)
    // {
    //     if (emp != null)
    //     {
    //         System.out.println(
    //                 emp.emp_no + " "
    //                         + emp.first_name + " "
    //                         + emp.last_name + "\n"
    //                         + emp.title + "\n"
    //                         + "Salary:" + emp.salary + "\n"
    //                         + emp.dept_name + "\n"
    //                         + "Manager: " + emp.manager + "\n");
    //     }
    // }
    //

/* (From test DB) (remove later)
    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }
*/

}