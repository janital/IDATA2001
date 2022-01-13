package edu.ntnu.idata2001.mappe1;

public class HospitalTestData {
    /**
     * Fills the hospital provided as a parameter with
     * departments and some employees and patients.
     *
     * @param hospital the hospital to be populated with testdata
     */
    public static void fillRegisterWithTestData(final Hospital hospital) {
        // Add some departments
        Department emergencyRoom = new Department("Akutten");
        emergencyRoom.addEmployee(new Employee("Odd Even", "Primtallet", "030202 542303"));
        emergencyRoom.addEmployee(new Employee("Huppasahn", "DelFinito", "040587 342402"));
        emergencyRoom.addEmployee(new Nurse("Rigmor", "Mortis", "030198 42342"));
        emergencyRoom.addEmployee(new GeneralPractitioner("Inco", "Gnito", "220301 42321"));
        emergencyRoom.addEmployee(new Surgeon("Inco", "Gnito", "300303 04203"));
        emergencyRoom.addPatient(new Patient("Nina", "Teknologi", "010203 040302"));
        emergencyRoom.addPatient(new Patient("Ove", "Ralt", "080101 340434"));
        hospital.addDepartment(emergencyRoom);

        Department childrensPolyclinic = new Department("Barne poliklinikk");
        childrensPolyclinic.addEmployee(new Employee("Salti", "Kaffen", "070414 60532"));
        childrensPolyclinic.addEmployee(new Employee("Nidel V.", "ElvefÃ¸lger", "090517 42420"));
        childrensPolyclinic.addEmployee(new Nurse("Anton", "Nym", "040507 54353"));
        childrensPolyclinic.addEmployee(new GeneralPractitioner("Gene", "Sis", "100418 42423"));
        childrensPolyclinic.addPatient(new Patient("Nanna", "Na", "170417 94240"));
        childrensPolyclinic.addPatient(new Patient("Nora", "Toriet", "010106 59302"));
        hospital.addDepartment(childrensPolyclinic);
    }
}
