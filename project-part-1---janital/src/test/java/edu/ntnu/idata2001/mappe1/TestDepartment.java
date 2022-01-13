package edu.ntnu.idata2001.mappe1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestDepartment {

    @Test
    @DisplayName("Positive test of creating department instance")
    public void positiveTestCreatingDepartment() {
        Department department = new Department("Kreftavdeling");

        if(department != null) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Negative test of creating department instance")
    public void negativeTestCreatingDepartment() {
        assertThrows(IllegalArgumentException.class, () -> new Department(null));
    }

    @Test
    @DisplayName("Positive test of adding employees to the  department")
    public void positiveTestAddEmployeesToDepartment() {
        Department department = new Department("Kreftavdeling");
        Employee nurse = new Nurse("Sarah", "Pleierske", "130293 40131");
        Employee surgeon = new Surgeon("Lisa", "Noine", "040453 34231");

        department.addEmployee(nurse);
        department.addEmployee(surgeon);

        assertEquals(2, department.getEmployees().size());
    }

    @Test
    @DisplayName("Negative test of adding employees to the department")
    public void negativeTestAddEmployeeToDepartment() {
        Department department = new Department("Øre, nese, hals");

        department.addEmployee(null);

        assertEquals(0, department.getEmployees().size());
    }

    @Test
    @DisplayName("Positive test of adding patients to the department")
    public void positiveTestAddingPatientsToTheDepartment() {
        Department department = new Department("Kreftavdeling");
        Patient patient1 = new Patient("Kari", "Nordmann", "130293 40131");
        Patient patient2 = new Patient("Jane", "Doe", "040453 34231");

        department.addPatient(patient1);
        department.addPatient(patient2);

        assertEquals(2, department.getPatients().size());
    }

    @Test
    @DisplayName("Negative test of adding patient to the department")
    public void negativeTestAddingPatientTotheDepartment() {
        Department department = new Department("Psychward");

        department.addPatient(null);

        assertEquals(0, department.getPatients().size());
    }

    @Test
    @DisplayName("Positive test of removing person from the departments registry")
    public void positiveTestRemovePersonFromDepartment() throws RemoveException {
        Department department = new Department("Kreftavdeling");
        Employee nurse = new Nurse("Sarah", "Pleierske", "130293 40131");
        Patient patient = new Patient("John", "Doe", "040453");

        department.addEmployee(nurse);
        department.addPatient(patient);
        department.remove(nurse);

        assertEquals(0, department.getEmployees().size());
    }

    @Test
    @DisplayName("Negative test of removing person not in the department")
    public void negativeTestRemovingUnregisteredPersonFromDepartment() throws RemoveException {
        Department department = new Department("Kreftavdeling");
        Employee nurse = new Nurse("Sarah", "Pleierske", "130293 40131");

        assertThrows(RemoveException.class, () ->department.remove(nurse));
    }
}
