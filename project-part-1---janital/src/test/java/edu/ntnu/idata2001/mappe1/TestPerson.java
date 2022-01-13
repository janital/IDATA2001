package edu.ntnu.idata2001.mappe1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPerson {

    @Test
    @DisplayName("Positive test of creating a person")
    public void positiveTestCreatingPerson() {
        Person patient = new Patient("Kari", "Nordmann", "030480 30943");

        if(patient != null) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Negative test of creating a person")
    public void negativeTestCreatingPerson() {
        assertThrows(IllegalArgumentException.class, () ->new Nurse("", null, ""));
    }

    @Test
    @DisplayName("Positive test of changing persons first name")
    public void positiveTestChangingPersonsFirstName() {
        Person doctor = new GeneralPractitioner("Lena", "Nygård", "300178 31232");

        doctor.setFirstName("Lisa");

        assertEquals("Lisa", doctor.getFirstName());
    }

    @Test
    @DisplayName("Negative test of changing persons first name")
    public void negativeTestChangingPersonsFirstName() {
        Patient patient = new Patient("Kari", "Nordmann", "300178 31232");

        patient.setFirstName("");

        assertEquals("Kari", patient.getFirstName());
    }

    @Test
    @DisplayName("Positive test of changing persons last name")
    public void positiveTestChangingPersonsLastName() {
        Person surgeon = new Surgeon("Per", "Nordmann", "300178 31232");

        surgeon.setLastName("Jansen");

        assertEquals("Jansen", surgeon.getLastName());
    }

    @Test
    @DisplayName("Negative test of changing persons last name")
    public void negativeTestChangingPersonsLastName() {
        Patient patient = new Patient("Kari", "Nordmann", "300178 31232");

        patient.setLastName(null);

        assertEquals("Nordmann", patient.getLastName());
    }

    @Test
    @DisplayName("Positive test of changing persons social security number")
    public void positiveTestChangingPersonsSocialSecurityNumber() {
        Patient patient = new Patient("Kari", "Nordmann", "300178 31232");

        patient.setSocialSecurity("300178 31222");

        assertEquals("300178 31222", patient.getSocialSecurity());
    }

    @Test
    @DisplayName("Negative test of changing patients social security number")
    public void negativeTestChangingPatientSocialSecurityNumber() {
        Patient patient = new Patient("Kari", "Nordmann", "300178 31232");

        patient.setSocialSecurity("");

        assertEquals("300178 31232", patient.getSocialSecurity());
    }
}
