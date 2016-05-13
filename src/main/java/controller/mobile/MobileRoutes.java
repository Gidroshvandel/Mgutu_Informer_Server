package controller.mobile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.BaseRoutes;
import controller.mobile.api.users.UsersRoutes;
import controller.site.admin.api.*;
import dao.Factory;
import model.Schedule;
import java.util.List;

import static spark.Spark.get;

/**
 * Created by Gidro on 14.10.2015.
 */
public class MobileRoutes extends BaseRoutes {


    private void initRoutes(){
        new UsersRoutes();
    }
    @Override
    public void routes() {
        initRoutes();

        get("/string", (request, response) -> "Hello world");
        get("/site/schedule.get", (request, response) -> {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            List<Schedule> a = Factory.getInstance().getGenericRepositoryInterface(Schedule.class).getAllObjects();
            return gson.toJson(a);
        });
    }

}

