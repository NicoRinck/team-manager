package tm.data_model.validator;

public class NameStringValidator {

    public static String getErrorMessage(String value) {
        if (!consistOfValidCharacter(value)) {
            return "ungültiges Zeichen!";
        }
        if (!hasEnoughCharacter(value)) {
            return "mindestens 2 Zeichen\nnotwendig!";
        }
        if (!startsWithUppercase(value)) {
            return "Name muss mit einem\nGroßbuchstaben beginnen!";
        }
        return "";
    }

    public static boolean isValidNameString(String value) {
        return consistOfValidCharacter(value) && startsWithUppercase(value) && hasEnoughCharacter(value);
    }

    public static boolean consistOfValidCharacter(String value) {
        return value.matches("[^0-9\\^\\§\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\+\\=\\[\\{\\]\\}\\|\\\\\\<\\,\\>\\?\\/\\\"\\;\\:°\\_]*");
    }

    public static boolean startsWithUppercase(String value) {
        if (value.length()> 0) {
            return Character.isUpperCase(value.trim().charAt(0));
        }
        return false;
    }

    public static boolean hasEnoughCharacter(String value) {
        return (value.length() > 1);
    }
}
