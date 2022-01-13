package edu.ntnu.idata2001.mappedel2;

import java.io.*;
import java.util.List;
import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.FuzzyMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.bean.exceptionhandler.ExceptionHandlerIgnore;
import com.opencsv.bean.exceptionhandler.ExceptionHandlerQueue;
import com.opencsv.bean.exceptionhandler.ExceptionHandlerThrow;
import com.opencsv.exceptions.CsvException;

/**
 * Parses .csv files. Reads from .csv files and converts the csv to patient, and
 * writes .csv files from the patient registry.
 */
public class CSVFileParser {

    /**
     * List over exceptions that gets thrown, and caught, when
     * trying to parse the .csv file into an Patient instance.
     */
    private List<CsvException> exceptions;

    /**
     * Creates an instance of csv file parser.
     */
    public CSVFileParser() {
    }

    /**
     * Converts the lines in the provided .csv file to patient and adds them to the
     * given registry.
     *  @param pathToFile the path to the .csv file for the patientregistry.
     * @param registry the registry to add the patients to.
     */
    public void importPatientsFromCSV(String pathToFile, PatientRegistry registry) throws FileNotFoundException, AddPatientToRegistryException {
        MappingStrategy<Patient> strategy = new FuzzyMappingStrategy<>();
        strategy.setType(Patient.class);
        CsvExceptionHandler handler = new ExceptionHandlerQueue();
        CsvToBean<Patient> parser = new CsvToBeanBuilder<Patient>(new FileReader(pathToFile))
                .withMappingStrategy(strategy)
                .withSeparator(';')
                .withExceptionHandler(handler)
                .build();
        List<Patient> patients = parser.parse();
        this.exceptions = parser.getCapturedExceptions();
        registry.addAll(patients);
    }

    /**
     * Writes a .csv file containing the patients in the given registry.
     *
     * @param fileName the name of the file to be written.
     * @param registry the registry to create .csv for.
     * @throws IOException
     */
    public void exportPatientsToCSV(String fileName, PatientRegistry registry) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            fileWriter.write("firstName;lastName;socialSecurity;diagnosis;generalPractitioner");
            for (Patient patient : registry) {
                if(patient.getDiagnosis() == null) {
                    patient.setDiagnosis("");
                }
                if(patient.getGeneralPractitioner() == null) {
                    patient.setGeneralPractitioner("");
                }
                fileWriter.write("\n" + patient.getFirstName() + ";"
                        + patient.getLastName() + ";"
                        + patient.getSocialSecurity() + ";"
                        + patient.getDiagnosis() + ";"
                        + patient.getGeneralPractitioner());
            }
        } finally {
            fileWriter.close();
        }
    }

    /**
     * Returns a list over exceptions that occured when trying
     * to parse a .csv file into Patient instances.
     *
     * @return list over exceptions that occured.
     */
    public List<CsvException> getExceptions() {
        return this.exceptions;
    }
}