import controller.api.MobileRoutes;
import controller.api.SiteRoutes;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {
    public static void main(String[] args){

        staticFileLocation("/public");
//        port(3132);
        int port;
        port = Integer.parseInt(args[0]);
        port(port);
        new MobileRoutes();
        SiteRoutes siteRoutes = new SiteRoutes();
        siteRoutes.initRoutes();
    }
}
