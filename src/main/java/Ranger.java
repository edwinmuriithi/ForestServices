import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Ranger {

    private int badge_number;
    private String name;
    private int id;

    //constructor.
    public Ranger(String name, int badge_number) {
        this.name = name;
        this.badge_number = badge_number;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ranger)) return false;
        Ranger ranger = (Ranger) o;
        return badge_number == ranger.badge_number && getName().equals(ranger.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(badge_number, getName());
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
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
}
