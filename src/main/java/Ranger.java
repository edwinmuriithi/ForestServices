import java.util.Objects;

public class Ranger {
    private int badge_number;
    private String name;

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

}
