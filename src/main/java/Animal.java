public class Animal {
    //properties
    private String name;
    private String type;
    //constructor
    public Animal(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
}
