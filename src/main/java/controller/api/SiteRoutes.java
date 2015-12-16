package controller.api;

import controller.BaseController;
import model.User;
import sun.security.util.PendingException;

import javax.persistence.PersistenceException;

import static spark.Spark.get;

/**
 * Created by Gidro on 14.10.2015.
 */
public class SiteRoutes extends BaseController{
    @Override
    public void routes() {
        get("/",(request, response) -> {
            User user = new User("1","2");
            em = getEm();
            try{
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
            }
            catch (PersistenceException e){
                em.getTransaction().rollback();
                throw  new RuntimeException("Не удалось");
            }
            return "Ok";
        });
    }
}
