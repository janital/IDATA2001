package edu.ntnu.idata2001.mappe1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPatient {

    @Test
    @DisplayName("Positive test of patient diagnosed by a general practitioner")
    public void positiveTestPatientDiagnosedByGeneralPractitioner() {
        Doctor generalPractitioner = new GeneralPractitioner("Gregory", "House", "110669 31292");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        generalPractitioner.setDiagnosis(patient, "Stomach ulcers");

        assertEquals("Stomach ulcers", patient.getDiagnosis());
    }

    @Test
    @DisplayName("Negative test of patient diagnosed by a general practitioner")
    public void negativeTestPatientDiagnosedByGeneralPractitioner() {
        Doctor generalPractitioner = new GeneralPractitioner("Gregory", "House", "110669 31292");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        generalPractitioner.setDiagnosis(patient, "Stomach ulcers");
        generalPractitioner.setDiagnosis(patient, "");

        assertEquals("Stomach ulcers", patient.getDiagnosis());
    }

    @Test
    @DisplayName("Positive test of general practitioner removing diagnosis")
    public void positiveTestGeneralPractitionerRemoveDiagnosis() {
        Doctor generalPractitioner = new GeneralPractitioner("Gregory", "House", "110669 31292");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        generalPractitioner.setDiagnosis(patient, "Stomach ulcers");
        generalPractitioner.removeDiagnosis(patient);

        assertEquals(null, patient.getDiagnosis());
    }

    @Test
    @DisplayName("Positive test of patient diagnosed by a surgeon")
    public void positiveTestPatientDiagnosedBySurgeon() {
        Doctor surgeon = new Surgeon("Sarah", "Smith", "290492 40123");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        surgeon.setDiagnosis(patient,"Benign tumors");

        assertEquals("Benign tumors", patient.getDiagnosis());
    }

    @Test
    @DisplayName("Negative test of patient diagnosed by a surgeon")
    public void negativeTestPatientDiagnosedBySurgeon() {
        Doctor surgeon = new Surgeon("Sarah", "Smith", "290492 40123");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        surgeon.setDiagnosis(patient,"Benign tumors");
        surgeon.setDiagnosis(patient,null);

        assertEquals("Benign tumors", patient.getDiagnosis());
    }

    @Test
    @DisplayName("Positive test of surgeon removing diagnosis")
    public void positiveTestSurgeonRemoveDiagnosis() {
        Doctor surgeon = new Surgeon("Sarah", "Smith", "290492 40123");
        Patient patient = new Patient("Jane", "Doe", "120289 39314");

        surgeon.setDiagnosis(patient,"Benign tumors");
        surgeon.removeDiagnosis(patient);

        assertEquals(null, patient.getDiagnosis());
    }
}
