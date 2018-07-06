package nr.data_model.form_fields;

public class PlayerName {

    private String forename;
    private String middleName;
    private final String surname;

    public PlayerName(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public PlayerName(String forename, String middleName, String surname) {
        this.forename = forename;
        this.middleName = middleName;
        this.surname = surname;
    }

    public String getNameString() {
        return surname + ", " + forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getForename() {
        return forename;
    }
}
