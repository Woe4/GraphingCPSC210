package ui;


import model.Function;
import model.FunctionHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

// Represents a graphical user interface for graphing
public class SwingGUI {

    private static final String JSON_STORE = "./data/saveFile.json";


    private Function function;
    private double domain;
    private double range;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private FunctionHistory history;

    private JFrame frame;
    private JPanel inputPanel;
    private JPanel menuPanel;
    private GraphPanel graphPanel;

    private JTextField textField;
    private JTextArea textArea;

    private JButton saveButton;
    private JButton loadButton;
    private JButton clearHistoryButton;
    private JButton exitButton;

    // EFFECTS: creates a new GUI
    public SwingGUI() {
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        history = new FunctionHistory();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Graphing Project");
        frame.setVisible(true);

        setupMenuPanel();

        setupInputPanel();

        graphPanel = new GraphPanel();
        graphPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        frame.add(graphPanel, BorderLayout.WEST);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(menuPanel, BorderLayout.EAST);
        frame.pack();

    }

    // MODIFIES: this
    // EFFECTS:
    private void setupMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.setLayout(new GridLayout(0, 1));
        menuPanel.setPreferredSize(new Dimension(200, 800));

        setupSaveButton();
        setupLoadButton();
        setupClearHistoryButton();

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        menuPanel.add(exitButton);
    }

    // MODIFIES: this
    // EFFECTS:
    private void setupInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new GridLayout(0, 1));
        inputPanel.setPreferredSize(new Dimension(200, 800));

        textField = new JTextField(20);
        textField.addActionListener(e -> {
            String text = textField.getText();
            textArea.append(text + "\n");
            textField.selectAll();
            function = new Function(text);
            history.addFunction(function);

            graphPanel.repaint();
        });
        textField.selectAll();
        textField.setPreferredSize(new Dimension(200, 100));

        textArea = new JTextArea(40, 20);
        textArea.setEditable(false);
        textArea.setFont(Font.getFont("Monospaced"));

        inputPanel.add(textArea);
        inputPanel.add(textField);
    }

    // MODIFIES: this
    // EFFECTS:
    private void setupSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                saveToSaveFile();
            } catch (IOException exception) {
                textArea.append("Save failed: " + JSON_STORE);
            }
        });
        menuPanel.add(saveButton);
    }

    // MODIFIES: this
    // EFFECTS:
    private void setupLoadButton() {
        loadButton = new JButton("Load");
        loadButton.addActionListener(e -> {
            try {
                loadFromSaveFile();
            } catch (IOException exception) {
                textArea.append("Load failed: " + JSON_STORE);
            }
        });
        menuPanel.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS:
    private void setupClearHistoryButton() {
        clearHistoryButton = new JButton("Clear History");
        clearHistoryButton.addActionListener(e -> {
            history.clearHistory();
            textArea.setText(null);
            textField.setText(null);
        });
        menuPanel.add(clearHistoryButton);
    }

    // MODIFIES: this
    // EFFECTS:
    private void drawGraph() {
        try {
            graphPanel.repaint();
        } catch (Exception e) {
            textArea.append("Input error" + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS:
    private void saveToSaveFile() throws IOException {
        jsonWriter.open();
        jsonWriter.write(history, function, domain, range);
        jsonWriter.close();
        textArea.append("Saved to " + JSON_STORE + "\n");
    }

    // MODIFIES: this
    // EFFECTS: load from save file and draw graph
    private void loadFromSaveFile() throws IOException {
        function = jsonReader.readFunction();
        history = jsonReader.readHistory();
        domain = jsonReader.readDomain();
        range = jsonReader.readRange();
        for (String s : history.getFunctionStrings()) {
            textArea.append(s + "\n");
        }
        drawGraph();
        textArea.append("Loaded from " + JSON_STORE + "\n");
    }

}
