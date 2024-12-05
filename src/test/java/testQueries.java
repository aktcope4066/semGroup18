import java.sql.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class testQueries
{
    @Test
    public void TestDatabaseDataConnection()
    {
        String jbcurl = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "group18";
        try(Connection Con = DriverManager.getConnection(jbcurl, username, password))
        {
            assertNotNull(Con);

            System.out.println("Connected to Database");
        }
        catch(SQLException e)
        {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
    @Test
    public void TestDataExtraction()
    {
        String jbcurl = "jdbc:mysql://localhost:3306/world";
        String username = "root";
        String password = "group18";
        try(Connection Con = DriverManager.getConnection(jbcurl, username, password)) {
            assertNotNull(Con);

            String Query = "SELECT table_name FROM information_schema.tables WHERE table_schema = 'world'";
            PreparedStatement stmt = Con.prepareStatement(Query);
            ResultSet rs = stmt.executeQuery(Query);

            System.out.println("Tables in the world DB");
            int TableCount = 0;

            while (rs.next()) {
                String tableName = rs.getString("table_name");

                System.out.println(tableName);
                System.out.println("Connection Successful");

                assertNotNull(tableName, "Table name should not be NULL");
                TableCount++;
            }
            assertTrue(TableCount > 0);
            System.out.println("Connected to Database and Data Extraction Successful");
        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

