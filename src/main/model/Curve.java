package model;

import java.util.ArrayList;
import java.util.List;

public class Curve {

    ArrayList<Coordinate> coordinateList;

    public Curve() {
        coordinateList = new ArrayList<Coordinate>();
    }

    // MODIFIES: this
    // EFFECTS: adds coordinate into the list
    public void addCoordinate(Coordinate coordinate) {
        coordinateList.add(coordinate);
    }


    // EFFECTS: returns coordinate at given index
    public Coordinate getCoordinate(int index) {
        return coordinateList.get(index);
    }

    // EFFECTS: returns number of coordinates in curve
    public int getNumberOfCoordinate() {
        return coordinateList.size();
    }

    // EFFECTS: returns the list reference
    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }
}
