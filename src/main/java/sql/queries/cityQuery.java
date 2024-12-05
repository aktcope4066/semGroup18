//template for city queries - Manik
package sql.queries;

public class cityQuery {
    //query strings that will be used in database methods for city reports

    //default constructure sort of thing
    public static String baseQuery = 
        "SELECT city.ID, city.Name, city.CountryCode, city.District, city.population " +
        "FROM city " + 
        "JOIN country ON city.ID = country.Name ";
    
    //methods for WHERE clauses
    public static String continent(String continentName) {
        return "WHERE country.Continent = '" + continentName + "'";
    }

    public static String region(String regionName) {
        return "WHERE country.Region = '" + regionName + "'";
    }

    public static String country(String countryName) {
        return "WHERE country.Name = '" + countryName + "'";
    }

    public static String district(String districtName) {
        return "WHERE city.District = '" + districtName + "'";
    }

    // Method for WHERE clause for capital cities (important for queries that need capitals)
    public static String capitalCity() {
        return "WHERE city.ID = country.Capital";
    }
}