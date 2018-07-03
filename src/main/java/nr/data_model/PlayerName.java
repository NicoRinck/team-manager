package nr.data_model;

public class PlayerName {

    private String forename;
    private final String surname;

    public PlayerName(String forename, String surname) {
        this.forename = forename;
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
