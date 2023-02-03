package model;

public class Coordinate {
    private double coordX;
    private double coordY;


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
