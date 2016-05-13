package controller.site.admin;

import controller.BaseRoutes;
import controller.logic.Api;
import controller.logic.LessonTimeController;
import controller.logic.ScheduleSite;
import controller.site.admin.api.*;
import model.*;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.logging.Logger;

import static spark.Spark.*;

public class AdministrationRoutes extends BaseRoutes {

    private static Logger log = Logger.getLogger(AdministrationRoutes.class.getName());

    private final String ROOT = "/admin/";

    private void initRoutes(){
        new DisciplineApi();
        new GroupsApi();
        new LectureHallApi();
        new LessonTimeApi();
        new ScheduleApi();
        new TeacherApi();
        new EmploymentTypeApi();
    }

    @Override
    public void routes() {
        initRoutes();

        before("/admin/*", (request, response) -> {
            if(request.session().attribute("username") == null) {
                response.redirect("/authorization");
            }else{
                if(!request.session().attribute("username").equals("admin")){
                    response.redirect("/authorization");
                }
            }
        });

        get(ROOT, (request, response) -> {
           return new ModelAndView(new HashMap<>(), "/public/admin/index.html");
        }, new VelocityTemplateEngine());

        get(ROOT+"schedule", (request, response) -> {
            return new ModelAndView(ScheduleSite.getSchedule(request.queryParams("name"),request.queryParams("numberWeekDay")), "/public/admin/schedule_new.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "groups", (request, response) -> {
            String query = request.queryParams("type");
            if(query.equals("list")){
                return new ModelAndView(Api.getHashMapObjects(Groups.class), "/public/admin/groups_list.html");
            }else if(query.equals("edit")){
                return new ModelAndView(Api.getHashMapObjects(Groups.class), "/public/admin/group.html");
            }
            else {
                return null;
            }
        }, new VelocityTemplateEngine());

        get(ROOT + "users", (request, response) -> {
            return new ModelAndView(Api.getHashMapObjects(Users.class), "/public/admin/users.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "teacher", (request, response) -> {
            return new ModelAndView(Api.getHashMapObjects(Teacher.class), "/public/admin/teacher.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "discipline", (request, response) -> {
            return new ModelAndView(Api.getHashMapObjects(Discipline.class), "/public/admin/discipline.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "lectureHall", (request, response) -> {
            return new ModelAndView(Api.getHashMapObjects(LectureHall.class), "/public/admin/lectureHall.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "employmentType", (request, response) -> {
            return new ModelAndView(Api.getHashMapObjects(EmploymentType.class), "/public/admin/employment_type.html");
        }, new VelocityTemplateEngine());

        get(ROOT + "time", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put(LessonTime.class.getName().substring(6), new LessonTime());
            model.put(LessonTime.class.getName().substring(6)+"List", LessonTimeController.lessonTimeTransform(Api.getObjectList(LessonTime.class)));
            return new ModelAndView(model, "/public/admin/lesson_time.html");
        }, new VelocityTemplateEngine());
    }
}
