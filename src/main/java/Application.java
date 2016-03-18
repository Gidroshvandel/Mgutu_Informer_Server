import controller.api.MobileRoutes;
import controller.api.SiteRoutes;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {
    public static void main(String[] args){

        staticFileLocation("/public");
        port(5555);
        new MobileRoutes();
        new SiteRoutes();
    }
}
