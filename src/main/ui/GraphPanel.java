package ui;

import com.googlecode.lanterna.TextColor;
import model.Curve;
import model.Function;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel {

    private static final int DEFAULT_DOMAIN = 20;
    private int numColumns;
    private int numRows;
    private double domain;
    private double range;
    private Function function;

    private static final Color drawColour = Color.GREEN;

    // EFFECTS: creates an empty graph panel of size 800x800 pixels
    public GraphPanel() {
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.WHITE);

        numColumns = 800;
        numRows = 800;

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
        drawCurve(g);
    }

    // MODIFIES: this
    // EFFECTS: draws the curve
    private void drawCurve(Graphics g) {
//        double columnStep = numColumns / domain;
//        double rowStep = numRows / range;
//
//        Curve curve = function.getCurve(columnStep, -1 * domain / 2, domain / 2);
//
//        for (int i = 0; i < curve.getNumberOfCoordinate() - 1; i++) {
//            if (Math.abs(curve.getCoordinate(i).getCoordY()) < (range / 2)
//                    && Math.abs(curve.getCoordinate(i + 1).getCoordY()) < (range / 2)) {
//                int row1 = (int) Math.round((-1 * curve.getCoordinate(i).getCoordY()) * rowStep + numRows / 2);
//                int column1 = (int) Math.round(i * columnStep * columnStep);
//                int row2 = (int) Math.round((-1 * curve.getCoordinate(i + 1).getCoordY()) * rowStep + numRows / 2);
//                int column2 = (int) Math.round((i + 1) * columnStep * columnStep);
//
//                g.drawLine(column1, row1, row2, column2);
//            }
//
//        }
    }

    // MODIFIES: this
    // EFFECTS: draws the axes based on domain and range
    private void drawAxes(Graphics g) {
        // TODO
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




}
