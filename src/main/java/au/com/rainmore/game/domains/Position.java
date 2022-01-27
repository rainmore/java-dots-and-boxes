package au.com.rainmore.game.domains;

import java.util.Optional;

public class Position {

    private final Point        point;
    private       PositionType type;
    private Player       setBy;

    public Position(Point point, PositionType positionType) {
        this.point = point;
        this.type = positionType;
    }

    public static Position of(Point point, PositionType type) {
        return new Position(point, type);
    }

    public static Position dot(Point point) {
        return of(point, PositionType.DOT);
    }

    public static Position empty(Point point) {
        return of(point, PositionType.EMPTY);
    }

    public static Position horizontal(Point point) {
        return of(point, PositionType.HORIZONTAL);
    }

    public static Position vertical(Point point) {
        return of(point, PositionType.VERTICAL);
    }

    public PositionType getPositionType() {
        return type;
    }

    public void setPositionType(PositionType positionType) {
        this.type = positionType;
    }

    public Point getPoint() {
        return point;
    }

    public Player getSetBy() {
        return setBy;
    }

    public void setSetBy(Player setBy) {
        this.setBy = setBy;
    }

    public boolean isSet() {
        return Optional.ofNullable(setBy).isPresent();
    }

}
