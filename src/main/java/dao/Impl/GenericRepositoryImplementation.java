package dao.Impl;

import dao.GenericRepositoryInterface;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import utils.EMF;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericRepositoryImplementation<T> implements GenericRepositoryInterface<T> {
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
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(emp);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception ex){
            return  false;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> getAllObjects() {
        entityManager = EMF.getEm();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> u = criteria.from(type);
            criteria.select(u);
            TypedQuery<T> query = entityManager.createQuery(criteria);
            List<T> parties = query.getResultList();
            return parties;
        } catch (NoResultException noResult) {
            return null;
        }catch (NonUniqueResultException nonUnique) {
            return null;
        }
        finally {
            entityManager.close();
        }
    }

    public List<T> getObjects(Map<String,Object> map) {
        entityManager = EMF.getEm();
        try {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> u = criteria.from(type);
            criteria.select(u);
            List<Predicate> prList = new ArrayList<>();
            for(Map.Entry<String,Object> map1: map.entrySet())
            {
                prList.add(builder.equal(u.get(map1.getKey()), map1.getValue()));
            }
            criteria.where(builder.and(prList.toArray(new Predicate[prList.size()])));
            TypedQuery<T> query = entityManager.createQuery(criteria);
            return query.getResultList();
        } catch (NoResultException noResult) {
            return null;
        }catch (NonUniqueResultException nonUnique) {
            return null;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean removeObject(Object emp) {
        entityManager = EMF.getEm();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(emp) ? emp : entityManager.merge(emp));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
        finally {
            entityManager.close();
        }

    }

    @Override
    public Boolean editObject(Object emp) {
        entityManager = EMF.getEm();
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(emp);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            return false;
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public T getObject(String columnName, Object columnValue) {
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
        finally {
            entityManager.close();
        }
    }

    @Override
    public T getObject(Map<String,Object> map) {
        entityManager = EMF.getEm();
        try {

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> criteria = builder.createQuery(type);
            Root<T> u = criteria.from(type);
            criteria.select(u);
            List<Predicate> prList = new ArrayList<>();
            for(Map.Entry<String,Object> map1: map.entrySet())
            {
               prList.add(builder.equal(u.get(map1.getKey()), map1.getValue()));
            }
            criteria.where(builder.and(prList.toArray(new Predicate[prList.size()])));
            TypedQuery<T> query = entityManager.createQuery(criteria);
            return query.getSingleResult();
        } catch (NoResultException noResult) {
            return null;
        }catch (NonUniqueResultException nonUnique) {
            return null;
        }
        finally {
            entityManager.close();
        }
    }

}