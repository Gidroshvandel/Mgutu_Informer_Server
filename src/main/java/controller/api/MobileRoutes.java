package controller.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseController;
import dao.Factory;
import model.Schedule;
import java.util.List;

import static spark.Spark.get;

/**
 * Created by Gidro on 14.10.2015.
 */
public class MobileRoutes extends BaseController {
    @Override
    public void routes() {
        get("/string", (request, response) -> "Hello world");
        get("/API/schedule.get", (request, response) -> {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            List<Schedule> a = Factory.getInstance().getGenericRepositoryInterface(Schedule.class).getAllObjects();
            return gson.toJson(a);
        });
    }

}

