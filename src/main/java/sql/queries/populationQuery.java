package sql.queries;

public class populationQuery {
    // Query to get total population, city population, and non-city population for a specific region/continent
    public static String query = 
    "SELECT country.Name, " +
    "country.Population AS TotalPopulation, " +
    "SUM(city.Population) AS CityPopulation, " +
    "(country.Population - SUM(city.Population)) AS NonCityPopulation " +
    "FROM country " +
    "LEFT JOIN city ON country.Capital = city.ID " +
    "GROUP BY country.Name, country.Population";

    // WHERE clauses for continent, region, or country
    public static String continent = "WHERE country.Continent=";
    public static String region = "WHERE country.Region=";
    public static String country = "WHERE country.Name=";
}
