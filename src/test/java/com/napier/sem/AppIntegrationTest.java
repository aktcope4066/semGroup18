package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import com.napier.sem.classes.Country;

public class AppIntegrationTest {
    static App app;

    @BeforeAll
    static void init() {
        try {
            app = new App();
            app.connect("localhost:33060", 30000);
        } catch (Exception e) {
            fail("Initialization failed: " + e.getMessage());
        }
    }

    @Test
    void testDatabaseConnection() {
        try {
            assertNotNull(app, "App instance should not be null");
            app.connect("localhost:33060", 30000);
        } catch (Exception e) {
            fail("Connecting to the database threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testRetrieveCountries() {
        try {
            db database = new db();
            database.connect();

            ArrayList<Country> countries = new ArrayList<>();
            countries.add(new Country("USA", "United States", "North America", "Northern America", 331002651, "Washington D.C."));
            countries.add(new Country("GBR", "United Kingdom", "Europe", "Northern Europe", 67886011, "London"));

            assertNotNull(countries, "Country list should not be null");
            assertEquals(2, countries.size(), "Country list size should be 2");
        } catch (Exception e) {
            fail("Retrieving countries threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testPrintCountries() {
        try {
            db database = new db();

            // Create a sample list of countries
            ArrayList<Country> countries = new ArrayList<>();
            countries.add(new Country("FRA", "France", "Europe", "Western Europe", 65273511, "Paris"));
            countries.add(new Country("IND", "India", "Asia", "Southern Asia", 1380004385, "New Delhi"));

            // Attempt to print countries
            database.printCountries(countries);
        } catch (Exception e) {
            fail("Printing countries threw an exception: " + e.getMessage());
        }
    }

    @Test
    void testDisconnect() {
        try {
            app.disconnect();
        } catch (Exception e) {
            fail("Disconnecting from the database threw an exception: " + e.getMessage());
        }
    }
}