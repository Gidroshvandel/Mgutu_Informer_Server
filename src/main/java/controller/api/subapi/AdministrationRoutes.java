package controller.api.subapi;

import controller.BaseController;
import dao.Factory;
import model.Groups;
import model.Teacher;
import model.Users;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.*;

public class AdministrationRoutes extends BaseController{

    private final String ROOT = "/admin/";

    @Override
    public void routes() {
        get(ROOT, (request, response) -> {
           return new ModelAndView(new HashMap<>(), "/public/admin/index.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "group", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Groups> groupsList = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
            model.put("group", new Groups());
            model.put("groupList", groupsList);
            return new ModelAndView(model, "/public/admin/group.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "teacher", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Teacher> teachers = Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getAllObjects();
            model.put("teacher", new Teacher());
            model.put("teacherList", teachers);
            return new ModelAndView(model, "/public/admin/teacher.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "users", (request, response) -> {
            HashMap<String, Object> model = new HashMap<String, Object>();
            List<Users>  users = Factory.getInstance().getGenericRepositoryInterface(Users.class).getAllObjects();
            model.put("user", new Users());
            model.put("userList", users);
            return new ModelAndView(model, "/public/admin/users.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "add_schedule", (request, response) -> {
            return new ModelAndView(new HashMap<>(), "/public/admin/add_schedule.html");
        }, new VelocityTemplateEngine());

        post(ROOT + "add_teacher", (request, response) -> {
            response.redirect("/teacher");
            return Factory.getInstance().getTeacherDAO().addTeacher(new Teacher(request.queryParams("teacher_name")));
        });

        post(ROOT + "delete_teacher", (request, response) -> {
            response.redirect("/teacher");
            return Factory.getInstance().getTeacherDAO().deleteTeacher(Factory.getInstance().getTeacherDAO().getTeacherByName(request.queryParams("teacher_name")));
        });

        post(ROOT + "add_group", (request, response) -> {
            response.redirect("/group");
            return Factory.getInstance().getGroupsDAO().addGroup(new Groups(request.queryParams("groups_name")));
        });

        post(ROOT + "delete_group", (request, response) -> {
            System.out.println("delete group");
            response.redirect("/group");
            return Factory.getInstance().getGroupsDAO().deleteGroup(Factory.getInstance().getGroupsDAO().getGroupByName(request.queryParams("groups_name")));
        });
    }
}
