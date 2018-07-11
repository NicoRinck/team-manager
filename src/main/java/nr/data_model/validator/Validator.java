package nr.data_model.validator;

public interface Validator<T> {

    String getErrorMessage(T value);

}
