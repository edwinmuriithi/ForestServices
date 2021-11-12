import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SightingTest {
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
    @DisplayName("Sighting Instantiates Correctly.")
    public void testInstanceOfAnimal_true(){
        Sighting testSighting = setUpNewSighting();
        assertEquals(true, testSighting instanceof Sighting);
    }

























    //helper method.
    private Sighting setUpNewSighting() {
        return new Sighting (1,1,1, 1);
    }
}
