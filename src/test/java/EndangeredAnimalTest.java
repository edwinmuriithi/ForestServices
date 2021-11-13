import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndangeredAnimalTest {

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
    @DisplayName("EndangeredAnimal Instantiates Correctly.")
    public void testInstanceOfEndangeredAnimal_true(){
        EndangeredAnimal testEndangeredAnimalTest = setUpNewEndangeredAnimal();
        testEndangeredAnimalTest.save();
        assertTrue(EndangeredAnimal.all().get(0).getHealth().equals(testEndangeredAnimalTest.getHealth()));
    }

    @Test
    @DisplayName("EndangeredAnimal saves")
    public void allInstancesAreSaved(){
        EndangeredAnimal testEndangeredAnimalTest = setUpNewEndangeredAnimal();
        testEndangeredAnimalTest.save();
        assertEquals(1, Animal.all().size());
    }

    @Test
    @DisplayName("EndangeredAnimal findById works correctly")
    public void findByIdReturnsCorrectInfo(){
        EndangeredAnimal testEndangeredAnimalTest = setUpNewEndangeredAnimal();
        testEndangeredAnimalTest.save();
        Animal foundAnimal= Animal.find(testEndangeredAnimalTest.getId());
        assertEquals(foundAnimal.getType(),EndangeredAnimal.ENDANGERED);
    }























    //helper method.
    private EndangeredAnimal setUpNewEndangeredAnimal() {
        return new EndangeredAnimal ("Betty-the-cow", "endangered","healthy","young");
    }
    private EndangeredAnimal setUpNewEndangeredAnimal2(){
        return new EndangeredAnimal ("Rose-the-goat", "endangered","healthy","young");
    }
}
