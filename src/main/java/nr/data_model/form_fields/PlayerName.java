package nr.data_model.form_fields;

import nr.data_model.validator.NameStringValidator;

public class PlayerName {

    private final String forename;
    private final String surname;

    public PlayerName(String forename, String surname) throws IllegalArgumentException {
        if (NameStringValidator.isValidNameString(forename) && NameStringValidator.isValidNameString(surname)) {
            this.forename = forename;
            this.surname = surname;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getNameString() {
        return surname + ", " + forename;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }


}
