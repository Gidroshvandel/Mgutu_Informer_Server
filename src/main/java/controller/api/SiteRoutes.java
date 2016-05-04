package controller.api;

import controller.BaseController;
import controller.api.subapi.ScheduleRoutes;
import dao.Factory;
import model.*;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static spark.Spark.get;
import static spark.Spark.post;

public class SiteRoutes extends BaseController {

    private String groupsColumn = "groupsName";
    private String formOfTrainingColumn = "formOfTrainingName";

//    private HashMap getGroupsName() {
//        HashMap<String, Object> model = new HashMap<>();
//
//        List<Groups> listGroups = null;
//        try {
//            listGroups = Factory.getInstance().getGroupsDAO().getAllGroups();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        List<String> groupsNameList = listGroups.stream().map(groupsLists -> groupsLists.getGroupsName()).collect(Collectors.toList());
//
//        model.put("formOfTrainingName", new String());
//        model.put("formOfTrainingNameArray", groupsNameList);
//        return model;
//    }

//    private HashMap getTeachersName() {
//        HashMap<String, Object> model = new HashMap<>();
//
//        List<Teacher> teachersList = null;
//        try {
//            teachersList = Factory.getInstance().getTeacherDAO().getAllTeachers();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        List<String> stringTeacherList = teachersList.stream().map(teacher -> teacher.getTeacherName()).collect(Collectors.toList());
//
//        model.put("teacherName", new String());
//        model.put("teacherNameList", stringTeacherList);
//        return model;
//    }

    private HashMap getFormParametrName(String modelColumnName, String groupsName) {
        Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(modelColumnName, groupsName));

//        model.cast(Factory.getInstance().getGenericRepositoryInterface(model).getObject(modelColumnName,columnValue));

        HashMap<String, Object> model = new HashMap<>();
        List<Groups> groupsList = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
//        List<T> modelList = Factory.getInstance().getGenericRepositoryInterface(model).getAllObjects();
        for (Groups groupsLists : groupsList) {

            if (groupsLists.getGroupsId().equals(groups.getGroupsId())) {
            } else {
                groupsList.remove(groupsLists.getGroupsId());
            }

        }

        model.put("formOfTrainingName", new String());
        model.put("formOfTrainingNameArray", groupsList);
        return model;
    }

    public void initRoutes() {
        new ScheduleRoutes();
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
            return new ModelAndView(model, "/public/group.html");
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
//        return new ModelAndView(formOfTrainingName(), "/public/group.html");
//        }, new VelocityTemplateEngine());
//
//        post("/deleteFormOfTraining",(request, response) -> {
//           FormOfTraining formOfTraining = FormOfTraining.class.cast(Factory.getInstance().getGenericRepositoryInterface(FormOfTraining.class).getObject(formOfTrainingColumn ,request.queryParams(formOfTrainingColumn)));
//            Factory.getInstance().getGenericRepositoryInterface().removeObject(formOfTraining);
//            return new ModelAndView(formOfTrainingName(), "/public/group.html");
//        }, new VelocityTemplateEngine());

        get("/", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "/public/index.html");
        }, new VelocityTemplateEngine());

        get("/group", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Groups> groupsList = Factory.getInstance().getGroupsDAO().getAllGroups();
            model.put("group", new Groups());
            model.put("groupList", groupsList);
            return new ModelAndView(model, "/public/group.html");
        }, new VelocityTemplateEngine());

        get("/teacher", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Teacher> teachers = Factory.getInstance().getTeacherDAO().getAllTeachers();
            model.put("teacher", new Teacher());
            model.put("teacherList", teachers);
            return new ModelAndView(model, "/public/teacher.html");
        }, new VelocityTemplateEngine());

        get("/users", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Users>  users = Factory.getInstance().getUsersDAO().getAllUsers();
            model.put("user", new Users());
            model.put("userList", users);
            return new ModelAndView(model, "/public/users.html");
        }, new VelocityTemplateEngine());

        get("/add_schedule", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "/public/add_schedule.html");
        }, new VelocityTemplateEngine());

        post("/find", (request, response) -> Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", request.queryParams("groupsName")));

        post("/users/registration", (request, response) -> {
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject(groupsColumn, request.queryParams(groupsColumn)));
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
