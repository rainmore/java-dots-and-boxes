package au.com.rainmore;

import au.com.rainmore.domains.Action;
import au.com.rainmore.domains.Player;
import au.com.rainmore.domains.Score;
import au.com.rainmore.game.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private final Config config;

    private final Player player1;
    private final Player player2;

    private final Score player1Score;
    private final Score player2Score;

    private final List<Action> actions = new ArrayList<>();

    public Game(Config config, Player player1, Player player2) {
        this.config = config;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = new Score();
        this.player2Score = new Score();
    }

    public Config getConfig() {
        return config;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Score getPlayer1Score() {
        return player1Score;
    }

    public Score getPlayer2Score() {
        return player2Score;
    }

    public List<Action> getActions() {
        return actions;
    }

    public Game resetActions() {
        actions.clear();
        return this;
    }

    public Game addAction(Action action) {
        Optional<Action> previousAction = getPreviousAction();

        if (previousAction.isPresent() && previousAction.get().getPlayer().equals(action.getPlayer())) {
            throw new RuntimeException("Player has to play in turn");
        }
        else {
            getActions().add(action);
        }

        return this;
    }

    private Optional<Action> getPreviousAction() {
        int size = getActions().size();
        if (size > 0) {
            return Optional.of(actions.get(size - 1));
        }
        else {
            return Optional.empty();
        }
    }

}
