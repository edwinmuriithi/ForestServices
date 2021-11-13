import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

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
    public void testInstanceOfAnimal_true(){
        EndangeredAnimal testEndangeredAnimalTest = setUpNewEndangeredAnimal();
        assertEquals(true, testEndangeredAnimalTest instanceof EndangeredAnimal);
    }


























    //helper method.
    private EndangeredAnimal setUpNewEndangeredAnimal() {
        return new EndangeredAnimal ("Betty-the-cow", "endangered","healthy","young");
    }

    private EndangeredAnimal setUpNewEndangeredAnimal2(){ return new EndangeredAnimal ("Rose-the-goat", "endangered","healthy","young"); }
}
