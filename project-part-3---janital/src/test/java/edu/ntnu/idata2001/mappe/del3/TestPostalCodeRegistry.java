package edu.ntnu.idata2001.mappe.del3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPostalCodeRegistry {

    @Test
    @DisplayName("Positive test creating PostalCodeRegistry instance")
    public void positiveTestCreatingPostalCodeRegistry() {
        PostalCodeRegistry postalCodeRegistry = new PostalCodeRegistryPlain();

        assertNotNull(postalCodeRegistry);
    }

    @Test
    @DisplayName("Positive test adding postalcode to postalcode registry")
    public void positiveTestAddingPostalCodeToPostalCodeRegistry() {
        PostalCodeRegistry postalCodeRegistry = new PostalCodeRegistryPlain();

        assertDoesNotThrow(() -> postalCodeRegistry.addPostalCode(new PostalCode("6014", "Ålesund", "1507", "Ålesund", "P")));
    }

    @Test
    @DisplayName("Negative test adding null object to postalcode registry")
    public void negativeTestAddingNullObjectToPostalCodeRegistry() {
        PostalCodeRegistry postalCodeRegistry = new PostalCodeRegistryPlain();

        assertThrows(Exception.class, () -> postalCodeRegistry.addPostalCode(null));
    }

    @Test
    @DisplayName("Negative test adding already existing postalcode to postalcode registry")
    public void negativeTestAddingAlreadyAddedPostalCodeToPostalCodeRegistry() {
        PostalCodeRegistry postalCodeRegistry = new PostalCodeRegistryPlain();

        try {
            postalCodeRegistry.addPostalCode(new PostalCode("6014", "Ålesund", "1507", "Ålesund", "P"));

            assertThrows(Exception.class, () -> postalCodeRegistry.addPostalCode(new PostalCode("6014", "Langevåg", "1530", "Sula", "P")));
        } catch (Exception e) {
            System.err.println("Couldn't prepare for actual test, problem adding general postalcodes.");
            assertFalse(false);
        }
    }
}
