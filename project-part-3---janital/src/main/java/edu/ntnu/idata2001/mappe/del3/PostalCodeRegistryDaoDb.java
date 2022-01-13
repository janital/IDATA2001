package edu.ntnu.idata2001.mappe.del3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a postalcode registry. Uses dao db for
 * persisting postalcode instances in registry.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public class PostalCodeRegistryDaoDb implements PostalCodeRegistry {

    /**
     * Entity manager factory for this dao db.
     */
    private final EntityManagerFactory emf;

    /**
     * Entity manager for this dao db.
     */
    private final EntityManager em;

    /**
     * Creates an instance of PostalCodeRegistryDaoDb.
     */
    public PostalCodeRegistryDaoDb() {
        this.emf = Persistence.createEntityManagerFactory("postnummer-register");
        this.em = this.emf.createEntityManager();
    }

    @Override
    public void addPostalCode(PostalCode postalCode) throws AddingExistingElementException {
        if(em.find(PostalCode.class, postalCode.getCode()) != null) {
            this.em.getTransaction().begin();
            this.em.persist(postalCode);
            this.em.getTransaction().commit();
        } else {
            throw new AddingExistingElementException("Already in the list");
        }
    }

    @Override
    public void changePostalCode(PostalCode postalCode) {
        PostalCode postalCodeToChange = em.find(PostalCode.class, postalCode.getCode());
        if (postalCodeToChange != null) {
            this.em.getTransaction().begin();
            this.em.persist(postalCodeToChange);
            this.em.getTransaction().commit();
        }
    }

    @Override
    public void removePostalCode(PostalCode postalCode) {
        this.em.getTransaction().begin();
        this.em.remove(postalCode);
        this.em.getTransaction().commit();
    }

    @Override
    public List<PostalCode> getRegistry() {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
            postalCodesFound.add(postalCode);
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodes(String search) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
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

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
            if (postalCode.getCode().contains(code)) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByCity(String city) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
            if (postalCode.getCity().toUpperCase().contains(city.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByMunicipalityKey(String key) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
            if (postalCode.getMunicipalityKey().contains(key)) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public List<PostalCode> findPostalCodesByMunicipality(String municipality) {
        List<PostalCode> postalCodesFound = new ArrayList<>();

        for (Iterator<PostalCode> it = this.iterator(); it.hasNext();) {
            PostalCode postalCode = it.next();
            if (postalCode.getMunicipality().toUpperCase().contains(municipality.toUpperCase())) {
                postalCodesFound.add(postalCode);
            }
        }

        return postalCodesFound;
    }

    @Override
    public void close() {
        this.em.close();
        this.emf.close();
    }

    @Override
    public Iterator<PostalCode> iterator() {
        List<PostalCode> postalCodes = null;

        String jpql = "SELECT p FROM PostalCode p";
        Query query = this.em.createQuery(jpql);

        postalCodes = query.getResultList();

        return postalCodes.iterator();
    }
}
