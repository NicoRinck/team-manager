package nr.data_converter;

import java.util.Optional;

public interface UserInputConverter<T> {

    Optional<T> convertInputToEntity();
}
