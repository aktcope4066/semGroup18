//template for country queries -manik
package sql.queries;
public class countryQuery {
//query strings that will be used in database methods for country reports

    //default constructure sort of thing
    public static String countryQuery = 
        "SELECT Code, country.Name, country.Continent, country.Region, country. Population, city.Name AS Capital " +
        "FROM country " + 
        "JOIN city ON country.Capital=city.ID ";
    
    //Where clause options
    public static String continent = "WHERE country.Continent = ";
    public static String region = "WHERE country.Region = ";
}