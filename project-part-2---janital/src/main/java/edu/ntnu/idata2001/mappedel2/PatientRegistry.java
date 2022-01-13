package edu.ntnu.idata2001.mappedel2;

import java.util.Iterator;
import java.util.List;

/**
 * Represents the interface for patientregistry.
 */
public interface PatientRegistry extends Iterable<Patient> {

    /**
     * Adds the given patient to the patient registry.
     *
     * @param patient patient to be added to the patient registry.
     */
    void addPatient(Patient patient) throws AddPatientToRegistryException;

    /**
     * Adds a list of patients to the patient registry.
     *
     * @param patients the list of patients to be added to the registry.
     */
    void addAll(List<Patient> patients) throws AddPatientToRegistryException;

    /**
     * Set patients first name.
     *
     * @param patient patient who's first name will be set.
     */
    void changePatientsName(Patient patient);

    /**
     * Removes the given patient from the pasient registry.
     *
     * @param patient patient to be removed from the patient registry
     */
    void removePatient(Patient patient);

    /**
     * Iterates over the patientregistry and searches for patients
     * who has a social security number, first name or last name that
     * matches the given String. Returns the patients found in a List.
     *
     * @param search the String to search for patients with mathcing name or ssn.
     * @return returns a list with patients matching the given String.
     */
    List<Patient> findPatients(String search);


    /**
     * Closes the entitymanager and the entitymanagerfactory.
     */
    void close();

    @Override
    Iterator<Patient> iterator();

}

