sample queries, SEM project

user story 01:
continent-
    SELECT  country.Continent AS `Continent`,
        country.Population AS `Population`,
        (SUM(city.Population)/country.Population)*100 AS `In City Population`,
        ((country.Population-city.Population)/country.Population)*100 `Non City Population`
    FROM country
    LEFT JOIN city ON country.Code = city.CountryCode
    GROUP BY country.Continent, country.Population

region-
    SELECT  country.Region AS `Region`,
        country.Population AS `Population`,
        (SUM(city.Population)/country.Population)*100 AS `In City Population`,
        ((country.Population-city.Population)/country.Population)*100 `Non City Population`
    FROM country
    LEFT JOIN city ON country.Code = city.CountryCode
    GROUP BY country.Region, country.Population

country-
    SELECT  country.Name AS `Country`,
        country.Population AS `Population`,
        (SUM(city.Population)/country.Population)*100 AS `In City Population`,
        ((country.Population-city.Population)/country.Population)*100 `Non City Population`
    FROM country
    LEFT JOIN city ON country.Code = city.CountryCode
    GROUP BY country.Name, country.Population


User Story 03:
world-
    SELECT Code, Name, Continent, Region, Population, Capitol
    FROM country
    ORDER BY Population DESC

continent-
    SELECT Code, Name, Continent, Region, Population, Capitol
    FROM country
    GROUP BY Continent
    ORDER BY Population DESC

region-
    SELECT Code, Name, Continent, Region, Population, Capitol
    FROM country
    GROUP BY region
    ORDER BY Population DESC


User Story 04:
world-


continent-


region-


country-


district-









