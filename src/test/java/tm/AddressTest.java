package tm;

import org.junit.Before;
import org.junit.Test;
import tm.data_model.form_fields.address.Address;
import tm.data_model.form_fields.address.AddressBuilder;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    private Address address;
    private String expectedResidence;
    private String expectedPostCode;
    private String expectedStreet;
    private int expectedHouseNumber;


    @Before
    public void initAddress() {
        this.expectedResidence = "Karlsruhe";
        this.expectedPostCode = "12345";
        this.expectedStreet = "Str";
        this.expectedHouseNumber = 324;
        this.address = AddressBuilder.residence(expectedResidence)
                .withPostCode(expectedPostCode)
                .inStreet(expectedStreet)
                .withHouseNumber(expectedHouseNumber)
                .build();
    }


    @Test
    public void testDestination() {
        assertEquals(expectedResidence, address.getResidence());
    }
    @Test
    public void testPostCode() {
        assertEquals(expectedPostCode, address.getPostCode());
    }
    @Test
    public void testStreet() {
        assertEquals(expectedStreet, address.getStreet());
    }
    @Test
    public void testHouseNumber() {
        assertEquals(expectedHouseNumber, address.getHouseNumber());
    }


}