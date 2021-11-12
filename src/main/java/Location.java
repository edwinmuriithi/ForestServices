import java.util.Objects;

public class Location {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Objects.equals(getLocationName(), location.getLocationName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationName());
    }

    private String locationName;

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
