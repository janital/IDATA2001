package edu.ntnu.idata2001.mappe1;

/**
 * Represents a patient. A patient is a Person who is diagnosable.
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Patient extends Person implements Diagnosable {

    private String diagnosis;

    /**
     * Creates an instance of patient.
     * @param firstName first name of the patient
     * @param lastName last name of the patient
     * @param socialSecurity social security of the patient
     */
    protected Patient(String firstName, String lastName, String socialSecurity) {
        super(firstName, lastName, socialSecurity);
    }

    /**
     * Returns information about the patient as String
     * @return information about the patient as String
     */
    public String toString() {

        return super.toString() + "[Diagnosis: " + this.diagnosis + "] \n";
    }

    /**
     * Returns a diagnosis on a patient
     * @return diagnosis on a patient
     */
    protected String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public void setDiagnosis(String diagnosis) {
        if(diagnosis != null && !diagnosis.isBlank()) {
            this.diagnosis = diagnosis;
        }
    }

    @Override
    public void removeDiagnosis() { this.diagnosis = null; }
}
