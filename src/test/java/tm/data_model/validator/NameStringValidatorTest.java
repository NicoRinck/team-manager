package tm.data_model.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NameStringValidatorTest {

    private static final String VALID_NAME = "Name";
    private static final String TO_SHORT_NAME = "N";
    private static final String INVALID_CHARACTER_NAME = "Na;d";
    private static final String LOWER_CASE_NAME = "name";

    @Test
    public void isValidNameString_validName() {
        boolean result = NameStringValidator.isValidNameString(VALID_NAME);
        assertTrue(result);
    }

    @Test
    public void consistOfValidCharacter_invalidCharacterName() {
        boolean result = NameStringValidator.consistOfValidCharacter(INVALID_CHARACTER_NAME);
        assertFalse(result);
    }
    @Test
    public void consistOfValidCharacter_validName() {
        boolean result = NameStringValidator.consistOfValidCharacter(VALID_NAME);
        assertTrue(result);
    }

    @Test
    public void startsWithUppercase_lowerCaseName() {
        boolean result = NameStringValidator.startsWithUppercase(LOWER_CASE_NAME);
        assertFalse(result);
    }

    @Test
    public void startsWithUppercase_validName() {
        boolean result = NameStringValidator.startsWithUppercase(VALID_NAME);
        assertTrue(result);
    }

    @Test
    public void hasEnoughCharacter_toShortName() {
        boolean result = NameStringValidator.hasEnoughCharacter(TO_SHORT_NAME);
        assertFalse(result);
    }
    @Test
    public void hasEnoughCharacter_validName() {
        boolean result = NameStringValidator.hasEnoughCharacter(VALID_NAME);
        assertTrue(result);
    }
}