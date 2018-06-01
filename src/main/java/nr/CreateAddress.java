package nr;

public final class CreateAddress implements Builder<Address> {
    private String residence;
    private String postCode;
    private String street;
    private int houseNumber;

    private CreateAddress(String residence) {
        this.residence = residence;
    }

    public static CreateAddress residence(String residence) {
        return new CreateAddress(residence);
    }

    public CreateAddress withPostCode(String postCode) {
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

        public CreateAddress withHouseNumber(int houseNumber) {
            CreateAddress.this.houseNumber = houseNumber;
            return CreateAddress.this;
        }

        public Address build() {
            return CreateAddress.this.build();
        }
    }

    public Address build() {
        return new Address(this.residence, this.postCode, this.street, this.houseNumber);
    }
}
