package nr;

import nr.data_model.Address;
import nr.data_model.AddressBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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