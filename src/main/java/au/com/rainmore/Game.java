package au.com.rainmore;

import au.com.rainmore.domains.*;
import au.com.rainmore.game.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Game {
    private final Config config;

    private final Player player1;
    private final Player player2;

    private final Score player1Score;
    private final Score player2Score;

    private final List<Action> actions = new ArrayList<>();

    private Position[][] positions;

    public Game(Config config, Player player1, Player player2) {
        this.config = config;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Score = new Score();
        this.player2Score = new Score();

        initPositions();
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

    public Optional<Action> getPreviousAction() {
        int size = getActions().size();
        if (size > 0) {
            return Optional.of(actions.get(size - 1));
        }
        else {
            return Optional.empty();
        }
    }

    public Position[][] getPositions() {
        return positions;
    }

    private void initPositions() {
        int columnSize = getConfig().getColumnSize() * 2 - 1;
        int rowSize = getConfig().getRowSize() * 2 - 1;

        positions = new Position[rowSize][columnSize];

        for (int i = 0; i < rowSize; i++) {
            Position[] row = new Position[columnSize];

            if (i % 2 == 0) {
                setDotsRow(i, row);
            }
            else {
                setEmptyRow(i, row);
            }

            positions[i] = row;
        }
    }

    private void setDotsRow(int rowIndex, Position[] row) {
        for (int i = 0; i < row.length; i++) {
            Point point =  Point.of(rowIndex, i);
            if (i % 2 == 0) {
                row[i] = Position.dot(point);
            }
            else {
                row[i] = Position.empty(point);
            }
        }
    }

    private void setEmptyRow(int rowIndex, Position[] row) {
        for (int i = 0; i < row.length; i++) {
            Point point =  Point.of(rowIndex, i);
            row[i] = Position.empty(point);
        }
    }

    public boolean isCompleted() {
        // TODO to complete the logic
        boolean isCompleted = new Random().nextBoolean();
        return isCompleted;
    }

    public Optional<Player> getWinner() {
        // TODO to complete the logic
        if (isCompleted()) {
            return Optional.of((new Random().nextBoolean()) ? player1 : player2);
        }
        else {
            return Optional.empty();
        }
    }

}
