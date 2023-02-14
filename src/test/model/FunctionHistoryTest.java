package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionHistoryTest {
    Function function;
    Function function2;
    FunctionHistory history;

    @BeforeEach
    public void setup() {
        history = new FunctionHistory();
        function = new Function("x");
        function2 = new Function("x*x");
    }

    @Test
    public void testFunctionHistory() {
        assertEquals(0, history.getLength());
    }

    @Test
    public void testAddFunction() {
        history.addFunction(function);
        assertEquals(1, history.getLength());
        assertEquals("x", history.getFunction(0).getFunctionString());
        history.addFunction(function2);
        assertEquals(2, history.getLength());
        assertEquals("x*x", history.getFunction(1).getFunctionString());
    }

    @Test
    public void testClearHistory() {
        history.addFunction(function);
        history.addFunction(function2);
        history.clearHistory();
        assertEquals(0, history.getLength());
    }

    @Test
    public void testGetFunctionStrings() {
        history.addFunction(function2);
        history.addFunction(function);
        List<String> result = history.getFunctionStrings();
        assertEquals("x*x", result.get(0));
        assertEquals("x", result.get(1));
    }
}
