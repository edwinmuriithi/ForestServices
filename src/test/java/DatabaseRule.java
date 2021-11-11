import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.rules.ExternalResource;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        //connect to the test DB with these credentials
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/animals_test", "davide", "jw8s0F4");
    }
    @Override
    protected void after() {
        //clear animals table after each test
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals ;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }
}
