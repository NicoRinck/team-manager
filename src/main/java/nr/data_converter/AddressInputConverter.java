package nr.data_converter;

import nr.data_model.form_fields.address.Address;
import nr.data_model.form_fields.address.AddressBuilder;
import nr.data_model.validator.AddressValidator;
import nr.data_model.validator.NameStringValidator;

import java.util.Optional;

public class AddressInputConverter implements UserInputConverter<Address> {

    private String residence = "";
    private String postCode = "";
    private String street = "";
    private int houseNumber = 0;

    @Override
    public Optional<Address> convertInputToEntity() {
        AddressBuilder addressBuilder;
        if (!residence.equals("")) {
            addressBuilder = AddressBuilder.residence(residence);
        } else {
            return Optional.empty();
        }
        if (!postCode.equals("")) {
            addressBuilder = addressBuilder.withPostCode(postCode);
        }
        if (!street.equals("")) {
            AddressBuilder.AddressWithStreet addressWithStreet = addressBuilder.inStreet(street);
            if (houseNumber != 0) {
                addressWithStreet.withHouseNumber(houseNumber);
            }
            return Optional.of(addressWithStreet.build());
        }
        return Optional.of(addressBuilder.build());
    }

    public boolean setResidence(String residence) {
        if (NameStringValidator.isValidNameString(residence)) {
            this.residence = residence;
            return true;
        }
        return false;
    }

    public boolean setPostCode(String postCode) {
        if (AddressValidator.validPostCode(postCode)) {
            this.postCode = postCode;
            return true;
        }
        return false;
    }

    public boolean setStreet(String street) {
        if (NameStringValidator.isValidNameString(street)) {
            this.street = street;
            return true;
        }
        return false;
    }

    public boolean setHouseNumber(String houseNumber) {
        int houseNumberAsInt;
        try {
            houseNumberAsInt = Integer.valueOf(houseNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        if (AddressValidator.validHouseNumber(houseNumberAsInt)) {
            this.houseNumber = houseNumberAsInt;
            return true;
        }
        return false;
    }
}