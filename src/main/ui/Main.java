package ui;

import model.FunctionHistory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // create and start

        try {
            TerminalUI terminalHandler = new TerminalUI();
            terminalHandler.getUserInputs();

        } catch (Exception e) {
            System.out.println("L");
            e.printStackTrace();
        }


    }
}
