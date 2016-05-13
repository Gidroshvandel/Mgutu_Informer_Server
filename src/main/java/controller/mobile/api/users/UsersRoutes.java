package controller.mobile.api.users;

import controller.BaseRoutes;
import dao.Factory;
import model.Groups;
import model.Users;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class UsersRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(UsersRoutes.class.getName());

        private final String ROOT = "/api/users/";

    public void routes() {
        post(ROOT+"registration", (request, response) -> {
            try {
                Boolean student =  Boolean.parseBoolean(request.queryParams("student"));
                Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", request.queryParams("groupsName")));
                if(student = true && groups != null) {
                    Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), student);
                    return Factory.getInstance().getUsersDAO().addUser(registration);
                }else {
                    if (student = false && groups == null) {
                        Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), student);
                        return Factory.getInstance().getUsersDAO().addUser(registration);
                    }
                    else {
                        return null;
                    }
                }
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT + "login", (request, response) -> {
            try {
                Users login = new Users(request.queryParams("login"), request.queryParams("password"));
                return Factory.getInstance().getUsersDAO().loginUsers(login);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
