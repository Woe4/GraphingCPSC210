package ui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import model.Coordinate;
import model.Curve;
import model.Function;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

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

    public TerminalUI() throws IOException {
        function = new Function(getUserFunction());
        domain = getUserDomain();
        range = getUserRange();
        terminal = defaultTerminalFactory.createTerminal();

        startPosition = terminal.getCursorPosition();
        terminalSize = terminal.getTerminalSize();

        numColumns = terminalSize.getColumns();
        numRows = terminalSize.getRows();

    }

    private double getUserRange() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter range length:");
        return Double.parseDouble(sc.nextLine());
    }

    private double getUserDomain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter domain length:");
        return Double.parseDouble(sc.nextLine());
    }


    public void start() throws IOException, InterruptedException {
        try {
            drawAxes();
            drawCurve();

            getDefiniteIntegral();

        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }

    }

    private void getDefiniteIntegral() throws ScriptException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Find definite integral? (y/n)");
        if (Objects.equals(sc.nextLine(), "y")) {
            System.out.println("Accuracy: ");
            double accuracy = Double.parseDouble(sc.nextLine());
            System.out.println(function.takeDefiniteIntegral(accuracy, -1 * domain / 2, domain / 2));
        }
    }

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

    private String getUserFunction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter function: y = ");
        return sc.nextLine();
    }

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