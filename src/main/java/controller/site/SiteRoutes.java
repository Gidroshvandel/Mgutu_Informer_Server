package controller.site;

import controller.BaseRoutes;
import controller.site.admin.AdministrationRoutes;
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

    private void initRoutes(){

    }

    @Override
    public void routes() {
        initRoutes();
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
