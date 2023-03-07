package persistence;

import model.Function;
import model.FunctionHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// class that reads data from a Json file
// inspiration taken from JsonSerializationDemo program
public class JsonReader {
    private String dataFile;

    // EFFECTS: Creates a new JsonReader object
    public JsonReader(String fileName) {
        dataFile = fileName;
    }

    // EFFECTS: reads history from file and returns it; throws IOException in the case of error during read
    public FunctionHistory readHistory() throws IOException {
        String jsonData = readFile(dataFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonHistory = jsonObject.getJSONArray("history");
        FunctionHistory history = new FunctionHistory();
        for (Object instance : jsonHistory) {
            history.addFunction(new Function((String) instance));
        }
        return history;
    }

    // EFFECTS: reads current function from file and returns it; throws IOException in the case of error during read
    public Function readFunction() throws IOException {
        String jsonData = readFile(dataFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return new Function(jsonObject.getString("function"));
    }

    // EFFECTS: reads the current domain from file and returns it; throws IOException in the case of error during read
    public double readDomain() throws IOException {
        String jsonData = readFile(dataFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getDouble("domain");
    }

    // EFFECTS: reads the current range from file and returns it; throws IOException in the case of error during read
    public double readRange() throws IOException {
        String jsonData = readFile(dataFile);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject.getDouble("range");
    }

    // EFFECTS: returns data file as string; throws IOException in the case of error during read
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: returns the source file name
    public String getDataFile() {
        return dataFile;
    }

}
