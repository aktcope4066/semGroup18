package com.napier.sem;

import com.napier.sem.classes.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import sql.queries.languageQuery;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTest {
    // Create a test database connection
    static db db;

    // Connect to test database
    @BeforeAll
    static void init() {
        db = new db();
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
        ArrayList<Country> countries = db.getAllCountriesSortedByPopulation(5);

        // Assert that the result is null, as the query is invalid
        assertNull(countries, "The result should be null due to invalid query");
    }
}