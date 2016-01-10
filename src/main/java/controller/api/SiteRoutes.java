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
        post("/test",(request, response) -> {
            Users registration = new Users(request.queryParams("name"),request.queryParams("groups"),request.queryParams("login"),request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            UserController userController = new UserController(registration);
//            userController.setHashPassword();
            return userController.userRegistration();
        });
    }
}
