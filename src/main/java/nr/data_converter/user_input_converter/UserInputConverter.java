package nr.data_converter.user_input_converter;

import java.util.Optional;

public interface UserInputConverter<T> {

    Optional<T> convertInputToEntity();
}
