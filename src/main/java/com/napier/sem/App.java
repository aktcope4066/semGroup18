package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.napier.sem.classes.*;
import sql.queries.*;
import com.napier.sem.db;

//------------

public class App
{
    public static void main(String[] args) {
        db db = new db();
        int n = 0;  // The number of countries or languages to retrieve 0 is all

        // Connect to the database
        if (args.length < 1) {
            // Default connection string and port, no timeout argument
            db.connect("localhost:33060");  // Use default DB location (host:port)
        } else {
            // Use the arguments for connecting to the DB
            db.connect(args[0]);  // The first argument should be the URL (host:port)
        }

        // Allow the user to input a custom value for 'n'
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of countries to retrieve(0 for all): ");
    
        if (scanner.hasNextInt()) {
            n = scanner.nextInt();
        } else {
            System.out.println("Invalid input using default value of " + n);
        }

        // Get top n populated countries in the world
        System.out.println("___________________________________________________________________________");
        System.out.println("The top n populated countries in the world where n is provided by the user.");
        System.out.println("___________________________________________________________________________");
        ArrayList<Country> countries = db.getAllCountriesSortedByPopulation(countryQuery.baseQuery, n);
        db.printCountries(countries);

        // Get top n populated countries in a specific region (e.g., North America)
        System.out.println("__________________________________________________________________________");
        System.out.println("The top n populated countries in a region where n is provided by the user.");
        System.out.println("__________________________________________________________________________");
        ArrayList<Country> countries2 = db.getCountriesWithPopulationGreaterThan(50000000);  // Example logic, update for region if needed
        db.printCountries(countries2);

        // Get top n populated countries in a specific continent (e.g., Asia)
        System.out.println("_____________________________________________________________________________");
        System.out.println("The top n populated countries in a continent where n is provided by the user.");
        System.out.println("_____________________________________________________________________________");
        ArrayList<Country> countries3 = db.getCountriesWithPopulationGreaterThan(100000000);  // Example logic, update for continent if needed
        db.printCountries(countries3);

        // Get the number of people who speak specific languages
        System.out.println("______________________________________________________________________________________________________________");
        System.out.println("The number of people who speak the following languages: Chinese, English, Hindi, Spanish, Arabic");
        System.out.println("______________________________________________________________________________________________________________");
        ArrayList<Language> languageReport = db.getOfficialLanguagesForCountry(languageQuery.LanguageQuery);  // Use the query defined in languageQuery class
        db.printLanguage(languageReport);

        // Disconnect from the database
        db.disconnect();
    }
}