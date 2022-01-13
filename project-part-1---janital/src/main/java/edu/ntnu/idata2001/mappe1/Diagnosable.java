package edu.ntnu.idata2001.mappe1;

/**
 * Makes an object "diagnosable".
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public interface Diagnosable {

    /**
     * Registers a diagnosis on a patient
     * @param diagnosis the diagnosis to be registered
     */
    public void setDiagnosis(String diagnosis);

    /**
     * Removes a diagnosis from a patient
     */
    public void removeDiagnosis();
}
