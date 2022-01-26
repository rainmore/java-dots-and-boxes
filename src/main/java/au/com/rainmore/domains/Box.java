package au.com.rainmore.domains;

import java.util.Objects;

public class Box {

    private Position top;
    private Position right;
    private Position bottom;
    private Position left;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(getTop(), box.getTop()) && Objects.equals(getLeft(), box.getLeft()) && Objects.equals(getBottom(), box.getBottom()) && Objects.equals(getRight(), box.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTop(), getLeft(), getBottom(), getRight());
    }
}
