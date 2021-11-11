import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {


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

    //helper method.
    private Animal setUpNewAnimal() {
        return new Animal ("Betty", "cow");
    }
}
