import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

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
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal testAnimal = setUpNewAnimal();
        testAnimal.save();
        Animal testAnimal2 = setUpNewAnimal();
        testAnimal2.save();
        assertEquals(true, Animal.all().get(0).equals(testAnimal));
        assertEquals(true, Animal.all().get(1).equals(testAnimal2));
    }




































    //helper method.
    private Animal setUpNewAnimal() {
        return new Animal ("Betty", "cow");
    }
}
