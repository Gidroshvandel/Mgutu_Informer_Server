import controller.mobile.MobileRoutes;
import controller.site.SiteRoutes;
import controller.site.admin.AdministrationRoutes;
import controller.mobile.api.users.UsersRoutes;
import dao.Factory;
import model.Users;

import java.sql.SQLException;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {
    public static void main(String[] args){

        staticFileLocation("/public");
//        port(5555);
        int port;
        port = Integer.parseInt(args[0]);
        port(port);
        try {
            Factory.getInstance().getUsersDAO().addUser(new Users("admin", null, "admin", "root", false));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        new MobileRoutes();
        new SiteRoutes();
        new AdministrationRoutes();
    }
}
