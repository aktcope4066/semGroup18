import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.napier.sem.classes.Country;
import com.napier.sem.db;

class AppTest {
    static db Database;

    @BeforeAll
    static void init() {
        db db = new db();
    }

    @Test
    void printCountriesTestEmpty() {
        ArrayList<Country> countries = new ArrayList<>();
        System.out.println("Testing printCountries with an empty list:");
        db.printCountries(countries);  // print header
    }

    @Test
    void printCountriesTestContainsNull() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        System.out.println("Testing printCountries with a list containing null:");
        db.printCountries(countries);  // skip empty
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