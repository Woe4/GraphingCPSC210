package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Coordinate;
import model.Curve;
import model.Function;
import model.FunctionHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// A class to display the function in a console
// THIS IS VERY LOW RESOLUTION SO IT LOOKS BAD

public class TerminalUI {

    private static final String JSON_STORE = "./data/saveFile.json";

    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

    private Terminal terminal = null;
    private TerminalPosition startPosition;
    private TerminalSize terminalSize;

    private int numColumns;
    private int numRows;

    private Scanner sc;
    private Function function;
    private double domain;
    private double range;

    private boolean endApp;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private FunctionHistory history;

    // EFFECTS: gets terminal setup; this includes:
    //          getting terminal sizes and cursor positions
    //          getting the domain and range to graph in
    public TerminalUI() throws IOException {
        terminal = defaultTerminalFactory.createTerminal();

        startPosition = terminal.getCursorPosition();
        terminalSize = terminal.getTerminalSize();

        numColumns = terminalSize.getColumns();
        numRows = terminalSize.getRows();

        sc = new Scanner(System.in);

        endApp = false;
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);

        domain = 0;
        range = 0;
        function = new Function("");
        history = new FunctionHistory();
    }

    // MODIFIES: this
    // EFFECTS: Displays menu and asks for input from user
    public void displayMenu() throws IOException {
        try {
            while (!endApp) {
                terminal.clearScreen();
                System.out.println("What would you like to do? \n"
                        + "a) Input a function \n"
                        + "b) Save function and history \n"
                        + "c) Load function and history \n"
                        + "d) Exit App");
                menu(sc.nextLine());
            }
        } finally {
            terminal.close();
        }

    }

    // REQUIRES: 1 <= int <= 4
    // MODIFIES: this
    // EFFECTS: Decides what to function to do depending on input
    private void menu(String nextChoice) throws IOException {
        if (nextChoice.equals("a")) {
            getUserInputs();
            start();
        } else if (nextChoice.equals("b")) {
            try {
                saveToSaveFile();
            } catch (IOException e) {
                System.out.println("Invalid file: " + JSON_STORE);
            }
        } else if (nextChoice.equals("c")) {
            try {
                loadFromSaveFile();
            } catch (IOException e) {
                System.out.println("Invalid file: " + JSON_STORE);
            }
        } else {
            System.out.println("Closing App \n" + "BYEEEE");
            endApp = true;
        }
    }

    private void loadFromSaveFile() throws IOException {
        function = jsonReader.readFunction();
        history = jsonReader.readHistory();
        domain = jsonReader.readDomain();
        range = jsonReader.readRange();
        System.out.println("Loaded from " + JSON_STORE);
        start();
    }

    private void saveToSaveFile() throws IOException {
        jsonWriter.open();
        jsonWriter.write(history, function, domain, range);
        jsonWriter.close();
        System.out.println("Saved to " + JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: draws axes and curve, and optionally gets definite integral
    public void start() throws IOException {
        try {
            drawAxes();
            drawCurve();

            printHistory();
            getDefiniteIntegral();

        } catch (ScriptException e) {
            System.out.println("Invalid input");

            getUserInputs();
        }
    }

    // EFFECTS: prints all functions in history
    private void printHistory() {
        System.out.println("History: ");
        for (String functionString : history.getFunctionStrings()) {
            System.out.println(functionString);
        }
    }

    // EFFECTS: Reads console input and returns integral value if asked for
    private void getDefiniteIntegral() throws ScriptException {
        System.out.println("Find definite integral? (y/n)");
        if (Objects.equals(sc.nextLine(), "y")) {
            System.out.println("Accuracy: ");
            double accuracy = Double.parseDouble(sc.nextLine());
            System.out.println(function.takeDefiniteIntegral(accuracy, -1 * domain / 2, domain / 2));
        }
    }

    // MODIFIES: this
    // EFFECTS: draws the curve of function
    private void drawCurve() throws ScriptException, IOException {
        double columnStep = numColumns / domain;
        double rowStep = numRows / range;

        terminal.setForegroundColor(TextColor.ANSI.CYAN);

        Curve curve = function.getCurve(columnStep, -1 * domain / 2, domain / 2);

        for (int i = 0; i < curve.getNumberOfCoordinate(); i++) {
            if (Math.abs(curve.getCoordinate(i).getCoordY()) < (range / 2)) {
                int row = (int) Math.round((-1 * curve.getCoordinate(i).getCoordY()) * rowStep + numRows / 2);
                int column = (int) Math.round(i * columnStep * columnStep);

                terminal.setCursorPosition(startPosition.withRelativeColumn(column).withRelativeRow(row));
                terminal.putCharacter('⬤');
                terminal.flush();
            }

        }
    }

    // MODIFIES: this
    // EFFEcTS: Gets user input for function, range and domain
    public void getUserInputs() {
        String inputFunction = getUserFunction();
        function = new Function(inputFunction);
        domain = getUserDomain();
        range = getUserRange();
        history.addFunction(function);
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a String
    private String getUserFunction() {
        System.out.println("Enter function: y = ");
        return sc.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a double
    private double getUserRange() {
        System.out.println("Enter range length:");
        return Double.parseDouble(sc.nextLine());
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a double
    private double getUserDomain() {
        System.out.println("Enter domain length:");
        return Double.parseDouble(sc.nextLine());
    }

    // MODIFIES: this
    // EFFECTS: Draws the y axis and x axis in middle of terminal
    private void drawAxes() throws IOException {
        terminal.setForegroundColor(TextColor.ANSI.WHITE);
        for (int row = 0; row < terminalSize.getRows(); row++) {
            terminal.setCursorPosition(startPosition.withRelativeColumn(numColumns / 2).withRelativeRow(row));
            terminal.putCharacter('█');
            terminal.flush();
        }

        for (int columns = 0; columns < numColumns; columns++) {
            terminal.setCursorPosition(startPosition.withRelativeColumn(columns).withRelativeRow(numRows / 2));
            terminal.putCharacter('█');
            terminal.flush();
        }
    }

    // MODIFIES: this
    // EFFECTS: create and start UI
    public static void main(String[] args) {


        try {
            TerminalUI terminalHandler = new TerminalUI();
            terminalHandler.displayMenu();

        } catch (Exception e) {
            System.out.println("L");
            e.printStackTrace();
        }


    }
}