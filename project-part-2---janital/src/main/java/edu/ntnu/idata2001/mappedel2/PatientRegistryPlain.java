package edu.ntnu.idata2001.mappedel2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a hospitals registry over patients.
 *
 * @author janitalillevikroyseth
 * @version 14.04.2021
 */
public class PatientRegistryPlain implements PatientRegistry {

    /**
     * The list over patients in registry.
     */
    private ArrayList<Patient> patients;

    /**
     * Creates an instance of patient registry.
     */
    public PatientRegistryPlain() {
        this.patients = new ArrayList<>();
    }

    @Override
    public void addPatient(Patient patient) throws AddPatientToRegistryException {
        if (!patients.contains(patient) && patient != null) {
            this.patients.add(patient);
        } else{
            throw new AddPatientToRegistryException("This patient is already in the registry");
        }
    }

    @Override
    public void addAll(List<Patient> patients) throws AddPatientToRegistryException {
        for(Patient patient : patients) {
            this.addPatient(patient);
        }
    }

    @Override
    public void changePatientsName(Patient patient) {

    }

    @Override
    public void removePatient(Patient patient) {
        this.patients.remove(patient);
    }

    @Override
    public List<Patient> findPatients(String search) {
        ArrayList<Patient> patientsFound = new ArrayList<>();

        for(Patient patient : this.patients) {
            String fullName = patient.getFirstName() + " "
                    + patient.getLastName();
            if (fullName.contains(search) ||
                    fullName.toLowerCase().contains(search) ||
                    fullName.toUpperCase().contains(search) ||
                    patient.getSocialSecurity().contains(search)) {
                patientsFound.add(patient);
            }
        }

        return patientsFound;
    }

    @Override
    public void close() {
        //Not used for patientregistry with plain java code.
    }

    @Override
    public Iterator<Patient> iterator() {
        return this.patients.iterator();
    }

}
