package controller;

import utils.EMF;

/**
 * Created by Gidro on 14.10.2015.
 */
public abstract class BaseRoutes extends EMF {
    public BaseRoutes(){
        routes();
    }

    abstract public void routes();
}
