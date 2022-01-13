package edu.ntnu.idata2001.mappe.del3;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a registry over postal codes.
 * Holds a list containing postalcodes and cities.
 *
 * @author janitalillevikroyseth
 * @version 14.04.2021
 */
public class PostalCodeRegistryPlain implements PostalCodeRegistry {

    /**
     * List over postal codes and cities.
     */
    private ArrayList<PostalCode> postalCodes;

    /**
     * Creates an instance of postalcode registry.
     */
    public PostalCodeRegistryPlain() {
        this.postalCodes = new ArrayList<>();
    }

    @Override
    public void addPostalCode(PostalCode postalCode) throws AddingExistingElementException {
        if (postalCode != null && !postalCodes.contains(postalCode)) {
            this.postalCodes.add(postalCode);
        } else {
            throw new AddingExistingElementException("Already in the list.");
        }
    }

    @Override
    public void changePostalCode(PostalCode postalCode) {
        //Not applicable for plain java.
    }

    @Override
    public void removePostalCode(PostalCode postalCode) {
        this.postalCodes.remove(postalCode);
    }

    @Override
    public List<PostalCode> getRegistry() {
        return this.postalCodes;
    }

    @Override
    public List<PostalCode> findPostalCodes(String search) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (PostalCode postalCode : this.postalCodes) {
            if (postalCode.getCode().contains(search)
                    || postalCode.getCity().toUpperCase().contains(search.toUpperCase())
                    || postalCode.getMunicipalityKey().contains(search)
                    || postalCode.getMunicipality().toUpperCase().contains(search.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }


    @Override
    public List<PostalCode> findPostalCodesByCode(String code) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (PostalCode postalCode : this.postalCodes) {
            if (postalCode.getCode().contains(code)) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByCity(String city) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (PostalCode postalCode : this.postalCodes) {
            if (postalCode.getCity().toUpperCase().contains(city.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByMunicipalityKey(String key) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (PostalCode postalCode : this.postalCodes) {
            if (postalCode.getMunicipalityKey().toUpperCase().contains(key.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByMunicipality(String municipality) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (PostalCode postalCode : this.postalCodes) {
            if (postalCode.getMunicipality().toUpperCase().contains(municipality.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public void close() {
        //Not applicable to plain java.
    }

    @Override
    public Iterator<PostalCode> iterator() {
        return this.postalCodes.iterator();
    }
}
