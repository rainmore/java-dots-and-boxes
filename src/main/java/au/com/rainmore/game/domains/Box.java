package au.com.rainmore.game.domains;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Box {

    private final Position top;
    private final Position right;
    private final Position bottom;
    private final Position left;

    public Box(Position top, Position right, Position bottom, Position left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Position getTop() {
        return top;
    }

    public Position getLeft() {
        return left;
    }

    public Position getBottom() {
        return bottom;
    }

    public Position getRight() {
        return right;
    }

    private Position[] getPositions() {
        return new Position[] {
            getTop(),
            getRight(),
            getBottom(),
            getLeft()
        };
    }

    public boolean isSet() {
        return Arrays.stream(getPositions()).allMatch(Position::isSet);
    }

    public Point getCenterPoint() {
        int column = getTop().getPoint().getColumn();
        int row = getLeft().getPoint().getRow();
        return Point.of(column, row);
    }

}
