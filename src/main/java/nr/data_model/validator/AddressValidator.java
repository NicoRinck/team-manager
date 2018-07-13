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

    public static String getPostCodeErrorMessage(String postCode) {
        if (postCode.length() != 5) {
            return "Postleitzahl besteht aus 5 Stellen";
        }
        if (isInPostCodeRange(Integer.valueOf(postCode))) {
            return "Die Postleitzahl ist ungültig";
        }
        return "";
    }
}
