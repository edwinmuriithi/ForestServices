import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class RangerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @AfterEach
    protected void after() {

        //clear rangers table after each test
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM rangers;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
        }
    }

    @Test
    @DisplayName("Ranger Instantiates Correctly.")
    public void testInstanceOfRanger_true(){
        Ranger testRanger = setUpNewRanger();
        assertEquals(true, testRanger instanceof Ranger);
    }

    @Test
    @DisplayName("Ranger Instantiates Correctly the name Chuck.")
    public void getName_rangerInstantiatesWithName_Chuck() {
        Ranger testRanger = setUpNewRanger();
        assertEquals("Chuck", testRanger.getName());
    }

    @Test
    @DisplayName("Ranger Instantiates Correctly the badge_number of 1.")
    public void getBadgeNumber_rangerInstantiatesWithBadgeNumber_1() {
        Ranger testRanger = setUpNewRanger();
        assertEquals(1, testRanger.getBadgeNumber());
    }

    @Test
    @DisplayName("Compare objects")
    public void returnTrueIfNameAndBadgeNumberAreTheSame_true() {
        Ranger testRanger = setUpNewRanger();
        Ranger testRanger2 = setUpNewRanger();
        assertTrue(testRanger.equals(testRanger2));
    }

    @Test
    @DisplayName("Ranger saves objects")
    public void save_insertsObjectIntoDatabase_Ranger() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        assertTrue(Ranger.all().get(0).equals(testRanger));
        assertTrue(Ranger.all().contains(testRanger));
    }

    @Test
    @DisplayName("Return one instances of Ranger")
    public void all_returnsOneInstancesOfRanger_true() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        assertEquals(1, Ranger.all().size());
    }

    @Test
    @DisplayName("Return all instances of Ranger")
    public void all_returnsAllInstancesOfRanger_true() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        Ranger testRanger2 = setUpNewRanger2();
        testRanger2.save();
        assertEquals(true, Ranger.all().get(0).equals(testRanger));
        assertEquals(true, Ranger.all().get(1).equals(testRanger2));
        assertEquals(2, Ranger.all().size());
    }

    @Test
    @DisplayName("DB correctly assigns IDs to objects")
    public void save_assignsIdToObject() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        Ranger savedAnimal = Ranger.all().get(0);
        assertEquals(testRanger.getId(), savedAnimal.getId());
    }









    //helper method.
    private Ranger setUpNewRanger() {
        return new Ranger ("Chuck", 1);
    }
    private Ranger setUpNewRanger2() { return new Ranger ("Norris", 2); }
}
