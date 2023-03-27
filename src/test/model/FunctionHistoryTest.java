package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// test class for FunctionHistory Class
public class FunctionHistoryTest {
    private Function function;
    private Function function2;
    private FunctionHistory history;
    private Event e;
    private Date d;

    @BeforeEach
    public void setup() {
        history = new FunctionHistory();
        function = new Function("x");
        function2 = new Function("x*x");
    }

    @Test
    // EFFECTS: test constructor initialized to length == 0
    public void testFunctionHistory() {
        assertEquals(0, history.getLength());
    }

    @Test
    // EFFECTS: test if function added correctly (twice)
    public void testAddFunction() {
        history.addFunction(function);
        assertEquals(1, history.getLength());
        assertEquals("x", history.getFunction(0).getFunctionString());

        history.addFunction(function2);
        assertEquals(2, history.getLength());
        assertEquals("x*x", history.getFunction(1).getFunctionString());
    }

    @Test
    // EFFECTS: test clear history makes size == 0
    public void testClearHistory() {
        history.addFunction(function);
        history.addFunction(function2);
        history.clearHistory();
        assertEquals(0, history.getLength());
    }

    @Test
    // EFFECTS: test if we get a list of strings correct
    public void testGetFunctionStrings() {
        history.addFunction(function2);
        history.addFunction(function);
        List<String> result = history.getFunctionStrings();
        assertEquals("x*x", result.get(0));
        assertEquals("x", result.get(1));
    }
}
