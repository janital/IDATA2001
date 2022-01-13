package edu.ntnu.idata2001.mappedel2;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a patient. Holds information about the patients
 * name, social security number, diagnosis and general practitioner.
 *
 * @author janitalillevikroyseth
 * @version 27.04.2021
 */
@Entity
public class Patient implements Serializable {

    /**
     * This patient's first name.
     */
    private String firstName;

    /**
     * This patient's last name.
     */
    private String lastName;

    /**
     * This patient's socialsecurity number.
     */
    //@CsvBindByPosition(position = 3)
    @Id
    private String socialSecurity; //Should be final, but use of beans requires it not to be.

    /**
     * This patient's diagnosis.
     */
    private String diagnosis;

    /**
     * This patient's general practitioner.
     */
    private String generalPractitioner;

    /**
     * Creates an instance of Patient.
     *
     * @param firstName the patients first name.
     * @param lastName the patients last name.
     * @param socialSecurity the patients socialsecurity number.
     * @param diagnosis the patients diagnosis.
     * @param generalPractitioner the patients general practitioner.
     */
    public Patient(String firstName, String lastName, String socialSecurity,
                      String generalPractitioner, String diagnosis) {
        if ( firstName != null && !firstName.isBlank() &&
                lastName != null && !lastName.isBlank() &&
                socialSecurity != null && !socialSecurity.isBlank()) {
            this.setFirstName(firstName);
            this.setLastName(lastName);
            this.setSocialSecurity(socialSecurity);
            this.setGeneralPractitioner(generalPractitioner);
            this.setDiagnosis(diagnosis);
        } else {
            throw new IllegalArgumentException("Firstname, lastname and ssn needs to be filled");
        }
    }

    /**
     * Empty constructor
     */
    public Patient() {

    }

    /**
     * Sets this patients first name.
     *
     * @param firstName the patients firstname to be set.
     */
    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = "";
        }
    }

    /**
     * Returns this patients first name.
     *
     * @return this patients first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets this patients last name.
     *
     * @param lastName the patients last name to be set.
     */
    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        } else {
            this.lastName = "";
        }
    }

    /**
     * Returns this patients last name.
     *
     * @return this patients last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets this patients socialsecurity number.
     *
     * @param socialSecurity the patients socialsecurity number to be set.
     */
    private void setSocialSecurity(String socialSecurity) {
        if(socialSecurity != null && !socialSecurity.isBlank()) {
            this.socialSecurity = socialSecurity;
        }
    }

    /**
     * Returns this patients socialsecurity number.
     *
     * @return this patients social security number.
     */
    public String getSocialSecurity() {
        return this.socialSecurity;
    }

    /**
     * Sets this patients diagnosis.
     *
     * @param diagnosis the patients diagnosis to be set.
     */
    public void setDiagnosis(String diagnosis) {
        if(diagnosis != null) {
            this.diagnosis = diagnosis;
        } else {
            this.diagnosis = "";
        }
    }

    /**
     * Returns this patients diagnosis.
     *
     * @return this patients diagnosis.
     */
    public String getDiagnosis() {
        return this.diagnosis;
    }

    /**
     * Sets this patients general practitioner.
     *
     * @param generalPractitioner the patients general practitioner to be set.
     */
    public void setGeneralPractitioner(String generalPractitioner) {
        if(generalPractitioner != null) {
            this.generalPractitioner = generalPractitioner;
        } else {
            this.generalPractitioner = "";
        }
    }

    /**
     * Returns this patients general practitioner.
     *
     * @return this patients general practitioner.
     */
    public String getGeneralPractitioner() {
        return this.generalPractitioner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return socialSecurity.equals(patient.socialSecurity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socialSecurity);
    }
}
