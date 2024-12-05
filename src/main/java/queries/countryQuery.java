//template for country queries -manik
package queries;
public class countryQuery {
//query strings that will be used in database methods for country reports
/*
    *USAGE
    * String query = cityQuery.baseQuery + cityQuery.continent("Europe");
    * ArrayList<City> cities = db.getCityData(query, 10); // Top 10 cities in Europe
*/
    //base query
    public static String baseQuery = 
        "SELECT Code, country.Name, country.Continent, country.Region, country. Population, city.Name AS Capital " +
        "FROM country " + 
        "JOIN city ON country.Capital=city.ID ";
    
    // Methods for WHERE clauses
    public static String continent(String continentName) {
        return "WHERE country.Continent = '" + continentName + "'";
    }

    public static String region(String regionName) {
        return "WHERE country.Region = '" + regionName + "'";
    }
}