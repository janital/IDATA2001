package edu.ntnu.idata2001.mappe1;

/**
 * Represents an employee whom is a doctor
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
abstract public class Doctor extends Employee {

    /**
     * Creates an instance of Doctor which extends Employee
     * @param firstName first name of the doctor
     * @param lastName last name of the doctor
     * @param socialSecurity social security of the doctor
     */
    protected Doctor(String firstName, String lastName, String socialSecurity) {
        super(firstName, lastName, socialSecurity);
    }

    /**
     * Registers a diagnosis on a patient
     * @param patient patient whom the diagnosis will be registered on
     * @param diagnosis the diagnosis that will be registered
     */
    abstract public void setDiagnosis(Patient patient, String diagnosis);

    /**
     * Removes a diagnosis from a patient
     * @param patient patient who will no longer have a diagnosis
     */
    abstract public void removeDiagnosis(Patient patient);

}
