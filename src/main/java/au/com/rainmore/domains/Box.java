package au.com.rainmore.domains;

import java.util.Objects;

public class Box {

    private final Dot dot;
    private final int step;

    private Dot topRight;
    private Dot bottomLeft;
    private Dot bottomRight;

    public Box(Dot dot, int step) {
        this.dot = dot;
        this.step = step;

        init();
    }

    private void init() {
        this.topRight = Dot.of(getTopRight().getX() + getStep(), getTopRight().getY());
        this.bottomLeft = Dot.of(getTopRight().getX(), getTopRight().getY() + getStep());
        this.bottomRight = Dot.of(getTopRight().getX() + getStep(), getTopRight().getY() + getStep());
    }

    public Dot getDot() {
        return dot;
    }

    public int getStep() {
        return step;
    }

    public Dot getTopLeft() {
        return getDot();
    }

    public Dot getTopRight() {
        return topRight;
    }

    public Dot getBottomLeft() {
        return bottomLeft;
    }

    public Dot getBottomRight() {
        return bottomRight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(getDot(), box.getDot()) && Objects.equals(getTopRight(), box.getTopRight()) && Objects.equals(getBottomLeft(), box.getBottomLeft()) && Objects.equals(getBottomRight(), box.getBottomRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDot(), getTopRight(), getBottomLeft(), getBottomRight());
    }
}
