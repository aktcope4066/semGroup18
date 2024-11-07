import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.napier.sem.classes.Country;
import com.napier.sem.db;

class Tests {
    static db Database;

    @BeforeAll
    static void init() {
        Database = new db();
    }

    @Test
    void printCountriesTestNull() {
        System.out.println("Testing printCountries with null input:");
        Database.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        System.out.println("Testing printCountries with an empty list:");
        Database.printCountries(countries);  // print header
    }

    @Test
    void printCountriesTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        System.out.println("Testing printCountries with a list containing null:");
        Database.printCountries(countries);  // skip empty
    }

    @Test
    void printCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Country country = new Country("ABC", "TestName", "TestContinent", "TestRegion", 123, "TestCapital");
        countries.add(country);
        System.out.println("Testing printCountries with a valid country entry:");
        Database.printCountries(countries);  // country dertails
    }

    //tests to check conditions
    @Test
    void unitTestEqual() {
        System.out.println("Testing equality:");
        assert 5 == 5 : "Values should be equal";
    }

    @Test
    void unitTestArrayEqual() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        assert java.util.Arrays.equals(a, b) : "Arrays should be equal";
    }

    @Test
    void unitTestTrueCondition() {
        System.out.println("Testing true condition:");
        assert 5 == 5 : "Condition should be true";
    }
}