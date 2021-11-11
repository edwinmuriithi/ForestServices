import java.util.Objects;

public class Animal {
    //properties
    private String name;
    private String type;
    //constructor
    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
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
}
