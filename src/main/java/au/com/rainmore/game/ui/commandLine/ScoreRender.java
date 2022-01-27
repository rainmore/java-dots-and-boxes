package au.com.rainmore.game.ui.commandLine;

import au.com.rainmore.Game;

public class ScoreRender implements Render {

    private String template = "SCORE %s: %d, %s: %d";

    @Override
    public void render(Game game) {
        String output = String.format(template,
                game.getPlayer1(),
                game.getPlayer1Score().getScore(),
                game.getPlayer2(),
                game.getPlayer2Score().getScore());
        println(output);
    }
}
