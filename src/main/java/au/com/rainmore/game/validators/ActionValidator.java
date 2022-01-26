package au.com.rainmore.game.validators;

import au.com.rainmore.Game;
import au.com.rainmore.domains.Action;

import java.util.List;
import java.util.Optional;

public class ActionValidator implements Validator<Action> {

    private Game game;

    public ActionValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(Action action, List<Error> errors) {
        validatePlayer(action, errors);
    }

    private void validatePlayer(Action action, List<Error> errors) {
        Optional<Action> previousAction = game.getPreviousAction();

        if (previousAction.isPresent() && previousAction.get().getPlayer().equals(action.getPlayer())) {
            errors.add(new Error("Player has to play in turn"));
        }
    }

    private void validatePoint(Action action, List<Error> errors) {
        // TODO
    }
}
