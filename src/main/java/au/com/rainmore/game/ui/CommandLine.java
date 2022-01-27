package au.com.rainmore.game.ui;

import au.com.rainmore.Game;
import au.com.rainmore.game.domains.Action;
import au.com.rainmore.game.domains.Player;
import au.com.rainmore.game.domains.Point;
import au.com.rainmore.game.ui.commandLine.BoardRender;
import au.com.rainmore.game.ui.commandLine.Render;
import au.com.rainmore.game.validators.ActionValidator;
import au.com.rainmore.game.validators.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandLine implements Render {

    private final Scanner     scanner;
    private final BoardRender boardRender = new BoardRender();

    private Game            game;
    private InputValidator  inputValidator;
    private ActionValidator actionValidator;

    public CommandLine(Scanner scanner) {
        this.scanner = scanner;
    }

    private void setGame(Game game) {
        this.game = game;
        this.inputValidator = new InputValidator(game);
        this.actionValidator = new ActionValidator(game);
    }

    @Override
    public void render(Game game) {
        this.setGame(game);

        boolean running = true;

        while (running) {
            printBoard();
            processPlayerInput(getNextPlayer());
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
        String template = "%s, input a move <column><row> (or '%s' to quit): ";
        print(String.format(template, player, game.getConfig().getSymbolQuit()));

        String input = scanner.next();

        List<Error> errors = new ArrayList<>();

        inputValidator.validate(input, errors);

        if (!errors.isEmpty()) {
            errors.forEach(error -> println(error.getMessage()));
            processPlayerInput(player);
        } else if (inputValidator.isQuite(input)) {
            quit();
        } else {
            processAction(player, input);
        }
    }

    private Player getNextPlayer() {
        Optional<Player> previousPlayer = game.getPreviousAction().map(Action::getPlayer);
        if (previousPlayer.isEmpty() ||
                previousPlayer.filter(player -> player.equals(game.getPlayer2())).isPresent()) {
            return game.getPlayer1();
        } else {
            return game.getPlayer2();
        }
    }

    private void processAction(Player player, String input) {
        Action action = new Action(player, Point.of(input));

        List<Error> errors = new ArrayList<>();

        actionValidator.validate(action, errors);

        if (!errors.isEmpty()) {
            errors.forEach(error -> println(error.getMessage()));
            processPlayerInput(player);
        } else {
            game.addAction(action);

            Optional<Player> winner = game.getWinner();
            if (winner.isPresent()) {
                printBoard();
                println("");
                String template = "Game over. %s is the winner!";
                println(String.format(template, winner.get()));
                quit();
            } else {
                // TODO to figure out the draw situation and in valid box situation
                printBoard();
                processPlayerInput(getNextPlayer());
            }
        }
    }

}
