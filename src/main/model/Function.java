package model;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.lang.Math.*;


// This is the class that deals with a function (like y = f(x) kind of function).
// You can get the function evaluated at any point.
//
public class Function {

    private final String functionString;

    // EFFECTS: creates a new function with given string
    public Function(String function) {
        this.functionString = function;
    }

    // EFFECTS: returns function evaluated at given x value, if unevaluable, returns -1
    public double evaluateFunction(double x) {
        try {
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            String stringX = Double.toString(x);
            String functionWithX = functionString.replace("x", "(" + stringX + ")");
            return Double.valueOf((engine.eval(functionWithX).toString()));
        } catch (ScriptException e) {
            // not much we can do here
            System.out.println("Invalid script/function");
            e.printStackTrace();

            return -1;
        }


    }

    // EFFECTS: returns coordinate of function at some x value
    public Coordinate getCoord(double x) {
        return new Coordinate(x, evaluateFunction(x));
    }

    // REQUIRES: start < end and accuracy < (end-start)
    // EFFECTS: returns Curve of the function with the given accuracy from start to end
    public Curve getCurve(double accuracy, double start, double end) {
        Curve curve = new Curve();
        while (end >= start) {
            curve.addCoordinate(new Coordinate(start, evaluateFunction(start)));
            start += accuracy;
        }
        return curve;
    }

    // REQUIRES: start < end and accuracy < (end-start)
    // EFFECTS: returns area under curve approximated as right Riemann Sum
    public double takeDefiniteIntegral(double accuracy, double start, double end) {
        double result = 0;
        while (start <= end) {
            result += evaluateFunction(start) * accuracy;
            start += accuracy;
        }
        return result;

    }


    // EFFECTS: returns the function as a string
    public String getFunctionString() {
        return functionString;
    }

}

