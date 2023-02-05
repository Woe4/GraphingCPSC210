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

    // EFFECTS: returns the function as a string
    public String getFunctionString() {
        return functionString;
    }

}

