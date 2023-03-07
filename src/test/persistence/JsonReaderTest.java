package persistence;

import model.Function;
import model.FunctionHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// test suite for the JsonReader class
public class JsonReaderTest {
    private JsonReader jsonReader;

    @Test
    public void testJsonReader() {
        jsonReader = new JsonReader("hello");
        assertEquals("hello", jsonReader.getDataFile());
        jsonReader = new JsonReader("./data/save.json");
        assertEquals("./data/save.json", jsonReader.getDataFile());
    }

    @Test
    void testReaderHistoryNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FunctionHistory history = reader.readHistory();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderFunctionNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Function function = reader.readFunction();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderDomainNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            double number = reader.readDomain();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderRangeNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            double number = reader.readRange();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReadHistoryEmpty() {
        jsonReader = new JsonReader("./data/testReaderNoHistory.json");
        try {
            FunctionHistory history = jsonReader.readHistory();
            assertEquals(0, history.getLength());
        } catch (IOException e) {
            fail("IOException is not expected");
        }

    }

    @Test
    public void testReadFunctionEmpty() {
        jsonReader = new JsonReader("./data/testReaderNoHistory.json");
        try {
            Function function = jsonReader.readFunction();
            assertEquals("", function.getFunctionString());
        } catch (IOException e) {
            fail("IOException is not expected");
        }

    }

    @Test
    public void testReadDomainEmpty() {
        jsonReader = new JsonReader("./data/testReaderNoHistory.json");
        try {
            Function function = jsonReader.readFunction();
            assertEquals("", function.getFunctionString());
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testReadRangeEmpty() {
        jsonReader = new JsonReader("./data/testReaderNoHistory.json");
        try {
            Function function = jsonReader.readFunction();
            assertEquals("", function.getFunctionString());
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testReadHistory() {
        jsonReader = new JsonReader("./data/testReaderWithHistory.json");
        try {
            FunctionHistory history = jsonReader.readHistory();
            assertEquals(2, history.getLength());
            assertEquals("x*x", history.getFunction(0).getFunctionString());
            assertEquals("10*x+7", history.getFunction(1).getFunctionString());
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testReadFunction() {
        jsonReader = new JsonReader("./data/testReaderWithHistory.json");
        try {
            Function aFunction = jsonReader.readFunction();
            assertEquals("-x", aFunction.getFunctionString());
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testReadDomain() {
        jsonReader = new JsonReader("./data/testReaderWithHistory.json");
        try {
            double domain = jsonReader.readDomain();
            assertEquals(80, domain);
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

    @Test
    public void testReadRange() {
        jsonReader = new JsonReader("./data/testReaderWithHistory.json");
        try {
            double range = jsonReader.readRange();
            assertEquals(24, range);
        } catch (IOException e) {
            fail("IOException is not expected");
        }
    }

}
