package model;

import java.util.ArrayList;
import java.util.List;

// A class representing a list of functions
public class FunctionHistory {
    private ArrayList<Function> history;

    // EFFECTS: Creates an empty history
    public FunctionHistory() {
        history = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds function to the end of the list
    public void addFunction(Function function) {
        history.add(function);
    }

    // MODIFIES: this
    // EFFECTS: clears all history (deletes functions from list)
    public void clearHistory() {
        history = new ArrayList<>();
    }

    // EFFECTS: returns list of functions as strings
    public List<String> getFunctionStrings() {
        List<String> result = new ArrayList<>();
        for (Function f : history) {
            result.add(f.getFunctionString());
        }
        return result;
    }

    // EFFECTS: gets function at 0 based index
    public Function getFunction(int index) {
        return history.get(index);
    }

    // EFFECTS: returns number of functions in history
    public int getLength() {
        return history.size();
    }




}
