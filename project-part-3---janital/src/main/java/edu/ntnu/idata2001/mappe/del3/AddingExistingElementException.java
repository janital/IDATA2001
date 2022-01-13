package edu.ntnu.idata2001.mappe.del3;

/**
 * Represents an exception that gets thrown when an already existing element
 * is attempted to be added to the registry.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public class AddingExistingElementException extends Exception {

    /**
     * Creates an instance of AddingExistingElementException.
     *
     * @param message the message of the exception.
     */
    public AddingExistingElementException(String message) {
        super(message);
    }
}
