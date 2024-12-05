//template for city queries - Manik
public class cityQuery {
    //query strings that will be used in database methods for city reports

    //default constructure sort of thing
    public static String cityQuery = 
        "SELECT city.ID, city.Name, city.CountryCode, city.District, city.population " +
        "FROM city " + 
        "JOIN country ON city.ID = country.Name ";
    
    //Where clause options
    public static String continent = "WHERE country.Continent = ";
    public static String region = "WHERE country.Region = ";
    public static String country = "WHERE country.Country = ";
    public static String district = "WHERE country.District = ";
}
