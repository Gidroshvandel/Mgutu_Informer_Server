package controller.site.admin.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.logic.ScheduleSite;
import dao.Factory;
import model.Groups;
import model.LessonTime;
import model.NumberWeekday;
import model.Weekday;
import model.json.Schedule;
import model.json.WeekDay;
import org.eclipse.jetty.server.HttpOutput;
import org.eclipse.jetty.server.Utf8HttpWriter;
import utils.Converter;

import javax.persistence.Convert;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.logic.ScheduleSite.convertNumberWeekDay;
import static spark.Spark.post;

public class ScheduleApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(ScheduleApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT+"schedule.post", (request, response) -> {
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
        post(ROOT+"schedule.delete", (request, response) -> {
            String a = request.queryParams("name");
            String[] b = a.split("\\|");
            Map<String,String> cookies = request.cookies();
            Map<String,Object> map = new HashMap<>();
            try {
                String result = java.net.URLDecoder.decode(cookies.get("nameCookie"), "UTF-8");
                map.put("groups",Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName",result));
                map.put("numberWeekday", convertNumberWeekDay(cookies.get("numberWeekDay")));
                map.put("weekday", Weekday.valueOf((b[0])));
                map.put("lessonTime", Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getObject("lessonTimeStart", Converter.startToDouble(b[1])));
                response.redirect(ROOT.substring(0,7) + "schedule?name="+cookies.get("nameCookie")+"&numberWeekDay="+ cookies.get("numberWeekDay"));
                model.Schedule schedule = model.Schedule.class.cast(Factory.getInstance().getGenericRepositoryInterface(model.Schedule.class).getObject(map));
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(schedule);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
