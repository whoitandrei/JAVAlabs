package model;

import model.interfaces.IScreenCleaner;

public class ScreenCleaner implements IScreenCleaner {
    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error [console cleaning]: " + e.getMessage());
        }
    }
}
