package nr.data_model;

public class Address {

    private final String residence;
    private final String postCode;
    private final String street;
    private final int houseNumber;

    Address(final String residence, String postCode, String street, int houseNumber) {
        this.residence = residence;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getResidence() {
        return residence;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }
}
