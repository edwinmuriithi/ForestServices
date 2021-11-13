public class EndangeredAnimal extends Animal implements DatabaseManagement {

    private String health;
    public static final String HEALTH_HEALTHY = "healthy";
    public static final String HEALTH_ILL = "ill";
    public static final String HEALTH_OKAY = "okay";
    private String age;
    public static final String AGE_NEWBORN = "newborn";
    public static final String AGE_YOUNG = "young";
    public static final String AGE_ADULT = "adult";

    public EndangeredAnimal(String name, String type, String healthStatus, String age) {
        //comes from Animal superclass.
        super(name, type);
    }

    @Override
    public void deleteById() {}

}
