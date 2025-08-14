package dev.marisol;

import java.util.Scanner;
import dev.marisol.controller.MainController;
import dev.marisol.service.MomentService;
import dev.marisol.view.MainMenuView;

public final class App {
    private App() {
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MomentService service = new MomentService();
        MainMenuView mainMenuView = new MainMenuView(scanner); // MainMenuView necesita un Scanner

        MainController mainController = new MainController(scanner);
        
        mainController.start();
    }
}