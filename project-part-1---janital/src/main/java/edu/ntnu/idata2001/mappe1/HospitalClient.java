package edu.ntnu.idata2001.mappe1;

import static edu.ntnu.idata2001.mappe1.HospitalTestData.fillRegisterWithTestData;

/**
 * A simple client with predefined actions to demonstrate the system
 * @author janitalillevikroyseth
 * @version 0.0.1 - Snapshot
 */
public class HospitalClient {

    public static void main(String[] args) {

        //Creates hospital instance and fills with test data
        Hospital hospital = new Hospital("St. NTNU hospital");
        try {
            fillRegisterWithTestData(hospital);
        } catch (IllegalArgumentException e) {
            System.err.println("All fields need to be filled in");
        }

        //Retrieves employee and attempts to remove from hospital
        Person employee1 = hospital.findEmployeeBySocialSecurity("030202 542303");
        try {
            hospital.removePersonFromHospital(employee1);
        } catch (RemoveException exception) {
            System.err.println("Person to remove could not be found in hospitals registry");
        }

        //Creates new instance of employee not in hospital registry,
        //and attempts to remove, exception should be caught and error printed.
        Patient patient = new Patient("Lars", "Lapskaus", "040274 32344");
        try {
            hospital.removePersonFromHospital(patient);
        } catch (RemoveException exception) {
            System.err.println("Person to remove could not be found in hospitals registry");
        }
    }
}
