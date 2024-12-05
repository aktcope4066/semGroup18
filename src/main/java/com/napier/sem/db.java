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
    public ArrayList<City> getAllCitiesSortedByPopulation() {
        ArrayList<City> cities = new ArrayList<>();
        String query = "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population " +
                       "FROM city JOIN country ON city.CountryCode = country.Code ORDER BY city.Population DESC";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                City city = new City();
                city.setName(rs.getString("city_name"));
                city.setCountryCode(rs.getString("country_name"));
                city.setDistrict(rs.getString("District"));
                city.setPopulation(rs.getInt("Population"));
                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    // 3. Retrieve population of each continent
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

    // 4. Retrieve a list of countries with a population greater than a specified value
    public ArrayList<Country> getCountriesWithPopulationGreaterThan(int minPopulation) {
        ArrayList<Country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Continent, Region, Population FROM country WHERE Population > ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, minPopulation);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Country country = new Country();
                country.setCode(rs.getString("Code"));
                country.setName(rs.getString("Name"));
                country.setContinent(rs.getString("Continent"));
                country.setRegion(rs.getString("Region"));
                country.setPopulation(rs.getInt("Population"));
                countries.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }


    // 5. Insert a new country
    public boolean insertCountry(Country country) {
        String query = "INSERT INTO country (Code, Name, Continent, Region, SurfaceArea, Population, LifeExpectancy, GNP, GNPOld, LocalName, GovernmentForm, HeadOfState, Capital, Code2) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, country.getCode());
            stmt.setString(2, country.getName());
            stmt.setString(3, country.getContinent());
            stmt.setString(4, country.getRegion());
            stmt.setDouble(5, country.getSurfaceArea());
            stmt.setInt(6, country.getPopulation());
            stmt.setDouble(7, country.getLifeExpectancy());
            stmt.setDouble(8, country.getGnp());
            stmt.setDouble(9, country.getGnpOld());
            stmt.setString(10, country.getLocalName());
            stmt.setString(11, country.getGovernmentForm());
            stmt.setString(12, country.getHeadOfState());
            stmt.setString(13, country.getCapital());
            stmt.setString(14, country.getCode2());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 6. Update an existing country
    public boolean updateCountry(Country country) {
        String query = "UPDATE country SET Name = ?, Continent = ?, Region = ?, SurfaceArea = ?, Population = ?, LifeExpectancy = ?, GNP = ?, GNPOld = ?, LocalName = ?, GovernmentForm = ?, HeadOfState = ?, Capital = ?, Code2 = ? " +
                       "WHERE Code = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, country.getName());
            stmt.setString(2, country.getContinent());
            stmt.setString(3, country.getRegion());
            stmt.setDouble(4, country.getSurfaceArea());
            stmt.setInt(5, country.getPopulation());
            stmt.setDouble(6, country.getLifeExpectancy());
            stmt.setDouble(7, country.getGnp());
            stmt.setDouble(8, country.getGnpOld());
            stmt.setString(9, country.getLocalName());
            stmt.setString(10, country.getGovernmentForm());
            stmt.setString(11, country.getHeadOfState());
            stmt.setString(12, country.getCapital());
            stmt.setString(13, country.getCode2());
            stmt.setString(14, country.getCode());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 7. Delete a country
    public boolean deleteCountry(String countryCode) {
        String query = "DELETE FROM country WHERE Code = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, countryCode);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 8. Insert a new city
    public boolean insertCity(City city) {
        String query = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountryCode());
            stmt.setString(3, city.getDistrict());
            stmt.setInt(4, city.getPopulation());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 9. Retrieve official languages of a country
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