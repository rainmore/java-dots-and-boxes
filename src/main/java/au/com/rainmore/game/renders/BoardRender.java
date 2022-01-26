package au.com.rainmore.game.renders;

import au.com.rainmore.Game;

public class BoardRender implements Render {

    private String rowTemplate = "%s %s";

    private ScoreRender scoreRender = new ScoreRender();

    private Game game;

    private int columnSize;
    private int rowSize;

    private void setGame(Game game) {
        this.game = game;
        this.columnSize = game.getConfig().getColumnSize() * 2 - 1;
        this.rowSize = game.getConfig().getRowSize() * 2 - 1;
    }

    @Override
    public void render(Game game) {
        setGame(game);
        println(buildHeaderRow());
        renderRows();
        scoreRender.render(game);
    }

    private void renderRows() {
        for (int i = 0; i < rowSize; i++) {
            if (i % 2 == 0) {
                println(buildDotsRow(i));
            }
            else {
                println(buildRow(i));
            }
        }
    }

    private String buildHeaderRow() {
        char index = ' ';
        char[] row = new char[columnSize];
        for (int i = 0; i < row.length; i++) {
            row[i] = (char) (65 + i);
        }
        return String.format(rowTemplate, index, String.valueOf(row));
    }

    private String buildDotsRow(int rowIndex) {
        char[] row = new char[columnSize];
        for (int i = 0; i < row.length; i++) {
            if (i % 2 == 0) {
                row[i] = game.getConfig().getSymbolDot();
            }
            else {
                // TODO to build row —
                row[i] = ' ';
            }

        }
        return String.format(rowTemplate, rowIndex, String.valueOf(row));
    }

    private String buildRow(int rowIndex) {
        char[] row = new char[columnSize];
        for (int i = 0; i < row.length; i++) {
            if (i % 2 != 0) {
                row[i] = ' ';
            }
            else {
                // TODO to build row —
                row[i] = ' ';
            }
        }
        return String.format(rowTemplate, rowIndex, String.valueOf(row));
    }

}
