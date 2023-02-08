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

    private double function1(double x) {
        return x+1;
    }

    private double function2(double x) {
        return (x) * (x);
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


    @Test
    public void testGetCurveSimple() throws ScriptException {
        Curve function1Curve = function1.getCurve(0.5, 0, 20);
        double i = 0;
        while (i <= 20) {
            assertEquals(function1(i), function1Curve.getCoordinate((int) (2 * i)).getCoordY());
            assertEquals(i, function1Curve.getCoordinate((int) (2 * i)).getCoordX());
            i += 0.5;
        }
    }

    @Test
    public void testGetCurveComplex() throws ScriptException {
        Curve curve = function2.getCurve(0.1, -100, 50);
        double i = -100;
        int index = 0;
        while (i <= 50) {
            assertEquals(function2(i), curve.getCoordinate(index).getCoordY());
            assertEquals(i, curve.getCoordinate(index).getCoordX());
            i += 0.1;
            index++;
        }
    }

    @Test
    public void testTakeDefiniteIntegral() throws ScriptException {
        double result = function1.takeDefiniteIntegral(1, 0, 10);
        assertEquals(11 * 5, result);
    }
}
