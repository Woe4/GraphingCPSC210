package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CoordinateTest {
    Coordinate c1;
    Coordinate c2;

    @Test
    public void testCoordinate() {
        c1 = new Coordinate(3.1, -5.0);
        c2 = new Coordinate(-42, 0);

        assertEquals(3.1, c1.getCoordX());
        assertEquals(-5.0, c1.getCoordY());
        assertEquals(-42, c2.getCoordX());
        assertEquals(0, c2.getCoordY());
    }
}
