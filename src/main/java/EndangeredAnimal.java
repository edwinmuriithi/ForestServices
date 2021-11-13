import org.sql2o.Connection;

public class EndangeredAnimal extends Animal implements DatabaseManagement {

    private String name;
    private String type;
    private String health;
    public static final String HEALTH_HEALTHY = "healthy";
    public static final String HEALTH_ILL = "ill";
    public static final String HEALTH_OKAY = "okay";
    private String age;
    public static final String AGE_NEWBORN = "newborn";
    public static final String AGE_YOUNG = "young";
    public static final String AGE_ADULT = "adult";
    public static final String ENDANGERED = "endangered";

    //constructor
    public EndangeredAnimal(String name, String type, String health, String age) {
        //comes from Animal superclass.
        super(name, type);
        this.type = type;
        this.health = health;
        this.age = age;
    }

    @Override
    public void save() {
        try (Connection con=DB.sql2o.open()){

            String sql ="INSERT INTO animals (name, type, health, age) VALUES (:name,:type,:health,:age)";

            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }
    }

    @Override
    public void deleteById() {
        String sql = "DELETE FROM animals WHERE id= :id";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }

    public String getHealth() {
        return health;
    }

}
