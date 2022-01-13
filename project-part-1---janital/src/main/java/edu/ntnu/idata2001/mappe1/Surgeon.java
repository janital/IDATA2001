package edu.ntnu.idata2001.mappe1;

/**
 * Represents a doctor whom is a surgeon
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Surgeon extends Doctor{

    /**
     * Creates an instance of surgeon
     * @param firstName first name of the surgeon
     * @param lastName last name of the surgeon
     * @param socialSecurity social security of the surgeon
     */
    public Surgeon(String firstName, String lastName, String socialSecurity) {
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
