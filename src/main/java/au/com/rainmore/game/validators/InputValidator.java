package au.com.rainmore.game.validators;

import au.com.rainmore.Game;

import java.util.List;
import java.util.regex.Pattern;

public class InputValidator implements Validator<String> {

    private Game game;
    private Pattern patternCommand;
    private Pattern patternQuit;

    public InputValidator(Game game) {
        this.game = game;
        patternCommand = buildPatternCommand();
        patternQuit = buildPatternQuit();
    }

    @Override
    public void validate(String s, List<Error> errors) {
        if (!isQuite(s) && !isValidCommand(s.toUpperCase())) {
            errors.add(new Error("Invalid move. Please try again."));
        }
    }

    public boolean isQuite(String s) {
        return patternQuit.matcher(s).matches();
    }

    public boolean isValidCommand(String s) {
       return patternCommand.matcher(s).matches();
    }

    private Pattern buildPatternQuit() {
        String symbol = String.valueOf(game.getConfig().getSymbolQuit());
        String pattern = String.format("[%s%s]{1}", symbol.toUpperCase(), symbol.toLowerCase());
        return Pattern.compile(pattern);
    }

    private Pattern buildPatternCommand() {
        char[] colurmChars = new char[game.getMatrixService().getColumnSize()];
        for (int i = 0; i < colurmChars.length; i++) {
            colurmChars[i] = (char) (65 + i);
        }
        String pattern = String.format("[%s]{1}[0-%d]{1}", String.valueOf(colurmChars), game.getMatrixService().getRowSize() - 1);
        return Pattern.compile(pattern);
    }


}
