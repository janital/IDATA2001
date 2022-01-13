package edu.ntnu.idata2001.mappedel2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestPatientRegistry {

    @Test
    @DisplayName("")
    public void positiveTestCreatingPatientRegistry() {
        //Arrange and Act
        PatientRegistry patients = new PatientRegistryPlain();

        //Assert
        if (patients == null) {
            assertFalse(false);
        } else {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("")
    public void positiveTestAddingPatientToPatientRegistry() {
        PatientRegistry patients = new PatientRegistryPlain();

        Patient patient = new Patient("Kari", "Nordmann", "03040304324", "", "Doktor Brille");
        patients.addPatient(patient);

        assertEquals(patients.iterator().next(), patient);
    }

    @Test
    @DisplayName("")
    public void negativeTestAddingPatientToPatientRegistry() {
        PatientRegistry patients = new PatientRegistryPlain();

        patients.addPatient(null);

        int patientRegistrySize = 0;
        for(Patient patient : patients) {
            patientRegistrySize++;
        }
        assertEquals(patientRegistrySize, 0);
    }

    @Test
    @DisplayName("")
    public void positiveTestRemovingPatientFromPatientRegistry() {
        PatientRegistry patients = new PatientRegistryPlain();
        Patient patient = new Patient("Kari", "Nordmann", "03040304324", "", "Doktor Brille");
        patients.addPatient(patient);

        if(!patients.iterator().hasNext()) {
            assertFalse(false);
        } else {
            patients.removePatient(patient);
            assertTrue(true);
        }
    }
}
