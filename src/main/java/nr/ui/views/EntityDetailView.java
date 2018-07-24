package nr.ui.views;

import nr.data_model.entities.Entity;
import nr.data_model.form_fields.address.Address;

public interface EntityDetailView<T extends Entity> {

    void showDetailView(T entity);

    void updateDetailView();

    static String getAddressString(Address address) {
        if (address == null) {
            return "-";
        }
        String result = "";
        if (address.getPostCode() != null) {
            result = address.getPostCode();
        }
        if (address.getResidence() != null && !address.getResidence().equals("")) {
            result += " " + address.getResidence();
        }
        if (address.getStreet() != null && !address.getStreet().equals("")) {
            result += ", " + address.getStreet();
        }
        if (address.getHouseNumber() > 0) {
            result += " " + address.getHouseNumber();
        }
        return result;
    }
}
