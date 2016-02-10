package controller.api;

import controller.BaseController;
import controller.logic.UserController;
import model.Users;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class SiteRoutes extends BaseController{
    @Override
    public void routes() {
        get("/advice_form", (request, response) -> {
            System.out.println("Выполняется /advice_form");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());
        get("/index",(request, response) -> {
            return new ModelAndView(new HashMap(),"/public/index.html");
        }, new VelocityTemplateEngine());
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
