package au.com.rainmore.game.validators;

import java.util.List;

public interface Validator<T> {

    void validate(T t, List<Error> errors);

}
