package au.com.rainmore.game;

import au.com.rainmore.game.domains.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

        for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
            Position[] row = new Position[columnSize];

            if (isPointOnDotRow(rowIndex)) {
                setDotsRow(rowIndex, row);
            } else {
                setEmptyRow(rowIndex, row);
            }

            matrix[rowIndex] = row;
        }
    }

    private void setDotsRow(int rowIndex, Position[] row) {
        for (int column = 0; column < row.length; column++) {
            Point point = Point.of(column, rowIndex);
            if (column % 2 == 0) {
                row[column] = Position.dot(point);
            } else {
                row[column] = Position.empty(point);
            }
        }
    }

    private void setEmptyRow(int rowIndex, Position[] row) {
        for (int column = 0; column < row.length; column++) {
            Point point = Point.of(column, rowIndex);
            row[column] = Position.empty(point);
        }
    }

    private boolean isPointOnDotRow(int row) {
        return row % 2 == 0;
    }

    public boolean isPointOnDotRow(Point point) {
        return isPointOnDotRow(point.getRow());
    }

    public void process(Action action) {
        updatePosition(action);
        updateBox(action);
    }

    private void updatePosition(Action action) {
        Position position = findPositionBy(action.getPoint());
        if (!position.isSet() && !position.getPositionType().isDot()) {
            position.setSetBy(action.getPlayer());
            if (isPointOnDotRow(action.getPoint().getRow())) {
                position.setPositionType(PositionType.HORIZONTAL);
            } else {
                position.setPositionType(PositionType.VERTICAL);
            }
        }
    }

    public long countUnsetPositions() {
        return Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).filter(position -> !position.getPositionType().isDot() && !position.isSet()).count())
                .reduce(0L, Long::sum);
    }

    public boolean isCompleted() {
        return countUnsetPositions() == 0L;
    }

    public long countBoxPositionsBy(Player player) {
        return Arrays.stream(matrix)
                .map(row -> Arrays.stream(row).filter(position -> position.getPositionType().isEmpty() && position.getSetBy() == player).count())
                .reduce(0L, Long::sum);
    }

    private void updateBox(Action action) {
        Position position = findPositionBy(action.getPoint());
        Set<Box> boxes = buildBoxesBy(position);

        boxes.stream()
                .filter(Box::isSet)
                .map(Box::getCenterPoint)
                .map(this::findPositionBy)
                .filter(centerPosition -> !centerPosition.isSet())
                .forEach(centerPosition -> centerPosition.setSetBy(action.getPlayer()));
    }

    private Set<Box> buildBoxesBy(Position position) {
        Set<Box> boxes = new HashSet<>();
        int row = position.getPoint().getRow();
        int column = position.getPoint().getColumn();
        switch (position.getPositionType()) {
            case HORIZONTAL:
                if (row < rowSize - 1) {
                    // build box below the point
                    boxes.add(new Box(
                            matrix[row][column], // 0 1
                            matrix[row + 1][column + 1],
                            matrix[row + 2][column],
                            matrix[row + 1][column - 1]
                    ));
                }
                if (row > 0) {
                    // build box above the point
                    boxes.add(new Box(
                            matrix[row - 2][column],
                            matrix[row - 1][column + 1],
                            matrix[row][column],
                            matrix[row - 1][column - 1]
                    ));
                }
                break;
            case VERTICAL:
                if (column < columnSize - 1) {
                    // build box at right side of the point
                    boxes.add(new Box(
                            matrix[row - 1][column + 1],
                            matrix[row][column + 2],
                            matrix[row + 1][column + 1],
                            matrix[row][column]
                    ));
                }
                if (column > 0) {
                    // build box at left side of the point
                    boxes.add(new Box(
                            matrix[row - 1][column - 1],
                            matrix[row][column],
                            matrix[row + 1][column - 1],
                            matrix[row][column - 2]
                    ));
                }
                break;
            default:
                // do nothing
                break;
        }
        return boxes;
    }

}
