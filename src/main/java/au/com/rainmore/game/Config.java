package au.com.rainmore.game;

public class Config {

    private static final char SYMBOL_DOT             = '*';
    private static final char SYMBOL_LINE_VERTICAL   = '|';
    private static final char SYMBOL_LINE_HORIZONTAL = '—';
    private static final char SYMBOL_QUIT            = 'Q';

    private final int rowSize;
    private final int columnSize;

    public Config(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public char getSymbolDot() {
        return SYMBOL_DOT;
    }

    public char getSymbolLineHorizontal() {
        return SYMBOL_LINE_HORIZONTAL;
    }

    public char getSymbolLineVertical() {
        return SYMBOL_LINE_VERTICAL;
    }

    public char getSymbolQuit() {
        return SYMBOL_QUIT;
    }

}
