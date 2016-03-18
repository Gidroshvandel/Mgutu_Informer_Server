package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EMF {
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("d4nm03ep7uplmc");

    protected EntityManager em;

    public static EntityManager getEm(){

        if (factory == null){
            throw new IllegalStateException("EntityManagerFactory not yet initialized");
        }
        return factory.createEntityManager();

    }
}
