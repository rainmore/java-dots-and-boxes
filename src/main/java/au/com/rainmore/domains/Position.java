package au.com.rainmore.domains;

public class Position {

    private Point point;
    private PositionType positionType;

    public Position(Point point, PositionType positionType) {
        this.point = point;
        this.positionType = positionType;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public Point getPoint() {
        return point;
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

}
