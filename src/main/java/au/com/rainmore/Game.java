package au.com.rainmore;

import au.com.rainmore.game.Config;
import au.com.rainmore.game.MatrixService;
import au.com.rainmore.game.domains.Action;
import au.com.rainmore.game.domains.Player;
import au.com.rainmore.game.domains.Position;
import au.com.rainmore.game.domains.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Game {

    private final Config config;

    private final Player player1;
    private final Player player2;

    private final Score player1Score;
    private final Score player2Score;

    private final List<Action> actions = new ArrayList<>();

    private final MatrixService matrixService;

    public Game(Config config, Player player1, Player player2) {
        this.config = config;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = new Score();
        this.player2Score = new Score();

        this.matrixService = new MatrixService(config);
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

    public void addAction(Action action) {
        Optional<Action> previousAction = getPreviousAction();

        if (previousAction.isPresent() && previousAction.get().getPlayer().equals(action.getPlayer())) {
            throw new RuntimeException("Player has to play in turn");
        } else {
            getActions().add(action);
            updatePosition(action);
        }
    }

    public Optional<Action> getPreviousAction() {
        int size = getActions().size();
        if (size > 0) {
            return Optional.of(actions.get(size - 1));
        } else {
            return Optional.empty();
        }
    }

    public MatrixService getMatrixService() {
        return matrixService;
    }

    private void updatePosition(Action action) {
        getMatrixService().updatePosition(action);
        updateScore();
    }

    private void updateScore() {
        // TODO to improve the performance
        Set<Position> boxPositions = getMatrixService().findBoxPositions();

        long player1Count = boxPositions.stream().filter(position -> player1.equals(position.getSetBy())).count();

        this.player1Score.setScore(player1Count);
        this.player2Score.setScore(boxPositions.size() - player1Count);
    }

    public Optional<Player> getWinner() {
        if (!getMatrixService().isCompleted()) {
            return Optional.empty();
        } else if (this.player1Score.getScore() == this.player2Score.getScore()) {
            return Optional.empty();
        } else {
            return Optional.of((this.player1Score.getScore() > this.player2Score.getScore()) ? player1 : player2);
        }
    }

}
