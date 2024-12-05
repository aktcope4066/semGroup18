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
    public static void main(String[] args)
    {
        db db = new db();
        //no of couuntries or whatever
        int n = 5;

        //connect
        if (args.length < 1) {
            db.connect("localhost:33060", 10000);
        } else {
            db.connect(args[0], Integer.parseInt(args[1]));
        }

        //probs output below for the reports
        System.out.println("___________________________________________________________________________");
        System.out.println("The top n populated countries in the world where n is provided by the user.");
        System.out.println("___________________________________________________________________________");
        // Extract country information
        ArrayList<Country> countries = db.getCountryWorld(countryQuery.countryQuery, n);
        // out results
        db.printCountries(countries);

        System.out.println("__________________________________________________________________________");
        System.out.println("The top n populated countries in a region where n is provided by the user.");
        System.out.println("__________________________________________________________________________");
        //Extract country information
        ArrayList<Country> countries2 = db.getCountryRegion(countryQuery.countryQuery,n,"North America");
        // out results
        db.printCountries(countries2);

        System.out.println("_____________________________________________________________________________");
        System.out.println("The top n populated countries in a continent where n is provided by the user.");
        System.out.println("_____________________________________________________________________________");
        //Extract country information
        ArrayList<Country> countries3 = db.getCountryContinent(countryQuery.countryQuery,n,"Asia");
        // Display results
        db.printCountries(countries3);

        System.out.println("______________________________________________________________________________________________________________");
        System.out.println("The number of people who speak the following the following languages: Chinese, English, Hindi, Spanish, Arabic");
        System.out.println("______________________________________________________________________________________________________________");
        //Extract country information
        ArrayList<Language> languageReport = db.getLanguages(LanguageQueries.query);
        // Display results
        db.printLanguage(languageReport);

        //dc
        db.disconnect();
    }

}