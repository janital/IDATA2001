package edu.ntnu.idata2001.mappe1;

import java.util.ArrayList;

/**
 * Represents a department at a hospital. Creates an arraylist of employees
 * and an arraylist of patient.
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class Department {

    private String departmentName;
    private ArrayList<Employee> employees;
    private ArrayList<Patient> patients;

    /**
     * Creates an instance of Department.
     * @param departmentName the name of the department
     */
    public Department(String departmentName){
        if(departmentName == null || departmentName.isBlank()) {
            throw new IllegalArgumentException("Department name needs to be filled in");
        } else {
            this.departmentName = departmentName;
            this.employees = new ArrayList<>();
            this.patients = new ArrayList<>();
        }
    }

    /**
     * Sets the name of the department
     * @param departmentName
     */
    public void setDepartmentName(String departmentName) {
        if(departmentName != null && !departmentName.isBlank()) {
            this.departmentName = departmentName;
        }
    }

    /**
     * Returns the name of the department
     * @return name of the department
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Returns the list with employees in the department
     * @return list with employees in the department
     */
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    /**
     * Adds an employee to the employees list
     * @param employee the employee to be added to the list
     */
    public void addEmployee(Employee employee) {
        if(employee != null) {
            this.employees.add(employee);
        }
    }

    /**
     * Returns the list with patients in the department
     * @return the list with patients in the department
     */
    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    /**
     * Adds a patient to the list of patients
     * @param patient the patient to be added to the list
     */
    public void addPatient(Patient patient) {
        if(patient != null) {
            this.patients.add(patient);
        }
    }

    /**
     * Iterates over the departments "patients" list, and returns the patient matching the
     * given social security number
     * Returns null if no matches are found
     * @param socialSecurity The social security number of the patient to search for
     * @return The patient who matches the given social security number, or null if there are no matches
     */
    public Person findPatientBySocialSecurity(String socialSecurity) {
        Person patientToBeFound = null;

        for(Person person : this.patients) {
            if(person.getSocialSecurity().equals(socialSecurity)) {
                patientToBeFound = person;
            }
        }

        return patientToBeFound;
    }

    /**
     * Iterates over the departments "employees" list and returns the employee matching the
     * given social security number
     * Returns null if no matches are found
     * @param socialSecurity The social security nymber of the employee to search for
     * @return The employee whi matches the given social security, or null if there are no matches
     */
    public Person findEmployeeBySocialSecurity(String socialSecurity) {
        Person employeeToBeFound = null;

        for(Person person : this.employees) {
            if(person.getSocialSecurity().equals(socialSecurity)) {
                employeeToBeFound = person;
            }
        }

        return employeeToBeFound;
    }

    /**
     * Computes an hashCode and returns it.
     * @return the objects hashCode
     */
    public int hashCode() {
        int code = 16;
        code = 41 * code + this.departmentName.hashCode();
        code = 41 * code + this.employees.hashCode();
        code = 41 * code + this.patients.hashCode();
        return code;
    }

    /**
     * Removes a person from either patients list og employee list. Throws RemoveException if the
     * gives person is not in any of the lists
     * @param person the person to be removed from the lists
     * @throws RemoveException If the person is not in any of the lists
     */
    public void remove(Person person) throws RemoveException {
        if(this.employees.contains(person)) {
            this.employees.remove(person);
        } else if(this.patients.contains(person)) {
            this.patients.remove(person);
        } else {
            throw new RemoveException("Person could not be found in any list, therefore is not removed");
        }
    }

    /**
     * Checks if there's another object of Department, and returns {code true}
     * if the department name matches another department name.
     * @param object the object to be checked
     * @return {code true} if the object matches the department
     * {code false} if the object does not match the department
     */
    public boolean equals(Object object) {
        if(object instanceof Department) {
            Department department = (Department) object;
            return departmentName.equals(((Department) object).getDepartmentName()) &&
                    employees.equals(((Department) object).getEmployees()) &&
                    patients.equals(((Department) object).getPatients());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Department: " + this.departmentName + "] \n";
    }
}
