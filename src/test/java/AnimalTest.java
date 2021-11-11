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
    //helper method.
    private Animal setUpNewAnimal() {
        return new Animal ("Betty", "cow");
    }
}
