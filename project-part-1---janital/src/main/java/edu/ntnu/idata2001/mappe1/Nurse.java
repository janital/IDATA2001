package edu.ntnu.idata2001.mappe1;

/**
 * Represents an employee whom is a nurse.
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Nurse extends Employee {

    /**
     * Creates an instance of nurse
     * @param firstName first name of the nurse
     * @param lastName last name of the nurse
     * @param socialSecurity social security of the nurse
     */
    public Nurse(String firstName, String lastName, String socialSecurity) {
        super(firstName, lastName, socialSecurity);
    }

    @Override
    public String toString() {
        return super.toString() + "[Job title: " + this.getClass().getSimpleName() + "] \n";
    }
}
