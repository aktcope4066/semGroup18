package com.napier.sem;

import com.napier.sem.classes.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {
    // Create a test database connection
    static db db;

    // Connect to test database
    @BeforeAll
    static void init() {
        db = new db();
        db.connect("localhost:33060", 30000);  // Test database location
    }

    // Disconnect from test database
    @AfterAll
    static void dc() {
        db.disconnect();
    }

    /**
     * Integration test for connecting to the database.
     */
    @Test
    void testDatabaseConnection() {
        // Test that connection is successful
        assertNotNull(db, "Database connection should not be null after connecting");
    }

    /**
     * Test for retrieving a list of countries from the world.
     * Verifies that the `getCountryWorld` method works as expected.
     */
    @Test
    void testGetCountryWorld() {
        String query = "SELECT * FROM country";
        ArrayList<Country> countries = db.getAllCountriesSortedByPopulation(5);

        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        Country country = countries.get(0);
        assertNotNull(country, "The first country should not be null");
        assertNotNull(country.getName(), "Country name should not be null");
        assertNotNull(country.getCode(), "Country code should not be null");
    }

    /**
     * Test for handling invalid queries when retrieving countries.
     */
    @Test
    void testGetCountryWorldInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        ArrayList<Country> countries = db.getCountryWorld(invalidQuery, 5);

        // Assert that the result is null, as the query is invalid
        assertNull(countries, "The result should be null due to invalid query");
    }

    /**
     * Test for retrieving countries from a specific region.
     */
    @Test
    void testGetCountryRegion() {
        String query = "SELECT * FROM country";
        String region = "North America";
        ArrayList<Country> countries = db.getCountryRegion(query, 5, region);

        assertNotNull(countries, "The list of countries should not be null");
        assertFalse(countries.isEmpty(), "The list of countries should not be empty");

        for (Country country : countries) {
            assertEquals(region, country.region, "Country region should be " + region);
        }
    }

    /**
     * Test for retrieving languages.
     */
    @Test
    void testGetLanguage() {
        String query = Language_queries.query;
        ArrayList<Language> languages = db.getLanguages(query);

        assertNotNull(languages, "The list of languages should not be null");
        assertFalse(languages.isEmpty(), "The list of languages should not be empty");

        Language language = languages.get(0);
        assertNotNull(language, "The first language should not be null");
        assertNotNull(language.name, "Language name should not be null");
        assertNotNull(language.population, "Language population should not be null");
    }

    /**
     * Test for retrieving a list of cities.
     */
    @Test
    void testGetCityWorld() {
        String query = "SELECT * FROM city";
        ArrayList<City> cities = db.getCitiesByPopulation(query, 5);

        assertNotNull(cities, "The list of cities should not be null");
        assertFalse(cities.isEmpty(), "The list of cities should not be empty");

        City city = cities.get(0);
        assertNotNull(city, "The first city should not be null");
        assertNotNull(city.name, "City name should not be null");
        assertNotNull(city.countryCode, "City country code should not be null");
    }

    /**
     * Test for handling invalid queries when retrieving cities.
     */
    @Test
    void testGetCityWorldInvalidQuery() {
        String invalidQuery = "SELECT * FROM non_existing_table";
        ArrayList<City> cities = db.getCitiesByPopulation(invalidQuery, 5);

        // Assert that the result is null, as the query is invalid
        assertNull(cities, "The result should be null due to invalid query");
    }

    /**
     * Test for handling empty result sets when retrieving cities.
     */
    @Test
    void testGetCityWorldEmpty() {
        String query = "SELECT * FROM city WHERE Population < 0";
        ArrayList<City> cities = db.getCitiesByPopulation(query, 5);

        assertNotNull(cities, "The list should not be null");
        assertTrue(cities.isEmpty(), "The list should be empty due to no matching results");
    }
}