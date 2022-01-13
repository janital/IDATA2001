package edu.ntnu.idata2001.mappe1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a hospital. Holds the hospitals name and an array list of
 * departments at the hospital
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Hospital {

    private final String hospitalName;
    private ArrayList<Department> departments;

    /**
     * Creates an instance of hospital.
     * @param hospitalName name of the hospital
     */
    public Hospital(String hospitalName) {
        if (hospitalName == null || hospitalName.isBlank()) {
            throw new IllegalArgumentException("Hospital name needs to be filled in");
        } else {
            this.hospitalName = hospitalName;
            this.departments = new ArrayList<>();
        }
    }

    /**
     * Returns the hospitals name
     * @return the hospitals name
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * Returns the departments in the hospital
     * @return departments in the hospital
     */
    public ArrayList<Department> getDepartments() {
        return departments;
    }

    /**
     * Adds a department to the hospitals list of departments
     * @param department department to be added to the hospitals deparment list
     */
    public void addDepartment(Department department) {
        if(department != null) {
            this.departments.add(department);
        }
    }

    /**
     * Removes the given department from list of departments in hospital
     * @param department the department to be removed
     */
    public void removeDepartment(Department department) {
        this.departments.remove(department);
    }

    /**
     * Iterates over the hospitals departments, and through them iterates over the
     * departments patient list. Returns the patient matching the given social security, or null
     * if no patients matches.
     * @param socialSecurity The social security for the patient to search for
     * @return the patient who matches the social security, or null if no matches are found
     */
    public Person findPatientBySocialSecurity(String socialSecurity) {
        Person patientToBeFound = null;
        boolean searching = false;
        Iterator<Department> iterator = this.departments.iterator();

        while(iterator.hasNext() && searching) {
            Department department = iterator.next();
            patientToBeFound = department.findPatientBySocialSecurity(socialSecurity);
            searching = false;
            }

        return patientToBeFound;
    }

    /**
     * Iterates over the hospitals departments, and through them iterates over the
     * departments employees list. Returns the employee matching the given social security, or null
     * if no employee matches.
     * @param socialSecurity The social security for the employee to search for
     * @return the employee who matches the social security, or null if no matches are found
     */
    public Person findEmployeeBySocialSecurity(String socialSecurity) {
        Person employeeToBeFound = null;
        boolean searching = true;
        Iterator<Department> iterator = this.departments.iterator();

        while(iterator.hasNext() && searching) {
            Department department = iterator.next();
            employeeToBeFound = department.findEmployeeBySocialSecurity(socialSecurity);
            searching = false;
            }

        return employeeToBeFound;
    }

    /**
     * Iterates over the hospitals deparments and attempts to remove the given person
     * If no person is not in any departments, RemoveException is thrown.
     * @param person the person to be removed from the hospital
     * @throws RemoveException If the person is not in any departments
     */
    public void removePersonFromHospital(Person person) throws RemoveException {
        boolean personIsRemoved = false;

        for (Department department : this.departments) {
            if (department.getPatients().contains(person) || department.getEmployees().contains(person)) {
                department.remove(person);
                personIsRemoved = true;
            }
        }

        if(!personIsRemoved) {
            throw new RemoveException("Person was not removed from the hospitals departments");
        }
    }

    /**
     * Returns information about the hospital as String.
     * @return information about the hospital as String.
     */
    public String toString() {
        return "[Hospital: " + getHospitalName() + "] \n";
    }
}
