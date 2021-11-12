import java.util.Objects;

public class Sighting {

    private int animalId;
    private int rangerId;
    private int locationId;

    public Sighting(int i, int animalId, int rangerId, int locationId) {
        this.animalId = animalId;
        this.rangerId = rangerId;
        this.locationId = locationId;


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
        return animalId;
    }

    public int getRangerId() {
        return rangerId;
    }

    public int getLocationId() {
        return locationId;
    }
}
