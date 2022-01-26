package au.com.rainmore.domains;

import java.util.Objects;

public class Dot {

    private final int x;
    private final int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dot)) return false;
        Dot dot = (Dot) o;
        return getX() == dot.getX() && getY() == dot.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return String.format("Dot(%d, %d)", getX(), getY());
    }

    public static Dot of(int x, int y) {
        return new Dot(x, y);
    }

}
