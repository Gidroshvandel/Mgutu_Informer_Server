package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.logic.ScheduleSite;
import dao.Factory;
import model.Groups;
import model.LessonTime;
import model.json.Schedule;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class SubApiRoutes extends BaseRoutes {

    private static Logger log = Logger.getLogger(SiteRoutes.class.getName());

    private final String ROOT = "/api/";

    public void routes() {
        post(ROOT+"lessonTime.add", (request, response) -> {
            log.info("Starting /api/addLessonTime");
            try {
                LessonTime lessonTime = new LessonTime(Double.parseDouble(request.queryParams("timeStart")), Double.parseDouble(request.queryParams("timeEnd")));
                response.redirect("/schedule");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(lessonTime);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT+"groups.add", (request, response) -> {
            log.info("Starting /api/addGroups");
            String groupsName = request.queryParams("groupsName");
            try {
                Groups addGroups = new Groups(groupsName);
                response.redirect("/");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(addGroups);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT+"groups.delete", (request, response) -> {
            log.info("Starting /api/deleteGroups");
            String a =request.queryParams("name");
            Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", a));
            response.redirect("/");
            return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
        });

        post(ROOT+"schedule.set", (request, response) -> {
            log.info("Starting /api/setSchedule");
            Gson gson = new GsonBuilder().create();
            String a = request.queryParams("name");
            Schedule schedule = new Schedule();
            try {
                schedule = gson.fromJson(a, Schedule.class);
                ScheduleSite.setSchedule(schedule);
                return "";
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
