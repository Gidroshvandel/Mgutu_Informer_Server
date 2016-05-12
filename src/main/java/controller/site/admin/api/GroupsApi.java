package controller.site.admin.api;

import controller.BaseRoutes;
import dao.Factory;
import model.Groups;

import java.util.logging.Level;
import java.util.logging.Logger;

import static spark.Spark.post;

public class GroupsApi extends BaseRoutes {
    private static Logger log = Logger.getLogger(GroupsApi.class.getName());

    private final String ROOT = "/admin/api/";

    @Override
    public void routes() {
        post(ROOT+"groups.post", (request, response) -> {
            String groupsName = request.queryParams("groups_name");
            try {
                Groups addGroups = new Groups(groupsName);
                response.redirect(ROOT.substring(0,7) + "groups?type=edit");
                return Factory.getInstance().getGenericRepositoryInterface().addObject(addGroups);
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });

        post(ROOT+"groups.delete", (request, response) -> {
            String a = request.queryParams("groups_name");
            try {
                response.redirect(ROOT.substring(0,7) + "groups?type=edit");
                Groups groups = Groups.class.cast(Factory.getInstance().getGenericRepositoryInterface(Groups.class).getObject("groupsName", a));
                return Factory.getInstance().getGenericRepositoryInterface().removeObject(groups);
            }catch (Exception e){
                log.log(Level.SEVERE, "Exception: ", e);
                return e;
            }
        });
    }
}
