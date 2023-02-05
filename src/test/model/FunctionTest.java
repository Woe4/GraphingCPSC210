package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.script.ScriptException;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    Function function1;
    Function function2;

    @BeforeEach
    public void setup() {
        function1 = new Function("x+1");
        function2 = new Function("x*x");
    }

    @Test
    public void testFunction() {
        assertEquals("x+1", function1.getFunctionString());
        assertEquals("x*x", function2.getFunctionString());
    }

    @Test
    public void testEvaluateFunction() throws ScriptException {
        double val1 = function1.evaluateFunction(1.1);
        assertEquals(2.1, val1);
        double val2 = function2.evaluateFunction(4.0);
        assertEquals(16, val2);
    }

    @Test
    public void testGetCoord() throws ScriptException {
        Coordinate coord1 = function1.getCoord(1.0);
        assertEquals(1, coord1.getCoordX());
        assertEquals(2, coord1.getCoordY());
        Coordinate coord2 = function2.getCoord(-2.0);
        assertEquals(-2, coord2.getCoordX());
        assertEquals(4, coord2.getCoordY());
    }
}
