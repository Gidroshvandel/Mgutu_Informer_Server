package controller.api;

import controller.BaseController;
import controller.logic.UserController;
import model.Groups;
import model.Users;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static spark.Spark.get;
import static spark.Spark.post;

public class SiteRoutes extends BaseController{
    @Override
    public void routes() {

        get("/advice_form", (request, response) -> {
            System.out.println("Выполняется /advice_form");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());
        get("/add", (request, response) -> {
            System.out.println("Выполняется /advice_form");
            HashMap<String, Object> model = new HashMap<>();
//            Advice advice = new Advice(request.queryParams("text"), request.queryParams("category"));
//            AdviceController adviceController = new AdviceController(advice);
//            adviceController.addToDataBase();
//            model.put("id", advice.getId());
//            model.put("category", advice.getCategory());
//            model.put("text", advice.getText());
//            model.put("rating", advice.getRating());
//            return new ModelAndView(model, "/public/templates/last_advice.vtl");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());
        get("/index",(request, response) -> {
            return new ModelAndView(new HashMap(),"/public/index.html");
        }, new VelocityTemplateEngine());
        post("/users/registration",(request, response) -> {
            Groups groups = new Groups();
            groups.setGroupsName(request.queryParams("groupsName"));
            Users registration = new Users(request.queryParams("name"),groups,request.queryParams("login"),request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            UserController userController = new UserController(registration);
            userController.setHashPassword();
            return userController.userRegistration();
        });



//        post("/add_advice", (request, response) -> {
//            System.out.println("Выполняется /add_advice");
//            HashMap<String, Object> model = new HashMap<>();
//            Advice advice = new Advice(request.queryParams("text"), request.queryParams("category"));
//            AdviceController adviceController = new AdviceController(advice);
//            adviceController.addToDataBase();
//            model.put("id", advice.getId());
//            model.put("category", advice.getCategory());
//            model.put("text", advice.getText());
//            model.put("rating", advice.getRating());
//            return new ModelAndView(model, "/public/templates/last_advice.vtl");
//        }, new VelocityTemplateEngine());

        post("/users/login", (request, response) -> {

            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

            UserController userController = new UserController(login);
            userController.setHashPassword();
            return userController.userLogin();
        });
    }
}
