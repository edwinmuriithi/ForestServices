import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @AfterEach
    protected void after() {
        //clear animals table after each test
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }

    @Test
    @DisplayName("Location Instantiates Correctly.")
    public void testInstanceOfLocation_true(){
        Location testLocation = setUpNewLocation();
        assertEquals(true, testLocation instanceof Location);
    }













    //helper method.
    private Location setUpNewLocation() {
        return new Location ("Location 1");
    }

}
