package edu.ntnu.idata2001.mappe1;

/**
 * Represents a person whom is an employee
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Employee extends Person {

    /**
     * Creates an instance of Employee which extends Person
     * @param firstName first name of the employee
     * @param lastName last name of the employee
     * @param socialSecurity social security of the employee
     */
    public Employee(String firstName, String lastName, String socialSecurity) {
        super(firstName, lastName, socialSecurity);
    }

    /**
     * Returns information about the employee object as String
     * @return information about the employee object as String
     */
    public String toString() {
        return super.toString();
    }
}
