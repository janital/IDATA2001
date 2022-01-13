package edu.ntnu.idata2001.mappe.del3;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Parses tabseperated text files. Reads from text files and converts
 * the lines to postalcodes instances, and writes text files.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public class TextFileParser {


    /**
     * Creates an instance of text file parser.
     */
    public TextFileParser() {
    }

    /**
     * Converts the lines in the provided text file to postalcodes instances
     * and adds them to the given registry.
     *
     * @param pathToFile the path to the text file for the postalcoderegistry.
     * @param registry the registry to add the postalcodes to.
     * @throws IOException if there's problems with reading the file.
     * @throws AddingExistingElementException if the element already exists in the registry.
     */
    public void importPostalCodesFromTextFile(String pathToFile, PostalCodeRegistry registry) throws IOException, AddingExistingElementException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(pathToFile, StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\t");
                if (!fields[0].isBlank() && !fields[1].isBlank() && !fields[2].isBlank() && !fields[3].isBlank() && !fields[4].isBlank()) {
                    registry.addPostalCode(new PostalCode(fields[0], (fields[1]), fields[2], (fields[3]), fields[4]));
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    /**
     * Writes a text file containing the postalcodes in the given registry.
     *
     * @param fileName the name of the file to be written.
     * @param registry the registry to create text file for.
     * @throws IOException from the filewriter.
     */
    public void exportPostalCodesToTextFile(String fileName, PostalCodeRegistry registry) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            for (PostalCode postalCode : registry) {
                if (registry.getRegistry().indexOf(postalCode) == 0) {
                    fileWriter.write(postalCode.getCode() + "\t"
                            + postalCode.getCity().toUpperCase() + "\t"
                            + postalCode.getMunicipalityKey() + "\t"
                            + postalCode.getMunicipality().toUpperCase() + "\t"
                            + postalCode.getCategory().charAt(0));
                } else {
                    fileWriter.write("\n" + postalCode.getCode() + "\t"
                            + postalCode.getCity().toUpperCase() + "\t"
                            + postalCode.getMunicipalityKey() + "\t"
                            + postalCode.getMunicipality().toUpperCase() + "\t"
                            + postalCode.getCategory().charAt(0));
                }
            }
        } finally {
            fileWriter.close();
        }
    }
}