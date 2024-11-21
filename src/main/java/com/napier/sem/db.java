package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import com.napier.sem.classes.Country;

public class db {

    public static void printCountries() {
        ArrayList<Country> countries = new ArrayList<Country>();
        System.out.println("print country place holder");
    }

    private Connection con = null;

    //try connect to db
    public void connect(){
        try{
            //load db driver
        } catch (Exception e){
            //error msg and exit
        }

        //num of retries
        int retry = 2;
        //
        for(int i=0; i<retry; ++i){
            //try con to db
            try {
                //connect to db
            } catch (Exception e) {
                //eroor msg and exit
            }
        }
    }
    // Print a formatted list of countries
    public void printCountries(ArrayList<Country> countries) {
        if (countries == null) {
            System.out.println("No countries to display (list is null).");
            return;
        }

        System.out.printf("%-10s %-20s %-15s %-20s %-10s %-15s%n", "Code", "Name", "Continent", "Region", "Population", "Capital");

        for (Country country : countries) {
            if (country == null) continue;
            System.out.printf("%-10s %-20s %-15s %-20s %-10d %-15s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    //imp close db con
}
