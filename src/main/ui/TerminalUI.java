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

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// A class to display the function in a console
// THIS IS VERY LOW RESOLUTION SO IT LOOKS BAD

public class TerminalUI {

    private DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

    private Terminal terminal = null;
    private TerminalPosition startPosition;
    private TerminalSize terminalSize;

    private int numColumns;
    private int numRows;

    private Function function;
    private double domain;
    private double range;

    private FunctionHistory history = new FunctionHistory();

    // EFFECTS: gets terminal setup; this includes:
    //          getting the user function
    //          getting terminal sizes and cursor positions
    //          getting the domain and range to graph in
    public TerminalUI() throws IOException {
        terminal = defaultTerminalFactory.createTerminal();

        startPosition = terminal.getCursorPosition();
        terminalSize = terminal.getTerminalSize();

        numColumns = terminalSize.getColumns();
        numRows = terminalSize.getRows();

    }

    // MODIFIES: this
    // EFFECTS: draws axes and curve, gets definite integral
    public void start() throws IOException {
        try {
            drawAxes();
            drawCurve();

            printHistory();
            getDefiniteIntegral();

            restart();

        } catch (IOException | ScriptException e) {
            System.out.println("Invalid input");

            getUserInputs();
        } finally {
            terminal.close();
        }

    }

    // MODIFIES: this
    // EFFECTS: gets another function
    private void restart() throws IOException {
        terminal.clearScreen();
        Scanner sc = new Scanner(System.in);
        System.out.println("New function? (y/n)");
        if (Objects.equals(sc.nextLine(), "y")) {
            getUserInputs();
        }
    }

    // EFFECTS: prints all functions in history
    private void printHistory() {
        System.out.println("History: \n");
        for (String functionString : history.getFunctionStrings()) {
            System.out.println(functionString);
        }
    }

    // EFFECTS: Reads console input and returns integral value if asked for
    private void getDefiniteIntegral() throws ScriptException {
        Scanner sc = new Scanner(System.in);
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
                int row =  (int) Math.round((-1 * curve.getCoordinate(i).getCoordY()) * rowStep + numRows / 2);
                int column = (int) Math.round(i * columnStep * columnStep);

                terminal.setCursorPosition(startPosition.withRelativeColumn(column).withRelativeRow(row));
                terminal.putCharacter('⬤');
                terminal.flush();
            }

        }
    }

    // MODIFIES: this
    // EFFEcTS: Gets user input for function, range and domain
    public void getUserInputs() throws IOException {
        function = new Function(getUserFunction());
        domain = getUserDomain();
        range = getUserRange();
        history.addFunction(function);
        start();
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a String
    private String getUserFunction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter function: y = ");
        return sc.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a double
    private double getUserRange() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter range length:");
        return Double.parseDouble(sc.nextLine());
    }

    // MODIFIES: this
    // EFFECTS: Reads console input and returns that value as a double
    private double getUserDomain() {
        Scanner sc = new Scanner(System.in);
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
}