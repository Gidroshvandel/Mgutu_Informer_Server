package dao.Impl;

import dao.Factory;
import dao.GroupsDAO;
import model.Groups;

import java.sql.SQLException;
import java.util.List;

public class GroupsDAOImpl implements GroupsDAO{
    @Override
    public Boolean addGroup(Groups groups) throws SQLException {
        return Factory.getInstance().getGenericRepositoryInterface().addObject(groups);
    }

    @Override
    public void updateGroup(Groups groups) throws SQLException {

    }

    @Override
    public Groups getGroupByName(String groupsName) throws SQLException {
        return (Groups) Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", groupsName);
    }

    @Override
    public List getAllGroups() throws SQLException {
        return Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
    }

    @Override
    public Boolean deleteGroup(Groups groups) throws SQLException {
        return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
    }
//    @Override
//    public String addGroup(Groups groups) throws SQLException {
//
//        Map<String, Object> result = new HashMap<>();
//
//        em = getEm();
//
//        try {
//            Groups groupsEquals = Factory.getInstance().getGroupsDAO().getGroupByName(groups.getGroupsName());
//            em.getTransaction().begin();
//
//            if(groupsEquals == null){
//                result.put("Status:", "OK");
//                em.persist(groups);
//            }
//            else {
//                result.put("Status:", "BAD");
//            }
//
//            em.getTransaction().commit();
//
//            return new Gson().toJson(result);
//
//        } catch (Exception e) {
//            if (em.getTransaction() != null){
//                em.getTransaction().rollback();
//            }
//            return "Error1"; //Ошибка записи пользователя
//        }
//    }
//
//    @Override
//    public void updateGroup(Groups groups) throws SQLException {
//
//    }
//
//    @Override
//    public Groups getGroupByName(String groupsName) throws SQLException {
//        Groups groups;
//
//            em = getEm();
//
//        try {
//            CriteriaBuilder builder = em.getCriteriaBuilder();
//            CriteriaQuery<Groups> criteria = builder.createQuery(Groups.class);
//            Root<Groups> u = criteria.from(Groups.class);
//            TypedQuery<Groups> query = em.createQuery(
//                    criteria.select(u).where(builder.equal(u.get("groupsName"), groupsName)));
//            groups = query.getSingleResult();
//        } catch(NoResultException noResult) {
//            return null;
//        }
//        return groups;
//    }
//
//    @Override
//    public List getAllGroups() throws SQLException {
//
//            em = getEm();
//
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Groups> criteria = builder.createQuery(Groups.class);
//        Root<Groups> u = criteria.from(Groups.class);
//        TypedQuery<Groups> query = em.createQuery(
//                criteria.select(u.get("groupsName")).where(u.get("groupsName").isNotNull()));
//        List<Groups> listGroups = query.getResultList();
//        return listGroups;
//    }
//
//    @Override
//    public String deleteGroup(Groups groups) throws SQLException {
//        Map<String, Object> result = new HashMap<>();
//
//        em = getEm();
//
//        try {
//            Groups groupsEquals = Factory.getInstance().getGroupsDAO().getGroupByName(groups.getGroupsName());
//            em.getTransaction().begin();
//
//            if(groupsEquals != null){
//                em.remove(groupsEquals);
//                result.put("Status:", "OK");
//            }
//            else {
//                result.put("Status:", "BAD");
//            }
//
//            em.getTransaction().commit();
//
//            return new Gson().toJson(result);
//
//        } catch (Exception e) {
//            if (em.getTransaction() != null){
//                em.getTransaction().rollback();
//            }
//            return "Error1"; //Ошибка записи пользователя
//        }
//
//    }
}
