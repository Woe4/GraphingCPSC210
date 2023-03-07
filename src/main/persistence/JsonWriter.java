package persistence;

import model.Function;
import model.FunctionHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// class that represents writing data from current state of program to Json file
// inspiration taken from JsonSerializationDemo program
public class JsonWriter {
    private static final int INDENT = 4;
    private PrintWriter writer;
    private String dataFile;

    // EFFECTS: creates new JsonWriter that writes to fileName
    public JsonWriter(String fileName) {
        dataFile = fileName;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(dataFile));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void stringToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of history, function, domain and range to file
    public void write(FunctionHistory history, Function function, double domain, double range) {
        JSONObject json = new JSONObject();
        JSONArray jsonArrayHistory = new JSONArray(history.getFunctionStrings());
        json.put("history", jsonArrayHistory);
        json.put("domain", domain);
        json.put("range", range);
        json.put("function", function.getFunctionString());
        stringToFile(json.toString(INDENT));
    }

    // EFFECTS: returns datafile as string
    public String getDataFile() {
        return dataFile;
    }


}
