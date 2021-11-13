public class Ranger {
    private int badge_number;
    private String name;

    public Ranger(String name, int badge_number) {
        this.name = name;
        this.badge_number = badge_number;
    }

    public String getName() {
        return name;
    }

    public int getBadgeNumber() {
        return badge_number;
    }
}
