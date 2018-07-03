package nr;

/*TODO: New File Structure --> address > Address, AddressBuilder, Validator
--> ensure that only Builder can use Constructor (package private)

 */

public final class AddressBuilder implements Builder<Address> {
    private String residence;
    private String postCode;
    private String street;
    private int houseNumber;

    private AddressBuilder(String residence) {
        this.residence = residence;
    }

    public static AddressBuilder residence(String residence) {
        return new AddressBuilder(residence);
    }

    public AddressBuilder withPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }

    public AddressWithStreet inStreet(String street) {
        this.street = street;
        return new AddressWithStreet();
    }

    public final class AddressWithStreet implements Builder<Address> {
        private AddressWithStreet() {
        }

        public AddressBuilder withHouseNumber(int houseNumber) {
            AddressBuilder.this.houseNumber = houseNumber;
            return AddressBuilder.this;
        }

        public Address build() {
            return AddressBuilder.this.build();
        }
    }

    public Address build() {
        return new Address(this.residence, this.postCode, this.street, this.houseNumber);
    }
}