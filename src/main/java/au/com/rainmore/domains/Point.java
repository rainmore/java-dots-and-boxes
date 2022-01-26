package au.com.rainmore.domains;

import java.util.Objects;

public class Point {

    private final int  column;
    private final int  row;

    public Point(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public char getColumnName() {
        return (char) (65 + column);
    }

    public int getColumn() {
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

    public static Point of(int column, int row) {
        return new Point(column, row);
    }

    public static Point of(char columnName, int row) {
        int column = ((int) String.valueOf(columnName).toUpperCase().charAt(0)) - 65;
        return of(column, row);
    }

    public static Point of(String s) {
        char column = s.charAt(0);
        int row = Integer.valueOf(String.valueOf(s.charAt(1)));
        return of(column, row);
    }

}
