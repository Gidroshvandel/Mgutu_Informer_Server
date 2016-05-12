package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.LessonTime;
import utils.Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class LessonTimeApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(LessonTimeApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT+"lessonTime.post", (request, response) -> {
            try {
                String time = request.queryParams("time");
                LessonTime lessonTime = new LessonTime(Converter.startToDouble(time), Converter.endToDouble(time));
                response.redirect(ROOT.substring(0,7) + "time");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(lessonTime);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT + "lessonTime.delete", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "time");
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(
                        Factory.getInstance().getGenericRepositoryInterface(LessonTime.class).getObject("lessonTimeStart",Converter.startToDouble(request.queryParams("time"))));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
