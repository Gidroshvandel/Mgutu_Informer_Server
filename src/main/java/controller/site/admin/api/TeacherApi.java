package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.Teacher;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class TeacherApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(TeacherApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT + "teacher.post", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "teacher");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(new Teacher(request.queryParams("teacher_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT + "teacher.delete", (request, response) -> {
            try {
                response.redirect(ROOT.substring(0,7) + "teacher");
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(
                        Factory.getInstance().getGenericRepositoryInterface(Teacher.class).getObject("teacherName",request.queryParams("teacher_name")));
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
