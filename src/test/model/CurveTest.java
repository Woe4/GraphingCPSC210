package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurveTest {

    Curve aCurve;
    Curve anotherCurve;

    @BeforeEach
    public void setup() {
        aCurve = new Curve();
        anotherCurve = new Curve();
    }

    @Test
    public void testCurve() {
        assertEquals(0, aCurve.getCoordinateList().size());
    }

    @Test
    public void testAddCoordinateOnce() {
        aCurve.addCoordinate(new Coordinate(-1.1, 1));
        assertEquals(-1.1, aCurve.getCoordinate(0).getCoordX());
        assertEquals(1, aCurve.getCoordinate(0).getCoordY());
        anotherCurve.addCoordinate(new Coordinate(32.2, -17.11));
        assertEquals(32.2, anotherCurve.getCoordinate(0).getCoordX());
        assertEquals(-17.11, anotherCurve.getCoordinate(0).getCoordY());
    }

    @Test
    public void testAddCoordinateMultipleTimes() {
        aCurve.addCoordinate(new Coordinate(-1.1, 1));
        aCurve.addCoordinate(new Coordinate(14, -3.7));
        assertEquals(14, aCurve.getCoordinate(1).getCoordX());
        assertEquals(-3.7, aCurve.getCoordinate(1).getCoordY());
    }

    @Test
    public void testGetNumberOfCoordinates() {
        assertEquals(0, aCurve.getNumberOfCoordinate());
        aCurve.addCoordinate(new Coordinate(-1.1, 1));
        assertEquals(1, aCurve.getNumberOfCoordinate());
        aCurve.addCoordinate(new Coordinate(14, -3.7));
        assertEquals(2, aCurve.getNumberOfCoordinate());
    }
}