package nr.data_model.validator;

public class NameStringValidator implements Validator<String> {

    @Override
    public String getErrorMessage(String value) {
        if (!consistOfValidCharacter(value)) {
            return "ungültiges Zeichen!";
        }
        if (!hasEnoughCharacter(value)) {
            return "mindestens 2 Zeichen!";
        }
        if (!startsWithUppercase(value)) {
            return "Name muss mit einem Großbuchstaben beginnen!";
        }
        return "";
    }

    public static boolean isValidNameString(String value) {
        return consistOfValidCharacter(value) && startsWithUppercase(value) && hasEnoughCharacter(value);
    }

    public static boolean consistOfValidCharacter(String value) {
        return value.matches("[^0-9\\^\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\+\\=\\[\\{\\]\\}\\|\\\\\\<\\,\\>\\?\\/\\\"\\;\\:°\\_]*");
    }

    public static boolean startsWithUppercase(String value) {
        return Character.isUpperCase(value.trim().charAt(0));
    }

    public static boolean hasEnoughCharacter(String value) {
        return (value.length() > 1);
    }
}
