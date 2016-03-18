package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.corba.se.spi.presentation.rmi.PresentationManager;
import controller.BaseController;
import dao.Factory;
import gson.serialize.ScheduleSerializer;
import model.Schedule;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;

import static spark.Spark.get;

/**
 * Created by Gidro on 14.10.2015.
 */
public class MobileRoutes extends BaseController {
    @Override
    public void routes() {
        get("/string", (request, response) -> "Hello world");
        get("/schedule", (request, response) -> {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            List<Schedule> a = Factory.getInstance().getGenericRepositoryInterface(Schedule.class).getAllObjects();
            return gson.toJson(a);
        });
    }

}

