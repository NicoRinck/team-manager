package tm.data_model.form_fields.address;

import tm.data_model.Builder;
import tm.data_model.validator.AddressValidator;
import tm.data_model.validator.NameStringValidator;

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

    public AddressBuilder withPostCode(String postCode) throws IllegalArgumentException {
        if (AddressValidator.validPostCode(postCode)) {
            this.postCode = postCode;
            return this;
        } else throw new IllegalArgumentException();
    }

    public AddressWithStreet inStreet(String street) throws IllegalArgumentException {
        if (NameStringValidator.isValidNameString(street)) {
            this.street = street;
            return new AddressWithStreet();
        } else throw new IllegalArgumentException();
    }

    public final class AddressWithStreet implements Builder<Address> {
        private AddressWithStreet() {
        }

        public AddressBuilder withHouseNumber(int houseNumber) throws IllegalArgumentException {
            if (AddressValidator.validHouseNumber(houseNumber)) {
                AddressBuilder.this.houseNumber = houseNumber;
                return AddressBuilder.this;
            } else throw new IllegalArgumentException();
        }

        public Address build() {
            return AddressBuilder.this.build();
        }
    }

    public Address build() {
        return new Address(this.residence, this.postCode, this.street, this.houseNumber);
    }
}