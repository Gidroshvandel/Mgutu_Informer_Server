package controller;

import utils.EMF;

/**
 * Created by Gidro on 14.10.2015.
 */
public abstract class BaseController extends EMF {
    public BaseController(){
        routes();
    }

    abstract public void routes();
}
