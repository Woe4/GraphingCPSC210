package ui;

import model.FunctionHistory;

import java.io.IOException;

public class Main {
    // EFFECTS: runs a console based program that asks for a function, graphs it, asks for definite integral
    //          and continually does so until the user ends the program.
    public static void main(String[] args) {
        // create and start

        try {
            TerminalUI terminalHandler = new TerminalUI();
            terminalHandler.displayMenu();

        } catch (Exception e) {
            System.out.println("L");
            e.printStackTrace();
        }


    }
}
