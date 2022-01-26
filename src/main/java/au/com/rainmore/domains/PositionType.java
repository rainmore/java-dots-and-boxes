package au.com.rainmore.domains;

public enum PositionType {
    DOT,
    EMPTY,
    HORIZONTAL,
    VERTICAL
    ;

    public boolean isDot() {
        return DOT == this;
    }

    public boolean isEmpty() {
        return EMPTY == this;
    }

    public boolean isHorizontal() {
        return HORIZONTAL == this;
    }

    public boolean isVertical() {
        return VERTICAL == this;
    }

}
