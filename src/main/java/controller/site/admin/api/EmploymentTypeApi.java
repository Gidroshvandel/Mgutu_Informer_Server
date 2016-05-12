package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.Discipline;
import model.EmploymentType;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class EmploymentTypeApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(EmploymentTypeApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT + "employmentType.post", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "employmentType");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(new EmploymentType(request.queryParams("employmentType_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT + "employmentType.delete", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "employmentType");
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(
                        Factory.getInstance().getGenericRepositoryInterface(EmploymentType.class).getObject("employmentTypeName",request.queryParams("employmentType_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
