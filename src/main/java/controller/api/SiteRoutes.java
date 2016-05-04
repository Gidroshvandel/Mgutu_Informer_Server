package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.api.admin.AdministrationRoutes;
import controller.api.users.UsersRouts;
import controller.logic.ScheduleSite;
import dao.Factory;
import model.*;
import model.json.Schedule;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.get;
import static spark.Spark.post;


public class SiteRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(SiteRoutes.class.getName());

    public void initRoutes(){
        new AdministrationRoutes();
        new UsersRouts();
        new SubApiRoutes();
    }

    @Override
    public void routes() {
        initRoutes();
        get("/schedule", (request, response) -> {
            log.info("Starting /schedule");
            return new ModelAndView(ScheduleSite.getSchedule(), "/public/schedule.html");
        }, new VelocityTemplateEngine());

        get("/", (request, response) -> {
            log.info("Starting /");
            HashMap<String, Object> model = new HashMap<>();
            List<Groups> listGroups = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
            model.put("groups", new String());
            model.put("groupsList", listGroups);
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());

    }
}
