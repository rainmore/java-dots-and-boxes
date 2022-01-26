package au.com.rainmore.game.renders;

import au.com.rainmore.Game;

public interface Render {

    void render(Game game);

    default void println(String output) {
        System.out.println(output);
    }

    default void print(String output) {
        System.out.print(output);
    }

}
