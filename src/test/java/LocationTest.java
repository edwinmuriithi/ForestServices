import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocationTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @AfterEach
    protected void after() {
        //clear locations table after each test
        try(Connection con = DB.sql2o.open()) {
            String deleteLocationsQuery = "DELETE FROM locations;";
            con.createQuery(deleteLocationsQuery).executeUpdate();
        }
    }

    @Test
    @DisplayName("Location Instantiates Correctly.")
    public void testInstanceOfLocation_true(){
        Location testLocation = setUpNewLocation();
        assertEquals(true, testLocation instanceof Location);
    }

    @Test
    @DisplayName("Location Instantiates Correctly with location Name.")
    public void getName_locationInstantiatesWithName() {
        Location testLocation = setUpNewLocation();
        assertEquals("Location 1", testLocation.getName());
    }

    @Test
    @DisplayName("Compare Location objects")
    public void returnTrueIfLocationIsTheSame_true() {
        Location testLocation = setUpNewLocation();
        Location testLocation2 = setUpNewLocation();
        assertTrue(testLocation.equals(testLocation2));
    }

    @Test
    @DisplayName("Location saves objects")
    public void save_insertsObjectIntoDatabase_Location() {
        Location testLocation = setUpNewLocation();
        testLocation.save();
        assertTrue(Location.all().get(0).equals(testLocation));
        assertTrue(Location.all().contains(testLocation));
    }

    @Test
    @DisplayName("Return all instances of Location")
    public void all_returnsOneInstancesOfLocation_true() {
        Location testLocation = setUpNewLocation();
        testLocation.save();
        Location testLocation2 = setUpNewLocation2();
        testLocation2.save();
        assertEquals(2, Location.all().size());
        assertEquals(true, Location.all().contains(testLocation));
        assertEquals(true, Location.all().contains(testLocation2));

    }

    @Test
    @DisplayName("DB correctly assigns IDs to objects")
    public void save_assignsIdToObject() {
        Location testLocation = setUpNewLocation();
        testLocation.save();
        Location savedLocation = Location.all().get(0);
        assertEquals(testLocation.getId(), savedLocation.getId());
    }

    @Test
    @DisplayName("Find returns correct object")
    public void find_returnsLocationWithSameId_testLocation2() {
        Location testLocation = setUpNewLocation();
        testLocation.save();
        Location testLocation2 = setUpNewLocation2();
        testLocation2.save();
        assertEquals(Location.find(testLocation2.getId()), testLocation2);
    }

    @Test
    @DisplayName("Delete by ID")
    public void deleteById() {
        Location testLocation = setUpNewLocation();
        testLocation.save();
        Location testLocation2 = setUpNewLocation2();
        testLocation2.save();
        testLocation.deleteById(testLocation.getId());
        assertEquals(null, Location.find(testLocation.getId()));
        assertEquals(1, Location.all().size());
    }











    //helper method.
    private Location setUpNewLocation() {
        return new Location ("Location 1");
    }
    private Location setUpNewLocation2() {
        return new Location ("Location 2");
    }
    }
