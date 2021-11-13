import org.sql2o.Connection;
import java.util.List;
import java.util.Objects;

public class Animal {
    //properties
    private String name;
    private String type;
    public int id;
    public String health;
    public String age;

    //constructor
    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
        this.health = "";
        this.age = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getName().equals(animal.getName()) && getType().equals(animal.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    //save
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";

            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    //fetch all animals
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    //get ID
    public int getId() {
        return id;
    }

    //Find Animal by ID
    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals WHERE id=:id";
            Animal animal = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

    //Delete by ID
    public void deleteById(int id) {
        String sql = "DELETE FROM animals WHERE id= :id";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }

    //Delete all Animals
    public static void deleteAll(){
        String sql = "DELETE FROM animals";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        }
    }


    public String getHealth() {
        return health;
    }
}
