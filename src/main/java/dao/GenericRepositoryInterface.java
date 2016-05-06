package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GenericRepositoryInterface<T> {
    Boolean addObject(T emp);
    Boolean removeObject(T emp);
    Boolean editObject(Object emp);
    T getObject(Map<String,Object> map);
    T getObject(String columnName, Object columnValue);
    List<T> getAllObjects();
    List<T> getObjects(Map<String,Object> map);
}
