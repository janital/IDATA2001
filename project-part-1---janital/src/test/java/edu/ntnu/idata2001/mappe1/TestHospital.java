package edu.ntnu.idata2001.mappe1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHospital {

    @Test
    @DisplayName("Positive test of creating hospital")
    public void positiveTestCreatingHospital() {
        Hospital hospital = new Hospital("st. NTNU hospital");

        if(hospital != null) {
            assertTrue(true);
        }
    }

    @Test
    @DisplayName("Negative test of creating hospital")
    public void negativeTestCreatingHospital() {
        assertThrows(IllegalArgumentException.class, () -> new Hospital(null) );
    }

    @Test
    @DisplayName("Positive test of adding department to hospital")
    public void positiveTestAddDepartmentToHospital() {
        Hospital hospital = new Hospital("St. Olavs hospital");
        Department cancerWard = new Department("Kreftavdeling");

        hospital.addDepartment(cancerWard);

        assertEquals(cancerWard, hospital.getDepartments().get(0));
    }

    @Test
    @DisplayName("Negative test of adding department to hospital")
    public void negativeTestAddDepartmentToHospital() {
        Hospital hospital = new Hospital("St. NTNU hospital");

        hospital.addDepartment(null);

        assertEquals(0, hospital.getDepartments().size());
    }

    @Test
    @DisplayName("Positive test of removing department from hospital")
    public void positiveTestRemovingDepartment() {
        Hospital hospital = new Hospital("St. Olavs hospital");
        Department cancerWard = new Department("Kreftavdeling");

        hospital.addDepartment(cancerWard);
        hospital.removeDepartment(cancerWard);

        assertEquals(0, hospital.getDepartments().size());
    }

    @Test
    @DisplayName("Positive test of adding person to hospital")
    public void positiveTestAddPersonToHospital() {
        Hospital hospital = new Hospital("st. NTNU hospital");
        Department department = new Department("Cancerward");
        Employee employee = new Surgeon("Gregory", "House", "030391 42243");

        hospital.addDepartment(department);
        hospital.getDepartments().get(0).addEmployee(employee);

        assertEquals(employee, hospital.getDepartments().get(0).getEmployees().get(0));
    }

    @Test
    @DisplayName("Negative test of adding person to hospital")
    public void negativeTestAddPersonToHospital() {
        Hospital hospital = new Hospital("st. NTNU hospital");
        Department department = new Department("Cancerward");

        hospital.addDepartment(department);
        hospital.getDepartments().get(0).addPatient(null); //Should not be added

        assertEquals(0, hospital.getDepartments().get(0).getPatients().size());
    }

    @Test
    @DisplayName("Positive test of removing person from hospital")
    public void positiveTestRemovePersonFromHospital() {
        Hospital hospital = new Hospital("st. NTNU hospital");
        Department department1 = new Department("Maternity");
        Department department2 = new Department("Cancerward");
        Employee employee = new Nurse("Lise", "Pleierske", "040592 40523");

        hospital.addDepartment(department1);
        hospital.addDepartment(department2);
        department2.addEmployee(employee);

        assertDoesNotThrow(() -> hospital.removePersonFromHospital(employee));
    }

    @Test
    @DisplayName("Negative test of removing person from hospital")
    public void negativeTestRemovePersonFromHospital() {
        Hospital hospital = new Hospital("st. NTNU hospital");
        Department department1 = new Department("Maternity");
        Department department2 = new Department("Cancerward");
        Employee employee = new GeneralPractitioner("Doktor", "Brille", "040592 40523");

        hospital.addDepartment(department1);
        hospital.addDepartment(department2);

        assertThrows(RemoveException.class, () -> hospital.removePersonFromHospital(employee));
    }
}
