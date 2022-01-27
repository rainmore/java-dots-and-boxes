package au.com.rainmore.game.ui.commandLine;

import au.com.rainmore.Game;
import au.com.rainmore.game.domains.Position;

public class BoardRender implements Render {

    private String rowTemplate = "%s %s";

    private ScoreRender scoreRender = new ScoreRender();

    private Game game;

    private int columnSize;
    private int rowSize;

    private void setGame(Game game) {
        this.game = game;

        this.rowSize = game.getMatrixService().getRowSize();
        this.columnSize = game.getMatrixService().getColumnSize();
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
            println(buildRow(i));
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

    private String buildRow(int rowIndex) {
        char[] row = new char[columnSize];
        for (int i = 0; i < columnSize; i++) {
           row[i] = buildPositionChar(game.getMatrixService().findPositionBy(rowIndex, i));
        }
        return String.format(rowTemplate, rowIndex, String.valueOf(row));
    }

    private char buildPositionChar(Position position) {
        char s = ' ';
        switch (position.getPositionType()) {
            case DOT:
                s = game.getConfig().getSymbolDot();
                break;
            case HORIZONTAL:
                s = game.getConfig().getSymbolLineHorizontal();
                break;
            case VERTICAL:
                s = game.getConfig().getSymbolLineVertical();
                break;
            default:
                // Display the name for the box
                if (position.isSet()) {
                    s = position.getSetBy().getName().charAt(0);
                }
                break;
        }
        return s;
    }

}
