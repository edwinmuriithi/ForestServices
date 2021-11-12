public class Sighting {

    private int animalId;
    private int rangerId;


    public Sighting(int i, int animalId, int rangerId, int locationId) {
        this.animalId = animalId;
        this.rangerId = rangerId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getRangerId() {
        return rangerId;
    }
}
