package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.plaf.synth.Region;

import com.napier.sem.classes.*;

import sql.queries.*;

public class db {
    private Connection con = null;

    //try connect to db
    public void connect(String dbURL) {
        // Default timeout value if not passed from main()
        int timeout = 10000;  // 10 seconds timeout for connection
    
        try {
            // Code to connect to the database, using the dbURL
            String url = "jdbc:mysql://" + dbURL;  // Format the URL properly
            con = DriverManager.getConnection(url, "root", "group18");
            System.out.println("Connected to database successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
        }
    }
    
    // Disconnect from the database
    public void disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Methods for creating Country Reports

    // Method to print list of countries
    public static void printCountries(ArrayList<Country> countries) {
        for (Country country : countries) {
            System.out.println("Country: " + country.getName() + ", Population: " + country.getPopulation());
        }
    }

    // Method to print list of cities
    public static void printCities(ArrayList<City> cities) {
        for (City city : cities) {
            System.out.println("City: " + City.getName() + ", Population: " + City.getPopulation());
        }
    }

    //print lang stats
    public static void printLanguage(ArrayList<Language> languageReport) {
        for (Language language : languageReport) {
            // Update the method to use the correct getter methods
            System.out.println("Language: " + language.getLanguage() + ", Speakers: " + language.getPercentage());
        }
    }

    // 1. Retrieve a list of countries sorted by population (Descending)
    public ArrayList<Country> getAllCountriesSortedByPopulation(String baseQuery, int n) {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = baseQuery + " ORDER BY Population DESC";

            //if N <= 0 then set no limit
            if(n>0) {
                strSelect = strSelect + " LIMIT " + n;
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            //ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation(rset.getInt("Population"));
                country.setCapital(rset.getString("Capital"));
                countries.add(country);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    // 2. Retrieve a list of cities sorted by population (Descending)
    public ArrayList<City> getAllCitiesSortedByPopulation(String baseQuery, int n) {
        ArrayList<City> cities = new ArrayList<>();
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = baseQuery + " ORDER BY Population DESC";

            //if N <= 0 then set no limit
            if(n>0) {
                strSelect = strSelect + " LIMIT " + n;
            }
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            //ArrayList<Country> country = new ArrayList<Country>();
            while (rset.next()) {
                City City = new City();
                City.setId(rset.getInt("Code"));
                City.setName(rset.getString("Name"));
                City.setCountryCode(rset.getString("Continent"));
                City.setDistrict(rset.getString("Region"));
                City.setPopulation(rset.getInt("Population"));
                cities.add(City);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
//capital cities query area

public ArrayList<City> getTopNPopulatedCapitalCities(String baseQuery, int n) {
    ArrayList<City> cities = new ArrayList<>();
    try {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Build the query dynamically
        String strSelect = baseQuery + 
                           cityQuery.capitalCity() + " " + //only select capital cities
                           "ORDER BY city.Population DESC";
        
        // Apply LIMIT if N > 0
        if (n > 0) {
            strSelect += " LIMIT " + n;
        }

        // Execute the SQL statement
        ResultSet rset = stmt.executeQuery(strSelect);

        // Extract city information from the result set
        while (rset.next()) {
            City city = new City();
            city.setId(rset.getInt("ID"));
            city.setName(rset.getString("Name"));
            city.setCountryCode(rset.getString("CountryCode"));
            city.setDistrict(rset.getString("District"));
            city.setPopulation(rset.getInt("Population"));
            cities.add(city);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return cities;
}


    //population of each continent
    public HashMap<String, Long> getPopulationByContinent() {
        HashMap<String, Long> populationByContinent = new HashMap<>();
        String query = "SELECT Continent, SUM(Population) AS total_population FROM country GROUP BY Continent";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String continent = rs.getString("Continent");
                long population = rs.getLong("total_population");
                populationByContinent.put(continent, population);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return populationByContinent;
    }

    //Retrieve official languages of a country
    public ArrayList<Language> getOfficialLanguagesForCountry(String query) {
        ArrayList<Language> languages = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Language language = new Language(
                        rs.getString("Language"), 
                        rs.getDouble("Speakers")
                );
                languages.add(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return languages;
    }
}