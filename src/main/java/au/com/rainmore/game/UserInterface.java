package au.com.rainmore.game;

import au.com.rainmore.Game;
import au.com.rainmore.domains.Player;
import au.com.rainmore.game.renders.BoardRender;
import au.com.rainmore.game.renders.Render;

import java.util.Scanner;

public class UserInterface implements Render {

    private Game    game;
    private final Scanner scanner;
    private final BoardRender boardRender = new BoardRender();

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    private Game getGame() {
        return game;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void render(Game game) {
        this.setGame(game);

        boolean running = true;

        while (running) {
            printBoard();
            processPlayerInput(game.getPlayer1());
            processPlayerInput(game.getPlayer2());
        }
    }

    private void printBoard() {
        boardRender.render(game);
    }

    private void quit() {
        scanner.close();
        println("Thanks for playing! Goodbye!");
        System.exit(0);
    }

    private boolean isQuitCommand(String command) {
        return String.valueOf(game.getConfig().getSymbolQuit()).equalsIgnoreCase(command);
    }

    private void processPlayerInput(Player player) {
        String template = "Player %s, input a move <column><row> (or '%s' to quit): ";
        print(String.format(template, player, game.getConfig().getSymbolQuit()));

        String input = scanner.next();

        if (isQuitCommand(input)) {
            quit();
        }
        else {
            processInput(input);
        }
    }

    private void processInput(String input) {
        println(input);
        // TODO to process input
    }

    private void sanitizeCommand() {

    }

    private void validateAction() {
        
    }

}
