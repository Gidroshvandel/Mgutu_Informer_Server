package controller.site.admin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.logic.ScheduleSite;
import model.json.Schedule;
import org.eclipse.jetty.server.HttpOutput;
import org.eclipse.jetty.server.Utf8HttpWriter;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
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
            Map<String,String> cookies = request.cookies();
            Schedule schedule = new Schedule();
            try {
                schedule = gson.fromJson(a, Schedule.class);
                ScheduleSite.setSchedule(schedule);
                response.redirect(ROOT.substring(0,7) + "schedule?name="+cookies.get("nameCookie")+"&numberWeekDay="+ cookies.get("numberWeekDay"));
                return "";
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
