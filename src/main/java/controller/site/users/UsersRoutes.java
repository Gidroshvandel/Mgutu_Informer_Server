package controller.site.users;

import controller.BaseRoutes;
import dao.Factory;
import model.Groups;
import model.Users;

import java.util.logging.Logger;

import static spark.Spark.post;

public class UsersRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(UsersRoutes.class.getName());

    private final String ROOT = "/api/users/";

    public void routes() {
        post(ROOT+"registration", (request, response) -> {
            log.info("Starting /api/users/registration");
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", request.queryParams("groupsName")));
            Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            return Factory.getInstance().getUsersDAO().addUser(registration);
        });

        post(ROOT + "login", (request, response) -> {
            log.info("Starting /api/users/login");
            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

            return Factory.getInstance().getUsersDAO().loginUsers(login);
        });
    }
}
