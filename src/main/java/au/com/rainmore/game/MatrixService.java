package au.com.rainmore.game;

import au.com.rainmore.game.domains.Action;
import au.com.rainmore.game.domains.Point;
import au.com.rainmore.game.domains.Position;
import au.com.rainmore.game.domains.PositionType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MatrixService {

    private final Config config;

    private int rowSize;
    private int columnSize;

    private Position[][] matrix;

    public MatrixService(Config config) {
        this.config = config;
        this.init();
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public Position[][] getMatrix() {
        return matrix;
    }

    public Position findPositionBy(Point point) {
        return findPositionBy(point.getRow(), point.getColumn());
    }

    public Position findPositionBy(int row, int column) {
        return matrix[row][column];
    }

    private void init() {
        columnSize = config.getColumnSize() * 2 - 1;
        rowSize = config.getRowSize() * 2 - 1;

        matrix = new Position[rowSize][columnSize];

        for (int i = 0; i < rowSize; i++) {
            Position[] row = new Position[columnSize];

            if (isPointOnDotRow(i)) {
                setDotsRow(i, row);
            } else {
                setEmptyRow(i, row);
            }

            matrix[i] = row;
        }
    }

    private void setDotsRow(int rowIndex, Position[] row) {
        for (int i = 0; i < row.length; i++) {
            Point point = Point.of(rowIndex, i);
            if (isPointOnDotRow(point.getRow())) {
                row[i] = Position.dot(point);
            } else {
                row[i] = Position.empty(point);
            }
        }
    }

    private void setEmptyRow(int rowIndex, Position[] row) {
        for (int i = 0; i < row.length; i++) {
            Point point = Point.of(rowIndex, i);
            row[i] = Position.empty(point);
        }
    }

    private boolean isPointOnDotRow(int row) {
        return row % 2 == 0;
    }

    public boolean isPointOnDotRow(Point point) {
        return isPointOnDotRow(point.getRow());
    }

    public void updatePosition(Action action) {
        Position position = findPositionBy(action.getPoint());
        if (!position.isSet() && !position.getPositionType().isDot()) {
            position.setSetBy(action.getPlayer());
            if (isPointOnDotRow(action.getPoint().getRow())) {
                position.setPositionType(PositionType.HORIZONTAL);
            } else {
                position.setPositionType(PositionType.VERTICAL);
            }
            updateBox(action);
        }
    }

    public Set<Position> findBoxPositions() {
        // TODO to improve the performance
        Set<Position> boxPositions = new HashSet<>();
        Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).filter(position -> position.getPositionType().isEmpty()).collect(Collectors.toSet()))
                .forEach(boxPositions::addAll);

        return boxPositions;
    }

    public boolean isCompleted() {
        // TODO to improve the performance
        long numberOfUnSetPosition = Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).noneMatch(Position::isSet))
                .filter(result -> result)
                .count();

        return numberOfUnSetPosition == 0;
    }

    private void updateBox(Action action) {
        // TODO to update box by setting the empty position with player
    }


}
