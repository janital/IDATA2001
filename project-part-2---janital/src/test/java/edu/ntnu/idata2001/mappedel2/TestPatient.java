package edu.ntnu.idata2001.mappedel2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestPatient {

    @Test
    @DisplayName("Positive test for creating patient")
    public void positiveTestCreatingPatientInstance() {
        assertDoesNotThrow(() -> new Patient("Kari", "Nordmann", "08097340932", null, null));
    }

    @Test
    @DisplayName("Negative test for creating patient")
    public void negativeTestCreatingPatientInstance() {
        assertThrows(IllegalArgumentException.class, () -> new Patient("Ola", "", "03043443243", null, null));
    }

    @Test
    @DisplayName("Positive test for changing patients first name")
    public void positiveTestChangePatientFirstName() {
        Patient patient = new Patient("Ola", "Nordmann", "03038334243", null, null);
        patient.setFirstName("Nils");
        assertEquals("Nils", patient.getFirstName());
    }

    @Test
    @DisplayName("Negative test for changing patients first name")
    public void negativeTestChangePatientFirstName() {
        Patient patient = new Patient("Kari", "Nordmann", "03049432123", null, null);

        patient.setFirstName(null);

        assertEquals("Kari", patient.getFirstName());
    }

    @Test
    @DisplayName("Positive test for changing patients last name")
    public void positiveTestChangePatientLastName() {
        Patient patient = new Patient("Ola", "Nordmann", "03038334243", "Mad Cow", "Dr. Strange");
        patient.setLastName("Rogersen");
        assertEquals("Rogersen", patient.getLastName());
    }

    @Test
    @DisplayName("Negative test for changing patients last name")
    public void negativeTestChangePatientLastName() {
        Patient patient = new Patient("Kari", "Nordmann", "03049432123", "Restless legs", "Doktor Proktor");

        patient.setLastName(null);

        assertEquals("Nordmann", patient.getLastName());
    }

    @Test
    @DisplayName("Positive test for changing patients last name")
    public void positiveTestChangePatientSocialSecurity() {
        Patient patient = new Patient("Ola", "Nordmann", "03038334243", "Diabetes", "Doktor Brille");
        patient.setLastName("Rogersen");
        assertEquals("Rogersen", patient.getLastName());
    }

    @Test
    @DisplayName("Negative test for changing patients last name")
    public void negativeTestChangePatientSocialSecurity() {
        Patient patient = new Patient("Kari", "Nordmann", "03049432123", "Necrotizing Fasciitis", "Dr. House");

        patient.setLastName(null);

        assertEquals("Nordmann", patient.getLastName());
    }
}
