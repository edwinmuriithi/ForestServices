import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import java.util.List;

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
        assertEquals("1", testRanger.getBadgeNumber());
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
        assertEquals(1, Ranger.all().size());
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

    @Test
    @DisplayName("Find returns correct object")
    public void find_returnsRangerWithSameId_testRanger2() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        Ranger testRanger2 = setUpNewRanger2();
        testRanger2.save();
        assertEquals( testRanger2, Ranger.find(testRanger2.getId()));
    }

    @Test
    @DisplayName("Delete by ID")
    public void deleteById() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        Ranger testRanger2 = setUpNewRanger2();
        testRanger2.save();
        testRanger.deleteById(testRanger.getId());
        assertEquals(null, Ranger.find(testRanger.getId()));
        assertEquals(1, Ranger.all().size());
    }

    @Test
    @DisplayName("Delete all Ranger entries.")
    public void deleteAllEntries() {
        Ranger testRanger = setUpNewRanger();
        testRanger.save();
        Ranger testRanger2 = setUpNewRanger2();
        testRanger2.save();
        Ranger.deleteAll();
        List<Ranger> rangers = Ranger.all();
        assertEquals(0, rangers.size());
    }

    @Test
    @DisplayName("Validate name")
    public void ensureNameFieldCannotBeEmpty(){
        Ranger testRanger = new Ranger("","1");
        try {
            if (testRanger.getName().equals("")){
                throw new IllegalArgumentException("You cannot leave Name blank");
            }
            testRanger.save();

        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        assertEquals("", testRanger.getName());
        assertEquals(0, Ranger.all().size());
    }

    @Test
    @DisplayName("Validate type")
    public void ensureBadgeNumberFieldCannotBeEmpty(){

        Ranger testRanger = new Ranger("Chuck", "");
        try {
            if (testRanger.getBadgeNumber().equals("")){
                throw new IllegalArgumentException("You cannot leave Badge Number blank");
            }
            testRanger.save();

        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
        assertEquals("", testRanger.getBadgeNumber());
        assertEquals(0, Ranger.all().size());
    }

    @Test
    @DisplayName("Ensure all Ranger's sightings are returned")
    public void allSightingsAreReturnedForRanger() {
        Ranger ranger = setUpNewRanger();
        try {
            Location location = new Location("Zone A");
            ranger.save();
            location.save();
            Sighting sighting = new Sighting(location.getId(), ranger.getId(),1);
            Sighting otherSighting = new Sighting(1, ranger.getId(),1);
            sighting.save();
            otherSighting.save();
            assertEquals(ranger.getRangerSightings().get(0), sighting);
            assertEquals(ranger.getRangerSightings().get(1), otherSighting);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }







    //helper method.
    private Ranger setUpNewRanger() {
        return new Ranger ("Chuck", "1");
    }
    private Ranger setUpNewRanger2() { return new Ranger ("Norris", "2"); }
}
