package controller.api;

import controller.BaseController;
import controller.logic.UserController;
import dao.Factory;
import dao.UsersDAO;
import model.Groups;
import model.Users;
import org.hibernate.NonUniqueResultException;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;

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
//            model.put("id", advice.getUsersId());
//            model.put("category", advice.getCategory());
//            model.put("text", advice.getText());
//            model.put("rating", advice.getRating());
//            return new ModelAndView(model, "/public/templates/last_advice.vtl");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());


        get("/index",(request, response) -> {
            HashMap<String, Object> model = new HashMap<>();

            //// TODO: 17.02.2016 обёртка
            model.put("groupsName", new String());
            model.put("groupsNameArray", Factory.getInstance().getGroupsDAO().getAllGroups());


            return new ModelAndView(model,"/public/index.html");
        }, new VelocityTemplateEngine());

        post("/addGroups", (request, response) -> {
            Groups addGroups = new Groups(request.queryParams("groupsName"));
            return Factory.getInstance().getGroupsDAO().addGroup(addGroups);
        });

        post("/deleteGroups", (request, response) -> {
            Groups deleteGroups = new Groups(request.queryParams("groupsName"));
            return Factory.getInstance().getGroupsDAO().deleteGroup(deleteGroups);
        });

        post("/users/registration", (request, response) -> {
            Groups groups = Factory.getInstance().getGroupsDAO().getGroupByName(request.queryParams("groupsName"));
            Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            return Factory.getInstance().getUsersDAO().addUser(registration);
        });



//        post("/add_advice", (request, response) -> {
//            System.out.println("Выполняется /add_advice");
//            HashMap<String, Object> model = new HashMap<>();
//            Advice advice = new Advice(request.queryParams("text"), request.queryParams("category"));
//            AdviceController adviceController = new AdviceController(advice);
//            adviceController.addToDataBase();
//            model.put("id", advice.getUsersId());
//            model.put("category", advice.getCategory());
//            model.put("text", advice.getText());
//            model.put("rating", advice.getRating());
//            return new ModelAndView(model, "/public/templates/last_advice.vtl");
//        }, new VelocityTemplateEngine());

        post("/users/login", (request, response) -> {

            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

           return Factory.getInstance().getUsersDAO().loginUsers(login);
//            UserController userController = new UserController(login);
//            userController.setHashPassword();
//            return userController.userLogin();
        });
    }
}
