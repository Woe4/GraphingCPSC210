package persistence;
import model.Function;
import model.FunctionHistory;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// test suite for JsonWriter class
public class JsonWriterTest {
    JsonWriter jsonWriter;

    @Test
    public void testJsonWriter() {
        jsonWriter = new JsonWriter("./data/testWriterWithHistory.json");
        assertEquals("./data/testWriterWithHistory.json", jsonWriter.getDataFile());
        jsonWriter = new JsonWriter("./data/testWriterNoHistory.json");
        assertEquals("./data/testWriterNoHistory.json", jsonWriter.getDataFile());
    }

    @Test
    public void testOpenIllegal() {
        try {
            jsonWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriter.open();
            fail("Exception expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    public void testWriteNoHistory() {
        try {
            jsonWriter = new JsonWriter("./data/testWriterNoHistory.json");
            FunctionHistory history = new FunctionHistory();
            Function function = new Function("");
            double domain = 0;
            double range = 0;
            jsonWriter.open();
            jsonWriter.write(history, function, domain, range);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterNoHistory.json");
            FunctionHistory testHistory = jsonReader.readHistory();
            assertEquals(0, testHistory.getLength());
            Function testFunction = jsonReader.readFunction();
            assertEquals("", testFunction.getFunctionString());
            assertEquals(0, jsonReader.readDomain());
            assertEquals(0, jsonReader.readRange());

        } catch (IOException e) {
            fail("Exception not expected");
        }
    }

    @Test
    public void testWriteGeneralHistory() {
        try {
            jsonWriter = new JsonWriter("./data/testWriterWithHistory.json");
            FunctionHistory history = new FunctionHistory();
            history.addFunction(new Function("x*x"));
            history.addFunction(new Function("10*x+7"));
            Function function = new Function("-x");

            jsonWriter.open();
            jsonWriter.write(history, function, 80, 24);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterWithHistory.json");
            FunctionHistory testHistory = jsonReader.readHistory();
            assertEquals(2, testHistory.getLength());
            assertEquals("x*x", testHistory.getFunction(0).getFunctionString());
            assertEquals("10*x+7", testHistory.getFunction(1).getFunctionString());
            Function testFunction = jsonReader.readFunction();
            assertEquals("-x", testFunction.getFunctionString());
            assertEquals(80, jsonReader.readDomain());
            assertEquals(24, jsonReader.readRange());

        } catch (IOException e) {
            fail("Exception not expected");
        }

    }
}
