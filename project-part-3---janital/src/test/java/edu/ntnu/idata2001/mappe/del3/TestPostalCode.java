package edu.ntnu.idata2001.mappe.del3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostalCode {

    @Test
    @DisplayName("Positive test of creating a postal code instance")
    public void positiveTestCreatingPostalCodeInstance() {
        assertDoesNotThrow(() -> new PostalCode("6014", "Ålesund", "1507", "Ålesund", "P"));
    }

    @Test
    @DisplayName("Negative test of creating a postal code instance with blank values")
    public void negativeTestCreatingPostalCodeInstaceWithBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> new PostalCode("", "Ålesund","1507", "", "P"));
    }

    @Test
    @DisplayName("Positive test changing city of postal code")
    public void positiveTestChangingCityOfPostalCode() {
        PostalCode postalCode = new PostalCode("6030", "Ålesund", "1531", "Sula", "P");

        postalCode.setCity("Langevåg");

        assertEquals("Langevåg", postalCode.getCity());
    }

    @Test
    @DisplayName("Negative test changing city of the postal code to blank value")
    public void negativeTestChangingCityOfPostalCodeToBlank() {
        PostalCode postalCode = new PostalCode("6014", "Ålesund", "1507", "Ålesund", "P");

        assertThrows(IllegalArgumentException.class, () -> postalCode.setCity(""));
    }

    @Test
    @DisplayName("Positive test changing municipality key of postalcode")
    public void postiveTestChangingMunicipalityKeyOfPostalCode() {
        PostalCode postalCode = new PostalCode("6030", "Langevåg", "1507", "Sula", "P");

        postalCode.setMunicipalityKey("1531");

        assertEquals("1531", postalCode.getMunicipalityKey());
    }

    @Test
    @DisplayName("Negative test changing municipality key of postalcode to null")
    public void negativeTestChangingMunicipalityKeyOfPostalCodeToNull() {
        PostalCode postalCode = new PostalCode("6030", "Langevåg", "1507", "Sula", "P");

        assertThrows(IllegalArgumentException.class, () -> postalCode.setMunicipalityKey(null));
    }

    @Test
    @DisplayName("Positive test changing municipality of postalcode")
    public void postiveTestChangingMunicipalityOfPostalCode() {
        PostalCode postalCode = new PostalCode("6030", "Langevåg", "1531", "Ålesund", "P");

        postalCode.setMunicipality("Sula");

        assertEquals("Sula", postalCode.getMunicipality());
    }

    @Test
    @DisplayName("Negative test changing municipality of postalcode to null")
    public void negativeTestChangingMunicipalityOfPostalCodeToNull() {
        PostalCode postalCode = new PostalCode("6030", "Langevåg", "1507", "Sula", "P");

        assertThrows(IllegalArgumentException.class, () -> postalCode.setMunicipality(null));
    }

    @Test
    @DisplayName("Positive test for checking equal postalcodes")
    public void positiveTestCheckingEqualPostalCodes() {
        PostalCode postalCode1 = new PostalCode("6030", "Langevåg", "1530", "Sula", "P");
        PostalCode postalCode2 = new PostalCode("6030", "Ålesund", "1507", "Ålesund", "P");

        assertTrue( postalCode1.equals(postalCode2) );
    }
}
