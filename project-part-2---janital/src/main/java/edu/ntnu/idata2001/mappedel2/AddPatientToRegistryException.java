package edu.ntnu.idata2001.mappedel2;

/**
 * Represents an exception that will occur when a patient already in a
 * registry is attempted to be added again.
 *
 * @author janitalillevikroyseth
 * @version 01.05.2021
 */
public class AddPatientToRegistryException extends Exception{

    /**
     * Creates an instance of AddPatientToRegistryException that will be
     * thrown.
     * @param message to be thrown.
     */
    public AddPatientToRegistryException(String message) {
        super(message);
    }
}