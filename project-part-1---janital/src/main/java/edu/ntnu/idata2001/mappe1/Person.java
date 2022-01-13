package edu.ntnu.idata2001.mappe1;

/**
 * Represents a person.
 */
abstract public class Person {
    private String firstName;
    private String lastName;
    private String socialSecurity;

    /**
     * Creates an instance of Person.
     * @param firstName first name of the person
     * @param lastName last name of the person
     * @param socialSecurity social security of the person
     */
    public Person(String firstName, String lastName, String socialSecurity) {
        if ( firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank() || socialSecurity == null || socialSecurity.isBlank()) {
            throw new IllegalArgumentException("All fields need to be filled");
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.socialSecurity = socialSecurity;
        }
    }

    /**
     * Returns the persons social security number
     * @return social security number
     */
    public String getSocialSecurity(){
        return this.socialSecurity;
    }

    /**
     * Sets the persons social security number
     * @param socialSecurity the social security number to be set
     */
    public void setSocialSecurity(String socialSecurity){
        if(socialSecurity != null && !socialSecurity.isBlank()) {
            this.socialSecurity = socialSecurity;
        }
    }

    /**
     * Returns the persons first name
     * @return first name of the person
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Sets the persons first name
     * @param firstName the name to be set
     */
    public void setFirstName(String firstName) {
        if(firstName != null && !firstName.isBlank()){
            this.firstName = firstName;
        }
    }

    /**
     * Returns the persons last name
     * @return last name of the person
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets the persons last name
     * @param lastName the last name to be set
     */
    public void setLastName(String lastName) {
        if(lastName != null && !lastName.isBlank()){
            this.lastName = lastName;
        }
    }

    /**
     * Returns information about the person as String
     * @return information about the person as String
     */
    public String toString() {
        return "[Firstname: " + this.firstName + "] \n"
                + "[Lastname: " + this.lastName + "] \n"
                + "[Socialsecurity: " + this.socialSecurity + "] \n";
    }

}
