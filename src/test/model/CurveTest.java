package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CurveTest {

    Curve aCurve;

    @BeforeEach
    public void setup() {
        aCurve = new Curve();
    }

    @Test
    public void testCurve() {
        assertEquals(0, aCurve.getCoordinateList().size());
    }
}