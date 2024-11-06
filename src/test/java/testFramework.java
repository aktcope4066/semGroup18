import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.napier.sem.db;
import com.napier.sem.classes.City;
import com.napier.sem.classes.Country;
import com.napier.sem.classes.Population;

class testFramework
{
    static db Database;

    @BeforeAll
    static void init()
    {
        Database = new db();
    }

    @Test
    void printCountriesTestNull()
    {
        Database.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Database.printCountries(countries);
    }

    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        Database.printCountries(countries);
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country("ABC", "TestName", "TestContinent", "TestRegion", 123, "TestCapital");
        countries.add(country);
        Database.printCountries(countries);
    }
}