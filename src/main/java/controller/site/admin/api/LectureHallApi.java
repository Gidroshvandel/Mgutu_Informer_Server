package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.LectureHall;
import model.Teacher;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class LectureHallApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(LectureHallApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT + "lectureHall.post", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "lectureHall");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(new LectureHall(request.queryParams("lectureHall_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT + "lectureHall.delete", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "lectureHall");
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(
                        Factory.getInstance().getGenericRepositoryInterface(LectureHall.class).getObject("lectureHallName",request.queryParams("lectureHall_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
