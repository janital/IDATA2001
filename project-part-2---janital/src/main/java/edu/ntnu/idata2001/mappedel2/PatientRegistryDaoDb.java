package edu.ntnu.idata2001.mappedel2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a patient registry. Uses dao db for persisting patients
 * in registry.
 *
 * @author janitalillevikroyseth
 * @version 29.04.2021
 */
public class PatientRegistryDaoDb implements PatientRegistry {

    /**
     * Entity manager factory for this dao db.
     */
    private final EntityManagerFactory emf;

    /**
     * Entity manager for this dao db.
     */
    private final EntityManager em;

    /**
     * Creates an instance of PatientRegistryDaoDb.
     */
    public PatientRegistryDaoDb() {
        this.emf = Persistence.createEntityManagerFactory("st-olavs-register");
        this.em = this.emf.createEntityManager();
    }

    @Override
    public void addPatient(Patient patient) throws AddPatientToRegistryException {
        if (this.em.find(Patient.class, patient.getSocialSecurity()) == null) {
            this.em.getTransaction().begin();
            this.em.persist(patient);
            this.em.getTransaction().commit();
        } else {
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
        Patient patientToChange = em.find(Patient.class, patient.getSocialSecurity());
        if(patientToChange != null) {
            this.em.getTransaction().begin();
            this.em.persist(patientToChange);
            this.em.getTransaction().commit();
        }
    }

    @Override
    public void removePatient(Patient patient) {
        Patient patientToRemove = em.find(Patient.class, patient.getSocialSecurity());
        if(patientToRemove != null) {
            this.em.getTransaction().begin();
            this.em.remove(patientToRemove);
            this.em.getTransaction().commit();
        }
    }

    @Override
    public List<Patient> findPatients(String search) {
        List<Patient> patients = new ArrayList<>();

        for (Iterator<Patient> it = this.iterator(); it.hasNext();) {
            Patient patient = it.next();
            String fullName = patient.getFirstName() + " "
                    + patient.getLastName();
            if (fullName.contains(search) ||
                    fullName.toLowerCase().contains(search) ||
                    fullName.toUpperCase().contains(search) ||
                    patient.getSocialSecurity().contains(search)) {

                patients.add(patient);
            }
        }

        return patients;
    }

    @Override
    public void close() {
        this.em.close();
        this.emf.close();
    }

    @Override
    public Iterator<Patient> iterator() {
        List<Patient> patients = null;

        String jpql = "SELECT c FROM Patient c";
        Query query = this.em.createQuery(jpql);

        patients = query.getResultList();

        return patients.iterator();
    }
}
