import org.junit.jupiter.api.Test;

public class AnimalTest {
    @Test
    public void testInstanceOfAnimal_true(){
        Animal testAnimal = setUpNewAnimal();
    }
    //helper method.
    private Animal setUpNewAnimal() {
        return new Animal ("Betty", "cow");
    }
}
