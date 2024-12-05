package sql.queries;
public class languageQuery {
    //query string that will be used in database methods for language reports
    public static String LanguageQuery = 
        "SELECT countryLanguage.Language, " +
        "   ROUND((SUM(countryLanguage.Percentage / 100 * country.Population) / (SELECT SUM(Population) FROM country)) * 100, 2)" +
        "FROM countryLanguage " + 
        "JOIN country ON countryLanguage.CountryCode = country.Code" +
        "WHERE countryLanguage.Languages IN ('Chinese', 'Hindi', 'Spanish', 'English', 'Arabic') " + 
        "GROUP BY countryLanguage.Language " +
        "ORDER BY Population DESC";
}
