package nr;

public class PlayerName {

    private final String forename;
    private final String surname;

    public PlayerName(String forename, String surname) {
        this.forename = forename;
        this.surname = surname;
    }

    public String getNameString() {
        return surname + ", " + forename;
    }

}
