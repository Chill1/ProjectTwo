public class FirstFloor extends Locale {

    //
    // -- PRIVATE --
    //

    // Constructor
    public FirstFloor (int id) {
        super(id);
    }

    // Getters and Setters
    public void setNearestPlanet(String value) {
        this.FirstFloor = value;
    }
    public String getNearestPlanet() {
        return this.FirstFloor;
    }

    // Other methods
    @Override
    public String toString() {
        return "First Floor: " + super.toString() + " FirstFloor=" + this.FirstFloor;
    }


    //
    // -- PRIVATE --
    //
    private String FirstFloor;

}
