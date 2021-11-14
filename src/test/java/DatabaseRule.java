import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.rules.ExternalResource;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        //connect to the test DB with these credentials
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "davide", "jw8s0F4");

        //connect to heroku DB
        String connectionString = "jdbc:postgresql://ec2-18-204-74-74.compute-1.amazonaws.com:5432/d8ie6bbjts7o40";
        DB.sql2o = new Sql2o(connectionString, "saditgxzvvjuzy", "fd1fda32eeb772315a0475986626297480509f647db23bf20805a34f8da742f4");

    }
    @Override
    protected void after() {
        //clear table after each test
        try(Connection con = DB.sql2o.open()) {

            String deleteAnimalsQuery = "DELETE FROM animals;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();

            String deleteLocationsQuery = "DELETE FROM locations;";
            con.createQuery(deleteLocationsQuery).executeUpdate();

            String deleteRangerQuery = "DELETE FROM rangers;";
            con.createQuery(deleteRangerQuery).executeUpdate();
        }

    }
}
