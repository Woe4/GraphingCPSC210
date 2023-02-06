package model;

import java.util.ArrayList;
import java.util.List;

public class Curve {

    List<Coordinate> coordinateList;

    public Curve() {
        coordinateList = new ArrayList<Coordinate>();
    }

    // MODIFIES: this
    // EFFECTS: adds coordinate into the list
    public void addCoordinate(Coordinate coordinate) {
        coordinateList.add(coordinate);
    }



    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }
}
