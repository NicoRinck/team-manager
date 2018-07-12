package nr.data_model.validator;

public class AddressValidator {

    public static boolean validHouseNumber(int houseNumber) {
        return houseNumber > 0 && houseNumber < 10000;
    }

    public static boolean validPostCode(String postCode) {
        return postCode.length() == 5 && isInPostCodeRange(Integer.valueOf(postCode));
    }

    private static boolean isInPostCodeRange(int value) {
        return value >= 1000 && value < 100000;
    }
}
