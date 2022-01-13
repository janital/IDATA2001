package edu.ntnu.idata2001.mappe1;

/**
 * Represents a doctor whom is a general practitioner
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class GeneralPractitioner extends Doctor{

    /**
     * Creates an instance of doctor who is a general practitioner
     * @param firstName first name of the general practitioner
     * @param lastName last name of the general practitioner
     * @param socialSecurity social security of the general practitioner
     */
    public GeneralPractitioner(String firstName, String lastName, String socialSecurity) {
        super(firstName, lastName, socialSecurity);
    }

    @Override
    public void setDiagnosis(Patient patient, String diagnosis) {

        if(diagnosis != null && !diagnosis.isBlank()) {
            patient.setDiagnosis(diagnosis);
        }
    }

    @Override
    public void removeDiagnosis(Patient patient) {
        patient.removeDiagnosis();
    }

    @Override
    public String toString() {
        return super.toString() + "[Job title: " + this.getClass().getSimpleName() + "] \n";
    }
}
