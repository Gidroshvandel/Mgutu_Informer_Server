package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.Discipline;
import model.Teacher;

import java.util.logging.Logger;

import static spark.Spark.post;

public class DisciplineApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(DisciplineApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT + "discipline.post", (request, response) -> {
            response.redirect(ROOT.substring(0,7) + "discipline");
            return Factory.getInstance().getGenericRepositoryInterface().addObject(new Discipline(request.queryParams("discipline_name")));
        });

        post(ROOT + "discipline.delete", (request, response) -> {
            response.redirect(ROOT.substring(0,7) + "discipline");
            return Factory.getInstance().getGenericRepositoryInterface().removeObject(
                    Factory.getInstance().getGenericRepositoryInterface(Discipline.class).getObject("disciplineName",request.queryParams("discipline_name")));
        });
    }
}
