import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger {

    private String badge_number;
    private String name;
    private int id;

    //constructor.
    public Ranger(String name, String badge_number) {
        this.name = name;
        this.badge_number = badge_number;

    }

    public static void deleteAll() {
        String sql = "DELETE FROM rangers";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return getId() == ranger.getId() && badge_number.equals(ranger.badge_number) && getName().equals(ranger.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(badge_number, getName(), getId());
    }

    public String getName() {
        return name;
    }

    public String getBadgeNumber() {
        return badge_number;
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {

            String sql = "INSERT INTO rangers (name, badge_number) VALUES (:name, :badge_number)";

            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("badge_number", this.badge_number)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Ranger.class);
        }
    }

    public int getId() {
        return id;
    }

    public static Ranger find(int id) {
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM rangers WHERE id=:id";
            Ranger ranger = con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM rangers WHERE id= :id";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).addParameter("id", this.id).executeUpdate();
        }
    }
}
