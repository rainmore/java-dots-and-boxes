package au.com.rainmore.game.validators;

import au.com.rainmore.Game;
import au.com.rainmore.game.domains.Action;
import au.com.rainmore.game.domains.Point;
import au.com.rainmore.game.domains.Position;

import java.util.List;
import java.util.Optional;

public class ActionValidator implements Validator<Action> {

    private final Game game;

    public ActionValidator(Game game) {
        this.game = game;
    }

    @Override
    public void validate(Action action, List<Error> errors) {
        validatePlayer(action, errors);
        validatePosition(action, errors);
    }

    private void validatePlayer(Action action, List<Error> errors) {
        Optional<Action> previousAction = game.getPreviousAction();

        if (previousAction.isPresent() && previousAction.get().getPlayer().equals(action.getPlayer())) {
            errors.add(new Error("Player has to play in turn"));
        }
    }

    private void validatePosition(Action action, List<Error> errors) {
        Position position = game.getMatrixService().findPositionBy(action.getPoint());
        if (position.isSet()) {
            errors.add(new Error("This position is already occupied. Please try again."));
        }

        if (position.getPositionType().isDot()) {
            errors.add(new Error("Invalid move. Please try again."));
        } else if (!game.getMatrixService().isPointOnDotRow(action.getPoint())) {
            Point point = Point.of(action.getPoint().getColumn(), action.getPoint().getRow() - 1);

            if (!game.getMatrixService().findPositionBy(point).getPositionType().isDot()) {
                errors.add(new Error("Invalid move. Please try again."));
            }
        }
    }
}
