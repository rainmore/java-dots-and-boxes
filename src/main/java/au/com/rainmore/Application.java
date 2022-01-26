package au.com.rainmore;

import au.com.rainmore.domains.Player;
import au.com.rainmore.game.Config;
import au.com.rainmore.game.UserInterface;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rowSize = 4;
        int columnSize = 4;

        Config config = new Config(rowSize, columnSize);
        Player player1 = new Player("1");
        Player player2 = new Player("2");

        Game game = new Game(config, player1, player2);

        UserInterface ui = new UserInterface(scanner);

        ui.render(game);
    }

}
