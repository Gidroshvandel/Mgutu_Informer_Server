package dao.Impl;

import com.google.gson.Gson;
import dao.Factory;
import dao.UsersDAO;
import model.Users;
import utils.EMF;
import utils.HashWithSalt;

import javax.persistence.NoResultException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDAOImpl extends EMF implements UsersDAO  {


    public Users setHashPassword(Users users) {
        HashWithSalt hash = null;
        try {
            hash = new HashWithSalt(users.getPassword(), users.getLogin());
        } catch (InvalidKeySpecException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        try {
            users.setPassword(hash.getHash());
        }
        catch (NullPointerException e){

        }
        return users;
    }

    @Override
    public String addUser(Users users) throws SQLException {

        setHashPassword(users);

//        Map<String, Object> result = new HashMap<>();

        em = getEm();
        try {
            em.getTransaction().begin();

            try {
                em.createQuery("SELECT users FROM model.Users users WHERE users.login=:login")
                        .setParameter("login", users.getLogin())
                        .getSingleResult();
            }catch (NoResultException e){
//                result.put("usersId", users.getUsersId());
//                result.put("secretKey", users.getSecretKey());
                em.persist(users);
            }

            em.getTransaction().commit();

//            return new Gson().toJson(result);
            users.setLogin("");
            users.setPassword("");
            return new Gson().toJson(users);
        } catch (Exception e) {
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
            return "Error1"; //Ошибка записи пользователя
        }
        finally {
            em.close();
        }
    }

    @Override
    public String loginUsers(Users users) {

        setHashPassword(users);

//        Map<String, Object> result = new HashMap<>();

            em = getEm();
        try {
            em.getTransaction().begin();

            Users u = em.createQuery("SELECT u FROM model.Users u WHERE u.login=:login and u.password=:password", Users.class)
                    .setParameter("login", users.getLogin())
                    .setParameter("password", users.getPassword())
                    .getSingleResult();

//            result.put("usersId", u.getUsersId());
//            result.put("secretKey", u.getSecretKey());

            em.getTransaction().commit();

//            return new Gson().toJson(result);
            u.setLogin("");
            u.setPassword("");
            return new Gson().toJson(u);
        }catch (NoResultException e){
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
            return "Error2";
        }catch (Exception e){
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
            return "Error3";
        }
        finally {
            em.close();
        }
    }

    @Override
    public void updateUser(Users users) throws SQLException {

    }

    @Override
    public Users getUserById(Long id) throws SQLException {
        return null;
    }

    @Override
    public List getAllUsers() throws SQLException {
        return Factory.getInstance().getGenericRepositoryInterface(Users.class).getAllObjects();
    }

    @Override
    public void deleteUser(Users users) throws SQLException {

    }
}
