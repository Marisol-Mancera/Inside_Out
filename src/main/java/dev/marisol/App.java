package dev.marisol;

import dev.marisol.controller.MainController;

public final class App {
    private App() {
    }
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.start();
    }
}