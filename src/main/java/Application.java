import controller.site.MobileRoutes;
import controller.site.SiteRoutes;
import controller.site.admin.AdministrationRoutes;
import controller.site.users.UsersRoutes;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {
    public static void main(String[] args){

        staticFileLocation("/public");
//        port(5555);
        int port;
        port = Integer.parseInt(args[0]);
        port(port);
        new MobileRoutes();
        new SiteRoutes();
        new AdministrationRoutes();
        new UsersRoutes();
    }
}
