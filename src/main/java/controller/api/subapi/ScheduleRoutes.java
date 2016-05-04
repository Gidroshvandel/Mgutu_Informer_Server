package controller.api.subapi;

import controller.BaseController;
import dao.Factory;
import model.Groups;
import model.Teacher;

import static spark.Spark.post;

public class ScheduleRoutes extends BaseController{

    @Override
    public void routes() {
        post("add_teacher", (request, response) -> {
            response.redirect("/teacher");
            return Factory.getInstance().getTeacherDAO().addTeacher(new Teacher(request.queryParams("teacher_name")));
        });
        post("delete_teacher", (request, response) -> {
            response.redirect("/teacher");
            return Factory.getInstance().getTeacherDAO().deleteTeacher(Factory.getInstance().getTeacherDAO().getTeacherByName(request.queryParams("teacher_name")));
        });
        post("add_group", (request, response) -> {
            response.redirect("/group");
            return Factory.getInstance().getGroupsDAO().addGroup(new Groups(request.queryParams("groups_name")));
        });
        post("delete_group", (request, response) -> {
            System.out.println("delete group");
            response.redirect("/group");
            return Factory.getInstance().getGroupsDAO().deleteGroup(Factory.getInstance().getGroupsDAO().getGroupByName(request.queryParams("groups_name")));
        });
    }
}
