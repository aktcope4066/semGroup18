package sql.queries;
/*
USAGE
 * String query = populationQuery.baseQuery + 
               populationQuery.continent("Asia") + 
               populationQuery.groupBy;
ArrayList<PopulationReport> report = db.getPopulationData(query);

 */
public class populationQuery {
    // Query to get total population, city population, and non-city population for a specific region/continent
    public static String baseQuery = 
    "SELECT country.Name, " +
    "country.Population AS TotalPopulation, " +
    "SUM(city.Population) AS CityPopulation, " +
    "(country.Population - SUM(city.Population)) AS NonCityPopulation " +
    "FROM country " +
    "LEFT JOIN city ON country.Capital = city.ID ";

    // Methods for dynamic WHERE clauses
    public static String continent(String continentName) {
        return "WHERE country.Continent = '" + continentName + "'";
    }

    public static String region(String regionName) {
        return "WHERE country.Region = '" + regionName + "'";
    }

    public static String country(String countryName) {
        return "WHERE country.Name = '" + countryName + "'";
    }

    // Group by
    public static String groupBy = "GROUP BY country.Name, country.Population";
}
