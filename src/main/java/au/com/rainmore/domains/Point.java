package au.com.rainmore.domains;

import java.util.Locale;
import java.util.Objects;

public class Point {

    private final char column;
    private final int  row;

    public Point(char x, int y) {
        this.column = x;
        this.row = y;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return getColumn() == point.getColumn() && getRow() == point.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColumn(), getRow());
    }

    public static Point of(char x, int y) {
        return new Point(x, y);
    }

    public static Point of(int x, int y) {
        return of((char) (65 + x), y);
    }

    public static Point of(String s) {
        char column = s.toUpperCase().charAt(0);
        int row = s.charAt(1);
        return of(column, row);
    }

}
