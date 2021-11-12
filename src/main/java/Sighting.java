public class Sighting {

    private int animalId;
    private int rangerId;
    private int locationId;

    public Sighting(int i, int animalId, int rangerId, int locationId) {
        this.animalId = animalId;
        this.rangerId = rangerId;
        this.locationId = locationId;


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
