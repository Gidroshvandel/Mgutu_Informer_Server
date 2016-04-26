package controller.api;

import controller.BaseController;
import dao.Factory;
import model.*;
import org.apache.velocity.runtime.directive.Foreach;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import javax.persistence.Convert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;
public class SiteRoutes extends BaseController{
    private String groupsColumn = "groupsName";
    private String formOfTrainingColumn = "formOfTrainingName";

    private HashMap getGroupsName(){
        HashMap<String, Object> model = new HashMap<>();

        List<Groups> listGroups = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
        List<String> groupsNameList = listGroups.stream().map(groupsLists -> groupsLists.getGroupsName()).collect(Collectors.toList());

        model.put("formOfTrainingName", new String());
        model.put("formOfTrainingNameArray", groupsNameList);
        return  model;
    }

    private HashMap getFormParametrName(String modelColumnName, String groupsName){
        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(modelColumnName, groupsName));

//        model.cast(Factory.getInstance().getGenericRepositoryInterface(model).getObject(modelColumnName,columnValue));

        HashMap<String, Object> model = new HashMap<>();
        List<Groups> groupsList = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
//        List<T> modelList = Factory.getInstance().getGenericRepositoryInterface(model).getAllObjects();
        for(Groups groupsLists : groupsList ){

            if(groupsLists.getGroupsId().equals(groups.getGroupsId())){
            }
            else {
                groupsList.remove(groupsLists.getGroupsId());
            }

        }

        model.put("formOfTrainingName", new String());
        model.put("formOfTrainingNameArray", groupsList);
        return  model;
    }

    @Override
    public void routes() {

        get("/advice_form", (request, response) -> {
            System.out.println("Выполняется /advice_form");
            return new ModelAndView(new HashMap(), "/public/templates/form.vtl");
        }, new VelocityTemplateEngine());

        get("/moveToCourse", (request, response) -> {
            System.out.println("Выполняется /moveToCourse");
          //  formCourseName(request.queryParams("formOfTrainingName"));
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/index.html");
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

//        post("/addFormOfTraining",(request, response) -> {
//            FormOfTraining formOfTraining = new FormOfTraining(request.queryParams(formOfTrainingColumn));
//            Factory.getInstance().getGenericRepositoryInterface().addObject(formOfTraining);
//        return new ModelAndView(formOfTrainingName(), "/public/index.html");
//        }, new VelocityTemplateEngine());
//
//        post("/deleteFormOfTraining",(request, response) -> {
//           FormOfTraining formOfTraining = FormOfTraining.class.cast(Factory.getInstance().getGenericRepositoryInterface(FormOfTraining.class).getObject(formOfTrainingColumn ,request.queryParams(formOfTrainingColumn)));
//            Factory.getInstance().getGenericRepositoryInterface().removeObject(formOfTraining);
//            return new ModelAndView(formOfTrainingName(), "/public/index.html");
//        }, new VelocityTemplateEngine());

        get("/",(request, response) -> {
            return new ModelAndView(getGroupsName(),"/public/index.html");
        }, new VelocityTemplateEngine());

        get("/index",(request, response) -> {
            return new ModelAndView(getGroupsName(),"/public/index.html");
        }, new VelocityTemplateEngine());

        post("/addGroups", (request, response) -> {
            Groups addGroups = new Groups(request.queryParams(groupsColumn));
            response.redirect("/index");
            return Factory.getInstance().getGenericRepositoryInterface().addObject(addGroups);

        });

        post("/find", (request, response) -> Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", request.queryParams("groupsName")));

        post("/deleteGroups", (request, response) -> {
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn ,request.queryParams("groupsName")));
            response.redirect("/index");
           return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
        });

        post("/users/registration", (request, response) -> {
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn,request.queryParams(groupsColumn)));
            Users registration = new Users(request.queryParams("name"), groups, request.queryParams("login"), request.queryParams("password"), Boolean.parseBoolean(request.queryParams("student")));
            return Factory.getInstance().getUsersDAO().addUser(registration);
        });

        post("/users/login", (request, response) -> {

            Users login = new Users(request.queryParams("login"), request.queryParams("password"));

           return Factory.getInstance().getUsersDAO().loginUsers(login);
//            UserController userController = new UserController(login);
//            userController.setHashPassword();
//            return userController.userLogin();
        });
    }
}
