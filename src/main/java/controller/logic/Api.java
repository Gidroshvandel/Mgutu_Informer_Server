package controller.logic;

import dao.Factory;
import model.Groups;

import java.util.HashMap;
import java.util.List;

public class Api {
    public static  <T> HashMap<String,Object> getHashMapObjects(Class<T> tClass){

        HashMap<String, Object> model = new HashMap<String, Object>();
        List<T> tModel = Factory.getInstance().getGenericRepositoryInterface(tClass).getAllObjects();
        model.put(tClass.getName().substring(6), tClass);
        model.put(tClass.getName().substring(6)+"List", tModel);

        return model;
    }
    public static  <T> List<T> getObjectList(Class<T> tClass){

        HashMap<String, Object> model = new HashMap<String, Object>();
        List<T> tModel = Factory.getInstance().getGenericRepositoryInterface(tClass).getAllObjects();

        return tModel;
    }
}
