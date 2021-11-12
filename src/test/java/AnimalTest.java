import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {

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
    @DisplayName("Animal Instantiates Correctly.")
    public void testInstanceOfAnimal_true(){
        Animal testAnimal = setUpNewAnimal();
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    @DisplayName("Animal Instantiates Correctly the name Betty.")
    public void getName_animalInstantiatesWithName_Betty() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("Betty", testAnimal.getName());
    }

    @Test
    @DisplayName("Animal Instantiates Correctly the type cow.")
    public void getType_animalInstantiatesWithType_Cow() {
        Animal testAnimal = setUpNewAnimal();
        assertEquals("cow", testAnimal.getType());
    }

    @Test
    @DisplayName("Compare objects")
    public void returnTrueIfNameAndTypeAreTheSame_true() {
        Animal testAnimal = setUpNewAnimal();
        Animal testAnimal2 = setUpNewAnimal();
        assertTrue(testAnimal.equals(testAnimal2));
    }

    @Test
    @DisplayName("Animal saves objects")
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
        assertTrue(Animal.all().contains(testAnimal));
    }

    @Test
    @DisplayName("Return one instances of Animal")
    public void all_returnsOneInstancesOfAnimal_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        assertEquals(1, Animal.all().size());
    }

    @Test
    @DisplayName("Return all instances of Animal")
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimal2 = setUpNewAnimal2();
        testAnimal2.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(testAnimal2));
        assertEquals(2, Animal.all().size());
    }

    @Test
    @DisplayName("DB correctly assigns IDs to objects")
    public void save_assignsIdToObject() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }

    @Test
    @DisplayName("Find returns correct object")
    public void find_returnsPersonWithSameId_testAnimal2() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimal2 = setUpNewAnimal2();
        testAnimal2.save();
        assertEquals(Animal.find(testAnimal2.getId()), testAnimal2);
    }

    @Test
    @DisplayName("Delete by ID")
    public void deleteById() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimal2 = setUpNewAnimal2();
        testAnimal2.save();
        testAnimal.deleteById(testAnimal.getId());
        assertEquals(null, Animal.find(testAnimal.getId()));
        assertEquals(1, Animal.all().size());
    }

    @Test
    @DisplayName("Delete all Animal entries.")
    public void deleteAllEntries() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimal2 = setUpNewAnimal2();
        testAnimal2.save();
        Animal.deleteAll();
        List<Animal> animals=Animal.all();
        assertEquals(0,animals.size());
    }































    //helper method.
    private Animal setUpNewAnimal() {
        return new Animal ("Betty", "cow");
    }
    private Animal setUpNewAnimal2(){ return new Animal ("Rose", "goat"); }
}
