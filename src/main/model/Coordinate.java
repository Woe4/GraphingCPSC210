package model;


// An ordered pair.
// A coordinate which has an x value and a y value.
public class Coordinate {
    private final double coordX;
    private final double coordY;


    // EFFECTS: Makes a Coordinate at (x, y)
    public Coordinate(double x, double y) {
        this.coordX = x;
        this.coordY = y;
    }

    public double getCoordX() {
        return coordX;
    }

    public double getCoordY() {
        return coordY;
    }
}
