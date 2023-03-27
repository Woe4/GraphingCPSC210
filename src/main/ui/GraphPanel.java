package ui;

import com.googlecode.lanterna.TextColor;
import model.Curve;
import model.Function;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

// Represents a panel that displays the graph of the function
public class GraphPanel extends JPanel {

    private static final int DEFAULT_DOMAIN = 20;
    private static final int PREFERRED_SIZE = 800;
    private int numColumns;
    private int numRows;
    private double domain;
    private double range;
    private Function function;

    private static final Color drawColour = Color.GREEN;

    // EFFECTS: creates an empty graph panel of size 800x800 pixels
    public GraphPanel() {
        setPreferredSize(new Dimension(PREFERRED_SIZE, PREFERRED_SIZE));
        setBackground(Color.WHITE);

        numColumns = PREFERRED_SIZE;
        numRows = PREFERRED_SIZE;

        domain = DEFAULT_DOMAIN;
        range = DEFAULT_DOMAIN;
        function = null;
    }

    // MODIFIES: this
    // EFFECTS: draws the graph
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(drawColour);
        drawAxes(g);
        if (function != null) {
            drawCurve(g);
        }
    }

    // MODIFIES: this
    // EFFECTS: draws the curve
    private void drawCurve(Graphics g) {
        g.setColor(Color.PINK);
        double columnStep = domain / numColumns;
        double rowStep = range / numRows;

        Curve curve = function.getCurve(columnStep, -1 * domain / 2, domain / 2);

        int row1;
        int column1;
        int row2;
        int column2;

        for (int i = 0; i < curve.getNumberOfCoordinate() - 1; i++) {
            if (Math.abs(curve.getCoordinate(i).getCoordY()) <= (range / 2)
                    && Math.abs(curve.getCoordinate(i + 1).getCoordY()) <= (range / 2)) {
                row1 = (int) Math.round((-1 * curve.getCoordinate(i).getCoordY()) / rowStep + numRows / 2.0);
                column1 = i;
                row2 = (int) Math.round((-1 * curve.getCoordinate(i + 1).getCoordY()) / rowStep + numRows / 2.0);
                column2 = i + 1;


                g.drawLine(column1, row1, column2, row2);
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: draws the axes based on domain and range
    private void drawAxes(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, PREFERRED_SIZE / 2, PREFERRED_SIZE, 2);
        g.fillRect(PREFERRED_SIZE / 2, 0, 2, PREFERRED_SIZE);
        g.drawString("0", PREFERRED_SIZE / 2 + 2, PREFERRED_SIZE / 2);
    }

    // MODIFIES: this
    // EFFECTS: sets domain
    public void setDomain(double domain) {
        this.domain = domain;
    }

    // MODIFIES: this
    // EFFECTS: sets range
    public void setRange(double range) {
        this.range = range;
    }

    // MODIFIES: this
    // EFFECTS: sets function
    public void setFunction(Function f) {
        function = f;
    }

    public double getDomain() {
        return domain;
    }

    public double getRange() {
        return range;
    }





}
