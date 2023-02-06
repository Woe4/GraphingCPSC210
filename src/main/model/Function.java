package model;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Function {

    private final String functionString;

    public Function(String function) {
        this.functionString = function;
    }

    // EFFECTS: returns function evaluated at given x value
    public double evaluateFunction(double x) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        String stringX = Double.toString(x);
        String functionWithX = functionString.replace("x", "(" + stringX + ")");
        double d = Double.valueOf((engine.eval(functionWithX).toString()));
        return d;

    }

    // EFFECTS: returns coordinate of function at some x value
    public Coordinate getCoord(double x) throws ScriptException {
        Coordinate result = new Coordinate(x, evaluateFunction(x));
        return result;
    }

    // REQUIRES: start < end and accuracy < (end-start)
    // EFFECTS: returns Curve of the function with the given accuracy from start to end
    public Curve getCurve(double accuracy, double start, double end) throws ScriptException {
        Curve curve = new Curve();
        while (end >= start) {
            curve.addCoordinate(new Coordinate(start, evaluateFunction(start)));
            start += accuracy;
        }
        return curve; // stub
    }

    // EFFECTS: returns the function as a string
    public String getFunctionString() {
        return functionString;
    }

}

