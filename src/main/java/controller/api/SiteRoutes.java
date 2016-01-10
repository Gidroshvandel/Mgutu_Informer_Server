package controller.api;

import controller.BaseController;
import controller.logic.UserController;
import model.Users;
import org.apache.velocity.runtime.directive.Parse;

import static spark.Spark.get;
import static spark.Spark.post;

public class SiteRoutes extends BaseController{
    @Override
    public void routes() {
        post("/users/registration",(request, response) -> {
            Users registration = new Users(request.queryParams("name"),request.queryParams("groups"),request.queryParams("login"),request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            UserController userController = new UserController(registration);
            userController.setHashPassword();
            return userController.userRegistration();
        });

        post("/users/login", (request, response) -> {

            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

            UserController userController = new UserController(login);
            userController.setHashPassword();
            return userController.userLogin();
        });
    }
}
