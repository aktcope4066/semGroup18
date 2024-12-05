package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.napier.sem.classes.*;
import sql.queries.*;
import com.napier.sem.db;

//------------

public class App
{
    public static void main(String[] args) {
        db db = new db();
        int n = 5;  // The number of countries or languages to retrieve

        // Connect to the database
        if (args.length < 1) {
            db.connect("localhost:33060", 10000);  // default DB location
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));  // if arguments provided
        }

        // Get top n populated countries in the world
        System.out.println("___________________________________________________________________________");
        System.out.println("The top n populated countries in the world where n is provided by the user.");
        System.out.println("___________________________________________________________________________");
        ArrayList<Country> countries = db.getAllCountriesSortedByPopulation(n);
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