package edu.ntnu.idata2001.mappe.del3;

import java.util.Iterator;
import java.util.List;

/**
 * Represents the interface for postalcode registry.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public interface PostalCodeRegistry extends Iterable<PostalCode> {

    /**
     * Adds a postal code to the postal code registry.
     *
     * @param postalCode postalcode to be added to the registry.
     */
    void addPostalCode(PostalCode postalCode) throws AddingExistingElementException;

    /**
     * Changes a postalcode in the postalcode registry.
     *
     * @param postalCode postalcode to be changed.
     */
    void changePostalCode(PostalCode postalCode);

    /**
     * Removes a postal code from the postal code registry.
     *
     * @param postalCode postalcode to be removed from the registry.
     */
    void removePostalCode(PostalCode postalCode);

    /**
     * Returns the list over postalcodes in the registry.
     *
     * @return list of postalcodes in registry.
     */
    List<PostalCode> getRegistry();

    /**
     * Iterates over the postalcode registry and finds postalcode with
     * the matching code, city, municipality key or municipality to the
     * given search String.
     * Returns a list over matching postalcodes found.
     *
     * @param search the String to search for matching postal codes.
     * @return list over matching postalcodes found.
     */
    List<PostalCode> findPostalCodes(String search);

    /**
     * Iterates over the postalcode registry and finds postalcodes with
     * the matching code to the given String.
     * Returns a list with matching postalcodes found.
     *
     * @param code the code to search for matches of
     * @return list over matching postalcodes found.
     */
    List<PostalCode> findPostalCodesByCode(String code);

    /**
     * Iterates over the postalcode registry and finds postalcodes with
     * the matching cities to the given String.
     * Returns a list with matching postalcodes found.
     *
     * @param city the city to search for matches of
     * @return list over matching postalcodes found.
     */
    List<PostalCode> findPostalCodesByCity(String city);

    /**
     * Iterates over the postalcode registry and finds postalcodes with
     * the matching municipality ket to the given String.
     * Returns a list with matching postalcodes found.
     *
     * @param key the municipality key to search for matches of
     * @return list over matching postalcodes found.
     */
    List<PostalCode> findPostalCodesByMunicipalityKey(String key);

    /**
     * Iterates over the postalcode registry and finds postalcodes with
     * the matching municipality to the given String.
     * Returns a list with matching postalcodes found.
     *
     * @param municipality the municipality to search for matches of
     * @return list over matching postalcodes found.
     */
    List<PostalCode> findPostalCodesByMunicipality(String municipality);

    /**
     * Closes the entitymanager and the entitymanagerfactory.
     */
    void close();

    @Override
    Iterator<PostalCode> iterator();
}
