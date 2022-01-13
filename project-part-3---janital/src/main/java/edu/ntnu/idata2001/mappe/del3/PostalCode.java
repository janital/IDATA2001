package edu.ntnu.idata2001.mappe.del3;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a postal code beloning to a specified place in a city.
 * Holds information about the postal code it self and the
 * city it belongs to.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
@Entity
public class PostalCode implements Serializable {

    /**
     * The code of this postal code.
     */
    @Id
    private String code;

    /**
     * The city this postal code belongs to.
     */
    private String city;

    /**
     * The key of the municipality this postal code is in.
     */
    private String municipalityKey;

    /**
     * The municipality this postal code is in.
     */
    private String municipality;

    /**
     * Category of this postal code.
     */
    private String category;

    /**
     * Creates an instance of postalcode.
     *
     * @param code the code of the postalcode.
     * @param city the city of where the postalcode is.
     * @param municipalityKey the municipality key of the municipality where the postalcode is.
     * @param municipality the municipality of the where the postalcode is.
     * @param category the category of the postalcode.
     */
    public PostalCode(String code, String city, String municipalityKey, String municipality, String category) {
        if (code == null || code.isBlank()
                || city == null || city.isBlank()
                || municipalityKey == null || municipalityKey.isBlank()
                || municipality == null || municipality.isBlank()
                || category == null || category.isBlank()) {
            throw new IllegalArgumentException("Not valid values for norwegian postal codes");
        } else {
            this.setCode(code);
            this.setCity(city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase());
            this.setMunicipalityKey(municipalityKey);
            this.setMunicipality(municipality.substring(0, 1).toUpperCase() + municipality.substring(1).toLowerCase());
            this.setCategory(category);
        }
    }

    /**
     * Empty constructor
     */
    public PostalCode() {

    }

    /**
     * Returns the code of this postal code.
     *
     * @return code of this postal code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code of this postal code.
     *
     * @param code the code to be set.
     */
    private void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("code needs to be 4 digits");
        } else {
            this.code = code;
        }
    }

    /**
     * Returns the city this postal code belongs to.
     *
     * @return city this postal code belongs to.
     */
    public String getCity() {
        return city;
    }

    /**
     * Ssets the city that this postal code belongs to.
     *
     * @param city the city to be set to this postal code.
     */
    public void setCity(String city) {
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("This field can not be empty");
        } else {
            this.city = city;
        }
    }

    /**
     * Returns the official municipality key of where this postalcode
     * belongs to.
     *
     * @return official municipality key where this postalcode belongs.
     */
    public String getMunicipalityKey() {
        return this.municipalityKey;
    }

    /**
     * Sets the official municipality key of where this postal code belongs.
     *
     * @param municipalityKey the municipality key to be set.
     */
    public void setMunicipalityKey(String municipalityKey) {
        if (municipalityKey == null || municipalityKey.isBlank()) {
            throw new IllegalArgumentException("Needs to be 4 digits");
        } else {
            this.municipalityKey = municipalityKey;
        }
    }

    /**
     * Returns the municipality of where this postal code belongs.
     *
     * @return municipality of where this postal code belongs.
     */
    public String getMunicipality() {
        return this.municipality;
    }

    /**
     * Sets the municipality of where this postal code belongs.
     *
     * @param municipality the municipality to be set.
     */
    public void setMunicipality(String municipality) {
        if (municipality == null || municipality.isBlank()) {
            throw new IllegalArgumentException("Field needs to be filled");
        } else {
            this.municipality = municipality;
        }
    }

    /**
     * Returns the category this postal code is used for.
     *
     * @return category this postal code is used for.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the category this postal code is used for.
     *
     * @param category the category this postal code is used for.
     */
    public void setCategory(String category) {
        if (category == null || category.isBlank()) {
            throw new IllegalArgumentException("Field needs to be filled");
        } else {
            switch (String.valueOf(category.charAt(0))) {
                case "B":
                    this.category = "Både gateadresser og postbokser";
                    break;
                case "F":
                    this.category = "Flere bruksområder (felles)";
                    break;
                case "G":
                    this.category = "Gateadresser (og stedsadresser), dvs. grønne postkasser";
                    break;
                case "P":
                    this.category = "Postbokser";
                    break;
                case "S":
                    this.category = "Servicepostnummer";
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostalCode)) {
            return false;
        }
        PostalCode that = (PostalCode) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
