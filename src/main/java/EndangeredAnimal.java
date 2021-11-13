public class EndangeredAnimal extends Animal implements DatabaseManagement {
    public EndangeredAnimal(String name, String type, String healthStatus, String age) {
        super(name, type);
    }

    @Override
    public void deleteById() {

    }
}
