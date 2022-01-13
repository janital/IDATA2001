package edu.ntnu.idata2001.mappe1;

/**
 * When a person who is not in a list is attempted to be removed, RemoveException will be thrown.
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class RemoveException extends Exception {

    private final static long serialversionUID = 1L;

    /**
     * Takes in the exception message to be thrown.
     * @param message to be thrown
     */
    public RemoveException(String message) {
        super(message);
    }
}
