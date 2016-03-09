package dao.Impl;

import dao.GenericRepositoryInterface;
import utils.EMF;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericRepositoryImplementation<T> implements GenericRepositoryInterface {
    protected EntityManager entityManager;
    private Class<T> type;

    public GenericRepositoryImplementation() {
        // TODO Auto-generated constructor stub

    }

    public GenericRepositoryImplementation(Class<T> type) {
        // TODO Auto-generated constructor stub

        this.type = type;
    }

    @Override
    public Boolean addObject(Object emp) {
        entityManager = EMF.getEm();
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(emp);
            entityManager.flush();
        }catch (Exception ex){
            return  false;
        }

        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public List<T> getAllObjects(String columnName) {
        entityManager = EMF.getEm();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> u = criteria.from(type);
            TypedQuery<T> query = entityManager.createQuery(
                    criteria.select(u.get(columnName)).where(u.get(columnName).isNotNull()));
            return query.getResultList();
        } catch (NoResultException noResult) {
            return null;
        }catch (NonUniqueResultException nonUnique) {
            return null;
        }
    }

    @Override
    public Boolean removeObject(Object emp) {
        try {
            entityManager = EMF.getEm();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(emp) ? emp : entityManager.merge(emp));
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean editObject(Object emp) {
        try{
            entityManager = EMF.getEm();
            entityManager.getTransaction().begin();
            entityManager.merge(emp);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            return false;
        }
    }


    @Override
    public Object getObject(String columnName, String columnValue) {
        entityManager = EMF.getEm();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> u = criteria.from(type);
            TypedQuery<T> query = entityManager.createQuery(
                    criteria.select(u).where(builder.equal(u.get(columnName), columnValue)));
            return query.getSingleResult();
        } catch (NoResultException noResult) {
            return null;
        }catch (NonUniqueResultException nonUnique) {
            return null;
        }
    }

}
