package controller.logic;

import com.google.gson.Gson;
import model.Users;
import utils.EMF;

import javax.persistence.NoResultException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends EMF {

    private Users users = null;
//    private HashWithSalt hash = null;

    public UserController(Users users) throws InvalidKeySpecException, NoSuchAlgorithmException {
        this.users = users;
//        this.hash = new HashWithSalt(users.getPassword(), users.getName());
    }

    public String userRegistration(){

        Map<String, Object> result = new HashMap<>();

        if (em == null){
            em = getEm();
        }
        try {
            em.getTransaction().begin();

            try {
                em.createQuery("SELECT users FROM model.Users users WHERE users.name=:name")
                        .setParameter("name", users.getName())
                        .getSingleResult();
            }catch (NoResultException e){
                em.persist(users);
                result.put("userId", users.getId());
            }

            em.getTransaction().commit();

            return new Gson().toJson(result);

        } catch (Exception e) {
            if (em.getTransaction() != null){
                em.getTransaction().rollback();
            }
            return "Error1"; //Ошибка записи пользователя
        } finally {
            em.close();
        }
    }

    public String userLogin(){

        Map<String, Object> result = new HashMap<>();

        if (em ==null){
            em = getEm();
        }
        try {
            em.getTransaction().begin();

            Users u = em.createQuery("SELECT user FROM model.User user WHERE user.login=:login and user.password=:password", Users.class)
                    .setParameter("login", users.getLogin())
                    .setParameter("password", users.getPassword())
                    .getSingleResult();

            result.put("userId", u.getId());
           // result.put("secretKey", u.getSecretKey());

            em.getTransaction().commit();

            return new Gson().toJson(result);
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
        }finally {
            em.close();
        }
    }

}
