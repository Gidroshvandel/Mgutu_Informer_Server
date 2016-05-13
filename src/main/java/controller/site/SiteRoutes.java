package controller.site;

import controller.BaseRoutes;
import controller.site.api.UsersApi;
import dao.Factory;
import model.Groups;
import spark.ModelAndView;
import utils.template.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static spark.Spark.get;


public class SiteRoutes extends BaseRoutes {
    private static Logger log = Logger.getLogger(SiteRoutes.class.getName());

    private final String ROOT = "/";

    private void initRoutes(){
        new UsersApi();
    }

    @Override
    public void routes() {
        initRoutes();
        get(ROOT, (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            List<Groups> listGroups = Factory.getInstance().getGenericRepositoryInterface(Groups.class).getAllObjects();
            model.put("groups", new String());
            model.put("groupsList", listGroups);
            return new ModelAndView(model, "/public/index.html");
        }, new VelocityTemplateEngine());

        get(ROOT+"authorization", (request, response) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "/public/authorization.html");
        }, new VelocityTemplateEngine());
    }

}
