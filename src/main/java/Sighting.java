import org.sql2o.Connection;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Sighting {

    private int animal_id;
    private int ranger_id;
    private int location_id;
    private Date date = new Date();
    private Timestamp time;
    private int id;

    public Sighting(int animal_id, int ranger_id, int location_id) {
        this.animal_id = animal_id;
        this.ranger_id = ranger_id;
        this.location_id = location_id;
        this.time = new Timestamp(date.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sighting)) return false;
        Sighting sighting = (Sighting) o;
        return getAnimalId() == sighting.getAnimalId() && getRangerId() == sighting.getRangerId() && getLocationId() == sighting.getLocationId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAnimalId(), getRangerId(), getLocationId());
    }

    public int getAnimalId() {
        return animal_id;
    }

    public int getRangerId() {
        return ranger_id;
    }

    public int getLocationId() {
        return location_id;
    }

    public int getId(){
        return id;
    }

    public void save() {
        if(this.animal_id == -1||this.location_id == -1 || this.ranger_id == -1){
            throw new IllegalArgumentException("All fields must be filled");
        }
        try (Connection con=DB.sql2o.open()){
            String sql= "INSERT INTO sightings (animal_id, ranger_id, location_id,time) VALUES (:animal_id, :ranger_id," +  ":location_id,:time)";
            String joinRanger="INSERT INTO rangers_sightings (ranger_id,sighting_id) VALUES (:ranger_id,:sighting_id)";
            String joinLocation="INSERT INTO locations_sightings (location_id,sighting_id) VALUES (:location_id," +
                    ":sighting_id)";

            this.id=(int) con.createQuery(sql,true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("ranger_id", this.ranger_id)
                    .addParameter("location_id", this.location_id)
                    .addParameter("time", this.time)
                    .executeUpdate()
                    .getKey();

            con.createQuery(joinRanger).addParameter("ranger_id", this.getRangerId()).addParameter("sighting_id",
                    this.getId()).executeUpdate();
            con.createQuery(joinLocation).addParameter("location_id", this.getLocationId()).addParameter("sighting_id",
                    this.id).executeUpdate();
        }
    }

    public static List<Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

}
