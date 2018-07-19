package nr.data_model.validator;

public class AddressValidator {

    public static boolean validHouseNumber(int houseNumber) {
        return houseNumber > 0 && houseNumber < 10000;
    }

    public static boolean validPostCode(String postCode) {
        try {
            return postCode.length() == 5 && isInPostCodeRange(Integer.valueOf(postCode));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isInPostCodeRange(int value) {
        return value >= 1000 && value < 100000;
    }

    public static String getPostCodeErrorMessage(String postCode) {
        if (postCode.length() != 5) {
            return "Postleitzahl muss aus\n5 Stellen bestehen!";
        }
        if (!isInPostCodeRange(Integer.valueOf(postCode))) {
            return "Die Postleitzahl ist ungültig!";
        }
        return "";
    }
}
