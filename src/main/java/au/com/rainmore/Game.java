package au.com.rainmore;

import au.com.rainmore.game.domains.*;
import au.com.rainmore.game.Config;

import java.util.*;
import java.util.stream.Collectors;

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

    public Game addAction(Action action) {
        Optional<Action> previousAction = getPreviousAction();

        if (previousAction.isPresent() && previousAction.get().getPlayer().equals(action.getPlayer())) {
            throw new RuntimeException("Player has to play in turn");
        }
        else {
            getActions().add(action);
            updatePosition(action);
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

            if (isPointOnDotRow(i)) {
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
            if (isPointOnDotRow(point.getRow())) {
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

    public Position findPositionBy(Point point) {
        return positions[point.getRow()][point.getColumn()];
    }

    private void updatePosition(Action action) {
        Position position = findPositionBy(action.getPoint());
        if (!position.isSet() && !position.getPositionType().isDot()) {
            position.setSetBy(action.getPlayer());
            if (isPointOnDotRow(action.getPoint().getRow())) {
                position.setPositionType(PositionType.HORIZONTAL);
            }
            else {
                position.setPositionType(PositionType.VERTICAL);
            }
            updateBox(action);
        }
    }

    private void updateBox(Action action) {
        // TODO to update box by setting the empty position with player
    }

    private boolean isPointOnDotRow(int row) {
        return row % 2 == 0;
    }

    public boolean isPointOnDotRow(Point point) {
        return isPointOnDotRow(point.getRow());
    }

    public boolean isCompleted() {
        // TODO to improve the performance
        long numberOfUnSetPosition = Arrays.stream(positions)
                .map(row -> Arrays.stream(row).noneMatch(Position::isSet))
                .filter(result -> result)
                .count();

        return numberOfUnSetPosition == 0;
    }

    public Optional<Player> getWinner() {
        if (isCompleted()) {
            // TODO to improve the performance
            Set<Position> setPositions = new HashSet<>();
            Arrays.stream(positions)
                    .map(row -> Arrays.stream(row).filter(position -> position.getPositionType().isEmpty()).collect(Collectors.toSet()))
                    .forEach(setPositions::addAll);

            long player1Count = setPositions.stream().filter(position -> player1.equals(position.getSetBy())).count();
            long player2Count = setPositions.size() - player1Count;
            // TODO to consider draw situation
            return Optional.of((player1Count > player2Count) ? player1 : player2);
        }
        else {
            return Optional.empty();
        }
    }

}
