package controller.site.api;

import controller.BaseRoutes;
import dao.Factory;
import model.Users;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class UsersApi extends BaseRoutes {

    private static Logger log = Logger.getLogger(UsersApi.class.getName());

    private final String ROOT = "/api/";

    public void routes() {

        post(ROOT + "login", (request, response) -> {
            try {
                String login =request.queryParams("login");
                Users users = new Users(login, request.queryParams("password"));
                String responseV = Factory.getInstance().getUsersDAO().loginUsers(users);
                if(responseV.equals("Error2")||responseV.equals("Error3")){
                    request.session().attribute("username", null);
                    response.redirect("/admin/");
                    return responseV;
                }else{
                    request.session().attribute("username", login);
                    response.redirect("/admin/");
                    return responseV;
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
