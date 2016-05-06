package controller.site.admin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.logic.ScheduleSite;
import model.json.Schedule;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class ScheduleApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(ScheduleApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT+"schedule.post", (request, response) -> {
            log.info("Starting /site/setSchedule");
            Gson gson = new GsonBuilder().create();
            String a = request.queryParams("name");
            Schedule schedule = new Schedule();
            try {
                schedule = gson.fromJson(a, Schedule.class);
                ScheduleSite.setSchedule(schedule);
                response.redirect(ROOT.substring(0,7) + "schedule");
                return "";
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
